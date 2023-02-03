/**
 * 
 */

function changeProductImage(_src) {
	document.getElementById("imageViewer").src = _src;
}

//function loadChechOutForm() {
//	if (getCookie("username") != "") {
//		$('#checkoutMember').hide();
//		$('#guestCheckoutLabel').text('Check Out');
//		$('#guestEmail').val(getCookie("username"));
//	} else {
//		$('#checkoutMember').show();
//		$('#guestCheckoutLabel').text('Check Out As Guest');
//		$('#guestEmail').val("");
//	}
//}

function redirectLoggedInUser() {
	if (getCookie("username") != "") {
		window.location.href = "index.html"
	}
}

$('#deliveryOptions input').change(function() {
	if ($(this).attr('id') == 'optDeliveryLB') {
		$('#receiverAddress').show();
		$('#deliveryOrPickup').text("Delivery Information");
	} else {
		$('#receiverAddress').hide();
		$('#deliveryOrPickup').text("Pickup Information");
		$('#receiverAddress').val("");
	}
	$('#receiverAddress').attr('required', $(this).attr('id') == 'optDeliveryLB');
	$('#deliveryMethod').attr('value', $(this).val());
});

$('#paymentOptions input').change(function() {
	if ($(this).attr('id') == 'payNowBtn') {
		$('#creditCardField').show();
	} else {
		$('#creditCardField').hide();
		$('#creditCardField').find('input').val("");
	}
	$('#cardHolderName').attr('required', $(this).attr('id') == 'payNowBtn');
	$('#cardNumber').attr('required', $(this).attr('id') == 'payNowBtn');
	$('#expiredDate').attr('required', $(this).attr('id') == 'payNowBtn');
	$('#cvvNumber').attr('required', $(this).attr('id') == 'payNowBtn');
	$('#billingFname').attr('required', $(this).attr('id') == 'payNowBtn');
	$('#billingLname').attr('required', $(this).attr('id') == 'payNowBtn');
	$('#billingPhone').attr('required', $(this).attr('id') == 'payNowBtn');
	$('#billingAddress').attr('required', $(this).attr('id') == 'payNowBtn');
	$('#paymentMethod').attr('value', $(this).val());
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

function searchProduct(e) {
	if (event.key === 'Enter') {
		//requestToServlet('Product', 'searchProduct', 'searchKeywords', $(e).val());	
		window.location.replace("Product?command=search&keywords=" + $(e).val());
	}
}

function searchProductWithFilters() {
	window.location.replace("Product?command=search&keywords=" + $('#searchKeyword').text() + getAllSelectedParams());
}

function getAllSelectedParams() {
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

	if ($('#priceFrom').val() != '')
		params += "&priceMin=" + $('#priceFrom').val();
	if ($('#priceTo').val() != '')
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

function ajaxToServlet(url, type, data, responseFn) {
	//requestToServlet('Cart', 'increase', productID);
	$.ajax({
		url: url,
		type: type,
		data: data,
		success: function(response) {
			console.log(response);
			if (responseFn != null)
				responseFn(response);
		}
	});
}

const formatter = new Intl.NumberFormat('en-Us', {
	style: 'currency',
	currency: 'AUD',
	minimumFractionDigits: 0
})

//delete cart
var count = $('.cartDelete').toArray().length;
$('.cartDelete').click(function() {
	deleteFunction($(this).parentsUntil('.cartItemWrapper'));
	count--;
	if (count == 0) {
		$('#checkOutNow').remove();
	}
})

//remove function
function deleteFunction(selector) {
	selector.remove();
	subTotal();
}

function getPriceValue(price){
	return Number(price.replace(/[^0-9.-]+/g, ""));
}

//subtotal

function subTotal() {
	var subtotal = 0;
	$('.itemPrice').toArray().forEach(function(i) {
		subtotal = Number($(i).text().replace(/[^0-9.-]+/g, "")) + subtotal;
	})
	
	$('.subTotalPrice').text(formatter.format(subtotal));

	if (subtotal == 0) {
		$('#checkOutNow').remove();
	}
}

function formatPriceOnLoad() {
	$('.formattedPrice').toArray().forEach(function(i) {
		var price = $(i).text();
		$(i).text(formatter.format(price));
	})
}

function setCartSize(response) {
	response = (response != 0) ? '(' + response + ')' : '';
	$('#cartSize').text(response);
}

function increase(productID, stock, obj) {
	if(obj !== undefined){
		var quantity = Number($(obj).siblings("span").text());
		quantity++;
		$(obj).siblings("span").text(quantity);
		var cartCol2 = $(obj).closest('.itemTable').find('.cartCol2');
		let totalPrice = Number(cartCol2.find('.unitPrice').text().replace(/[^0-9.-]+/g, "")) * quantity;
		cartCol2.find('.totalPrice').find('.itemPrice').text(formatter.format(totalPrice));
		subTotal();	
		if (stock != undefined && quantity != undefined && quantity == stock)
			$(obj).prop('disabled', true);		
	} 

	ajaxToServlet('Cart', 'POST', { 'command': 'increase', 'productID': productID }, setCartSize);
}

function decrease(productID, stock, obj) {
	var quantity = Number($(obj).siblings("span").text());
	quantity--;
	$(obj).siblings("span").text(quantity);

	var cartCol2 = $(obj).closest('.itemTable').find('.cartCol2');
	let totalPrice = Number(cartCol2.find('.unitPrice').text().replace(/[^0-9.-]+/g, "")) * quantity;
	cartCol2.find('.totalPrice').find('.itemPrice').text(formatter.format(totalPrice));

	if (quantity <= 0) {
		var deleteSelector = $(obj).parentsUntil('.cartItemWrapper');
		deleteFunction(deleteSelector);
	}
	subTotal();

	if (quantity < stock)
		$(obj).siblings("button").prop('disabled', false);

	ajaxToServlet('Cart', 'POST', { 'command': 'decrease', 'productID': productID }, setCartSize);
}

function remove(productID) {
	ajaxToServlet('Cart', 'POST', { 'command': 'remove', 'productID': productID }, setCartSize);
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

$('.addbutton').click(function productOverlayOn(e) {
	var target = e.target;
	$(target).closest('.product_box').find('.productOverlay').css("opacity", "1").show();
	setTimeout(() => {
		$(target).closest('.product_box').find('.productOverlay').hide("slow", "linear");
	}, 300)

})

$('.productOverlay').click(function productOverlayOff() {
	$(this).hide();
})


//end cookies functions

//$('#searchBar').change(function(){
//	alert('test');
//
//});



