<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div class="header">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xl col-lg col-md col-sm ">
					<div class="navbar-brand">
						<div class="center-desk" class="d-inline-flex">
							<div class="logo">
								<a href="Home"><img src="../images/logo.png" alt="#" /></a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6">
					<div class="search_bar ">
						<div class="form_container">
							<input id="searchBar" class="form-control me-2" type="search"
								placeholder="Search" onkeyup="searchProduct(this)">
						</div>
					</div>
				</div>
				<div class="col-xl col-lg col-md col-sm ">
					<nav class="navigation navbar navbar-expand-xl navbar-dark ">
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarsExample04"
							aria-controls="navbarsExample04" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarsExample04">
							<ul class="navbar-nav mr-auto">
								<li class="nav-item "><a class="nav-link" href="Home">Home</a></li>

								<!-- 									<li class="nav-item"><a class="nav-link"
										href="product.html">Products</a></li> -->

								<li class="nav-item d_none cartBtnLi"><a class="nav-link"
									href="Cart?command=viewCart"><i class="fa fa-shopping-cart"
										aria-hidden="true"></i></a></li>
								<li class="nav-item menuBarUserLi">
									<c:choose>
										<c:when
											test="${sessionScope.userfullname == null || sessionScope.userfullname == ''}">
											<h3 class="menuBarUsername"></h3>
											<jsp:include page="loginButton.jsp">
												<jsp:param name="curUrl" value="${param.curUrl}" />
											</jsp:include>
										</c:when>
										<c:otherwise>
											<h3 class="menuBarUsername">Hi
												${sessionScope.userfullname},</h3>
<%-- 											<a class="nav-link menuBarLoginBtn" href="Logout?prevUrl=${param.curUrl}">Logout</a> --%>
											<a class="nav-link menuBarLoginBtn" href="Auth?command=logout&prevUrl=${param.curUrl}">Logout</a>										
										</c:otherwise>
									</c:choose>
								</li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</div>
</header>
