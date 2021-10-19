package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.object.user.User;


public class Welcome extends HttpServlet {
	private UserDAO userDAO;
	
	public Welcome() {
		super();
		this.userDAO = new UserDAO();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/welcome.jsp");
		
		try {
			rd.forward(request,response);
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
		String mail = req.getParameter("mailAddress");
		String password = req.getParameter("pwd");
		User user = this.userDAO.get(mail);
		req.setAttribute("user", user);
		
		if(user != null) {
			if(user.getPassword().equals(password)) {
				this.doProcess(req, resp);
			}
		} else {
			System.out.println("user doesn't exist");
		}
	}
}