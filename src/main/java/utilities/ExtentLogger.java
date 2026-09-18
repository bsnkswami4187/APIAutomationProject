package utilities;
import static utilities.ExtentTestManager.*;
import static utilities.ExtentTestManager.getTest;

public class ExtentLogger
{
    public static void pass(String message)
    {
        getTest().pass(message);
    }
    public static void fail(String message)
    {
        getTest().fail(message);
    }
    public static void info(String message)
    {
        getTest().info(message);
    }
    public static void warning(String message)
    {
        getTest().warning(message);
    }
}
