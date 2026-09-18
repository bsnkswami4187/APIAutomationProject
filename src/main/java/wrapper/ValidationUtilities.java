package wrapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import wrapper.ResponseUtilities;
import java.io.IOException;
import static org.assertj.core.api.Assertions.*;
public class ValidationUtilities
{
    private final ResponseUtilities responseUtilities;
    public ValidationUtilities(ResponseUtilities responseUtilities)
    {
        if(responseUtilities == null)
        {
            throw new IllegalArgumentException(" Response Utilities cannot be null ");
        }
        this.responseUtilities = responseUtilities;
    }
    public <T> void validateStaticJSONValue(Response jsonResponse, String jsonFileName, String jsonKey, Class<T> dataType) throws IOException
    {
        T staticActual = responseUtilities.extractJSONValueWithRESTAssured(jsonResponse, jsonKey, dataType);
        T staticExpected = responseUtilities.extractJSONValueFromJSONFile(jsonFileName, jsonKey, dataType);
        assertThat(staticActual)
                .withFailMessage(
                        "Static Validation failed for JSON key '%s', Expected: <%s>, Actual: <%s>",
                        jsonKey, staticExpected, staticActual).isEqualTo(staticExpected);
    }
    public <T> void validateDynamicJSONValue(Response jsonResponse, ObjectNode requestPayload, String jsonKey, Class<T> dataType)
    {
        T dynamicActual = responseUtilities.extractJSONValueWithRESTAssured(jsonResponse, jsonKey, dataType);
        T dynamicExpected = responseUtilities.extractJSONValueFromObjectNode(requestPayload, jsonKey, dataType);
        assertThat(dynamicActual)
                .withFailMessage(
                        "Static Validation failed for JSON key '%s', Expected: <%s>, Actual: <%s>",
                        jsonKey, dynamicExpected, dynamicActual).isEqualTo(dynamicExpected);
    }
}
