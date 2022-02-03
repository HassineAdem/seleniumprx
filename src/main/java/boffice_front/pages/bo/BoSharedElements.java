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
public class BoSharedElements {
    public BoSharedElements(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    @FindBy (css = "#username")
    @CacheLookup
    private WebElement usernameInput;
    @FindBy (css = "#password")
    @CacheLookup
    private WebElement passwordInput;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/ul[1]/li[2]/a[1]")
    @CacheLookup
    private WebElement userManagementMenuOption;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/ul[1]/li[6]/a[1]")
    @CacheLookup
    private WebElement contentManagementMenuOption;
}
