package resources;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import propertyresources.GoogleConfig;
//import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class Utilities 
{
         public static RequestSpecification requestSpecification;
         public static PrintStream log;
         public RequestSpecification requestSpecification() throws IOException
         {
        	 if(requestSpecification == null)
        	 {
        		 log = new PrintStream(new FileOutputStream("Logging.txt"));
        		 requestSpecification = new RequestSpecBuilder().setBaseUri(GoogleConfig.extractDataFromFile("baseURL"))
        		 .addQueryParam("key", GoogleConfig.extractDataFromFile("apiKey")).addFilter(RequestLoggingFilter.logRequestTo(log))
        		 .addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
        		 return requestSpecification;
        	 }
        	 return requestSpecification;
         }
         public String extractDataForField(Response jsonResponse,String fieldName)
         {
        	 JsonPath jsonPath = jsonResponse.jsonPath();
        	 return jsonPath.getString(fieldName);
         }
}
