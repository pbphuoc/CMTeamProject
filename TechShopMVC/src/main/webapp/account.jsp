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
						      <th scope="col">#</th>
						      <th scope="col">First</th>
						      <th scope="col">Last</th>
						      <th scope="col">Handle</th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr>
						      <th scope="row">1</th>
						      <td>Mark</td>
						      <td>Otto</td>
						      <td>@mdo</td>
						    </tr>
						    <tr>
						      <th scope="row">2</th>
						      <td>Jacob</td>
						      <td>Thornton</td>
						      <td>@fat</td>
						    </tr>
						    <tr>
						      <th scope="row">3</th>
						      <td>Larry</td>
						      <td>the Bird</td>
						      <td>@twitter</td>
						    </tr>
						  </tbody>
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