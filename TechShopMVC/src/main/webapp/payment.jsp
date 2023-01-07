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
<!-- body -->
<body class="main-layout">
	<jsp:include page="header.jsp"></jsp:include>
	<div id="checkoutBody" class="projectContainer">
	
	<!-- payment Card -->
		<div class="row">
			<div id="paymentForm" class="col-md-7">
				<div class="row ">
					<div class="col">
						<h1>Payment</h1>
						<div class="form-row mb-3 saveCardLabelContainer">
							<div id="paymentOptions" class="btn-group btn-group-toggle"
								data-toggle="buttons">
								<label class="btn btn-secondary active mr-2"> <input
									id="payNowBtn" type="radio" name="options" autocomplete="off"
									checked> Pay now
								</label> <label class="btn btn-secondary"> <input
									id="payOnPickupBtn" type="radio" name="options"
									autocomplete="off"> Pay on pickup
								</label>
							</div>
						</div>
					</div>
				</div>
				<div id="creditCardField" class="row">
					<div class="col">
						<div class="form-row mb-3 icons">
							<img src="https://img.icons8.com/color/48/000000/visa.png" /> <img
								src="https://img.icons8.com/color/48/000000/mastercard-logo.png" />
							<img src="https://img.icons8.com/color/48/000000/maestro.png" />
						</div>
						<div class="form_container" id="guestForm">
							<!-- payment option -->
							<form class="paymentForm">

								<!-- payment option -->
								<span>Cardholder's name:</span> <input class="paymentBox"
									placeholder="Linda Williams"> <span>Card Number:</span>
								<input class="paymentBox" placeholder="0125 6780 4567 9909">
								<div class="row">
									<div class="col-4 cardSpan">
										<span>Expiry date:</span> <input placeholder="YY/MM">
									</div>
									<div class="col-4 cardSpan">
										<span>CVV:</span> <input placeholder="XYZ">
									</div>
								</div>
							</form>
							
						</div>
					</div>
				</div>
				<div class="form-row mb-3 saveCardLabelContainer">
					<button class="paymentBtn btn btn-primary ml-0" type="submit">
						Proceed</button>
				</div>
			</div>

			<!-- payment Card -->
			
			<div id="checkoutRight" class="col-md-5">
				<h1>Order Detail</h1>

				<div class="card-body pt-0">


					<c:forEach var="item" items="${CartItemDetails}">
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
								<p class="rowPrice">
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
	
	<jsp:include page="allscript.jsp"></jsp:include>	
	<script src="${pageContext.request.contextPath}/js/checkout.js"></script>

</body>
</html>