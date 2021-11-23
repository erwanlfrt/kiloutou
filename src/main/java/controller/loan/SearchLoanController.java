package controller.loan;

import model.dao.LoanDAO;
import model.object.loan.Loan;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;

public class SearchLoanController extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String pageName = "/view/loan/search-loan.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);

		LoanDAO loanDAO = new LoanDAO();
		ArrayList<Loan> loans = loanDAO.listAll();
		ArrayList<Loan> outdatedLoans = new ArrayList<Loan>();
		ArrayList<Loan> currentLoans = new ArrayList<Loan>();
		ArrayList<Loan> loansToCome = new ArrayList<Loan>();
		ArrayList<Loan> oldLoans = new ArrayList<Loan>();
		
		loanDAO.closeConn();

		for (Loan loan : loans) {
      if(loan.isCurrentlyLoaned()) {
        currentLoans.add(loan);
      }
			else if (loan.isLate()) {
				outdatedLoans.add(loan);
			}
			else if (loan.isOver()) {
				oldLoans.add(loan);
			}
			else if (loan.hasNotStarted()) {
				loansToCome.add(loan);
			}
		}

		request.setAttribute("outdatedLoans", outdatedLoans);
		request.setAttribute("currentLoans", currentLoans);
		request.setAttribute("oldLoans", oldLoans);
		request.setAttribute("loansToCome", loansToCome);

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
		this.doProcess(req, resp);

	}
}
