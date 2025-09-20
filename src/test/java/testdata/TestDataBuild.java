package testdata;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import googlerequestpayloads.GOOGLEDeleteRequestPayload;
import googlerequestpayloads.GOOGLEPostRequestPayload;
import googlerequestpayloads.GOOGLEPutRequestPayload;
import postparameters.POSTLocation;
import propertyutilities.GoogleConfig;
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
    	   location = new POSTLocation(lat, lng);
    	   return location;
       }
       public GOOGLEPostRequestPayload sendRequestPayloadForPOST()
       {
    	   location = new TestDataBuild().getPOSTLocation();
    	   postParams = new GOOGLEPostRequestPayload(location, accuracy, name, phone_number, address, types, website, language);
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
    	   putParams = new GOOGLEPutRequestPayload(placeId, updatedAddress, GoogleConfig.getDataFromPropertyFile("apiKey"));
    	   return putParams;
       }
       public GOOGLEDeleteRequestPayload sendRequestPayloadForDELETE(String placeId)
       {
    	   deleteParams = new GOOGLEDeleteRequestPayload(placeId);
    	   return deleteParams;
       }
       public GOOGLEPostRequestPayload sendRequestPayloadForPOST(String phone_number, String address, String language)
       {
    	   location = new TestDataBuild().getPOSTLocation();
    	   postParams = new GOOGLEPostRequestPayload(location, accuracy, name, phone_number, address, types, website, language);
    	   return postParams;
       }
}
