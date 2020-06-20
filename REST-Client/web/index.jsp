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
	<link rel="stylesheet" href="./css/index.css">
	<title>Ants-R-Us</title>

</head>

<body>

	<%@ page language="java" %>
	<jsp:include page="/Session" flush="true" />

	<!-- Navbar -->

	<jsp:include page="navbar.jsp" flush="true" />


	<!-- Jumbotron -->
	<div class="jumbotron">
		<div>
			<h1>Arts & Crafts</h1>
		</div>
	</div>

	<!--Main Content-->
	<div id="main">
		<!--Filters-->
		<div class="filterblock">

			<div class="filter">
				<h3>Filter by Categories</h3>
				<div class="checkbox">
					<input type="checkbox" id="floral" name="filter"><label for="floral"><span>Floral</span></label>
				</div>
				<div class="checkbox">
					<input type="checkbox" id="crafts-hobbies" name="filter"><label for="crafts-hobbies"><span>Crafts
							and Hobbies</span></label>
				</div>
				<div class="checkbox">
					<input type="checkbox" id="home-office" name="filter"><label for="home-office"><span>Home
							Office</span></label>
				</div>
				<div class="checkbox">
					<input type="checkbox" id="knitting-crochet" name="filter"><label
						for="knitting-crochet"><span>Knitting and Crochet</span></label>
				</div>
			</div>
		</div>

		<!--Table-->
		<div class="table" id="table">
			<jsp:include page="/ProductTable" flush="true" />
		</div>
	</div>
                
	<!--History-->
	<jsp:include page="/History" flush="true" />

	<!-- scripts-->
	<script src="./js/index.js"></script>
	</form>
</body>

</html>