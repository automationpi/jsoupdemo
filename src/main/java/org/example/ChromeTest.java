package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.time.Duration;


public class ChromeTest {
    WebDriver driver;
    void click(String linkText){
        String pageSource = driver.getPageSource();    
        Document doc =  Jsoup.parse(pageSource);
        Element myLink = doc.select(":containsOwn("+linkText+")").first();

        try{
            if(myLink!=null){
                String cssLocator = myLink.cssSelector();
                driver.findElement(By.cssSelector(cssLocator)).click();
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    void goBack(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.history.go(-1)");
    }

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void jsoupSeleniumTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        click("Web form");
        goBack();
        click("Navigation");
        goBack();
        click("Web Storage");
        goBack();
        click("Geolocation");
    }


    
}
