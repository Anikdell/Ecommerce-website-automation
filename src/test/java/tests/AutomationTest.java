package tests;

import utils.DriverUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.*;
import java.time.Duration;
import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AutomationTest {
    WebDriver driver;
    Properties prop = new Properties();
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void setupReport() throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Test Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        FileInputStream fis = new FileInputStream("src/test/java/tests/config.properties");
        prop.load(fis);
    }

    @BeforeMethod
    public void setup() {
        driver = DriverUtil.getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void runTest() throws InterruptedException, IOException {
        test = extent.createTest("Automation Exercise Test");
        try {
            driver.get("https://www.automationexercise.com/");
            Thread.sleep(5000);
            takeScreenshot("01_Homepage");

            clickByXpath("//a[@href='/login']", "02_LoginPage");
            WebElement el9060 = driver.findElement(By.xpath("//*[@id='form']/div/div/div[1]/div/form/input[2]"));
highlightElement(driver, el9060);
el9060.sendKeys(prop.getProperty("email"));
String screenshotPath1 = takeScreenshot(driver, "el9060");
test.log(Status.INFO, "Performed action with screenshot").addScreenCaptureFromPath(screenshotPath1);

            WebElement el6526 = driver.findElement(By.xpath("//*[@id='form']/div/div/div[1]/div/form/input[3]"));
highlightElement(driver, el6526);
el6526.sendKeys(prop.getProperty("password"));
String screenshotPath2 = takeScreenshot(driver, "el6526");
test.log(Status.INFO, "Performed action with screenshot").addScreenCaptureFromPath(screenshotPath2);

                        WebElement el9024 = driver.findElement(By.xpath("//*[@id='form']/div/div/div[1]/div/form/button"));
highlightElement(driver, el9024);
el9024.click();
String screenshotPath3 = takeScreenshot(driver, "el9024");
test.log(Status.INFO, "Performed action with screenshot").addScreenCaptureFromPath(screenshotPath3);

            Thread.sleep(2000);
            takeScreenshot("03_AfterLogin");

            clickByXpath("//*[@id='header']/div/div/div/div[2]/div/ul/li[2]/a", "04_Products");
            WebElement el7576 = driver.findElement(By.xpath("//*[@id='search_product']"));
highlightElement(driver, el7576);
el7576.sendKeys("Tshirt");
String screenshotPath4 = takeScreenshot(driver, "el7576");
test.log(Status.INFO, "Performed action with screenshot").addScreenCaptureFromPath(screenshotPath4);

            Thread.sleep(2000);
            clickByXpath("//*[@id='submit_search']", "05_SearchSubmit");
            Thread.sleep(2000);
            clickByXpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/a", "06_AddToCart");
            Thread.sleep(2000);
            clickByXpath("//*[@id='cartModal']/div/div/div[3]/button", "07_ContinueShopping");
            Thread.sleep(2000);
            clickByXpath("//*[@id='header']/div/div/div/div[2]/div/ul/li[3]/a", "08_GoToCart");
            Thread.sleep(2000);
            clickByXpath("//*[@id='do_action']/div[1]/div/div/a", "09_ProceedToCheckout");
            Thread.sleep(2000);
            clickByXpath("//*[@id='cart_items']/div/div[7]/a", "10_PlaceOrder");
            Thread.sleep(2000);
            clickByXpath("//*[@id='header']/div/div/div/div[2]/div/ul/li[1]", "11_HomeBack");
            Thread.sleep(2000);
            clickByXpath("//*[@id='header']/div/div/div/div[2]/div/ul/li[4]/a", "12_Logout");
            Thread.sleep(2000);
            clickByXpath("//*[@id='header']/div/div/div/div[2]/div/ul/li[1]", "13_HomeBackFinal");

            test.pass("Test completed successfully.");
        } catch (Exception e) {
            takeScreenshot("Error_Screenshot");
            test.fail("Test failed: " + e.getMessage());
        }
    }

    public void clickByXpath(String xpath, String screenshotName) throws InterruptedException, IOException {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        highlight(xpath);
        element.click();
        Thread.sleep(2000);
        takeScreenshot(screenshotName);
    }

    public void highlight(String xpath) {
        WebElement el = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", el);
    }

    public void takeScreenshot(String name) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshots/" + name + ".png");
        Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterTest
    public void finishReport() {
        extent.flush();
    }

public void highlightElement(WebDriver driver, WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].style.border='3px solid red'; arguments[0].style.background='yellow'", element);
}


public String takeScreenshot(WebDriver driver, String screenshotName) {
    try {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = "test-output/screenshots/" + screenshotName + ".png";
        File destination = new File(dest);
        destination.getParentFile().mkdirs();
        Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return dest;
    } catch (Exception e) {
        System.out.println("Exception while taking screenshot: " + e.getMessage());
        return null;
    }
}

}
