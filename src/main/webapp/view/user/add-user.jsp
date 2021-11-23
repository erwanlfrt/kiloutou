<%@ page language="java" pageEncoding="UTF-8"%>
<%@page
	import="model.object.user.* , javax.servlet.* , java.io.IOException"%>
<%

User user = (User) request.getSession().getAttribute("user");
Employee employee = (Employee) request.getSession().getAttribute("employee");

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
	profile = employee.getProfil();
}
%>
<html>
  <head>
    <meta charset="utf-8">
    <title>Add user</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/user/css/add-user.css" />
  </head>
  <body>
  
  	<jsp:include page="header-user.jsp">
  		<jsp:param name="profile" value="<%= profile %>" />
  	</jsp:include>
  	
  	<main>
  		<section>
  			<h1>NOUVEL UTILISATEUR</h1>
  			<form id="addForm" method="POST" name="addForm">
  				<p><i>Les champs marqués par * sont obligatoires.</i></p>
				<div class="form-item input-group">
					<input type="text" name="name" required>
					<label for="name">Nom</label>
					<span class="bar"></span>
				</div>
				<div class="form-item input-group">
        			<input type="text" name="firstname" required>
					<label for="firstname">Prénom</label>
       	 		</div>
       	 		<div class="form-item input-group">
        			<input type="text" name="login" required>
					<label for="login">Login</label>
       	 		</div>
       	 		<div class="form-item input-group">
            		<input type="password" name="password" required>
            		<label for="password">Mot de passe</label>
       	 		</div>
       	 		<div class="form-item input-group">
            		<input type="text" name="email" required>
					<label for="email">Addresse mail</label>
       	 		</div>
       	 		<div class="form-item input-group">
        			<input type="text" name="address" required>
       	 			<label for="address">Adresse</label>
				</div>
				<div class="form-item input-group">
        			<input type="text" name="phoneNumber" required>
        			<label for="phoneNumber">Numéro de téléphone</label>
       	 		</div>
       	 		<div class="form-item employee-item" id="employeeZone">
              		<p>S'agit-il d'un employé ?</p>
              		<div>
              			<label for="isEmployee">Oui</label>
              			<input type="radio" value="true" name="isEmployee" onClick="handleEmployeeZone(this)">
			
              			<label for="isEmployee">Non</label>
              			<input type="radio" value="false" name="isEmployee" onClick="handleEmployeeZone(this)" checked>
              		</div>
            	</div>
            	<div id="employeeForm">
            		<div class="form-item input-group">
                		<input type="text" name="employeeFunction" required>
                		<label for="employeeFunction">Fonction</label>
					</div>
					<div class="form-item input-group">
                		<input type="text" name="employeeService">
                		<label for="service">Service</label>
					</div>
					<div class="form-item input-group">
                		<input type="text" name="deskNumber">
                		<label for="deskNumber">Numéro bureau</label>
                	</div>
                	
                	<div class="form-item profil-item">
                  		<label>Profil : </label>
						<div>
							<div>
                    			<input type="radio" value="ADMIN" name="profile" checked>
								<label for="profile">administrateur</label>
							</div>
							<div>
                    			<input type="radio" value="EQUIPMENT_ADMIN" name="profile">
                    			<label for="profile">responsable équipement</label>
							</div>
							<div>
                    			<input type="radio" value="LOAN_ADMIN" name="profile">
                    			<label for="profile">responsable emprunt</label>
                    		</div>
						</div>
                   </div>
				</div>
       	 		
				<div class="form-buttons">
  					<button type="button" id="button-cancel" class="cancel">Annuler</button>
  					<button type="submit" value="submit" class="validate">Confirmer</button>
  				</div>
  			</form>
  		</section>
  	</main>
     
  </body>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  <script>
  	
  	document.getElementById("button-cancel").addEventListener('click', function() {
  		location.href = "/kiloutou/welcome";
  	});
  
  	document.getElementById("burger").addEventListener('click', function(ev) {
  		document.getElementById("burger-dropdown").classList.toggle('active');
	});
  
    function handleEmployeeZone(radio) {
    	document.getElementById("employeeForm").classList.toggle('active');
      	let inputs = document.getElementById("employeeForm").getElementsByTagName('input');
      	for(let i = 0 ; i < inputs.length ; i++) { // On désactive/réactive les champs cachés/visibles
			if(radio.value === "true") {
      			inputs[i].disabled = false;
      		} else {
      			inputs[i].disabled = true;
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