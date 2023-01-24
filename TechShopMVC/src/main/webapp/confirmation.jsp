<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="util.Utility"%>
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
	<div class="confirmationContainer">
		<div class="shopInfor">
			<div class="logo comfirmationLogo ">
				<a href="Home"><img src="../images/logo.png" alt="#" /></a>
			</div>
			<c:choose>
				<c:when test="${order.orderNumber.equals('')}">
					<div class="comfirmationOrderName ">
						<h1>Review Order</h1>
					</div>
				</c:when>
				<c:otherwise>
					<div class="comfirmationOrderName ">
						<h1>Order Confirmed</h1>
						${order.paymentDate}
				
					</div>
				</c:otherwise>
			</c:choose>
		</div>

		<form class="confirmationForm" action="Order">
			<input type="hidden" name="command" value="submitOrder">
			<div class="reviewBox row col-xl-8">
			
				<div class="card-body pt-0 reviewUserDetail reviewDetail">
					<div>
						<h1 class="confirmationTitle">Contact information</h1>
						<div class="confirmationDetail">
							<p class="mb-0">
								<b>Email Address</b>
							</p>
							<input class="confirmationInput" name="checkOutEmail"
								value="${order.checkOutEmail}" readonly>
						</div>

						<br>
						<h1 class="confirmationTitle">Receiving information</h1>
						<div class="confirmationDetail">
							<p class="mb-0">
								<b>Delivery Method</b>
							</p>
							<input class="mb-0 confirmationInput" name="receiveMethod"value="${order.receiveMethod}">
						</div>
						<br>
					</div>
					<div>

						<div class="confirmationDetail">
							<p class="mb-0">
								<b>Receiver Full Name</b>
							</p>
							<input class="confirmationInput" name="receiverFullname"
								value="${order.receiverFullname}" readonly>
						</div>

						<div class="confirmationDetail">
							<p class="mb-0">
								<b>Receiver Phone Number</b>
							</p>
							<input class="confirmationInput" name="receiverPhone"
								value="${order.receiverPhone}" readonly>
						</div>

						<c:choose>
							<c:when test="${order.receiveMethod.equals('DELIVERY')}">
								<div class="confirmationDetail">
									<p class="mb-0">
										<b>Receiver Delivery Address</b>
									</p>
									<input class="confirmationInput" name="receiverAddress"
										value="${order.receiverAddress}" readonly>

								</div>
								<br>
							</c:when>
							<c:otherwise>
								<br>
							</c:otherwise>
						</c:choose>
					</div>
					<div>
						<h1 class="confirmationTitle">Payment Information</h1>
						<c:choose>
							<c:when test="${order.paymentType.equals('UNPAID')}">
								<div class="confirmationDetail">
									<p class="mb-0">
										<b>Payment Status</b>
									</p>
									<input class="mb-0 confirmationInput" name="paymentType" value="${order.paymentType}" readonly>
								</div>
								<br>
							</c:when>
							<c:otherwise>

								<div class="confirmationDetail">
									<p class="mb-0">
										<b>Payment Status</b>
									</p>
									<input class="mb-0 confirmationInput" name="paymentType" value="${order.paymentType}">
								</div>
								<div class="confirmationDetail">
									<p class="mb-0">
										<b>Cardholder's name</b>
									</p>
									<input class="mb-0 confirmationInput" name="cardHolderName" value="${order.paymentName}">
								</div>
								<div class="confirmationDetail">
									<p class="mb-0">
										<b>Card Number</b>
									</p>
									<input class="mb-0 confirmationInput" name="cardNumber" value="${order.paymentSource}">
								</div>
								<br>
								<h1 class="confirmationTitle">Billing Information</h1>

								<div class="confirmationDetail">
									<p class="mb-0">
										<b>Billing Fullname</b>
									</p>
									<input class="confirmationInput" name="billingFullname"
										value="${order.billingFullname}" readonly>
								</div>
								<div class="confirmationDetail">
									<p class="mb-0">
										<b>Billing Phone</b>
									</p>
									<input class="confirmationInput" name="billingPhone"
										value="${order.billingPhone}" readonly>
								</div>
								<div class="confirmationDetail">
									<p class="mb-0">
										<b>Billing Address</b>
									</p>
									<input class="confirmationInput" name="billingAddress"
										value="${order.billingAddress}" readonly>
								</div>
								<br>
								
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="card-body pt-0 reviewOrderDetail reviewDetail">
					<h1 class="confirmationTitle">Your Order ${order.orderNumber}</h1>
					<c:forEach var="item" items="${items}">
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
										<!-- <b>0</b> -->
									</p>
								</div>
							</div>
							<br>
							<div class="row justify-content-between">
								<div class="col-4">
									<p>
										<b>Shipping Cost</b>
									</p>
								</div>
								<div class="flex-sm-col col-auto">
									<p class="mb-1 shippingCost">
										<b>0</b>
									</p>
								</div>
							</div>
							<br>
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
							<c:choose>
								<c:when test="${order.orderNumber.equals('')}">		
									<input class="btn btn-primary ml-0 submitOrder" type="submit"
										value="Submit Order">
								</c:when>
								<c:otherwise>
									<a href="Home">
									<input class="btn btn-primary ml-0 submitOrder" type="text"
										value="Back To Home">					
									</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>

			</div>
		</form>
	</div>
	<jsp:include page="allscript.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/js/checkout.js"></script>
</body>
</html>