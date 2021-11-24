package controller.user;

import model.dao.UserDAO;
import model.object.user.User;

import java.io.IOException;
import java.util.ArrayList;

import controller.router.Router;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchUserController extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String pageName = "/view/user/search-user.jsp";

		Router.forward(pageName, this, request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO userDAO = new UserDAO();
		ArrayList<User> users = userDAO.listAll();
		req.setAttribute("users", users);
		userDAO.closeConn();
		this.doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
