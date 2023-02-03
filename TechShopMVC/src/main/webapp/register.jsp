<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="constant.GlobalConstant"%>
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
<jsp:include page="${GlobalConstant.ALLREF_JSP}"></jsp:include>
</head>
<body class="main-layout">
	<jsp:include page="${GlobalConstant.HEADER_JSP}"></jsp:include>
	<div class="container">
		<div class="projectContainer">
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
						<form action="${GlobalConstant.AUTH_URL}" method="Post"
							class="needs-validation" novalidate>
							<input type="hidden" name="${GlobalConstant.COMMAND}"
								value="${GlobalConstant.REGISTER}"> <input type="hidden"
								name="${GlobalConstant.PREV_URL}"
								value="${param[GlobalConstant.PREV_URL]}">
							<div class="form-group">
								<label for="fullnameRegister">Full Name</label> <input
									type="text" class="form-control"
									name="${GlobalConstant.FULLNAME_REGISTER}"
									id="${GlobalConstant.FULLNAME_REGISTER}"
									placeholder="Please enter your full name" required>
								<div id="fullnameRegisterFeedback" class="invalid-feedback">
								</div>
							</div>
							<div class="form-group">
								<c:choose>
									<c:when test="${error != null}">
										<label for="${GlobalConstant.EMAIL_REGISTER}">Email
											Address</label>
										<input type="email" class="form-control is-invalid"
											name="${GlobalConstant.EMAIL_REGISTER}"
											id="${GlobalConstant.EMAIL_REGISTER}"
											placeholder="Please enter your email address"
											pattern="${GlobalConstant.EMAIL_PATTERN}" required>
										<div id="emailRegisterFeedback" class="invalid-feedback">
											${error}</div>
									</c:when>
									<c:otherwise>
										<label for="${GlobalConstant.EMAIL_REGISTER}">Email
											Address</label>
										<input type="email" class="form-control"
											name="${GlobalConstant.EMAIL_REGISTER}"
											id="${GlobalConstant.EMAIL_REGISTER}"
											placeholder="Please enter your email address"
											pattern="${GlobalConstant.EMAIL_PATTERN}" required>
										<div id="emailRegisterFeedback" class="invalid-feedback"></div>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<label for="${GlobalConstant.PASSWORD_REGISTER}">Password</label>
								<input type="password" class="form-control"
									name="${GlobalConstant.PASSWORD_REGISTER}"
									id="${GlobalConstant.PASSWORD_REGISTER}"
									placeholder="Please enter your password" required>
								<div id="passwordRegisterFeedback" class="invalid-feedback">
								</div>
							</div>
							<div class="form-group">
								<label for="confirmPasswordRegister">Confirm Password</label> <input
									type="password" class="form-control"
									id="confirmPasswordRegister"
									placeholder="Please enter your password again"
									aria-label="Password" aria-describedby="basic-addon1" required>
								<div id="confirmPasswordRegisterFeedback"
									class="invalid-feedback"></div>
							</div>
							<div class="form-group">
								<label for="${GlobalConstant.MOBILE_REGISTER}">Mobile
									Number</label> <input type="tel" class="form-control"
									name="${GlobalConstant.MOBILE_REGISTER}"
									id="${GlobalConstant.MOBILE_REGISTER}"
									placeholder="Please enter your mobile number" required>
								<div id="mobileRegisterFeedback" class="invalid-feedback">
								</div>
							</div>
							<button class="btn btn-info" type="submit">Register</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="${GlobalConstant.FOOTER_JSP}"></jsp:include>
	<jsp:include page="${GlobalConstant.ALLSCRIPT_JSP}"></jsp:include>
	<script type="text/javascript">
		(function() {
			'use strict';
			window.addEventListener('load',
					function() {
						// Fetch all the forms we want to apply custom Bootstrap validation styles to
						var forms = document
								.getElementsByClassName('needs-validation');
						// Loop over them and prevent submission
						var validation = Array.prototype.filter.call(forms,
								function(form) {
									form.addEventListener('submit', function(
											event) {
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
		function validateFullname() {
			var fullnameFeedback = $('#fullnameRegisterFeedback');
			var fullnameRegister = $('#fullnameRegister');
			var fullname = $('#fullnameRegister').val();
			var errorMsg = '';
			if (fullname == '') {
				errorMsg = 'Fullname cannot be blank';
			}
			setErrorMessage(fullnameRegister, fullnameFeedback, errorMsg);
		}
		function validateEmail() {
			var emailFeedback = $('#emailRegisterFeedback');
			var emailRegister = $('#emailRegister');
			var email = $('#emailRegister').val();
			var errorMsg = '';
			if (email == '') {
				errorMsg = 'Email Address cannot be blank';
			} else if (!isEmailFormatValid(email)) {
				errorMsg = 'Please provide a correct email address, e.g test@gmail.com';
			}
			setErrorMessage(emailRegister, emailFeedback, errorMsg);
		}
		function validatePassword() {
			var passwordFeedback = $('#passwordRegisterFeedback');
			var passwordRegister = $('#passwordRegister');
			var password = $('#passwordRegister').val();
			var errorMsg = '';
			if (password == '') {
				errorMsg = 'Password cannot be blank';
			}
			setErrorMessage(passwordRegister, passwordFeedback, errorMsg);
		}
		function validateConfirmPassword() {
			var confirmPasswordFeedback = $('#confirmPasswordRegisterFeedback');
			var confirmPasswordRegister = $('#confirmPasswordRegister');
			var confirmPassword = $('#confirmPasswordRegister').val();
			var password = $('#passwordRegister').val();
			var errorMsg = ''
			if (confirmPassword == '') {
				errorMsg = 'Confirmed Password cannot be blank';
			} else if (confirmPassword !== password) {
				errorMsg = 'Confirmed Password does not match';
			}
			setErrorMessage(confirmPasswordRegister, confirmPasswordFeedback,
					errorMsg);
		}
		function validateMobile() {
			var mobileFeedback = $('#mobileRegisterFeedback');
			var mobileRegister = $('#mobileRegister');
			var mobile = $('#mobileRegister').val();
			var errorMsg = '';
			if (mobile == '') {
				errorMsg = 'Mobile Number cannot be blank';
			} else if (!isMobileFormatValid(mobile)) {
				errorMsg = 'Please provide a correct Australian mobile number, 61/0 123 456 789';
			}
			setErrorMessage(mobileRegister, mobileFeedback, errorMsg);
		}
		$('#fullnameRegister').on('focusout', function() {
			validateFullname();
		});
		$('#emailRegister').on('focusout', function() {
			validateEmail();
		});
		$('#passwordRegister').on('focusout', function() {
			validatePassword();
		});
		$('#confirmPasswordRegister').on('focusout', function() {
			validateConfirmPassword();
		});
		$('#mobileRegister').on('focusout', function() {
			validateMobile();
		});
	</script>
</body>
</html>