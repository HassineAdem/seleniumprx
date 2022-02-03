package baraka.test;
import baraka.page.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumCore.web.WebConfig;


public class LoginTest  extends WebConfig {
    private LoginPage loginPage;




    @Test (priority = 1)
    public void  userNotAvailableCheck (){
    authentication("wrongusername","wrongpassword");
    if (isInvisible(loginPage.getErrorMessage())){
        testInfoFail("Error Message not found");
    }
    test = extent.createTest("nametest","descriptiontest");
        Assert.assertEquals(loginPage.getErrorMessage().getText(),"Invalid username or password.\n");

    }
@BeforeMethod
    public void setupEnvironment(){

        ChromeOptions options = new ChromeOptions();
         options.addArguments(new String[]{"--disable-popup-blocking"});

    this.loginPage = new LoginPage(driver);
        driver.get("http://auth.albaraka.dev.proxym-it.tn/auth/realms/al-baraka-front/protocol/openid-connect/auth?client_id=albaraka-front&redirect_uri=http%3A%2F%2Falbarakabank.qa.proxym-it.tn&response_type=code&darkMode=false&scope=openid+offline_access");

    }

    public void authentication (String usernameKey, String passwordKey){
        if (isInvisible(loginPage.getUsernameInput())){
            testInfoFail("Cannot reach to username input field");
        }else
            testInfo("Reached to username input element");
        loginPage.getUsernameInput().sendKeys(usernameKey);
        testInfo("value sent to username input");
        if (isInvisible(loginPage.getPasswordInput())){
            testInfoFail("Cannot reach to password input field");
        }
        else {
            testInfo("Reached to password input element");
        }
        loginPage.getPasswordInput().sendKeys(passwordKey);
        testInfo("value sent to password input");
        if (isInvisible(loginPage.getButtonSubmit()) || !isClickable(loginPage.getButtonSubmit())){
            testInfoFail("Login button is inavailable");
        }else {
            testInfo("Reached to login button");
        }
        loginPage.getButtonSubmit().click();
        testInfo("Click sent to the login button ");
    }





}

