<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 <%@page import="model.SearchFilterDTO"%>   
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
	<div id="product-container" class="projectContainer">
		<div class="row ">
			<div class="col-md-6 ecommerce-gallery" data-mdb-zoom-effect="true"
				data-mdb-auto-height="true">
				<div class="row" id="productImageDiv">
					<c:if test="${medias.size() > 0}">
						<div class="col-12">
							<div class="lightbox productImageContainer">
								<img id="imageViewer" src="..${medias[0]}" alt="Gallery image 1"
									class="active" />
							</div>
						</div>
						<c:forEach var="media" items="${medias}">
							<div class="col-3 mb-1">
								<img src="..${media}" class="active imgViewerItem"
									onclick="changeProductImage(this.src)" />
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<div class="col-md-6 productSection ">
				<div>
					<h2 class="product-name" id="productName">
						<c:out value="${product.name}"></c:out>
					</h2>
				</div>
				<div class="priceBox">
					<span class="oldPrice" id="productOldPrice">
						<c:if test="${product.oldPrice > product.newPrice}">
							<c:out value="$ ${product.oldPrice}"></c:out>
						</c:if>						
					</span>
				</div>				
				<div class="priceBox">
					<span class="newPrice" id="productnewPrice">
						<c:out value="$ ${product.newPrice }"></c:out>
					</span>
				</div>
				<div class="addToCart-container">
					<c:choose>
						<c:when test="${product.getStock() > 0}">
							<button type="button" class="addToCart-button" onclick="increase(${product.id})">
								<i id="iconCart" class="fa fa-shopping-cart"></i> <span
									class="addToCart-text">Add to cart</span>
							</button>													
						</c:when>
						<c:otherwise>
							<button type="button" class="addToCart-button" disabled>
								<span class="addToCart-text">${SearchFilterDTO.AVAILABILITY_MAP.get(product.getStockStatus())}</span>
							</button>
						</c:otherwise>
					</c:choose>											
				</div>
				<div class="product-desction">
					<h2 class="keyFeature">Key features</h2>
					<ul class="featureList" id="productDescription">
						<c:out value="$ ${product.description }"></c:out>					
					</ul>
				</div>

			</div>
		</div>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>	
	<jsp:include page="allscript.jsp"></jsp:include>
</body>
</html>

