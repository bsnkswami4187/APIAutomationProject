package hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.Helper;
public class Hooks 
{
	   public static ThreadLocal<Scenario> threadScenario = new ThreadLocal<Scenario>();
       @Before
       public void before(Scenario scenario)
       {
    	   Helper.deleteFolder("./logs");
    	   threadScenario.set(scenario);
       }
       @After
       public void after()
       {
    	   
       }
}
