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
			<div id="checkoutLeft" class="col-md-7">
				<div class="row ">
					<div id="checkoutMember" class="col">
					<h1 >Payment</h1>
						<div class="row">
							<div class="icons">
								<img src="https://img.icons8.com/color/48/000000/visa.png" /> <img
									src="https://img.icons8.com/color/48/000000/mastercard-logo.png" />
								<img src="https://img.icons8.com/color/48/000000/maestro.png" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
							<div class="form_container" id="guestForm">
							<form class="paymentForm">
							
								<span>Cardholder's name:</span> 
								<input class="paymentBox" placeholder="Linda Williams"> 
								
								<span>Card Number:</span> 
								<input class="paymentBox" placeholder="0125 6780 4567 9909">
								<div class="row">
									<div class="col-4 cardSpan">
										<span>Expiry date:</span> <input placeholder="YY/MM">
									</div>
									<div class="col-4 cardSpan">
										<span>CVV:</span> <input placeholder="XYZ">
									</div>
								</div>
								<div class="form-row mb-3 saveCardLabelContainer">
								<input type="checkbox" id="save_card" class="align-left"> <label
									class="saveCardLabel" for="save_card">Save card details to wallet</label>
								</div>
								
								
								<!-- delivery option -->
								<div class="form-row mb-3 saveCardLabelContainer">
									<div id="deliveryOptions" class="btn-group btn-group-toggle"
										data-toggle="buttons">
										<label class="btn btn-secondary active mr-2"> <input
											id="optDeliveryLB" type="radio" name="options"
											autocomplete="off" checked> Delivery
										</label> <label class="btn btn-secondary"> <input
											id="optCollectLB" type="radio" name="options"
											autocomplete="off"> Collect At Store
										</label>
									</div>
								</div>
								<div class="form-row mb-3 saveCardLabelContainer">
									<textarea class="form-control" rows="5"
										placeholder="Delivery Address" aria-describedby="basic-addon1"
										id="guestAddress"></textarea>
								</div>

							</form>
							<div class="form-row mb-3 saveCardLabelContainer">
									<button class="paymentBtn btn btn-primary ml-0" type="submit">
										Paynow</button>
								</div>
						</div>
					</div>
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