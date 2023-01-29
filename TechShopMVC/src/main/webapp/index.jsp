<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="model.SearchFilterDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- basic -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- mobile metas -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<!-- site metas -->
<title>Techila</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">
<jsp:include page="allref.jsp"></jsp:include>
</head>
<body class="main-layout">
	<jsp:include page="header.jsp"></jsp:include>
	<section class="banner_main">
		<div id="banner1" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#banner1" data-slide-to="0" class="active"></li>
<!-- 				<li data-target="#banner1" data-slide-to="1"></li>				 -->
			</ol>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<div class="container">
						<div class="carousel-caption">
							<div class="row">
								<div class="col-md-6">
									<div class="text-bg">
										<span>Computer And Laptop</span>
										<h1>Accessories</h1>
										<p>We have a wide range of computers, laptops, cellphones,
											smart watches and accessories.</p>
										<a class="read_more" href="Product?command=search&keywords=">View All </a> 
<!-- 										<a href="contact.html">Contact </a> -->
									</div>
								</div>
								<div class="col-md-6">
									<div class="text_img">
										<figure>
											<img src="../images/pct.png" alt="#" />
										</figure>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
<!-- 				<div class="carousel-item"> -->
<!-- 					<div class="container"> -->
<!-- 						<div class="carousel-caption"> -->
<!-- 							<div class="row"> -->
<!-- 								<div class="col-md-6"> -->
<!-- 									<div class="text-bg"> -->
<!-- 										<span>Computer And Laptop</span> -->
<!-- 										<h1>Accessories</h1> -->
<!-- 										<p>We have a wide range of computers, laptops, cellphones, -->
<!-- 											smart watches and accessories.</p> -->
<!-- 										<a href="Product?command=search&keywords=">View All </a>  -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-6"> -->
<!-- 									<div class="text_img"> -->
<!-- 										<figure> -->
<!-- 											<img src="../images/pct.png" alt="#" /> -->
<!-- 										</figure> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div>				 -->
			</div>
			<a class="carousel-control-prev" href="#banner1" role="button"
				data-slide="prev"> <i class="fa fa-chevron-left"
				aria-hidden="true"></i>
			</a> <a class="carousel-control-next" href="#banner1" role="button"
				data-slide="next"> <i class="fa fa-chevron-right"
				aria-hidden="true"></i>
			</a>
		</div>
	</section>

	<div class="three_box mb-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="titlepage">
						<h2>Categories</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<c:forEach items="${categoryList}" var="category">
					<div class="col-md-4 mb-4">				
						<div class="box_text">
							<a href="Product?command=search&keywords=&category=${category.id}">
								<i><img class="categoryIcon"
									src="${category.imgSrc}"/></i>
								<h3>${category.name}</h3>
							</a>
						</div>								
					</div>
				</c:forEach>				
			</div>			
		</div>
	</div>
	
	<div class="three_box">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="titlepage">
						<h2>Brands</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<c:forEach items="${brandList}" var="brand">
					<div class="col-md-4 mb-4">				
						<div class="box_text">
							<a href="Product?command=search&keywords=&brand=${brand.id}">
								<i><img class="categoryIcon"
									src="${brand.imgSrc}"/></i>
								<h3>${brand.name}</h3>
							</a>
						</div>								
					</div>
				</c:forEach>				
			</div>			
		</div>
	</div>
	

	<div class="products">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="titlepage">
						<h2>Products</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="our_products">
						<div id="productRowDiv" class="row">
							<c:forEach var="product" items="${productList}">
								<div class='col-md-4'>
									<div class='product_box productOverlayCover'>
										<div class="productOverlay" onclick="productOverlayOff()">
											<p>Item added!</p>
										</div>
										<form>											
											<div class="productThumbnailContainer">
												<a href="Product?command=viewProductDetail&productID=${product.id}">
												<img class="productThumbnail"
													src='..${product.imgSrc}' />
													</a>
											</div>
											<p class="productDescription">
												<c:out value="${product.name}"></c:out>
											</p>
											<h3 class="oldPrice">
												<c:if test="${product.oldPrice > product.newPrice}">
													<c:out value="$ ${product.oldPrice}"></c:out>
												</c:if>
											</h3>
											<h3 class="newPrice">
												<c:out value="$ ${product.newPrice }"></c:out>
											</h3>
											<div class="row">
												<div class="col-md-6">
													<a class="productButton" type="button" href="Product?command=viewProductDetail&productID=${product.id}">View</a>
												</div>
												<div class="col-md-6">										
													<c:choose>
														<c:when test="${product.getStock() > 0}">
															<button class="productButton addbutton" type="button"
																onclick="increase(${product.id})">Add To Cart</button>														
														</c:when>
														<c:otherwise>
															<button class="productButton" type="button" disabled>${product.getStockStatusDescription()}</button>
														</c:otherwise>
													</c:choose>															
												</div>
											</div>
										</form>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<a class="read_more" href="Product?command=search&keywords=">View All Products</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>	
	<jsp:include page="allscript.jsp"></jsp:include>
</body>
</html>

