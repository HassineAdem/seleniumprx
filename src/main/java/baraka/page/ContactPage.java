package baraka.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactPage {
    public ContactPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[1]/main[1]/div[1]/div[2]/section[1]/app-contuct-us[1]/div[2]/div[1]/div[1]/formio[1]/div[1]/div[1]/div[1]/div[1]/div[10]/div[1]/div[2]/button[1]")
    @CacheLookup
    private WebElement submitButton;

}
