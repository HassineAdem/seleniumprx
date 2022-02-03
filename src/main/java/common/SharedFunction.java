package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import seleniumCore.web.WebConfig;


public class SharedFunction  extends WebConfig {
    public SharedFunction( ExtentReports extentReports, ExtentTest test){
        this.extent = extentReports;
        this.test=  test;
    }
    public SharedFunction (ExtentReports extent, ExtentTest test,WebDriver driver){
        this.driver = driver;
        this.extent = extent;
        this.test= test;
    }
    public Integer registrationSuccess (String username, String password, WebElement usernameInput,WebElement passwordInput, WebElement successElement){
        Integer found = 0 ;
        if (isInvisible(usernameInput)){
            testInfoFail("Username input field not found");
        }
        else {
            testInfo ("Username input found");
           usernameInput.sendKeys(username);
            testInfoPass(username +" sent to username input");
        }
        if (isInvisible(passwordInput)){
            testInfoFail("password input field not found");
        }
        else {
            testInfo ("password input found");
            passwordInput.sendKeys(password);
            testInfoPass(password +" sent to password input");
            passwordInput.sendKeys(Keys.ENTER);
        }
        testInfo("Entered Credentials ");
        threadSleep(2000);
        testInfo("Thread is Sleep for 2 seconds");
        try{
            if (isVisible(successElement)){
                testInfoPass("Registration passed success and passed to the next step");
                found ++;
            }
            threadSleep(1000);
        }catch (NullPointerException ex){

        }
        return found;


    }
    public void otpValidation (WebElement otpInput,String otpKey){
        if (isInvisible(otpInput)) {
            testInfoFail("OTP input not found");
        }
        testInfo("OTP input found");
        otpInput.sendKeys(otpKey);
        testInfoPass(otpKey + " Sent to the otp input");
        otpInput.sendKeys(Keys.ENTER);
        testInfo("OTP submit");
        threadSleep(2000);
        testInfoPass("Thread is sleeping for 2 seconds");
    }
    public void refreshAndAcceptPopUp (){
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
    }
}
