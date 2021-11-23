<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.user.* , java.io.IOException"%>
<% String profile = request.getParameter("profile"); %>

<header>
	<div>
		<img src="${pageContext.request.contextPath}/images?name=logo.png" width="auto" height="30px"
			alt="KILOUPRESQUETOUT">
	</div>
	<nav class="nav-desktop">
		<a href="${pageContext.request.contextPath}/welcome">Accueil</a>

		<% if(profile.equals(Profil.ADMIN.toString()) || profile.equals(Profil.LOAN_ADMIN.toString())) { %>

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
		<% if(profile.equals(Profil.EQUIPMENT_ADMIN.toString()) || profile.equals(Profil.ADMIN.toString())) { %>

		<div class="dropdown">
			<a href="#">Matériels</a>
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
		<% if(profile.equals(Profil.ADMIN.toString())) { %>

		<div class="dropdown">
			<a href="#" class="active">Utilisateurs</a>
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

			<% if(profile.equals(Profil.ADMIN.toString()) || profile.equals(Profil.LOAN_ADMIN.toString())) { %>

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
			<% if(profile.equals(Profil.EQUIPMENT_ADMIN.toString()) || profile.equals(Profil.ADMIN.toString())) { %>

			<div class="dropdown">
				<a href="#">Matériels</a>
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
			<% if(profile.equals(Profil.ADMIN.toString())) { %>

			<div class="dropdown">
				<a href="#"  class="active">Utilisateurs</a>
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

			<% } %>

			<hr>

			<div class="deconnexion-item">
				<img src="${pageContext.request.contextPath}/images?name=power-off.png" alt="Déconnexion" width="auto"
					height="20px"> <a href="${pageContext.request.contextPath}/logout">Se déconnecter</a>
			</div>

		</div>
	</div>
</header>