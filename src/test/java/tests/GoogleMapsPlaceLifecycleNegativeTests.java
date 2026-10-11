package tests;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.PlaceEndpoints;
import enums.RestAssuredHttpMethod;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;
import static constants.APIConfiguration.getConfiguration;
public class GoogleMapsPlaceLifecycleNegativeTests extends BaseTest
{
   @Override
   protected String getTestType()
   {
       return "negative";
   }
   public static final Logger logger = LoggerFactory.getLogger(GoogleMapsPlaceLifecycleNegativeTests.class);
   String placeId;
   Map<String, String> propertyKeys;
   ObjectNode postRequestPayload;
   Map<String, Object> getRequestPayload;
   ObjectNode putRequestPayload;
   ObjectNode deleteRequestPayload;
   Response postResponse;
   Response deleteResponse;
   Response getResponse_afterDelete;
   Response putResponse_afterDelete;
   @BeforeTest
   public void createAndDeletePlaceForNegativeScenarios() throws IOException
   {
       logger.info(" =========== Test POST call and extract placeId =========== ");
       propertyKeys = getConfiguration();
       postRequestPayload = testDataBuild.postPayload();
       postResponse = requestUtilities.sendRequest(RestAssuredHttpMethod.POST, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), postRequestPayload, null, PlaceEndpoints.CREATE_PLACE.getEndpoint());
       responseUtilities.validateStatusCode(postResponse, 200);
       responseUtilities.validatePresenceAndNullability(postResponse, "place_id", String.class);
       placeId = responseUtilities.extractJSONValueWithRESTAssured(postResponse, "place_id", String.class);
       logger.info(" ======================= ");
       logger.info(" =========== Test DELETE call after successful POST call =========== ");
       deleteRequestPayload = testDataBuild.deletePayload(placeId);
       deleteResponse = requestUtilities.sendRequest(RestAssuredHttpMethod.DELETE, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), deleteRequestPayload, null, PlaceEndpoints.DELETE_PLACE.getEndpoint());
       logger.info(" ======================= ");
   }
   @Test(priority = 1)
    public void validateDeletePlaceResponse() throws IOException
   {
       responseUtilities.validateStatusCode(deleteResponse, 200);
       validationUtilities.validateStaticJSONValue(deleteResponse, "delete_response.json", "status", String.class);
   }
   @Test(priority = 2)
   public void validateGetForDeletedPlace() throws IOException
   {
      logger.info(" ============ Test GET after successful DELETE call ============ ");
      getRequestPayload = testDataBuild.getPayload(placeId);
      getResponse_afterDelete = requestUtilities.sendRequest(RestAssuredHttpMethod.GET, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), null, getRequestPayload, PlaceEndpoints.RETRIEVE_PLACE.getEndpoint());
      responseUtilities.validateStatusCode(getResponse_afterDelete, 404);
      validationUtilities.validateStaticJSONValue(getResponse_afterDelete, "get_after_delete_response.json", "msg", String.class);
      logger.info(" ========================= ");
   }
   @Test(priority = 3)
   public void validateCompleteUpdateForDeletedPlace() throws IOException
   {
       logger.info(" ===================== Test PUT after successful DELETE call ===================== ");
       putRequestPayload = testDataBuild.putPayload(placeId);
       putResponse_afterDelete = requestUtilities.sendRequest(RestAssuredHttpMethod.PUT, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), putRequestPayload, null, PlaceEndpoints.UPDATE_PLACE.getEndpoint());
       responseUtilities.validateStatusCode(putResponse_afterDelete, 404);
       validationUtilities.validateStaticJSONValue(putResponse_afterDelete, "put_after_delete_response.json", "msg", String.class);
       logger.info(" ========================================== ");
   }
}
