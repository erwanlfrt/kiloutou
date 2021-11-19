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
     <button onClick="deleteUser()">Supprimer</button>
   </a>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script>
      function deleteUser() {
        
        let data = {
          mail : '<%= user.getMail()%>'
        }
        $.ajax({
          url: "/Kiloutou/user/delete",
          method: "POST",
          contentType: "application/json", // NOT dataType!
          data: JSON.stringify(data),
        }).done(function() {
        let url = window.location.href;
        url = url.substring(0, url.indexOf('info'));
        url += 'search'
        window.location.href = url;
        });

        
      }
    </script>
</html>