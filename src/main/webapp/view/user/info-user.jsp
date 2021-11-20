<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.user.* , java.io.IOException, java.util.ArrayList, model.object.loan.Loan"%>
<%
  User user = (User)request.getAttribute("user");
  Employee employee = (Employee)request.getAttribute("employee");
  ArrayList<Loan> loans = (ArrayList<Loan>) request.getAttribute("loans");
%>
<%
User logged = (User) request.getSession().getAttribute("user");
String login = "";
/*
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
} */

%>
<html>
  <head>
    <meta charset="utf-8">
    <title>Info user</title>
  </head>
  <body>
    <div id="info">
      <p><b>Adresse mail : </b><%= user.getMail()%></p>
      <p><b>Nom : </b> <%= user.getName()%></p>
      <p><b>Prénom : </b> <%= user.getFirstname()%></p>
      <p><b>Login : </b> <%= user.getLogin()%></p>
      <p><b>Adresse : </b> <%= user.getAddress()%></p>
      <p><b>n° tel. : </b> <%= user.getPhoneNumber()%></p>

      <%
        if(employee != null) {
          %>
            <p><b>Fonction : </b><%= employee.getFunction()%></p>
            <p><b>Service : </b><%= employee.getService()%></p>
            <p><b>n° bureau : </b><%= employee.getDeskNumber()%></p>
            <p><b>Profil : </b><%= employee.getProfil().name()%></p>
          <%
        }
      %>
    </div>

    <div>
      <a href="modify?mail=<%= user.getMail()%>">
        <button>Modifier</button>
     </a>
     <button onClick="deleteUser()">Supprimer</button>
   </a>
    </div>

    <% if(loans.size() > 0) {
      %>
      <div>
        <table>   
          <thead>
            <tr>
              <th colspan = "3">Liste des emprunts</th>
            </tr>
          </thead>

          <tbody>
            <%
            for(Loan loan : loans) {
              if(loan.isBorrowed() && !loan.hasNotStarted()) {
                %>
                <tr style="background-color : <%= loan.isLate() ? "red" : "green" %>">
                  <td><%= loan.getEquipment().getName()%></td>
                  <td><input type="checkbox" name="checkbox" class="checkbox" value="<%= loan.getId()%>"></td>
                </tr>
                <%
              }
            }
            %>
          </tbody>
        </table>
        <button onClick="returnEquipments()" >restituer</button>
      </div>
      <%
    }
    %>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script>
      function deleteUser() {
        
        let data = {
          mail : '<%= user.getMail()%>'
        }
        $.ajax({
          url: "/Kiloutou/user/delete",
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

      function returnEquipments() {
        let checkboxes = document.getElementsByClassName('checkbox');

        for(let i = 0 ; i < checkboxes.length ; i++) {
          if(checkboxes[i].checked) {
            let data = {
              id : checkboxes[i].value
            };
            $.ajax({
              url: "/Kiloutou/user/info",
              method: "POST",
              contentType: "application/json",
              data: JSON.stringify(data),
            });
          }
        }
        location.reload();
      }
    </script>
</html>