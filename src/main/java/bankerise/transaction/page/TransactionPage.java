package bankerise.page;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPage {

    public TransactionPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
    @FindBy (id = "username")
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
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[3]/a[1]")
    @CacheLookup
    private  WebElement transferChoiceMenu;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/button[1]")
    @CacheLookup
    private WebElement addTransactionIcon;
    @FindBy(xpath = "/html[1]/body[1]/div[5]/div[1]/div[4]/div[1]/section[1]/div[1]/div[2]/div[1]/div[2]")
    @CacheLookup
    private WebElement withinBankTransaction ;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/select[1]")
    @CacheLookup
    private WebElement debitAccountSelect;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/select[1]")
    @CacheLookup
    private WebElement beneficiarySelect;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/input[1]")
    @CacheLookup
    private WebElement transferAmountInput;
    @FindBy (css = "#transferDate")
   @CacheLookup
    private WebElement transferDate;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[6]/div[1]/div[1]/div[1]/button[1]")
    @CacheLookup
    private WebElement submitTransactionButton;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/select[1]/option[2]")
    @CacheLookup
    private WebElement optionAcountCard;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/a[1]")
    @CacheLookup
    private WebElement dashboardMenuIcon;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]")
    @CacheLookup
    private WebElement totalBalanceMoney;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")
    @CacheLookup
    private WebElement cardsDashboard;
   @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[2]/button[1]")
    @CacheLookup
    private WebElement addBeneficiarySubmitButton;
   @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[4]/a[1]")
    @CacheLookup
    private WebElement beneficiaryIconMenu;
   @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/button[1]")
    private WebElement beneficiaryAddIcon;
   @FindBy (xpath = "/html[1]/body[1]/div[5]/div[1]/div[4]/div[1]/section[1]/div[1]/div[2]/div[2]")
    private WebElement addBeneficiaryWithinBank;
   @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[4]/div[1]/label[1]")
    private WebElement reasonText ;
   @FindBy(css = "#debitAccount-feedback")
    private WebElement debitAccountValidationText;
   @FindBy(css = "#beneficiaryNickname-feedback")
    private WebElement beneficiaryValidationText;
   @FindBy (css = "#transferDate-feedback")
    private WebElement transferDateValidationText;


}
