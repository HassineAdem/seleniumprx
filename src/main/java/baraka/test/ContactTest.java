//package baraka.test;
//
//import baraka.page.ContactPage;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import seleniumCore.web.WebConfig;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//public class ContactTest extends WebConfig {
// private ContactPage contactPage;
// private static List<String> fieldInputTitle = Arrays.asList(new String[]{"First name","Address","City","Email","Subject","Prénom ","Agence","Numéro de téléphone"});
// @Test(priority = 1)
//    public void checkRequiredFields (){
//     test = extent.createTest("Contact Validations","Check required inputs");
//     testInfo("Initiating");
//     threadSleep(2000);
//     if (isInvisible(contactPage.getSubmitButton())){
//         testInfoFail("Submit button not found");
//     }else {
//         testInfo("Sending a click to the submit button...");
//         contactPage.getSubmitButton().click();
//     }
//     int index ;
//          for (String option:fieldInputTitle){
//              index= fieldInputTitle.indexOf(option) + 1;
//              WebElement error = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-contuct-us[1]/div[2]/div[1]/div[1]/formio[1]/div[1]/div[1]/div[1]/div[1]/div["+index+"]/div["++"]/div[1]/div[2]/div[1]"));
//              if (error.getText().equals("Ce champ est obligatoire")){
//                  testInfoPass("Found Required message for "+option );
//              }else {
//                  testInfoFail("No required message for " + option );
//              }
//          }
//    }
//
//    @BeforeMethod
//    public void setUpEnvironement (){
//     contactPage= new ContactPage(driver);
//     driver.get("http://albarakabank.qa.proxym-it.tn/#/contact-us");
//
//    }
//}
