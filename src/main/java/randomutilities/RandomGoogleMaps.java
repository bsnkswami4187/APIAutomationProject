package randomutilities;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class RandomGoogleMaps 
{
	public double getLatLng(double min,double max)
    {
    	return ThreadLocalRandom.current().nextDouble(min, max);
    }
    public int getAccuracy(int min,int max)
    {
    	Random r = new Random();
    	return r.nextInt(max - min + 1) + min;
    }
    public String getName()
    {
    	String [] adjectives = {"Frontline","Modern","Golden","Silver","Royal","Sunrise","Emerald","Tranquil"};
    	String [] nouns = {"House","Villa","Mansion","Heaven","Retreat","Nest","Cottage","Estate"};
    	Random r = new Random();
    	String adjective = adjectives[r.nextInt(adjectives.length)];
    	String noun = nouns[r.nextInt(nouns.length)];
    	return adjective + " " + noun;
    }
    public String getPhoneNumber()
    {
    	Random r = new Random();
    	int part1 = 900 + r.nextInt(100);
    	int part2 = 100 + r.nextInt(900);
    	int part3 = 1000 + r.nextInt(9000);
    	return String.format("(+91) %d %d %d", part1, part2, part3);
    }
    public String getAddress()
    {
    	String[] streetNames = {"Maple", "Oak", "Pine", "Cedar", "Elm", "Willow", "Main", "Sunset", "River"};
        String[] streetTypes = {"Street", "Avenue", "Boulevard", "Lane", "Road", "Drive", "Terrace"};
        String[] cityNames = {"Springfield", "Riverside", "Greenville", "Lakeside", "Fairview", "Hillcrest"};
        String[] states = {"California", "Texas", "New York", "Florida", "Illinois", "Ohio"};
        String[] countryCodes = {"USA", "UK", "Canada", "Australia", "India"};
        Random r = new Random();
        int streetNumber = 100 + r.nextInt(900);
        String streetName = streetNames[r.nextInt(streetNames.length)];
        String streetType = streetTypes[r.nextInt(streetTypes.length)];
        String city = cityNames[r.nextInt(cityNames.length)];
        String state = states[r.nextInt(states.length)];
        String country = countryCodes[r.nextInt(countryCodes.length)];
        int zipCode = 10000 + r.nextInt(90000);
        return String.format("%d %s %s, %s, %s, %s - %d", streetNumber, streetName, streetType, city, state, country, zipCode);                              
    }
    public ArrayList<String> getTypes(int count)
    {
    	ArrayList<String> typeList = new ArrayList<String>();
    	Random r = new Random();
    	String [] adjectives = {"Sunny","Green","Silver","Golden","Quiet","Tranquil","Hidden","Serene"};
    	String [] nouns = {"Meadow","Heaven","Valley","Retreat","Grove","Pond","Village","Hills"};
    	for(int i = 0; i < count; i++)
    	{
    		String adjective = adjectives[r.nextInt(adjectives.length)];
    		String noun = nouns[r.nextInt(nouns.length)];
    		typeList.add(adjective + " " + noun);
    	}
    	return typeList;
    }
    public String getWebsite()
    {
    	String[] domains = {"google", "facebook", "amazon", "microsoft", "apple", "netflix", "twitter", "linkedin"};
        String[] tlds = {".com", ".net", ".org", ".io", ".co", ".tech"};
        Random r = new Random();
        String domain = domains[r.nextInt(domains.length)];
        String tld = tlds[r.nextInt(tlds.length)];
        return "http://" + domain + tld;
    }
    public String getLanguage()
    {
    	String[] languages = {"English", "French", "Spanish", "German", "Italian", "Portuguese", "Dutch", "Hindi", "Japanese"};
        String[] countryCodes = {"US", "UK", "FR", "DE", "IN", "ES", "IT", "BR", "NL", "JP"};
        Random r = new Random();
        String language = languages[r.nextInt(languages.length)];
        String countryCode = countryCodes[r.nextInt(countryCodes.length)];
        return language + "-" + countryCode;
    }
}
