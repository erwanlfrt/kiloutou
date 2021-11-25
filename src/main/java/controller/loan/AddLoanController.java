package controller.loan;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.auth.Role;
import controller.router.Router;
import model.dao.EquipmentDAO;
import model.dao.LoanDAO;
import model.dao.UserDAO;
import model.object.equipment.Equipment;
import model.object.loan.Loan;
import model.object.user.Profil;
import model.object.user.User;

// import com.google.gson.Gson;

public class AddLoanController extends HttpServlet {

	// private Gson gson;

	private EquipmentDAO eDAO;
	private LoanDAO loanDAO;
	private UserDAO userDAO;

	public AddLoanController() {
		this.eDAO = new EquipmentDAO();
		this.userDAO = new UserDAO();
		this.loanDAO = new LoanDAO();
		// this.gson = new Gson();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<User> users = this.userDAO.listAll();

		request.setAttribute("users", users);

		String pageName = "/view/loan/add-loan.jsp";
		Router.forward(pageName, this, request, response);
		// this.closeConn();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.LOAN_ADMIN, Profil.ADMIN))
			return;
		
		this.doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.LOAN_ADMIN, Profil.ADMIN))
			return;
		
		int equipmentId = Integer.parseInt(req.getParameter("equipmentId"));
		String userMail = req.getParameter("userMail");
		String beginningDateString = req.getParameter("beginningDate");
		String endDateString = req.getParameter("endDate");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

		// convert String to LocalDate
		LocalDate beginningDate = LocalDate.parse(beginningDateString, formatter);
		LocalDate endDate = LocalDate.parse(endDateString, formatter);

		Equipment equipment = this.eDAO.get(equipmentId);
		User user = this.userDAO.get(userMail);
		Loan loan = new Loan(0, equipment, user, beginningDate, endDate, false);

		this.loanDAO.add(loan);
		this.closeConn();

    // redirect to welcome page
    String pageName = "/welcome";
    Router.redirect(pageName, this, req, resp);
	}

	private void closeConn() {
		this.eDAO.closeConn();
		this.userDAO.closeConn();
		this.loanDAO.closeConn();
	}
}
