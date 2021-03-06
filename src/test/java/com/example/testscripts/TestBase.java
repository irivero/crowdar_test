package com.example.testscripts;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;

import java.io.File;

import com.example.properties.Environment;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

abstract class TestBase {

    protected WebDriver driver;

    @BeforeSuite
    public void setupSuite() {
        String browser = Environment.getProperties().browser().toString().toLowerCase();

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
        
            default:
                WebDriverManager.chromedriver().setup();
                break;
        }        
    }

    /**

     * This function will configure the browser for each suite
     * We use switch structure because there are too many browsers to be added

     */
    @BeforeTest
    public void setup() {

        String browser = Environment.getProperties().browser().toString().toLowerCase();

        switch (browser) {
            
            case "firefox":
                FirefoxOptions options = new FirefoxOptions();            
                if(Environment.getProperties().headless()){
                    options.addArguments("--headless");
                }
                driver = new FirefoxDriver();
                break;
        
            default:
                ChromeOptions optionsChrome = new ChromeOptions();
                if(Environment.getProperties().headless()){
                    optionsChrome.addArguments("--headless");
                }       
                driver = new ChromeDriver(optionsChrome);
                break;
        }       

        driver.manage().window().maximize();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }    

    /**

     * This function will take screenshot

     * @param fileWithPath

     * @throws Exception

     */
    @Attachment(value = "Page screenshot {fileWithPath}", type = "image/png")
    protected byte[] takeSnapShot(String fileWithPath){
        try{
            TakesScreenshot scrShot =((TakesScreenshot)driver);

            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            File DestFile=new File("./target/pictures/"+ fileWithPath);

            FileUtils.copyFile(SrcFile, DestFile);

            return scrShot.getScreenshotAs(OutputType.BYTES);

        }catch(WebDriverException e){
            e.printStackTrace(); 

        }catch(Exception e){
            e.printStackTrace(); 
        }
        return null;        
    }   

}
