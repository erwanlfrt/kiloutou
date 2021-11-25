<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import=" java.io.IOException, java.util.ArrayList, model.object.equipment.*, model.object.user.* , javax.servlet.*  "%>
<%
  Equipment equipment = (Equipment) request.getAttribute("equipment");
  
  boolean isAModification = (equipment != null);
  
  Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");
  Car car = (Car) request.getAttribute("car");
  Bike bike = (Bike) request.getAttribute("bike");
  ComputerAccessory computerAccessory = (ComputerAccessory) request.getAttribute("computerAccessory");
  VehicleAccessory vehicleAccessory = (VehicleAccessory) request.getAttribute("vehicleAccessory");
  Computer computer = (Computer) request.getAttribute("computer");
  
  boolean isAvailable = (equipment == null) || equipment.isAvailable();
  boolean canBeLoaned = (equipment == null) || equipment.canBeLoaned();

  ArrayList<Processor> processors = new ArrayList<Processor>();
  ArrayList<GraphicCard> graphicCards = new ArrayList<GraphicCard>();

  if(!isAModification || computer != null) {
    processors = (ArrayList<Processor>) request.getAttribute("processors");
    graphicCards = (ArrayList<GraphicCard>) request.getAttribute("graphicCards");
  }
  
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
    <title>Add equipment</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/equipment/css/add-equipment.css" />
  </head>
  <body>
  
  	<%@include file="../layout/header.jsp" %>
  	
	<main>
	  	<section>
		  	<h1>AJOUT DE MATERIEL</h1>
		    <form id="addForm" method="POST" name="addForm">
		      <p><i>Les champs marqués par * sont obligatoires.</i></p>
		      <div class="form-item input-group">
		    	<input type="text" value="${equipment == null ? '' : equipment.getName() }" name="name" required>
		      	<label for="name">Nom</label>
			  </div>
			  
			  <div class="form-item dispo-item" >
			      <p>Disponible</p>
			      <div>
			        <label for="available">Oui</label>
			        <input type="radio" value="available" name="available" required <%= isAvailable ? "checked" : "" %>>
			        
			        <label for="available">Non</label>
			    	<input type="radio" value="notAvailable" name="available" required <%= !isAvailable ? "checked" : "" %>>
			      </div>
			  </div>
			  
			  <div class="form-item input-group">
    		      <input type="text" value="${equipment == null ? '' : equipment.getImageUrl()}" name="imageURL" required>
			      <label for="imageURL">URL de l'image</label>
			  </div>
			  <div class="search-type">
			      <select name="categories" id="categories" onchange="loadForm()" <%= equipment != null ? "disabled" : ""%>>
			        <option value="other" selected="selected" <%= (computer == null && vehicle == null && computerAccessory == null && vehicleAccessory == null) ? "selected" : "" %> >Catégorie</option>
			        <option value="computer"<%= computer != null ? "selected" : "" %>>Ordinateur</option>
			        <option value="vehicle" <%= vehicle != null ? "selected" : "" %>>Véhicule</option>
			        <option value="computerAccessory" <%= computerAccessory != null ? "selected" : "" %>>Accessoire informatique</option>
			        <option value="vehicleAccessory" <%= vehicleAccessory != null ? "selected" : "" %>>Accessoire automobile</option>
			      </select>
			  </div>
			  
		      <div class="form" id="specificForm"></div>
		      <% if(isAModification) {
		        %>
		        <input type="text"  name="canBeLoaned" value="<%= canBeLoaned%>" id="sliderButton">
		        <label for="canBeLoaned" >Actif : </label>
		        <label class="switch" >
		          <input type="checkbox" <%= canBeLoaned && isAModification ? "checked" : "" %>>
		          <span onclick="sliderClick()" class="slider round" ></span>
		        </label>
		        <%
		      }
		      %>
		     
		      
		      <div class="form-buttons">
				<button type="button" id="button-cancel" class="cancel">Annuler</button>
			  	<button type="submit" value="submit" class="validate">Confirmer</button>
			  </div>
		    </form>
		  	
	  	</section>
	 </main>
   </body>

  <script>
  
	document.getElementById("button-cancel").addEventListener('click', function() {
  		location.href = "/kiloutou/welcome";
  	});

    // load processorsS
    var processors = [];
    var processorsIds = [];
    var graphicCards = [];
    var graphicCardsIds = [];
    <%
    for (Processor p : processors) {
      %>
      processors.push("<%= p.getBrand()%> <%= p.getName()%>");
      processorsIds.push(<%= p.getId()%>);
      <%
    }

    for (GraphicCard gc : graphicCards) {
      %>
      graphicCards.push("<%= gc.getBrand()%> <%= gc.getName()%>");
      graphicCardsIds.push(<%= gc.getId()%>);
      <%
    }
    %>


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
      fetch("${pageContext.request.contextPath}/view/equipment/forms/" + category + ".jsp").then(data => data.text()).then((html) => {
        document.getElementById("specificForm").innerHTML = html;
        if(category === "computer") {
          initComputerForm();
        }
      });
      
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
        document.getElementById("spec").appendChild(placeInput);
        document.getElementById("spec").appendChild(label);
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
        document.getElementById("spec").appendChild(numberOfCylindersInput);
        document.getElementById("spec").appendChild(label);
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
      let processorSelect = document.getElementById("processorSelect");
      processorSelect.innerHTML = "";
      for(let i = 0 ; i < processors.length ; i++) {
        processorSelect.innerHTML += '<option value="' + processorsIds[i] + '">' + processors[i] +'</option>';
      }
      <%
      if (computer != null) {
        %>
        document.getElementById("processorSelect").value = '<%= computer.getProcessor().getId()%>';

        <%
      }
      %>
      
      // document.getElementById("processorBrand").value = '<%= computer != null ? computer.getProcessor().getBrand(): ""%>';
      // document.getElementById("processorName").value = '<%= computer != null ? computer.getProcessor().getName() : ""%>';
      // document.getElementById("numberOfCores").value = '<%= computer != null ? computer.getProcessor().getNumberOfCores() : ""%>';
      // document.getElementById("processorFrequency").value = '<%= computer != null ? computer.getProcessor().getFrequency() : ""%>';

      

      //intit graphic card
      let graphicCardSelect = document.getElementById("graphicCardSelect");
      graphicCardSelect.innerHTML = "";
      for(let j = 0 ; j < graphicCards.length ; j++) {
        graphicCardSelect.innerHTML += '<option value="' + graphicCardsIds[j] + '" >' + graphicCards[j] + '</option>';
      }
      <%
      if (computer != null) {
        %>
        document.getElementById("graphicCardSelect").value = '<%= computer.getGraphicCard().getId()%>';

        <%
      }
      %>

      // document.getElementById("graphicCardBrand").value = '<%= computer != null ? computer.getGraphicCard().getBrand() : ""%>';
      // document.getElementById("graphicCardName").value = '<%= computer != null ? computer.getGraphicCard().getName() : ""%>';
      // document.getElementById("graphicCardFrequency").value = '<%= computer != null ? computer.getGraphicCard().getFrequency() : ""%>';

  
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

    function sliderClick() {
      let canBeLoaned = document.getElementById("sliderButton");
      canBeLoaned.value === "true" ? canBeLoaned.value = "false" : canBeLoaned.value ="true";
    }

    function processorClick() {
      let processorChoice = document.getElementById("processorChoice");
      let processorForm = document.getElementById("processorForm");
      let processorSelect = document.getElementById("processorDivSelect");
      let processorLabel = document.getElementById("processorLabel");
      if(!processorChoice.checked) {
        processorLabel.innerText = "Nouveau processeur";
        processorForm.style.display = "block";
        processorSelect.style.display = "none";
        
      }
      else {
        processorLabel.innerText = "Choisir un processeur déjà existant";
        processorForm.style.display = "none";
        processorSelect.style.display = "flex";
      }
    }

    function graphicCardClick() {
      let graphicCardChoice = document.getElementById("graphicCardChoice");
      let graphicCardForm = document.getElementById("graphicCardForm");
      let graphicCardSelect = document.getElementById("graphicCardDivSelect");
      let graphicCardLabel = document.getElementById("graphicCardLabel");
      if(!graphicCardChoice.checked) {
        graphicCardLabel.innerText = "Nouvelle carte graphique";
        graphicCardForm.style.display = "block";
        graphicCardSelect.style.display = "none";
        
      }
      else {
        graphicCardLabel.innerText = "Choisir une carte graphique déjà existante";
        graphicCardForm.style.display = "none";
        graphicCardSelect.style.display = "flex";
      }
    }

  </script>
</html>