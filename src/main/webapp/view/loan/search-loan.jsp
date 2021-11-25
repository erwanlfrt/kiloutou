<%@page import="java.io.IOException"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.loan.Loan , model.object.user.* , model.object.equipment.* , java.util.ArrayList"%>
<%
  ArrayList<Loan> outdatedLoans = (ArrayList<Loan>) request.getAttribute("outdatedLoans");
  ArrayList<Loan> currentLoans = (ArrayList<Loan>) request.getAttribute("currentLoans");
  ArrayList<Loan> oldLoans = (ArrayList<Loan>) request.getAttribute("oldLoans");
  ArrayList<Loan> loansToCome = (ArrayList<Loan>) request.getAttribute("loansToCome");
  
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
    <title>Search Loan</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/loan/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/loan/css/search-loan.css" />
  </head>
  <body>
  
  	<jsp:include page="header-loan.jsp">
  		<jsp:param name="profile" value="<%= profile %>" />
  	</jsp:include>
  	
  	<main>
  		<div>
  			<h1>Emprunts</h1>
  		</div>
  		<section>
  			<div id="outdatedLoans" class="list-loan">
  				<h2>Emprunts en retard</h2>
  				<% if(outdatedLoans.size() > 0) { %>
  				<table>
        			<thead>
          				<tr>
            				<th>Utilisateur</th>
            				<th>Matériel</th>
            				<th> </th>
          				</tr>
        			</thead>

        			<tbody>
          			<% for(Loan loan : outdatedLoans) { %>
            			<tr>
              				<td data-label="Utilisateur"><%= loan.getUser().getMail()%></td>
              				<td data-label="Matériel"><%= loan.getEquipment().getName()%></td>
              				<td data-label=" "><a href="/kiloutou/loan/info?id=<%=loan.getId()%>">Détail</a></td>
            			</tr>
            		<% } %>
        			</tbody>
     			</table>
     			<% } else { %>
     			<p>Il n'y a pas d'emprunts en retard.</p>
     			<% } %>
  			</div>
  			<div id="actualLoans" class="list-loan">
  				<h2>Emprunts en cours</h2>
  				
  				<% if(currentLoans.size() > 0) { %>
  				<table>
        			<thead>
          				<tr>
            				<th>Utilisateur</th>
            				<th>Matériel</th>
            				<th> </th>
          				</tr>
        			</thead>

        			<tbody>
          			<% for(Loan loan : currentLoans) { %>
            			<tr>
              				<td data-label="Utilisateur"><%= loan.getUser().getMail()%></td>
              				<td data-label="Matériel"><%= loan.getEquipment().getName()%></td>
              				<td data-label=" "><a href="/kiloutou/loan/info?id=<%=loan.getId()%>">Détail</a></td>
            			</tr>
            		<% } %>
        			</tbody>
     			</table>
     			<% } else { %>
     			<p>Il n'y a pas d'emprunts en cours.</p>
     			<% } %>
  			</div>
  			<div id="incommingLoans" class="list-loan">
  				<h2>Emprunts à venir</h2>
  				
  				<% if(loansToCome.size() > 0) { %>
  				<table>
        			<thead>
          				<tr>
            				<th>Utilisateur</th>
            				<th>Matériel</th>
            				<th> </th>
          				</tr>
        			</thead>

        			<tbody>
          			<% for(Loan loan : loansToCome) { %>
            			<tr>
              				<td data-label="Utilisateur"><%= loan.getUser().getMail()%></td>
              				<td data-label="Matériel"><%= loan.getEquipment().getName()%></td>
              				<td data-label=" "><a href="/kiloutou/loan/info?id=<%=loan.getId()%>">Détail</a></td>
            			</tr>
            		<% } %>
        			</tbody>
     			</table>
     			<% } else { %>
     			<p>Il n'y a pas d'emprunts à venir.</p>
     			<% } %>
  			</div>
  			<div id="oldLoans" class="list-loan">
  				<h2>Emprunts terminés</h2>
  				
  				<% if(oldLoans.size() > 0) { %>
  				<table>
        			<thead>
          				<tr>
            				<th>Utilisateur</th>
            				<th>Matériel</th>
            				<th> </th>
          				</tr>
        			</thead>

        			<tbody>
          			<% for(Loan loan : oldLoans) { %>
            			<tr>
              				<td data-label="Utilisateur"><%= loan.getUser().getMail()%></td>
              				<td data-label="Matériel"><%= loan.getEquipment().getName()%></td>
              				<td data-label=" "><a href="/kiloutou/loan/info?id=<%=loan.getId()%>">Détail</a></td>
            			</tr>
            		<% } %>
        			</tbody>
     			</table>
     			<% } else { %>
     			<p>Il n'y a pas d'anciens emprunts.</p>
     			<% } %>
  			</div>
  		</section>
  	</main>
	<script src="${pageContext.request.contextPath}/view/loan/js/header.js"></script>
  </body>
</html>