<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
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
<!-- bootstrap css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<!-- our own css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/projectStyle.css">
<!-- Responsive-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
<!-- fevicon -->
<link rel="icon" href="${pageContext.request.contextPath}/images/fevicon.png" type="image/gif" />
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.mCustomScrollbar.min.css">
<!-- Tweaks for older IEs-->
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
	media="screen">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">	
</head>
<!-- body -->
<body class="main-layout">
	<!-- loader  -->
	<div class="loader_bg">
		<div class="loader">
			<img src="${pageContext.request.contextPath}/images/loading.gif" alt="#" />
		</div>
	</div>
	<!-- end loader -->
	<!-- header -->
	<header>
		<!-- header inner -->
		<div class="header">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xl col-lg col-md col-sm ">
						<div class="navbar-brand">
							<div class="center-desk" class="d-inline-flex">
								<div class="logo">
									<a href="${pageContext.request.contextPath}/Home"><img src="${pageContext.request.contextPath}/images/logo.png" alt="#" /></a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6">
						<div class="search_bar ">
							<div class="form_container">
								<form action="searchResult.html" method="post">
									<input id="searchBar" class="form-control me-2" type="search"
										placeholder="Search" aria-label="Search">
								</form>
							</div>
						</div>
					</div>
					<div class="col-xl col-lg col-md col-sm ">
						<nav class="navigation navbar navbar-expand-xl navbar-dark ">
							<button class="navbar-toggler" type="button"
								data-toggle="collapse" data-target="#navbarsExample04"
								aria-controls="navbarsExample04" aria-expanded="false"
								aria-label="Toggle navigation">
								<span class="navbar-toggler-icon"></span>
							</button>
							<div class="collapse navbar-collapse" id="navbarsExample04">
								<ul class="navbar-nav mr-auto">
									<li class="nav-item "><a class="nav-link"
										href="${pageContext.request.contextPath}/Home">Home</a></li>

