package stepdefinition;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static propertyutilities.GoogleConfig.getDataFromPropertyFile;
import static wrapper.LHMGetData.getMapData;
import static wrapper.WriteToProperties.readProperty;
import static wrapper.WriteToProperties.writeProperty;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import constants.GoogleMapsEndpoint;
import googlerequestpayloads.GOOGLEDeleteRequestPayload;
import googlerequestpayloads.GOOGLEPostRequestPayload;
import googlerequestpayloads.GOOGLEPutRequestPayload;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
//import support.TestContext;
import testdata.TestDataBuild;
import wrapper.APIMethods;
public class APIStepDefinition extends APIMethods
{
	String placeId = "";
	String apiKey = "";
	String placeId_dataDriven;
	private TestDataBuild dataBuilder;
//	private TestContext context;
	GOOGLEPostRequestPayload postParams_withoutData;
	GOOGLEPostRequestPayload postParams_withData;
	GOOGLEPutRequestPayload putParams;
	LinkedHashMap<String, String> getParams;
	LinkedHashMap<String, String> getParams_dataDriven;
	GOOGLEDeleteRequestPayload deleteParams;
	Response postResponse_withoutData;
	Response postResponse_withData;
	Response getResponse_beforePut;
	Response getResponse_dataDriven;
	Response putResponse;
	Response getResponse_afterPut;
	Response deleteResponse;
	Response getResponse_afterDelete;
	Response deleteResponse_afterDelete;
	public APIStepDefinition()
	{
//		this.context = new TestContext();
		this.dataBuilder = new TestDataBuild();
	}
	@Given("adding the API Base URL")
	public void adding_the_api_base_url() throws FileNotFoundException, IOException 
	{
		getBaseURL(getDataFromPropertyFile("base.url"));
	}
	@When("user calls AddPlaceAPI with POST http request")
	public void user_calls_add_place_api_with_post_http_request() throws IOException 
	{
		postParams_withoutData = dataBuilder.sendRequestPayloadForPOSTWithoutDataDriven();
		postResponse_withoutData = requestPayloadForPOST(postParams_withoutData, GoogleMapsEndpoint.POSTPLACE.getResource());
		Hooks.threadScenario.get().log(postParams_withoutData.toString());
		Hooks.threadScenario.get().log(postResponse_withoutData.asPrettyString());
	}
	@Then("the POST call got successful with status code {int}")
	public void the_post_call_got_successful_with_status_code(int statusCode) 
	{
		Hooks.threadScenario.get().log(String.valueOf(statusCode));
		assertEquals(postResponse_withoutData.statusCode(), statusCode);
	}
	@Then("{string} in the POST response body is equal to {string}")
	public void in_the_post_response_body_is_equal_to(String jsonKey, String jsonValue) 
	{
		assertEquals(extractJSONValue(postResponse_withoutData.asPrettyString(), jsonKey), jsonValue);
	}
	@Then("verify that place_id is not null in the POST response")
	public void verify_that_place_id_is_not_null_in_the_post_response() 
	{
		placeId = extractJSONValue(postResponse_withoutData.asPrettyString(), "place_id");
		Hooks.threadScenario.get().log(placeId);
		writeProperty("place_id", placeId);
		assertNotNull(placeId, "place_id should not be null");
	}
	@When("user calls GetPlaceAPI with GET http request")
	public void user_calls_get_place_api_with_get_http_request() throws FileNotFoundException, IOException 
	{
		placeId = readProperty("place_id");
		apiKey = getDataFromPropertyFile("api.key.valid");
		getParams = getMapData(apiKey, placeId);
		getResponse_beforePut = requestPayloadForGET(getParams, GoogleMapsEndpoint.GETPLACE.getResource());
		Hooks.threadScenario.get().log(getResponse_beforePut.asPrettyString());
	}
	@Then("the GET call got successful with status code {int}")
	public void the_get_call_got_successful_with_status_code(int statusCode) 
	{
		assertEquals(getResponse_beforePut.statusCode(), statusCode);
	}
	@Then("address in the GET response body should match with the address given in POST request")
	public void address_in_the_get_response_body_should_match_with_the_address_given_in_post_request() 
	{
		String address_beforePut = extractJSONValue(getResponse_beforePut.asPrettyString(), "address");
		Hooks.threadScenario.get().log(address_beforePut);
		postParams_withoutData = dataBuilder.sendRequestPayloadForPOSTWithoutDataDriven();
        assertEquals(extractJSONValue(getResponse_beforePut.asPrettyString(), "address"), postParams_withoutData.getAddress());
	}
	@When("user calls UpdatePlaceAPI with PUT http request")
	public void user_calls_update_place_api_with_put_http_request() throws FileNotFoundException, IOException 
	{
		placeId = readProperty("place_id");
		putParams = dataBuilder.sendRequestPayloadForPUT(placeId);
		putResponse = requestPayloadForPUT(putParams, GoogleMapsEndpoint.PUTPLACE.getResource());
		Hooks.threadScenario.get().log(putParams.toString());
		Hooks.threadScenario.get().log(putResponse.asPrettyString());
	}
	@Then("the PUT call got successful with status code {int}")
	public void the_put_call_got_successful_with_status_code(int statusCode) 
	{
		Hooks.threadScenario.get().log(String.valueOf(putResponse.statusCode()));
		assertEquals(putResponse.statusCode(), statusCode);
	}
	@Then("{string} in the PUT response body is equal to {string}")
	public void in_the_put_response_body_is_equal_to(String jsonKey, String jsonValue) 
	{
		assertEquals(extractJSONValue(putResponse.asPrettyString(), jsonKey), jsonValue);
	}
	@When("user calls GETPlaceAPI with GET http request after address update")
	public void user_calls_get_place_api_with_get_http_request_after_address_update() throws FileNotFoundException, IOException 
	{
		placeId = readProperty("place_id");
		apiKey = getDataFromPropertyFile("api.key.valid");
		getParams = getMapData(apiKey, placeId);
		getResponse_afterPut = requestPayloadForGET(getParams, GoogleMapsEndpoint.GETPLACE.getResource());
		Hooks.threadScenario.get().log(getResponse_afterPut.asPrettyString());
	}
	@Then("the GET call got successful with status code {int} after address update")
	public void the_get_call_got_successful_with_status_code_after_address_update(int statusCode) 
	{
		Hooks.threadScenario.get().log(String.valueOf(getResponse_afterPut.statusCode()));
		assertEquals(getResponse_afterPut.statusCode(), statusCode);
	}
	@Then("address in the GET response body should match with the address given in PUT request")
	public void address_in_the_get_response_body_should_match_with_the_address_given_in_put_request() throws FileNotFoundException, IOException 
	{
		String address_afterPut = extractJSONValue(getResponse_afterPut.asPrettyString(), "address");
		placeId = readProperty("place_id");
		putParams = dataBuilder.sendRequestPayloadForPUT(placeId);
		Hooks.threadScenario.get().log(address_afterPut);
		assertEquals(extractJSONValue(getResponse_afterPut.asPrettyString(), "address"), putParams.getAddress());
	}
	@When("user calls DeletePlaceAPI with DELETE request")
	public void user_calls_delete_place_api_with_delete_request() throws FileNotFoundException 
	{
		placeId = readProperty("place_id");
		deleteParams = dataBuilder.sendRequestPayloadForDELETE(placeId);
		deleteResponse = requestPayloadForDELETE(deleteParams, GoogleMapsEndpoint.DELETEPLACE.getResource());
		Hooks.threadScenario.get().log(deleteParams.toString());
		Hooks.threadScenario.get().log(deleteResponse.asPrettyString());
	}
	@Then("the DELETE call got successful with status code {int}")
	public void the_delete_call_got_successful_with_status_code(int statusCode) 
	{
		Hooks.threadScenario.get().log(String.valueOf(deleteResponse.statusCode()));
		assertEquals(deleteResponse.statusCode(), statusCode);
	}
	@Then("{string} in the DELETE response body is equal to {string}")
	public void in_the_delete_response_body_is_equal_to(String jsonKey, String jsonValue) 
	{
		assertEquals(extractJSONValue(deleteResponse.asPrettyString(), jsonKey), jsonValue);
	}
	@When("user calls GETPlaceAPI with GET http request after delete place")
	public void user_calls_get_place_api_with_get_http_request_after_delete_place() throws FileNotFoundException, IOException 
	{
		placeId = readProperty("place_id");
		apiKey = getDataFromPropertyFile("api.key.valid");
		getParams = getMapData(apiKey, placeId);
		getResponse_afterDelete = requestPayloadForGET(getParams, GoogleMapsEndpoint.GETPLACE.getResource());
		Hooks.threadScenario.get().log(getResponse_afterDelete.asPrettyString());
	}
	@Then("the GET call got failed with status code {int}")
	public void the_get_call_got_failed_with_status_code(int statusCode) 
	{
		Hooks.threadScenario.get().log(String.valueOf(getResponse_afterDelete.statusCode()));
		assertEquals(getResponse_afterDelete.statusCode(), statusCode);
	}
	@Then("{string} in the GET response body is equal to {string}")
	public void in_the_get_response_body_is_equal_to(String jsonKey, String jsonValue) 
	{
		assertEquals(extractJSONValue(getResponse_afterDelete.asPrettyString(), jsonKey), jsonValue);
	}
	@When("user calls DeletePlaceAPI again with DELETE request")
	public void user_calls_delete_place_api_again_with_delete_request() throws FileNotFoundException 
	{
		placeId = readProperty("place_id");
		deleteParams = dataBuilder.sendRequestPayloadForDELETE(placeId);
		deleteResponse_afterDelete = requestPayloadForDELETE(deleteParams, GoogleMapsEndpoint.DELETEPLACE.getResource());
		Hooks.threadScenario.get().log(deleteParams.toString());
		Hooks.threadScenario.get().log(deleteResponse_afterDelete.asPrettyString());
	}
	@Then("the DELETE call got failed with status code {int}")
	public void the_delete_call_got_failed_with_status_code(int statusCode) 
	{
		Hooks.threadScenario.get().log(String.valueOf(deleteResponse_afterDelete.statusCode()));
		assertEquals(deleteResponse_afterDelete.statusCode(), statusCode);
	}
	@Then("{string} in the second DELETE response body is equal to {string}")
	public void in_the_second_delete_response_body_is_equal_to(String jsonKey, String jsonValue) 
	{
		assertEquals(extractJSONValue(deleteResponse_afterDelete.asPrettyString(), jsonKey), jsonValue);
	}
	@When("user calls AddPlaceAPI with POST http request using phone number {string}, address {string} and language {string}")
	public void user_calls_add_place_api_with_post_http_request_using_phone_number_address_and_language(String phone_number, String address, String language) throws IOException
	{
		postParams_withData = dataBuilder.sendRequestPayloadForPOSTWithDataDriven(phone_number, address, language);
		postResponse_withData = requestPayloadForPOST(postParams_withData, GoogleMapsEndpoint.POSTPLACE.getResource());
		Hooks.threadScenario.get().log(postParams_withData.toString());
		Hooks.threadScenario.get().log(postResponse_withData.asPrettyString());
		placeId_dataDriven = extractJSONValue(postResponse_withData.asPrettyString(), "place_id");
		Hooks.threadScenario.get().log(placeId_dataDriven);
		int statusCode = postResponse_withData.getStatusCode();
		Hooks.threadScenario.get().log(String.valueOf(statusCode));
		assertEquals(postResponse_withData.statusCode(), statusCode);
	}
	@Then("the GET call got successful with status code {int} by place_id")
	public void the_get_call_got_successful_with_status_code_by_place_id(int statusCode_get) throws FileNotFoundException, IOException 
	{
		getParams_dataDriven = dataBuilder.sendRequestPayloadForGET(getDataFromPropertyFile("api.key.valid"), placeId_dataDriven);
		getResponse_dataDriven = requestPayloadForGET(getParams_dataDriven, GoogleMapsEndpoint.GETPLACE.getResource());
		Hooks.threadScenario.get().log(String.valueOf(getResponse_dataDriven.statusCode()));
		assertEquals(getResponse_dataDriven.statusCode(), statusCode_get);
	}
	@Then("address in the GET response body should match with {string}")
	public void address_in_the_get_response_body_should_match_with(String expectedAddress) 
	{
		assertEquals(extractJSONValue(getResponse_dataDriven.asPrettyString(), "address"), expectedAddress);
	}
}
