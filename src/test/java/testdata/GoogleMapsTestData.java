package testdata;
import randomutilities.RandomGoogleMaps;
import java.util.ArrayList;
public class GoogleMapsTestData extends RandomGoogleMaps
{
//Location Parameters
double lat = getLatLng(-20, 30);
double lng = getLatLng(-10, 20);
//POST Request Payload Paremeters
int accuracy = getAccuracy(15, 20);
String name = getName();
String phone_number = getPhoneNumber();
String address = getAddress();
ArrayList<String> types = getTypes(2);
String website = getWebsite();
String language = getLanguage();
//PUT Request Payload Parameters
String updatedAddress = getAddress();
}
