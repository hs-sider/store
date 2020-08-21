# Store Stock Management

## Features From User perspective

- Register a Sale
- Register a Purchase
- Generate Reports
	- Transactions by Date Range
		- Last Week
		- Last Month
	- Product by Stock
		- Low stock products
		- Over stock Products

## Features Technical perspective

- Logger for track important information (Singleton)
- Read initial data from different sources
	- Read from json file Implemented
- Notifications for product stock alerts (Observer)
	- Implemented for Low Stock
	- Implemented for Over Stock
- Report generator (Factory)
	- Date Ranges
		- Implemented for Last Week and Last Month
	- Product Stock
		- Implemented for Low/Over product stock
- Custom Exceptions
- Junit Tests

## End Points

- localhost:8080/api/v0/sale (Body {productCode: prod-03, quantity: 5})
- localhost:8080/api/v0/purchase (Body {productCode: prod-01, quantity: 20})
- localhost:8080/api/v0/report?type=product&subType=overstock
- localhost:8080/api/v0/report?type=product&subType=lowstock
- localhost:8080/api/v0/report?type=date&subType=lastweek
- localhost:8080/api/v0/report?type=date&subType=lastmonth
