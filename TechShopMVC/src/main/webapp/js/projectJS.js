/**
 * 
 */

function changeProductImage(_src) {
	document.getElementById("imageViewer").src = _src;
}

$('#sorter').change(function() {
	//	var selectedSorter = $('#sorter').val();
});

//$('#categorySelect').change(function() {
//	selectedCategories.innerHTML = '';
//	$('#categorySelect :selected').each(function(index) {
//		if (index != 0)
//			selectedCategories.innerHTML += ', ';
//		if (index != 0 && index % 3 == 0)
//			selectedCategories.innerHTML += '<br/>';
//		selectedCategories.innerHTML += this.innerHTML.substring(0,this.innerHTML.length-3);		
//	});
//});
//
//$('#brandSelect').change(function() {
//	selectedBrands.innerHTML = '';
//	$('#brandSelect :selected').each(function(index) {
//		if (index != 0)
//			selectedBrands.innerHTML += ', ';
//		if (index != 0 && index % 3 == 0)
//			selectedBrands.innerHTML += '<br/>';
//		selectedBrands.innerHTML += this.innerHTML.substring(0,this.innerHTML.length-3);
//	});
//});
//
//$('#availabilitySelect').change(function() {
//	selectedAvailabilities.innerHTML = '';
//	$('#availabilitySelect :selected').each(function(index) {
//		if (index != 0)
//			selectedAvailabilities.innerHTML += ', ';
//		if (index != 0 && index % 3 == 0)
//			selectedAvailabilities.innerHTML += '<br/>';
//		selectedAvailabilities.innerHTML += this.innerHTML.substring(0,this.innerHTML.length-3);		
//	});
//});

function loadOptions(opt_arr, select_id) {
	$.each(opt_arr, function() {
		jQuery('<option>', {
			value: Object.values(this)[0],
			html: Object.values(this)[1]
		}).appendTo(select_id);
	});
}

function loadCountryCode() {
	const country_codes = [
		{ country: "vietnam", code: "+84" },
		{ country: "us", code: "+1" }
	];
	loadOptions(country_codes, '#countryCodeList');
}

//function loadSorter() {
//	const sort_options = [
//		{ sort_key: "pricelowtohigh", sort_value: "Price Low To High" },
//		{ sort_key: "pricehightolow", sort_value: "Price High To Low" },
//		{ sort_key: "nameatoz", sort_value: "Name A To Z" },
//		{ sort_key: "nameztoa", sort_value: "Name Z To A" },
//		{ sort_key: "oldtonew", sort_value: "Old To New" },
//		{ sort_key: "newtoold", sort_value: "New To Old" },
//	];
//	loadOptions(sort_options, '#sorter');
//}

function loadChechOutForm() {
	if (getCookie("username") != "") {
		$('#checkoutMember').hide();
		$('#guestCheckoutLabel').text('Check Out');
		$('#guestEmail').val(getCookie("username"));
	} else {
		$('#checkoutMember').show();
		$('#guestCheckoutLabel').text('Check Out As Guest');
		$('#guestEmail').val("");
	}
}

function redirectLoggedInUser() {
	if (getCookie("username") != "") {
		window.location.href = "index.html"
	}
}

function loadLoggedInUser() {
	if (getCookie("username") != "") {
		$('.menuBarUsername').text("Welcome back " + getCookie("username") + ", ");
		$('.menuBarLoginBtn').text("Logout");
	}
}

$('#deliveryOptions input').change(function() {
	if ($(this).attr('id') == 'optDeliveryLB'){
		$('#checkOutAddress').show();	
	}else if ($(this).attr('id') == 'optCollectLB'){
		$('#checkOutAddress').hide();
		$('#checkOutAddress').val("");
		
	}
		
});

$('#paymentOptions input').change(function() {
	if ($(this).attr('id') == 'payNowBtn')
		$('#creditCardField').show();
	else if ($(this).attr('id') == 'payOnPickupBtn')
		$('#creditCardField').hide();
		$('#creditCardField').find('input').val("");
});

