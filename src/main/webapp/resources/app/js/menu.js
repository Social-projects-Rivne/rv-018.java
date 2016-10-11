(function() {
	var body = $('body'), menuButton = body.find('#toggle-button'), menuList = body
			.find('.not-active');
	menuButton.on('click', function(e) {
		menuList.toggleClass('active-ul');
	});
})();


// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
	if (!event.target.matches('.login-form')) {

		var dropdowns = document.getElementsByClassName(".login-form");
		var i;
		for (i = 0; i < dropdowns.length; i++) {
			var openDropdown = dropdowns[i];
			if (openDropdown.classList.contains('show')) {
				openDropdown.classList.remove('show');
			}
		}
	}
}