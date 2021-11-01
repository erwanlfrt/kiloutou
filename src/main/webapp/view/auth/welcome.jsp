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

<div id="loan">
  <a href="loan/add">New Loan</a>
  <a href="loan/search">Search Loan</a>
</div>

<div id="user">
  <a href="user/add">Add user</a>
  <a href="user/search">Search user</a>
</div>

<div id="equipment">
  <a href="equipment/add">Add equipment</a>
  <a href="equipment/search">Search equipment</a>
</div>


</html>