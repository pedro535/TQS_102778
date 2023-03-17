package pt.ua.tqs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuyTripSteps {

    private WebDriver driver;
    

    @Given("I am on the {string} website")
    public void navigateTo(String url) {
        driver = WebDriverManager.firefoxdriver().create();
        driver.get(url);
    }


    @When("I select {string} as departure city")
    public void selectDepartureCity(String city) {
        WebElement dropdown = driver.findElement(By.name("fromPort"));
        String xpath = "//option[. = '" + city + "']";
        dropdown.findElement(By.xpath(xpath)).click();
    }


    @When("I select {string} as destination city")
    public void selectDestinationCity(String city) {
        WebElement dropdown = driver.findElement(By.name("toPort"));
        String xpath = "//option[. = '" + city + "']";
        dropdown.findElement(By.xpath(xpath)).click();
    }


    @When("I click on the Find Flights button")
    public void clickFindFlightsBtn() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }


    @When("I choose the flight {int}")
    public void chooseFlight(int number) {
        String cssSelector = "tr:nth-child(" + number + ") .btn";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }


    @When("I fill the name form with {string}")
    public void fillNameForm(String name) {
        driver.findElement(By.id("inputName")).sendKeys(name);
    }


    @When("I fill the Location form with the Address {string}, City {string}, State {string} and ZipCode {string}")
    public void fillLocationForm(String address, String city, String state, String zipCode) {
        driver.findElement(By.id("address")).sendKeys(address);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("zipCode")).sendKeys(zipCode);
    }


    @When("I fill the Card form with Card Type {string}, Card Number {string}, Month {string}, Year {string} and Name on Card {string}")
    public void fillCardForm(String cardType, String cardNumber, String month, String year, String nameOnCard) {
        WebElement dropdown = driver.findElement(By.id("cardType"));
        dropdown.findElement(By.xpath("//option[. = '" + cardType + "']")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys(cardNumber);
        driver.findElement(By.id("creditCardMonth")).sendKeys(month);
        driver.findElement(By.id("creditCardYear")).sendKeys(year);
        driver.findElement(By.id("nameOnCard")).sendKeys(nameOnCard);
    }


    @When("I click on the Purchase Flight button")
    public void clickPurchaseFlightBtn() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    
    @Then("I should be redirected to a page with the title {string}")
    public void verifyTitle(String expected) {
        assertThat(driver.getTitle(), equalTo(expected));
    }
    

    @Then("I should see the {string} phrase {string}")
    public void verifyMainPhrase(String tag, String expected) {
        assertThat(driver.findElement(By.cssSelector(tag)).getText(), is(expected));
    }

}
