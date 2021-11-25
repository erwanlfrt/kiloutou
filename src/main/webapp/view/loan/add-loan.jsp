<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.equipment.*"%>
<%@page import="model.object.user.User"%>
<%@page import="java.util.ArrayList"%>
<%
  ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
%>

<html>
<head>
<title>Nouvel emprunt</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/loan/css/header.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/loan/css/add-loan.css" />

<link  rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">

</head>
<body>

	<%@include file="../layout/header.jsp" %>

	<main>
		<div>
			<h1>Nouvel emprunt</h1>
		</div>
		<form id="form" method="POST" action="add">
			<section>
				<div id="step1" class="step active">
					<div>
						<p>Choisir le matériel à emprunter : </p>
						<div class="select">
							<select name="liste1" id="liste1">
                				<option value="" selected disabled hidden>Type de  matériel</option>
                				<option value="others">Autre</option>
                				<option value="vehicles">Véhicule</option>
                				<option value="vehicleAccessories">Accessoire de véhicule</option>
                				<option value="computers">Ordinateur</option>
                				<option value="computerAccessories">Accessoire d'ordinateur</option>
              				</select>
              				<label for="liste1">Type de matériel</label>
						</div>
						<div class="select">
							<select name="liste2" id="liste2">
                				<option value="" selected disabled hidden>Matériel</option>
              				</select>
              				<label for="liste2">Matériel</label>
						</div>
              			<input type="text" value="" hidden name="equipmentId" id="equipmentId" required>
					</div>
					<div class="recap" id="info1">
						<h2>Détail de l'&eacute;quipement</h2>
					</div>	
				</div>
				<div id="step2" class="step">
					<div>
						<p>Choisir l'utilisateur qui emprunte : </p>
						<div class="search-section">
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
  						</div>
  						<div id="listUsers">
        					<% for(User usr : users) { %>
           						<% if(usr.isReal()) { %>
           							<div class="user-item">
           								<img src="${pageContext.request.contextPath}/images?name=user.png" alt="Utilisateur" width="20px" height="20px" />
                						<p class="userItem active"><a><%= usr.getMail() %></a></p>
                						<p class="userItem"><a><%= usr.getFirstname() %> <%= usr.getName() %></a></p>
           								<img class="user-select" src="${pageContext.request.contextPath}/images?name=plus.png" alt="Choisir" width="20px" height="20px" />
           							</div>
           						<% } %>
        					<% } %>
      					</div>
      					<input type="text" value="" hidden required name="userMail" id="userMail">
  					</div>
					<div class="recap" id="info2">
						<h2>Détail de l'&eacute;quipement</h2>
					</div>	
				</div>
				<div id="step3" class="step">
					<div>
						<p>Choisir la date d'emprunt : </p>
						<label for="beginningDate">Date de début</label>
  						<input class="datepicker" data-provide="datepicker" name="beginningDate" id="beginningDate" required>
 
  						<label for="endDate">Date de fin</label>
              			<input class="datepicker" data-provide="datepicker" name="endDate" id="endDate" required>
					</div>
					<div class="recap" id="info3">
						<h2>Détail de l'&eacute;quipement</h2>
					</div>
				</div>
			</section>
			<section>
				<div class="section-button">
					<a href="${pageContext.request.contextPath}/welcome" id="cancel">Annuler</a>
					<a id="previous">Précédent</a>
					<a id="next" class="active">Suivant</a>
					<a id="validate">Confirmer</a>
				</div>
			</section>
		</form>
	<!--  
	
		<h1>Emprunt</h1>
		<form method="POST">
			<section>
				<div class="panel">
          <h1>Nouvel emprunt</h1>
          <form>
            <div class="equipment" id="equipmentForm">
              <h2>Sélectionner un équipement :</h2>
              <select name="liste1" id="liste1">
                <option value="" selected disabled hidden>Type de
                  matériel</option>
                <option value="others">Autre</option>
                <option value="vehicles">Véhicule</option>
                <option value="vehicleAccessories">Accessoire de véhicule</option>
                <option value="computers">Ordinateur</option>
                <option value="computerAccessories">Accessoire d'ordinateur</option>
              </select> 
    
              <select name="liste2" id="liste2">
                <option value="" selected disabled hidden>Matériel</option>
              </select>

              <input type="text" value="" hidden name="equipmentId" id="equipmentId" required>
            </div>

            <div id="userForm" class="user">
              <h2>Sélectionner un emprunteur : </h2>
              <input type="text" id="searchBarFirstname" onkeyup="searchUsers()" placeholder="Prénom">
              <input type="text" id="searchBarName" onkeyup="searchUsers()" placeholder="Nom">
              <table id="userTable">
                <thead >
                  <tr>
                    <th colspan = "2">Utilisateurs</th>
                  </tr>
                </thead>
                <tbody>
                  <%
                    for(User user : users) {
                      if(user.isReal()) {
                        %>
                        <tr class="row" >
                          <td><%= user.getName()%></td>
                          <td><%= user.getFirstname()%></td>
                          <td hidden><%= user.getMail()%></td>
                        </tr>
                        <%
                      }
                    }
                  %>
                </tbody>
              </table>

              <input type="text" value="" hidden required name="userMail" id="userMail">
            </div>

            <div class="recap">
              <h2>Récapitulatif</h2>
              <p id="recapUser">Emprunteur : </p>
              <input class="datepicker" data-provide="datepicker" name="beginningDate" id="beginningDate" required>
              <input class="datepicker" data-provide="datepicker" name="endDate" id="endDate" required>
            </div>
          </form>		
          
				</div>
				<div class="panel equipment" id="info">
				</div>
        <div class="section-button">
          <a href="${pageContext.request.contextPath}/welcome">
            <input type="button" value="annuler" id="cancel">
          </a>
          <button id="previous" disabled>Précédent</button>
          <div id="nextButtons">
            <input type="submit" value="ajouter un prêt" name="submit" id="submitInput" hidden disabled>
            <button id="next">Suivant</button>
          </div>
          
        </div>
			</section>
		</form>-->
	</main>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/view/loan/js/header.js"></script>
  	<script src="${pageContext.request.contextPath}/view/user/js/search-user.js"></script>
	<script src="${pageContext.request.contextPath}/view/loan/js/add-loan.js"></script>
  
</body>
</html>