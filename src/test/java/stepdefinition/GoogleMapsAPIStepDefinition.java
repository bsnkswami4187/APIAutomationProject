package stepdefinition;
import static org.testng.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.io.IOException;
import constants.GoogleMapsEndpoint;
import googlerequestpayloads.GOOGLEPostRequestPayload;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import wrapper.APIMethods;
import utilities.JSONUtilities;
import propertyutilities.GoogleConfig;
import testdata.TestDataBuild;
public class GoogleMapsAPIStepDefinition extends APIMethods
{
	Response postResponse;
	GOOGLEPostRequestPayload postParams;
	TestDataBuild dataBuilder;
	@Given("adding the API Base URL")
	public void adding_the_api_base_url() throws FileNotFoundException, IOException 
	{
		getBaseURL(GoogleConfig.getDataFromPropertyFile("baseGoogleMapsURL"));
	}
	@When("user calls AddPlaceAPI with Post http request")
	public void user_calls_add_place_api_with_post_http_request() throws FileNotFoundException 
	{
		dataBuilder = new TestDataBuild();
		postParams = dataBuilder.getRequestPayloadForPOST();
		postResponse = getRequestPayloadForPOST(postParams, GoogleMapsEndpoint.POSTPLACE.getResource());
		Hooks.threadScenario.get().log(postParams.toString());
		Hooks.threadScenario.get().log(postResponse.asPrettyString());
	}
	@Then("the API call got successful with status code {int}")
	public void the_api_call_got_successful_with_status_code(int statusCode) 
	{
		Hooks.threadScenario.get().log(String.valueOf(postResponse.statusCode()));
		assertEquals(postResponse.getStatusCode(), statusCode);
	}
	@Then("{string} in response body is equal to {string}")
	public void in_response_body_is_equal_to(String key, String value) 
	{
		assertEquals(JSONUtilities.getDataForField(postResponse, key), value);
	}
}
