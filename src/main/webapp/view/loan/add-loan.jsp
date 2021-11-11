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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/loan/css/styleAddLoan.css" />

<link  rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"></link>
<link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">

</head>
<body onload="main()">
	<main>
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
                      %>
                      <tr class="row" >
                        <td><%= user.getName()%></td>
                        <td><%= user.getFirstname()%></td>
                        <td hidden><%= user.getMail()%></td>
                      </tr>
                      <%
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
		</form>
	</main>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/view/loan/js/main.js"></script>
  
  
</body>
</html>