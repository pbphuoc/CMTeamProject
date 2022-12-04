/**
 * 
 */
 
 function changeProductImage(_src){
	document.getElementById("imageViewer").src = _src;
}

$('#sorter').change( function(){
//	var selectedSorter = $('#sorter').val();
});

$('#categorySelect').change(function(){
	selectedCategories.innerHTML = '';
	$('#categorySelect :selected').each(function(index){
		if(index != 0)
			selectedCategories.innerHTML += ', ';
		if(index != 0 && index % 3  == 0)
			selectedCategories.innerHTML += '<br/>';
		selectedCategories.innerHTML += this.innerHTML;
	});	
});

$('#brandSelect').change(function(){
	selectedBrands.innerHTML = '';
	$('#brandSelect :selected').each(function(index){
		if(index != 0)
			selectedBrands.innerHTML += ', ';
		if(index != 0 && index % 3  == 0)
			selectedBrands.innerHTML += '<br/>';
		selectedBrands.innerHTML += this.innerHTML;
	});	
});

$('#availabilitySelect').change(function(){
	selectedAvailabilities.innerHTML = '';
	$('#availabilitySelect :selected').each(function(index){
		if(index != 0)
			selectedAvailabilities.innerHTML += ', ';
		if(index != 0 && index % 3  == 0)
			selectedAvailabilities.innerHTML += '<br/>';
		selectedAvailabilities.innerHTML += this.innerHTML;
	});	
});

function loadOptions(opt_arr, select_id){	
	$.each(opt_arr, function(){
		jQuery('<option>',{
			value: Object.values(this)[0],
			html: Object.values(this)[1]
		}).appendTo(select_id);
	});
}

function loadCountryCode(){	
	const country_codes = [
		{country: "vietnam", code: "+84"},
		{country: "us", code: "+1"}
		];
	loadOptions(country_codes, '#countryCodeList');
}

function loadSorter(){	
	const sort_options = [
		{sort_key: "pricelowtohigh", sort_value: "Price Low To High"},
		{sort_key: "pricehightolow", sort_value: "Price High To Low"},
		{sort_key: "nameatoz", sort_value: "Name A To Z"},
		{sort_key: "nameztoa", sort_value: "Name Z To A"},
		{sort_key: "oldtonew", sort_value: "Old To New"},
		{sort_key: "newtoold", sort_value: "New To Old"},
		];
	loadOptions(sort_options, '#sorter');
}

function loadChechOutForm(){
	if (getCookie("username") != ""){
		$('#checkoutMember').hide();
		$('#guestCheckoutLabel').text('Check Out');
		$('#guestEmail').val(getCookie("username"));
	}else{
		$('#checkoutMember').show();
		$('#guestCheckoutLabel').text('Check Out As Guest');
		$('#guestEmail').val("");
	}
}

function redirectLoggedInUser(){
	if (getCookie("username") != ""){
		window.location.href = "index.html"
	}
}

function loadLoggedInUser(){
	if (getCookie("username") != ""){
		$('.menuBarUsername').text("Welcome back " + getCookie("username") + ", ");
		$('.menuBarLoginBtn').text("Logout");
	}	
}

$('#deliveryOptions input').change(function(){
	if($(this).attr('id') == 'optDeliveryLB')
		$('#guestAddress').show();
	else if($(this).attr('id') == 'optCollectLB')
		$('#guestAddress').hide();
});

$('.menuBarLoginBtn').click(() => {
	if (getCookie("username") != ""){
		deleteCookie("username");
		$('.menuBarUsername').text("");
		$('.menuBarLoginBtn').text("Login");				
	}else{
		window.location.href = "login.html";
	}
});

$('#checkoutLoginBtn').click(() => {
	//AUTHENTICATION NEEDED BEFORE THIS POINT
	const loginEmail = $('#checkoutEmailLogin').val();
	setCookie("username", loginEmail, 0.5);
	loadChechOutForm();
});

$('#loginBtn').click(() => {
	//AUTHENTICATION NEEDED BEFORE THIS POINT
	const loginEmail = $('#emailLogin').val();
	setCookie("username", loginEmail, 0.5);
	redirectLoggedInUser();
});

//cookies functions from https://www.w3schools.com/js/js_cookies.asp
function setCookie(cname, cvalue, exdays) {
	const d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	let expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
	let name = cname + "=";
	let ca = document.cookie.split(';');
	for (let i = 0; i < ca.length; i++) {
		let c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

function deleteCookie(cname) {
	document.cookie = cname +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

//end cookies functions

//$('#searchBar').change(function(){
//	alert('test');
//	
//});



