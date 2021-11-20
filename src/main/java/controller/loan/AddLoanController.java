package controller.loan;

import model.dao.*;
import model.object.equipment.*;
import model.object.loan.Loan;
import model.object.user.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);

		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
	}

}
