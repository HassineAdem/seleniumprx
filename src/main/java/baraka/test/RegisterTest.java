package baraka.test;

import baraka.page.RegisterPage;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumCore.web.WebConfig;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterTest extends WebConfig {

    public static List<String> optionsList = Arrays.asList(new String[]{"CIN","Patente","Carte de séjour","N° de passeport"});
    private RegisterPage registerPage;
    @Test (priority = 1)
    public void clientNotFoundRegistration ()  {
        test = extent.createTest("Client non existant","donnée invalide");
        registration("12345678","1234567890123456", optionsList.get(2));
        Assert.assertEquals(registerPage.getErrorMessage().getText(),"Nous ne trouvons pas votre compte dans notre système, merci de vérifier les informations rensignées ou bien contacter la banque.");
    }
    @Test (priority = 2)
    public void clientAlreadyExists (){
        test = extent.createTest("Client existant","donnée existant");
        registration("10101010","1234568891234569",optionsList.get(2));
        Assert.assertEquals(registerPage.getErrorMessage().getText(),"Le client existe déjà dans le système.");
    }
    @Test (priority = 3)
    public void checkRequiredFieldChoiceInput () {
        List<String> listAssertActual= new ArrayList<>();
        List<String>  listAssertExpected = new ArrayList<>();
        for (String option:optionsList){
            test= extent.createTest("Check Required Error Message On " +option+" input" ,"(Registration fields)" );
            registration("","1234568891234569",option);
            Integer index =  optionsList.indexOf(option) +1 ;
            WebElement errorMessage = driver.findElement(By.xpath("//body/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-register[1]/div[1]/div[2]/div[1]/formio[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div["+index+"]/div[2]/div[1]"));
            listAssertActual.add(errorMessage.getText());
            listAssertExpected.add("Ce champ est obligatoire");
            if (errorMessage.getText().equals("Ce champ est obligatoire")){
                giveMarkupInfo(1,option + " Required Test " );
            }else {
                testInfoFail("Test Failed");
                giveMarkupInfo(2, option +"Required Test");
            }
            Assert.assertEquals(listAssertExpected,listAssertActual);
            this.setupEnvironment();
        }
    }
    @Test (priority = 4)
    public void checkCinFieldLength () {
        test = extent.createTest("Registration CIN Input Validations ","Check Minimum length");
        registration("123","1234568891234569",optionsList.get(0));
        WebElement errorMessage = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-register[1]/div[1]/div[2]/div[1]/formio[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]"));
        Assert.assertEquals(errorMessage.getText(),"CIN doit avoir 8 chiffres");
    }
    @Test(priority = 5)
    public void checkCinRegexInput ()  {
        test = extent.createTest("Registration CIN input validations ","Check input regex");
        registration("aa","1234568891234569",optionsList.get(0));
        WebElement errorMessage = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-register[1]/div[1]/div[2]/div[1]/formio[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]"));
        Assert.assertEquals(errorMessage.getText(),"CIN n'accepte que les chiffres");
    }
    @Test(priority = 6)
    public void checkRequiredAcountInput () {
        test = extent.createTest("Account number input validations", "(Required Account Number)");
        registration("12345678","",optionsList.get(0));
        WebElement errorMessage = driver.findElement(By.xpath("//body/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-register[1]/div[1]/div[2]/div[1]/formio[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]"));
        Assert.assertEquals(errorMessage.getText(),"Ce champ est obligatoire");
    }
    @Test(priority = 7)
    public void checkRegexAcountInput ()   {
        test = extent.createTest("Account number input validations", "(Regex for Account Number)");
        registration("12345678","aaaa",optionsList.get(0));
        WebElement errorMessage = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-register[1]/div[1]/div[2]/div[1]/formio[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]"));
        Assert.assertEquals(errorMessage.getText(),"Numéro de compte ne correspond pas au motif ^[0-9]*$");
    }
 @Test (priority = 8)
 public void availableClientForRegistration()  {
        test = extent.createTest("Inscription autorisé","Client existant ,elegible,non inscrit ");
        registration("00741643","0377881011077601",optionsList.get(0));
        Assert.assertEquals(registerPage.getPhoneValidationMessage().getText(),"VALIDATION DE VOTRE NUMÉRO");

 }
 @Test (priority = 9)
 public void giveWrongPhoneNumberAfterRegistration (){
        test = extent.createTest("Step 2 of registration","giving wrong phone number");
     registration("00741643","0377881011077601",optionsList.get(0));
        threadSleep(6000);
        this.sendKeysForPhoneValidationInput("52690660");
        Assert.assertEquals(registerPage.getInvalidPhoneNumberError().getText(),"Merci de vérifier vos données pour pouvoir traiter votre demande.");
 }
 @Test (priority = 10)
 public void giveRightPhoneNumberAfterRegistration (){
        test= extent.createTest("Step 2 of registration","giving right phone number");
      registration("00741643","0377881011077601",optionsList.get(0));
        this.sendKeysForPhoneValidationInput("26282389");
        Assert.assertEquals(registerPage.getThirdStepRegistrationWelcomeText().getText(),"CHOISISSEZ VOTRE SERVICE");
 }
@Test (priority = 10)
public void testRegexOnPhoneNumberInput (){
        test = extent.createTest("Validation on step 2 of registration","Regex of phone input");
        registration("00741643","0377881011077601",optionsList.get(0));
        this.sendKeysForPhoneValidationInput("52690660");
        this.registerPage.getSubmitInvalidPhoneNumberButton().click();
        testInfo("phone input error message were visible and clicked");
        this.sendKeysForPhoneValidationInput("aaa");
        Assert.assertEquals (this.registerPage.getPhoneInput().getAttribute("value"),"");
}
   @BeforeMethod
    public void setupEnvironment(){
        registerPage = new RegisterPage(driver);
        driver.get("http://albarakabank.qa.proxym-it.tn/#/auth/register");
        driver.manage().window().maximize();

    }
public void sendKeysForPhoneValidationInput(String input){
    threadSleep(6000);
      if (!isVisible(registerPage.getPhoneInput())){
          testInfoFail("Phone input not found ");
      }
      else {
          testInfo("Phone input found");
      }

      registerPage.getPhoneInput().sendKeys(Keys.CONTROL +"a");
    registerPage.getPhoneInput().sendKeys(Keys.DELETE);
    registerPage.getPhoneInput().sendKeys(input);
      testInfo (input +" sent to the input");
      if (!isVisible(registerPage.getSubmitPhoneButton())){
          testInfoFail("Submit button not found");
      }else {
          testInfo("Submit button found");
      }
      registerPage.getSubmitPhoneButton().click();
      threadSleep(5000);

}


    public void registration (String choiceInput, String acountInput,String choice) {
        if (isVisible(registerPage.getWebsiteBug())){
            this.registerPage.getWebsiteBug().click();
        }
 if (isInvisible(this.registerPage.getSelectInputButton())){
      testInfoFail("Cannot find select options");
 }
        this.registerPage.getSelectInputButton().click();
 testInfoPass("Reached to choice element");
            WebElement myChoice =  driver.findElement(By.xpath("//span[contains(text(),'"+choice+ "')]"));
                myChoice.click();
                testInfoPass("We picked up "+ choice +" option");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer index =  optionsList.indexOf(choice) +1 ;
                WebElement input = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-register[1]/div[1]/div[2]/div[1]/formio[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div["+index+"]/div[1]/input[1]"));
                if (isInvisible(input)){
                    testInfoFail("Cannot reach to "+choice+" input field");
                }

                else {
                    testInfo("Reached to "+choice+" input element");

                }

              input.sendKeys(choiceInput);
                testInfo("value sent to "+choice+" input");
                if (isInvisible(registerPage.getAccountNumber())){
                    testInfoFail("Cannot reach to Account Number input field");
                }
                else {
                    testInfo("Reached to Account Number input element");
                }
                registerPage.getAccountNumber().sendKeys(acountInput);
                testInfo("value sent to account input");

                registerPage.getSubmitButton().click();
                testInfo("Click sent to the register button ");
                threadSleep(5000);
    }
    void giveMarkupInfo (int index,String message ){
        if (index == 2) {
            this.test.log(Status.FAIL, MarkupHelper.createLabel(message + " Test case FAILED : ", ExtentColor.RED));

        } else if (index == 1) {
            this.test.log(Status.PASS, MarkupHelper.createLabel(message + " Test case PASSED : ", ExtentColor.GREEN));
        }
    }
}
