package wrapper;
import static io.restassured.RestAssured.given;
import static utilities.LoggingUtilities.*;
import enums.PlaceHttpMethod;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.filter.Filter;
import java.io.IOException;
import java.util.Map;
public class PlaceRequestUtilities
{
    public RequestSpecification baseRequestSpec(String baseUrl, String apiKey, ContentType requestType)
    {
        if (baseUrl == null || baseUrl.isEmpty())
        {
            throw new IllegalArgumentException(" Base URL can neither be null nor be empty ");
        }
        Filter requestFilter = ((requestSpec, responseSpec, ctx) ->
        {
            logRequest(requestSpec.getMethod(), requestSpec.getURI(), requestSpec.getBody());
            return ctx.next(requestSpec, responseSpec);
        });
        Filter responseFilter = ((requestSpec, responseSpec, ctx) ->
        {
            Response jsonResponse = ctx.next(requestSpec, responseSpec);
            logResponse(jsonResponse);
            return jsonResponse;
        });
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri(baseUrl).addFilter(requestFilter).addFilter(responseFilter);
        if (apiKey != null && !apiKey.trim().isEmpty())
        {
            requestSpecBuilder.addQueryParam("key", apiKey);
        }
        if (requestType != null)
        {
            requestSpecBuilder.setContentType(requestType);
        }
        return requestSpecBuilder.build();
    }
    public Response sendRequest(PlaceHttpMethod httpMethod, String baseUrl, String apiKey, Object requestPayload, Map<String, Object> queryParams, String endPoint) throws IOException
    {
       if(httpMethod == null)
       {
           throw new IllegalArgumentException(" HTTP Method cannot be null ");
       }
       if(endPoint == null || endPoint.trim().isEmpty())
       {
           throw new IllegalArgumentException(" Endpoint cannot be null or empty ");
       }
       ContentType requestType = httpMethod == PlaceHttpMethod.GET ? null : ContentType.JSON;
       RequestSpecification request = given().spec(baseRequestSpec(baseUrl, apiKey, requestType));
       if(httpMethod == PlaceHttpMethod.GET)
       {
           request.queryParams(queryParams);
       }
       request = requestPayload != null ? request.body(requestPayload) : request;
       switch (httpMethod)
       {
           case POST :
           return request.when().post(endPoint);
           case GET :
           return request.when().get(endPoint);
           case PUT :
           return request.when().put(endPoint);
           case DELETE :
           return request.when().delete(endPoint);
           default :
           throw new IllegalArgumentException(" Invalid HTTP Method : " + httpMethod);
       }
    }
}
