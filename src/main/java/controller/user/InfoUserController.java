package controller.user;

import model.dao.UserDAO;
import model.dao.EmployeeDAO;
import model.object.user.User;
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

    UserDAO userDAO = new UserDAO();
    User user = userDAO.get(mail);

    EmployeeDAO employeeDAO = new EmployeeDAO();
    Employee employee = employeeDAO.get(mail);

    if(user == null) {
      user = new User("", "", "", "", "", "", "");
    }
    req.setAttribute("user", user);
    req.setAttribute("employee", employee);

    this.doProcess(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doProcess(req, resp);
	}
}
