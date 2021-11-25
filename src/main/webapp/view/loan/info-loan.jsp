
<%@page import="java.io.IOException"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.loan.Loan , model.object.user.* , model.object.equipment.* , java.time.LocalDate"%>
<%
  Loan loan = (Loan)request.getAttribute("loan");
  Equipment equipment = loan.getEquipment();
  User user = loan.getUser();
  
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
    <title>Info emprunt</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/loan/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/loan/css/info-loan.css" />
  </head>
  <body>
  
  	<jsp:include page="header-loan.jsp">
  		<jsp:param name="profile" value="<%= profile %>" />
  	</jsp:include>
  	
  	<main>
  		<div>
  			<h1>Information sur un emprunt</h1>
  		</div>
  		<section>
  			<div class="info">
  				<p>Date d'emprunt : <%= loan.getStringBeginningDate()%></p>
     			<p>-</p>
     			<p>Date de rendu : <%= loan.getStringEndDate()%></p>
  			</div>
  			<div class="user">
  				<div class="user-name">
  					<img src="${pageContext.request.contextPath}/images?name=profil.png" alt="User" width="70px" height="70px">
  					<h2><%= user.getFirstname() %> <%= user.getName() %></h2>
  				</div>
      			<p>Mail : <%= user.getMail()%></p>
      			<p>Téléphone : <%= user.getPhoneNumber()%></p>
      			<p>Adresse : <%= user.getAddress()%></p>
  			</div>
  			<div class="equipment">
  				<img src="<%= equipment.getImageUrl() %>" alt="Equipement" width="70px" height="auto">
  				<h2><%= equipment.getName()%></h2>
  			</div>
  			<div class="button-section">
  				<a href="/kiloutou/loan/modify?id=<%=loan.getId()%>">Modifier</a>
        
  				<form method="POST">
      			<% if(loan.isOver()) { %>
        			<!-- <input type="text" value="signaler comme non rendu" name="updateLoan" class="over"> -->
        			<button type="submit" value="signaler comme non rendu" name="updateLoan" class="over">Signaler comme non rendu</button>
        		<% } else if(!loan.isOver() && loan.getBeginningDate().isBefore(LocalDate.now())){ %>
        			<!-- <input type="text" value="signaler comme rendu" name="updateLoan" class="notover"> -->
        			<button type="submit" value="signaler comme rendu" name="updateLoan" class="notover">Signaler comme rendu</button>
        		<% } %>
    			</form>
  			</div>
  		</section>
  	</main>
  	<script src="${pageContext.request.contextPath}/view/loan/js/header.js"></script>
  </body>
</html>