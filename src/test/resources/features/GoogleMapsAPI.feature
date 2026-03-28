Feature: Validating place APIs

Background: Assign the base URL
Given adding the API Base URL

@addPlace_getAddress
Scenario: Verify if place is added successfully using AddPlaceAPI and validate address
When user calls AddPlaceAPI with POST http request
Then the POST call got successful with status code 200
And "status" in the POST response body is equal to "OK"
And "scope" in the POST response body is equal to "APP"
And verify that place_id is not null in the POST response
When user calls GetPlaceAPI with GET http request
Then the GET call got successful with status code 200
And address in the GET response body should match with the address given in POST request

@updatePlace_getAddress
Scenario: Verify if place is updated successfully using UpdatePlaceAPI and validate address
When user calls UpdatePlaceAPI with PUT http request
Then the PUT call got successful with status code 200
And "msg" in the PUT response body is equal to "Address successfully updated"
When user calls GETPlaceAPI with GET http request after address update
Then the GET call got successful with status code 200 after address update
And address in the GET response body should match with the address given in PUT request

@deletePlace_getAddress
Scenario: Verify if place is deleted successfully using DeletePlaceAPI and validate address
When user calls DeletePlaceAPI with DELETE request
Then the DELETE call got successful with status code 200
And "status" in the DELETE response body is equal to "OK"
When user calls GETPlaceAPI with GET http request after delete place
Then the GET call got failed with status code 404
And "msg" in the GET response body is equal to "Get operation failed, looks like place_id  doesn't exists"

@deletePlace_afterDelete
Scenario: Verify if place is deleted successfully using DeletePlaceAPI and validate delete
#When user calls DeletePlaceAPI with DELETE request
#Then the DELETE call got successful with status code 200
#And "status" in the DELETE response body is equal to "OK"
#Send a second DELETE request for the same place_id and validate failure
When user calls DeletePlaceAPI again with DELETE request
Then the DELETE call got failed with status code 404
And "msg" in the second DELETE response body is equal to "Delete operation failed, looks like the data doesn't exists"

@addPlace_getAddress_dataDriven
Scenario Outline: Verify if place is created successfully with data driven testing
When user calls AddPlaceAPI with POST http request using phone number "<phone_number>", address "<address>" and language "<language>"
#Then the POST call with data driven got successful with status code 200
#And verify that place_id is not null in the POST response
#When user calls GetPlaceAPI with GET http request
Then the GET call got successful with status code 200 by place_id
And address in the GET response body should match with "<address>"
Examples:
| phone_number         | address                                | language |
| (+91) 959 156 2894 | ITPL, Whitefield, Bengaluru | Telugu    |
| (+91) 998 934 9104 | Uppal Depot, Hyderabad     | Telugu    |
| (+91) 967 667 5720 | Chanda Nagar, Hyderabad   | Telugu   | 

