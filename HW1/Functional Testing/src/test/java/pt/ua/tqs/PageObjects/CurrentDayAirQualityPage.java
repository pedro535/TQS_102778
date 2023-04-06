package pt.ua.tqs.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CurrentDayAirQualityPage {

    private WebDriver driver;
    
    private WebDriverWait wait;

    @FindBy(id = "page-title")
    private WebElement title;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "countryCode")
    private WebElement countryCodeInput;

    @FindBy(id = "search-todays-AQ")
    private WebElement searchBtn;

    @FindBy(id = "search-summary")
    private WebElement searchSummary;

    @FindBy(id = "coords")
    private WebElement coords;

    @FindBy(id = "error-location")
    private WebElement errorLocation;


    public CurrentDayAirQualityPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }


    /*
    * Page Actions as methods
    */
    
    public String getTitle() {
        return title.getText();
    }

        
    public void fillCityInput(String city) {
        cityInput.sendKeys(city);
    }


    public void fillCoutryCodeInput(String countryCode) {
        countryCodeInput.sendKeys(countryCode);
    }


    public void clickSearchBtn() {
        searchBtn.click();
    }


    public String getSearchSummary() {
        wait.until(ExpectedConditions.visibilityOf(searchSummary));
        return searchSummary.getText();
    }
    
    
    public String getCoords() {
        wait.until(ExpectedConditions.visibilityOf(coords));
        return coords.getText();
    }


    public boolean existsAtLeastOneResult() {
        wait.until(ExpectedConditions.visibilityOf(coords));
        
        try {
            driver.findElement(By.cssSelector(".flex:nth-child(1) .text-lg"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public String getErrorLocation() {
        return errorLocation.getText();
    }

}
