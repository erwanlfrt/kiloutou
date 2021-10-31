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
%>
<html>
  <head>
    <title>Info equipment</title>
  </head>
  <body>
    <div>
      <h2>Equipment<%= vehicle != null ? "/Véhicule" : "" %><%= car != null ? "/Voiture" : ""%><%= bike != null ? "/Moto" : ""%><%=computerAccessory != null ? "/Accessoire Informatique" : ""%><%= vehicleAccessory != null ? "/Accessoire automobile" : ""%><%= computer != null ? "/Ordinateur" : ""%></h2>
      <p><b>Nom : </b><%= equipment.getName() %></p>
      <p><b>Disponibilité </b><%= equipment.isAvailable() ? "disponible" : "pas disponible" %></p>
      <p><b>illustration</b></p>
      <img src="<%= equipment.getImageUrl()%>" style="width : 300px ; height : 300px">

      <%  
        if(vehicle != null) {
          %>
          <p><b>Kilométrage : </b><%= vehicle.getKilometers()%></p>
          <p><b>Kilométrage de renouvellement : </b><%= vehicle.getRenewalKilometers() %></p>
          <p><b>Marque : </b><%= vehicle.getBrand() %></p>
          <p><b>Modèle : </b><%= vehicle.getModel() %></p>
          <p><b>Immatriculation : </b><%= vehicle.getRegistrationNumber() %></p>
          <p><b>État : </b><%= state %></p>
          <p><b>Vitesse maximale : </b><%= vehicle.getMaxSpeed() %></p>
          <p><b>Puissance : </b><%= vehicle.getPower() %></p>
          <%
          if(car != null) {
            %>
            <p><b>Nombre de places : </b><%= car.getNumberOfSeats()%></p>
            <%
          }
          else if( bike != null ){
            %>
            <p><b>Nombre de cylindres : </b><%= bike.getNumberOfCylinders()%></p>
            <%
          }
        }
        else if(computer != null) {
          %>
          <p><b>Marque : </b><%= computer.getBrand()%></p>
          <p><b>Modèle : </b><%= computer.getModel()%></p>
          <p><b>N° de série : </b><%= computer.getSerialNumber()%></p>
          <p><b>Taille de la mémoire : </b><%= computer.getMemorySize()%></p>
          <p><b>Portable ? </b><%= computer.isLaptop() ? "oui" : "non"%></p>
          <p><b>Taille de l'écran : </b><%= computer.getScreenSize()%></p>
          <p><b>Date d'achat : </b><%= computer.getPurchaseDate()%></p>
          <p><b>Date de renouvellement : </b><%= computer.getRenewalDate()%></p>
          <div id="processor">
            <p><b>Marque : </b><%= computer.getProcessor().getBrand()%></p>
            <p><b>Nom : </b><%= computer.getProcessor().getName()%></p>
            <p><b>Nombre de coeurs : </b><%= computer.getProcessor().getNumberOfCores()%></p>
            <p><b>Fréquence : </b><%= computer.getProcessor().getFrequency()%></p>
          </div>
          <div id="graphicCard">
            <p><b>Marque : </b><%= computer.getGraphicCard().getBrand()%></p>
            <p><b>Nom : </b><%= computer.getGraphicCard().getName()%></p>
            <p><b>Fréquence : </b><%= computer.getGraphicCard().getFrequency()%></p>
          </div>
          <%
        } 
      %>
    </div>
    
  </body>
</html>