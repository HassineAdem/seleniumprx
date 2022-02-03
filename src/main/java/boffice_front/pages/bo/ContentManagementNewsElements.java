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
public class ContentManagementNewsElements {
    public ContentManagementNewsElements(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath ="/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/ul[1]/li[6]/ul[1]/li[5]/a[1]" )
    @CacheLookup
    private WebElement newsManagementOption;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/button[1]")
    @CacheLookup
    private WebElement  addNewsButton;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/div[4]/span[1]/button[1]")
    @CacheLookup
    private WebElement  startDateCalenderIcon;
    @FindBy (xpath = "/html[1]/body[1]/div[7]/div[1]/a[2]")
    @CacheLookup
    private WebElement incrementMnthStartDateIcon;
    @FindBy (xpath = "/html[1]/body[1]/div[7]/div[1]/a[2]")
    @CacheLookup
    private WebElement incrementMnthEndDateIcon;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/div[6]/span[1]/button[1]")
    @CacheLookup
    private WebElement endDateCalenderIcon;
    @FindBy (xpath = "body.main-body:nth-child(2) div.layout-wrapper:nth-child(1) div.layout-main:nth-child(4) div.layout-main-content div.card.card-w-title div.ui-panelgrid.ui-widget.ui-panelgrid-blank.ui-fluid:nth-child(4) div.ui-panelgrid-content.ui-widget-content.ui-grid.ui-grid-responsive div.ui-g:nth-child(4) div.ui-panelgrid-cell.ui-g-3.ui-md-2:nth-child(2) div.ui-selectbooleanbutton.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-icon-left > span.ui-button-text.ui-c")
    @CacheLookup
    private WebElement statusButton ;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[2]")
    @CacheLookup
    private WebElement channelWebButton;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/input[1]")
    @CacheLookup
    private WebElement titleInputEng;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/div[4]/input[1]")
    @CacheLookup
    private WebElement titleInputFr;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/div[6]/input[1]")
    @CacheLookup
    private WebElement titleInputAr;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[3]")
    @CacheLookup
    private WebElement categorySelect;
    @FindBy (xpath = "/html[1]/body[1]/div[8]/div[1]/ul[1]/li[2]")
    @CacheLookup
    private WebElement categoryOption;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/div[3]/div[4]/div[1]/div[3]")
    @CacheLookup
    private WebElement subcategorySelect;
    @FindBy (xpath = "/html[1]/body[1]/div[15]/div[1]/ul[1]/li[2]")
    @CacheLookup
    private  WebElement testNewsSubOption;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/div[3]/div[6]/div[1]/div[3]")
    @CacheLookup
    private WebElement typeSelect;
    @FindBy (xpath = "/html[1]/body[1]/div[9]/div[1]/ul[1]/li[2]" )
    @CacheLookup
    private WebElement newTypeOption;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/div[4]/div[2]/div[1]")
    @CacheLookup
    private WebElement publishedButton;
    @FindBy (id = "form:web")
    @CacheLookup
    private WebElement webChannel;
    @FindBy (xpath = "//body/div[1]/div[3]/div[2]/div[1]/form[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/iframe[1]" )
    @CacheLookup
    private WebElement iframeDescriptionEng;
    @FindBy (xpath = "//body/div[1]/div[3]/div[2]/div[1]/form[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/iframe[1]")
    @CacheLookup
    private WebElement iframeDescriptionFr;
    @FindBy (xpath = "//body/div[1]/div[3]/div[2]/div[1]/form[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/iframe[1]")
    @CacheLookup
    private WebElement iframeDescriptionAr;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/span[1]/input[1]")
    @CacheLookup
    private WebElement chooseImageInput;

    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]")
    @CacheLookup
    private WebElement uploadImageButton;
    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[7]/div[1]/div[1]/div[2]/button[1]")
    @CacheLookup
    private WebElement saveButton;
    @FindBy (xpath = "/html[1]/body[1]/div[3]/div[3]/button[1]")
    @CacheLookup
    private WebElement confirmationSaveButton;

}
