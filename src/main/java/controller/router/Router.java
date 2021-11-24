package controller.router;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Router {
	public static void redirect(String route, HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.sendRedirect(req.getContextPath() + route);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

  public static void forward(String route, HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) {
    RequestDispatcher rd = servlet.getServletContext().getRequestDispatcher(route);
		try {
			rd.forward(req, resp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
  }
}