package enums;
import lombok.Getter;
@Getter
public enum PlaceEndpoints
{
    CREATE_PLACE("maps/api/place/add/json"),
    RETRIEVE_PLACE("maps/api/place/get/json"),
    UPDATE_PLACE("maps/api/place/update/json"),
    DELETE_PLACE("maps/api/place/delete/json");

    private final String endpoint;

    PlaceEndpoints(String endpoint)
    {
        this.endpoint = endpoint;
    }

}
