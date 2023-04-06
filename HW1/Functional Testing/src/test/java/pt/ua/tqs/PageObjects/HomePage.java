package pt.ua.tqs.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "todays-air-quality")
    private WebElement todaysAirQualityBtn;

    @FindBy(id = "air-quality-forecast")
    private WebElement airQualityForecastBtn;


    public HomePage(WebDriver driver, WebDriverWait wait, String URL) {
        this.driver = driver;
        this.wait = wait;
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }



    /*
    * Page Actions as methods
    */

    public CurrentDayAirQualityPage clickTodaysAirQualityBtn() {
        todaysAirQualityBtn.click();
        return new CurrentDayAirQualityPage(driver, wait);
    }


    public AirQualityForecastPage clickAirQualityForecastBtn() {
        airQualityForecastBtn.click();
        return new AirQualityForecastPage(driver, wait);
    }    
}
