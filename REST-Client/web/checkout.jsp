<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta name="author" content="co-authored by Greg Zubatov, Swan Toma, Genesis Garcia">
	<meta name="description" content="ECommerce Website">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@1,500&amp;display=swap"
		rel="stylesheet">
	<link rel="stylesheet" href="./css/global.css">
	<link rel="stylesheet" href="./css/products.css">
	<script src="./js/products.js"></script>
	<title>Ants-R-Us</title>

</head>

<body>
	<%@ page language="java" %>
        
        	<!-- Start Session servlet which contains logic for session data  -->
	<jsp:include page="/Session" flush="true" />

	<!-- Navbar -->
	<jsp:include page="navbar.jsp" flush="true" />

        	<!-- Start CheckoutServlet which contains logic for Cart  -->
	<jsp:include page="/CheckoutServlet" flush="true" />

</body>

</html>