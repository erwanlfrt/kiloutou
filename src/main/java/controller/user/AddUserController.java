package controller.user;

import model.dao.UserDAO;
import model.object.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddUserController extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String pageName="/view/user/add-user.jsp";
	
		UserDAO userDAO = new UserDAO();
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
		this.doProcess(req, resp);
		
		String name = req.getParameter("name");
		String firstname = req.getParameter("firstname");
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String phoneNumber = req.getParameter("phoneNumber");
		
		
		User user = new User(name, firstname, address, phoneNumber, email, login, password);
		
		UserDAO userDAO = new UserDAO();
		userDAO.add(user);
		
		System.out.println("User added");
		
	}
}