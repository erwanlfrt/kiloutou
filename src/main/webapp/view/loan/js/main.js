var equipment;


/**
 * Main from add-loan page
 */

function main() {
  let rows = document.getElementsByClassName('row');

  for(let i = 0 ; i <rows.length ; i++) {
    rows[i].addEventListener('click', function(e) {
      let row = e.target;
      if(row !== null && row !== undefined) {
        row.parentNode.style.backgroundColor = "green";
        let rows = document.getElementsByClassName('row');
        for(let j = 0 ; j < rows.length ; j++) {
          rows[j].style.backgroundColor = "";
        }
        row.parentNode.style.backgroundColor = "green";
        document.getElementById("userMail").value = row.parentNode.children[2].innerText;
        document.getElementById('recapUser').innerHTML = `
        <h3>Emprunteur</h3> 
        <p>`+ row.parentNode.children[1].innerText + ` ` + row.parentNode.children[0].innerText + `</p>`;
      }
    });
  }


	/**
	 * Event for the first <select> : Type de materiel
	 */
	let filterSelect = document.getElementById("liste1");
	  filterSelect.addEventListener("change", function() {
		let index = filterSelect.selectedIndex;
		let filterSelected = filterSelect.children[index].value;
    
    loadListEquipment(filterSelected);

	});

  // document.getElementById("liste2").addEventListener('change', ajax());

  document.getElementById("liste2").addEventListener('click', function() {
    loadInfoEquipment();
  });

  document.getElementById("next").addEventListener("click", function() {
    let equipments = document.getElementsByClassName("equipment");
    let equipmentValue = document.getElementById("liste2").value;
    let userValue = document.getElementById("userMail").value;
    let users = document.getElementsByClassName("user");
    let recap = document.getElementsByClassName("recap");

    // which screen is selected ?
    let isEquipment = window.getComputedStyle(equipments[0], null).getPropertyValue("display") === "block";
    let isUser = window.getComputedStyle(users[0], null).getPropertyValue("display") === "block";

    if(isEquipment) {
      if(equipmentValue === "" ) {
        document.getElementById("liste2").style.border = "solid red";
      }
      else {
        document.getElementById("liste2").style.border = "";
        for(let i = 0 ; i < equipments.length ; i++) {
          equipments[i].style.display = "none";
        }
        for(let i =0 ; i < users.length ; i++) {
          users[i].style.display = "block";
        }
        document.getElementById('previous').disabled = false;
        
      }
    }
    else if(isUser) {

      if(userValue === "") {
        document.getElementById("userTable").style.border = "solid red";
      }
      else {
        document.getElementById("userTable").style.border = "";
        document.getElementById('info').style.display = "block";
        for(let i =0 ; i < users.length ; i++) {
          users[i].style.display = "none";
        }
        for(let i = 0 ; i < recap.length ; i++) {
          recap[i].style.display = "block"; 
        }
        document.getElementById("submitInput").hidden = false;
        document.getElementById("submitInput").disabled = false;
        document.getElementById("next").hidden = true;
        document.getElementById("next").disabled = true;

        setDatePickers(true);
      }
      
    }
  });
}

document.getElementById('previous').addEventListener('click', function() {
  let equipments = document.getElementsByClassName("equipment");
  let users = document.getElementsByClassName("user");
  let recap = document.getElementsByClassName("recap");

  // which screen is selected ?
  let isUser = window.getComputedStyle(users[0], null).getPropertyValue("display") === "block";
  let isRecap = window.getComputedStyle(recap[0], null).getPropertyValue("display") === "block";

  if(isUser) {
    for(let i = 0 ; i < equipments.length ; i++) {
      equipments[i].style.display = "block";
    }
    for(let i =0 ; i < users.length ; i++) {
      users[i].style.display = "none";
    }
    document.getElementById('previous').disabled = true;
  }
  else if(isRecap) {
    for(let i =0 ; i < users.length ; i++) {
      users[i].style.display = "block";
    }
    for(let i = 0 ; i < recap.length ; i++) {
      recap[i].style.display = "none"; 
    }
    document.getElementById("submitInput").hidden = true;
    document.getElementById("submitInput").disabled = true;
    document.getElementById("next").hidden = false;
    document.getElementById("next").disabled = false;
  }
});

