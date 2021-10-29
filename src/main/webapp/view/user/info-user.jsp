<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.user.* , java.io.IOException"%>
<%
  User user = (User)request.getAttribute("user");
  Employee employee = (Employee)request.getAttribute("employee");
%>
<%
User logged = (User) request.getSession().getAttribute("user");
String login = "";

if(logged == null) {
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
	 login = logged.getLogin();
}

%>
<html>
  <head>
    <meta charset="utf-8">
    <title>Info user</title>
  </head>
  <body>
    <div id="info">
      <p><b>Adresse mail : </b><%= user.getMail()%></p>
      <p><b>Nom : </b> <%= user.getName()%></p>
      <p><b>Prénom : </b> <%= user.getFirstname()%></p>
      <p><b>Login : </b> <%= user.getLogin()%></p>
      <p><b>Adresse : </b> <%= user.getAddress()%></p>
      <p><b>n° tel. : </b> <%= user.getPhoneNumber()%></p>

      <%
        if(employee != null) {
          %>
            <p><b>Fonction : </b><%= employee.getFunction()%></p>
            <p><b>Service : </b><%= employee.getService()%></p>
            <p><b>n° bureau : </b><%= employee.getDeskNumber()%></p>
            <p><b>Profil : </b><%= employee.getProfil().name()%></p>
          <%
        }
      %>
    </div>

    <div>
      <a href="modify?mail=<%= user.getMail()%>">
        <button>Modifier</button>
     </a>
     <a href="delete?mail=<%= user.getMail()%>">
      <button>Supprimer</button>
   </a>
    </div>
</html>