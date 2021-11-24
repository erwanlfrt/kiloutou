/**
 * search-equipment.js
 */

document.getElementById("searchBar").addEventListener("keyup", function(ev) {
	let searchValue = ev.target.value;
	ev.target.classList.toggle("active", ev.target.value);
	searchEquipment(searchValue);
});

document.getElementById("categories").addEventListener("change", function(ev) {
	filterByCategory(ev.target.value);
});

function searchEquipment(searchValue) {
	let equipments = document.getElementsByClassName("list-item");
	for(let i = 0 ; i < equipments.length ; i++) {
		let nameEquipment = equipments[i].getElementsByTagName('a')[0].innerText;
		if(nameEquipment.toLowerCase().includes(searchValue.toLowerCase())) {
			equipments[i].style.display = "flex";
		} else {
			equipments[i].style.display = "none";
		}
	}
}

function filterByCategory(filter) {
	let list = document.getElementsByClassName("list-equipment")[0].children;
	for(let i = 0 ; i < list.length ; i += 2) {
		if(filter.includes("all")) {
			list[i].style.display = "block";
			list[i + 1].style.display = "flex";
		} else {
			console.log(filter.toLowerCase());
			console.log(list[i + 1].id.toLowerCase());
			if(list[i + 1].id.toLowerCase().includes(filter.toLowerCase())) {
				list[i].style.display = "block";
				list[i + 1].style.display = "flex";
			} else {
				list[i].style.display = "none";
				list[i + 1].style.display = "none";
			}
		}
	}
}