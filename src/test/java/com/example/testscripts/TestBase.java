package com.example.testscripts;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

abstract class TestBase {

    protected WebDriver driver;


    @BeforeSuite
    public void setupSuite() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
    }

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");        
        driver = new ChromeDriver(options);

        //FirefoxOptions options = new FirefoxOptions();
        //options.addArguments("--headless");
        //driver = new FirefoxDriver();

        driver.manage().window().maximize();

    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
    

    /**

     * This function will take screenshot

     * @param fileWithPath

     * @throws Exception

     */

    protected void takeSnapShot(String fileWithPath){
        try{
            TakesScreenshot scrShot =((TakesScreenshot)driver);

            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            File DestFile=new File("./target/pictures/"+ fileWithPath);

            FileUtils.copyFile(SrcFile, DestFile);


        }catch(Exception e){
            e.printStackTrace(); 
        }

        
    }

   

}
