package controller.loan;

import model.dao.LoanDAO;
import model.object.loan.Loan;
import model.object.user.Profil;

import java.io.IOException;
import java.util.HashMap;

import controller.auth.Role;
import controller.router.Router;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.router.Router;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ModifyLoanController extends HttpServlet {
	private LoanDAO loanDAO;
	private Loan loan;

	public ModifyLoanController() {
		super();
		this.loanDAO = new LoanDAO();
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.LOAN_ADMIN, Profil.ADMIN))
			return;
		
		String pageName;
		int id = Integer.valueOf(req.getParameter("id"));
		this.loan = this.loanDAO.get(id);
		this.loanDAO.closeConn();

		if (loan != null) {
			req.setAttribute("loan", loan);
			pageName = "/view/loan/modify-loan.jsp";
      Router.forward(pageName, this, req, resp);

		} else {
			pageName = "/error";
      Router.redirect(pageName, this, req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.LOAN_ADMIN, Profil.ADMIN))
			return;
		
		String beginningDateString = req.getParameter("beginningDate");
		String endDateString = req.getParameter("endDate");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		HashMap<String, Object> params = new HashMap<String, Object>();

		if (beginningDateString != null) {
			LocalDate beginningDate = LocalDate.parse(beginningDateString, formatter);
			String finalBeginningDate = this.loanDAO.javaDateToMysqlDate(beginningDate);
			params.put("beginningDate", finalBeginningDate);
		}

		if (endDateString != null) {
			LocalDate endDate = LocalDate.parse(endDateString, formatter);
			String finalEndDate = this.loanDAO.javaDateToMysqlDate(endDate);
			params.put("endDate", finalEndDate);
		}

		this.loanDAO.update(this.loan, params);
		this.loanDAO.closeConn();
		
		Router.redirect("/loan/info?id=" + this.loan.getId(), this, req, resp);
	}

}
