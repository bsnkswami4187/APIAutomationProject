package utilities;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManagerSetName
{
    public static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    public static void setTest(ExtentTest test)
    {
        extentTest.set(test);
    }
    public static ExtentTest getTest()
    {
        return extentTest.get();
    }
    public static void unload()
    {
        extentTest.remove();
    }
}
