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
	<jsp:include page="header.jsp">
			<jsp:param name="curUrl" value="${requestScope['javax.servlet.forward.request_uri']}" />
	</jsp:include>
	<div id="checkoutBody" class="projectContainer">
		<div class="row">
			<div id="checkoutLeft" class="col-md-7">
				<c:if
					test="${sessionScope.user == null}">
					<div class="row mb-5">
						<div id="checkoutMember" class="col">
							<h1>Already A User?</h1>
							<jsp:include page="loginButton.jsp">
								<jsp:param name="curUrl"
									value="${requestScope['javax.servlet.forward.request_uri']}" />
							</jsp:include>
						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="col">
						<c:choose>
							<c:when
								test="${sessionScope.user == null}">
								<h1 id="checkOutLabel">Or Check Out As Guest</h1>
							</c:when>
							<c:otherwise>
								<h1 id="checkOutLabel">Member Checkout</h1>
							</c:otherwise>
						</c:choose>
						<div class="form_container" id="checkOutForm">
							<form action="Confirmation">
								<div class="form-row mb-3 emailContainer">
									<div class="col-md-12">
										<c:choose>
											<c:when
												test="${sessionScope.user == null}">
												<input type="email" name="email" class="form-control"
													placeholder="Email Address" aria-describedby="basic-addon1"
													id="checkOutEmail" required>
											</c:when>
											<c:otherwise>
												<input type="email" name="email" class="form-control"
													placeholder="Email Address" aria-describedby="basic-addon1"
													id="checkOutEmail" value="${sessionScope.user.email}" required readonly>

											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="form-row mb-3">								
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
								<div id="deliveryBox" class="row">
									<div class="col">
										
											<h1>Delivery information</h1>
										
										<div class="form-row mb-3">
											<div class="form-group col-md-6">
												<input type="text" name="fname" class="form-control"
													placeholder="First Name" aria-describedby="basic-addon1"
													id="checkOutFirstName" required>
											</div>
											<div class="form-group col-md-6">
												<input type="text" name="lname" class="form-control"
													placeholder="Last Name" aria-describedby="basic-addon1"
													id="checkOutLastName" required>
											</div>
										</div>
										<div class="form-row mb-3">
											<div class="col-md-1.1">
												<select id="countryCodeList" class="form-select">
													<option value="australia" selected >+61</option>
												</select>
											</div>
											<div class="col-md-12">
												<input type="text" name="phone" class="form-control"
													placeholder="Phone Number" aria-describedby="basic-addon1"
													id="checkOutPhoneNumber" required>
											</div>
											<br>
											<br>
											<div class="form-group col-md-12">
												<input type="text" name="address" class="form-control"
													placeholder="Delivery Address" aria-describedby="basic-addon1"
													id="checkOutAddress" required>
											</div>
										</div>
									</div>

								</div>



								<div id="guestAddress" class="row">
									<div class="col">
										<h1>Payment</h1>
										<div class="form-row mb-3 saveCardLabelContainer">
											<div id="paymentOptions" class="btn-group btn-group-toggle"
												data-toggle="buttons">
												<label class="btn btn-secondary active mr-2"> <input
													id="payNowBtn" type="radio" name="options"
													autocomplete="off" checked> Pay now
												</label> <label class="btn btn-secondary"> <input
													id="payOnPickupBtn" type="radio" name="options"
													autocomplete="off"> Pay later
												</label>
											</div>
										</div>
									</div>
								</div>
			
								<div id="creditCardField" class="row">
									<div class="col">
									<h1>Billing Information</h1>
										<div class="form-row mb-3">
											
											<div class="form-group col-md-6">
												<input type="text" name="billingFname" class="form-control"
													placeholder="First Name" aria-describedby="basic-addon1"
													id="billingFname" required>
											</div>
											<div class="form-group col-md-6">
												<input type="text" name="billingLname" class="form-control"
													placeholder="Last Name" aria-describedby="basic-addon1"
													id="billingLname" required>
											</div>
											<div class="form-group col-md-6">
												<input type="text" name="billingPhone" class="form-control"
													placeholder="Phone number" aria-describedby="basic-addon1"
													id="billingPhone" required>
											</div>
											<div class="form-group col-md-12">
												<input type="text" name="billingAddress" class="form-control"
													placeholder="Address" aria-describedby="basic-addon1"
													id="billingAddress" required>
											</div>

										</div>
										
									
										<div class="form-row mb-3 icons">
											<img src="https://img.icons8.com/color/48/000000/visa.png" />
											<img
												src="https://img.icons8.com/color/48/000000/mastercard-logo.png" />
											<img src="https://img.icons8.com/color/48/000000/maestro.png" />
										</div>
										<div id="paymentForm">
											
											<!-- payment option -->
											<span>Cardholder's name:</span> <input type="text" class="paymentBox"
												id="cardHolderName" name="cardHolderName" placeholder="Linda Williams" required> <span>Card
												Number:</span> <input type="number" class="paymentBox"
												id="cardNumber" name="cardNumber" placeholder="0125 6780 4567 9909" required>
											<div class="row">
												<div class="col-4 cardSpan">
													<span>Expiry date:</span> <input type="date" placeholder="YY/MM" id="expiredDate" name="expiredDate" required>
												</div>
												<div class="col-4 cardSpan">
													<span>CVV:</span> <input type="number" placeholder="XYZ" id="cvvNumber" name="cvvNumber" required>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-row mb-3">
									<input class="btn btn-primary ml-0" type="submit" value="Review Order">
								</div>
				
							</form>
							
						</div>
					</div>
				</div>
			</div>



			<div id="checkoutRight" class="col-md-5">
				<h1>Order Detail</h1>
				<div class="card-header card-1">
					<p class="card-text text-muted mt-md-4  mb-2 space">
						<a href="Cart"> <span
							class=" small text-muted ml-2 cursor-pointer">EDIT
								SHOPPING BAG</span>
						</a>
					</p>

				</div>
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