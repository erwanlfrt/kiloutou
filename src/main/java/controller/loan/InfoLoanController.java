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

public class InfoLoanController extends HttpServlet {
	private LoanDAO loanDAO;
	private Loan loan;

	public InfoLoanController() {
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

		if (loan != null) {
			req.setAttribute("loan", loan);
			pageName = "/view/loan/info-loan.jsp";
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
		
		if (this.loan != null && req.getParameter("updateLoan") != null) {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("isOver", req.getParameter("updateLoan").equals("signaler comme rendu"));
			loanDAO.update(loan, params);
		}
		this.doGet(req, resp);
    this.loanDAO.closeConn();
	}

}
