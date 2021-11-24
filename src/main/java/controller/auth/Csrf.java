package controller.auth;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
	
	public static Boolean validateRequest(HttpServletRequest request, String token) {
		
		//String csrfToken = req.getParameter("_token");
		
				//if (Csrf.validateRequest(req, csrfToken)) {
					
				//}
		
		Boolean pass = true;
		Object csrf = request.getSession().getAttribute("csrf");
		
		if (csrf == null || !token.equals((String) csrf)) {
			pass = false;
		}
		
		return pass;
	}
	
	private void generateToken() {
		/*byte[] array = new byte[32 * 8];
	    new Random().nextBytes(array);
	    this.csrfToken = new String(array, Charset.forName("UTF-8"));*/
		this.csrfToken = UUID.randomUUID().toString();
	}
}
