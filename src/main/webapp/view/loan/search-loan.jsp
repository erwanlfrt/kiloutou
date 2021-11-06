<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.loan.Loan , model.object.user.* , model.object.equipment.* , java.util.ArrayList"%>
<%
  ArrayList<Loan> outdatedLoans = (ArrayList<Loan>) request.getAttribute("outdatedLoans");
  ArrayList<Loan> currentLoans = (ArrayList<Loan>) request.getAttribute("currentLoans");
  ArrayList<Loan> oldLoans = (ArrayList<Loan>) request.getAttribute("oldLoans");
  ArrayList<Loan> loansToCome = (ArrayList<Loan>) request.getAttribute("loansToCome");
%>
<html>
  <head>
    <title>Search Loan</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/loan/css/search-loan.css" />
  </head>
  <body>
    <h1>Search loan</h1>

    <div id="outdatedLoans">
      <h2>Emprunts en retard</h2>
      <table>
        <thead>
          <tr>
            <th colspan = "3">Emprunts en retard</th>
          </tr>
        </thead>

        <tbody>
          <%
          for(Loan loan : outdatedLoans) {
            %>
            <tr>
              <td><%= loan.getUser().getMail()%></td>
              <td><%= loan.getEquipment().getName()%></td>
              <td><a href="/Kiloutou/loan/info?id=<%=loan.getId()%>">Modifier</a></td>
            </tr>
            <%
            }
          %>
        </tbody>
      </table>
    </div>

    <div>
      <h2>Emprunts actuel</h2>

      <table>
        <thead>
          <tr>
            <th colspan = "3">Emprunts actuels</th>
          </tr>
        </thead>

        <tbody>
          <%
          for(Loan loan : currentLoans) {
            %>
            <tr>
              <td><%= loan.getUser().getMail()%></td>
              <td><%= loan.getEquipment().getName()%></td>
              <td><a href="/Kiloutou/loan/info?id=<%=loan.getId()%>">Modifier</a></td>
            </tr>
            <%
            }
          %>
        </tbody>
      </table>
    </div>

    <div>
      <h2>Emprunts à venir</h2>

      <table>
        <thead>
          <tr>
            <th colspan = "3">Emprunts à venir</th>
          </tr>
        </thead>

        <tbody>
          <%
          for(Loan loan : loansToCome) {
            %>
            <tr>
              <td><%= loan.getUser().getMail()%></td>
              <td><%= loan.getEquipment().getName()%></td>
              <td><a href="/Kiloutou/loan/info?id=<%=loan.getId()%>">Modifier</a></td>
            </tr>
            <%
            }
          %>
        </tbody>
      </table>
    </div>

    <div>
      <h2>Emprunts terminés</h2>

      <table>
        <thead>
          <tr>
            <th colspan = "3">Emprunts terminés</th>
          </tr>
        </thead>

        <tbody>
          <%
          for(Loan loan : oldLoans) {
            %>
            <tr>
              <td><%= loan.getUser().getMail()%></td>
              <td><%= loan.getEquipment().getName()%></td>
              <td><a href="/Kiloutou/loan/info?id=<%=loan.getId()%>">Modifier</a></td>
            </tr>
            <%
            }
          %>
        </tbody>
      </table>
    </div>

  </body>
</html>