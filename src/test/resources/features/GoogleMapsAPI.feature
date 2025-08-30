Feature: Validating Place API's

Background: Assign the base URL
Given adding the API Base URL

@addPlace
Scenario: Verify if Place is being successfully added using AddPlaceAPI
When user calls AddPlaceAPI with Post http request
Then the API call got successful with status code 200
And "status" in response body is equal to "OK"
And "scope" in response body is equal to "APP"