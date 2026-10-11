package utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LoggingUtilities
{
    public static final Logger logger = LoggerFactory.getLogger(LoggingUtilities.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static String getFormattedJSON(String jsonPayload)
    {
        try
        {
            Object json = OBJECT_MAPPER.readValue(jsonPayload, Object.class);
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        }
        catch(Exception exception)
        {
            return jsonPayload;
        }
    }
    public static void logRequest(String httpMethod, String requestUrl, Object requestPayload)
    {
        logger.info(" =================================================== ");
        logger.info(" HTTP Method : {} ", httpMethod);
        logger.info(" Request URL : {} ", requestUrl);
        if(requestPayload != null)
        {
            logger.info(" Request Payload : \n{} ", LoggingUtilities.getFormattedJSON(requestPayload.toString()));
        }
        else
        {
            logger.info(" Request Payload : No Request Body ");
        }
        logger.info(" =================================================== ");
    }
    public static void logResponse(Response jsonResponse)
    {
        logger.info(" =================================================== ");
        logger.info(" Response Status Code : {} ", jsonResponse.getStatusCode());
        logger.info(" Response Payload : \n{} ", LoggingUtilities.getFormattedJSON(jsonResponse.getBody().asString()));
        logger.info(" =================================================== ");
    }
}
