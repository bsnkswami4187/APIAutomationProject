package hooks;
//import java.io.IOException;
//import constants.GoogleMapsEndpoint;
import googlerequestpayloads.GOOGLEPostRequestPayload;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
//import io.restassured.response.Response;
//import support.TestContext;
import testdata.TestDataBuild;
import utilities.Helper;
//import wrapper.APIMethods;
public class Hooks extends TestDataBuild
{
//	   @SuppressWarnings("unused")
//	   private final TestContext context;
	   @SuppressWarnings("unused")
	   private final TestDataBuild dataBuilder;
//	   private final APIMethods methods;
	   GOOGLEPostRequestPayload postParams;
//	   private final TestDataBuild dataBuilder;
	   public Hooks()
	   {
//		   this.methods = new APIMethods();
		   this.dataBuilder = new TestDataBuild();
//		   this.context = new TestContext();
	   }
	   public static ThreadLocal<Scenario> threadScenario = new ThreadLocal<Scenario>();
	   public static ThreadLocal<Integer> scenarioIndex = ThreadLocal.withInitial(() -> 0);
       @Before
       public void before(Scenario scenario)
       {
    	   Helper.deleteFolder("./logs");
    	   threadScenario.set(scenario);
       }
       @Before
       public void beforeScenario()
       {
    	   
       }
//       @Before("@updatePlace_getAddress")
//       public void createPlaceAndExtractPlaceID() throws IOException
//       {
//    	   postParams = dataBuilder.sendRequestPayloadForPOST();
//    	    Response postResponse = methods.requestPayloadForPOST(postParams, GoogleMapsEndpoint.POSTPLACE.getResource());
//    	    int statusCode = postResponse.getStatusCode();
//    	    String contentType = postResponse.getHeader("Content-Type");
//    	    String body = postResponse.asPrettyString();
//    	    // Debug output to help diagnose what came back (use logger in real code)
//    	    System.out.println("POST /place response status: " + statusCode);
//    	    System.out.println("POST /place response Content-Type: " + contentType);
//    	    System.out.println("POST /place response body:\n" + body);
//    	    // Expect application/json and 200/201 — adjust allowedStatusCodes if your API returns different code
//    	    if (!(statusCode == 200 || statusCode == 201)) 
//    	    {
//    	        throw new IllegalStateException("Unexpected status code from POST: " + statusCode + "\nResponse body:\n" + body);
//    	    }
//    	    if (contentType == null || !contentType.toLowerCase().contains("application/json")) 
//    	    {
//    	        throw new IllegalStateException("Response is not JSON. Content-Type: " + contentType + "\nResponse body:\n" + body);
//    	    }
//    	    // Safely extract place_id using Response.jsonPath()
//    	    String placeId = null;
//    	    try 
//    	    {
//    	        placeId = postResponse.jsonPath().getString("place_id");
//    	    } 
//    	    catch (Exception e) 
//    	    {
//    	        throw new IllegalStateException("Failed to extract 'place_id' from JSON response. Response body:\n" + body, e);
//    	    }
//    	    if (placeId == null || placeId.isEmpty()) 
//    	    {
//    	        throw new IllegalStateException("'place_id' was null/empty. Response body:\n" + body);
//    	    }
//    	    context.setPlace_id(placeId);
//       }
       @After
       public void after()
       {
    	   
       }
}
