package tests;
import enums.PlaceEndpoints;
import enums.RestAssuredHttpMethod;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;
import static constants.APIConfiguration.getConfiguration;
public class InvalidPlaceIDTests extends BaseTest
{
    @Override
    protected String getTestType()
    {
        return "negative";
    }
    public static final Logger logger = LoggerFactory.getLogger(InvalidPlaceIDTests.class);
    Map<String, Object> getRequestPayload;
    Map<String, String> propertyKeys;
    Response getResponse;
    @Test(priority = 1)
    public void validateGetWithInvalidPlaceIdAndValidApiKeyReturnsNotFound() throws IOException
    {
        propertyKeys = getConfiguration();
        getRequestPayload = testDataBuild.getPayload("ppb281186");
        getResponse = requestUtilities.sendRequest(RestAssuredHttpMethod.GET, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), null, getRequestPayload, PlaceEndpoints.RETRIEVE_PLACE.getEndpoint());
        responseUtilities.validateStatusCode(getResponse, 404);
        validationUtilities.validateStaticJSONValue(getResponse, "get_after_delete_response.json", "msg", String.class);
    }
    @Test(priority = 2)
    public void validateGetWithInvalidPlaceIdAndInvalidApiKeyReturnsNotFound() throws IOException
    {
        propertyKeys = getConfiguration();
        getRequestPayload = testDataBuild.getPayload("ppb281186");
        getResponse = requestUtilities.sendRequest(RestAssuredHttpMethod.GET, propertyKeys.get("base.url"), "swami4187", null, getRequestPayload, PlaceEndpoints.RETRIEVE_PLACE.getEndpoint());
        responseUtilities.validateStatusCode(getResponse, 404);
        validationUtilities.validateStaticJSONValue(getResponse, "get_after_delete_response.json", "msg", String.class);
    }
    @Test(priority = 3)
    public void validateGetWithNullPlaceIdAndValidApiKeyReturnsNotFound() throws IOException
    {
        propertyKeys = getConfiguration();
        getRequestPayload = testDataBuild.getPayload(null);
        getResponse = requestUtilities.sendRequest(RestAssuredHttpMethod.GET, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), null, getRequestPayload, PlaceEndpoints.RETRIEVE_PLACE.getEndpoint());
        responseUtilities.validateStatusCode(getResponse, 404);
        validationUtilities.validateStaticJSONValue(getResponse, "get_after_delete_response.json", "msg", String.class);
    }
    @Test(priority = 4)
    public void validateGetWithEmptyPlaceIdAndValidApiKeyReturnsNotFound() throws IOException
    {
        propertyKeys = getConfiguration();
        getRequestPayload = testDataBuild.getPayload("");
        getResponse = requestUtilities.sendRequest(RestAssuredHttpMethod.GET, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), null, getRequestPayload, PlaceEndpoints.RETRIEVE_PLACE.getEndpoint());
        responseUtilities.validateStatusCode(getResponse, 404);
        validationUtilities.validateStaticJSONValue(getResponse, "get_after_delete_response.json", "msg", String.class);
    }
}
