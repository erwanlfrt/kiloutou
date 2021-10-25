package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.object.user.User;


public class Login extends HttpServlet {
	private UserDAO userDAO;
	
	public Login() {
		super();
		this.userDAO = new UserDAO();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
		String mail = req.getParameter("mailAddress");
		String password = req.getParameter("pwd");
		User user = this.userDAO.get(mail);
		
		if(user != null) {
			if(user.getPassword().equals(password)) {
				req.setAttribute("user", user);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/logged");
				try {
					rd.forward(req, resp);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("user doesn't exist");
		}
		
		
	}
}
