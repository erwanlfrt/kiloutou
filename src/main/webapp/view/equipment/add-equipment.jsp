<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.equipment.* , java.io.IOException"%>
<%
  Equipment equipment = (Equipment) request.getAttribute("equipment");
  Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");
  Car car = (Car) request.getAttribute("car");
  Bike bike = (Bike) request.getAttribute("bike");
  ComputerAccessory computerAccessory = (ComputerAccessory) request.getAttribute("computerAccessory");
  VehicleAccessory vehicleAccessory = (VehicleAccessory) request.getAttribute("vehicleAccessory");
  Computer computer = (Computer) request.getAttribute("computer");
  boolean isAvailable = (equipment == null) || equipment.isAvailable();

%>
<html>
  <head>
    <title>Add equipment</title>
  </head>
  <body>
    <h1>Add equipment</h1>
    <form style="display : flex ; flex-direction: column; align-items: flex-start" method="POST">
      <label for="name">Nom</label>
    	<input type="text" value="${equipment == null ? '' : equipment.getName() }" name="name" required>

      <label for="available">Disponible</label>
      <div>
        <label for="available">Oui</label>
        <input type="radio" value="available" name="available" required <%= isAvailable ? "checked" : "" %>>
        
        <label for="available">Non</label>
    	  <input type="radio" value="notAvailable" name="available" required <%= !isAvailable ? "checked" : "" %>>
      </div>
      <label for="imageURL">URL de l'image</label>
    	<input type="text" value="${equipment == null ? '' : equipment.getImageUrl()}" name="imageURL" required>

      <label>Catégorie</label>
      <select name="categories" id="categories" onchange="loadForm()" <%= equipment != null ? "disabled" : ""%>>
        <option value="other" <%= (computer == null && vehicle == null && computerAccessory == null && vehicleAccessory == null) ? "selected" : "" %> >Autre</option>
        <option value="computer"<%= computer != null ? "selected" : "" %>>Ordinateur</option>
        <option value="vehicle" <%= vehicle != null ? "selected" : "" %>>Véhicule</option>
        <option value="computerAccessory" <%= computerAccessory != null ? "selected" : "" %>>Accessoire informatique</option>
        <option value="vehicleAccessory" <%= vehicleAccessory != null ? "selected" : "" %>>Accessoire automobile</option>
      </select>
      <div id="specificForm"></div>
      <input type="submit" value="submit">
    </form>
  </body>

  <script>

    var isCar = ('<%= car != null%>') === 'true';
    var isBike = ('<%= bike != null%>') === 'true';
    var isComputer = ('<%= computer != null%>') === 'true';
    var isVehicle = ('<%= vehicle != null%>') === 'true';

    if(isVehicle || isComputer) {
      loadForm();
      
    }

    checkElement('additionalForm').then((element) => {
        if(isVehicle) {
          initVehicleForm();
        }
        else if(isComputer) {
          initComputerForm();
        }
    });

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
        if(isCar) {
          placeInput.value = '<%= car != null ? car.getNumberOfSeats() : "" %>';
        }
        document.getElementById("spec").appendChild(label);
        document.getElementById("spec").appendChild(placeInput);
        inputAdded = true;
      }
      else if(select === "bike") {
        let label = document.createElement("label");
        label.innerText = "Nombre de cylindres";
        let numberOfCylindersInput = document.createElement("input");
        numberOfCylindersInput.type = "number";
        numberOfCylindersInput.name = "numberOfCylinders";
        if(isBike) {
          numberOfCylindersInput.value = '<%= bike != null ? bike.getNumberOfCylinders() : "" %>';
        }
        document.getElementById("spec").appendChild(label);
        document.getElementById("spec").appendChild(numberOfCylindersInput);
        inputAdded = true;
      }
    }

    function initVehicleForm() {
      document.getElementById("kilometers").value = '<%= vehicle != null ? vehicle.getKilometers() : "" %>';
      document.getElementById("renewalKilometers").value = '<%= vehicle != null ? vehicle.getRenewalKilometers() : "" %>';
      document.getElementById("vehicleBrand").value = '<%= vehicle != null ? vehicle.getBrand() : "" %>';
      document.getElementById("vehicleModel").value = '<%= vehicle != null ? vehicle.getModel() : "" %>';
      document.getElementById("registrationNumber").value = '<%= vehicle != null ? vehicle.getRegistrationNumber() : "" %>';
      document.getElementById('<%= vehicle != null ? vehicle.getState() : "GOOD"%>').checked = "true";
      document.getElementById("maxSpeed").value = '<%= vehicle != null ? vehicle.getMaxSpeed() : "" %>';
      document.getElementById("numberOfSpeeds").value = '<%= vehicle != null ? vehicle.getNumberOfSpeeds() : "" %>';
      document.getElementById("power").value = '<%= vehicle != null ? vehicle.getPower() : "" %>';

      let typeVehicle = document.getElementById("selectTypeVehicle");
      if(isCar) {
        typeVehicle.value = "car";
        typeVehicle.disabled = "true";
      }
      else if(isBike) {
       typeVehicle.value = "bike";
       typeVehicle.disabled = "true";
      }
      else {
       typeVehicle.value = "other";
       typeVehicle.disabled = "true";
      }
      addInput();

    }

    function initComputerForm() {
      document.getElementById('computerBrand').value = '<%= computer != null ? computer.getBrand() : ""%>';
      document.getElementById('computerModel').value = '<%= computer != null ? computer.getModel() : ""%>';
      document.getElementById('serialNumber').value = '<%= computer != null ? computer.getSerialNumber() : ""%>';
      document.getElementById('memorySize').value = '<%= computer != null ? computer.getMemorySize() : ""%>';
      document.getElementById('<%= computer != null && computer.isLaptop() ? "isLaptop" : "notLaptop"%>').checked= 'true';
      document.getElementById('screenSize').value = '<%= computer != null ? computer.getScreenSize() : ""%>';
      document.getElementById('date').value = '<%= computer != null ? computer.getPurchaseDate() : ""%>';
      document.getElementById('renewalDate').value = '<%= computer != null ? computer.getRenewalDate() : ""%>';

      //init processor form
      document.getElementById("processorBrand").value = '<%= computer != null ? computer.getProcessor().getBrand(): ""%>';
      document.getElementById("processorName").value = '<%= computer != null ? computer.getProcessor().getName() : ""%>';
      document.getElementById("numberOfCores").value = '<%= computer != null ? computer.getProcessor().getNumberOfCores() : ""%>';
      document.getElementById("processorFrequency").value = '<%= computer != null ? computer.getProcessor().getFrequency() : ""%>';

      //intit graphic card
      document.getElementById("graphicCardBrand").value = '<%= computer != null ? computer.getGraphicCard().getBrand() : ""%>';
      document.getElementById("graphicCardName").value = '<%= computer != null ? computer.getGraphicCard().getName() : ""%>';
      document.getElementById("graphicCardFrequency").value = '<%= computer != null ? computer.getGraphicCard().getFrequency() : ""%>';
    }


    async function checkElement(id) {
      var querySelector = null;
      while (querySelector === null) {
          await rafAsync();
          querySelector = document.getElementById(id);
      }
      return querySelector;
    }

    function rafAsync() {
        return new Promise(resolve => {
            requestAnimationFrame(resolve); //faster than set time out
        });
    }

  </script>
</html>