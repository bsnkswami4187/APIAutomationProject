package wrapper;
import java.io.*;
import java.util.Properties;
public class WriteToProperties 
{
	    private static final String FILE_PATH = "config.properties";

	    /**
	     * Updates or adds a property in the config.properties file
	     * @param key   property key (e.g. "place_id")
	     * @param value property value (e.g. "34m2c41e61027aedab11ce9f069d4685e38")
	     */
	    public static void writeProperty(String key, String value) 
	    {
	        Properties props = new Properties();

	        // Load existing properties if file exists
	        try (FileInputStream in = new FileInputStream(FILE_PATH)) 
	        {
	            props.load(in);
	        } 
	        catch (FileNotFoundException e) 
	        {
	            System.out.println("Properties file not found, creating a new one...");
	        } catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        // Set/update the property
	        props.setProperty(key, value);
	        // Store back to file
	        try (FileOutputStream out = new FileOutputStream(FILE_PATH)) 
	        {
	            props.store(out, "Updated " + key);
	            System.out.println(key + " updated successfully with value: " + value);
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	    /**
	     * Reads a property value by key from the config.properties file
	     * @param key property key
	     * @return value as String (or null if not found)
	     */
	    public static String readProperty(String key) 
	    {
	        Properties props = new Properties();
	        try (FileInputStream in = new FileInputStream(FILE_PATH)) 
	        {
	            props.load(in);
	            return props.getProperty(key);
	        } catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        return null;
	    }
	}

