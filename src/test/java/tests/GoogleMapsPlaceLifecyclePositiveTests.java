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
import static constants.APIConfiguration.*;
import java.util.Map;
public class GoogleMapsPlaceLifecyclePositiveTests extends BaseTest
{
    @Override
    protected String getTestType()
    {
        return "positive";
    }
    public static final Logger logger = LoggerFactory.getLogger(GoogleMapsPlaceLifecyclePositiveTests.class);
    Map<String, Object> getRequestPayload;
    Map<String, String> propertyKeys;
    String placeId;
    Response postResponse;
    Response getResponse_beforePut;
    Response getResponse_afterPut;
    Response putResponse;
    ObjectNode postRequestPayload;
    ObjectNode putRequestPayload;
    @BeforeTest
    public void createPlaceAndCapturePlaceId() throws IOException
    {
       propertyKeys = getConfiguration();
       postRequestPayload = testDataBuild.postPayload();
       postResponse = requestUtilities.sendRequest(RestAssuredHttpMethod.POST, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), postRequestPayload, null, PlaceEndpoints.CREATE_PLACE.getEndpoint());
       responseUtilities.validateStatusCode(postResponse, 200);
       placeId = responseUtilities.extractJSONValueWithRESTAssured(postResponse, "place_id", String.class);
       responseUtilities.validatePresenceAndNullability(postResponse, "place_id", String.class);
    }
    @Test(priority = 1)
    public void verifyCreatePlaceContainsExpectedFields() throws IOException
    {
       validationUtilities.validateStaticJSONValue(postResponse, "post_response.json", "status", String.class);
       validationUtilities.validateStaticJSONValue(postResponse, "post_response.json", "scope", String.class);
    }
    @Test(priority = 2)
    public void verifyPlaceAddressBeforeUpdate() throws IOException
    {
       logger.info(" ======================= Validating the address field with GET request before PUT request ======================= ");
       getRequestPayload = testDataBuild.getPayload(placeId);
       getResponse_beforePut = requestUtilities.sendRequest(RestAssuredHttpMethod.GET, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), null, getRequestPayload, PlaceEndpoints.RETRIEVE_PLACE.getEndpoint());
       responseUtilities.validateStatusCode(getResponse_beforePut, 200);
       validationUtilities.validateDynamicJSONValue(getResponse_beforePut, postRequestPayload, "address", String.class);
        logger.info(" ================================================ ");
    }
    @Test(priority = 3)
    public void updatePlaceAddressAndVerifyChanges() throws IOException
    {
        logger.info(" ================= Test PUT for complete update ================= ");
        putRequestPayload = testDataBuild.putPayload(placeId);
        putResponse = requestUtilities.sendRequest(RestAssuredHttpMethod.PUT, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), putRequestPayload, null, PlaceEndpoints.UPDATE_PLACE.getEndpoint());
        responseUtilities.validateStatusCode(putResponse, 200);
        validationUtilities.validateStaticJSONValue(putResponse, "put_response.json", "msg", String.class);
        logger.info(" ================================== ");
        logger.info(" ================= Test GET after successful PUT for complete update ================= ");
        getRequestPayload = testDataBuild.getPayload(placeId);
        getResponse_afterPut = requestUtilities.sendRequest(RestAssuredHttpMethod.GET, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), null, getRequestPayload, PlaceEndpoints.RETRIEVE_PLACE.getEndpoint());
        responseUtilities.validateStatusCode(getResponse_afterPut, 200);
        validationUtilities.validateDynamicJSONValue(getResponse_afterPut, putRequestPayload, "address", String.class);
        logger.info(" ================================== ");
    }
    @Test(priority = 4)
    public void verifyCreatePlaceWithEmptyJsonPayload() throws IOException
    {
        logger.info(" ================= Test POST with empty JSON payload ================= ");
        Response postResponseWithEmptyJSON = requestUtilities.sendRequest(RestAssuredHttpMethod.POST, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), "{}", null, PlaceEndpoints.CREATE_PLACE.getEndpoint());
        responseUtilities.validateStatusCode(postResponseWithEmptyJSON, 200);
        logger.info(" =================================== ");
    }
}
