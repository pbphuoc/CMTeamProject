<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="global.OrderStatusEnum"%>
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
			<div class="comfirmationOrderName ">
				<c:if test="${order.orderStatus == OrderStatusEnum.RECEIVED}">
					<h1>THANK YOU</h1>
				</c:if>
				<h2>Order ${not empty order.orderNumber ? '#'+= order.orderNumber : ''}
					${order.orderStatus}</h2>
				<h3>${order.orderDate}</h3>
				<c:if test="${order.orderStatus == OrderStatusEnum.ERROR}">
					<h1>Sorry.. There have been some problem submitting your
						order. Paypal Transaction has been voided. Please contact
						admin@techila.com.au for further support</h1>
				</c:if>
			</div>

		</div>

		<div class="confirmationForm">
			<div class="reviewBox row col-xl-10">
				<div class="card-body pt-0 reviewUserDetail">
					<div>
						<h1 class="confirmationTitle">Contact information</h1>
						<div class="confirmationDetail">
							<p class="mb-0">
								<b>Email Address</b>
							</p>
							<input class="confirmationInput" name="checkOutEmail"
								value="${order.checkOutEmail}" readonly>
						</div>
						<c:if test="${not empty order.checkOutFullname}">
							<div class="confirmationDetail">
								<p class="mb-0">
									<b>Fullname</b>
								</p>
								<input class="confirmationInput" name="checkOutFullname"
									value="${order.checkOutFullname}" readonly>
							</div>
						</c:if>
						<c:if test="${not empty order.checkOutPhone}">
							<div class="confirmationDetail">
								<p class="mb-0">
									<b>Phone Number</b>
								</p>
								<input class="confirmationInput" name="checkOutFullname"
									value="${order.checkOutPhone}" readonly>
							</div>
						</c:if>

						<br>
						<h1 class="confirmationTitle">Receiving information</h1>
						<div class="confirmationDetail">
							<p class="mb-0">
								<b>Delivery Method</b>
							</p>
							<input class="mb-0 confirmationInput" name="receiveMethod"
								value="${order.receiveMethod}">
						</div>
					</div>
					<div>

						<div class="confirmationDetail">
							<p class="mb-0">
								<b>Recipient Full Name</b>
							</p>
							<input class="confirmationInput" name="receiverFullname"
								value="${order.receiverFullname}" readonly>
						</div>

						<div class="confirmationDetail">
							<p class="mb-0">
								<b>Recipient Phone Number</b>
							</p>
							<input class="confirmationInput" name="receiverPhone"
								value="${order.receiverPhone}" readonly>
						</div>
						<div class="confirmationDetail">
							<p class="mb-0">
								<b>Recipient Address</b>
							</p>
							<input class="confirmationInput" name="receiverAddress"
								value="${order.receiverAddress}" readonly>
						</div>
					</div>
					<div>
						<h1 class="confirmationTitle">Payment Information</h1>
						<div class="confirmationDetail">
							<p class="mb-0">
								<b>Payment Status</b>
							</p>
							<input class="mb-0 confirmationInput" name="paymentType"
								value="${order.paymentType}" readonly>
						</div>
						<div class="confirmationDetail">
							<c:if test="${not empty order.paymentDate}">
								<p class="mb-0">
									<b>Payment Date</b>
								</p>
								<input class="mb-0 confirmationInput" name="paymentDate"
									value="${order.paymentDate}" readonly>
							</c:if>
						</div>
					</div>
				</div>
				<div class="card-body pt-0 reviewOrderDetail">
					<h1 class="confirmationTitle">Detail</h1>
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
									<p class="mb-1 formattedPrice">${order.totalCost - order.shippingCost}</p>
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
									<p class="mb-1 shippingCost formattedPrice">
										<b>${order.shippingCost}</b>
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
									<p class="mb-1 formattedPrice">
										<b>${order.totalCost}</b>
									</p>
								</div>
							</div>
							<hr class="my-0">
						</div>
					</div>
					<div class="row mb-5 mt-4 ">
						<div class="col-md-7 col-lg-6 mx-auto">
							<c:if test="${order.orderStatus == OrderStatusEnum.RECEIVED}">
								<a href="Home"> <input
									class="btn btn-primary ml-0 submitOrder" type="button"
									value="Back To Home">
								</a>
							</c:if>
							<c:if test="${order.orderStatus == OrderStatusEnum.REVIEWING}">
								<form action="/Checkout" method="POST">
									<input type="hidden" name="paymentId"
										value="${param.paymentId}" /> <input type="hidden"
										name="PayerID" value="${param.PayerID}" /> <input
										type="hidden" name="command" value="submitOrder" /> <input
										class="btn btn-primary ml-0 submitOrder" type="submit"
										value="Place Order">
								</form>
							</c:if>
							<c:if test="${order.orderStatus == OrderStatusEnum.ERROR}">
								<a href="Home"> <input
									class="btn btn-primary ml-0 submitOrder" type="button"
									value="Finish">
								</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="allscript.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function() {
			formatPriceOnLoad();
		});
	</script>
</body>
</html>