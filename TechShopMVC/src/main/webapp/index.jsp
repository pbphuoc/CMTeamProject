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

									<li class="nav-item d_none"><a class="nav-link" onclick="viewCart()"><i
											class="fa fa-shopping-cart" aria-hidden="true"></i></a></li>																
									<li class="nav-item menuBarUserLi"><h3 class="menuBarUsername"></h3><a class="nav-link menuBarLoginBtn" href="#">Login</a>
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
	<!-- end banner -->
	<!-- three_box -->
	<div class="three_box">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="titlepage">
						<h2>Categories</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 mb-4">
					<div class="box_text">
						<i><img class="categoryIcon"
							src="${pageContext.request.contextPath}/images/category/category_computer.png" alt="#" /></i>
						<h3>Computer</h3>
						<p>We have computers</p>
					</div>
				</div>
				<div class="col-md-4 mb-4">
					<div class="box_text">
						<i><img class="categoryIcon"
							src="${pageContext.request.contextPath}/images/category/category_laptop.png" alt="#" /></i>
						<h3>Laptop</h3>
						<p>We have laptops</p>
					</div>
				</div>
				<div class="col-md-4 mb-4">
					<div class="box_text">
						<i><img class="categoryIcon"
							src="${pageContext.request.contextPath}/images/category/category_tablet.png" alt="#" /></i>
						<h3>Tablet</h3>
						<p>We have tablets</p>
					</div>
				</div>
				<div class="col-md-4 mb-4">
					<div class="box_text">
						<i><img class="categoryIcon"
							src="${pageContext.request.contextPath}/images/category/category_cellphone.png" alt="#" /></i>
						<h3>Cellphone</h3>
						<p>We have cellphones</p>
					</div>
				</div>
				<div class="col-md-4 mb-4">
					<div class="box_text">
						<i><img class="categoryIcon"
							src="${pageContext.request.contextPath}/images/category/category_accessory.png" alt="#" /></i>
						<h3>Accessory</h3>
						<p>We have device accessories</p>
					</div>
				</div>
				<div class="col-md-4 mb-4">
					<div class="box_text">
						<i><img class="categoryIcon"
							src="${pageContext.request.contextPath}/images/category/category_smartwatch.png" alt="#" /></i>
						<h3>Smart Watch</h3>
						<p>We have smart watches</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- three_box -->
	<!-- products -->
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
									<div class='product_box'>
										<form action="ProductDetail" method="post">
											<input class="productID" type="hidden" name="id" value="${product.id}" hidden>
											<div class="productThumbnailContainer">
												<img class="productThumbnail"
													src='${pageContext.request.contextPath}${product.imgSrc}' />
											</div>
											<p class="productDescription">
												<c:out value="${product.name}"></c:out>
											</p>
											<h3 class="oldPrice">											
												<c:choose>
													<c:when test="${product.oldPrice > product.newPrice}">
														<c:out value="$ ${product.oldPrice}"></c:out>
													</c:when>
												</c:choose>												
											</h3>
											<h3 class="newPrice">
												<c:out value="$ ${product.newPrice }"></c:out>
											</h3>
											<div class="row">
												<div class="col-md-6">
													<button class="productButton" type="submit">View</button>
												</div>
												<div class="col-md-6">
													<button class="productButton" type="button" onclick="addToCart(${product.id})">Add To Cart</button>
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
							<p>© 2022 Frankie and Vinnie.</p>
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
	<script type="text/javascript">
		document.onload = loadLoggedInUser();
	</script>		
</body>
</html>

