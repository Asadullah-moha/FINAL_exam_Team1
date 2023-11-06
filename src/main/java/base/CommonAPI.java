package base;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class CommonAPI {
    Logger LOG = LogManager.getLogger(CommonAPI.class.getName());
    private Object Utility;
    public Properties prop;
    public WebDriver driver;
    int implicitWait = Integer.parseInt(prop.getProperty("wait.time", "10"));
    String windowMaximize = prop.getProperty("window.maximize", "true");
    String username = prop.getProperty("browserstack.username");
    String password = prop.getProperty("browserstack.key");

    //String takeScreenshot = Utility.getProperties().getProperty("take.screenshot", "false");
    //String headlessMode = Utility.getProperties().getProperty("headless.mode", "false");

    //report setup from line 48 to 105
    public static com.relevantcodes.extentreports.ExtentReports extent;

    public CommonAPI() throws InterruptedException {
        Utility.wait();
    }

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }
    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
//        if (takeScreenshot.equalsIgnoreCase("true")){
//            if (result.getStatus() == ITestResult.FAILURE) {
//                takeScreenshot(result.getName());
//            }
//        }
        driver.quit();
    }
    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public void getCloudDriver(String envName, String os, String osVersion, String browserName, String browserVersion) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("os", os);
        cap.setCapability("os_version", osVersion);
        cap.setCapability("browser", browserName);
        if (envName.equalsIgnoreCase("browserstack")){
            cap.setCapability("browser_version", browserVersion);
            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@hub-cloud.browserstack.com:80/wd/hub"), cap);
        }else if (envName.equalsIgnoreCase("saucelabs")){
            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@ondemand.saucelabs.com:80/wd/hub"), cap);
        }
    }
    public void getLocalDriver(String browserName){
        if (browserName.equalsIgnoreCase("chrome")){
            //launch the browser
            driver = new ChromeDriver();
            LOG.info("chrome browser launched");
        }else if (browserName.equalsIgnoreCase("firefox")){
            //launch the browser
            driver = new FirefoxDriver();
            LOG.info("firefox browser launched");
            System.out.println();
        }else if (browserName.equalsIgnoreCase("edge")){
            //launch the browser
            driver = new EdgeDriver();
            LOG.info("edge browser launched");
        }
    }

    @Parameters({"useCloudEnv","envName","os","osVersion","browserName","browserVersion","url"})
    @BeforeMethod
    public  void setUp(@Optional("false") boolean useCloudEnv, @Optional("browserstack") String envName, @Optional("windows") String os,
                       @Optional("10") String osVersion, @Optional("chrome") String browserName, @Optional("110") String browserVersion,
                       @Optional("https://www.google.com") String url) throws MalformedURLException
    {
        if (useCloudEnv){
            getCloudDriver(os, osVersion, browserName, browserVersion, envName);
        }else {
            getLocalDriver(browserName);
        }
        //Maximize window
        if(windowMaximize.equalsIgnoreCase("true")){
            driver.manage().window().maximize();
            LOG.info("window maximize");
        }

        //set the implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        LOG.info("implicit wait set to " + implicitWait);

        // Navigate to the website
        driver.get(url);
        LOG.info("navigate to "+url+" ...");
    }
    //------------------------------------------------------------------------------------------------------------------
    //reusable methods (non page object model methods)
    //------------------------------------------------------------------------------------------------------------------

    public WebDriver getDriver() {
        return driver;
    }

    public String getCurrentTitle(){
        return driver.getTitle();
    }
    public String getElementTextNPOM(String cssOrXpath){
        try {
            return driver.findElement(By.cssSelector(cssOrXpath)).getText();
        }catch (Exception e){
            return driver.findElement(By.xpath(cssOrXpath)).getText();
        }
    }
    public void clickOnNPOM(String cssOrXpath){
        try {
            driver.findElement(By.cssSelector(cssOrXpath)).click();
        }catch (Exception e){
            driver.findElement(By.xpath(cssOrXpath)).click();
        }
    }
    public void typeNPOM(String cssOrXpath, String text){
        try {
            driver.findElement(By.cssSelector(cssOrXpath)).sendKeys(text);
        }catch (Exception e){
            driver.findElement(By.xpath(cssOrXpath)).sendKeys(text);
        }
    }
    public void hoverOverNPOM(String cssOrXpath){
        Actions actions = new Actions(driver);
        try {
            WebElement element = driver.findElement(By.cssSelector(cssOrXpath));
            actions.moveToElement(element).build().perform();
        }catch (Exception e){
            WebElement element = driver.findElement(By.xpath(cssOrXpath));
            actions.moveToElement(element).build().perform();
        }
    }
    public void selectDropdownOptionNPOM(String cssOrXpath, String option){
        try {
            WebElement dropdown = driver.findElement(By.cssSelector(cssOrXpath));
            Select select = new Select(dropdown);
            try {
                select.selectByValue(option);
            }catch (Exception e){
                select.selectByVisibleText(option);
            }
        }catch (Exception e){
            WebElement dropdown = driver.findElement(By.xpath(cssOrXpath));
            Select select = new Select(dropdown);
            try {
                select.selectByValue(option);
            }catch (Exception ex){
                select.selectByVisibleText(option);
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //reusable methods (page object model methods)
    //------------------------------------------------------------------------------------------------------------------
    public String getElementText(WebElement element){
        return element.getText();
    }
    public void clickOn(WebElement element){
        element.click();
    }
    public void type(WebElement element, String text){
        element.sendKeys(text);
    }
    public void hoverOver(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
    public void selectDropdownOption(WebElement element, String option){
        Select select = new Select(element);
        try {
            select.selectByValue(option);
        }catch (Exception e){
            select.selectByValue(option);
        }
    }
}