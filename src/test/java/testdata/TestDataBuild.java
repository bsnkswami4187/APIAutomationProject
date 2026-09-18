package testdata;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import static config.GoogleConfig.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static utilities.RandomUtilities.*;
public class TestDataBuild
{
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public ObjectNode postPayload()
    {
        ObjectNode postPayload = OBJECT_MAPPER.createObjectNode();
        ObjectNode locationNode = OBJECT_MAPPER.createObjectNode();
        ArrayNode typesArray = OBJECT_MAPPER.createArrayNode();
        locationNode.put("lat", getLatLng(20, 30));
        locationNode.put("lng", getLatLng(40, 50));
        postPayload.set("location", locationNode);
        postPayload.put("accuracy", getAccuracy(10, 20));
        postPayload.put("name", getName());
        postPayload.put("phone_number", getPhoneNumber());
        postPayload.put("address", getAddress());
        List<String> types = getTypes(2);
        types.forEach(typesArray::add);
        postPayload.set("types", typesArray);
        postPayload.put("website", getWebsite());
        postPayload.put("language", getLanguage());
        return postPayload;
    }
    public ObjectNode putPayload(String placeId) throws IOException
    {
        if(placeId == null || placeId.trim().isEmpty())
        {
            throw new IllegalArgumentException(" Place ID cannot be null or empty for a PUT request ");
        }
        ObjectNode putPayload = OBJECT_MAPPER.createObjectNode();
        putPayload.put("place_id", placeId);
        putPayload.put("address", getAddress());
        putPayload.put("key", getPropertyKeyValue().get("api.key.valid"));
        return putPayload;
    }
    public Map<String, Object> getPayload(String placeId)
    {
        Map<String, Object> getPayload = new LinkedHashMap<>();
        getPayload.put("place_id", placeId);
        return getPayload;
    }
    public ObjectNode deletePayload(String placeId)
    {
        if(placeId == null || placeId.isEmpty())
        {
            throw new IllegalArgumentException(" Place ID cannot be null or empty for a DELETE request ");
        }
        ObjectNode deletePayload = OBJECT_MAPPER.createObjectNode();
        deletePayload.put("place_id", placeId);
        return deletePayload;
    }
    public Map<String, Object> removeJSONKey(Map<String, Object> payload, String jsonKeyToRemove)
    {
        if(payload == null || payload.isEmpty())
        {
            throw new IllegalArgumentException(" Payload cannot be null or empty ");
        }
        if(jsonKeyToRemove == null || jsonKeyToRemove.trim().isEmpty())
        {
            throw new IllegalArgumentException(" JSON Key to remove cannot be null or empty ");
        }
        if(!payload.containsKey(jsonKeyToRemove))
        {
            throw new IllegalArgumentException(" JSON Key to remove does not exist in the payload ");
        }
        payload.remove(jsonKeyToRemove);
        return payload;
    }
}
