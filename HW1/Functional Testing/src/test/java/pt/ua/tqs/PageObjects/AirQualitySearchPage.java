package pt.ua.tqs.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AirQualitySearchPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(id = "page-title")
    protected WebElement pageTitle;

    @FindBy(id = "city")
    protected WebElement cityInput;

    @FindBy(id = "country-code")
    protected WebElement countryCodeInput;

    @FindBy(id = "search")
    protected WebElement searchBtn;

    @FindBy(id = "search-summary")
    protected WebElement searchSummary;

    @FindBy(id = "coords")
    protected WebElement coords;

    @FindBy(id = "error-message")
    protected WebElement errorMessage;

    
    public AirQualitySearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    /*
    * Page Actions as methods
    */
    
    public String getTitle() {
        return pageTitle.getText();
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


    public String getErrorMessage() {
        return errorMessage.getText();
    }
    
}
