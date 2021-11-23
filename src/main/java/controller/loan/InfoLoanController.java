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

public class InfoLoanController extends HttpServlet {
	private LoanDAO loanDAO;
	private Loan loan;

	public InfoLoanController() {
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
			pageName = "/view/loan/info-loan.jsp";
		} else {
			pageName = "/error";
		}
		this.doProcess(req, resp, pageName);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (this.loan != null && req.getParameter("updateLoan") != null) {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("isOver", req.getParameter("updateLoan").equals("signaler comme rendu"));
			loanDAO.update(loan, params);
		}
		this.doGet(req, resp);
	}

}
