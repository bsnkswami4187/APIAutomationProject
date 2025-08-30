package wrapper;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import googlerequestpayloads.GOOGLEPostRequestPayload;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.Helper;
public class APIMethods 
{
     public void getBaseURL(String baseURL)
     {
    	 RestAssured.baseURI = baseURL;
     }
     public Response getRequestPayloadForPOST(GOOGLEPostRequestPayload googlePostPayload,String postEndpoint) throws FileNotFoundException
     {
    	String logPath = "./logs/filterLog"+Helper.getCurrentDate()+".txt";		
 		PrintStream ps = new PrintStream(new File(logPath));
    	Response postResponse = given().contentType(ContentType.JSON)
    	.filter(RequestLoggingFilter.logRequestTo(ps))
    	.filter(ResponseLoggingFilter.logResponseTo(ps))
    	.body(googlePostPayload).post(postEndpoint);
    	 return postResponse;
     }
}
