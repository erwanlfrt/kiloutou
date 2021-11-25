<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.user.* , java.util.ArrayList , java.io.IOException "%>
<%
ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");

User user = (User) request.getSession().getAttribute("user");
Employee employee = (Employee) request.getSession().getAttribute("employee");

String login = "";
Profil profile = null;

if (user == null || employee == null) {
	RequestDispatcher rd = getServletContext().getRequestDispatcher("/error");
	try {
		rd.forward(request, response);
	} catch (ServletException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
} else {
	login = user.getLogin();
	profile = employee.getProfil();
}
%>
<html>
  <head>
    <title>kiloutou</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/user/css/search-user.css" />
  </head>
  <body>
  
  	<jsp:include page="header-user.jsp">
  		<jsp:param name="profile" value="<%= profile %>" />
  	</jsp:include>
  	
  	<main>
  		<div>
  			<h1>UTILISATEURS</h1>
  		</div>
  		<section class="search-section">
  			<div class="search-icon">
  				<img src="${pageContext.request.contextPath}/images?name=search_user.png" alt="Chercher un utilisateur" width="30px" height="30px" />
  			</div>
  			<div class="search-input">
  				<input type="text" id="searchBar" name="searchBar" />
  				<label for="searchBar">Prénom et/ou Nom</label>
  			</div>
  			<div class="search-slider" title="Changer affichage prénom nom / email">
  				<label class="switch" >
          			<input type="checkbox" id="searchType">
          			<span id="sliderClick" class="slider round" ></span>
      			</label>
  			</div>
  		</section>
  		<section>
  			<div id="listUsers">
        	<% for(User usr : users) { %>
           		<% if(usr.isReal()) { %>
           		<div class="user-item">
           			<img src="${pageContext.request.contextPath}/images?name=user.png" alt="Utilisateur" width="20px" height="20px" />
                	<p class="userItem active"><a href="/kiloutou/user/info?mail=<%=usr.getMail()%>"><%= usr.getMail() %></a></p>
                	<p class="userItem"><a href="/kiloutou/user/info?mail=<%=usr.getMail()%>"><%= usr.getFirstname() %> <%= usr.getName() %></a></p>
           			<img class="user-edit" src="${pageContext.request.contextPath}/images?name=edit.png" alt="Modifier" width="20px" height="20px" onclick="window.location.replace('${pageContext.request.contextPath}/user/modify?mail=<%= usr.getMail() %>')" />
           			<img class="user-bin" src="${pageContext.request.contextPath}/images?name=bin.png" alt="Supprimer" width="20px" height="20px" onclick="deleteUser('<%= usr.getMail() %>')"/>
           		</div>
           		<% } %>
        	<% } %>
      		</div>
  		</section>
  	</main>
    
  </body>
  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  <script src="${pageContext.request.contextPath}/view/user/js/header.js"></script>
  <script src="${pageContext.request.contextPath}/view/user/js/search-user.js"></script>

</html>