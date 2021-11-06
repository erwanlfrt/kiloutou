
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.loan.Loan , model.object.user.* , model.object.equipment.*"%>
<%
  Loan loan = (Loan)request.getAttribute("loan");
  Equipment equipment = loan.getEquipment();
  User user = loan.getUser();
%>
<html>
  <head>
    <title>Info emprunt</title>
  </head>
  <body>
    <h1>Information sur un emprunt</h1>

    <div id="info">
      <p><b>Date d'emprunt : </b><%= loan.getStringBeginningDate()%></p>
      <p><b>Date de rendu : </b><%= loan.getStringEndDate()%></p>
    </div>

    <div id="user">
      <h2>Emprunteur</h2>

      <p><b>Nom : </b> <%= user.getName()%></p>
      <p><b>Prénom : </b><%= user.getFirstname()%></p>
      <p><b>Adresse mail : </b><%= user.getMail()%></p>
      <p><b>N° de téléphone : </b><%= user.getPhoneNumber()%></p>
      <p><b>Adresse : </b><%= user.getAddress()%></p>
    </div>

    <div id="equipment">
      <h2>Équipement : </h2>
      <p><b>Nom : </b><%= equipment.getName()%></p>
      <p><b>Illustration : </b></p>
      <img src="<%= equipment.getImageUrl()%>" style="width : 300px ; height : 300px">
    </div>
    
    
  </body>
</html>