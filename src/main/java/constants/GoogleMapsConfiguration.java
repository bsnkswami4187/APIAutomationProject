package constants;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.*;
public class GoogleMapsConfiguration
{
    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final String CONFIG_FILE_PATH = "src/main/java/config/";
    public static Map<String, String> getConfiguration() throws IOException
    {
        Map<String, String> configuration = new LinkedHashMap<>();
        configuration.put("base.url", GoogleMapsConfiguration.extractConfigData("google_config.json", "base.url"));
        configuration.put("api.key.valid", GoogleMapsConfiguration.extractConfigData("google_config.json", "api.key.valid"));
        return configuration;
    }
    public static String extractConfigData(String jsonFileName, String jsonKey) throws IOException
    {
        JsonNode rootNode = OBJECT_MAPPER.readTree(new File(CONFIG_FILE_PATH + jsonFileName));
        return rootNode.get(jsonKey).asText();
    }
}
