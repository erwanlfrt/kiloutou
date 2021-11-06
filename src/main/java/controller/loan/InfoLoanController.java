package controller.loan;

import model.dao.LoanDAO;
import model.object.loan.Loan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;


public class InfoLoanController extends HttpServlet {
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
    LoanDAO loanDAO = new LoanDAO();
    Loan loan = loanDAO.get(id);

    if(loan != null) {
      req.setAttribute("loan", loan);
      pageName="/view/loan/info-loan.jsp";
    }
    else {
      pageName="/error";
    }
		this.doProcess(req, resp, pageName);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
