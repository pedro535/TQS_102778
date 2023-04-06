package pt.ua.tqs.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CurrentDayAirQualityPage extends AirQualitySearchPage {

    public CurrentDayAirQualityPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }


    /*
    * Page Actions as methods
    */
    
    public boolean existsAtLeastOneResult() {
        wait.until(ExpectedConditions.visibilityOf(coords));
        
        try {
            driver.findElement(By.cssSelector(".flex:nth-child(1) .text-lg"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
