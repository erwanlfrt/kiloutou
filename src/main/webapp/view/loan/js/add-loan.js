/**
 * add-loan.js
 */

window.onload = function () {
	var step = 1;
	var equipment;
	var user = null;
	
	document.getElementById("next").classList.add("disabled");
	document.getElementById("validate").classList.add("disabled");
	
	/**
	* Event for the first <select> : Type de materiel
	*/
	document.getElementById("liste1").addEventListener("change", function(ev) {
		let index = ev.target.selectedIndex;
		let filterSelected = ev.target.children[index].value;
    	loadListEquipment(filterSelected);
	});

	/**
	* Event for the second <select> : Materiel
	*/
	document.getElementById("liste2").addEventListener('click', function() {
		let idEquipement = document.getElementById("liste2").value;
		let typeMateriel = document.getElementById("liste1").value;
		loadInfoEquipment(idEquipement, typeMateriel);
		if(idEquipement !== "") {
			document.getElementById("next").classList.remove("disabled");
		}
	});
	
	document.getElementById("previous").addEventListener("click", function() {
		changeStep(-1);
	});
	
	document.getElementById("next").addEventListener("click", function() {
		changeStep(1);
	});
	
	document.getElementById("validate").addEventListener("click", function() {
		changeStep(-1);
	});
	
	document.getElementById("beginningDate").addEventListener("click", function() {
		if(document.getElementById("beginningDate").value !== "" && document.getElementById("endDate").value !== "") {
			document.getElementById("validate").classList.remove("disabled");
		} else {
			document.getElementById("validate").classList.add("disabled");
		}
	});
	
	document.getElementById("endDate").addEventListener("click", function() {
		if(document.getElementById("beginningDate").value !== "" && document.getElementById("endDate").value !== "") {
			document.getElementById("validate").classList.remove("disabled");
		} else {
			document.getElementById("validate").classList.add("disabled");
		}
	});
	
	document.getElementById('validate').addEventListener("click", function() {
		console.log(document.getElementById('equipmentId').value);
		console.log(document.getElementById('userMail').value);
		console.log(document.getElementById('beginningDate').value);
		console.log(document.getElementById('endDate').value);
		if(!document.getElementById('validate').classList.contains("disabled") && (document.getElementById('equipmentId').value !== "" && document.getElementById('userMail').value !== "" && document.getElementById('beginningDate').value && document.getElementById('endDate').value)) {
			document.getElementById('form').submit();
		}
	});
	
	let users = document.getElementsByClassName("user-item");
	for(let i = 0 ; i < users.length ; i++) {
		let mail = users[i].children[1].children[0].innerText;
		let userTemp = {
			mail : users[i].children[1].children[0].innerText,
			firstname : users[i].children[2].children[0].innerText.split(" ")[0],
			name : users[i].children[2].children[0].innerText.split(" ")[1],
		}
		users[i].children[1].addEventListener("click", function() {
			changeUser(userTemp)
		});
		users[i].children[2].addEventListener("click", function() {
			changeUser(userTemp)
		});
		users[i].children[3].addEventListener("click", function() {
			changeUser(userTemp)
		});
	}
	
	function loadListEquipment(filter) {
		$.ajax({
    		url: "/kiloutou/loan/list/" + filter,
    		method: "GET",
    		dataType : "json",
  		})
  		.done(function(data) {
  			let selectEquipment = document.getElementById('liste2');
    		selectEquipment.innerHTML = `<option value="" selected disabled hidden>Matériel</option>`
    		for(let i = 0 ; i < data.length ; i++) {
				selectEquipment.innerHTML += `<option value="` + data[i].id + `">` + data[i].name + `</option>`;
			}
		});
	}

	function loadInfoEquipment(id, type) {
		$.ajax({
			url: "/kiloutou/loan/get/" + type + "?id=" + id,
    		method: "GET",
    		dataType : "json",
  		})
  		.done(function(response) {
    		equipment = response;
    		prettyPrintEquipment(response);
    		document.getElementById('equipmentId').value = response.id;
  		});
	}


	function prettyPrintEquipment(equipment) {
		let info = document.getElementById('info' + step);
		info.innerHTML = `
			<div>
				<img src="`+ equipment.imageUrl +`" alt="Equipement" width="70px" height="70px" />
				<h2>` + equipment.name +`</h2>
			</div>
		`;

		// if is a vehicle
		if(equipment.kilometers !== undefined) {
			info.innerHTML += `
      			<p>Marque : ` + equipment.brand + `</p>
      			<p>Modèle : ` + equipment.model + `</p>
      			<p>Kilometrage : ` + equipment.kilometers + `</p>
      			<p>Puissance : ` + equipment.power + `</p>
      			<p>Vitesse maximale : ` + equipment.maxSpeed + `</p>
      			<p>Boîte de vitesse : ` + equipment.numberOfSpeeds + ` rapports</p>
     			<p>Immatriculation : ` + equipment.registrationNumber + `</p>
    		`;

			// if is a car
			if(equipment.numberOfSeats !== undefined) {
				info.innerHTML += `
					<p>Nombre de places : ` + equipment.numberOfSeats + `</p>
				`;
    		} else if(equipment.numberOfCylinders !== undefined) {
				info.innerHTML += `
					<p>Nombre de cylindres : ` + equipment.numberOfCylinders + ` cylindres</p>
				`;
    		} else if(equipment.isLaptop !== undefined) { // else if is a computer
				info.innerHTML += `
					<p>Marque : ` + equipment.brand + `</p>
					<p>Modèle : ` + equipment.model + `</p>
    				<p>Stockage : ` + equipment.memorySize + ` Go</p>
    				<p>Portable : ` + (equipment.isLaptop ? "oui" : "non") + `</p>
    				<h4>Processeur</h4>
    				<p>Marque : ` + equipment.processor.brand + `</p>
    				<p>Modèle : ` + equipment.processor.name + `</p>
    				<p>Nombre de coeurs : ` + equipment.processor.numberOfCores + `</p>
    				<p>Fréquence : ` + equipment.processor.frequency + `</p>
    				<h4>Carte graphique</h4>
    				<p>Marque : ` + equipment.graphicCard.brand + `</p>
    				<p>Modèle : ` + equipment.graphicCard.name + `</p>
    				<p>Fréquence : ` + equipment.graphicCard.frequency + `</p>
    			`;
			}
		}
	}

	function changeStep(value) {
		step += value;
		let buttonPrevious = document.getElementById("previous");
		let buttonNext = document.getElementById("next");
		let buttonValidate = document.getElementById("validate");
		let steps = document.getElementsByClassName("step");
		for (let i = 0 ; i < steps.length ; i++) {
			if(steps[i].id === "step" + step) {
				steps[i].classList.add("active");
			} else {
				steps[i].classList.remove("active");
			}
		}
		if(step === 1) {
			buttonPrevious.classList.remove("active");
			buttonNext.classList.add("active");
			buttonValidate.classList.remove("active");
		} else if(step === 2) {
			buttonPrevious.classList.add("active");
			buttonNext.classList.add("active");
			buttonValidate.classList.remove("active");
			if(document.getElementById('userMail').value !== "") {
				document.getElementById("next").classList.remove("disabled");
			} else {
				document.getElementById("next").classList.add("disabled");
			}
			prettyPrintEquipment(equipment);
			setDatePickers(true);
		} else {
			buttonPrevious.classList.add("active");
			buttonNext.classList.remove("active");
			buttonValidate.classList.add("active");
			prettyPrintEquipment(equipment);
			prettyPrintUser(user);
		}
	}
	
	function changeUser(userTemp) {
		user = userTemp;
		document.getElementById("userMail").value = user.mail;
		let usersItem = document.getElementsByClassName("userItem");
		for(let i = 0 ; i < usersItem.length ; i += 2) {
			if(usersItem[i].children[0].innerText.includes(user.mail)) {
				usersItem[i].classList.add("selected");
				usersItem[i + 1].classList.add("selected");
			} else {
				usersItem[i].classList.remove("selected");
				usersItem[i + 1].classList.remove("selected");
			}
		}
		
		if(user !== null) {
			document.getElementById("next").classList.remove("disabled");
		} else {
			document.getElementById("next").classList.add("disabled");
		}
	}
	
	function prettyPrintUser(user) {
		let info = document.getElementById('info' + step);
		info.innerHTML += `
			<h4 class="user-title">Utilisateur</h4>
			<p>` + user.firstname + ` ` + user.name + `</p>
			<p>` + user.mail + `</p>
		`; 
	}
	
	function setDatePickers(isInit) {
  		let periods = equipment.loanedDates
  		let dates = [];
  		for(let period of periods) {
    		let beginningPeriod = new Date(period[0]);
    		let endPeriod = new Date(period[1]);

    		while(beginningPeriod.getTime() <= endPeriod.getTime()) {
      			let pFormat = beginningPeriod.toLocaleDateString('en-GB');
      			dates.push(pFormat);
      			beginningPeriod.setDate(beginningPeriod.getDate() + 1);
    		}
  		}
  		if(isInit) {
    		$('#beginningDate').datepicker({
      			format: 'dd/mm/yyyy',
      			maxViewMode: 1,
      			datesDisabled: dates,
      			startDate : "today"
    		});
    		$('#beginningDate').datepicker('update', new Date());

    		//on beginningDate change, set the minimum of endDate
    		$('#beginningDate').datepicker().on('changeDate', function() {
      			setDatePickers(false);
    		});
  		} else {
    		var dateString = $('#beginningDate').val();
    		var dateParts = dateString.split("/");
    		var validString = dateParts[2] + '-' + dateParts[1] + '-' + dateParts[0];
    		// var dateObject = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
    
    		//check for max date, infinity if no loan to come or the beginning date of the next loan to come.
    		let beginningDateOfLoan = undefined;
    		let nextLoan = undefined
    		let now = new Date();
    		let chosenBeginning = new Date(validString);

    		for(let period of periods) {
      			beginningDateOfLoan = new Date(period[0]);
      			if (beginningDateOfLoan.getTime() > chosenBeginning && (nextLoan === undefined || beginningDateOfLoan.getTime() < nextLoan.getTime())) {
       				nextLoan = beginningDateOfLoan;
      			}
    		}

    		$('#endDate').datepicker({
      			format: 'dd/mm/yyyy',
      			maxViewMode: 1,
      			datesDisabled: dates,
      			startDate: chosenBeginning,
    		}); 
    		if(nextLoan !== undefined) {
      			$('#endDate').datepicker('setEndDate', nextLoan);
    		} else {
      			$('#endDate').datepicker('setEndDate',new Date(8640000000000000)); //infinity
    		}
    		$('#endDate').datepicker('setStartDate', chosenBeginning);
    		$('#endDate').datepicker('update', chosenBeginning);
  		}
	}
};