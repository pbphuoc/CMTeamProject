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
<!-- fevicon -->
<link rel="icon" href="../images/logo.png" type="image/gif" />
<!-- Tweaks for older IEs-->
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
	media="screen">
<!-- Responsive-->
<link rel="stylesheet" href="../css/responsive.css">	
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet" href="../css/jquery.mCustomScrollbar.min.css">	
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />	
<link rel="stylesheet" href="../css/style.css">	
</head>
<!-- body -->
<body class="main-layout">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="projectContainer">
		<div id="mainBodySection" class="row">
			<div id="filterSection" class="col-md-2">
				<div class="row filter">
					<div class="col-md-12">
						<h2>Brand</h2>
						<select id="brandSelect" multiple data-live-search="true"
							title="Filter By Brand" class="queryFilter">
							<c:forEach var="entry" items="${brandFilters.entrySet()}">
								<option value="${entry.getKey()}"
									${entry.getValue().getSelected()}>
									${entry.getValue().getName()}(${entry.getValue().getStock()})
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row filter">
					<div class="col-md-12">
						<h2>Category</h2>
						<select id="categorySelect" multiple data-live-search="true"
							title="Filter By Category" class="queryFilter">
							<c:forEach var="entry" items="${categoryFilters.entrySet()}">
								<option value="${entry.getKey()}"
									${entry.getValue().getSelected()}>
									${entry.getValue().getName()}(${entry.getValue().getStock()})
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row filter">
					<div class="col-md-12">
						<h2>Availability</h2>
						<select id="availabilitySelect" multiple data-live-search="true"
							title="Filter By Availability" class="queryFilter">
							<c:forEach var="entry" items="${availabilityFilters.entrySet()}">
								<option value="${entry.getKey()}"
									${entry.getValue().getSelected()}>
									${entry.getValue().getName()}(${entry.getValue().getStock()})
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row filter">
					<div class="col-md-12">
						<h2>Price</h2>
						<div class="row">
							<input type="number" class="form-control" id="priceFrom"
								placeholder="From" value="${priceMin}">
						</div>
						<div class="row mt-2">
							<input type="number" class="form-control" id="priceTo"
								placeholder="To" value="${priceMax}">
						</div>
						<div class="row mt-3">
							<button id="priceFilterBtn" onclick="searchProductWithFilters()">Apply</button>
						</div>
					</div>
				</div>				
			</div>
			<div class="col-md-9">
				<div class="row">
					<div class="col-md-12">
						<div id="numberOfResult">
							<p>${totalResult} result(s) matched  <span id="searchKeyword">${keyword}</span></p>							
							<span class="searchParam">
								<c:forEach var="entry" items="${brandFilters.entrySet()}">
									<c:if
										test='${!entry.getValue().getSelected().equalsIgnoreCase("")}'>
										${entry.getValue().getName()} 
									</c:if>
								</c:forEach>
							</span>
							<span class="searchParam">
								<c:forEach var="entry" items="${categoryFilters.entrySet()}">
									<c:if
										test='${!entry.getValue().getSelected().equalsIgnoreCase("")}'>
										${entry.getValue().getName()} 
									</c:if>
								</c:forEach>
							</span>
							<span class="searchParam">
								<c:forEach var="entry" items="${availabilityFilters.entrySet()}">
									<c:if
										test='${!entry.getValue().getSelected().equalsIgnoreCase("")}'>
										${entry.getValue().getName()} 
									</c:if>
								</c:forEach>
							</span>
							<span class="searchParam">
								<c:if test="${!priceMin.equals('')}">Min  $${priceMin} </c:if>
								<c:if test="${!priceMax.equals('')}">Max  $${priceMax}</c:if>
							</span>																														
						</div>
					</div>
				</div>
				<div class="row floatRight">	
					<div class="col-md-6">
						<h2>Results Per Page</h2>
						<div id="maxPerPageButtonGroup" class="btn-group" role="group" data-toggle="buttons-radio">
						<c:forEach var="entry" items="${resultPerPage.entrySet()}">
						  <button type="button" class="btn btn-default maxPerPageButton ${entry.getValue()}" value="${entry.getKey()}">${entry.getKey()}</button>							
						</c:forEach>						 
						</div>						
					</div>									
					<div class="col-md-6">
						<h2>Sort By</h2>											
						<select id="sorter" class="form-select queryFilter"
							aria-label="Default select example">
							<c:forEach var="entry" items="${sorters.entrySet()}">
								<option value="${entry.getKey()}" ${entry.getValue()}>
								${entry.getKey()}
								</option>
							</c:forEach>							
						</select>
					</div>				
				</div>
				<div id="productSection" class="row">
					<div class="col-md-12">
						<div class="row our_products">
							<c:forEach var="product" items="${products}">
								<div class="col-md-4">
									<div class="product_box productOverlayCover">
										<div class="productOverlay" onclick="productOverlayOff()">
											<p>Item added!</p>
										</div>									
										<form>
											<div class="productThumbnailContainer">
												<a
													href="Product?command=viewProductDetail&productID=${product.id}">
													<img class="productThumbnail" src='..${product.imgSrc}' />
												</a>
											</div>
											<p class="productDescription">
												<c:out value="${product.name}"></c:out>
											</p>
											<h3 class="oldPrice">
												<c:if test="${product.oldPrice > product.newPrice}">
													<c:out value="$ ${product.oldPrice}"></c:out>
												</c:if>
											</h3>
											<h3 class="newPrice">
												<c:out value="$ ${product.newPrice }"></c:out>
											</h3>
											<div class="row productButtonSection">
												<div class="col-md-5">
													<a class="productButton" type="button"
														href="Product?command=viewProductDetail&productID=${product.id}">View</a>
												</div>
												<div class="col-md-5">
													<c:choose>
														<c:when test="${product.getStock() > 0}">
															<button class="productButton addbutton" type="button"
																onclick="increase(${product.id})">Add To Cart</button>
														</c:when>
														<c:otherwise>
															<button class="productButton" type="button" disabled>${product.getStockStatusDescription()}</button>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</form>
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="row floatRight">	
							<div class="col-md-12">
								<h2 class="inlineHeading">Page</h2>
								<div id="pageButtonGroup" class="btn-group" role="group" data-toggle="buttons-radio">
									<c:forEach var="entry" items="${pagingMap.entrySet()}">
									  <button type="button" class="btn btn-default pageButton ${entry.getValue()}" value="${entry.getKey()}">${entry.getKey()}</button>							
									</c:forEach>								  
								</div>						
							</div>													
						</div>							
					</div>
				</div>			
			</div>			
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>	
	<script src="../js/jquery.min.js"></script>
	<script src="../js/popper.min.js"></script>
	<script src="../js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="../js/custom.js"></script>
	<script src="../js/projectJS.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$('select').selectpicker();
		});
		$('.maxPerPageButton').on('click',function(){		
			$('#maxPerPageButtonGroup > .btn').removeClass('active');
			$('#maxPerPageButtonGroup > .btn').removeClass('selected');			
			$(this).addClass('selected');			
			$('#pageButtonGroup > .btn').removeClass('selected');
			$('#pageButtonGroup > .btn').removeClass('active');
			$('#pageButtonGroup button[value="1"]').addClass('selected');
			$('#pageButtonGroup button[value="1"]').addClass('active');
			searchProductWithFilters();			
		});	
		$('.pageButton').on('click',function(){					
			$('#pageButtonGroup > .btn').removeClass('selected');
			$('#pageButtonGroup > .btn').removeClass('active');
			$(this).addClass('selected');			
			searchProductWithFilters();
		});			
	</script>		
</body>
</html>