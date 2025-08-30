package propertyresources;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
public class GoogleConfig 
{
	     public static Properties properties;
	     public static FileInputStream inputStream;
         public static String extractDataFromFile(String key) throws IOException
         {
        	 properties = new Properties();
        	 inputStream = new FileInputStream("C:\\Users\\prach\\eclipse-workspace\\googlemapsrestassured\\src\\main\\java\\resources\\GoogleMapsConfig.properties");
        	 properties.load(inputStream);
        	 return properties.getProperty(key);
         }
}
