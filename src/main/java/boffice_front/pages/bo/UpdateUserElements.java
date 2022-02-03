package boffice_front.pages.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserElements {
    public UpdateUserElements (WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/ul[1]/li[2]/ul[1]/li[1]/a[1]/span[1]")
    @CacheLookup
    private WebElement costumersOptionInManagementMenuOption;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[1]/td[8]/button[1]/span[1]")
    @CacheLookup
    private WebElement updateOneUserInformation;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[2]/input[1]")
    @CacheLookup
    private WebElement firstNameInputUserInfo ;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[4]/input[1]")
    @CacheLookup
    private WebElement lastNameInputUserInfo;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/button[1]/span[1]")
    @CacheLookup
    private WebElement saveUpdateChangesButton;
    @FindBy (xpath = "/html[1]/body[1]/div[3]/div[3]/button[1]")
    @CacheLookup
    private WebElement confirmationForUpdatesButton;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[1]/td[5]")
    @CacheLookup
    private WebElement oneUserLastNameInTable;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/input[1]")
    @CacheLookup
    private WebElement usernameInputUserInfo;

}
