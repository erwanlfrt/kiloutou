<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.user.User , javax.servlet.* , java.io.IOException"%>
<%
User user = (User) request.getSession().getAttribute("user");
String login = "";

if(user == null) {
	RequestDispatcher rd = getServletContext().getRequestDispatcher("/error");
	try {
		rd.forward(request,response);
	} catch (ServletException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
else {
	 login = user.getLogin();
}

%>
<html>
<head>
<p> Welcome <%=login %> !</p>
<a href="loan/add">New Loan</a>

</html>