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
		<% double subTotal = 0; %>
		<c:forEach var="item" items="${cartItemDetails}">
			<div class="itemTable">
				<div class="cartCol1">
					<div class="itemInfor">
					<div class="imageContainer">
					<a href="Product?command=viewProductDetail&productID=${item.product.id}"> 
					<img src="..<c:out value='${item.product.imgSrc}'/>">
					</a>
					</div>
					<div>
					
					<a  href="Product?command=viewProductDetail&productID=${item.product.id}"> <c:out value="${item.product.name }"/> </a>
					</div>
						
						
					</div>
					<div class="itemQuantity">
						<button onclick="increase(${item.product.id})" class="increment">
							<i class="fa fa-plus"></i>
						</button>
						<span class="quantity">
							<c:out value="${item.quantity}"/>
						</span>
						<button class="decrement" onclick="decrease(${item.product.id})"><i class="fa fa-minus"></i><span class="itemId" hidden><c:out value="${item.product.id}"/></span></button>						
					</div>
				</div>
				<div class="cartCol2">
					<div class="unitPrice">
					<c:out value='${item.product.newPrice}'/>
					</div>
					<div class="totalPrice">
						<div class="itemPrice">
						<c:out value='${item.product.newPrice * item.quantity}'/> 
						</div>
						<div class="itemRemove">
							<button onclick="remove(${item.product.id})">
								<i class="fa fa-trash-o"></i> <span class="cartDelete"><span class="itemId" hidden><c:out value="${item.product.id}"/></span>  Remove</span>
							</button>
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
		<div class="paymentWrapper">
			<div class="subTotal">
				<div class="summary">Subtotal</div>
				<p class="subTotalPrice">
			
			</p>
			</div>
			<div class="checkOutNow">
				<a href="Checkout">
					<button>
						<i class="fa-solid fa-dollar-sign"></i>Check Out Now
					</button>
				</a>
			</div>
		</div>
		
	</div>
	<jsp:include page="footer.jsp"></jsp:include>	
	<jsp:include page="allscript.jsp"></jsp:include>	
	<script src="${pageContext.request.contextPath}/js/cart.js"></script>
</body>
</html>

