package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.auth.Csrf;
import controller.auth.Role;
import controller.router.Router;
import model.dao.EmployeeDAO;
import model.dao.UserDAO;
import model.object.user.Employee;
import model.object.user.Profil;
import model.object.user.User;

public class AddUserController extends HttpServlet {

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String pageName = "/view/user/add-user.jsp";
		Router.forward(pageName, this, request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.ADMIN))
			return;

		User user = new User("", "", "", "", "", "", "");
		req.setAttribute("user", user);
		this.doProcess(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (!Role.can(this, req, resp, Profil.ADMIN))
			return;

		String name = req.getParameter("name");
		String firstname = req.getParameter("firstname");
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String phoneNumber = req.getParameter("phoneNumber");

		User user = new User(name, firstname, address, phoneNumber, email, login, password);

		UserDAO userDAO = new UserDAO();
		userDAO.add(user);
		userDAO.closeConn();

		if (req.getParameter("isEmployee").equals("true")) {
			String employeeService = req.getParameter("employeeService");
			String employeeFunction = req.getParameter("employeeFunction");
			String profile = req.getParameter("profile");
			int deskNumber = Integer.parseInt(req.getParameter("deskNumber"));

			Employee employee = new Employee(name, firstname, address, phoneNumber, email, login, password,
					employeeFunction, employeeService, deskNumber, Profil.valueOf(profile));

			EmployeeDAO employeeDAO = new EmployeeDAO();

			employeeDAO.add(employee);
			employeeDAO.closeConn();

		}
		Router.redirect("/user/search", this, req, resp);
	}
}
