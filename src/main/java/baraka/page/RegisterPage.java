package baraka.page;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterPage {
    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//body[1]/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-register[1]/div[1]/div[2]/div[1]/formio[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")
    @CacheLookup
    private WebElement selectInputButton;
    @FindBy (name = "data[data_accountNumber]")
    @CacheLookup
    private WebElement accountNumber;
    @FindBy (xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-register[1]/div[1]/div[2]/div[1]/formio[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/button[1]")
    @CacheLookup
    private WebElement submitButton;
    @FindBy (xpath = "//body[1]/app-root[1]/div[1]/app-error-modal[1]/div[1]/div[1]/div[1]/div[2]/div[1]/label[1]")
    @CacheLookup
    private WebElement errorMessage;
    @FindBy (xpath = "/html[1]/body[1]/app-root[1]/div[1]/app-error-modal[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/button[1]")
    @CacheLookup
    private WebElement websiteBug;
    @FindBy(xpath = "//body/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-register[1]/div[1]/div[2]/div[1]/formio[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]")
    @CacheLookup
    private WebElement accountNumberErrorMessage;
    @FindBy(xpath = "//div[contains(text(),'Validation de votre numéro')]")
    @CacheLookup
    private WebElement phoneValidationMessage;
    @FindBy(css = "#phoneNumber")
    @CacheLookup
    private WebElement phoneInput;
    @FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-number-validation[1]/div[1]/div[1]/form[1]/div[2]/div[2]/button[1]")
    @CacheLookup
    private WebElement submitPhoneButton;
    @FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/app-error-modal[1]/div[1]/div[1]/div[1]/div[2]/div[1]/label[1]")
    @CacheLookup
    private WebElement invalidPhoneNumberError;
    @FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-register-wizard[1]/div[1]/formio[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/p[1]")
    @CacheLookup
    private WebElement thirdStepRegistrationWelcomeText;
    @FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/app-error-modal[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/button[1]")
    @CacheLookup
    private WebElement submitInvalidPhoneNumberButton ;

}
