package controller.user;

import model.dao.UserDAO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ResetPassword extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
		String mail = data.get("mail").getAsString();
		if (mail != null) {
			UserDAO userDAO = new UserDAO();
			userDAO.generateRandomPwd(mail);
		}
	}
}
