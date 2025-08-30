package constants;
public enum GoogleMapsEndpoint 
{
            POSTPLACE("maps/api/place/add/json"),
            GETPLACE("maps/api/place/get/json"),
            PUTPLACE("maps/api/place/update/json"),
            DELETEPLACE("maps/api/place/delete/json");
	        public final String resource;
	        GoogleMapsEndpoint(String resource)
	        {
	        	this.resource = resource;
	        }
	        public String getResource()
	        {
	        	return resource;
	        }
}
