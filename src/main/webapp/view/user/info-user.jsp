<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.user.* , java.io.IOException, java.util.ArrayList, model.object.loan.Loan"%>
<%
  User user = (User)request.getAttribute("user");
  Employee employee = (Employee)request.getAttribute("employee");
  ArrayList<Loan> loans = (ArrayList<Loan>) request.getAttribute("loans");
%>
<%
User logged = (User) request.getSession().getAttribute("user");
Employee loggedEmployee = (Employee) request.getSession().getAttribute("employee");

String login = "";
Profil profile = null;

if (user == null || employee == null) {
	request.setAttribute("message", "Vous n'êtes pas autorisé à accéder à cette page.");
	RequestDispatcher rd = getServletContext().getRequestDispatcher("/error");
	try {
		rd.forward(request, response);
	} catch (ServletException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
} else {
	login = user.getLogin();
	profile = loggedEmployee.getProfil();
}

%>
<html>
  <head>
    <meta charset="utf-8">
    <title>Info user</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/user/css/info-user.css" />
  </head>
  <body>
  
  	<jsp:include page="header-user.jsp">
  		<jsp:param name="profile" value="<%= profile %>" />
  	</jsp:include>
  	
  	<main>
  		<div>
  			<h1>UTILISATEUR</h1>
  		</div>
  		<section>
  			<div class="user">
  				<div>
  					<div class="user-name">
  						<img src="${pageContext.request.contextPath}/images?name=profil.png" alt="User" width="70px" height="70px">
  						<div>
  							<h2><%= user.getFirstname() %> <%= user.getName() %></h2>
  							<% if(employee != null) { %>
  							<p><%= employee.getFunction() %></p>
  							<% } %>
  						</div>
  					</div>
  					<div>
  						<img title="Modifier" class="user-edit" src="${pageContext.request.contextPath}/images?name=edit.png" alt="Modifier" width="25px" height="25px" onclick="window.location.replace('${pageContext.request.contextPath}/user/modify?mail=<%= user.getMail() %>')" />
           				<img title="Supprimer" class="user-bin" src="${pageContext.request.contextPath}/images?name=bin.png" alt="Supprimer" width="25px" height="25px" onclick="deleteUser('<%= user.getMail() %>')"/>
  					</div>
  				</div>
  				<div>
  					<p>Login : <%= user.getLogin() %></p>
  					<p>Adresse : <%= user.getAddress() %></p>
  					<p>Téléphone : <%= user.getPhoneNumber() %></p>
  					<p>Mail : <%= user.getMail() %></p>
  					<% if(employee != null) { %>
  					<p>N° Bureau : <%= employee.getDeskNumber() %></p>
  					<p>Profil : <%= employee.getProfil().name() %></p>
  					<p>Service : <%= employee.getService() %></p>
  					<% } %>
  				</div>
  			</div>
  			<div class="loan">
  				<h2>Emprunts</h2>
  				<div class="list-loan">
  				<%
  				int count = 0;
  				if(loans.size() > 0) { 
            
            %>
  					<% for(Loan loan : loans) { %>
              			<% if(!loan.isOver() && !loan.hasNotStarted()) { %>
  							<div class="list-loan-item">
  								<p>
  								<%= loan.getEquipment().getName() %>
  								<% if(loan.isLate()) { %>
                      count++;
  									<span> - En retard</span>
  								<% } %>
  								</p>
  								<a href="" onClick="returnEquipments('<%= loan.getId() %>')">Restituer</a>
  							</div>
  						<% } %>
  					<% } %>
  				<% } 
          		if(loans.size() <=0 || count == 0) { %>
  					<p class="no-loan">Cet utilisateur n'a pas d'emprunt.</p>
  				<% } %>
  				</div>
  			</div>
  		</section>
  	</main>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script>
      function deleteUser() {
        
        let data = {
          mail : '<%= user.getMail()%>'
        }
        $.ajax({
          url: "/kiloutou/user/delete",
          method: "POST",
          contentType: "application/json", // NOT dataType!
          data: JSON.stringify(data),
        }).done(function() {
        let url = window.location.href;
        url = url.substring(0, url.indexOf('info'));
        url += 'search'
        window.location.href = url;
        });

        
      }

      function returnEquipments(id) {
		$.ajax({
			url: "/kiloutou/user/info",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
              id: id
            }),
        });
    	location.reload(); 
      }
    </script>
</html>