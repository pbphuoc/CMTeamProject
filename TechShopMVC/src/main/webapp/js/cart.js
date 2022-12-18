//format currency

const formatter = new Intl.NumberFormat('en-Us',{
	style: 'currency',
	currency: 'USD',
	minimumFractionDigits: 0
})
//increment button

/*
$('.increment').click(function(){
	var quantity = Number($(this).siblings("span").text());
		var cartCol2 = $(this).closest('.itemTable').find('.cartCol2');
		let totalPrice = Number(cartCol2.find('.unitPrice').text().replace(/[^0-9.-]+/g,""))*quantity;
		alert(totalPrice)
		cartCol2.find('.totalPrice').find('.itemPrice').text(formatter.format(totalPrice)); 
		subTotal();
})
*/

/*
//total price function
function updateTotalPrice (num){
	
	let totalPrice = Number($(selector).parentsUntil('.itemTable').find('.unitPrice').text().replace(/[^0-9.-]+/g,"")) * num;
	
	$('.itemPrice').text(formatter.format(totalPrice)); 
	
}
*/
/*
var storageArr = JSON.parse(localStorage.getItem('carts')).data;
*/
//console.log(increButton);
/*
increButton.forEach(function(button, index) {
	button.addEventListener("click", function(event) {
		let btn = event.target;
		let quantityNumClass = btn.parentElement.parentElement.querySelector("span");
		let quantityNumText = quantityNumClass.innerText;
		quantityNumClass.innerHTML = Number(quantityNumText) + 1;
		totalPrice(Number(quantityNumClass.innerHTML),btn);
		subTotal();
	})

})
*/
//decrement button
/*
$('.decrement').click(function(){
	var quantitySpan = Number($(this).siblings("span").text());
		quantitySpan --
		$(this).siblings("span").text(quantitySpan);
	var storageArr = JSON.parse(localStorage.getItem('carts')).data;
	var decrementArray = storageArr;
		var position = decrementArray.findIndex(i => i.id == Number($(this).children().text()))
		decrementArray[position].quantity = quantitySpan;
		updateLocalStorage(decrementArray);
	if(quantitySpan <= 0){
		var deleteSelector = $(this).parentsUntil('.cartItemWrapper').find('.cartDelete');
		deleteFunction(deleteSelector);
		
	}
	totalPrice(quantitySpan,this)
	subTotal();	
	
})
*/


/*
decreButton.forEach(function(button, index) {
	button.addEventListener("click", function(event) {
		let btn = event.target;
		let quantityNum = btn.parentElement.parentElement.querySelector("span");
		let quantityNumText = quantityNum.innerText;
		if(Number(quantityNumText) > 0){
			quantityNum.innerText = Number(quantityNumText) - 1;
			totalPrice(Number(quantityNum.innerText),btn);
			subTotal();
		}else{
			return;
		}	
})
})
*/





//delete cart

//remove function

//data array from localStorage
	
/*
//function updateLocalStorage
function updateLocalStorage(array){
	localStorage.setItem('carts',JSON.stringify({lastUpdate : new Date().getTime(),data : array}));
}
*/

//subtotal function
/*
function subTotal(){
	var sum = 0;
	var totalArray = $('.itemPrice').toArray();
	
	for(let i = 0; i < totalArray.lenght; i++ ){
		sum += 
	}
	
	console.log(sum)
	$('.subTotalPrice').text(formatter.format(sum));

*/
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
	/*
	for(let i = 0 ; i < storageArr.length; i++){
		$('.'+storageArr[i].id.toString()).text(storageArr[i].quantity.toString());
	}
	*/
	$('.subTotalPrice').text(function(){
		$(this).text(formatter.format(subtotal));
	});

	}
)
















