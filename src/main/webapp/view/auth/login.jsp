<%@ page language="java" pageEncoding="UTF-8"%>
<% 
  String error = (String)request.getAttribute("error");
  if(error == null) {
    error = "";
  }
%>
<html>
<head>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/auth/css/login.css" />
</head>
<form action="welcome" method="post">
  <div id="main">
    <div id="logDiv">
      <h1 class="element">Kiloutou</h1>
      <input class="element inputForm" type="text" placeholder="Saisissez votre adresse mail" name="mailAddress" required>
      <input class="element inputForm" type="password" placeholder="Saisissez votre mot de passe" name="pwd" required>
      <button id="submitButton" class="element" type="submit">Login</button>
      <p id="error"><%= error%></p>
    </div>
  </div>
  
</form>
</html>