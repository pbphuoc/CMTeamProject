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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<!-- our own css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/projectStyle.css">
<!-- Responsive-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/responsive.css">
<!-- fevicon -->
<link rel="icon"
	href="${pageContext.request.contextPath}/images/fevicon.png"
	type="image/gif" />
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery.mCustomScrollbar.min.css">
<!-- Tweaks for older IEs-->
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
	media="screen">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<!-- body -->
<body class="main-layout">
	<!-- loader  -->
	<div class="loader_bg">
		<div class="loader">
			<img src="${pageContext.request.contextPath}/images/loading.gif"
				alt="#" />
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
									<a href="${pageContext.request.contextPath}/Home"><img
										src="${pageContext.request.contextPath}/images/logo.png"
										alt="#" /></a>
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

									<!-- <li class="nav-item"><a class="nav-link"
										href="product.html">Products</a></li> -->

									<li class="nav-item d_none cartBtnLi"><a class="nav-link"
										onclick="viewCart()"><i class="fa fa-shopping-cart"
											aria-hidden="true"></i></a></li>
								</ul>
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</header>

	<div id="checkoutBody" class="projectContainer">
		<div class="row">
			<div id="checkoutLeft" class="col-md-7">
				<div class="row mb-5">
					<div id="checkoutMember" class="col">
						<h1>Login</h1>
						<div class="form_container">
							<form>
								<div class="form-row mb-3">
									<div class="col-md-5">
										<input type="text" class="form-control"
											placeholder="Email Address" aria-describedby="basic-addon1"
											id="checkoutEmailLogin">
									</div>
									<div class="col-md-5">
										<input type="password" class="form-control"
											placeholder="Password" aria-describedby="basic-addon1">
									</div>
									<div class="col-md-2">
										<button id="checkoutLoginBtn" class="btn btn-primary"
											type="button">Login</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<h1 id="guestCheckoutLabel">Check Out As Guest</h1>
						<div class="form_container" id="guestForm">
							<form>
								<div class="form-row mb-3">
									<div class="col-md-6">
										<!-- <label for="guestEmail">Contact</label>  -->
										<input type="email" class="form-control"
											placeholder="Email Address" aria-describedby="basic-addon1"
											id="guestEmail">
									</div>
								</div>
								<div class="form-row">
									<div class="form-group col-md-6">
										<input type="text" class="form-control"
											placeholder="First Name" aria-describedby="basic-addon1"
											id="guestFirstName">
									</div>
									<div class="form-group col-md-6">
										<input type="text" class="form-control"
											placeholder="Last Name" aria-describedby="basic-addon1"
											id="guestLastName">
									</div>
								</div>
								<div class="form-row mb-3">
									<div class="col-md-1.1">
										<select id="countryCodeList" class="form-select">
											<option value="australia" selected>+61</option>
										</select>
									</div>
									<div class="col-md-6">
										<input type="text" class="form-control"
											placeholder="Phone Number" aria-describedby="basic-addon1"
											id="guestPhoneNumber">
									</div>
								</div>
								<div class="form-row mb-3">
									<div id="deliveryOptions" class="btn-group btn-group-toggle"
										data-toggle="buttons">
										<label class="btn btn-secondary active mr-2"> <input
											id="optDeliveryLB" type="radio" name="options"
											autocomplete="off" checked> Delivery
										</label> <label class="btn btn-secondary"> <input
											id="optCollectLB" type="radio" name="options"
											autocomplete="off"> Collect At Store
										</label>
									</div>
								</div>
								<div class="form-row mb-3">
									<textarea class="form-control" rows="5"
										placeholder="Delivery Address" aria-describedby="basic-addon1"
										id="guestAddress"></textarea>
								</div>
								<div class="form-row mb-3">
									<button class="btn btn-primary ml-0" type="submit">Proceed
										To Payment</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div id="checkoutRight" class="col-md-5">
				<h1>Order Detail</h1>


				<div class="card-header card-1">
					<p class="card-text text-muted mt-md-4  mb-2 space">
					<a href="${pageContext.request.contextPath}/Cart">
						<span class=" small text-muted ml-2 cursor-pointer">EDIT SHOPPING BAG</span>
					</a>
					</p>
					
				</div>
				<div class="card-body pt-0">
				
				
				<c:forEach var="item" items="${CartItemDetails}">				
					<div class="row justify-content-between">
						<div class="col-auto col-md-7">
							<div class="media flex-column flex-sm-row">
								<img class="img-fluid"
									src="${pageContext.request.contextPath}<c:out value='${item.product.imgSrc}'/>">
								<p class="mb-0">
									<b><c:out value="${item.product.name}"/></b>
								</p>
							</div>
						</div>
					
							
						<div class=" pl-0 flex-sm-col col-auto  my-auto">
							<p class="boxed-1"><c:out value="${item.quantity}"/></p>
						</div>
						<div class=" pl-0 flex-sm-col col-auto  my-auto ">
							<p class="rowPrice">
								<b><c:out value="${item.product.newPrice * item.quantity }"/></b>
							</p>
						</div>
					</div>
				</c:forEach>
					<hr class="my-2">
					<div class="row ">
						<div class="col">
							<div class="row justify-content-between">
								<div class="col-4">
									<p class="mb-1 ">
										<b>Subtotal</b>
									</p>
								</div>
								<div class="flex-sm-col col-auto">
									<p class="mb-1 subTotalPrice">
										<b>0</b>
									</p>
								</div>
							</div>
							
							<div class="row justify-content-between">
								<div class="col-4">
									<p>
										<b>Total</b>
									</p>
								</div>
								<div class="flex-sm-col col-auto">
									<p class="mb-1 subTotalPrice">
										<b>0</b>
									</p>
								</div>
							</div>
							<hr class="my-0">
						</div>
					</div>
					<div class="row mb-5 mt-4 ">
						<div class="col-md-7 col-lg-6 mx-auto">
							<button type="button"
								class="btn btn-block btn-outline-primary btn-lg">ADD
								GIFT CODE</button>
						</div>
					</div>
				</div>


			</div>
            </div>
        </div>
	
	<!-- end footer -->
	<!-- Javascript files-->
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
	<!-- sidebar -->
	<script
		src="${pageContext.request.contextPath}/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>
	<script src="${pageContext.request.contextPath}/js/projectJS.js"></script>
	<script src="${pageContext.request.contextPath}/js/checkout.js"></script>

</body>
</html>