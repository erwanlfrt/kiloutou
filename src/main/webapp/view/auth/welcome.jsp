<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.user.* , javax.servlet.* , java.io.IOException"%>
<%
User user = (User) request.getSession().getAttribute("user");
Employee employee = (Employee) request.getSession().getAttribute("employee");
String login = "";
Profil profile = null;

if(user == null || employee == null) {
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
   profile = employee.getProfil();
}

%>
<html>
<head>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/auth/css/welcome.css" />
</head>
<body>
  <div id="container">
    <div id="navigation">
      <nav>
        <ul>
          <%
            if(profile == Profil.ADMIN || profile == Profil.LOAN_ADMIN) {
              %>
              <li class="dropdown" id="loan"><a href="#">Emprunts</a>
                <ul class="child">
                  <li><a href="loan/add">Ajouter</a></li>
                  <li><a href="loan/search">Rechercher</a></li>
                </ul>
              </li>
              <%
            }
            if(profile == Profil.ADMIN) {
              %>
              <li class="dropdown" id="user"><a href="#">Utilisateurs</a>
                <ul class="child">
                  <li><a href="user/add">Ajouter</a></li>
                  <li><a href="user/search">Rechercher</a></li>
                </ul>
              </li>
              <%
            }
            if(profile == Profil.EQUIPMENT_ADMIN || profile == Profil.ADMIN) {
              %>
              <li class="dropdown" id="equipment"><a href="#">Matériels</a>
                <ul class="child">
                  <li><a href="equipment/add">Ajouter</a></li>
                  <li><a href="equipment/search">Rechercher</a></li>
                </ul>
              </li>
              <%
            } %>
        </ul>
      </nav>
      
    </div>

    <div id="centralContainer">
      <div id="welcomeMessage">
        <h1>Application de Gestion d'Emprunt</h1>
        <h2>Bienvenue <%=login %> !</h2>
      </div>
    </div>
    
  </div>
</body>

</html>