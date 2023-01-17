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
<jsp:include page="allref.jsp"></jsp:include>
</head>
<body class="main-layout">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<div class="projectContainer">
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
							<input type="hidden" name="prevUrl" value="${param['prevUrl']}">
							<div class="input-group mb-3">
								<c:choose>
									<c:when
										test="${loginError == 'invalid'}">
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
							<button class="btn btn-info" type="submit" id="loginBtn">Login</button>
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
						<a href="Auth?command=getRegisterForm&prevUrl=${param['prevUrl']}">
							<button class="btn btn-secondary" type="button">Create an
								account</button>
						</a>
	
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>	
	<jsp:include page="allscript.jsp"></jsp:include>
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
	$('#emailLogin').on('focusout', function(){
		validateEmail();
	});
	$('#passwordLogin').on('focusout', function(){
		validatePassword();
	});	
	</script>		
</body>
</html>