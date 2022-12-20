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
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
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
			<div class="col-lg-12">
				<div id="register-intro-text" class="formTitle">
					<h2>Create an account</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8 m-auto">
				<div class="form_container">
					<form action="Auth" method="Post" class="needs-validation" novalidate>
						<input type="hidden" name="command" value="register">
						<div class="form-group">
							<label for="fullnameRegister">Full Name</label>
							<input type="text" class="form-control" name="fullnameRegister" id="fullnameRegister" placeholder="Please enter your full name" required>
							<div id="fullnameRegisterFeedback" class="invalid-feedback">
							</div>								
						</div>
						<div class="form-group">
							<label for="emailRegister">Email Address</label>
							<input type="email" class="form-control" name="emailRegister" id="emailRegister" placeholder="Please enter your email address" aria-label="Username" aria-describedby="basic-addon1" required>
							<div id="emailRegisterFeedback" class="invalid-feedback">
							</div>							
						</div>	
						<div class="form-group">
							<label for="passwordRegister">Password</label>
							<input type="password" class="form-control" name="passwordRegister" id="passwordRegister" placeholder="Please enter your password" aria-label="Password" aria-describedby="basic-addon1" required>
							<div id="passwordRegisterFeedback" class="invalid-feedback">
							</div>															
						</div>
						<div class="form-group">
							<label for="confirmPasswordRegister">Confirm Password</label>
							<input type="password" class="form-control" id="confirmPasswordRegister" placeholder="Please enter your password again" aria-label="Password" aria-describedby="basic-addon1" required>
							<div id="confirmPasswordRegisterFeedback" class="invalid-feedback">
							</div>															
						</div>																		
						<div class="form-group">
							<label for="mobileRegister">Mobile Number</label>
							<input type="tel" class="form-control"  pattern="/^(?:\+?(61))? ?(?:\((?=.*\)))?(0?[2-57-8])\)? ?(\d\d(?:[- ](?=\d{3})|(?!\d\d[- ]?\d[- ]))\d\d[- ]?\d[- ]?\d{3})$/" name="mobileRegister" id="mobileRegister" placeholder="Please enter your mobile number" aria-label="Mobile" aria-describedby="basic-addon1" required>
							<div id="mobileRegisterFeedback" class="invalid-feedback">
							</div>															
						</div>						
						<button class="btn btn-primary" type="submit">Register</button>									
					</form>
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
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
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
			        validateFullname();
			        validateEmail();
			        validatePassword();
			        validateConfirmPassword();
			        validateMobile();	        
			        
			        form.classList.add('was-validated');
			      }, false);
			    });
			  }, false);
			})();
		function validateFullname(){
			var fullnameFeedback =  $('#fullnameRegisterFeedback') ;
			var fullnameRegister =  $('#fullnameRegister');
			var fullname =  $('#fullnameRegister').val();	
			var errorMsg = '';
			if(fullname == ''){				
				errorMsg = 'Fullname cannot be blank';
			}
			setErrorMessage(fullnameRegister,fullnameFeedback, errorMsg);
		}		
		function validateEmail(){
			var emailFeedback = $('#emailRegisterFeedback') ;
			var emailRegister = $('#emailRegister');
			var email = $('#emailRegister').val();
			var errorMsg = '';
			if(email == ''){				
				errorMsg = 'Email Address cannot be blank';
			}else if(!isEmailFormatValid(email)){				
				errorMsg = 'Please provide a correct email address, e.g test@gmail.com';
			}
			setErrorMessage(emailRegister, emailFeedback, errorMsg);
		}
		function validatePassword(){
			var passwordFeedback =  $('#passwordRegisterFeedback') ;
			var passwordRegister =  $('#passwordRegister');
			var password =  $('#passwordRegister').val();	
			var errorMsg = '';
			if(password == ''){				
				errorMsg = 'Password cannot be blank';
			}
			setErrorMessage(passwordRegister, passwordFeedback, errorMsg);
		}
		function validateConfirmPassword(){
			var confirmPasswordFeedback =  $('#confirmPasswordRegisterFeedback') ;
			var confirmPasswordRegister =  $('#confirmPasswordRegister');
			var confirmPassword =  $('#confirmPasswordRegister').val();
			var password =  $('#passwordRegister').val();
			var errorMsg = ''			
			if(confirmPassword == ''){
				errorMsg = 'Confirmed Password cannot be blank';
			}else if(confirmPassword !== password){				
				errorMsg = 'Confirmed Password does not match';
			}			
			setErrorMessage(confirmPasswordRegister, confirmPasswordFeedback, errorMsg);
		}
		function validateMobile(){
			var mobileFeedback = $('#mobileRegisterFeedback') ;
			var mobileRegister = $('#mobileRegister');
			var mobile = $('#mobileRegister').val();
			var errorMsg = '';
			if(mobile == ''){				
				errorMsg = 'Mobile Number cannot be blank';
			}else if(!isMobileFormatValid(mobile)){				
				errorMsg = 'Please provide a correct Australian mobile number, 61/0 123 456 789';
			}
			setErrorMessage(mobileRegister,mobileFeedback, errorMsg);
		}		
	</script>	
</body>
</html>