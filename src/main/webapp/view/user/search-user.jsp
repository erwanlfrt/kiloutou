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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/user/css/search-user.css" />
  </head>
  <body>
    <div id="search">
      <h3 id="searchLabel" >Rechercher par adresse mail : </h3>
      <label class="switch" >
          <input type="checkbox" id="searchType">
          <span onclick="sliderClick()" class="slider round" ></span>
      </label>
      <input type="text" id="searchBar" onkeyup="searchUsers()" placeholder="Search for users..">
      <ul id="listUsers">
        <%
          for(User user : users) {
            if(user.isReal()) {
              %>
                <li class="userItem"><a href="/Kiloutou/user/info?mail=<%=user.getMail()%>"><%=user.getMail()%></a></li>
              <%
            }
          }
        %>
      </ul>
    </div>
    
  </body>
  <script>

    var userEmails = [];
    var userNames = [];
    var userFirstnames = [];
    <%
      for(User user : users) {
        if(user.isReal()) {
          %>
            userEmails.push('<%= user.getMail()%>');
            userNames.push('<%= user.getName()%>');
            userFirstnames.push('<%= user.getFirstname()%>');
          <%
        }
      }
    %>

   


    var children = [];
    
    function searchUsers() {
      
      if(children.length === 0) {
        document.getElementById("listUsers").childNodes.forEach(child => {
        if(child.tagName === "LI") {
          children.push(child.childNodes[0].innerHTML)
        }
       });
      }
      

      let list = document.getElementById("listUsers");
      list.innerHTML = "";
      let value = document.getElementById("searchBar").value;
      children.forEach(child => {
        //name and firstname ?
        let name = '';
        let firstname = '';
        if(child.indexOf(' ') > 0) {
          name = child.substring(child.indexOf(' ') +1);
          firstname = child.substring(0, child.indexOf(' '));
        }

        if(child.startsWith(value) || name.startsWith(value) || firstname.startsWith(value)) {
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

    function sliderClick() {
      let searchType = document.getElementById('searchType');
      let listUsers = document.getElementById('listUsers');
      let label = document.getElementById('searchLabel');
      listUsers.innerHTML = '';

      if (searchType.checked) {
        // search by mail
        label.innerText = 'Rechercher par adresse mail : ';
        for(let i = 0 ; i < userEmails.length ; i++ ) {
          listUsers.innerHTML += '<li class="userItem"><a href="/Kiloutou/user/info?mail=' + userEmails[i] +'">' + userEmails[i] + '</a></li>'
        }
      }
      else {
        // search by name and fistname
        label.innerText = 'Rechercher par nom / prénom : ';
        for(let i = 0 ; i < userEmails.length ; i++ ) {
          listUsers.innerHTML += '<li class="userItem"><a href="/Kiloutou/user/info?mail=' + userEmails[i] +'">' + userFirstnames[i] + ' ' + userNames[i] +'</a></li>';
        }
      }
      children = [];

    }
  </script>

</html>