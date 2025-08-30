package propertyutilities;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class GoogleConfig 
{
     public static final String CONFIG_FILE_PATH = "./src/main/resources/config/GoogleMapsConfig.properties";
     public static String getDataFromPropertyFile(String key) throws FileNotFoundException, IOException
     {
    	 Properties properties = new Properties();
    	 try(FileInputStream inputStream = new FileInputStream(CONFIG_FILE_PATH))
    	 {
    		 properties.load(inputStream);
    	 }
    	 String propertyData = properties.getProperty(key);
    	 if(propertyData == null || propertyData.isEmpty())
    	 {
    		 throw new IllegalArgumentException("Property Data not found or empty in properties file");
    	 }
    	 return propertyData;
     }
}
