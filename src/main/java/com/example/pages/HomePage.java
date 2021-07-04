package com.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.example.properties.Environment;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends AbstractPage<HomePage> {

    @FindBy(xpath = "//span[@class='title']")
    private WebElement pageTitle;
    
    private String ProductsTitle = "Products";


    public HomePage(WebDriver driver) {
        super(driver);

    }

    @Step("Open the Home Page")
    @Override
    public void load() {
        driver.get(Environment.getProperties().url() + Environment.getProperties().home_page());
    }    

    @Override
    protected void isLoaded() throws Error {
        assertThat(pageTitle.getText()).isEqualTo(ProductsTitle);
    }  


}
