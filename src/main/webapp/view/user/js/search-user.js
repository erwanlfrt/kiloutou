/**
 * search-user js
 */

document.getElementById('searchBar').addEventListener("keyup", function(ev) {
	ev.target.classList.toggle("active", ev.target.value);
	searchUsers(ev.target);
});

document.getElementById('sliderClick').addEventListener("click", function() {
	sliderClick();
});

function searchUsers(input) {

	let userHTML = document.getElementsByClassName('user-item');
	let nb = 0;
	
	for(let i = 0 ; i < userHTML.length ; i++) {
		
		let trouve = false;
		let user = userHTML[i].getElementsByClassName('userItem');
		
		for(let j = 0 ; j < user.length ; j++) {
			if(user[j].getElementsByTagName("a")[0].innerText.toLowerCase().includes(input.value.toLowerCase())) {
				trouve = true;
			}
		}
		if(trouve) {
			nb++;
			userHTML[i].style.display = "flex";
			if(nb % 2 === 0) {
				userHTML[i].style.backgroundColor = "rgba(255, 180, 180, 0.24)";
			} else {
				userHTML[i].style.backgroundColor = "white";
			}
		} else {
			userHTML[i].style.display = "none";
		}
	}
}

function sliderClick() {
	let userHTML = document.getElementsByClassName('user-item');
	for(let i = 0 ; i < userHTML.length ; i++) {
		let user = userHTML[i].getElementsByClassName('userItem');
		for(let j = 0 ; j < user.length ; j++) {
			user[j].classList.toggle("active");
		}
	}
}

function deleteUser(mail) {
	$.ajax({
		url: "/kiloutou/user/delete",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
			mail: mail
		})
    }).done(function() {
        let url = window.location.href;
        url = url.substring(0, url.indexOf('info'));
        url += 'search'
        window.location.href = url;
    });
}