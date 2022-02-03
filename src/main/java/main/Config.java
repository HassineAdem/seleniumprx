package main;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStepResult;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.errorprone.annotations.DoNotCall;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;

import org.openqa.selenium.remote.codec.w3c.W3CHttpCommandCodec;
import org.openqa.selenium.remote.codec.w3c.W3CHttpResponseCodec;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class Config {
    public static String CLASS_NAME;
    public final Logger logger;
    public WebDriver driver;
    public ExtentSparkReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;
    public ChromeOptions options;
    public Actions actionG;
    public WebDriverWait loaderWait;
    public WebDriverWait pageLoadWait;
    public WebDriverWait wait;
    public DateFormat dateFormat;
    public DateFormat timeFormat;
    public Date date;
    public int count;
    public int screenShotCount;
    public URL SERVER_URL;
    String propFileName;
    InputStream inputStream;
    protected Properties prop;
    public List<TestCaseStepResult> testCaseStepResultList;
    public String DEV_KEY;
    public String PROJECT_NAME;
    public String PPROJECT_PREFIX;
    public String PLAN_NAME;
    public Integer PLAN_ID;
    public String BUILD_NAME;
    public Integer BUILD_ID;
    public String PLATFORM_NAME;
    public Integer PLATFORM_ID;
    public Integer caseId;
    public String note;
    public String tlinkNote;
    public ExecutionStatus result2;
    public static String cookieName;
    public static String sessionId;
    public static String cookieDomain;
    public static Cookie cookie;
    public static Cookie sessionCookie;
    public static Set<Cookie> cookies;
    String reportName;
    String charset;
    String param;
    File fileToUpload;
    String boundary;
    String CRLF;

    public Config() {
        System.out.println("Logger set config file");
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        this.logger = LogManager.getLogger(this.getClass().getName());
        this.date = new Date();
        this.count = 0;
        this.screenShotCount = 0;
        this.propFileName = System.getenv("SELENIUM_CONFIG");
        System.out.println("propFile= " + this.propFileName);
        this.prop = new Properties();
        this.dateFormat = new SimpleDateFormat(this.prop.getProperty("DATE_FORMAT", "yyyyMMdd-HHmmss"));
        this.logger.trace("Setting date format");
        this.timeFormat = new SimpleDateFormat(this.prop.getProperty("TIME_FORMAT", "HH:mm"));
        this.logger.trace("Setting time format");

        try {
            this.inputStream = new FileInputStream(this.propFileName);
        } catch (FileNotFoundException var4) {
            var4.printStackTrace();
        }

        try {
            this.prop.load(this.inputStream);
        } catch (IOException var3) {
            System.out.println(this.dateFormat.format(new Date()) + " | " + "Error loading configuration file : " + this.propFileName);
        }

        if (this.prop.getProperty("TLINK").equals("TRUE")) {
            this.DEV_KEY = this.prop.getProperty("TLINK_DEV_KEY");
            this.logger.trace("DEV_KEY set to " + this.DEV_KEY);
        }

        if (this.prop.getProperty("TLINK").equals("TRUE")) {
            try {
                URL URL = new URL(this.prop.getProperty("TLINK_SERVER_URL"));
                this.SERVER_URL = URL;
            } catch (MalformedURLException var2) {
                System.out.println(this.dateFormat.format(new Date()) + " | " + "SERVER_URL MalformedURLException");
            }
        }

        if (this.prop.getProperty("TLINK").equals("TRUE")) {
            this.PROJECT_NAME = this.prop.getProperty("TLINK_PROJECT_NAME");
            this.PPROJECT_PREFIX = this.prop.getProperty("TLINK_PPROJECT_PREFIX");
            this.PLAN_NAME = this.prop.getProperty("TLINK_PLAN_NAME");
            this.PLAN_ID = Integer.parseInt(this.prop.getProperty("TLINK_PLAN_ID"));
            this.BUILD_NAME = this.prop.getProperty("TLINK_BUILD_NAME");
            this.BUILD_ID = Integer.parseInt(this.prop.getProperty("TLINK_BUILD_ID"));
            this.PLATFORM_NAME = this.prop.getProperty("TLINK_PLATFORM_NAME");
            this.PLATFORM_ID = Integer.parseInt(this.prop.getProperty("TLINK_PLATFORM_ID"));
        }

        this.reportName = this.PROJECT_NAME + this.dateFormat.format(this.date) + ".html";
        this.charset = "UTF-8";
        this.param = "value";
        this.fileToUpload = new File("/test-output/" + this.reportName);
        this.boundary = Long.toHexString(System.currentTimeMillis());
        this.CRLF = "\r\n";
    }

    @BeforeTest
    public void setUp() {
        System.out.println("PASSED HERE");
        this.htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + this.prop.getProperty("PROJECT_PREFIX") + this.dateFormat.format(this.date) + ".html");
        this.htmlReporter.config().setDocumentTitle(this.prop.getProperty("EXTENT_DOCUMENT_TITLE"));
        this.htmlReporter.config().setReportName(this.prop.getProperty("EXTENT_REPORT_NAME"));
        if (this.prop.getProperty("EXTENT_THEME").equals("DARK")) {
            this.htmlReporter.config().setTheme(Theme.DARK);
        } else {
            this.htmlReporter.config().setTheme(Theme.STANDARD);
        }

        this.extent = new ExtentReports();
        this.extent.attachReporter(new ExtentObserver[]{this.htmlReporter});
        this.extent.setSystemInfo("OS", this.prop.getProperty("EXTENT_OS"));
        this.extent.setSystemInfo("Host Name", this.prop.getProperty("EXTENT_HOST_NAME"));
        this.extent.setSystemInfo("Environment", this.prop.getProperty("EXTENT_ENVIRONMENT"));
        this.extent.setSystemInfo("User Name", this.prop.getProperty("EXTENT_USER_NAME"));
    }

    @DoNotCall
    public void updateTestLinkResult(Integer caseId, Integer PLATFORM_ID, String PLATFORM_NAME, String note) throws TestLinkAPIException {
        new TestLinkAPI(this.SERVER_URL, this.DEV_KEY);
    }

    @BeforeTest
    public void lookups() {
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == 2) {
            this.test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED : ", ExtentColor.RED));
            Reporter.setCurrentTestResult(result);
        } else if (result.getStatus() == 1) {
            this.test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test case PASSED : ", ExtentColor.GREEN));
        } else {
            this.test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test case SKIPPED : ", ExtentColor.YELLOW));
        }

    }

    @AfterTest
    public void tearDown() {

        System.out.println("HELLOOOO");
        this.extent.flush();
    }



    public void reportPassStatus(String reportMsg, String tLinkNote, Integer caseId, Integer PLATFORM_ID, String PLATFORM_NAME) {
        this.test.log(Status.PASS, reportMsg);
        Reporter.getCurrentTestResult().setStatus(1);
        this.result2 = ExecutionStatus.PASSED;
        if (this.prop.getProperty("TLINK").equals("TRUE")) {
            this.updateTestLinkResult(caseId, PLATFORM_ID, PLATFORM_NAME, tLinkNote);
        }

    }

    public void reportFailStatus(String reportMsg, String tLinkNote, Integer caseId, Integer PLATFORM_ID, String PLATFORM_NAME) {
        this.test.log(Status.FAIL, reportMsg);
        this.logger.trace("Test failed : " + reportMsg);
        Reporter.getCurrentTestResult().setStatus(2);
        this.result2 = ExecutionStatus.FAILED;
        if (this.prop.getProperty("TLINK").equals("TRUE")) {
            this.updateTestLinkResult(caseId, PLATFORM_ID, PLATFORM_NAME, tLinkNote);
        }

        try {
            Assert.fail("Failed");
        } catch (Exception var7) {
            this.logger.error(var7);
        }

    }

    public void reportBlockedStatus(String reportMsg, String tLinkNote, Integer caseId, Integer PLATFORM_ID, String PLATFORM_NAME) {
        this.test.log(Status.FAIL, reportMsg);
        this.logger.trace("Test blocked : " + reportMsg);
        Reporter.getCurrentTestResult().setStatus(2);
        this.result2 = ExecutionStatus.BLOCKED;
        if (this.prop.getProperty("TLINK").equals("TRUE")) {
            this.updateTestLinkResult(caseId, PLATFORM_ID, PLATFORM_NAME, tLinkNote);
        }

        try {
            Assert.fail("Blocked");
        } catch (Exception var7) {
        }

    }

    public void noSuchElementFail() {
        this.test.log(Status.FAIL, "Element not found");
        this.logger.trace("Element not found");
        Reporter.getCurrentTestResult().setStatus(2);
        this.reportFailStatus("", "", this.caseId, this.PLATFORM_ID, this.PLATFORM_NAME);
    }

    public void noSuchElementsFail() {
        this.test.log(Status.FAIL, "Elements not found");
        this.logger.trace("Elements not found");
        Reporter.getCurrentTestResult().setStatus(2);
    }

    public void notClickableElement() {
        this.test.log(Status.FAIL, "Element not clickable");
        this.logger.trace("Element not clickable");
        Reporter.getCurrentTestResult().setStatus(2);
    }

    public void notInvisibleElement() {
        this.test.log(Status.FAIL, "Element is still visible when it should be invisible");
        this.logger.trace("Element is still visible when it should be invisible");
        Reporter.getCurrentTestResult().setStatus(2);
    }

    public boolean isClickable(WebElement webElement) {
        this.logger.trace("Checking if webElement is clickable");

        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(webElement));
            this.logger.trace("WebElement clickable");
            return true;
        } catch (NoSuchElementException var3) {
            this.logger.fatal("NoSuchElementException");
            return false;
        } catch (TimeoutException var4) {
            this.logger.fatal("TimeoutException");
            return false;
        }
    }

    public boolean isVisible(WebElement webElement) {
        this.logger.trace("Checking if webElement is visible");

        try {
            if (webElement.isDisplayed()) {
                return true;
            } else {
                this.wait.until(ExpectedConditions.visibilityOf(webElement));
                this.logger.trace("WebElement visible");
                return true;
            }
        } catch (TimeoutException var3) {
            this.logger.trace("TimeoutException");
            return false;
        } catch (NullPointerException var4) {
            this.logger.trace("NullPointerException");
            return false;
        } catch (WebDriverException var5) {
            this.logger.trace("NullPointerException");
            return false;
        }
    }

    public boolean isInvisible(WebElement webElement) {
        this.logger.trace("Checking if webElement is invisible");

        try {
            if (webElement.isDisplayed()) {
                this.wait.until(ExpectedConditions.invisibilityOf(webElement));
            }

            this.logger.trace("WebElement Invisible");
            return true;
        } catch (TimeoutException var3) {
            return false;
        } catch (NullPointerException var4) {
            return false;
        }catch (InvalidSelectorException ex){
            return true;
        }
    }

    public boolean isEnabled(WebElement webElement) {
        this.logger.trace("Checking if webElement is enabled");
        if (webElement.isEnabled()) {
            this.logger.trace("webElement is enabled");
            return true;
        } else {
            this.logger.trace("webElement is not enabled");
            return false;
        }
    }

    public void clickElement(WebElement webElement) {
        this.logger.trace("Clicking WebElement");
        if (!webElement.equals((Object)null)) {
            if (this.isVisible(webElement) && this.isClickable(webElement)) {
                try {
                    this.logger.trace("Clicking WebElement (selenium click)");
                    webElement.click();
                    this.logger.trace("WebElement clicked (selenium click)");
                } catch (Exception var5) {
                    this.logger.fatal("FAILED (selenium click)");

                    try {
                        this.logger.trace("Clicking WebElement (ACTION click)");
                        this.actionG.moveToElement(webElement);
                        this.actionG.click();
                        this.actionG.build().perform();
                        this.logger.trace("WebElement clicked (ACTION click)");
                    } catch (Exception var4) {
                        this.logger.fatal("FAILED (ACTION click)");
                        this.logger.trace("Performing javascript click");
                        this.jsClick(webElement);
                    }
                }
            } else {
                this.logger.trace("WebElement not visible or not clickable");
            }
        } else {
            this.logger.trace("WebElement is null");
        }

    }

    public void testInfoPass(String msg) {
        this.logger.trace("[info]Test step pass : " + msg);
        this.test.log(Status.PASS, msg);
    }

    public void testInfoFail(String msg) {
        this.logger.trace("[info]Test step fail : " + msg);
        this.test.log(Status.FAIL, msg);
        Assert.fail("Failed");
    }

    public void testInfo(String msg) {
        this.test.log(Status.INFO, msg);
    }

    public void testAlert(String msg) {
        this.logger.trace("[info]Test step alert : " + msg);
        this.test.log(Status.WARNING, msg);
    }

    public String getElementAttribute(WebElement webElement, String attribute) {
        this.logger.trace("Getting element attribute " + attribute);
        String value = null;

        try {
            value = webElement.getAttribute(attribute);
            this.logger.trace("Value of " + attribute);
        } catch (NoSuchElementException var5) {
            this.logger.fatal("NoSuchElementException");
        }

        return value;
    }

    public WebElement findElementByXpath(String xpath) {
        this.logger.trace("Attemting to find WebElement by xpath @xpath: " + xpath);
        WebElement webElement = null;

        try {
            webElement = this.driver.findElement(By.xpath(xpath));
            this.logger.trace("WebElement found by xpath @xpath: " + xpath);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @xpath: " + xpath);
        }

        return webElement;
    }

    public WebElement findElementById(String id) {
        WebElement webElement = null;

        try {
            webElement = this.driver.findElement(By.id(id));
            this.logger.trace("WebElement found by id @id: " + id);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @id: " + id);
        }

        return webElement;
    }

    public WebElement findElementByClassName(String className) {
        WebElement webElement = null;

        try {
            webElement = this.driver.findElement(By.className(className));
            this.logger.trace("WebElement found by className @className: " + className);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @className: " + className);
        }

        return webElement;
    }

    public WebElement findElementByCssSelector(String css) {
        WebElement webElement = null;

        try {
            webElement = this.driver.findElement(By.cssSelector(css));
            this.logger.trace("WebElement found by cssSelector @cssSelector: " + css);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @cssSelector: " + css);
        }

        return webElement;
    }

    public WebElement findElementByLinkText(String linkText) {
        WebElement webElement = null;

        try {
            webElement = this.driver.findElement(By.linkText(linkText));
            this.logger.trace("WebElement found by linkText @linkText: " + linkText);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @linkText: " + linkText);
        }

        return webElement;
    }

    public WebElement findElementByName(String name) {
        WebElement webElement = null;

        try {
            webElement = this.driver.findElement(By.name(name));
            this.logger.trace("WebElement found by name @name: " + name);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @name: " + name);
        }

        return webElement;
    }

    public WebElement findElementByTagName(String tagName) {
        WebElement webElement = null;

        try {
            webElement = this.driver.findElement(By.tagName(tagName));
            this.logger.trace("WebElement found by tagName @tagName: " + tagName);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @tagName: " + tagName);
        }

        return webElement;
    }

    public List<WebElement> findElementsByXpath(String xpath) {
        List webElementList = null;

        try {
            webElementList = this.driver.findElements(By.xpath(xpath));
            this.logger.trace("WebElements found by xpath @xpath: " + xpath);
            this.logger.trace(webElementList);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @xpath: " + xpath);
        }

        return webElementList;
    }

    public List<WebElement> findElementsById(String id) {
        List webElementList = null;

        try {
            webElementList = this.driver.findElements(By.id(id));
            this.logger.trace("WebElements found by id @id: " + id);
            this.logger.trace(webElementList);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @id: " + id);
        }

        return webElementList;
    }

    public List<WebElement> findElementsByClassName(String className) {
        List webElementList = null;

        try {
            webElementList = this.driver.findElements(By.className(className));
            this.logger.trace("WebElements found by className @className: " + className);
            this.logger.trace(webElementList);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @className: " + className);
        }

        return webElementList;
    }

    public List<WebElement> findElementsByCssSelector(String css) {
        List webElementList = null;

        try {
            webElementList = this.driver.findElements(By.cssSelector(css));
            this.logger.trace("WebElements found by cssSelector @cssSelector: " + css);
            this.logger.trace(webElementList);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @cssSelector: " + css);
        }

        return webElementList;
    }

    public List<WebElement> findElementsByLinkText(String linkText) {
        List webElementList = null;

        try {
            webElementList = this.driver.findElements(By.linkText(linkText));
            this.logger.trace("WebElements found by linkText @linkText: " + linkText);
            this.logger.trace(webElementList);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @linkText: " + linkText);
        }

        return webElementList;
    }

    public List<WebElement> findElementsByName(String name) {
        List webElementList = null;

        try {
            webElementList = this.driver.findElements(By.name(name));
            this.logger.trace("WebElements found by name @name: " + name);
            this.logger.trace(webElementList);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @name: " + name);
        }

        return webElementList;
    }

    public List<WebElement> findElementsByTagName(String tagName) {
        List webElementList = null;

        try {
            webElementList = this.driver.findElements(By.tagName(tagName));
            this.logger.trace("WebElements found by tagName @tagName: " + tagName);
            this.logger.trace(webElementList);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @tagName: " + tagName);
        }

        return webElementList;
    }

    public void jsClick(WebElement webElement) {
        this.logger.trace("jsClick on WebElement");
        String js = "arguments[0].click();";
        ((JavascriptExecutor)this.driver).executeScript(js, new Object[]{webElement});
        System.out.println(this.dateFormat.format(new Date()) + " | " + webElement + "  Clicked");
    }

    public WebElement findByText(String text, int position, boolean log) {
        this.logger.trace("Find WebElement by text @text: " + text + " in a list with position: " + position);
        List<WebElement> elements = this.driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        WebElement e1;
        if (log) {
            Iterator var6 = elements.iterator();

            while(var6.hasNext()) {
                e1 = (WebElement)var6.next();
                System.out.println(this.dateFormat.format(new Date()) + " | " + e1.getText());
            }
        }

        if (position == 0) {
            position = elements.size();
        }

        e1 = null;
        int i = 0;
        Iterator var8 = elements.iterator();

        while(var8.hasNext()) {
            WebElement e = (WebElement)var8.next();
            ++i;
            if (i == position) {
                e1 = e;
                break;
            }
        }

        return e1;
    }

    public void threadSleep(long time) {
        try {
            Thread.sleep(time);
            this.logger.trace("Thread is sleeping for " + time + "ms");
        } catch (Exception var4) {
            this.logger.fatal("Thread couldn't sleep");
        }

    }

    public long fileSize(String url) {
        this.logger.trace("Getting file size (MB) @url: " + url);
        File file = new File(url);
        long fileSizeInBytes = file.length();
        this.logger.trace("File size (Byte): " + fileSizeInBytes);
        long fileSizeInKB = fileSizeInBytes / 1024L;
        this.logger.trace("File size (KB): " + fileSizeInKB);
        long fileSizeInMB = fileSizeInKB / 1024L;
        this.logger.trace("File size (MB): " + fileSizeInMB);
        return fileSizeInMB;
    }

    public String getFileExtension(String url) {
        String fullName = null;
        File file = new File(url);
        fullName = file.getName();
        if (fullName.equals("") && fullName.equals((Object)null)) {
            return null;
        } else {
            String fileName = (new File(fullName)).getName();
            int dotIndex = fileName.lastIndexOf(46);
            return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
        }
    }

    public void click() {
        this.logger.trace("Attempting a random click action");

        try {
            Robot robot = new Robot();
            robot.mouseMove(200, 70);
            this.actionG.click().build().perform();
            this.logger.trace("Random click action performed");
        } catch (AWTException var2) {
        }

    }

    public String getScreenhot(String screenshotName) {
        String dateName = (new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date());
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = (File)ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);

        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return destination;
    }

    @DoNotCall
    public String onlyNumbers(String string) {
        this.logger.trace("Converting String to numbersOnly @String: " + string);
        string = string.replaceAll("\\D+", "");
        this.logger.trace("Convesion result: " + string);
        return string;
    }

    public Integer stringToInt(String string) {
        this.logger.trace("Converting String to integer @String: " + string);
        Integer result = Integer.valueOf(string);
        this.logger.trace("Convesion result: " + result);
        return result;
    }

    public WebElement findParent(WebElement childElement) {
        WebElement parentElement = childElement.findElement(By.xpath("./.."));
        this.logger.trace("Parent element found for childElement");
        return parentElement;
    }

    @DoNotCall
    public static RemoteWebDriver createDriverFromSession(final String sessionId, URL command_executor) {
        CommandExecutor executor = new HttpCommandExecutor(command_executor) {
            public Response execute(Command command) throws IOException {
                Response response = null;
                if (command.getName() == "newSession") {
                    response = new Response();
                    response.setSessionId(sessionId.toString());
                    response.setStatus(0);
                    response.setValue(Collections.emptyMap());

                    try {
                        Field commandCodec = null;
                        commandCodec = this.getClass().getSuperclass().getDeclaredField("commandCodec");
                        commandCodec.setAccessible(true);
                        commandCodec.set(this, new W3CHttpCommandCodec());
                        Field responseCodec = null;
                        responseCodec = this.getClass().getSuperclass().getDeclaredField("responseCodec");
                        responseCodec.setAccessible(true);
                        responseCodec.set(this, new W3CHttpResponseCodec());
                    } catch (NoSuchFieldException var5) {
                        var5.printStackTrace();
                    } catch (IllegalAccessException var6) {
                        var6.printStackTrace();
                    }
                } else {
                    response = super.execute(command);
                }

                return response;
            }
        };
        return new RemoteWebDriver(executor, new DesiredCapabilities());
    }

    public void jsEnable(WebElement element) {
        ((JavascriptExecutor)this.driver).executeScript("arguments[0].disabled=false", new Object[]{element});
    }
}
