package utilities;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
public class JSONUtilities 
{
       public static String getDataForField(Response jsonResponse,String fieldName)
       {
    	   JsonPath jsonPath = jsonResponse.jsonPath();
    	   return jsonPath.getString(fieldName);
       }
}
