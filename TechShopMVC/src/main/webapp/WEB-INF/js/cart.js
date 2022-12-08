//format currency

const formatter = new Intl.NumberFormat('en-Us',{
	style: 'currency',
	currency: 'USD',
	minimumFractionDigits: 0
})
//increment button
const increButton = document.querySelectorAll(".increment");

console.log(increButton);
increButton.forEach(function(button, index) {
	button.addEventListener("click", function(event) {
		let btn = event.target;
		let quantityNumClass = btn.parentElement.parentElement.querySelector("span");
		let quantityNumText = quantityNumClass.innerText;
		quantityNumClass.innerHTML = Number(quantityNumText) + 1;
		totalPrice(Number(quantityNumClass.innerHTML),btn);
		subTotal(btn);
	})

})
//decrement button
const decreButton = document.querySelectorAll(".decrement");
console.log(increButton);
decreButton.forEach(function(button, index) {
	button.addEventListener("click", function(event) {
		let btn = event.target;
		let quantityNum = btn.parentElement.parentElement.querySelector("span");
		let quantityNumText = quantityNum.innerText;
		if(Number(quantityNumText) > 0){
			quantityNum.innerText = Number(quantityNumText) - 1;
			totalPrice(Number(quantityNum.innerText),btn);
			subTotal(btn);
		}else{
			return;
		}	
})
})
//total price function
function totalPrice (num, button){
	let unitPrice = button.parentElement.parentElement.parentElement.parentElement.querySelector(".unitPrice").innerText;
	let total = Number(unitPrice.replace(/[^0-9.-]+/g,"")) * num;
	console.log(formatter.format(total))
	button.parentElement.parentElement.parentElement.parentElement.querySelector(".itemPrice").innerText = formatter.format(total);
	console.log(Number(total));
}



//delete cart
const deleteButton = document.querySelectorAll(".cartDelete");
deleteButton.forEach(function(button, index) {
	button.addEventListener("click", function(event) {
		let cartDelete = event.target;
		let cartItem = cartDelete.parentElement.parentElement.parentElement.parentElement.parentElement;
		cartItem.remove();
		subTotal(button);
		console.log(cartItem);
	})

})


//subtotal function

function subTotal(button){
	let totalPriceList = document.querySelectorAll(".itemPrice");
	let subTotalPrice = 0;
	for(let i = 0 ; i < totalPriceList.length; i++){
		subTotalPrice = subTotalPrice + Number(totalPriceList[i].innerHTML.replace(/[^0-9.-]+/g,""));
	}
	document.querySelector(".subTotalPrice").innerText = formatter.format(subTotalPrice);
}











