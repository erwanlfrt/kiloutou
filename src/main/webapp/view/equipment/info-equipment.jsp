<%@page import="model.object.user.Profil"%>
<%@page import="model.object.user.Employee"%>
<%@page import="model.object.user.User"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.equipment.* , java.io.IOException"%>
<%
  Equipment equipment = (Equipment) request.getAttribute("equipment");
  Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");
  Car car = (Car) request.getAttribute("car");
  Bike bike = (Bike) request.getAttribute("bike");
  ComputerAccessory computerAccessory = (ComputerAccessory) request.getAttribute("computerAccessory");
  VehicleAccessory vehicleAccessory = (VehicleAccessory) request.getAttribute("vehicleAccessory");
  Computer computer = (Computer) request.getAttribute("computer");
  boolean isAvailable = (equipment == null) || equipment.isAvailable();
  String state = "";
  if(vehicle != null) {
    state = vehicle.getState();
    if(state.equals("GOOD")) {
      state = "Bon état";
    }
    else if(state.equals("AVERAGE")) {
      state = "Légère usure";
    }
    else {
      state = "Mauvais état";
    }
  }
  
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
<html>
  <head>
    <title>Information équipement</title>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/view/equipment/css/info-equipment.css" />
  </head>
  <body>
  
  	<jsp:include page="header-equipment.jsp">
  		<jsp:param name="profile" value="<%= profile %>" />
  	</jsp:include>
  	
  	
  	<main>
  		<div>
  			<h1>Equipment<%= vehicle != null ? "/Véhicule" : "" %><%= car != null ? "/Voiture" : ""%><%= bike != null ? "/Moto" : ""%><%=computerAccessory != null ? "/Accessoire Informatique" : ""%><%= vehicleAccessory != null ? "/Accessoire automobile" : ""%><%= computer != null ? "/Ordinateur" : ""%></h1>
  		</div>
  		<section>
  			<div class="equipment">
  				<div class="equipment-name">
  					<img src="<%= equipment.getImageUrl() %>" alt="Equipement" width="100px" height="100px">
  					<div>
  						<h2><%= equipment.getName() %></h2>
  						<div class="<%= equipment.isAvailable() ? "disponible" : "indisponible" %>">
  							<div></div>
  							<p><%= equipment.isAvailable() ? "Disponible" : "Indisponible" %></p>
  						</div>
  					</div>
  					<div>
  						<img title="Modifier" class="equipment-edit" src="${pageContext.request.contextPath}/images?name=edit.png" alt="Modifier" width="25px" height="25px" onclick="window.location.replace('${pageContext.request.contextPath}/equipment/modify?id=<%= equipment.getId() %>')" />
  					</div>
  				</div>
  				<div>
  				<% if (vehicle != null) { %>
					<p>Kilométrage : <%= vehicle.getKilometers()%></p>
          			<p>Kilométrage de renouvellement : <%= vehicle.getRenewalKilometers() %></p>
          			<p>Marque : <%= vehicle.getBrand() %></p>
          			<p>Modèle : <%= vehicle.getModel() %></p>
          			<p>Immatriculation : <%= vehicle.getRegistrationNumber() %></p>
          			<p>État : <%= state %></p>
          			<p>Vitesse maximale : <%= vehicle.getMaxSpeed() %></p>
          			<p>Puissance : <%= vehicle.getPower() %></p>
          			<% if (car != null) { %>
          				<p>Nombre de places : <%= car.getNumberOfSeats()%></p>
          			<% } else if (bike != null) { %>
          				<p>Nombre de cylindres : <%= bike.getNumberOfCylinders()%></p>
          			<% } %>
          		<% } else if (computer != null) { %>
          			<p>Marque : <%= computer.getBrand() %></p>
         			<p>Modèle : <%= computer.getModel() %></p>
          			<p>N° de série : <%= computer.getSerialNumber() %></p>
          			<p>Taille de la mémoire : <%= computer.getMemorySize() %></p>
          			<p>Portable : <%= computer.isLaptop() ? "oui" : "non" %></p>
          			<p>Taille de l'écran : <%= computer.getScreenSize() %></p>
          			<p>Date d'achat : <%= computer.getPurchaseDate() %></p>
          			<p>Date de renouvellement : <%= computer.getRenewalDate() %></p>
          			<div>
          				<h3>Accessoire lié</h3>
          				<div class="accessory">
          					<div id="processor">
          						<h3>Processeur</h3>
            					<p>Marque : <%= computer.getProcessor().getBrand() %></p>
            					<p>Nom : <%= computer.getProcessor().getName() %></p>
            					<p>Nombre de coeurs : <%= computer.getProcessor().getNumberOfCores() %></p>
            					<p>Fréquence : <%= computer.getProcessor().getFrequency() %></p>
          					</div>
          					<div id="graphicCard">
          						<h3>Carte graphique</h3>
            					<p>Marque : <%= computer.getGraphicCard().getBrand() %></p>
            					<p>Nom : <%= computer.getGraphicCard().getName() %></p>
            					<p>Fréquence : <%= computer.getGraphicCard().getFrequency() %></p>
          					</div>
          				</div>
          			</div>
          		<% } %>
  				</div>
  			</div>
  		</section>
  	</main>
  	<script src="${pageContext.request.contextPath}/view/equipment/js/header.js"></script>
  </body>
</html>