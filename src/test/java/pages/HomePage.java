package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonAPI {
    Logger LOG = LogManager.getLogger(HomePage.class.getName());

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".title")
    WebElement header;

    public String getHeaderText(){
        String headerText = getElementText(header);
        LOG.info("header text: "+headerText);
        return headerText;
    }
}