<%@ page language="java"%>
<%@page import="model.object.user.* , java.io.IOException"%>
<%
  Employee employee = (Employee)request.getAttribute("employee");
%>
<%
User user = (User) request.getAttribute("user");
User logged = (User) request.getSession().getAttribute("user");
String login = "";

if(logged == null) {
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
}

%>
<html>
  <head>
    <meta charset="utf-8">
    <title>Add user</title>
  </head>
  <body>
    <div>
      <form id="addForm" method="POST" name="addForm" style="display : flex ; flex-direction: column; align-items: flex-start">
        <label for="name"><b>Nom : </b></label>
        <input type="text" value="<%= user.getName()%>" name="name" required>

        <label for="firstname"><b>Prénom : </b></label>
        <input type="text" value="<%= user.getFirstname() %>" name="firstname" required>
        
        <label for="login"><b>Login : </b></label>
        <input type="text" value="<%= user.getLogin() %>" name="login" required>

        <label for="password"><b>Mot de passe: </b></label>
        <input type="password" value="" name="password" required>

        <label for="email"><b>Addresse mail : </b></label>
        <%
          if(user.getMail().equals("")) {
            %>
            <input type="text" value="<%= user.getMail()%>" name="email" required>
            <%
          }
          else {
            %>
            <input type="text" value="<%= user.getMail()%>" name="email" readonly>
            <%
          }
        %>
        <label for="address"><b>Adresse : </b></label>
        <input type="text" value="<%= user.getAddress()%>" name="address" required>

        <label for="phoneNumber"><b>Numéro de téléphone: </b></label>
        <input type="text" value="<%= user.getPhoneNumber()%>" name="phoneNumber" required>
        <%
          if (employee == null) {
            %>
            <div id="employeeZone">
              <p>S'agit-il d'un employé ? ?</p>
              <label for="isEmployee"><b>Oui</b></label>
              <input type="radio" value="true" name="isEmployee" onClick="handleEmployeeZone(this)">

              <label for="isEmployee"><b>Non</b></label>
              <input type="radio" value="false" name="isEmployee" onClick="handleEmployeeZone(this)" checked>
            </div>
            <%
          }
          else {
            %>
            <div id="employeeForm" style="display : flex ; flex-direction: column; align-items: flex-start">
              <div style="display : flex ; flex-direction: column; align-items: flex-start">
                <label for="employeeFunction"><b>Fonction : </b></label>
                <input type="text" value="<%= employee.getFunction() %>" name="employeeFunction">

                <label for="service"><b>Service : </b></label>
                <input type="text" value="<%= employee.getService() %>" name="employeeService">

                <label for="deskNumber"><b>Numéro bureau : </b></label>
                <input type="text" value="<%= employee.getDeskNumber() %>" name="deskNumber">
                <div>
                  <label><b>Profil : </b></label>

                    <label for="profile"><b>administrateur</b></label>
                    <input type="radio" value="ADMIN" name="profile">

                    <label for="profile"><b>responsable équipement</b></label>
                    <input type="radio" value="EQUIPMENT_ADMIN" name="profile">

                    <label for="profile"><b>responsable emprunt</b></label>
                    <input type="radio" value="LOAN_ADMIN" name="profile">
                  
                </div>
              </div>
            </div>
            <%
          }
        %>
        <input type="submit" value="submit">
      </form>
    </div>
  </body>
  <script>

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
  </script>
</html>