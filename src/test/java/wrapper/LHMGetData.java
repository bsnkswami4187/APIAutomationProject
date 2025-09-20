package wrapper;
import java.util.LinkedHashMap;
public class LHMGetData 
{
      public static LinkedHashMap<String, String> getMapData(String apiKey,String placeId)
      {
    	  LinkedHashMap<String, String> getParams = new LinkedHashMap<String, String>();
    	  getParams.put("key", apiKey);
    	  getParams.put("place_id", placeId);
    	  return getParams;
      }
}
