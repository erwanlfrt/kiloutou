package controller.user;

import model.dao.UserDAO;
import model.dao.EmployeeDAO;
import model.object.user.User;
import model.object.user.Employee;
import model.object.user.Profil;

import java.io.IOException;

import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.router.Router;

public class ModifyUserController extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String pageName = "/view/user/modify-user.jsp";

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

		if (user == null) {
			user = new User("", "", "", "", "", "", "");
		}
		req.setAttribute("user", user);

		if (employee != null) {
			req.setAttribute("employee", employee);
		}
		
		employeeDAO.closeConn();
		userDAO.closeConn();

		this.doProcess(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String firstname = req.getParameter("firstname");
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String phoneNumber = req.getParameter("phoneNumber");
		User user = new User(name, firstname, address, phoneNumber, email, login, password);

		HashMap<String, Object> userParameters = new HashMap<String, Object>();
		userParameters.put("name", name);
		userParameters.put("firstname", firstname);
		userParameters.put("login", login);
		userParameters.put("password", password);
		userParameters.put("address", address);
		userParameters.put("phoneNumber", phoneNumber);

		UserDAO userDAO = new UserDAO();
		userDAO.update(user, userParameters);

		if (req.getParameter("employeeService") != null) {
			String employeeService = req.getParameter("employeeService");
			String employeeFunction = req.getParameter("employeeFunction");
			String profile = req.getParameter("profile");
			int deskNumber = Integer.parseInt(req.getParameter("deskNumber"));

			HashMap<String, Object> employeeParameters = new HashMap<String, Object>();
			employeeParameters.put("employeeService", employeeService);
			employeeParameters.put("employeeFunction", employeeFunction);
			employeeParameters.put("profil", profile);
			employeeParameters.put("deskNumber", deskNumber);

			Employee employee = new Employee(name, firstname, address, phoneNumber, email, login, password,
					employeeFunction, employeeService, deskNumber, Profil.valueOf(profile));
			EmployeeDAO employeeDAO = new EmployeeDAO();
			Employee check = employeeDAO.get(email);
			if (check != null) {
				employeeDAO.update(employee, employeeParameters);
			} else {
				employeeDAO.add(employee);
			}
			
			employeeDAO.closeConn();

		}
		
		userDAO.closeConn();
		
		Router.redirect("/welcome", this, req, resp);
		//resp.sendRedirect(req.getContextPath() + "/welcome");
	}
}
