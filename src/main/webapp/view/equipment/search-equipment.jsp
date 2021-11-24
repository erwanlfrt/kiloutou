<%@page import="model.object.user.Profil"%>
<%@page import="model.object.user.Employee"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.user.User , javax.servlet.* , java.io.IOException, model.object.equipment.*, java.util.ArrayList, java.util.HashMap, java.util.Map"%>
<%
User logged = (User) request.getSession().getAttribute("user");
Employee loggedEmployee = (Employee) request.getSession().getAttribute("employee");

String login = "";
Profil profile = null;

if(logged == null || loggedEmployee == null) {
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
	 profile = loggedEmployee.getProfil();
}

%>
<%
  HashMap<String, ArrayList<Equipment>> lists = (HashMap<String, ArrayList<Equipment>>) request.getAttribute("lists");
%>
<html>
  <head>
  	<meta charset="UTF-8" />
    <title>Chercher un équipement</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/equipment/css/search-equipment.css" />
  </head>
  <body>
  
  	<jsp:include page="header-equipment.jsp">
  		<jsp:param name="profile" value="<%= profile %>" />
  	</jsp:include>

<<<<<<< HEAD
	<main>
		<div>
			<h1>équipements</h1>
		</div>
		<section class="search-section">
			<div class="search-icon">
				<img src="${pageContext.request.contextPath}/images?name=search_user.png"
					alt="Chercher un équipement" width="30px" height="30px" />
			</div>
			<div class="search-input">
				<input type="text" id="searchBar" name="searchBar" />
				<label for="searchBar">Nom équipement</label>
			</div>
			<div class="search-type">
				<select name="categories" id="categories" onchange="searchEquipments()">
					<option value="all">Tout afficher</option>
					<option value="Véhicules">Véhicules</option>
					<option value="Voitures">Voitures</option>
					<option value="Motos">Motos</option>
					<option value="Accessoires pour véhicule">Accessoires pour véhicule</option>
					<option value="Accessoires informatiques">Accessoires informatiques</option>
					<option value="Ordinateurs">Ordinateurs</option>
				</select>
				<label for="categories">Type d'équipement</label>
			</div>
		</section>
		<section class="list-equipment">
		<% for(Map.Entry<String, ArrayList<Equipment>> entry : lists.entrySet()) { %>
        	<% String key = entry.getKey(); %>
        	<% ArrayList<Equipment> list = entry.getValue(); %>
        	<h2 class="category_title_<%= key %>"><%= key %></h2>
        	<div id="category_<%= key %>">
        		<% for(Equipment e : list) { %>
                	<% if(e.canBeLoaned()) { %>
                	<div class="list-item">
                		<img src="<%= e.getImageUrl() %>" alt="Equipement" width="50px" height="50px" />
        				<p><a href="${pageContext.request.contextPath}/equipment/info?id=<%=e.getId()%>"><%= e.getName()%></a></p>
                	</div>
        			<% } %>
        		<% } %>
        	</div>  		
        <% } %>
		</section>
	</main>
  </body>
  
  <script src="${pageContext.request.contextPath}/view/equipment/js/search-equipment.js"></script>
  
</html>