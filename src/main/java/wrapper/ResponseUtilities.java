package wrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.io.File;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ResponseUtilities
{
    public static final Logger logger = LoggerFactory.getLogger(ResponseUtilities.class);
    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static String FILE_PATH = "src/test/java/responses/";
    public <T> T extractJSONValueWithRESTAssured(Response jsonResponse, String jsonKey, Class<T> dataType)
    {
        JsonPath jsonPath = jsonResponse.jsonPath();
        if(!jsonPath.getMap("$").containsKey(jsonKey))
        {
            return null;
        }
        Object jsonValue = jsonPath.get(jsonKey);
        return dataType.cast(jsonValue);
    }
    public <T> T extractJSONValueFromObjectNode(ObjectNode requestPayload, String jsonKey, Class<T> dataType)
    {
        if(requestPayload == null || !requestPayload.has(jsonKey))
        {
            return null;
        }
        JsonNode jsonNode = requestPayload.get(jsonKey);
        if(jsonNode == null || jsonNode.isNull())
        {
            return null;
        }
        return OBJECT_MAPPER.convertValue(jsonNode, dataType);
    }
    public <T> T extractJSONValueFromJSONFile(String jsonFileName, String jsonKey, Class<T> dataType) throws IOException
    {
        JsonNode rootNode = OBJECT_MAPPER.readTree(new File(FILE_PATH + jsonFileName));
        if(!rootNode.has(jsonKey))
        {
            return null;
        }
        return OBJECT_MAPPER.treeToValue(rootNode.get(jsonKey), dataType);
    }
    public <T> void validatePresenceAndNullability(Response jsonResponse, String jsonKey, Class<T> expectedDataType)
    {
        JsonPath jsonPath = jsonResponse.jsonPath();
        assertThat(jsonPath.getMap("$"))
                .withFailMessage(" JSON key '%s' is not present in response ", jsonKey)
                .containsKey(jsonKey);
        Object actualJsonValue = jsonPath.get(jsonKey);
        assertThat(actualJsonValue)
                .withFailMessage(" JSON key '%s' has null value ", jsonKey)
                .isNotNull();
        assertThat(actualJsonValue)
                .withFailMessage(" JSON key '%s' Expected Type : %s Actual Type : %s "
                        , jsonKey, expectedDataType.getSimpleName(), actualJsonValue.getClass().getSimpleName())
                .isInstanceOf(expectedDataType);
        if(expectedDataType.equals(String.class))
        {
            assertThat(actualJsonValue.toString())
                    .withFailMessage(" JSON key '%s' has a blank value ", jsonKey)
                    .isNotBlank();
        }
        logger.info(" Validated JSON Key : {} | Value : {} | Type : {} ", jsonKey, actualJsonValue,expectedDataType.getSimpleName());
    }
    public void validateStatusCode(Response jsonResponse, int expectedStatusCode)
    {
        int actualStatusCode = jsonResponse.getStatusCode();
        assertThat(actualStatusCode)
                .withFailMessage("Expected Status Code : <%d>, Actual Status Code : <%d>", expectedStatusCode, actualStatusCode)
                .isEqualTo(expectedStatusCode);
    }
}
