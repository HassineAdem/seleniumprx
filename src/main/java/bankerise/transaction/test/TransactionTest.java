package bankerise.transaction.test;

import bankerise.page.TransactionPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumCore.web.WebConfig;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TransactionTest extends WebConfig {
    static final List<String> beneficiaryInformation = Arrays.asList(new String[]{"nickName","firstName","lastName","RIB","email","phonenumber"});
    TransactionPage transactionPage;
    JavascriptExecutor js;
    Actions actions;
    Float previousMoney;
    public TransactionTest(WebDriver driver){
        this.driver= driver;
        transactionPage = new TransactionPage(driver);
    }
    public TransactionTest(){

    }

@Test
    public void checkTransaction (){
test = extent.createTest("Check Transaction in bankerise","check transaction from acount to another acount");

        Float moneyToTransfer = Float.valueOf(2);
      registrationSuccess ("bankerise","12345678");

        otpValidation("00000");
        validTransactionFromAcountToAnother (moneyToTransfer,"035183598478731","nidhal","12-31-2021");
        refreshAndAcceptPopUp();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/header[1]/div[2]/button[1]")).click();
        initiateDriver();
        otpValidation("00000");
        goToWithinBankTransaction();
        Float availableMoney = checkMoneyFromTransactionWithinBank("035183598478731",transactionPage.getDebitAccountSelect());
        Float sum =previousMoney - moneyToTransfer;
        Integer availableMoneyInt =  Integer.parseInt(availableMoney.toString().split("\\.")[0]);
        Integer sumInt = Integer.parseInt(sum.toString().split("\\.")[0]);
        Assert.assertEquals(java.util.Optional.of(availableMoneyInt), java.util.Optional.of(sumInt));
    }
    @Test
    public void checkRequiredInTransactionWithinBank (){
        test =extent.createTest("Transaction Validations","Check required validations");
        registrationSuccess("bankerise","12345678");
        otpValidation("00000");
        goToWithinBankTransaction();
        checkValidation("Required");
        List <String> actualAssertList = checkValidation("Required");
        boolean assertion = actualAssertList.size()==3 && actualAssertList.stream().allMatch(s -> s.equals("Required"));
     Assert.assertTrue(assertion);
    }
    @Test
    public void maxAmountTransfer (){
        test = extent.createTest("Transaction Validations","Check amount must be less then");
        registrationSuccess("bankerise","12345678");
        otpValidation("00000");
        goToWithinBankTransaction();
        Float availableMoney = checkMoneyFromTransactionWithinBank("035183598478731",transactionPage.getDebitAccountSelect());
        checkMoneyFromTransactionWithinBank("nidhal",transactionPage.getBeneficiarySelect());
        transactionPage.getTransferAmountInput().sendKeys("9999999999999999999");
        testInfo("Sending max transfer Amount");
        String expectedText = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[1]/div[1]/div[1]")).getText();
        Assert.assertEquals(expectedText,"Transfer Amount\n*\n*\nAmout must be less than " + availableMoney +" !",expectedText);
    }



    @Test(priority = 2)
    public void addBeneficiary (){
        test= extent.createTest("Adding beneficiary");
        registrationSuccess("bankerise","12345678");
        otpValidation("00000");
        this.addBeneficiaryWithinBank(beneficiaryInformation);
        Assert.assertTrue(true);
    }


    public List<String> checkValidation(String required) {
        List<String > listValidationText = new ArrayList<>();
        transactionPage.getDebitAccountSelect().click();
        testInfoPass("Found  & send click to debit acount select");
        transactionPage.getBeneficiarySelect().click();
        testInfoPass("Found  & send click to beneficiary select");
        transactionPage.getTransferDate().click();
        testInfoPass("Found  & send click to transfer date select");
        transactionPage.getReasonText().click();
        testInfoPass("Found  & send click to reason input");
        listValidationText.add(transactionPage.getDebitAccountValidationText().getText());
        testInfoPass("Found debit account validation");
        listValidationText.add(transactionPage.getTransferDateValidationText().getText());
        testInfoPass("Found transfer date validation");
        listValidationText.add(transactionPage.getBeneficiaryValidationText().getText());
        testInfoPass("Found beneficiary validation");
        return listValidationText;

    }


    public void registrationSuccess (String usernameInput, String passwordInput){
        System.out.println("test in the main test class"+ test);
        if (isInvisible(transactionPage.getUsernameLogin())){
            testInfoFail("Username input field not found");
        }
        else {
            testInfo ("Username input found");
            transactionPage.getUsernameLogin().sendKeys(usernameInput);
            testInfoPass(usernameInput +" sent to username input");
        }
         if (isInvisible(transactionPage.getPasswordLogin())){
            testInfoFail("password input field not found");
        }
        else {
            testInfo ("password input found");
            transactionPage.getPasswordLogin().sendKeys(passwordInput);
            testInfoPass(passwordInput +" sent to password input");
            transactionPage.getPasswordLogin().sendKeys(Keys.ENTER);
        }
        testInfo("Entered Credentials ");
     threadSleep(2000);
     testInfo("Thread is Sleep for 2 seconds");
     if (isVisible(transactionPage.getOtpMessage())){
         testInfoPass("Registration passed success and passed to the next step");
     }

    }
    public void otpValidation (String otpInput){
        if (isInvisible(transactionPage.getOtpInput())) {
            testInfoFail("OTP input not found");
        }
        testInfo("OTP input found");
        transactionPage.getOtpInput().sendKeys(otpInput);
        testInfoPass(otpInput + " Sent to the otp input");
        transactionPage.getOtpInput().sendKeys(Keys.ENTER);
        testInfo("OTP submit");
        threadSleep(2000);
        testInfoPass("Thread is sleeping for 2 seconds");
    }

    public void goToWithinBankTransaction(){
        if (isInvisible(transactionPage.getTransferChoiceMenu())){
            testInfoFail("There is no transfer choice in our menu ");
        }
        testInfo("Transfer choice found in menu");
        transactionPage.getTransferChoiceMenu().click();
        testInfoPass("click is sent to our transfer choice in menu");
        if (isInvisible(transactionPage.getAddTransactionIcon())){
            testInfoFail("Add Transaction icon not found");
        }
        testInfo("Add icon transaction is found");
        transactionPage.getAddTransactionIcon().click();
        testInfoPass("Click is sent to the add transaction icon");
        if (isInvisible(transactionPage.getWithinBankTransaction())){
            testInfoFail("Within bank transaction choice not found");
        }
        testInfo("Within bank transaction choice found");
        transactionPage.getWithinBankTransaction().click();
        threadSleep(2000);
        testInfo("Thread is sleeping for 2 seconds");
        testInfoPass("Click sent to within bank transaction choice");
    }
    public Float checkMoneyFromTransactionWithinBank (String valueAccount,WebElement selectElement){
        if (isInvisible(selectElement)){
            testInfoFail("Debit account select not found");
        }
        testInfo("Debit account select is found");
        Select select=  new Select(selectElement);
        select.selectByValue(valueAccount);
        try{
            String optionText = select.getFirstSelectedOption().getText();
            optionText = optionText.substring(valueAccount.length() +1);
            optionText = optionText.replaceFirst("TND","");
            previousMoney = Float.parseFloat(optionText);
        }catch (NumberFormatException exception){

        }

        return previousMoney;
    }
    public void validTransactionFromAcountToAnother (Float transactionMoney,String valueAccount,String valueBeneficiairy,String date){
     goToWithinBankTransaction();
        if (!driver.getCurrentUrl().equals("http://bkr.qa.proxym-it.tn/private/transfers/withinbank")){
            testInfoFail("not reached to the right url");
            getScreenhot("Screenshot currentUrl ");
        }
        testInfo("success step and passed to the next step");

   previousMoney =checkMoneyFromTransactionWithinBank(valueAccount,transactionPage.getDebitAccountSelect());
       Select   select = new Select(transactionPage.getBeneficiarySelect());
          select.selectByValue(valueBeneficiairy);
        threadSleep(1000);
        testInfo("Thread sleeping for 1 second");
       if (isInvisible(transactionPage.getTransferAmountInput())){
           testInfoFail("transfer amount input not found");
           getScreenhot("no-amount-input");
       }
       testInfo ("transfer amount is found");
       transactionPage.getTransferAmountInput().sendKeys(Keys.CONTROL + "a");
       transactionPage.getTransferAmountInput().sendKeys(Keys.DELETE);
       transactionPage.getTransferAmountInput().sendKeys(transactionMoney.toString());
        testInfoPass("Sent " + transactionMoney +" to amount input ");
       if (isInvisible(transactionPage.getTransferDate())){
           testInfoFail("Transfer date input not found");
       }
       testInfo("Transfer date input found");
      List<String >dateArray = List.of(date.split("-"));
       transactionPage.getTransferDate().sendKeys(dateArray.get(0));
       transactionPage.getTransferDate().sendKeys(dateArray.get(1));
       transactionPage.getTransferDate().sendKeys(dateArray.get(2));
       testInfoPass("Date sent to transfer date input");
     if (isInvisible(transactionPage.getSubmitTransactionButton())){
         testInfoFail("Submit transaction button not found");
     }
     testInfo("Submit transaction button found");
     threadSleep(5000);
     transactionPage.getSubmitTransactionButton().click();

    }
   public Float checkDashboardMoney (){

        if (isInvisible(transactionPage.getDashboardMenuIcon())){
            testInfoFail("Dashboard Icon in menu not found");
        }
        testInfo("Dashboard icon found");
        transactionPage.getDashboardMenuIcon().click();
        testInfoPass("Click sent to dashboard icon ");
        threadSleep(2000);
      if (isInvisible(transactionPage.getCardsDashboard())){
          testInfoFail("Cannot find card dashboard");
      }
      testInfo("Find card in dashboard");
       actions.moveToElement(transactionPage.getCardsDashboard()).clickAndHold().moveByOffset(0,10).release().perform();
       threadSleep(5000);
         WebElement totalBalance = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]"));
       if (isInvisible(totalBalance)){
            testInfoFail("Cannot find total balance money");
            getScreenhot("total-balance-money-not-found");
        }
        testInfo("Total balance found");
         String moneyFoundString =totalBalance.getText();
         moneyFoundString = moneyFoundString.replaceFirst(",","");
        Float moneyFound = Float.parseFloat(moneyFoundString);
        return moneyFound;
   }
   public void addBeneficiaryWithinBank (List<String> listInputs){
  if (isInvisible(transactionPage.getBeneficiaryIconMenu())){
      testInfoFail("cannot find beneficiary icon");
  }
  testInfoPass("Reached to add beneficiary");
  transactionPage.getBeneficiaryIconMenu().click();
  if (isInvisible(transactionPage.getBeneficiaryAddIcon())){
      testInfoFail("Cannot find beneficiary add icon");
  }
  testInfo("find add icon for adding beneficiary");
  transactionPage.getBeneficiaryAddIcon().click();
  if (isInvisible(transactionPage.getAddBeneficiaryWithinBank())){
      testInfoFail("Cannot find add beneficiary within bank");
  }
  testInfo("find add beneficiary within bank");
  transactionPage.getAddBeneficiaryWithinBank().click();
  testInfoPass("Sent click to add beneficiary within bank");
  for (String option:beneficiaryInformation){
      Integer index = beneficiaryInformation.indexOf(option)+2;
      WebElement beneficiaryInputField = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div["+index+"]/div[1]/input[1]"));
      if (isInvisible(beneficiaryInputField)){
          testInfoFail("Cannot find" + option + " input field");
      }
      testInfo(option+" input field found");
      beneficiaryInputField.sendKeys(listInputs.get(index -2));
      testInfoPass(listInputs.get(index -2 ) + "sent to " +option + " input");
      if (isInvisible(transactionPage.getAddBeneficiarySubmitButton())){
          testInfoFail("Cannot find Beneficiary submit button");
      }
      transactionPage.getAddBeneficiarySubmitButton().click();
      testInfoPass("Sent click to add beneficiary button");
  }

   }
 @BeforeMethod
    public void setupEnvironment(){
    initiateDriver();
      js = (JavascriptExecutor) driver;
     actions = new Actions(driver);
        driver.get("http://bankerise-keycloak.demo.proxym-it.tn/auth/realms/bankerise-front/protocol/openid-connect/auth?client_id=front&scope=openid&response_type=code&redirect_uri=http://bkr.qa.proxym-it.tn/");
    }
    public void initiateDriver (){
        transactionPage= new TransactionPage(driver);
    }
    public void refreshAndAcceptPopUp (){
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
    }

}
