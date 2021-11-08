/**
 * Main from add-loan page
 */

function main() {
  $('.datepicker').datepicker();
	
	 $.ajax({
        //L'URL de la requête 
        url: "/kiloutou/loan/all",

        //La méthode d'envoi (type de requête)
        method: "GET",

        //Le format de réponse attendu
        dataType : "json",
    })
    //Ce code sera exécuté en cas de succès - La réponse du serveur est passée à done()
    /*On peut par exemple convertir cette réponse en chaine JSON et insérer
     * cette chaine dans un div id="res"*/
    .done(function(response){
		console.log(response);
        let data = JSON.stringify(response);
        console.log(data);
    })

    //Ce code sera exécuté en cas d'échec - L'erreur est passée à fail()
    //On peut afficher les informations relatives à la requête et à l'erreur
    .fail(function(error){
        alert("La requête s'est terminée en échec. Infos : " + JSON.stringify(error));
    })

    //Ce code sera exécuté que la requête soit un succès ou un échec
    .always(function(){
        alert("Requête effectuée");
    });

  //init dates
  var beginningDate = document.getElementById("beginningDate");
  var endDate = document.getElementById("endDate")
  beginningDate.min = new Date().toLocaleDateString('en-ca')
  beginningDate.value = beginningDate.min;

  //on beginningDate change, set the minimum of endDate
  beginningDate.addEventListener('change', function() {
    endDate.min = beginningDate.value;
    endDate.value = endDate.min;
  });


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
    
		let selectedOptions = document.getElementsByClassName('option-' + filterSelected);
    let options = document.getElementsByClassName('options');
    
    for (let i = 0 ; i < options.length ; i++) {
      options[i].style.display = "none";
    }

    for (let i = 0 ; i < selectedOptions.length ; i++) {
      selectedOptions[i].style.display = "block";
    }

	});

  document.getElementById("liste2").addEventListener('change', function() {
    let content = document.getElementById("liste2").value;
    let name = content.split(' ')[1];
    let imageUrl = content.split(' ')[2];
    document.getElementById('info').innerHTML = `
    <h3>Équipement </h3>
    <p> Nom : ` + name +` </p>
    <img src="`+ imageUrl +`">
    `;

    document.getElementById('equipmentId').value = content.split(' ')[0];
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
        console.log("par ici les petits"),
        document.getElementById("submitInput").hidden = false;
        document.getElementById("submitInput").disabled = false;
        document.getElementById("next").hidden = true;
        document.getElementById("next").disabled = true;
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

  function selectUser(row) {


      if(row !== null && row !== undefined) {
        row.style.backgroundColor = "green";
      }
  }
}
