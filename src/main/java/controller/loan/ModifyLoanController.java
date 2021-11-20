package controller.loan;

import model.dao.LoanDAO;
import model.object.loan.Loan;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
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

	private void doProcess(HttpServletRequest request, HttpServletResponse response, String pageName) {
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
		String pageName;
		int id = Integer.valueOf(req.getParameter("id"));
		this.loan = this.loanDAO.get(id);

		if (loan != null) {
			req.setAttribute("loan", loan);
			pageName = "/view/loan/modify-loan.jsp";
		} else {
			pageName = "/error";
		}
		this.doProcess(req, resp, pageName);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String beginningDateString = req.getParameter("beginningDate");
		String endDateString = req.getParameter("endDate");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
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

		Router.redirect("/loan/info?id=" + this.loan.getId(), this, req, resp);
	}

}
