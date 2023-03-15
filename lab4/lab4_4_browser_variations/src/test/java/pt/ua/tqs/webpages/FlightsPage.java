package pt.ua.tqs.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FlightsPage {

    private WebDriver driver;

    private By flightButton;   //Not using @Find in order to select the button by number!

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /*
     * Page Actions as methods
     */

    public ReservationPage chooseFlight(int flightIdx) {
        flightButton = By.cssSelector("tr:nth-child(" + flightIdx +") .btn");
        driver.findElement(flightButton).click();
        return new ReservationPage(this.driver);
    }


}
