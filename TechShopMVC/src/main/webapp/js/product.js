const product_images = [
	{
		code: "prd2",
		images: ["../images/product/product_laptopapple1.jpg",
			"../images/product/product_laptopapple2.png",
			"../images/product/product_laptopapple3.jpg",
			"../images/product/product_laptopapple4.jpg",
			"../images/product/product_laptopapple5.jpeg"]
	}, {
		code: "prd6",
		images: ["../images/product/product_iPhonecase1.jpg",
			"../images/product/product_iPhonecase2.jpg",
			"../images/product/product_iPhonecase3.jpg"]
	}, {
		code: "prd5",
		images: ["../images/product/product_appleWatch71.jpg",
			"../images/product/product_appleWatch72.jpg",
			"../images/product/product_appleWatch73.jpg",
			"../images/product/product_appleWatch74.jpg",
			"../images/product/product_appleWatch75.jpg"]
	},{
		code: "prd4",
		images: ["../images/product/product_iPadairgen51.jpg",
			"../images/product/product_iPadairgen52.jpg",
			"../images/product/product_iPadairgen53.jpg",
			"../images/product/product_iPadairgen54.jpg",
			"../images/product/product_iPadairgen55.jpg",
			"../images/product/product_iPadairgen56.jpg"]
	}

];

function loadImagesBySelectedProduct(){
	//need to load from table images from database before this step
	
}

function loadCurrentProduct(){
	var selectedProduct = JSON.parse(localStorage.getItem('selectedProduct'));
	$('#imageViewer').attr("src",selectedProduct.src);
	$('#productName').text(selectedProduct.name);
	$('#productOldPrice').text((selectedProduct.oldPrice -  selectedProduct.newPrice > 0) ? "$" +formatNumberWithCommas(selectedProduct.oldPrice) : "");
	$('#productnewPrice').text(selectedProduct.newPrice);
	$('#productDescription').text(selectedProduct.desc);
	var images = product_images.find(x => x.code === selectedProduct.code).images;
	alert(images.length)
	$.each(images, function(){
		var smallImageCol = $("<div class='col-3 mb-1'></div>").appendTo('#productImageDiv');
		jQuery('<img>',{
			class: "active w-100",
			src: this,
			onclick: "changeProductImage(src)"
		}).appendTo(smallImageCol);		
	});
}