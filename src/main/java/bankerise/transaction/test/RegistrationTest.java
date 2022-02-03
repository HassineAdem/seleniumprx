package bankerise.transaction.test;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumCore.web.WebConfig;


public class RegistrationTest extends WebConfig {

    @Test
    public void doRegistration (){
        if (isInvisible(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[2]")))){
            test.log(Status.FAIL,"Failed");
            Assert.fail("Test FAIL");
        }
    test= extent.createTest("TEST");

    }
    @BeforeMethod
    public void setup (){
        driver.get("http://bankerise-keycloak.demo.proxym-it.tn/auth/realms/bankerise-front/protocol/openid-connect/auth?client_id=front&scope=openid&response_type=code&redirect_uri=http://bkr.qa.proxym-it.tn/");
    }
}
