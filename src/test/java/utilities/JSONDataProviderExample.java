package utilities;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
public class JSONDataProviderExample
{
    public static final String filePath = "src/main/java/config/";
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private JSONDataProviderExample()
    {

    }
    public static Object[][] getTestData(String jsonFileName) throws IOException
    {
        List<Map<String, String>> data = OBJECT_MAPPER.readValue(new File(filePath + jsonFileName),
                new TypeReference<List<Map<String, String>>>()
                {
                });
        Object[ ][ ] testData = new Object[data.size()][1];
        for(int listIndex = 0; listIndex < data.size(); listIndex++)
        {
            testData[listIndex][0] = data.get(listIndex);
        }
        return testData;
    }
}
