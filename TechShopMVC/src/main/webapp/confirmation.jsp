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
	<div class="confirmationContainer">
		<div class="reviewBox row col-xl-8">
			<div class="card-body pt-0 reviewUserDetail reviewDetail">
			<h1 class="confirmationTitle">Your information</h1>
				<div class="confirmationDetail">
					<h1>Email Address</h1>
					<h2>${param.email}</h2>
				</div>
				<div class="confirmationDetail">
					<h1>First Name</h1>
					<h1>${param.fname}</h1>
				</div>
				<div class="confirmationDetail">
					<h1>Last Name</h1>
					<h1>${param.lname}</h1>
				</div>
				<div class="confirmationDetail">
					<h1>Phone Number</h1>
					<h1>${param.phone}</h1>
				</div>
				<br>
				<c:choose>
					<c:when test="${param.billingFname == null || param.billingFname == ''}">
						<br>
					</c:when>
					<c:otherwise>
						<h1 class="confirmationTitle">Your Billing Information</h1>
						<div class="confirmationDetail">
							<h1>First Name</h1>
							<h2>${param.billingFname}</h2>
						</div>
						<div class="confirmationDetail">
							<h1>First Name</h1>
							<h1>${param.billingLname}</h1>
						</div>
						<div class="confirmationDetail">
							<h1 >Billing Address</h1>
							<h1>${param.billingAddress}</h1>
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
						<input class="btn btn-primary ml-0 submitOrder" type="submit"
							value="Submit Order">
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="allscript.jsp"></jsp:include>	
	<script src="${pageContext.request.contextPath}/js/checkout.js"></script>
</body>
</html>