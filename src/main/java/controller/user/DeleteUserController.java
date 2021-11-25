package controller.user;

import model.dao.UserDAO;
import model.dao.EmployeeDAO;
import model.dao.LoanDAO;
import model.object.user.Profil;
import model.object.user.User;

import java.io.IOException;

import controller.auth.Role;
import controller.router.Router;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class DeleteUserController extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String pageName = "/user/search";

		Router.redirect(pageName, this, request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.FORBIDDEN))
			return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.ADMIN))
			return;
		
		JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
		String mail = data.get("mail").getAsString();

		UserDAO userDAO = new UserDAO();

		User user = userDAO.get(mail);

		if (user != null) {
			// create a new User in order to replace the user to delete by a fictitious
			// user.
			String login = userDAO.getRandomLogin();
			String randomEmail = userDAO.getRandomEmail();

			User substitute = new User("unknown", "unknown", "unknown", "unknown", randomEmail, login, "unknown");
			substitute.setReal(false);

			userDAO.add(substitute);

			// delete in Employee table if exists
			EmployeeDAO employeeDAO = new EmployeeDAO();
			employeeDAO.deleteById(mail);

			// replace in Loan table if exists
			LoanDAO loanDAO = new LoanDAO();
			loanDAO.replaceUser(mail, substitute);
			userDAO.deleteById(mail);
			loanDAO.closeConn();
			employeeDAO.closeConn();

		}
		
		userDAO.closeConn();
		this.doProcess(req, resp);
	}
}
