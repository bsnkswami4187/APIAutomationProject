package wrapper;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;

import googlerequestpayloads.GOOGLEDeleteRequestPayload;
import googlerequestpayloads.GOOGLEPostRequestPayload;
import googlerequestpayloads.GOOGLEPutRequestPayload;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.Helper;
import propertyutilities.GoogleConfig;
public class APIMethods 
{
     public void getBaseURL(String baseURL)
     {
    	 RestAssured.baseURI = baseURL;
     }
     public Response requestPayloadForPOST(GOOGLEPostRequestPayload googlePostPayload, String postEndpoint) throws IOException
     {
    	String logPath = "./logs/POSTfilterLog"+Helper.getCurrentDate()+".txt";		
 		PrintStream ps = new PrintStream(new File(logPath));
    	Response postResponse = given().contentType(ContentType.JSON).queryParam("key", GoogleConfig.getDataFromPropertyFile("apiKey"))
    	.filter(RequestLoggingFilter.logRequestTo(ps))
    	.filter(ResponseLoggingFilter.logResponseTo(ps))
    	.body(googlePostPayload).post(postEndpoint);
    	 return postResponse;
     }
     public Response requestPayloadForGET(LinkedHashMap<String, String> googleGetPayload, String getEndpoint) throws FileNotFoundException
     {
     	String logPath = "./logs/GETfilterLog"+Helper.getCurrentDate()+".txt";		
  		PrintStream ps = new PrintStream(new File(logPath));
        Response getResponse = given().queryParams(googleGetPayload)
        .filter(RequestLoggingFilter.logRequestTo(ps))
        .filter(ResponseLoggingFilter.logResponseTo(ps)).when()
        .get(getEndpoint);
        return getResponse;
     }
     public Response requestPayloadForPUT(GOOGLEPutRequestPayload googlePutPayload, String putEndpoint) throws IOException
     {
    	 String logPath = "./logs/PUTfilterLog"+Helper.getCurrentDate()+".txt";
    	 PrintStream ps = new PrintStream(new File(logPath));
    	 Response putResponse = given().contentType(ContentType.JSON).queryParam("key", GoogleConfig.getDataFromPropertyFile("apiKey"))
         .filter(RequestLoggingFilter.logRequestTo(ps)).filter(ResponseLoggingFilter.logResponseTo(ps)).body(googlePutPayload).when().put(putEndpoint); 
    	 return putResponse;
     }
     public Response requestPayloadForDELETE(GOOGLEDeleteRequestPayload googleDeletePayload, String deleteEndpoint) throws FileNotFoundException
     {
    	 String logPath = "./logs/DELETEfilterLog"+Helper.getCurrentDate()+".txt";
    	 PrintStream ps = new PrintStream(new File(logPath));
    	 Response deleteResponse = given().contentType(ContentType.JSON).filter(RequestLoggingFilter.logRequestTo(ps))
         .filter(ResponseLoggingFilter.logResponseTo(ps)).body(googleDeletePayload).when().delete(deleteEndpoint);
    	 return deleteResponse;
     }
     public String extractJSONValue(String jsonResponse, String jsonKey) 
     {
         JsonPath jsonPath = new JsonPath(jsonResponse); 
         return jsonPath.getString(jsonKey);
     }
     public Response dataDrivenRequestPayloadForPOST(GOOGLEPostRequestPayload googlePostPayload, String postEndpoint) throws IOException
     {
     	String logPath = "./logs/POSTfilterLog"+Helper.getCurrentDate()+".txt";		
  		PrintStream ps = new PrintStream(new File(logPath));
     	Response postResponse = given().contentType(ContentType.JSON).queryParam("key", GoogleConfig.getDataFromPropertyFile("apiKey"))
     	.filter(RequestLoggingFilter.logRequestTo(ps))
     	.filter(ResponseLoggingFilter.logResponseTo(ps))
     	.body(googlePostPayload).post(postEndpoint);
     	 return postResponse;
     }
}
