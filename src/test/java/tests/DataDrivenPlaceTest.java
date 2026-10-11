package tests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Endpoints;
import enums.HttpMethod;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;
import static utilities.JSONDataProviderExample.*;
import static constants.GoogleMapsConfiguration.*;
public class DataDrivenPlaceTest extends BasePlaceTest
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
        postResponse = requestUtilities.sendRequest(HttpMethod.POST, propertyKeys.get("base.url"), propertyKeys.get("api.key.valid"), postRequestPayload, null, Endpoints.CREATE_PLACE.getEndpoint());
        responseUtilities.validateStatusCode(postResponse, 200);
    }
}
