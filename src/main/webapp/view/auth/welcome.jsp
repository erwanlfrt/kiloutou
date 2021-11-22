<%@ page language="java" pageEncoding="UTF-8"%>
<%@page
	import="model.object.user.* , javax.servlet.* , java.io.IOException"%>
<%
User user = (User) request.getSession().getAttribute("user");
Employee employee = (Employee) request.getSession().getAttribute("employee");
String login = "";
Profil profile = null;

if (user == null || employee == null) {
	request.setAttribute("message", "Vous n'êtes pas autorisé à accéder à cette page.");
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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/auth/css/welcome.css" />
</head>
<body>
	<header>
		<div>
			<img src="images?name=logo.png" width="auto" height="30px" alt="KILOUPRESQUETOUT" >
		</div>
		<nav class="nav-desktop">
			<a href="#" class="active">Accueil</a>
			<div class="dropdown">
				<a href="#">Emprunts</a>
				<div class="dropdown-list">
					<div class="dropdown-item">
						<img src="images?name=add.png" alt="add" width="auto" height="18px" >
						<a href="loan/add">Ajouter</a>
					</div>
					<hr>
					<div class="dropdown-item">
						<img src="images?name=search.png" alt="search" width="auto" height="18px" >
						<a href="loan/search">Chercher</a>
					</div>
				</div>
			</div>
			<div class="dropdown">
				<a href="#">Matériels</a>
				<div class="dropdown-list">
					<div class="dropdown-item">
						<img src="images?name=add.png" alt="add" width="auto" height="18px" >
						<a href="equipment/add">Ajouter</a>
					</div>
					<hr>
					<div class="dropdown-item">
						<img src="images?name=search.png" alt="search" width="auto" height="18px" >
						<a href="equipment/search">Chercher</a>
					</div>
				</div>
			</div>
			<div class="dropdown">
				<a href="#">Utilisateurs</a>
				<div class="dropdown-list">
					<div class="dropdown-item">
						<img src="images?name=add.png" alt="add" width="auto" height="18px" >
						<a href="user/add">Ajouter</a>
					</div>
					<hr>
					<div class="dropdown-item">
						<img src="images?name=search.png" alt="search" width="auto" height="18px" >
						<a href="user/search">Chercher</a>
					</div>
				</div>
			</div>
		</nav>
		<div class="disconnect nav-desktop">
			<img src="images?name=power-off.png" alt="Déconnexion" width="auto" height="20px" >
			<a href="#">Se déconnecter</a>
		</div>
		<div class="nav-mobile">
			<img id="burger" src="images?name=menu.png" alt="Menu" width="30px" height="auto">
			<div id="burger-dropdown" class="nav-dropdown">
				<div class="accueil-item">
					<a href="/" class="active">Accueil</a>
				</div>
				
				<hr>
				
				<% if(profile == Profil.ADMIN || profile == Profil.LOAN_ADMIN) { %>
        
				<div class="dropdown">
					<a href="#">Emprunts</a>
					<div class="dropdown-list">
						<div class="dropdown-item">
							<img src="images?name=add.png" alt="add" width="auto" height="18px" >
							<a href="loan/add">Ajouter</a>
						</div>
						<hr>
						<div class="dropdown-item">
							<img src="images?name=search.png" alt="search" width="auto" height="18px" >
							<a href="loan/search">Chercher</a>
						</div>
					</div>
				</div>
				
				<% } %>
        		<% if(profile == Profil.EQUIPMENT_ADMIN || profile == Profil.ADMIN) { %>
        	
				
				<div class="dropdown">
					<a href="#">Matériels</a>
					<div class="dropdown-list">
						<div class="dropdown-item">
							<img src="images?name=add.png" alt="add" width="auto" height="18px" >
							<a href="equipment/add">Ajouter</a>
						</div>
						<hr>
						<div class="dropdown-item">
							<img src="images?name=search.png" alt="search" width="auto" height="18px" >
							<a href="equipment/search">Chercher</a>
						</div>
					</div>
				</div>
				
				<% } %>
        		<% if(profile == Profil.ADMIN) { %>
        	
				<div class="dropdown">
					<a href="#">Utilisateurs</a>
					<div class="dropdown-list">
						<div class="dropdown-item">
							<img src="images?name=add.png" alt="add" width="auto" height="18px" >
							<a href="user/add">Ajouter</a>
						</div>
						<hr>
						<div class="dropdown-item">
							<img src="images?name=search.png" alt="search" width="auto" height="18px" >
							<a href="user/search">Chercher</a>
						</div>
					</div>
				</div>
				
				<% } %>
				
				<hr>
				
				<div class="deconnexion-item">
					<img src="images?name=power-off.png" alt="Déconnexion" width="auto" height="20px" >
					<a href="logout">Se déconnecter</a>
				</div>
				
			</div>
		</div>
	</header>
	<main>
		<h1>Application de Gestion d'Emprunt</h1>
        <h2>Bienvenue <%=login %> !</h2>
        <section class="section-nav">
       
        <% if(profile == Profil.ADMIN || profile == Profil.LOAN_ADMIN) { %>
        
        	<div class="section-nav-item">
        		<h2>NOUVEL EMPRUNT</h2>
        		<p>Un nouvel emprunt à faire ?</p>
        		<a href="loan/add">Emprunter</a>
        	</div>
        	
        <% } %>
        <% if(profile == Profil.ADMIN) { %>
        	
        	<div class="section-nav-item">
        		<h2>NOUVEL UTILISATEUR</h2>
        		<p>Un nouvel utilisateur à créer ?</p>
        		<a href="user/add">Créer</a>
        	</div>
        	
        <% } %>
        <% if(profile == Profil.EQUIPMENT_ADMIN || profile == Profil.ADMIN) { %>
        	
        	<div class="section-nav-item">
        		<h2>NOUVEAU MAT&Eacute;RIEL</h2>
        		<p>Un ou plusieurs matériels à saisir</p>
        		<a href="equipment/add">Saisir</a>
        	</div>
        	
        <% } %>
        	
        </section>
        <img src="images?name=tracteur.png" alt="Image de trois tracto-pelles" width="80%" height="auto" >
	</main>
	
	<script>
		document.getElementById("burger").addEventListener('click', function(ev) {
			if(document.getElementById("burger-dropdown").className.includes("active")) {
				document.getElementById("burger-dropdown").classList.remove('active');
			} else {
				document.getElementById("burger-dropdown").classList.add('active');
			}
		});
	</script>
</body>

</html>