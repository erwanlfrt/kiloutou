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
<body>
	<main>
 		<form action="welcome" method="post">
      		<img src="images?name=logo.png" alt="KILOUPRESQUETOUT" width="250px" height="auto">
      		<h1>SE CONNECTER</h1>
      		<input class="element inputForm" type="text" placeholder="Saisissez votre adresse mail" name="mailAddress" required>
      		<input class="element inputForm" type="password" placeholder="Saisissez votre mot de passe" name="pwd" required>
      		<button id="submitButton" class="element" type="submit"><strong>CONTINUER</strong></button>
      		<p id="error"><%= error%></p>
 		</form>
 	</main>
</body>

</html>