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

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class ResetPassword extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
	    String mail = data.get("mail").getAsString();
	    
	    if(mail != null) {
	    	UserDAO userDAO = new UserDAO();
	        userDAO.generateRandomPwd(mail);
	    }
    
	}
  
}
