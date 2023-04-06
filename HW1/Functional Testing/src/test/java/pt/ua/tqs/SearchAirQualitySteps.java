package pt.ua.tqs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pt.ua.tqs.pageObjects.AirQualityForecastPage;
import pt.ua.tqs.pageObjects.AirQualitySearchPage;
import pt.ua.tqs.pageObjects.CurrentDayAirQualityPage;
import pt.ua.tqs.pageObjects.HomePage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.Assertions.*;
import java.time.Duration;


public class SearchAirQualitySteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homepage;
    private AirQualitySearchPage searchPage;


    @Given("I am on the {string} website")
    public void navigateTo(String url) {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  //wait up to 10 seconds for API calls complete
        homepage = new HomePage(driver, wait, url);
    }


    @When("I click on the Today's Air Quality button")
    public void clickTodaysAirQualityBtn() {
        searchPage = homepage.clickTodaysAirQualityBtn();
    }


    @When("I click on the Air Quality Forecast button")
    public void clickAirQualityForecastBtn() {
        searchPage = homepage.clickAirQualityForecastBtn();
    }

    
    @Then("I should be redirected to a new page with the title {string}")
    public void pageHasTitle(String title) {
        assertThat(searchPage.getTitle()).isEqualTo(title);
    }


    @When("I insert {string} in the City field")
    public void fillCityInput(String city) {
        searchPage.fillCityInput(city);
    }


    @When("I insert {string} in the Country Code field")
    public void fillCoutryCodeInput(String countryCode) {
        searchPage.fillCoutryCodeInput(countryCode);
    }


    @When("I insert {int} in the totalDays field")
    public void fillTotalDaysInput(int totalDays) {
        AirQualityForecastPage forecastPage = (AirQualityForecastPage) searchPage;
        forecastPage.fillTotalDaysInput(totalDays);
    }

    
    @When("I click on the Search button")
    public void clickSearchBtn() throws InterruptedException {
        searchPage.clickSearchBtn();
    }
    
    
    @Then("I should see the text {string}")
    public void checkResultsText(String searchSummary) {
        assertThat(searchPage.getSearchSummary()).isEqualTo(searchSummary);
    }
    
    
    @Then("I should see the coordinates {string}")
    public void checkCoords(String coords) {
        assertThat(searchPage.getCoords()).isEqualTo(coords);
    }
    
    
    @Then("I should see at least one result")
    public void checkResultsDate() {
        CurrentDayAirQualityPage currentDayAirQualityPage = (CurrentDayAirQualityPage) searchPage;
        assertThat(currentDayAirQualityPage.existsAtLeastOneResult()).isTrue();
    }
    
    
    @Then("I should see exactly {int} available dates")
    public void totalAvailableDates(int availableDates) {
        AirQualityForecastPage forecastPage = (AirQualityForecastPage) searchPage;
        assertThat(forecastPage.existAvailableDates(availableDates)).isTrue();
    }


    @Then("I should see the message {string}")
    public void checkMessage(String msg) {
        assertThat(searchPage.getErrorMessage()).isEqualTo(msg);
    }


    /*
     * Quit driver
    */

    @Then("I close the browser")
    public void closeBrowser() {
        driver.quit();
    }
}
