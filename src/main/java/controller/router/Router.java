package controller.router;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Router {
  public static void redirect(String route, HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = servlet.getServletContext().getRequestDispatcher(route);
    if(rd == null) {
      //bad redirection
      rd = servlet.getServletContext().getRequestDispatcher("/error");
    }
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
}