//$('.menuBarLoginBtn').click(() => {
//	if (getCookie("username") != ""){
//		deleteCookie("username");
//		$('.menuBarUsername').text("");
//		$('.menuBarLoginBtn').text("Login");				
//	}else{
//		window.location.href = "login.html";
//	}
//});
//
//$('#checkoutLoginBtn').click(() => {
//	//AUTHENTICATION NEEDED BEFORE THIS POINT
//	const loginEmail = $('#checkoutEmailLogin').val();
//	setCookie("username", loginEmail, 0.5);
//	loadChechOutForm();
//});
//
//$('#loginBtn').click(() => {
//	//AUTHENTICATION NEEDED BEFORE THIS POINT
//	const loginEmail = $('#emailLogin').val();
//	setCookie("username", loginEmail, 0.5);
//	redirectLoggedInUser();
//});

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
	document.cookie = cname + '=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

//const products = [
//	{
//		code: "prd1",
//		name: "Dell 7040 SFF Bundle Desktop i7-6700 3.4GHz 16GB RAM 512GB NVMe SSD + 22 Monitor - Refurbished Grade A",
//		desc: "Dell 7040 SFF Bundle Desktop i7-6700 3.4GHz 16GB RAM 512GB NVMe SSD + 22 Monitor - Refurbished Grade A",
//		oldPrice: "2850.00",
//		newPrice: "2599.00",
//		category: "laptop",
//		brand: "dell",
//		quantity: "0",
//		src: pageContextPath + "/images/product/product_computerdell1.jpg"
//	},{
//		code: "prd2",
//		name: "Apple MacBook Pro 13 M2 chip 512GB Space Grey 2022 MNEJ3X/A",
//		desc: "The 13-inch MacBook Pro laptop is a portable powerhouse. Get more done faster with a next-generation 8-core CPU, 10-core GPU and 8GB of unified memory. Go all day and into the night, thanks to the power-efficient performance of the Apple M2 chip*Thanks to its active cooling system, the 13-inch MacBook Pro can sustain pro levels of performance, so you can run CPU- and GPU-intensive tasks for hours on end.",
//		oldPrice: "0",
//		newPrice: "3200.00",
//		category: "laptop",
//		brand: "apple",
//		quantity: "0",
//		src: pageContextPath + "/images/product/product_laptopapple1.jpg"		
//	},{
//		code: "prd3",
//		name: "iPhone 14 Pro Max 128GB",
//		desc: "iPhone 14 Pro Max 128GB",
//		oldPrice: "0",
//		newPrice: "1850.00",
//		category: "cellphone",
//		brand: "apple",
//		quantity: "0",
//		src: pageContextPath + "/images/product/product_iPhone14promax.jpg"		
//	},
//	{
//		code: "prd4",
//		name: "APPLE IPAD AIR (5 GEN) 10.9-INCH WI-FI+CELL 256GB STARLIGHT",
//		desc: "Apple iPad Air (5th Generation) 10.9-inch 256GB Wi-Fi + Cellular Starlight iPad Air. With an immersive 10.9-inch Liquid Retina display1. The breakthrough Apple M1 chip delivers faster performance, making iPad Air a creative and mobile gaming powerhouse. Featuring Touch ID, advanced cameras, blazing-fast 5G2 and Wi-Fi 6, USB-C, and support for Magic Keyboard and Apple Pencil (2nd generation)3.",
//		oldPrice: "1000.00",
//		newPrice: "985.50",
//		category: "tablet",
//		brand: "apple",
//		quantity: "0",
//		src: pageContextPath + "/images/product/product_iPadairgen51.jpg"		
//	},	{
//		code: "prd5",
//		name: "Apple Watch Series 7 (GPS + Cellular) 45mm Blue Aluminium Case with Abyss Blue Sport Band",
//		desc: "Originally released October 2021. S7 with 64-bit dual-core processor. Water resistant to 50 metres¹Always-On Retina LTPO OLED display (1,000 nits brightness). 802.11b/g/n 2.4GHz and 5GHz. Bluetooth 5.0. Built-in rechargeable lithium-ion battery (Up to 18 hours²). Third-generation optical heart sensor. Accelerometer: up to 32 g-forces with fall detection. Gyroscope. Ambient light sensor. Capacity 32GB. Ceramic and sapphire crystal back",
//		oldPrice: "750.00",
//		newPrice: "639.20",
//		category: "smartwatch",
//		brand: "apple",
//		quantity: "0",
//		src: pageContextPath + "/images/product/product_appleWatch71.jpg"		
//	},{
//		code: "prd6",
//		name: "iPhone 11 Pro Max Silicone",
//		desc: "Designed by Apple to complement iPhone 11 Pro Max, the form of the silicone case fits snugly over the volume buttons, side button and curves of your device without adding bulk. A soft microfibre lining on the inside helps protect your iPhone. On the outside, the silky, soft-touch finish of the silicone exterior feels great in your hand. And you can keep it on all the time, even when you’re charging wirelessly. Like every Apple-designed case, it undergoes thousands of hours of testing throughout the design and manufacturing process. So not only does it look great, it’s built to protect your iPhone from scratches and drops.",
//		oldPrice: "0",
//		newPrice: "65.00",
//		category: "accessory",
//		brand: "apple",
//		quantity: "0",
//		src: pageContextPath + "/images/product/product_iPhonecase1.jpg"		
//	}
//];

