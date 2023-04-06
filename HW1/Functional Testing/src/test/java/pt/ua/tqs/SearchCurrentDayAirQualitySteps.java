package pt.ua.tqs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pt.ua.tqs.PageObjects.CurrentDayAirQualityPage;
import pt.ua.tqs.PageObjects.HomePage;

import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.*;

import java.time.Duration;


public class SearchCurrentDayAirQualitySteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homepage;
    private CurrentDayAirQualityPage currentDayAirQualityPage;


    @Given("I am on the {string} website")
    public void navigateTo(String url) {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  //wait up to 10 seconds for API calls complete
        homepage = new HomePage(driver, wait, url);
    }


    @When("I click on the Today's Air Quality button")
    public void clickTodaysAirQualityBtn() {
        currentDayAirQualityPage = homepage.clickTodaysAirQualityBtn();
    }

    
    @Then("I should be redirected to a new page with the title {string}")
    public void pageHasTitle(String title) {
        assertThat(currentDayAirQualityPage.getTitle()).isEqualTo(title);
    }


    @When("I insert {string} in the City field")
    public void fillCityInput(String city) {
        currentDayAirQualityPage.fillCityInput(city);
    }


    @When("I insert {string} in the Country Code field")
    public void fillCoutryCodeInput(String countryCode) {
        currentDayAirQualityPage.fillCoutryCodeInput(countryCode);
    }


    @When("I click on the Search button")
    public void clickSearchBtn() throws InterruptedException {
        currentDayAirQualityPage.clickSearchBtn();
    }


    @Then("I should see the text {string}")
    public void checkResultsText(String searchSummary) {
        assertThat(currentDayAirQualityPage.getSearchSummary()).isEqualTo(searchSummary);
    }


    @Then("I should see the coordinates {string}")
    public void checkCoords(String coords) {
        assertThat(currentDayAirQualityPage.getCoords()).isEqualTo(coords);
    }


    @Then("I should see at least one result")
    public void checkResultsDate() {
        assertThat(currentDayAirQualityPage.existsAtLeastOneResult()).isTrue();
    }


    @Then("I should see the message {string}")
    public void checkMessage(String msg) {
        assertThat(currentDayAirQualityPage.getErrorLocation()).isEqualTo(msg);
    }
}
