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
	<header>
		<div>
			<img src="images?name=logo.png" width="auto" height="30px" alt="KILOUPRESQUETOUT" >
		</div>
		<nav>
			<a href="#" class="active">Accueil</a>
			<a href="#">Emprunts</a>
			<a href="#">Matériels</a>
			<a href="#">Utilisateurs</a>
		</nav>
		<div class="disconnect">
			<img src="images?name=power-off.png" alt="Déconnexion" width="auto" height="20px" >
			<a href="#">Se déconnecter</a>
		</div>
	</header>
	<main>
		<h1>Application de Gestion d'Emprunt</h1>
        <h2>Bienvenue <%=login %> !</h2>
        <section class="section-nav">
        	<div class="section-nav-item">
        		<h2>NOUVEL EMPRUNT</h2>
        		<p>Un nouvel emprunt à faire ?</p>
        		<a>Emprunter</a>
        	</div>
        	
        	<div class="section-nav-item">
        		<h2>NOUVEAU MAT&Eacute;RIEL</h2>
        		<p>Un ou plusieurs matériels à saisir</p>
        		<a>Découvrir</a>
        	</div>
        	
        	<div class="section-nav-item">
        		<h2>NOUVEL EMPRUNT</h2>
        		<p>Un nouvel emprunt à faire ?</p>
        		<a>Emprunter</a>
        	</div>
        	
        </section>
        <img src="images?name=tracteur.png" alt="Image de trois tracto-pelles" width="80%" height="auto" >
	</main>


<!-- 
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
 -->
</body>

</html>