<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.user.User , javax.servlet.* , java.io.IOException, model.object.equipment.*, java.util.ArrayList, java.util.HashMap, java.util.Map"%>
<%
User user = (User) request.getSession().getAttribute("user");
String login = "";

if(user == null) {
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
	 login = user.getLogin();
}

%>
<%
  HashMap<String, ArrayList<Equipment>> lists = (HashMap<String, ArrayList<Equipment>>) request.getAttribute("lists");
%>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <div id="search">
      <h1>Search equipment</h1>
      <select name="categories" id="categories" onchange="searchEquipments()">
        <option value="all">Tout afficher</option>
        <option value="Véhicules">Véhicules</option>
        <option value="Voitures">Voitures</option>
        <option value="Motos">Motos</option>
        <option value="Accessoires pour véhicule">Accessoires pour véhicule</option>
        <option value="Accessoires informatiques">Accessoires informatiques</option>
        <option value="Ordinateurs">Ordinateurs</option>
      </select>
      <input type="text" id="searchBar" onkeyup="searchEquipments()" placeholder="Search for users..">
      <div id="searchResults">

        <%

        for(Map.Entry<String, ArrayList<Equipment>> entry : lists.entrySet()) {
          String key = entry.getKey();
          ArrayList<Equipment> list = entry.getValue();
          
          %>
          <div id="category_<%=key%>" class="category">
            <h2><%=key%></h2>
            <ul class="results">
              <%
                  for(Equipment e : list) {
                    if(e.canBeLoaned()) {
              %>
                      <li><a href="/kiloutou/equipment/info?id=<%=e.getId()%>"><%= e.getName()%></a></li>
                    <%
                    }
                  }
              %>
              </ul>
            </div>
          <%
        }

        %>
      </div>
    </div>
  </body>
  <script>
    let saveInitialResults = document.getElementById("searchResults").innerHTML
    children = [];
    let results = document.getElementsByClassName("results");
    for(let i=0 ; i < results.length ; i++) {
      results[i].childNodes.forEach(child => {
        if(child.tagName === "LI") {
          children.push(child.childNodes[0].innerHTML)
        }
      });
    }

    function searchEquipments() {
      let categoryfilter = document.getElementById("categories").value;
      match = [];
      let list = document.getElementById("searchResults");
      list.innerHTML = "";
      let value = document.getElementById("searchBar").value;
      list.innerHTML = saveInitialResults;

      let categories = document.getElementsByClassName("category");
      for(let i = 0 ; i < categories.length ; i++) {
        if(categories[i].id !== ("category_" + categoryfilter) && categoryfilter !== "all") {
          categories[i].hidden = true;
        }
        let ul = categories[i].childNodes[3];
        for(let j = 0 ; j < ul.childNodes.length ; j++) {
          if(ul.childNodes[j].tagName === "LI" ) {
            if(ul.childNodes[j].childNodes[0].innerHTML.toLowerCase().startsWith(value.toLowerCase())) {
              match.push(ul.childNodes[j].childNodes[0].innerhTML);
            }
            else {
              ul.childNodes[j].hidden = true;
            }
          }
        }
      }
      

      if(match.length === 0) {
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