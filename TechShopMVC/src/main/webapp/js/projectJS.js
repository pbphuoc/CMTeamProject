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
		$('#checkOutAddress').attr('required',true);
	}else {
		$('#checkOutAddress').hide();
		$('#checkOutAddress').val("");
		$('#checkOutAddress').attr('required',false);
		
	}
	//if ($(this).attr('id') == 'optCollectLB')
		
});

$('#paymentOptions input').change(function() {
	if ($(this).attr('id') == 'payNowBtn'){
		$('#paymentForm').show();
		$('#cardHolderName').attr('required',true);
		$('#cardNumber').attr('required',true);
		$('#expiredDate').attr('required',true);
		$('#cvvNumber').attr('required',true);
		$('#billingFname').attr('required',true);
		$('#billingLname').attr('required',true);
		$('#billingAddress').attr('required',true);
	}	else {
		$('#paymentForm').hide();
		$('#paymentForm').find('input').val("");
		$('#cardHolderName').attr('required',false);
		$('#cardNumber').attr('required',false);
		$('#expiredDate').attr('required',false);
		$('#cvvNumber').attr('required',false);
		
		
		
	}
		//if ($(this).attr('id') == 'payOnPickupBtn')
		
		
});

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

function formatNumberWithCommas(x) {
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

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
	if ($('#pageButtonGroup .selected').val() !== undefined)
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



