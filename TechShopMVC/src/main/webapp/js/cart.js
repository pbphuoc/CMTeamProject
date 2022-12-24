//format currency
const formatter = new Intl.NumberFormat('en-Us',{
	style: 'currency',
	currency: 'USD',
	minimumFractionDigits: 0
})
//increment button


$('.increment').click(function(){
	var quantity = Number($(this).siblings("span").text());
		quantity++;
	$(this).siblings("span").text(quantity);
		var cartCol2 = $(this).closest('.itemTable').find('.cartCol2');
		let totalPrice = Number(cartCol2.find('.unitPrice').text().replace(/[^0-9.-]+/g,""))*quantity;
		cartCol2.find('.totalPrice').find('.itemPrice').text(formatter.format(totalPrice)); 
		subTotal();
})

//decrement button

$('.decrement').click(function(){
	var quantitySpan = Number($(this).siblings("span").text());
		quantitySpan--;
		$(this).siblings("span").text(quantitySpan);
	
	var cartCol2 = $(this).closest('.itemTable').find('.cartCol2');
	let totalPrice = Number(cartCol2.find('.unitPrice').text().replace(/[^0-9.-]+/g,""))*quantitySpan;
		cartCol2.find('.totalPrice').find('.itemPrice').text(formatter.format(totalPrice)); 
	if(quantitySpan <= 0){
		var deleteSelector = $(this).parentsUntil('.cartItemWrapper');
		deleteFunction(deleteSelector);
		$('.checkOutNow').remove();
		}
	subTotal();	
	
})


//delete cart
var count = $('.cartDelete').toArray().length;
$('.cartDelete').click(function(){
	deleteFunction($(this).parentsUntil('.cartItemWrapper'));
	count--;
	if(count == 0){
		$('.checkOutNow').remove();
	}
})

//remove function
function deleteFunction(selector){
	selector.remove();
	subTotal();
}

//subtotal

function subTotal(){
	var subtotal = 0;
	$('.itemPrice').toArray().forEach( function(i){
		subtotal = Number($(i).text().replace(/[^0-9.-]+/g,"")) + subtotal;
	})
	
	$('.subTotalPrice').text(function(){
		$(this).text(formatter.format(subtotal));
	});
}

//checkout


//document.onload
$(document).ready(function(){
	
	$('.unitPrice').toArray().forEach( function(i){
		var price = $(i).text();
		$(i).text(formatter.format(price));
	})
	var subtotal = 0;
	
	$('.itemPrice').toArray().forEach( function(i){
		subtotal = Number($(i).text()) + subtotal;
		var price = $(i).text();
		$(i).text(formatter.format(price));
	})

	$('.subTotalPrice').text(function(){
		$(this).text(formatter.format(subtotal));
	});
	if(subtotal == 0){
		$('.checkOutNow').remove();
	}
	
	}
)
