<!-- 									<li class="nav-item"><a class="nav-link"
										href="product.html">Products</a></li> -->

									<li class="nav-item d_none cartBtnLi"><a class="nav-link" onclick="viewCart()"><i
											class="fa fa-shopping-cart" aria-hidden="true"></i></a></li>																
									<li class="nav-item menuBarUserLi">
										<c:choose>
											<c:when test="${sessionScope.userfullname == null || sessionScope.userfullname == ''}">
												<h3 class="menuBarUsername"></h3><a class="nav-link menuBarLoginBtn" href="Auth?command=getLoginForm" >Login</a>
											</c:when>
											<c:otherwise>
												<h3 class="menuBarUsername">Hi ${sessionScope.userfullname},</h3><a class="nav-link menuBarLoginBtn" href="Auth?command=logout">Logout</a>
											</c:otherwise>																						
										</c:choose>
									</li>
								</ul>
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- end header inner -->
	<!-- end header -->
	<!-- banner -->
	<section class="banner_main">
		<div id="banner1" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#banner1" data-slide-to="0" class="active"></li>
				<li data-target="#banner1" data-slide-to="1"></li>
				<li data-target="#banner1" data-slide-to="2"></li>
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
										<a href="#">Buy Now </a> <a href="contact.html">Contact </a>
									</div>
								</div>
								<div class="col-md-6">
									<div class="text_img">
										<figure>
											<img src="${pageContext.request.contextPath}/images/pct.png" alt="#" />
										</figure>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="carousel-item">
					<div class="container">
						<div class="carousel-caption">
							<div class="row">
								<div class="col-md-6">
									<div class="text-bg">
										<span>Computer And Laptop</span>
										<h1>Accessories</h1>
										<p>We have a wide range of computers, laptops, cellphones,
											smart watches and accessories.</p>
										<a href="#">Buy Now </a> <a href="contact.html">Contact </a>
									</div>
								</div>
								<div class="col-md-6">
									<div class="text_img">
										<figure>
											<img src="${pageContext.request.contextPath}/images/pct.png" alt="#" />
										</figure>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="carousel-item">
					<div class="container">
						<div class="carousel-caption">
							<div class="row">
								<div class="col-md-6">
									<div class="text-bg">
										<span>Computer And Laptop</span>
										<h1>Accessories</h1>
										<p>We have a wide range of computers, laptops, cellphones,
											smart watches and accessories.</p>
										<a href="#">Buy Now </a> <a href="contact.html">Contact </a>
									</div>
								</div>
								<div class="col-md-6">
									<div class="text_img">
										<figure>
											<img src="${pageContext.request.contextPath}/images/pct.png" alt="#" />
										</figure>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
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

	<div class="projectContainer">
		<div class="row">
			<div class="col-md-3">
				<div class="row filterSection">
					<div class="col-md-12">
						<h3>Brand</h3>
						<p class="selectedFilter" id="selectedBrands"></p>
						<select id="brandSelect" multiple data-live-search="true"
							title="Filtered By Brand">
							<option>Apple</option>
							<option>Samsung</option>
							<option>Google</option>
						</select>
					</div>
				</div>
				<div class="row mt-2 filterSection">
					<div class="row">
						<div class="col-md-12">
							<h3>Category</h3>
							<p class="selectedFilter" id="selectedCategories"></p>
							<select id="categorySelect" multiple data-live-search="true"
								title="Filtered By Category">
								<option value="computer">Computer</option>
								<option value="laptop">Laptop</option>
								<option value="tablet">Tablet</option>
								<option value="phone">Phone</option>
								<option value="accessory">Accessory</option>
								<option value="smartwatch">Smart Watch</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row mt-2 filterSection">
					<div class="col-md-12">
						<h3>Price</h3>
						<div class="row">
							<input type="number" class="form-control" id="priceFrom"
								placeholder="From Price">
						</div>
						<div class="row mt-2">
							<input type="number" class="form-control" id="priceTo"
								placeholder="To Price">
						</div>
						<div class="row mt-3">
							<button id="priceFilterBtn">Apply</button>
						</div>
					</div>
				</div>
				<div class="row mt-2 filterSection">
					<div class="row">
						<div class="col-md-12">
							<h3>Availability</h3>
							<p class="selectedFilter" id="selectedAvailabilities"></p>
							<select id="availabilitySelect" multiple data-live-search="true"
								title="Filtered By Availability">
								<option value="inStock">In Stock</option>
								<option value="outOfStock">Out Of Stock</option>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-8">
				<div class="row">
					<div class="col-md-12">
						<div id="numberOfResult">
							<p>6 results matched</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div id="sortSection">
						<h3>Sort By</h3>
						<select id="sorter" class="form-select"
							aria-label="Default select example">
							<option value="relevancy" selected>Relevancy</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="our_products">
							<div class="row">
								<div class="col-md-4">
									<div class="product_box">
										<figure>
											<img class="productThumbnail"
												src="../images/product/product_computerdell1.jpg" alt="#" />
										</figure>
										<p class="productDescription">Dell 7040 SFF Bundle Desktop
											i7-6700 3.4GHz 16GB RAM 512GB NVMe SSD + 22" Monitor -
											Refurbished Grade A</p>
										<h3 class="oldPrice">$2,850.00</h3>
										<h3 class="newPrice">$2,599.00</h3>
										<div class="row">
											<div class="col-md-6">
												<a class="productButton" href="#">View</a>
											</div>
											<div class="col-md-6">
												<a class="productButton" href="#">Add To Cart</a>
											</div>
										</div>
									</div>
								</div>								
							</div>
						</div>
					</div>
				</div>
			</div>			
		</div>
	</div>

	<footer>
		<div class="footer">
			<div class="container">
				<div class="row">
					<div class="col-xl-3 col-lg-3 col-md-6 col-sm-6">
						<div class="logo-footer">
							<img class="logo1" src="${pageContext.request.contextPath}/images/logo.png" alt="#" />
						</div>

						<ul class="social_icon">
							<li><a href="#"><i class="fa fa-facebook"
									aria-hidden="true"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter"
									aria-hidden="true"></i></a></li>
							<li><a href="#"><i class="fa fa-linkedin-square"
									aria-hidden="true"></i></a></li>
							<li><a href="#"><i class="fa fa-instagram"
									aria-hidden="true"></i></a></li>
						</ul>
					</div>
					<div class="col-xl-3 col-lg-3 col-md-6 col-sm-6">
						<h3>HELP & SUPPORT</h3>
						<ul class="help_support">
							<li><a href="#">Faulty goods returns</a></li>
							<li><a href="#">Refunds & Warranties guide</a></li>
							<li><a href="#">Scam alerts</a></li>
						</ul>
					</div>
					<div class="col-xl-3 col-lg-3 col-md-6 col-sm-6">
						<h3>Contact Us</h3>
						<ul class="conta">
							<li><a href="#">About us</a></li>
							<li><a href="#">Jobs</a></li>
							<li><a href="#">Guides & Tips</a></li>
						</ul>
					</div>
					<div class="col-xl-3 col-lg-3 col-md-6 col-sm-6">
						<form class="bottom_form">
							<h3>Newsletter</h3>
							<input class="enter" placeholder="Enter your email" type="text"
								name="Enter your email">
							<button class="sub_btn">subscribe</button>
						</form>
					</div>
				</div>
			</div>
			<div class="copyright">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<p>� 2022 Frankie and Vinnie.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- end footer -->
	<!-- Javascript files-->
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
	<!-- sidebar -->
	<script src="${pageContext.request.contextPath}/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>
	<script src="${pageContext.request.contextPath}/js/projectJS.js"></script>
</body>
</html>