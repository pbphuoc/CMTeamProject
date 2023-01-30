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
	<div class="container">
		<div class="projectContainer">
			<div class="row">
				<div class="col-md-12">
					<div class="formTitle">
						<h2>Track Order</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 m-auto">
					<div class="form_container formDiv">
						<form onsu action="Order" method="Post" class="needs-validation"
							novalidate>
							<input type="hidden" name="command" value="trackOrder">							
							<div class="input-group mb-3">
								<c:choose>
									<c:when
										test="${error != null}">
										<input type="email" class="form-control is-invalid"
											placeholder="Email Address" id="emailAddress" name="emailAddress"
											required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
										<div id="emailAddressFeedback" class="invalid-feedback">
											${error}</div>
									</c:when>
									<c:otherwise>
										<input type="email" class="form-control"
											placeholder="Email Address" id="emailAddress" name="emailAddress"
											required>
										<div id="emailAddressFeedback" class="invalid-feedback"></div>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="input-group mb-3">
								<input type="text" class="form-control"
									placeholder="Order Number" id="orderNumber" name="orderNumber"
									required>
								<div id="orderNumberFeedback" class="invalid-feedback"></div>
							</div>
							<button class="btn btn-info" type="submit" id="trackOrderBtn">Track Order</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>	
	<jsp:include page="allscript.jsp"></jsp:include>
	<script type="text/javascript">
	(function() {
		  'use strict';
		  window.addEventListener('load', function() {
		    // Fetch all the forms we want to apply custom Bootstrap validation styles to
		    var forms = document.getElementsByClassName('needs-validation');
		    // Loop over them and prevent submission
		    var validation = Array.prototype.filter.call(forms, function(form) {
		      form.addEventListener('submit', function(event) {
		        if (form.checkValidity() === false) {
		          event.preventDefault();
		          event.stopPropagation();
		        }	        
		        validateEmailAddress();
		        validateOrderNumber();
		        		        
		      }, false);
		    });
		  }, false);
		})();

	function validateEmailAddress(){
		var emailFeedback = $('#emailAddressFeedback');
		var emailAddress = $('#emailAddress');
		var emailAddressValue = $('#emailAddress').val();
		var errorMsg = '';
		if(emailAddressValue == ''){
			errorMsg = 'Email Address cannot be blank';
		}else if(!isEmailFormatValid(emailAddressValue)){
			errorMsg = 'Please provide a correct email address, e.g test@gmail.com';
		}
		setErrorMessage(emailAddress, emailFeedback, errorMsg);
	}
	function validateOrderNumber(){
		var orderNumberFeedback =  $('#orderNumberFeedback');
		var orderNumber =  $('#orderNumber');
		var orderNumberValue =  $('#orderNumber').val();
		var errorMsg = '';
		if(orderNumberValue == ''){
			errorMsg= 'Order Number cannot be blank';
		}
		setErrorMessage(orderNumber, orderNumberFeedback, errorMsg);
	}	
	$('#orderNumber').on('focusout', function(){
		validateEmailAddress();
	});
	$('#emailAddress').on('focusout', function(){
		validateOrderNumber();
	});	
	</script>		
</body>
</html>