<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.user.* , java.io.IOException"%>

<%
	Employee emp = (Employee) request.getSession().getAttribute("employee");
	Profil role = null;

	if (emp != null) {
		role = emp.getProfil();
	}
%>

<header>
	<div>
		<img src="${pageContext.request.contextPath}/images?name=logo.png" width="auto" height="30px"
			alt="KILOUPRESQUETOUT">
	</div>
	<nav class="nav-desktop">
		<a href="${pageContext.request.contextPath}/welcome">Accueil</a>

		<% if(role == Profil.ADMIN || role == Profil.LOAN_ADMIN) { %>

		<div class="dropdown">
			<a href="#">Emprunts</a>
			<div class="dropdown-list">
				<div class="dropdown-item">
					<img src="${pageContext.request.contextPath}/images?name=add.png" alt="add" width="auto" height="18px">
					<a href="${pageContext.request.contextPath}/loan/add">Ajouter</a>
				</div>
				<hr>
				<div class="dropdown-item">
					<img src="${pageContext.request.contextPath}/images?name=search.png" alt="search" width="auto"
						height="18px"> <a href="${pageContext.request.contextPath}/loan/search">Chercher</a>
				</div>
			</div>
		</div>

		<% } %>
		<% if(role == Profil.EQUIPMENT_ADMIN || role == Profil.ADMIN) { %>

		<div class="dropdown">
			<a href="#" class="active">Matériels</a>
			<div class="dropdown-list">
				<div class="dropdown-item">
					<img src="${pageContext.request.contextPath}/images?name=add.png" alt="add" width="auto" height="18px">
					<a href="${pageContext.request.contextPath}/equipment/add">Ajouter</a>
				</div>
				<hr>
				<div class="dropdown-item">
					<img src="${pageContext.request.contextPath}/images?name=search.png" alt="search" width="auto"
						height="18px"> <a href="${pageContext.request.contextPath}/equipment/search">Chercher</a>
				</div>
			</div>
		</div>

		<% } %>
		<% if(role == Profil.ADMIN) { %>

		<div class="dropdown">
			<a href="#">Utilisateurs</a>
			<div class="dropdown-list">
				<div class="dropdown-item">
					<img src="${pageContext.request.contextPath}/images?name=add.png" alt="add" width="auto" height="18px">
					<a href="${pageContext.request.contextPath}/user/add">Ajouter</a>
				</div>
				<hr>
				<div class="dropdown-item">
					<img src="${pageContext.request.contextPath}/images?name=search.png" alt="search" width="auto"
						height="18px"> <a href="${pageContext.request.contextPath}/user/search">Chercher</a>
				</div>
			</div>
		</div>
		
		<a href="${pageContext.request.contextPath}/stats/statistics">Statistiques</a>

		<% } %>

	</nav>
	<div class="disconnect nav-desktop">
		<img src="${pageContext.request.contextPath}/images?name=power-off.png" alt="Déconnexion" width="auto"
			height="20px"> <a href="${pageContext.request.contextPath}/logout">Se déconnecter</a>
	</div>
	<div class="nav-mobile">
		<img id="burger" src="${pageContext.request.contextPath}/images?name=menu.png" alt="Menu" width="30px"
			height="auto">
		<div id="burger-dropdown" class="nav-dropdown">
			<div class="accueil-item">
				<a href="${pageContext.request.contextPath}/welcome">Accueil</a>
			</div>

			<hr>

			<% if(role == Profil.ADMIN || role == Profil.LOAN_ADMIN) { %>

			<div class="dropdown">
				<a href="#">Emprunts</a>
				<div class="dropdown-list">
					<div class="dropdown-item">
						<img src="${pageContext.request.contextPath}/images?name=add.png" alt="add" width="auto"
							height="18px"> <a href="${pageContext.request.contextPath}/loan/add">Ajouter</a>
					</div>
					<hr>
					<div class="dropdown-item">
						<img src="${pageContext.request.contextPath}/images?name=search.png" alt="search" width="auto"
							height="18px"> <a href="${pageContext.request.contextPath}/loan/search">Chercher</a>
					</div>
				</div>
			</div>

			<% } %>
			<% if(role == Profil.ADMIN || role == Profil.EQUIPMENT_ADMIN) { %>

			<div class="dropdown">
				<a href="#" class="active">Matériels</a>
				<div class="dropdown-list">
					<div class="dropdown-item">
						<img src="${pageContext.request.contextPath}/images?name=add.png" alt="add" width="auto"
							height="18px"> <a href="${pageContext.request.contextPath}/equipment/add">Ajouter</a>
					</div>
					<hr>
					<div class="dropdown-item">
						<img src="${pageContext.request.contextPath}/images?name=search.png" alt="search" width="auto"
							height="18px"> <a href="${pageContext.request.contextPath}/equipment/search">Chercher</a>
					</div>
				</div>
			</div>

			<% } %>
			<% if(role == Profil.ADMIN) { %>

			<div class="dropdown">
				<a href="#">Utilisateurs</a>
				<div class="dropdown-list">
					<div class="dropdown-item">
						<img src="${pageContext.request.contextPath}/images?name=add.png" alt="add" width="auto"
							height="18px"> <a href="${pageContext.request.contextPath}/user/add">Ajouter</a>
					</div>
					<hr>
					<div class="dropdown-item">
						<img src="${pageContext.request.contextPath}/images?name=search.png" alt="search" width="auto"
							height="18px"> <a href="${pageContext.request.contextPath}/user/search">Chercher</a>
					</div>
				</div>
			</div>
			
			<div class="accueil-item">		
				<a href="${pageContext.request.contextPath}/stats/statistics">Statistiques</a>
			</div>
			
			<% } %>

			<hr>

			<div class="deconnexion-item">
				<img src="${pageContext.request.contextPath}/images?name=power-off.png" alt="Déconnexion" width="auto"
					height="20px"> <a href="${pageContext.request.contextPath}/logout">Se déconnecter</a>
			</div>

		</div>
	</div>
</header>