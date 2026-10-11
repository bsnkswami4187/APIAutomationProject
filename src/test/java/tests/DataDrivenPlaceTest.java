package tests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.PlaceEndpoints;
import enums.RestAssuredHttpMethod;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;
import static utilities.JSONDataProvider.*;
import static constants.APIConfiguration.*;
public class DataDrivenPlaceTest extends BaseTest
{
    @Override
    protected String getTestType()
    {
        return "positive";
    }
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    ObjectNode postRequestPayload;
    Map<String, String> propertyKeys;
    Response postResponse;
    @DataProvider(name = "placeData")
    public Object[][] getPlaceData() throws IOException
    {
        return getTestData("post_request_payload.json");
    }
    @Test(dataProvider = "placeData")
    public void validateCreatePlaceWithMultipleTestDataSets(Map<String, String> testData) throws IOException
    {
        propertyKeys = getConfiguration();
        postRequestPayload = testDataBuild.postPayload(testData.get("name"), testData.get("phone_number"), testData.get("address"));
        postResponse = requestUtilities.sendRequest(RestAssuredHttpMethod.POST, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), postRequestPayload, null, PlaceEndpoints.CREATE_PLACE.getEndpoint());
        responseUtilities.validateStatusCode(postResponse, 200);
    }
}