//function loadProductsToLS(){
//	localStorage.setItem('products', JSON.stringify(products));
//}

function loadProducts() {
	$.each(products, function() {
		var oneProductColumn = $("<div class='col-md-4'></div>").appendTo('#productRowDiv');
		var productBoxDiv = $("<div class='product_box'></div>").appendTo(oneProductColumn);
		productBoxDiv.attr("value", Object.values(this)[0]);
		var figure = $("<figure></figure>").appendTo(productBoxDiv);
		jQuery('<img>', {
			class: "productThumbnail",
			src: Object.values(this)[8]
		}).appendTo(figure);
		jQuery('<p>', {
			class: "productDescription",
			html: Object.values(this)[1]
		}).appendTo(productBoxDiv);
		jQuery('<h3>', {
			class: "oldPrice",
			html: (Object.values(this)[3] - Object.values(this)[4] > 0) ? "$" + formatNumberWithCommas(Object.values(this)[3]) : ""
		}).appendTo(productBoxDiv);
		jQuery('<h3>', {
			class: "newPrice",
			html: "$" + formatNumberWithCommas(Object.values(this)[4])
		}).appendTo(productBoxDiv);
		var buttonRow = $("<div class='row'></div>").appendTo(productBoxDiv);
		var viewBtnDiv = $("<div class='col-md-6'></div>").appendTo(buttonRow);
		jQuery('<button>', {
			class: "productButton viewProductBtn",
			html: "View",
		}).appendTo(viewBtnDiv);
		var addBtnDiv = $("<div class='col-md-6'></div>").appendTo(buttonRow);
		jQuery('<button>', {
			class: "productButton addToCartBtn",
			html: "Add To Cart",
		}).appendTo(addBtnDiv);
	});
}

//$('#productRowDiv').on('click', 'button.viewProductBtn', function(){
//	const selectedProductCode = $(this).closest('.product_box').attr('value');
//	var product = products.find(x => x.code === selectedProductCode);
//	localStorage.setItem('selectedProduct', JSON.stringify(product));
//});
//
//$('#productRowDiv').on('click', 'button.addToCartBtn', function(){
//	const selectedProductCode = $(this).closest('.product_box').attr('value');
//	var product = products.find(x => x.code === selectedProductCode);
//	product.quantity += 1;
//	var carts = [];
//	if(localStorage.getItem("carts") !== null)
//		carts = JSON.parse(localStorage.getItem('carts'));
//	carts.push(product);
//	alert(carts.length);
//	localStorage.setItem('carts', JSON.stringify(carts));
//});

//function addToCart(productID){
//		
//	const selectedProductID = productID;
//	var carts = [];
//	if(localStorage.getItem("carts") !== null)
//		carts = JSON.parse(localStorage.getItem('carts')).data;	
//    var product;
//    product = carts.find(x => x.id === selectedProductID);
//    if(product === undefined){
//		product = {id: selectedProductID, quantity: 1};
//		carts.push(product)
//	}
//	else{
//		product.quantity += 1;
//	}
//	localStorage.setItem('carts', JSON.stringify({lastUpdate: new Date().getTime(),data: carts}));
//}

