package controller.auth;

import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.router.Router;

public class Csrf {
	
	private String csrfToken;
	
	public Csrf(HttpServletRequest request) {
		//request.
		Object csrf = request.getSession().getAttribute("csrf");
		if (csrf == null) {
			this.generateToken();
		} else {
			this.csrfToken = (String) csrf; 
		}
		
		// System.out.println("CSRF TOKEN :" + this.csrfToken);
	}
	
	public String getCsrfToken() {
		return this.csrfToken;
	}
	
	public static Boolean validateRequest(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) {
		Boolean pass = false;
		
		String inputToken = req.getParameter("_token");
		Object csrf = null;
		
		if (inputToken != null) {
			csrf = req.getSession().getAttribute("csrf");
			
			if (csrf != null) {
				pass = inputToken.equals((String) csrf);
			}	
		}
		
		if (pass == false) {
			String pageName = "/error";
			req.setAttribute("message", "Session expirée veuillez recharger cette page.");
			Router.redirect(pageName, servlet, req, resp);
			
			System.out.println("DO NOT PASS \nForm token : " + inputToken + "\nToken : " + csrf);
		}
		
		return pass;
	}
	
	private void generateToken() {
		/*byte[] array = new byte[32 * 8];
	    new Random().nextBytes(array);
	    this.csrfToken = new String(array, Charset.forName("UTF-8"));*/
		this.csrfToken = UUID.randomUUID().toString().replace("-", "");
	}
}
