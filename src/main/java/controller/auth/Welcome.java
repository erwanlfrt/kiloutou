package controller.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import model.dao.EmployeeDAO;
import model.dao.UserDAO;
import model.object.user.Employee;
import model.object.user.User;

public class Welcome extends HttpServlet {
	private UserDAO userDAO;
	private EmployeeDAO employeeDAO;

	public Welcome() {
		super();
		System.out.println("Welcome.java");
		this.userDAO = new UserDAO();
		this.employeeDAO = new EmployeeDAO();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd;
		if (request.getAttribute("error") == null) {
			rd = getServletContext().getRequestDispatcher("/view/auth/welcome.jsp");
		} else {
			rd = getServletContext().getRequestDispatcher("/login");
		}

		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.destroy();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Doget");
		this.doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail = req.getParameter("mailAddress");
		String password = req.getParameter("pwd");
		User user = this.userDAO.get(mail);
		Employee employee = this.employeeDAO.get(mail);

		HttpSession session = req.getSession();

		if (user != null && employee != null) {
			if (DigestUtils.sha1Hex(password).equals(user.getPassword())) {
				session.setAttribute("user", user);
				session.setAttribute("employee", employee);
				session.setAttribute("csrf", new Csrf(req).getCsrfToken());
			} else {
				req.setAttribute("error", "Adresse mail ou mot de passe incorrect");
			}
		} else {
			req.setAttribute("error", "Adresse mail ou mot de passe incorrect");
		}
		this.doProcess(req, resp);
	}
	
	private void close() {
		this.userDAO.destroy();
		this.employeeDAO.destroy();
	}
}