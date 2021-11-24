package controller.auth;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.router.Router;
import model.object.user.Employee;
import model.object.user.Profil;

public class Role {

	/*
	 * public static Boolean hasRole(HttpServletRequest request, Profil role) {
	 * Boolean pass = false;
	 * 
	 * HttpSession session = request.getSession();
	 * 
	 * if (session != null) { Employee employee = (Employee)
	 * session.getAttribute("employee");
	 * 
	 * if (employee != null) { pass = employee.hasRole(role); } }
	 * 
	 * return pass; }
	 */

	public static Boolean can(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp, Profil... role) {
		Boolean canRedirect = true;
		
		HttpSession session = req.getSession();

		if (session != null) {
			Employee employee = (Employee) session.getAttribute("employee");

			if (employee != null) {

				if (!hasRole(employee, role)) {
					canRedirect = false;
					String pageName = "/error";
					Router.redirect(pageName, servlet, req, resp);
				}
			} else {
				canRedirect = false;
				String pageName = "/login";
				Router.redirect(pageName, servlet, req, resp);
			}
		}
		
		return canRedirect;
	}
	
	private static Boolean hasRole(Employee emp, Profil[] roles) {
		Boolean pass = false;
		int index = 0;
		
		if (roles.length == 0) {
			pass = true;
		} else {
			while (pass == false && roles.length > index) {
				pass = emp.hasRole(roles[index]);
				index++;
			}
		}
		
		return pass;
	}
	
}
