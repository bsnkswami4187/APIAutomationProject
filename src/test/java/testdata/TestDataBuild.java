package testdata;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import googlerequestpayloads.GOOGLEDeleteRequestPayload;
import googlerequestpayloads.GOOGLEPostRequestPayload;
import googlerequestpayloads.GOOGLEPutRequestPayload;
import io.restassured.response.Response;
import postparameters.POSTLocation;
import static propertyutilities.GoogleConfig.*;
@SuppressWarnings("unused")
public class TestDataBuild extends GoogleMapsTestData
{
       POSTLocation location;
       GOOGLEPostRequestPayload postParams;
       LinkedHashMap<String, String> getParams;
       GOOGLEPutRequestPayload putParams;
       GOOGLEDeleteRequestPayload deleteParams;
       public POSTLocation getPOSTLocation()
       {
    	   location = new POSTLocation();
    	   location.setLat(lat);
    	   location.setLng(lng);
    	   return location;
       }
       public GOOGLEPostRequestPayload sendRequestPayloadForPOSTWithoutDataDriven()
       {
    	   location = new TestDataBuild().getPOSTLocation();
    	   postParams = new GOOGLEPostRequestPayload();
    	   postParams.setLocation(location);
    	   postParams.setAccuracy(accuracy);
    	   postParams.setName(name);
    	   postParams.setPhone_number(phone_number);
    	   postParams.setAddress(address);
    	   postParams.setTypes(types);
    	   postParams.setWebsite(website);
    	   postParams.setLanguage(language);
    	   return postParams;
       }
       public LinkedHashMap<String, String> sendRequestPayloadForGET(String key,String placeId)
       {
    	   getParams = new LinkedHashMap<String, String>();
    	   getParams.put("key", key);
    	   getParams.put("place_id", placeId);
    	   return getParams;
       }
       public GOOGLEPutRequestPayload sendRequestPayloadForPUT(String placeId) throws FileNotFoundException, IOException
       {
    	   putParams = new GOOGLEPutRequestPayload();
    	   putParams.setPlace_id(placeId);
    	   putParams.setAddress(address);
    	   putParams.setKey(getDataFromPropertyFile("api.key.valid"));
    	   return putParams;
       }
       public GOOGLEDeleteRequestPayload sendRequestPayloadForDELETE(String placeId)
       {
    	   deleteParams = new GOOGLEDeleteRequestPayload();
    	   deleteParams.setPlace_id(placeId);
    	   return deleteParams;
       }
       public GOOGLEPostRequestPayload sendRequestPayloadForPOSTWithDataDriven(String phone_number, String address, String language)
       {
    	   location = new TestDataBuild().getPOSTLocation();
    	   postParams = new GOOGLEPostRequestPayload();
    	   postParams.setLocation(location);
    	   postParams.setAccuracy(accuracy);
    	   postParams.setName(name);
    	   postParams.setPhone_number(phone_number);
    	   postParams.setAddress(address);
    	   postParams.setTypes(types);
    	   postParams.setWebsite(website);
    	   postParams.setLanguage(language);
    	   return postParams;
       }
}
