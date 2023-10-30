package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonAPI {
    Logger LOG = LogManager.getLogger(LoginPage.class.getName());

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    //objects (web elements)
    @FindBy(css = "#user-name")
    WebElement usernameField;

    @FindBy(css = "#password")
    WebElement passwordField;

    @FindBy(css = "#login-button")
    WebElement loginBtn;

    @FindBy(css = ".error-message-container.error h3")
    WebElement errorMessage;


    //reusable steps
    public void typeUsername(String username){
        type(usernameField, username);
        LOG.info("type username success");
    }
    public void typePassword(String password){
        type(passwordField, password);
        LOG.info("type password success");
    }
    public void clickOnLoginBtn(){
        clickOn(loginBtn);
        LOG.info("click on login button success");
    }
    public String getErrorMessage(){
        String errorMessageText = getElementText(errorMessage);
        LOG.info("error message: "+errorMessageText);
        return errorMessageText;
    }
}