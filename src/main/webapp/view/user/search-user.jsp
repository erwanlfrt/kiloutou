<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.user.* , java.util.ArrayList , java.io.IOException "%>
<%
  ArrayList<User> users = (ArrayList<User>)request.getAttribute("users");
%>
<%
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
    <title>Kiloutou</title>
  </head>
  <body>
    <div id="search">
      <input type="text" id="searchBar" onkeyup="searchUsers()" placeholder="Search for users..">
      <ul id="listUsers">
        <%
          for(User user : users) {
            %>
              <li class="userItem"><a href="/Kiloutou/user/info?mail=<%=user.getMail()%>"><%=user.getMail()%></a></li>
            <%
          }
        %>
      </ul>
    </div>
    
  </body>
  <script>
    children = [];
    document.getElementById("listUsers").childNodes.forEach(child => {
      if(child.tagName === "LI") {
        children.push(child.childNodes[0].innerHTML)
      }
    });
    function searchUsers() {
      let list = document.getElementById("listUsers");
      list.innerHTML = "";
      let value = document.getElementById("searchBar").value;
      children.forEach(child => {
        if(child.startsWith(value)) {
          let a = document.createElement('a');
          a.href = "/Kiloutou/user/info?mail=" + child;
          a.innerHTML = child;
          let element = document.createElement('li');
          element.className = "userItem";
          element.appendChild(a);
          list.appendChild(element);
        } 
      });
      if(list.childNodes.length === 0) {
        if( document.getElementById("noResult") === null) {
          let noResult = document.createElement("p");
          noResult.innerText = "Aucun résultat ne correspond à votre recherche";
          noResult.id = "noResult";
          document.getElementById('search').appendChild(noResult);
        }
      } else {
        let noResult = document.getElementById("noResult");
        if(noResult !== null) {
          document.getElementById('search').removeChild(noResult);
        }
      }
    }
  </script>

</html>