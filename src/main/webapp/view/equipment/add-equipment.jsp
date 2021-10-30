<%@ page language="java" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Add equipment</title>
  </head>
  <body>
    <h1>Add equipment</h1>
    <form style="display : flex ; flex-direction: column; align-items: flex-start" method="POST">
      <label for="name">Nom</label>
    	<input type="text" placeholder="name" name="name" required>

      <label for="available">Disponible</label>
      <div>
        <label for="available">Oui</label>
        <input type="radio" value="available" name="available" required>
        
        <label for="available">Non</label>
    	  <input type="radio" value="notAvailable" name="available" required>
      </div>
      <label for="imageURL">URL de l'image</label>
    	<input type="text" placeholder="imageURL" name="imageURL" required>

      <label>Catégorie</label>
      <select name="categories" id="categories" onchange="loadForm()">
        <option value="other">Autre</option>
        <option value="computer">Ordinateur</option>
        <option value="vehicle">Véhicule</option>
        <option value="computerAccessory">Accessoire informatique</option>
        <option value="vehicleAccessory">Accessoire automobile</option>
      </select>
      <div id="specificForm"></div>
      <input type="submit" value="submit">
    </form>
  </body>

  <script>
    var formLoaded = false;
    function loadForm() {
      let category = document.getElementById("categories").value;
      if(formLoaded) {
        document.getElementById("specificForm").innerHTML = "";
        formLoaded = false;
      }
      if(category === "computer" || category === "vehicle") {
        injectForm(category);
        formLoaded = true;
      } 
    }
    function injectForm(category) {
      fetch("${pageContext.request.contextPath}/view/equipment/forms/" + category + ".jsp").then(data => data.text()).then(html => document.getElementById("specificForm").innerHTML = html);
    }


    var inputAdded = false;
    function addInput() {
      let select = document.getElementById("selectTypeVehicle").value;

      if(inputAdded) {
        document.getElementById("spec").innerHTML = "";
        inputAdded = false;
      }

      if(select === "car") {
        let label = document.createElement("label");
        label.innerText = "Nombre de places";
        let placeInput = document.createElement("input");
        placeInput.type = "number";
        placeInput.name = "numberOfSeats";
        document.getElementById("spec").appendChild(label);
        document.getElementById("spec").appendChild(placeInput);
        inputAdded = true;
      }
      else if(select === "bike") {
        let label = document.createElement("label");
        label.innerText = "Nombre de cylindres"
        let numberOfCylindersInput = document.createElement("input");
        numberOfCylindersInput.type = "number";
        numberOfCylindersInput.name = "numberOfCylinders";
        document.getElementById("spec").appendChild(label);
        document.getElementById("spec").appendChild(numberOfCylindersInput);
        inputAdded = true;
      }
    }

  </script>
</html>