package config;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
public class GoogleConfig
{
    public static String getConfigFilePath(String environment)
    {
        switch(environment.toLowerCase())
        {
            case "dev" :
                return "config/GoogleMapsConfig_Dev.properties";
            case "test" :
                return "config/GoogleMapsConfig_Test.properties";
            case "prod" :
                return "config/GoogleMapsConfig_Prod.properties";
            case "preprod" :
                return "config/GoogleMapsConfig_PreProd.properties";
            default :
                throw new IllegalArgumentException(" Invalid environment : " + environment);
        }
    }
    public static String getDataFromPropertyFile(String environment, String propertyKey) throws IOException
    {
        if(environment == null || environment.isEmpty())
        {
            throw new IllegalArgumentException(" Environment can neither be null nor be empty ");
        }
        String configFilePath = GoogleConfig.getConfigFilePath(environment);
        Properties properties = new Properties();
        try(InputStream inputStream = GoogleConfig.class.getClassLoader().getResourceAsStream(configFilePath))
        {
            properties.load(inputStream);
        }
        String propertyData = properties.getProperty(propertyKey);
        if(propertyData == null || propertyData.isEmpty())
        {
            throw new IllegalArgumentException(" Property " + propertyKey + " not found or empty in " + configFilePath);
        }
        return propertyData;
    }
    public static Map<String, String> getPropertyKeyValue() throws IOException
    {
        Map<String, String> propertyKeys = new LinkedHashMap<>();
        propertyKeys.put("base.url", GoogleConfig.getDataFromPropertyFile("test", "base.url"));
        propertyKeys.put("api.key.valid", GoogleConfig.getDataFromPropertyFile("test", "api.key.valid"));
        return propertyKeys;
    }
}