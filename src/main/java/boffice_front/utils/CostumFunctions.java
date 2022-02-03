package boffice_front.utils;

import boffice_front.pages.bo.ContentManagementNewsElements;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import main.WebConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class CostumFunctions extends WebConfig {
    private DateConverter dateConverter  = new DateConverter();
    public CostumFunctions(ExtentReports extentReports, ExtentTest test,WebDriver driver){
        this.extent = extentReports;
        this.test=  test;

       this.driver =driver;
    }
public void clickOneUserUpdateById (Integer id){
        List<WebElement> list = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr/td[1]"));
        for (WebElement item:list){
            if (Integer.parseInt(item.getText()) == id){
                Integer index= list.indexOf(item)+1;
             driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr["+index+"]/td[8]/button[1]")).click();
             testInfoPass("find update icon for the user");
             return;
            }

        }
        testInfoFail("cannot find update icon for the user " + id);
}
    public String changeInBo (WebElement arg0,WebElement arg1,Integer user_id,WebElement arg3,WebElement arg4,WebElement arg5,WebElement arg6,String newChange,String change){
        String expectedChange = newChange;
            handleVisibleNotVisible(arg0,"User Management not found","User Management Found");
            arg0.click();
            testInfoPass("Click sent to User Management");
    threadSleep(2000);

    handleVisibleNotVisible(arg1,"Costumer option not found","Costumer Option found");
    arg1.click();
    testInfoPass("Click sent to costumers");
  clickOneUserUpdateById(user_id);
    testInfoPass("Click sent to update icon for user with id "+ user_id);
    if (change.equals("first name") || change.equals("last name")){
        handleVisibleNotVisible(arg3,"cannot find first name input field","find first name input field");
        handleVisibleNotVisible(arg4,"cannot find last name input field","find last name input field");
        if (change.equals("first name")){
            expectedChange =   newChange + " " +arg3.getAttribute("value");
        } if (change.equals("last name")){
            expectedChange =    arg3.getAttribute("value")+ " " +newChange;
        }
    }
    arg4.sendKeys(Keys.CONTROL + "a");
    arg4.sendKeys(Keys.DELETE);
    arg4.sendKeys(newChange);
    testInfoPass("Change for " + change + " sent with value " + newChange +" For user with id = " + user_id);
    handleVisibleNotVisible(arg5,"cannot find save button", "save button found");

    arg5.click();
    testInfoPass("Click sent to save button");
    handleVisibleNotVisible(arg6,"Cannot find confirmation message","find confirmation message");
    arg6.click();
    testInfoPass("Click sent to submit changes confirmation button ");
    return expectedChange;
    }
    public void pickUpSomeDate (String date, WebElement calenderIcon, WebDriver driver){
        List<String> dateToArray= List.of(date.split("-"));
        handleVisibleNotVisible(calenderIcon,"Cannot find the calender icon","find the calender icon");
        calenderIcon.click();

        WebElement incrementIcon = driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[1]/a[2]"));
        handleVisibleNotVisible(incrementIcon,"Cannot find increment icon in calender date","find increment icon in calender date");
        testInfo("We are about getting the date");
    loopForDate(dateToArray.get(2));
    loopForDate(dateConverter.convertIndexToAMnth(dateToArray.get(1)));
    driver.findElement(By.xpath("//a[contains(text(),'"+dateToArray.get(0) +"')]")).click();
    testInfo("We Picked up the date");
 threadSleep(2000);
 testInfo("Thread is sleeping for 2 seconds");

    }
    public void loopForDate (String date){
        WebElement incrementIcon = null;
        boolean found= false;
        while (!found){
            try{
                System.out.println("WORKED ");
                incrementIcon =driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[1]/a[2]"));
                WebElement element = driver.findElement(By.xpath("//span[contains(text(),'"+date +"')]"));

                found = true;
            }catch (Exception ex){
                incrementIcon.click();

                found = false;
            }
        }
    }
    public void goToAddNews (WebElement arg0 ,WebElement arg1 ,WebElement arg2){
        handleVisibleNotVisible(arg0,"Cannot found content management option in menu ", "found content management option in menu");
        arg0.click();
        threadSleep(2000);
        handleVisibleNotVisible(arg1,"cannot found news content option ","found news content option ");
        arg1.click();
        handleVisibleNotVisible(arg2,"cannot found add news button","found add news button");
        arg2.click();


        threadSleep(1000);
    }
    public void changePublishedAndSave (WebElement arg0,WebElement arg1,WebElement arg2,WebElement arg3){
        arg0.sendKeys("D:\\Users\\adem.hassine\\Desktop\\Mon Dossier\\Profissonel\\alternance Proxym\\Proxym\\selenium-utils\\default-user-image.png");
        testInfoPass("Image sent");
        handleVisibleNotVisible(arg1,"Cannot find upload button","upload button found");
        arg1.click();
        threadSleep(500);

        handleVisibleNotVisible(arg2,"Cannot find save button", "find save button");
        arg2.click();
        handleVisibleNotVisible(arg3,"cannot find confirmation save button","find confirmation save button");
        arg3.click();
        testInfoPass("Keys sent to save news");

    }
    public void fillingFields (WebElement arg0,  WebElement arg1,  WebElement arg2, WebElement arg3,
                               WebElement arg4, WebElement arg5,  WebElement arg6,  WebElement arg7,
                               WebElement arg8,   WebElement arg9,  WebElement arg10,  WebElement arg11,
                               WebElement arg12,  WebElement arg13,WebDriver driver){
WebElement arg14 = null;
        handleVisibleNotVisible (arg0,"Cannot find titleEn","Find titleEn element");
        arg0.sendKeys("test");
        testInfoPass("Keys sent to titleEng");
        handleVisibleNotVisible (arg1,"Cannot find titleFr","Find titleFr element");
        arg1.sendKeys("test");
        testInfoPass("Keys sent to title fr");
        handleVisibleNotVisible (arg2,"Cannot find titleAr","Find title Ar element");
        arg2.sendKeys("Test");
        testInfoPass("Keys sent to title Ar");
        handleVisibleNotVisible (arg3,"Cannot find category select ","Find category select  element");
        arg3.click();
        handleVisibleNotVisible (arg4,"Cannot find category option","Find category option element");
        arg4.click();
        testInfoPass("Selected category option");
        threadSleep(500);
        handleVisibleNotVisible (arg5,"Cannot find subcategory select","Find  subcategory select element");
        arg5.click();
        handleVisibleNotVisible (arg6,"Cannot find  subcategory option","Find subcategory option element");
        arg6.click();
        testInfoPass("Selected subcategory option");
        threadSleep(500);
        handleVisibleNotVisible (arg7,"Cannot find type select","Find  type select element");
        arg7.click();
        threadSleep(500);
        handleVisibleNotVisible (arg8,"Cannot find  type option","Find type option element");
        arg8.click();
        testInfoPass("Selected type option");
        threadSleep(500);
        handleVisibleNotVisible(arg12,"cannot find web channel","find web channel");
        arg12.click();
        handleVisibleNotVisible(arg13,"Cannot find published button","find published button");
        arg13.click();
        handleVisibleNotVisible(arg9,"cannot find descriptionENG element","find descriptionENG element");
        driver.switchTo().frame(arg9);
        arg14= driver.findElement(By.xpath("/html[1]/body[1]/p[1]"));
        arg14.sendKeys("TESSSSSSST");
        testInfoPass("Keys sent to descriptionENG");
        driver.switchTo().parentFrame();
        handleVisibleNotVisible(arg10,"cannot find descriptionFR element","find descriptionFR element");
        driver.switchTo().frame(arg10);
        arg14= driver.findElement(By.xpath("/html[1]/body[1]/p[1]"));
        arg14.sendKeys("TESSSSSSST");
        driver.switchTo().parentFrame();
        testInfoPass("Keys sent to descriptionFR");
        handleVisibleNotVisible(arg11,"cannot find descriptionAR element","find descriptionAR element");
        driver.switchTo().frame(arg11);
        arg14= driver.findElement(By.xpath("/html[1]/body[1]/p[1]"));
        arg14.sendKeys("TESSSSSSST");
        driver.switchTo().parentFrame();
        testInfoPass("Keys sent to descriptionAR");

        threadSleep(2000);
    }
    public String returnActualFullName (WebElement arg0){
        handleVisibleNotVisible(arg0,"user fullname not found","user fullname found");
        return arg0.getText();
    }

    public void handleVisibleNotVisible (WebElement arg0, String errMsg,String successMsg) {
        if (isInvisible(arg0)) {
            testInfoFail(errMsg);
        }

        testInfo(successMsg);
    }
    public boolean isVisiblee (WebElement webElement) {
        try {
            if (webElement.isDisplayed()) {
                return true;
            } else {
                this.wait.until(ExpectedConditions.visibilityOf(webElement));
                return true;
            }
        }catch (Exception ex){
            return false;
        }
    }

    public Integer checkNewsCount(WebElement contentMenuChoice, WebElement newsChoice) {
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/header[1]/div[1]/div[1]")).click();
        handleVisibleNotVisible(contentMenuChoice,"cannot find content menu choice","find content menu choice");
        contentMenuChoice.click();
        testInfoPass("Click sent to content Menu choice");
        threadSleep(500);
        handleVisibleNotVisible(newsChoice,"Cannot find news choice","find news choice");
        newsChoice.click();
        threadSleep(1000);

        List<WebElement> list = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div"));
        testInfo("Returning number of news " + list.size());
        return list.size();
    }
}
