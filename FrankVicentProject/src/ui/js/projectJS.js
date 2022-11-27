/**
 * 
 */
 
 function changeProductImage(_src){
	document.getElementById("imageViewer").src = _src;
}

$('#sorter').change( function(){
	var selectedSorter = $('#sorter').val();
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

//$('#searchBar').change(function(){
//	alert('test');
//	
//});



