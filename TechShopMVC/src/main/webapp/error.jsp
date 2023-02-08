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
<title>${errorName}</title>
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
						<h2>${errorCode}</h2>
						<h1>Oopsss! Sorry.. Something has just gone wrong..</h1>
						<h3>${errorMessage}</h3>
						<img src="../images/error.jpg">
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="allscript.jsp"></jsp:include>	
</body>
</html>