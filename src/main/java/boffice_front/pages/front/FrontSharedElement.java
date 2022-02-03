package boffice_front.pages.front;

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
public class FrontSharedElement {
    public FrontSharedElement(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "username")
    @CacheLookup
    private WebElement usernameLogin;
    @FindBy (id = "password")
    @CacheLookup
    private WebElement passwordLogin ;
    @FindBy (xpath = "/html[1]/body[1]/div[4]/div[1]/div[4]/div[1]/section[1]/div[1]/div[1]/div[1]/p[1]")
    @CacheLookup
    private WebElement otpMessage;
    @FindBy (xpath = "/html[1]/body[1]/div[4]/div[1]/div[4]/div[1]/section[1]/div[1]/div[1]/form[1]/div[1]/input[1]" )
    @CacheLookup
    private WebElement otpInput;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/header[1]/div[1]/div[2]/div[1]/div[1]/p[1]")
    @CacheLookup
    private WebElement myUserFullName;

    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[2]")
    @CacheLookup
    private WebElement contentMenuChoice;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[4]/a[3]/div[1]")
    @CacheLookup
    private WebElement newsChoice;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/header[1]/div[1]/div[1]")
    @CacheLookup
    private WebElement menuIcon;
    @FindBy (xpath = "")
    @CacheLookup
    private WebElement profileMenuChoice;


}
