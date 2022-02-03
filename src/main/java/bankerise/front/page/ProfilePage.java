package bankerise.front.page;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
@Data
public class ProfilePage {
    public ProfilePage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    @FindBy (xpath = "//body/div[@id='root']/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[2]/div[1]/*[1]")
    @CacheLookup
    private WebElement updatePhoneNumberIcon;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[2]/p[1]")
    @CacheLookup
    private WebElement phoneNumberInput;
    @FindBy (xpath = "//body/div[@id='root']/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[2]/div[1]/*[1]")
    @CacheLookup
    private WebElement  updateEmailIcon;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[2]/p[1]")
    @CacheLookup
    private WebElement emailInput;
    @FindBy (xpath = "//body/div[@id='root']/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[7]/div[1]/div[2]/div[1]/*[1]")
    @CacheLookup
    private WebElement updateAddressIcon;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[7]/div[1]/div[2]/p[1]")
    @CacheLookup
    private WebElement addressInput;
    @FindBy (xpath = "//body/div[@id='root']/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[2]/div[1]/*[2]")
    @CacheLookup
    private WebElement confirmationPhoneUpdate;
    @FindBy (xpath = "//body/div[@id='root']/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[7]/div[1]/div[2]/div[1]/*[2]")
    @CacheLookup
    private WebElement confirmationAddressUpdate;
    @FindBy (xpath = "//body/div[@id='root']/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[2]/div[1]/*[2]")
    @CacheLookup
    private WebElement confirmationEmailUpdate;


}
