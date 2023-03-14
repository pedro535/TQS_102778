package pt.ua.tqs.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;
    private String PAGE_URL = "https://blazedemo.com/";

    @FindBy(name = "fromPort")
    private WebElement departureDropdown;

    @FindBy(name = "toPort")
    private WebElement destinationDropdown;

    @FindBy(css = ".btn-primary")
    private WebElement findFlightsBtn;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }


    /*
    * Page Actions as methods
    */

    public void selectDepartureCity(String city) {
        String xpath_str = String.format("//option[. = '%s']", city);
        departureDropdown.findElement(By.xpath(xpath_str)).click();
    }

    public void selectDestinationCity(String city) {
        String xpath_str = String.format("//option[. = '%s']", city);
        destinationDropdown.findElement(By.xpath(xpath_str)).click();
    }

    public FlightsPage findFlights() {
        findFlightsBtn.click();
        return new FlightsPage(this.driver);
    }


}
