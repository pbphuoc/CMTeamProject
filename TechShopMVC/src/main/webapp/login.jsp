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
										href="${pageContext.request.contextPath}/Home">Home</a></li>

<!-- 									<li class="nav-item"><a class="nav-link"
										href="product.html">Products</a></li> -->

									<li class="nav-item d_none cartBtnLi"><a class="nav-link" onclick="viewCart()"><i
											class="fa fa-shopping-cart" aria-hidden="true"></i></a></li>																
								</ul>
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</header>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="formTitle">
					<h2>Login</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 m-auto">
				<div class="form_container" id="loginDiv">
					<form action="Auth" method="Post" class="needs-validation"
						novalidate>
						<input type="hidden" name="command" value="login">
						<div class="input-group mb-3">
							<c:choose>
								<c:when
									test="${sessionScope.userfullname == '' && sessionScope.useremail == 'invalid'}">
									<input type="email" class="form-control is-invalid"
										placeholder="Email Address" id="emailLogin" name="emailLogin"
										required>
									<div id="emailLoginFeedback" class="invalid-feedback">
										Incorrect Email or Password. Please try again</div>
								</c:when>
								<c:otherwise>
									<input type="email" class="form-control"
										placeholder="Email Address" id="emailLogin" name="emailLogin"
										required>
									<div id="emailLoginFeedback" class="invalid-feedback"></div>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="input-group mb-3">
							<input type="password" class="form-control"
								placeholder="Password" id="passwordLogin" name="passwordLogin"
								required>
							<div id="passwordLoginFeedback" class="invalid-feedback"></div>
						</div>
						<button class="btn btn-primary" type="submit" id="loginBtn">Login</button>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 m-auto">
				<div class="form_container" id="registerDiv">
					<div id="create-intro">
						<span class="create-text">Don't have account yet ? Join
							with us now ! </span>
					</div>
					<a href="Auth?command=getRegisterForm">
						<button class="btn btn-secondary" type="button">Create an
							account</button>
					</a>

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
							<img class="logo1" src="../images/logo.png" alt="#" />
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
	(function() {
		  'use strict';
		  window.addEventListener('load', function() {
		    // Fetch all the forms we want to apply custom Bootstrap validation styles to
		    var forms = document.getElementsByClassName('needs-validation');
		    // Loop over them and prevent submission
		    var validation = Array.prototype.filter.call(forms, function(form) {
		      form.addEventListener('submit', function(event) {
		        if (form.checkValidity() === false) {
		          event.preventDefault();
		          event.stopPropagation();
		        }	        
		        validateEmail();
		        validatePassword()
		        		        
		      }, false);
		    });
		  }, false);
		})();

	function validateEmail(){
		var emailFeedback = $('#emailLoginFeedback');
		var emailLogin = $('#emailLogin');
		var email = $('#emailLogin').val();
		var errorMsg = '';
		if(email == ''){
			errorMsg = 'Email Address cannot be blank';
		}else if(!isEmailFormatValid(email)){
			errorMsg = 'Please provide a correct email address, e.g test@gmail.com';
		}
		setErrorMessage(emailLogin, emailFeedback, errorMsg);
	}
	function validatePassword(){
		var passwordFeedback =  $('#passwordLoginFeedback');
		var passwordLogin =  $('#passwordLogin');
		var password =  $('#passwordLogin').val();
		var errorMsg = '';
		if(password == ''){
			errorMsg= 'Password cannot be blank';
		}
		setErrorMessage(passwordLogin, passwordFeedback, errorMsg);
	}	
	</script>		
</body>
</html>