<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div class="header">
		<div class="container-fluid">
			<div class="row alignItemCenter">
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
								
								<c:choose>
									<c:when
										test="${sessionScope.user == null}">
										<li class="nav-item menuBarUserLi">										
											<jsp:include page="loginButton.jsp">
												<jsp:param name="curUrl" value="${param.curUrl}" />
											</jsp:include>
										</li>
									</c:when>
									<c:otherwise>
									  <li class="nav-item dropdown">
									    <a class="nav-link dropdown-toggle menuBarUsername" href="" id="menuBarUsernameDropdownLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									      Hi ${sessionScope.user.name}
									    </a>
									    <div class="dropdown-menu" aria-labelledby="menuBarUsernameDropdownLink">									      
									      <a class="dropdown-item" href="Account?command=viewOrders">Order History</a>
									      <a class="dropdown-item" href="Auth?command=logout&prevUrl=${param.curUrl}">Logout</a>
									    </div>
									  </li>											
									</c:otherwise>
								</c:choose>														
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</div>
</header>