function searchUsers() {
  let name = document.getElementById('searchBarName').value;
  let firstname = document.getElementById('searchBarFirstname').value;

  let rows = document.getElementsByClassName('row');
  for(let i = 0 ; i < rows.length ; i++) {
    let children = rows[i].children;
    let rowName = children[0].innerText;
    let rowFirstname = children[1].innerText;

    if(!rowName.startsWith(name) || !rowFirstname.startsWith(firstname)) {
      rows[i].style.display = "none";
    }
    else {
      rows[i].style.display = "block";
    }

  }
}

function selectUser(row) {
  if(row !== null && row !== undefined) {
    row.style.backgroundColor = "green";
  }
}

function loadListEquipment(filter) {
  $.ajax({
    //L'URL de la requête 
    url: "/kiloutou/loan/list/" + filter,

    //La méthode d'envoi (type de requête)
    method: "GET",

    //Le format de réponse attendu
    dataType : "json",
  })
  .done(function(data){
      let selectEquipment = document.getElementById('liste2');
      selectEquipment.innerHTML = `<option value="" selected disabled hidden>Matériel</option>`
      for(let i = 0 ; i < data.length ; i++) {
        selectEquipment.innerHTML += `<option value="` + data[i].id + `">` + data[i].name + `</option>`
      }
  })
}


function loadInfoEquipment() {
  $.ajax({
    //L'URL de la requête 
    url: "/kiloutou/loan/get/" + document.getElementById("liste1").value + "?id=" + document.getElementById("liste2").value,

    //La méthode d'envoi (type de requête)
    method: "GET",

    //Le format de réponse attendu
    dataType : "json",
  })
  .done(function(response){
    equipment = response;
    prettyPrintEquipment(response);
    document.getElementById('equipmentId').value = response.id;
  });
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
  }
  else {
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
    }
    else {
      $('#endDate').datepicker('setEndDate',new Date(8640000000000000)); //infinity
    }
    $('#endDate').datepicker('setStartDate', chosenBeginning);
    $('#endDate').datepicker('update', chosenBeginning);


  }
  
}


function prettyPrintEquipment(equipment) {
  let info = document.getElementById('info');
  info.innerHTML = `
	<div>
		<img src="`+ equipment.imageUrl +`" alt="Equipement" width="70px" height="70px" />
		<h2>` + equipment.name +`</h2>
	</div>
  `

  // if is a vehicle
  if(equipment.kilometers !== undefined) {
    info.innerHTML += `
      <p>Marque : ` + equipment.brand + `</p>
      <p>Modèle : ` + equipment.model + `</p>
      <p>Kilometrage : ` + equipment.kilometers + `</p>
      <p>Puissance : ` + equipment.power + `</p>
      <p>Vitesse maximale : ` + equipment.maxSpeed + `</p>
      <p> : Boîte de vitesse ` + equipment.numberOfSpeeds + ` rapports</p>
      <p>Immatriculation : ` + equipment.registrationNumber + `</p>
    `

    // if is a car
    if(equipment.numberOfSeats !== undefined) {
      info.innerHTML += `
      <p>Nombre de places : ` + equipment.numberOfSeats + `</p>
      `
    }
    else if(equipment.numberOfCylinders !== undefined) {
      info.innerHTML += `
      <p>Nombre de cylindres : ` + equipment.numberOfCylinders + ` cylindres</p>
      `
    }
  }
  // else if is a computer
  else if(equipment.isLaptop !== undefined) {
    info.innerHTML += `
    <p>Marque : ` + equipment.brand + `</p>
    <p>Modèle : ` + equipment.model + `</p>
    <p>Stockage : ` + equipment.memorySize + ` Go</p>
    <p>Portable : ` + (equipment.isLaptop ? "oui" : "non") + `</p>
    <h3>Processeur</h3>
    <p>Marque : ` + equipment.processor.brand + `</p>
    <p>Modèle : ` + equipment.processor.name + `</p>
    <p>Nombre de coeurs : ` + equipment.processor.numberOfCores + `</p>
    <p>Fréquence : ` + equipment.processor.frequency + `</p>
    <h3>Carte graphique</h3>
    <p>Marque : ` + equipment.graphicCard.brand + `</p>
    <p>Modèle : ` + equipment.graphicCard.name + `</p>
    <p>Fréquence : ` + equipment.graphicCard.frequency + `</p>
    `
  }


}
