package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.object.user.User;

public class Main extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String pageName="/welcome.jsp";
		UserDAO userDAO = new UserDAO();
		ArrayList<User> users = userDAO.listAll();
		User add = new User("Aimarre", "Jean", "1 rue de la flemme", "0102010201", "jaimarre@gmail.com", "jaimarre", "mdpjaimarre");
		// userDAO.add(add);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("address", "1 rue de la nouvelle");
		map.put("phoneNumber", "0000000000");
		userDAO.update(userDAO.get("jaimarre@gmail.com"), map);
		// User user = userDAO.get("root@gmail.com"); et add fait;
		request.setAttribute("users", users);
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
	}
}
