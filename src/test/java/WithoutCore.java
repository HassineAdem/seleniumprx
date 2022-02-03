import bankerise.page.TransactionPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class WithoutCore{
    private ExtentSparkReporter htmlReporter;
    private ExtentReports extent;
    private ExtentTest test;
    private WebDriver driver;
    private bankerise.page.TransactionPage transactionPage;
    @Test
    public void authenticate() {
        test = extent.createTest("Authenticating with right credentials");
        System.out.println("test in the main test class" + test);
         try {
             transactionPage.getUsernameLogin().sendKeys("bankerise");
           test.log(Status.PASS,"Username input found");
        } catch (Exception ex){
            test.log(Status.FAIL,"Username input not found");
        }
         try{
            transactionPage.getPasswordLogin().sendKeys("12345678");
            transactionPage.getPasswordLogin().sendKeys(Keys.ENTER);
        }catch (Exception ex){

         }

        try {
            Thread.sleep(2000);
            test.log(Status.PASS,"Thread is Sleep for 2 seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            test.info("Registration passed success and passed to the next step");
        Assert.assertEquals(transactionPage.getOtpMessage().getText(),"");

    }
    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","D:\\Users\\adem.hassine\\Desktop\\Mon Dossier\\Profissonel\\alternance Proxym\\driver browser\\chromedriver.exe");
        driver= new ChromeDriver();
        driver.get("http://bankerise-keycloak.demo.proxym-it.tn/auth/realms/bankerise-front/protocol/openid-connect/auth?client_id=front&scope=openid&response_type=code&redirect_uri=http://bkr.qa.proxym-it.tn/");
        transactionPage=  new TransactionPage(driver);
    }
    @BeforeTest
    public void startReport() {
           htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
    @AfterTest
    public void tearDown() {
        extent.flush();
    }
}
