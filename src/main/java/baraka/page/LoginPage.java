package baraka.page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginPage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(name = "username")
    @CacheLookup
    private WebElement usernameInput;
    @FindBy (name = "password")
    @CacheLookup
    private WebElement passwordInput;
    @FindBy(name="login")
    @CacheLookup
    private WebElement buttonSubmit;
    @FindBy(id = "input-error")
    @CacheLookup
    private WebElement errorMessage;
    @FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-dashboard[1]/div[1]/div[1]/app-onboarding-widget[1]/div[1]/div[1]/div[1]/h2[1]")
    @CacheLookup
    private WebElement welcomeMessage;

}



