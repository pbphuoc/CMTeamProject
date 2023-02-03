<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="constant.OrderReceiveMethodEnum"%>
<%@page import="constant.OrderPaymentTypeEnum"%>
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
<script src="${GlobalConstant.PAYPAL_API}" data-page-type="checkout"></script>
<jsp:include page="${GlobalConstant.ALLREF_JSP}"></jsp:include>
</head>
<!-- body -->
<body class="main-layout">
	<jsp:include page="${GlobalConstant.HEADER_JSP}">
		<jsp:param name="curUrl"
			value="${requestScope['javax.servlet.forward.request_uri']}" />
	</jsp:include>
	<div id="checkoutBody" class="projectContainer">
		<div class="row">
			<div id="checkoutLeft" class="col-md-4">
				<c:if test="${sessionScope.user == null}">
					<div class="row mb-5">
						<div id="checkoutMember" class="col">
							<h1>Are you a member?</h1>
							<jsp:include page="${GlobalConstant.LOGIN_BUTTON_JSP}">
								<jsp:param name="curUrl"
									value="${requestScope['javax.servlet.forward.request_uri']}" />
							</jsp:include>
						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="col">
						<div class="form_container" id="checkOutForm">
							<div class="form-row mb-3">
								<div class="col">
									<h1>Payment</h1>
									<div id="paypal-button-container"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="checkoutRight" class="col-md-8">
				<h1>Order Detail</h1>
				<div class="card-header card-1">
					<p class="card-text text-muted mt-md-4  mb-2 space">
						<a href="Cart"> <span
							class=" small text-muted ml-2 cursor-pointer">EDIT
								SHOPPING CART</span>
						</a>
					</p>
				</div>
				<div class="card-body pt-0">
					<c:set var="totalCost"></c:set>
					<c:forEach var="item" items="${items}">
						<c:set var="totalCost"
							value="${totalCost + item.product.newPrice * item.quantity}"></c:set>
						<div class="cartItemRow row justify-content-between">
							<div class="col-auto col-md-7">
								<div class="media flex-column flex-sm-row">
									<img class="img-fluid"
										src="..<c:out value='${item.product.imgSrc}'/>">
									<p class="mb-0">
										<b><c:out value="${item.product.name}" /></b>
									</p>
								</div>
							</div>
							<div class=" pl-0 flex-sm-col col-auto  my-auto">
								<p class="boxed-1">
									<c:out value="${item.quantity}" />
								</p>
							</div>
							<div class=" pl-0 flex-sm-col col-auto  my-auto ">
								<p class="formattedPrice itemPrice">
									<b><c:out value="${item.product.newPrice * item.quantity }" /></b>
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
									<p class="mb-1 formattedPrice">
										<b>${totalCost}</b>
										<!-- 										<input id="subtotal" name="subtotal" value="0"> -->
									</p>
								</div>
							</div>
							<div class="row justify-content-between">
								<div class="col-4">
									<p>
										<b>Shipping Cost</b>
									</p>
								</div>
								<div class="flex-sm-col col-auto">
									<p id="shippingCost" class="mb-1 formattedPrice">
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
									<p class="mb-1 formattedPrice" id="totalCost">
										<b>${totalCost}</b>
									</p>
								</div>
								<!-- 								<input id="total" name="total" value="0" hidden="true"> -->
							</div>
							<hr class="my-0">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="${GlobalConstant.ALLSCRIPT_JSP}"></jsp:include>
	<script
		src="${pageContext.request.contextPath}/js/paypal/paypal-api.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			formatPriceOnLoad();
		});

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
									}, false);
								});
					}, false);
		})();

		function validateEmail() {
			var emailFeedback = $('#checkOutEmailFeedback');
			var emailLogin = $('#checkOutEmail');
			var email = $('#checkOutEmail').val();
			var errorMsg = '';
			if (!isEmailFormatValid(email)) {
				errorMsg = 'Please provide a correct email address, e.g test@gmail.com';
			}
			setErrorMessage(emailLogin, emailFeedback, errorMsg);
		}
		$('#checkOutEmail').on('focusout', function() {
			validateEmail();
		});
	</script>
</body>
</html>