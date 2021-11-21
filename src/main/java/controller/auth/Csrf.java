package controller.auth;

import java.nio.charset.Charset;
import java.util.Random;

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
	}
	
	public String getCsrfToken() {
		return this.csrfToken;
	}
	
	public static Boolean validateRequest(HttpServletRequest request, String token) {
		Boolean pass = true;
		Object csrf = request.getSession().getAttribute("csrf");
		
		if (csrf == null || !token.equals((String) csrf)) {
			pass = false;
		}
		
		return pass;
	}
	
	private void generateToken() {
		byte[] array = new byte[32];
	    new Random().nextBytes(array);
	    this.csrfToken = new String(array, Charset.forName("UTF-8"));
	}
}
