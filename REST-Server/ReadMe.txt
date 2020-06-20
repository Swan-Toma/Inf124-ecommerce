Swan Toma 32503923
Greg Zubatov 57854629
Genesis Garcia 74825459

GET /zipcode/zip
Request: http://localhost:8080/REST-Server/v1/api/zipcode/92011
Response: Returns a JSON object with the city, state, and tax rate of the corresponding zip code
	{
		"city": "Chula Vista",
		"state": "CA",
		"taxRate": 0.08,
		"zipCode": 91911
	}

GET /products
Request: http://localhost:8080/REST-Server/v1/api/products
Response: Returns a list of JSON objects containing all product information


GET /products/categories/id
Request: http://localhost:8080/REST-Server/v1/api/products/categories/1
Response:
	[
		{
			"category": "floral"
		},
		{
			"category": "home-office"
		},
		{
			"category": "product"
   	    }
	]
	
	
GET /products/id
Request: http://localhost:8080/REST-Server/v1/api/products/1
Response:
	{
		"description": "Do you love plants, but do not have a green thumb or the time to care for them? Then this mini potted cactus plants will be the perfect addition to your home.  The extremely realistic cactus plants come nestled in a silver tin pot and are set in faux dirt for stability.",
		"details": "Green, Overall dimensions: 3.5\" x 2.5\", Pot dimensions: 2.5\" x 2.5\" top diameter x 1.75\" bottom diameter, Plastic and tin, Recommended for indoor and covered outdoor use",
		"image": "./imgs/products/mini_cactus.jpg",
		"name": "Mini Cactus",
		"pid": 1,
		"price": 7.99
	}


POST /orders
Request URL: http://localhost:8080/REST-Server/v1/api/orders
Request Body (JSON or Form URL Encoded):
    {
		"city": "Chula Vista",
		"creditCard": "9876543212345673",
		"expMonth": 1,
		"expYear": 2020,
		"firstName": "mouse",
		"lastName": "micky",
		"phoneNumber": "122-323-2332",
		"pids": "2:4",
		"priceTotal": 43.18,
		"securityCode": 444,
		"shippingAddress": "wefdqwd",
		"shippingMethod": "overnight",
		"state": "CA",
		"zipCode": 91911
	}	
Response: Integer representing order id is returned


GET /order/id
Request: http://localhost:8080/REST-Server/v1/api/orders/19
Response:
	{
		"city": "Chula Vista",
		"creditCard": "9876543212345673",
		"expMonth": 1,
		"expYear": 2020,
		"firstName": "mouse",
		"lastName": "micky",
		"oid": 19,
		"phoneNumber": "122-323-2332",
		"pids": "2:4",
		"priceTotal": 43.18,
		"securityCode": 444,
		"shippingAddress": "wefdqwd",
		"shippingMethod": "overnight",
		"state": "CA",
		"zipCode": 91911
	}


Example of 404 message (invalid zip code number 12345678):
GET http://localhost:8080/REST-Server/v1/api/zipcode/12345678
	<body>
		<h1>HTTP Status 404 - Not Found</h1>
		<hr />
		<p><b>type</b> Status report</p>
		<p><b>message</b>Not Found</p>
		<p><b>description</b>The requested resource is not available.</p>
		<hr />
		<h3>GlassFish Server Open Source Edition 5.1.0 </h3>
	</body>