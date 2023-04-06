package pt.ua.tqs.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AirQualityForecastPage extends AirQualitySearchPage {


    @FindBy(id = "total-days")
    private WebElement totalDaysInput;

    public AirQualityForecastPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }


    /*
    * Page Actions as methods
    */
    
    public void fillTotalDaysInput(int totalDays) {
        totalDaysInput.sendKeys(Integer.toString(totalDays));
    }


    public boolean existAvailableDates(int totalDates) {
        String cssSelector = String.format(".my-btn:nth-child(%d)", totalDates);

        try {
            driver.findElement(By.cssSelector(cssSelector));
            return true;
            
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
}
