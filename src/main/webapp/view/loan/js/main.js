/**
 * Main from add-loan page
 */

function main() {
	console.log("Main is loaded !");
	/**
	 * Event for the first <select> : Type de materiel
	 */
	let filterSelect = document.getElementById("liste1");
	filterSelect.addEventListener("change", function() {
		let index = filterSelect.selectedIndex;
		let filterSelected = filterSelect.children[index].value;
		let materielSelect = document.getElementById("liste2").children;
		for(let i = 0 ; i < materielSelect.length ; i++) {
			if(materielSelect[i].text.search(new RegExp("\\b" + filterSelected  + "\\b")) >= 0) {
				materielSelect[i].hidden = false;
			} else {
				materielSelect[i].hidden = true;
			}
		}
	});
	
	/**
	 * Event for the second <select> : Materiel
	 */
	let materielSelect = document.getElementById("liste2");
	materielSelect.addEventListener("change", function() {
		let index = materielSelect.selectedIndex;
		let materielSelected = materielSelect.children[index].value;
		
		$.get("/kiloutou/loan/add", {
			idEquipment: materielSelected
		}, function(responseText) {
			console.log(responseText);
		});
	});
}
