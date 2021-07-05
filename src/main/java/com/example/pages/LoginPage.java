package com.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.example.properties.Environment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class LoginPage extends AbstractPage<LoginPage> {

    @FindBy(xpath = "//input[@data-test='username']")
    private WebElement username;
    
    @FindBy(xpath = "//input[@data-test='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@data-test='login-button']")
    private WebElement loginButton;

    @FindBy(className = "error-button")
    private WebElement xErrorMessage;

    @FindBy(className = "login_logo")
    private WebElement logo;

    @FindBy(className = "loading")
    private WebElement loading;

    @FindBy(xpath = "//div[@class='error-message-container error']//*")
    private List<WebElement> errorElements; 

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    private String title = "Swag Labs";

    //messages
    private String usernameRequiredMessage = "Epic sadface: Username is required";
    private String passwordRequiredMessage = "Epic sadface: Password is required";
    private String lockedUserMessage = "Epic sadface: Sorry, this user has been locked out.";
    private String accessNotAllowed = "Epic sadface: You can only access '/inventory.html' when you are logged in.";
    private String userNotFound = "Epic sadface: Username and password do not match any user in this service";


    public LoginPage(WebDriver driver) {
        super(driver);

    }

    @Step("Open the Login Page")
    @Override
    public void load() {
        driver.get(Environment.getProperties().url());
    }    

    @Override
    protected void isLoaded() throws Error {
        assertThat(driver.getTitle()).isEqualTo(title);
    }   
    

    @Step("Type Username {usernameText}")
    public void typeUsername(String usernameText) {
        username.sendKeys(usernameText);
    }

    @Step("Type Password {passwordText}")
    public void typePassword(String passwordText) {
        password.sendKeys(passwordText);
    }

    @Step("Click Login Button")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Click Close Error Message")
    public void clickCloseErrorMessage() {
        xErrorMessage.click();
    }


    //util
    public WebElement getErrorMessage(){
        return errorMessage;
    }

    public String getUsernameRequiredMessage(){
        return usernameRequiredMessage;
    }

    public String getPasswordRequiredMessage(){
        return passwordRequiredMessage;
    }
    
    public String getLockedUserMessage() {
        return lockedUserMessage;
    }

    public String getAccessNotAllowedMessage() {
        return accessNotAllowed;
    }

    public String getNotFoundUserMessage() {
        return userNotFound;
    }

    public WebElement getUsernameInput() {
        return username;
    }

    
    public WebElement getPasswordInput() {
        return username;
    }

    
    public WebElement getLoginButton() {
        return username;
    }

    
    public WebElement getLogo() {
        return logo;
    }

    public WebElement getLoading() {
        return loading;
    }

    public List<WebElement> getErrorMessageComponents() {
        return errorElements;
    }

   





}
