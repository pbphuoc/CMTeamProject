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
<!-- body -->
<body class="main-layout">
	<jsp:include page="${GlobalConstant.HEADER_JSP}">
		<jsp:param name="curUrl"
			value="${requestScope['javax.servlet.forward.request_uri']}" />
	</jsp:include>
	<div id="cartContainer" class="container">
		<div class="row">
			<div class="col-xl-12">
				<div class="cart">
					<h1 class="cartTitle">
						My Shopping Cart <a href="Home" class="btn"> <i
							class="fa fa-shopping-cart " aria-hidden="true"></i> <span
							class="backToShop">Back To Shop</span>
						</a>
					</h1>
				</div>
			</div>
		</div>
		<div class="tableWrapper">
			<table class="tableInfor">
				<tr>
					<th class="tableProduct">Product</th>
					<th class="tableQuantity">Quantity</th>
					<th class="tableUnitPrice">Unit Price</th>
					<th class="tableTotal">Total</th>
				</tr>
			</table>
		</div>
		<div class="cartItemWrapper">
			<c:set var="totalCost"></c:set>
			<c:forEach var="item" items="${items}">
				<c:set var="totalCost"
					value="${totalCost + item.product.newPrice * item.quantity}"></c:set>
				<div class="itemTable">
					<div class="cartCol1">
						<div class="itemInfor">
							<div class="imageContainer">
								<a
									href="Product?command=viewProductDetail&productID=${item.product.id}">
									<img src="..<c:out value='${item.product.imgSrc}'/>">
								</a>
							</div>
							<div
								class='<c:if test="${item.product.stock == 0}">crossOutText</c:if>'>
								<a
									href="Product?command=viewProductDetail&productID=${item.product.id}">
									${item.product.name} </a>
							</div>

						</div>
						<div class="itemQuantity">
							<button
								onclick="increase(${item.product.id},${item.product.stock}, this)"
								class="increment"
								<c:if test="${item.product.stock == item.quantity}">disabled</c:if>>
								<i class="fa fa-plus"></i>
							</button>
							<span class="quantity"> <c:out value="${item.quantity}" />
							</span>
							<button class="decrement"
								onclick="decrease(${item.product.id},${item.product.stock}, this)">
								<i class="fa fa-minus"></i><span class="itemId" hidden><c:out
										value="${item.product.id}" /></span>
							</button>
						</div>
					</div>
					<div class="cartCol2">
						<div
							class="formattedPrice unitPrice <c:if test="${item.product.stock == 0}">crossOutText</c:if>">
							<c:out value='${item.product.newPrice}' />
						</div>
						<div class="totalPrice">
							<div class="itemPrice formattedPrice">
								${item.product.newPrice * item.quantity}</div>
							<div class="itemRemove">
								<button onclick="remove(${item.product.id})">
									<i class="fa fa-trash-o"></i> <span class="cartDelete"><span
										class="itemId" hidden><c:out value="${item.product.id}" /></span>
										Remove</span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<c:if test="${not empty items}">
			<div class="paymentWrapper">
				<div class="subTotal">
					<div class="summary">Subtotal</div>
					<p class="subTotalPrice formattedPrice">${totalCost}</p>
				</div>
				<div id="checkOutNow">
					<a href="Checkout?command=detail">
						<button>
							<i class="fa-solid fa-dollar-sign"></i>Check Out Now
						</button>
					</a>
				</div>
			</div>
		</c:if>

	</div>
	<jsp:include page="${GlobalConstant.FOOTER_JSP}"></jsp:include>
	<jsp:include page="${GlobalConstant.ALLSCRIPT_JSP}"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
				formatPriceOnLoad();
			}
		);
	</script>
</body>
</html>

