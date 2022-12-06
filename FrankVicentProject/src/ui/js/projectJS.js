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

const products = [
	{
		code: "prd1",
		name: "Dell 7040 SFF Bundle Desktop i7-6700 3.4GHz 16GB RAM 512GB NVMe SSD + 22 Monitor - Refurbished Grade A",
		desc: "Dell 7040 SFF Bundle Desktop i7-6700 3.4GHz 16GB RAM 512GB NVMe SSD + 22 Monitor - Refurbished Grade A",
		oldPrice: "2850.00",
		newPrice: "2599.00",
		category: "laptop",
		brand: "dell",
		quantity: "0",
		src: "../images/product/product_computerdell1.jpg"
	},{
		code: "prd2",
		name: "Apple MacBook Pro 13 M2 chip 512GB Space Grey 2022 MNEJ3X/A",
		desc: "Apple MacBook Pro 13 M2 chip 512GB Space Grey 2022 MNEJ3X/A",
		oldPrice: "3200.00",
		newPrice: "3200.00",
		category: "laptop",
		brand: "apple",
		quantity: "0",
		src: "../images/product/product_laptopapple1.jpg"		
	},{
		code: "prd3",
		name: "iPhone 14 Pro Max 128GB",
		desc: "iPhone 14 Pro Max 128GB",
		oldPrice: "1850.00",
		newPrice: "1850.00",
		category: "cellphone",
		brand: "apple",
		quantity: "0",
		src: "../images/product/product_iPhone14promax.jpg"		
	},
	{
		code: "prd4",
		name: "iPad Air (5th gen) 256GB",
		desc: "iPad Air (5th gen) 256GB",
		oldPrice: "1000.00",
		newPrice: "985.50",
		category: "tablet",
		brand: "apple",
		quantity: "0",
		src: "../images/product/product_iPadairgen5.png"		
	},	{
		code: "prd5",
		name: "Apple Watch Series 7 (GPS + Cellular) 45mm Blue Aluminium Case with Abyss Blue Sport Band",
		desc: "Apple Watch Series 7 (GPS + Cellular) 45mm Blue Aluminium Case with Abyss Blue Sport Band",
		oldPrice: "750.00",
		newPrice: "639.20",
		category: "smartwatch",
		brand: "apple",
		quantity: "0",
		src: "../images/product/product_appleWatch7.jpg"		
	},{
		code: "prd6",
		name: "iPhone 11 Pro Max Silicone",
		desc: "iPhone 11 Pro Max Silicone",
		oldPrice: "65.00",
		newPrice: "65.00",
		category: "accessory",
		brand: "apple",
		quantity: "0",
		src: "../images/product/product_iPhonecase.jpg"		
	}
];

function loadProductsToLS(){
	localStorage.setItem('products', JSON.stringify(products));
}

function loadProducts(){	
	$.each(products, function(){
		var oneProductColumn = $("<div class='col-md-4'></div>").appendTo('#productRowDiv');	
		var productBoxDiv = $("<div class='product_box'></div>").appendTo(oneProductColumn);
		productBoxDiv.attr("value", Object.values(this)[0]);
		var figure = $("<figure></figure>").appendTo(productBoxDiv);		
		jQuery('<img>',{
			class: "productThumbnail",
			src: Object.values(this)[8]
		}).appendTo(figure);
		jQuery('<p>',{
			class: "productDescription",
			html: Object.values(this)[1]
		}).appendTo(productBoxDiv);	
		jQuery('<h3>',{
			class: "oldPrice",
			html: (Object.values(this)[3] - Object.values(this)[4] > 0) ? "$" + formatNumberWithCommas(Object.values(this)[3]) : ""
		}).appendTo(productBoxDiv);
		jQuery('<h3>',{
			class: "newPrice",
			html: "$" + formatNumberWithCommas(Object.values(this)[4])
		}).appendTo(productBoxDiv);		
		var buttonRow = $("<div class='row'></div>").appendTo(productBoxDiv);
		var viewBtnDiv = $("<div class='col-md-6'></div>").appendTo(buttonRow);
		jQuery('<button>',{
			class: "productButton",
			html: "View",
		}).appendTo(viewBtnDiv);			
		var addBtnDiv = $("<div class='col-md-6 addToCartBtn'></div>").appendTo(buttonRow);
		jQuery('<button>',{
			class: "productButton addToCartBtn",
			html: "Add To Cart",
		}).appendTo(addBtnDiv);			
	});
}

$('#productRowDiv').on('click', 'button.addToCartBtn', function(){
	const selectedProductCode = $(this).closest('.product_box').attr('value');
	var product = products.find(x => x.code === selectedProductCode);
	product.quantity += 1;
	var carts = [];
	if(localStorage.getItem("carts") !== null)
		carts = JSON.parse(localStorage.getItem('carts'));
	carts.push(product);
	alert(carts.length);
	localStorage.setItem('carts', JSON.stringify(carts));
});

function loadCurrentCart(){
	var currentCarts = JSON.parse(localStorage.getItem('carts'));
}

function formatNumberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
//end cookies functions

//$('#searchBar').change(function(){
//	alert('test');
//	
//});



