<%@ page language="java" pageEncoding="UTF-8"%>
<%@page
	import="model.object.user.* , javax.servlet.* , java.io.IOException"%>
<%
User logged = (User) request.getSession().getAttribute("user");
Employee loggedEmployee = (Employee) request.getSession().getAttribute("employee");

User user = (User) request.getAttribute("user");
Employee employee = (Employee) request.getAttribute("employee");

String login = "";
Profil profile = null;

if (user == null) {
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
    <title>Modify user</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/user/css/modify-user.css" />
  </head>
  <body>
  
  	<jsp:include page="header-user.jsp">
  		<jsp:param name="profile" value="<%= profile %>" />
  	</jsp:include>
  	
  	<main>
  		<section>
  			<h1>MODIFIER UTILISATEUR</h1>
  			<form id="addForm" method="POST" name="addForm">
  				<p><i>Les champs marqués par * sont obligatoires.</i></p>
				<div class="form-item input-group">
					<input type="text" value="<%= user.getName() %>" name="name" required>
					<label for="name">Nom</label>
					<span class="bar"></span>
				</div>
				<div class="form-item input-group">
        			<input type="text" value="<%= user.getFirstname() %>" name="firstname" required>
					<label for="firstname">Prénom</label>
       	 		</div>
       	 		<div class="form-item input-group">
        			<input type="text" value="<%= user.getLogin() %>" name="login" required>
					<label for="login">Login</label>
       	 		</div>
       	 		<div class="form-item input-group">
				<% if(logged.getMail().equals(user.getMail())) { %>
            		<input type="password" value="" name="password" required>
            		<label for="password">Mot de passe</label>
            	<% } else { %>
            		<button type="button" id="resetPasswordButton" onClick="resetPassword()">Regénérer un mot de passe</button>
            	<% } %>
       	 		</div>
       	 		<div class="form-item input-group">
        		<% if(user.getMail().equals("")) { %>
            		<input type="text" value="<%= user.getMail()%>" name="email" required>
            	<% } else { %>
            		<input type="text" value="<%= user.getMail()%>" name="email" readonly>
            	<% } %>
					<label for="email">Addresse mail</label>
       	 		</div>
       	 		<div class="form-item input-group">
        			<input type="text" value="<%= user.getAddress()%>" name="address" required>
       	 			<label for="address">Adresse</label>
				</div>
				<div class="form-item input-group">
        			<input type="text" value="<%= user.getPhoneNumber()%>" name="phoneNumber" required>
        			<label for="phoneNumber">Numéro de téléphone</label>
       	 		</div>
       	 		<% if (employee == null) { %>
            	<div class="form-item" id="employeeZone">
              		<p>S'agit-il d'un employé ? ?</p>
              		
              		<label for="isEmployee">Oui</label>
              		<input type="radio" value="true" name="isEmployee" onClick="handleEmployeeZone(this)">

              		<label for="isEmployee">Non</label>
              		<input type="radio" value="false" name="isEmployee" onClick="handleEmployeeZone(this)" checked>
            	</div>
            	<% } else { %>
            	<div id="employeeForm">
            		<div class="form-item input-group">
                		<input type="text" value="<%= employee.getFunction() %>" name="employeeFunction" required>
                		<label for="employeeFunction">Fonction</label>
					</div>
					<div class="form-item input-group">
                		<input type="text" value="<%= employee.getService() %>" name="employeeService">
                		<label for="service">Service</label>
					</div>
					<div class="form-item input-group">
                		<input type="text" value="<%= employee.getDeskNumber() %>" name="deskNumber">
                		<label for="deskNumber">Numéro bureau</label>
                	</div>
                	<div class="form-item profil-item">
                  		<label>Profil : </label>
						<div>
							<div>
                    			<input type="radio" value="ADMIN" name="profile" 
                    			<% if(employee.getProfil().equals(Profil.ADMIN)) { %>
                    				checked
                    			<% } %>
                    			>
								<label for="profile">administrateur</label>
							</div>
							<div>
                    			<input type="radio" value="EQUIPMENT_ADMIN" name="profile" 
                    			<% if(employee.getProfil().equals(Profil.EQUIPMENT_ADMIN)) { %>
                    				checked
                    			<% } %>
                    			>
                    			<label for="profile">responsable équipement</label>
							</div>
							<div>
                    			<input type="radio" value="LOAN_ADMIN" name="profile" 
                    			<% if(employee.getProfil().equals(Profil.LOAN_ADMIN)) { %>
                    				checked
                    			<% } %>
                    			>
                    			<label for="profile">responsable emprunt</label>
                    		</div>
						</div>
                   </div>
				</div>
            	<% } %>
       	 		
				<div class="form-buttons">
  					<button type="button" id="button-cancel" class="cancel">Annuler</button>
  					<button type="submit" value="submit" class="validate">Confirmer</button>
  				</div>
  			</form>
  		</section>
  	</main>
     
  </body>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  <script src="${pageContext.request.contextPath}/view/user/js/header.js"></script>
  <script>
  	
  	document.getElementById("button-cancel").addEventListener('click', function() {
  		location.href = "/kiloutou/welcome";
  	});
  
  	document.getElementById("burger").addEventListener('click', function(ev) {
		if(document.getElementById("burger-dropdown").className.includes("active")) {
			document.getElementById("burger-dropdown").classList.remove('active');
		} else {
			document.getElementById("burger-dropdown").classList.add('active');
		}
	});
  
    function handleEmployeeZone(radio) {
      let isEmployee = radio.value === "true";
      if(isEmployee) {
        document.getElementById("employeeZone").innerHTML += `
                                                            <div id="employeeForm" style="display : flex ; flex-direction: column; align-items: flex-start">
                                                              <div style="display : flex ; flex-direction: column; align-items: flex-start">
                                                                <label for="employeeFunction"><b>Fonction : </b></label>
                                                                <input type="text" value="" name="employeeFunction">

                                                                <label for="service"><b>Service : </b></label>
                                                                <input type="text" value="" name="employeeService">

                                                                <label for="deskNumber"><b>Numéro bureau : </b></label>
                                                                <input type="text" value="" name="deskNumber">
                                                                <div>
                                                                    <label><b>Profil : </b></label>

                                                                      <label for="profile"><b>administrateur</b></label>
                                                                      <input type="radio" value="ADMIN" name="profile">

                                                                      <label for="profile"><b>responsable équipment</b></label>
                                                                      <input type="radio" value="EQUIPMENT_ADMIN" name="profile">

                                                                      <label for="profile"><b>responsable emprunt</b></label>
                                                                      <input type="radio" value="LOAN_ADMIN" name="profile">
                                                                    
                                                                </div>
                                                              </div>
                                                            </div>
                                  `;
        document.addForm.isEmployee[0].checked = true;
      } else {
        let employeeForm = document.getElementById("employeeForm")
        if(employeeForm !== null) {
          employeeForm.innerHTML = "";
          document.getElementById("employeeZone").removeChild(employeeForm);
        }
      }
    }

    function resetPassword() {
      let data = {
        mail : '<%= user.getMail()%>'
      }
      $.ajax({
        url: "/kiloutou/user/resetPassword",
        method: "POST",
        contentType: "application/json", // NOT dataType!
        data: JSON.stringify(data),
      });
    }
  </script>
</html>