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
<!-- fevicon -->
<link rel="icon" href="${pageContext.request.contextPath}/images/logo.png" type="image/gif" />
<!-- Tweaks for older IEs-->
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
	media="screen">
<!-- bootstrap css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">	
<!-- Responsive-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">	
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.mCustomScrollbar.min.css">	
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />	
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
									<a href="Home"><img src="${pageContext.request.contextPath}/images/logo.png" alt="#" /></a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6">
						<div class="search_bar ">
							<div class="form_container">
								<input id="searchBar" class="form-control me-2" type="search"
									placeholder="Search" onkeyup="searchProduct(this)">
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
										href="Home">Home</a></li>

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
												<h3 class="menuBarUsername">Hi ${sessionScope.userfullname},</h3><a class="nav-link menuBarLoginBtn" href="Logout">Logout</a>
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

	<div class="projectContainer">
		<div class="row">
			<div class="col-md-3">
				<div class="row filterSection">
					<div class="col-md-12">
						<h3>Brand</h3>
						<p class="selectedFilter" id="selectedBrands">
							<ul>
								<c:forEach var="key" items="${brandFilters.keySet()}">									
									<c:if test='${!brandFilters.get(key).getSelected().equalsIgnoreCase("")}'>
										<li>${brandFilters.get(key).getName()}</li>
									</c:if>
								</c:forEach>	
							</ul>					
						</p>
						<select id="brandSelect" multiple data-live-search="true"
							title="Filtered By Brand" class="queryFilter">
							<c:forEach var="key" items="${brandFilters.keySet()}">
								<option value="${brandFilters.get(key).getId()}" ${brandFilters.get(key).getSelected()}>
								${brandFilters.get(key).getName()}(${brandFilters.get(key).getStock()})
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row mt-1 filterSection">
					<div class="row">
						<div class="col-md-12">
							<h3>Category</h3>
							<p class="selectedFilter" id="selectedCategories">
								<ul>
									<c:forEach var="key" items="${categoryFilters.keySet()}">
										<c:if test='${!categoryFilters.get(key).getSelected().equalsIgnoreCase("")}'>
											<li>${categoryFilters.get(key).getName()}</li>
										</c:if>
									</c:forEach>
								</ul>							
							</p>
							<select id="categorySelect" multiple data-live-search="true"
								title="Filtered By Category" class="queryFilter">
								<c:forEach var="key" items="${categoryFilters.keySet()}">
									<option value="${categoryFilters.get(key).getId()}" ${categoryFilters.get(key).getSelected()}>
									${categoryFilters.get(key).getName()}(${categoryFilters.get(key).getStock()})
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="row mt-1 filterSection">
					<div class="col-md-12">
						<h3>Price</h3>
						<div class="row">
							<input type="number" class="form-control" id="priceFrom"
								placeholder="From Price" value="${priceMin}">
						</div>
						<div class="row mt-2">
							<input type="number" class="form-control" id="priceTo"
								placeholder="To Price" value="${priceMax}">
						</div>
						<div class="row mt-3">
							<button id="priceFilterBtn" onclick="searchProductWithFilters()">Apply</button>
						</div>
					</div>
				</div>
				<div class="row mt-1 filterSection">
					<div class="row">
						<div class="col-md-12">
							<h3>Availability</h3>
							<p class="selectedFilter" id="selectedAvailabilities">
								<ul>
									<c:forEach var="key" items="${availabilityFilters.keySet()}">
										<c:if test='${!availabilityFilters.get(key).getSelected().equalsIgnoreCase("")}'>
											<li>${availabilityFilters.get(key).getName()}</li>
										</c:if>
									</c:forEach>
								</ul>							
							</p>
							<select id="availabilitySelect" multiple data-live-search="true"
								title="Filtered By Availability" class="queryFilter">
								<c:forEach var="key" items="${availabilityFilters.keySet()}">
									<option value="${availabilityFilters.get(key).getId()}" ${availabilityFilters.get(key).getSelected()}>
									${availabilityFilters.get(key).getName()}(${availabilityFilters.get(key).getStock()})
									</option>
								</c:forEach>								
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-8">
				<div class="row">
					<div class="col-md-12">
						<div id="numberOfResult">
							<p>${products.size()} result(s) matched for <span id="searchKeyword">${keyword}</span></p>
						</div>
					</div>
				</div>
				<div class="row">
					<div id="sortSection">
						<h3>Sort By</h3>
						<select id="sorter" class="form-select queryFilter"
							aria-label="Default select example" class="queryFilter">
							<c:forEach var="key" items="${sorters.keySet()}">
								<option value="${sorters.get(key).getId()}" ${sorters.get(key).getSelected()}>
								${sorters.get(key).getName()}
								</option>
							</c:forEach>							
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="our_products">
							<div class="row">
								<c:forEach var="product" items="${products}">
									<div class="col-md-4">
										<div class="product_box">
											<form>
												<div class="productThumbnailContainer">
													<a
														href="Product?command=viewProductDetail&productID=${product.id}">
														<img class="productThumbnail"
														src='${pageContext.request.contextPath}${product.imgSrc}' />
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
														<a class="productButton" type="button"
															href="Product?command=viewProductDetail&productID=${product.id}">View</a>
													</div>
													<div class="col-md-6">
													<c:choose>
														<c:when test="${product.getStock() > 0}">
															<button class="productButton" type="button"
																onclick="increase(${product.id})">Add To Cart</button>														
														</c:when>
														<c:otherwise>
															<button class="productButton" type="button" disabled>${product.getAvailabilityMap().get(product.getStockStatus())}</button>
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
	<!-- sidebar -->
	<script src="${pageContext.request.contextPath}/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>
	<script src="${pageContext.request.contextPath}/js/projectJS.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>	
	<script type="text/javascript">
		$(function() {
			$('select').selectpicker();
		});
//		document.onload = loadSorter();	
	</script>		
</body>
</html>