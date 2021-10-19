<%@ page language="java"%>
<%@page import="model.object.user.User"%>
<%
User user = (User) request.getAttribute("user");
String login = user.getLogin();
%>
<html>
<head>
<p> Welcome <%=login %> !</p>

</html>