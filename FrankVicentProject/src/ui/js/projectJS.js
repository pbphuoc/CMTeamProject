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

$('#deliveryOptions input').change(function(){
	if($(this).attr('id') == 'optDeliveryLB')
		$('#guestAddress').show();
	else if($(this).attr('id') == 'optCollectLB')
		$('#guestAddress').hide();
});


//$('#searchBar').change(function(){
//	alert('test');
//	
//});



