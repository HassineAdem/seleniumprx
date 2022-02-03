package boffice_front.utils;

import main.WebConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class DateConverter  {
DateConverter (){

}
    private static final List<String> MONTHS_ENG = Arrays.asList(new String[]{"January","February","March","April","May","June","July","August","" +
            "September","October","November","December"});

    public String convertIndexToAMnth (String mmDate){
    return MONTHS_ENG.get(Integer.parseInt(mmDate)-1);
    }
}
