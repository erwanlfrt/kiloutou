package controller.user;

import model.dao.UserDAO;
import model.dao.EmployeeDAO;
import model.dao.LoanDAO;
import model.object.user.User;
import model.object.loan.Loan;
import model.object.user.Employee;
import model.object.user.Profil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class InfoUserController extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String pageName="/view/user/info-user.jsp";

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
    String mail = req.getParameter("mail");

    if(mail == null) {
      //maybe it's a refresh
      JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
      mail= data.get("mail").getAsString();

    }

    UserDAO userDAO = new UserDAO();
    User user = userDAO.get(mail);

    EmployeeDAO employeeDAO = new EmployeeDAO();
    Employee employee = employeeDAO.get(mail);

    LoanDAO loanDAO = new LoanDAO();
    ArrayList<Loan> initialLoans = loanDAO.listByUser(user);
    ArrayList<Loan> loans = new ArrayList<Loan>();
    for(Loan loan : initialLoans) {
      if(loan.isBorrowed() && !loan.hasNotStarted()) {
        loans.add(loan);
      }
    }

    if(user == null) {
      user = new User("", "", "", "", "", "", "");
    }
    req.setAttribute("user", user);
    req.setAttribute("employee", employee);
    req.setAttribute("loans", loans);

    this.doProcess(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
      String id = data.get("id").getAsString();
      LoanDAO loanDAO = new LoanDAO();
      HashMap<String, Object> params = new HashMap<String, Object>();
      params.put("isBorrowed", false);
      Loan loan = loanDAO.get(Integer.parseInt(id));
      loanDAO.update(loan, params);
	}
}
