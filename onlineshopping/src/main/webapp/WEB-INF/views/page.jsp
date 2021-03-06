<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Online Shopping Website Using Spring MVC and Hibernate">
<meta name="author" content="Mukesh Tatarri">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<title>Online Shopping - ${title}</title>
<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.css" rel="stylesheet">

<!-- Add custom CSS here -->
<link href="${css}/myapp.css" rel="stylesheet">

<!-- Bootstrap redable theme CSS -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- data table  CSS -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">
		<!-- navigation -->

		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content Start here  -->
		<div class="content">

			<!-- include home.jsp  -->
			<c:if test="${userClickHome == true}">
				<%@include file="home.jsp"%>
			</c:if>


			<!-- include about.jsp  only when user clicked about.jsp-->
			<c:if test="${userClickAbout == true}">
				<%@include file="about.jsp"%>
			</c:if>


			<!-- include contact.jsp  only when user clicked contact.jsp-->
			<c:if test="${userClickContact == true}">
				<%@include file="contact.jsp"%>
			</c:if>

			<!-- include listProducts.jsp  only when user clicked list products or categoryProducts-->
			<c:if
				test="${userClickAllProducts == true or userClickCategoryProducts == true}">
				<%@include file="listProducts.jsp"%>
			</c:if>

			<!-- include productDetail.jsp  only when user clicked list single product-->
			<c:if test="${userClickShowProduct == true}">
				<%@include file="productDetail.jsp"%>
			</c:if>

			<!-- include ManageProduct.jsp  only when user clicked list Manage product-->
			<c:if test="${userClickManageProducts == true}">
				<%@include file="manageProducts.jsp"%>
			</c:if>
			
			
			<!-- include ManageProduct.jsp  only when user clicked list Manage product-->
			<c:if test="${userClickedShowCart == true}">
				<%@include file="cart.jsp"%>
			</c:if>
			
			<!-- include places.jsp  only when user clicked list Travel -->
			<c:if test="${userClickTravel == true}">
					<%@include file="./travels/places.jsp"%>
			</c:if>
			
			<!-- include PlaceDetails.jsp  only when user clicked list Travel -->
			<c:if test="${UserClickedPlaceDetails == true}">
					<%@include file="./travels/placeDetails.jsp"%>
			</c:if>
			
			
		
		</div>

		<!-- Page Content end  here  -->


		<!-- footer comes here -->
		<%@include file="./shared/footer.jsp"%>

		<!-- JavaScript -->
		<script src="${js}/jquery.js"></script>
		<script src="${js}/bootstrap.js"></script>

		<!-- data tables js included -->
		<script src="${js}/jquery.dataTables.js"></script>

		<!-- data tables bootStrap js included -->
		<script src="${js}/dataTables.bootstrap.js"></script>
		
		<!-- boot box  JS file -->
		<script src="${js}/bootbox.min.js"></script>
		
		<!-- jquery validator  JS file -->
		<script src="${js}/jquery.validate.js"></script>	

		<!-- self coded JS file -->
		<script src="${js}/myapp.js"></script>	
		
	</div>
</body>

</html>
