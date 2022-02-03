import bankerise.page.TransactionPage;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumCore.web.WebConfig;

public class WithCore extends WebConfig {
    private bankerise.page.TransactionPage transactionPage;
    @Test
    public void authenticate() {
        driver.get("http://bankerise-keycloak.demo.proxym-it.tn/auth/realms/bankerise-front/protocol/openid-connect/auth?client_id=front&scope=openid&response_type=code&redirect_uri=http://bkr.qa.proxym-it.tn/");
        transactionPage = new TransactionPage(driver);
        test = extent.createTest("Authenticating with right credentials");
            transactionPage.getUsernameLogin().sendKeys("bankerise");
           testInfoPass("Keys sent to username");
            transactionPage.getPasswordLogin().sendKeys("12345678");
            transactionPage.getPasswordLogin().sendKeys(Keys.ENTER);
            testInfoPass("Keys sent to password");
            threadSleep(2000);
            testInfo("Thread is sleeping for 2 seconds");
        test.info("Registration passed success and passed to the next step");
        Assert.assertEquals(transactionPage.getOtpMessage().getText(),"");

    }
}
