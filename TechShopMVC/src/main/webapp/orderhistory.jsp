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
<body class="main-layout">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container widerContainer">
		<div class="projectContainer">	
			<div class="row">
				<div class="col-md-12">
					<div class="formTitle">
						<h2>Order History</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 m-auto">
					<div class="table-responsive">
						<table class="table">
						  <thead>
						    <tr>
						      <th scope="col">Order</th>
						      <th scope="col">Date</th>
						      <th scope="col">Email</th>
						      <th scope="col">Receiver</th>
						      <th scope="col">Delivery</th>
						      <th scope="col">Payment</th>
						      <th scope="col">Status</th>
						      <th scope="col">Total</th>
						      <th scope="col">Detail</th>
						    </tr>
						  </thead>
						  <c:forEach items="${orderList}" var="order">
							  <tbody>
							    <tr>
							      <th scope="row">${order.orderNumber}</th>
							      <td>${order.orderDate}</td>
							      <td>${order.checkOutEmail}</td>
							      <td>${order.receiverFullname}</td>
							      <td>${order.receiveMethod}</td>
							      <td>${order.paymentType}</td>
							      <td>${order.orderStatus}</td>
							      <td class="formattedPrice">${order.totalCost}</td>
							      <td><a class="btn btn-info" href="Order?command=viewOrderDetail&emailAddress=${order.checkOutEmail}&orderNumber=${order.orderNumber}">View</a></td>
							    </tr>
							  </tbody>						  
						  </c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>	
	<jsp:include page="allscript.jsp"></jsp:include>
	<script type="text/javascript">
	</script>		
</body>
</html>