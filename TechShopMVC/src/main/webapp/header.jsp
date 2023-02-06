<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div class="header">
		<div class="container-fluid">
			<div class="row alignItemCenter" onmouseleave="mouseOffHelp()">
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
				<div class="col-xl col-lg col-md col-sm">
					<nav class="navigation navbar navbar-expand-xl navbar-dark ">
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarsExample04"
							aria-controls="navbarsExample04" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarsExample04">
							<ul class="navbar-nav mr-auto">
								<li id="helpLi" class="nav-item dropdown" onmouseenter="mouseOverHelp()">
									  <a class="nav-link dropdown-toggle" id="menuBarHelpDropdownLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										Help
									 </a>
									 <div class="dropdown-menu" id="trackOrderDiv" aria-labelledby="menuBarHelpDropdownLink">									      
									   <a class="dropdown-item" href="Order?command=getTrackOrderForm">Track Order</a>									   
									  </div>
								</li>									
								<li class="nav-item d_none cartBtnLi"><a class="nav-link"
									href="Cart?command=viewCart"><i class="fa fa-shopping-cart"
										aria-hidden="true"></i><span id="cartSize">${not empty sessionScope.cartItems ? '(' += sessionScope.cartItems.size() += ')' : ''}</span></a></li>
								
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
									    <a class="nav-link dropdown-toggle" href="" id="menuBarUsernameDropdownLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									      Hi ${sessionScope.user.fullname}
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
<<script type="text/javascript">
	function mouseOverHelp(){
		document.getElementById("helpLi").classList.add("show");
		document.getElementById("trackOrderDiv").classList.add("show");
		document.getElementById("menuBarHelpDropdownLink").setAttribute("aria-expanded", true);
	}
	function mouseOffHelp(){
		document.getElementById("helpLi").classList.remove("show");
		document.getElementById("trackOrderDiv").classList.remove("show");
		document.getElementById("menuBarHelpDropdownLink").setAttribute("aria-expanded", false);
	}
</script>
