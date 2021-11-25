<%@ page language="java" pageEncoding="UTF-8"%>
<%@page
	import="model.object.user.* , javax.servlet.* , java.io.IOException, controller.router.Router"%>
<%
User user = (User) request.getSession().getAttribute("user");
Employee employee = (Employee) request.getSession().getAttribute("employee");
String login = "";
Profil profile = null;

if (user == null || employee == null) {
	request.setAttribute("message", "Vous n'êtes pas authentifié.");
	RequestDispatcher rd = getServletContext().getRequestDispatcher("/error");
	try {
		rd.forward(request,response);
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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/auth/css/welcome.css" />
</head>
<body>
	<%@include file="../layout/header.jsp" %>
	<main>
		<h1>Application de Gestion d'Emprunt</h1>
        <h2>Bienvenue <%=login %> !</h2>
        <section class="section-nav">
       
        <% if(profile == Profil.ADMIN || profile == Profil.LOAN_ADMIN) { %>
        
        	<div class="section-nav-item">
        		<h2>NOUVEL EMPRUNT</h2>
        		<p>Un nouvel emprunt à faire ?</p>
        		<a href="${pageContext.request.contextPath}/loan/add">Emprunter</a>
        	</div>
        	
        <% } %>
        <% if(profile == Profil.ADMIN) { %>
        	
        	<div class="section-nav-item">
        		<h2>NOUVEL UTILISATEUR</h2>
        		<p>Un nouvel utilisateur à créer ?</p>
        		<a href="${pageContext.request.contextPath}/user/add">Créer</a>
        	</div>
        	
        <% } %>
        <% if(profile == Profil.EQUIPMENT_ADMIN || profile == Profil.ADMIN) { %>
        	
        	<div class="section-nav-item">
        		<h2>NOUVEAU MAT&Eacute;RIEL</h2>
        		<p>Un ou plusieurs matériels à saisir</p>
        		<a href="${pageContext.request.contextPath}/equipment/add">Saisir</a>
        	</div>
        	
        <% } %>
        	
        </section>
        <img src="${pageContext.request.contextPath}/images?name=tracteur.png" alt="Image de trois tracto-pelles" width="80%" height="auto" >
	</main>
	
	<script>
		document.getElementById("burger").addEventListener('click', function(ev) {
  			document.getElementById("burger-dropdown").classList.toggle('active');
		});
	</script>
</body>

</html>