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
	<div class="confirmationContainer">
		<form class="confirmationForm" action="Order">
		
			<div class="reviewBox row col-xl-8">
				<div class="card-body pt-0 reviewUserDetail reviewDetail">
					<h1 class="confirmationTitle">Your information</h1>
					<div class="confirmationDetail">
						<h1>Email Address</h1>
						<input name="checkOutEmail" value="${param.email}" readonly>
					</div>
					<c:choose>
						<c:when test="${param.address == null || param.address == ''}">
							<br>
							<h1>Collect At Store</h1>
							<div class="confirmationDetail">
								<h1>Full Name</h1>
								<input name="checkOutFullname"
									value="${param.fname} ${param.lname}" readonly>
							</div>

							<div class="confirmationDetail">
								<h1>Phone Number</h1>
								<input name="checkOutPhone" value="${param.phone}" readonly>
							</div>
						</c:when>
						<c:otherwise>
							<br>
							<h1>Delivery</h1>
							<div class="confirmationDetail">
								<h1>Full Name</h1>
								<input name="checkOutFullname"
									value="${param.fname} ${param.lname}" readonly>
							</div>
							<div class="confirmationDetail">
								<h1>Phone Number</h1>
								<input name="checkOutPhone" value="${param.phone}" readonly>
							</div>
							<div class="confirmationDetail">
								<h1>Delivery Address</h1>
								<input name="checkOutPhone" value="${param.phone}" readonly>
							</div>
						</c:otherwise>
					</c:choose>

					<h1>Pay With Card</h1>
					<h1 class="confirmationTitle">Receiver Information</h1>
					<div class="confirmationDetail">
						<h1>Receiver Full Name</h1>
						<input name="receiverFullname"
							value="${param.billingFname} ${param.billingLname}" readonly>
					</div>
					<div class="confirmationDetail">
						<h1>Receiver Phone</h1>
						<input name="receiverPhone" value="${param.billingPhone}" readonly>
					</div>
					<div class="confirmationDetail">
						<h1>Receiver Address</h1>
						<input name="receiverAddress" value="${param.billingAddress}"
							readonly>
					</div>
					<br> <br>
					<c:choose>
						<c:when
							test="${param.cardHolderName == null || param.cardHolderName == ''}">
							<br>
							<h1>Pay Later</h1>
						</c:when>
						<c:otherwise>

							<h1>Payment Card</h1>
							<div class="confirmationDetail">
								<h1>Cardholder's name</h1>
								<h2>${param.cardHolderName}</h2>
							</div>
							<div class="confirmationDetail">
								<h1>Card Number</h1>
								<h1>${param.cardNumber}</h1>
							</div>
							<div class="confirmationDetail">
								<h1>Expiry date</h1>
								<h1>${param.expiredDate}</h1>
							</div>
							<div class="confirmationDetail">
								<h1>CVV</h1>
								<h1>${param.cvvNumber}</h1>
							</div>

						</c:otherwise>
					</c:choose>
				</div>

				<div class="card-body pt-0 reviewOrderDetail reviewDetail">
					<h1 class="confirmationTitle">Your Order</h1>
					<c:forEach var="item" items="${CartItemDetails}">
						<div class="cartItemRow row justify-content-between">

							<div class="col-auto col-md-7">
								<div class="media flex-column flex-sm-row">
									<%-- <img class="img-fluid"
									src="..<c:out value='${item.product.imgSrc}'/>"> --%>
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
										<b>Shipping Cost</b>
									</p>
								</div>
								<div class="flex-sm-col col-auto">
									<p class="mb-1 shippingCost">
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
							<input type="hidden" name="command" value=submitOrder>
							<input class="btn btn-primary ml-0 submitOrder" type="submit" value="Submit Order">
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