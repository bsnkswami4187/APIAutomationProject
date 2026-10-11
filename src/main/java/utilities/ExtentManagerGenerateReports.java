package utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class ExtentManagerGenerateReports
{
    public static final Logger logger = LoggerFactory.getLogger(ExtentManagerGenerateReports.class);
    private ExtentManagerGenerateReports()
    {

    }
    public static ExtentReports positiveExtentReports;
    public static ExtentReports negativeExtentReports;
    static
    {
        try
        {
            Files.createDirectories(Paths.get("reports/positive"));
            Files.createDirectories(Paths.get("reports/negative"));
        }
        catch (Exception e)
        {
            throw new RuntimeException(" Failed to create base report folders ", e);
        }
    }
    public static ExtentReports createReport(String folderName)
    {
        try
        {
            Files.createDirectories(Paths.get("reports", folderName));
        }
        catch (Exception e)
        {
            throw new RuntimeException(" Failed to create reports directory ", e);
        }
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy_HH-mm-ss"));
        String reportPath = Paths.get("reports", folderName, " ExtentReport_ " + timeStamp + ".html").toString();
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle(" Extent Report - " + folderName.toUpperCase());
        spark.config().setReportName(" Google Maps API Automation - " + folderName.toUpperCase() + " tests ");
        spark.config().setTheme(Theme.STANDARD);
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(spark);
        logger.info(" Extent Report created at {}", reportPath);
        return extentReports;
    }
    public static synchronized ExtentReports getExtentReports(String testType)
    {
        boolean isPositive = "positive".equalsIgnoreCase(testType);
        boolean isNegative = "negative".equalsIgnoreCase(testType);
        if(!isPositive && !isNegative)
        {
            throw new IllegalArgumentException(" Invalid test type : " + testType);
        }
        if(isPositive && positiveExtentReports == null)
        {
            positiveExtentReports = createReport("positive");
        }
        if(isNegative && negativeExtentReports == null)
        {
            negativeExtentReports = createReport("negative");
        }
        return isPositive ? positiveExtentReports : negativeExtentReports;
    }
    public static synchronized void flushReports()
    {
        if(positiveExtentReports != null)
        {
            positiveExtentReports.flush();
            positiveExtentReports = null;
            logger.info(" Positive extent report flushed ");
        }
        if(negativeExtentReports != null)
        {
            negativeExtentReports.flush();
            negativeExtentReports = null;
            logger.info(" Negative extent report flushed ");
        }
    }
}
