package boffice_front.test;

import boffice_front.pages.bo.BoSharedElements;
import boffice_front.pages.bo.ContentManagementNewsElements;
import boffice_front.pages.bo.UpdateUserElements;
import boffice_front.pages.front.FrontSharedElement;
import boffice_front.utils.CostumFunctions;

import common.SharedFunction;
import common.URL;
import main.WebConfig;
import org.testng.Assert;
import org.testng.annotations.Test;



public class PagesTest extends WebConfig {
    private FrontSharedElement frontSharedElement;
    private UpdateUserElements updateUserElements;
    private BoSharedElements  boSharedElements;
    private SharedFunction sharedFunction;
    private CostumFunctions costumFunctions;
    private ContentManagementNewsElements contentManagementNewsElements;

    public PagesTest (){

    }
    @Test
    public void checkLastNameUpdate (){
        initiate (URL.BO_URL.getUrl());
        setUpReport("Check profile update in bo-front","update last name");
        sharedFunction.registrationSuccess("bkdev","Pr0xym-1T",boSharedElements.getUsernameInput(),boSharedElements.getPasswordInput(),null);
      String expectedFullName = costumFunctions.changeInBo(boSharedElements.getUserManagementMenuOption(),
              updateUserElements.getCostumersOptionInManagementMenuOption(),
              306,
              updateUserElements.getFirstNameInputUserInfo(),
              updateUserElements.getLastNameInputUserInfo(),
              updateUserElements.getSaveUpdateChangesButton(),
              updateUserElements.getConfirmationForUpdatesButton(),
              "Ben Mohamed",
              "last name");

        initiate (URL.LOGIN_FRONT_URL.getUrl());
        sharedFunction.registrationSuccess("mounir","12345678",frontSharedElement.getUsernameLogin(),frontSharedElement.getPasswordLogin(),null);
        sharedFunction.otpValidation(frontSharedElement.getOtpInput(),"00000");
        String actualFullName = costumFunctions.returnActualFullName(frontSharedElement.getMyUserFullName());
        Assert.assertEquals(actualFullName,expectedFullName);
    }
    @Test
    public void checkUsernameUpdate (){
        initiate (URL.BO_URL.getUrl());
        setUpReport("Check profile update in bo-front","update username");
        sharedFunction.registrationSuccess("bkdev","Pr0xym-1T",boSharedElements.getUsernameInput(),boSharedElements.getPasswordInput(),null);
        String expectedFullName = costumFunctions.changeInBo(boSharedElements.getUserManagementMenuOption(),
                updateUserElements.getCostumersOptionInManagementMenuOption(),
                306,
                null,
                updateUserElements.getUsernameInputUserInfo(),
                updateUserElements.getSaveUpdateChangesButton(),
                updateUserElements.getConfirmationForUpdatesButton(),
                "mounira",
                "username");

        initiate (URL.LOGIN_FRONT_URL.getUrl());
       Integer found= sharedFunction.registrationSuccess("mounira","12345678",frontSharedElement.getUsernameLogin()
                                                            ,frontSharedElement.getPasswordLogin(),frontSharedElement.getOtpInput());
        Assert.assertEquals(found, java.util.Optional.of(1).get());
    }
    @Test
    public void checkFirstNameUpdates (){
        initiate (URL.BO_URL.getUrl());
        setUpReport("Check  profile update in bo-front","update first name");
        sharedFunction.registrationSuccess("bkdev","Pr0xym-1T",boSharedElements.getUsernameInput(),boSharedElements.getPasswordInput(),null);
        String expectedFullName = costumFunctions.changeInBo(boSharedElements.getUserManagementMenuOption(),
                updateUserElements.getCostumersOptionInManagementMenuOption(),
                303,
                updateUserElements.getLastNameInputUserInfo(),
                updateUserElements.getFirstNameInputUserInfo(),
                updateUserElements.getSaveUpdateChangesButton(),
                updateUserElements.getConfirmationForUpdatesButton(),
                "Islem",
                "first name");

        initiate (URL.LOGIN_FRONT_URL.getUrl());
        sharedFunction.registrationSuccess("bankerise","12345678",frontSharedElement.getUsernameLogin(),frontSharedElement.getPasswordLogin(),null);
        sharedFunction.otpValidation(frontSharedElement.getOtpInput(),"00000");
        String actualFullName = costumFunctions.returnActualFullName(frontSharedElement.getMyUserFullName());
        Assert.assertEquals(actualFullName,expectedFullName);
    }
    @Test
    public void addNewsAndCheck (){
        Integer previousNewsCount;
        Integer actualNewsCount;
        initiate(URL.PUBLIC_FRONT_URL.getUrl());
        setUpReport("Adding News over Back Office","adding and checking in front ");
        previousNewsCount = costumFunctions.checkNewsCount (frontSharedElement.getContentMenuChoice(),
                frontSharedElement.getNewsChoice());
            initiate(URL.BO_URL.getUrl());
            sharedFunction.registrationSuccess("bkdev","Pr0xym-1T",boSharedElements.getUsernameInput(), boSharedElements.getPasswordInput(), null);
            costumFunctions.goToAddNews (boSharedElements.getContentManagementMenuOption(),
                    contentManagementNewsElements.getNewsManagementOption(),
                    contentManagementNewsElements.getAddNewsButton());
            costumFunctions.pickUpSomeDate("11-03-2022", contentManagementNewsElements.getStartDateCalenderIcon(),driver);
            costumFunctions.pickUpSomeDate("12-03-2022", contentManagementNewsElements.getEndDateCalenderIcon(),driver);
            costumFunctions.fillingFields (
                    contentManagementNewsElements.getTitleInputEng(),
                    contentManagementNewsElements.getTitleInputFr(),
                    contentManagementNewsElements.getTitleInputAr(),
                    contentManagementNewsElements.getCategorySelect(),
                    contentManagementNewsElements.getCategoryOption(),
                    contentManagementNewsElements.getSubcategorySelect(),
                    contentManagementNewsElements.getTestNewsSubOption(),
                    contentManagementNewsElements.getTypeSelect(),
                    contentManagementNewsElements.getNewTypeOption(),
                    contentManagementNewsElements.getIframeDescriptionEng(),
                    contentManagementNewsElements.getIframeDescriptionFr(),
                    contentManagementNewsElements.getIframeDescriptionAr(),
                    contentManagementNewsElements.getPublishedButton(),
                    contentManagementNewsElements.getChannelWebButton(),
                    driver);
            costumFunctions.changePublishedAndSave (contentManagementNewsElements.getChooseImageInput(),
                    contentManagementNewsElements.getUploadImageButton(),
                    contentManagementNewsElements.getSaveButton(),
                    contentManagementNewsElements.getConfirmationSaveButton());
           initiate(URL.PUBLIC_FRONT_URL.getUrl());
           actualNewsCount = costumFunctions.checkNewsCount(frontSharedElement.getContentMenuChoice()
                   ,frontSharedElement.getNewsChoice());
           previousNewsCount++;
           Assert.assertEquals(actualNewsCount,previousNewsCount);
    }
 public void setUpReport (String name,String description){
        test =extent.createTest(name,description);
     sharedFunction = new SharedFunction(extent,test);
     costumFunctions = new CostumFunctions(extent,test,driver);
 }
    private void initiate(String url) {
        driver.navigate().to(url);
        frontSharedElement = new FrontSharedElement(driver);
        updateUserElements = new UpdateUserElements(driver);
        boSharedElements = new BoSharedElements(driver);
        contentManagementNewsElements = new ContentManagementNewsElements(driver);

    }
}
