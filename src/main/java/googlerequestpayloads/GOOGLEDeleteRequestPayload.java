package googlerequestpayloads;
public class GOOGLEDeleteRequestPayload 
{
      private String place_id;
      public GOOGLEDeleteRequestPayload(String place_id)
      {
    	  this.place_id = place_id;
      }
	  public String getPlace_id() 
	  {
		return place_id;
	  }
	  @Override
	  public String toString() 
	  {
	   return "{\r\n"
		  		+"\"place_id\": \"" + place_id + "\"\r\n"
		  		+"}";
	  }	  
}
