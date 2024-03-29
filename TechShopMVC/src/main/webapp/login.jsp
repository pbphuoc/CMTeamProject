<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="global.GlobalConstant"%>
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
				<div class="col-md-12">
					<div class="formTitle">
						<h2>Login</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 m-auto">
					<div class="form_container formDiv">
						<form action="${GlobalConstant.AUTH_URL}" method="Post"
							class="needs-validation" novalidate>
							<input type="hidden" name="${GlobalConstant.COMMAND}"
								value="${GlobalConstant.LOGIN}"> <input type="hidden"
								name="${GlobalConstant.PREV_URL}"
								value="${param[GlobalConstant.PREV_URL]}">
							<div class="input-group mb-3">
								<c:choose>
									<c:when test="${error != null}">
										<input type="email"
											class="form-control ${GlobalConstant.IS_INVALID}"
											placeholder="Email Address"
											id="${GlobalConstant.EMAIL_LOGIN}"
											name="${GlobalConstant.EMAIL_LOGIN}"
											pattern="${GlobalConstant.EMAIL_PATTERN}" required>
										<div id="emailLoginFeedback" class="invalid-feedback">
											${error}</div>
									</c:when>
									<c:otherwise>
										<input type="email" class="form-control"
											placeholder="Email Address"
											id="${GlobalConstant.EMAIL_LOGIN}"
											name="${GlobalConstant.EMAIL_LOGIN}"
											pattern="${GlobalConstant.EMAIL_PATTERN}" required>
										<div id="emailLoginFeedback" class="invalid-feedback"></div>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="input-group mb-3">
								<input type="password" class="form-control"
									placeholder="Password" id="${GlobalConstant.PASSWORD_LOGIN}"
									name="${GlobalConstant.PASSWORD_LOGIN}" required>
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
							<button class="btn btn-secondary" type="button">Create
								an account</button>
						</a>

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
										validateEmail();
										validatePassword();

									}, false);
								});
					}, false);
		})();

		function validateEmail() {
			var emailFeedback = $('#emailLoginFeedback');
			var emailLogin = $('#emailLogin');
			var email = $('#emailLogin').val();
			var errorMsg = '';
			if (email == '') {
				errorMsg = 'Email Address cannot be blank';
			} else if (!isEmailFormatValid(email)) {
				errorMsg = 'Please provide a correct email address, e.g test@gmail.com';
			}
			setErrorMessage(emailLogin, emailFeedback, errorMsg);
		}
		function validatePassword() {
			var passwordFeedback = $('#passwordLoginFeedback');
			var passwordLogin = $('#passwordLogin');
			var password = $('#passwordLogin').val();
			var errorMsg = '';
			if (password == '') {
				errorMsg = 'Password cannot be blank';
			}
			setErrorMessage(passwordLogin, passwordFeedback, errorMsg);
		}
		$('#emailLogin').on('focusout', function() {
			validateEmail();
		});
		$('#passwordLogin').on('focusout', function() {
			validatePassword();
		});
	</script>
</body>
</html>