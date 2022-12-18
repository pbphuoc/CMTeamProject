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
<!-- style css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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

									<li class="nav-item d_none"><a class="nav-link" onclick="viewCart()"><i
											class="fa fa-shopping-cart" aria-hidden="true"></i></a></li>																
									<li class="nav-item menuBarUserLi">
									<c:choose>
										<c:when test="${sessionScope.user == ''}">
											<h3 class="menuBarUsername"></h3>
											<a class="nav-link menuBarLoginBtn" onclick="login()">Login</a>
										</c:when>
										<c:when test="${sessionScope.user != ''}">
											<h3 class="menuBarUsername"> Hi ${sessionScope.user},</h3>
											<a class="nav-link menuBarLoginBtn" onclick="logout('${sessionScope.user}')">Logout</a>
										</c:when>
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
	<div id="cartContainer" class="container">
		<div class="row">
			<div class="col-xl-12">
				<div class="cart">
					<h1 class="cartTitle">
						My Shopping Cart <a href="${pageContext.request.contextPath}/Home" class="btn"> <i
							class="fa fa-shopping-cart " aria-hidden="true"></i> <span
							class="backToShop">Back To Shop</span>
						</a>
					</h1>
				</div>
			</div>
		</div>
		<div class="tableWrapper">
			<table class="tableInfor">
				<tr>
					<th class="tableProduct">Product</th>
					<th class="tableQuantity">Quantity</th>
					<th class="tableUnitPrice">Unit Price</th>
					<th class="tableTotal">Total</th>
				</tr>
			</table>
		</div>
		<div class="cartItemWrapper">
		<% double subTotal = 0; %>
		
		<c:forEach var="item" items="${cartItemDetails}">
			<div class="itemTable">
				<div class="cartCol1">
					<div class="itemInfor">
						<a href="product.html"> 
					<img src="${pageContext.request.contextPath}<c:out value='${item.product.imgSrc}'/>">
					</a>
						<a href="product.html"> Apple MacBook Pro 13" M2 chip 512GB </a>
					</div>
					<div class="itemQuantity">
						<button onclick="increase(${item.product.id})" class="increment">
							<i class="fa fa-plus"></i>
						</button>
						<span class="quantity">
							<c:out value="${item.quantity}"/>
						</span>
						<button class="decrement" onclick="decrease(${item.product.id})"><i class="fa fa-minus"></i><span class="itemId" hidden><c:out value="${item.product.id}"/></span></button>						
					</div>
				</div>
				<div class="cartCol2">
					<div class="unitPrice">
					<c:out value='${item.product.newPrice}'/>
					</div>
					<div class="totalPrice">
						<div class="itemPrice">
						<c:out value='${item.product.newPrice * item.quantity}'/> 
						</div>
						<div class="itemRemove">
							<button onclick="remove(${item.product.id})">
								<i class="fa fa-trash-o"></i> <span class="cartDelete"><span class="itemId" hidden><c:out value="${item.product.id}"/></span>  Remove</span>
							</button>
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
		<div class="paymentWrapper">
			<div class="subTotal">
				<div class="summary">Subtotal</div>
				<p class="subTotalPrice">
			
			</p>
			</div>
			<div class="checkOutNow">
				<a href="checkout.html">
					<button>
						<i class="fa-solid fa-dollar-sign"></i>Check Out Now
					</button>
				</a>
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
	<script src="${pageContext.request.contextPath}/js/cart.js"></script>
	<script type="text/javascript">
		document.onload = loadLoggedInUser();
	</script>		
</body>
</html>