function formatNumberWithCommas(x) {
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//function login(){		
//	 var form = jQuery('<form>',{
//			action: 'Auth',
//			method: 'Get'
//		}).append(jQuery('<input>',{
//			type: 'hidden',
//			name: 'command',
//			value: 'getLoginForm'
//		}));
//	form.hide().appendTo("body").submit();	
//}

//function logout(username){		
//	alert(username);
//	var form= jQuery('<form>',{
//			action: 'Auth',
//			method: 'Post'
//		});
//	jQuery('<input>',{
//		type: 'hidden',
//		name: 'command',
//		value: 'logout'
//	}).appendTo(form);
//	jQuery('<input>',{
//		type: 'hidden',
//		name: 'emailLogin',
//		value: username
//	}).appendTo(form);	
//	form.hide().appendTo("body").submit();
//}

function requestToServlet(servlet, method, command, paramName, paramValue) {
	var form =
		jQuery('<form>', {
			action: servlet,
			method: method
		}).append(jQuery('<input>', {
			type: 'hidden',
			name: 'command',
			value: command
		}));
	if (command != '') {
		jQuery('<input>', {
			type: 'hidden',
			name: paramName,
			value: paramValue
		}).appendTo(form);
	}
	form.hide().appendTo("body").submit();
}

function searchProduct(e){
	if (event.key === 'Enter') {
		//requestToServlet('Product', 'searchProduct', 'searchKeywords', $(e).val());	
		window.location.replace("Product?command=search&keywords="+$(e).val());	
    }
}

function searchProductWithFilters(){
	window.location.replace("Product?command=search&keywords=" + $('#searchKeyword').text() + getAllSelectedParams());	    
}

function getAllSelectedParams(){
	var params = "";
	
	$('#brandSelect option:selected').map(function() {
		params += "&brand=" + this.value;
	});
	
	$('#categorySelect option:selected').map(function() {
		params += "&category=" + this.value;
	});
	
	$('#availabilitySelect option:selected').map(function() {
		params += "&availability=" + this.value;
	});	
	
	if($('#priceFrom').val() != '')
		params += "&priceMin=" + $('#priceFrom').val();
	if($('#priceTo').val() != '')
		params += "&priceMax=" + $('#priceTo').val();	
	
	params += "&sortBy=" + $('#sorter option:selected').val();	
	params += "&perPage=" + $('#maxPerPageButtonGroup .selected').val();
	params += "&page=" + $('#pageButtonGroup .selected').val();

	return params;	
}

$('.queryFilter').change(function() {
	searchProductWithFilters();
});

function viewProductDetail(productID) {
	requestToServlet('Product', 'GET', 'viewProductDetail', 'productID', productID);
}

//function viewCart() {
//	requestToServlet('Cart', 'GET', 'viewCart', 'productID', '');
//}

function ajaxToServlet(url, type, data) {
	//requestToServlet('Cart', 'increase', productID);
	$.ajax({
		url: url,
		type: type,
		data: data,
		success: function(response){
			console.log(response);
		}
	});
}

function increase(productID) {
	ajaxToServlet('Cart', 'POST', {'command' : 'increase', 'productID' : productID});
}

function decrease(productID) {
	ajaxToServlet('Cart', 'POST', {'command' : 'decrease', 'productID' : productID});
}

function remove(productID) {
	ajaxToServlet('Cart', 'POST', {'command' : 'remove', 'productID' : productID});
}

function setErrorMessage(element, feedback, message) {
	$(feedback).html(message);
	if (message == '') {
		element.addClass('is-valid');
		element.removeClass('is-invalid');
	} else {
		element.addClass('is-invalid');
		element.removeClass('is-valid');
	}
}

function isEmailFormatValid(email) {
	var emailPattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	return new RegExp(emailPattern).test(email);
}

function isMobileFormatValid(mobile) {
	var ausMobilePattern = /^(?:\+?(61))? ?(?:\((?=.*\)))?(0?[2-57-8])\)? ?(\d\d(?:[- ](?=\d{3})|(?!\d\d[- ]?\d[- ]))\d\d[- ]?\d[- ]?\d{3})$/;
	return new RegExp(ausMobilePattern).test(mobile);
}

$('.addbutton').click(function productOverlayOn(e){
	var target = e.target;
	$(target).closest('.product_box').find('.productOverlay').css("opacity", "1").show();
	setTimeout( ()=>{
		$(target).closest('.product_box').find('.productOverlay').hide("slow","linear");
	},300)
	
})

$('.productOverlay').click(function productOverlayOff(){
	$(this).hide();
})


//end cookies functions

//$('#searchBar').change(function(){
//	alert('test');
//
//});



