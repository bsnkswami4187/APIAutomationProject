package enums;
public enum Endpoints
{
    CREATE_PLACE("maps/api/place/add/json"),
    RETRIEVE_PLACE("maps/api/place/get/json"),
    UPDATE_PLACE("maps/api/place/update/json"),
    DELETE_PLACE("maps/api/place/delete/json");

    private final String endpoint;

    Endpoints(String endpoint)
    {
        this.endpoint = endpoint;
    }

    public String getEndpoint()
    {
        return endpoint;
    }
}
