/**
 * 
 */
 
const formatter = new Intl.NumberFormat('en-Us',{
	style: 'currency',
	currency: 'USD',
	minimumFractionDigits: 0
})
 
 
 
 $(document).ready(function(){
	
	$('.rowPrice').toArray().forEach( function(i){
		var price = $(i).text();
		console.log(price);
		$(i).text(formatter.format(price));
	})
	var subtotal = 0;
	
	$('.rowPrice').toArray().forEach( function(i){
		subtotal = Number($(i).text().replace(/[^0-9.-]+/g,"")) + subtotal;
	})

	$('.subTotalPrice').text(function(){
		$(this).text(formatter.format(subtotal));
		;
	});
	
	$("#total").val(`${subtotal}`);
	}
)
