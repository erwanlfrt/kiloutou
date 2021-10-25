package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.object.user.User;

public class Signup extends HttpServlet {
	private UserDAO userDAO;
	
	public Signup() {
		super();
		this.userDAO = new UserDAO();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/signup.jsp");
		try {
			rd.forward(request, response);
		}
		catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}
}