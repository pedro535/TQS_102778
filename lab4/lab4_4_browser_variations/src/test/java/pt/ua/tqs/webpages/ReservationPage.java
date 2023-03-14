package pt.ua.tqs.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ReservationPage {

    private WebDriver driver;

    @FindBy(id = "inputName")
    private WebElement name;

    @FindBy(id = "address")
    private WebElement address;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "zipCode")
    private WebElement zipCode;

    @FindBy(id = "cardType")
    private WebElement cardTypeDropDown;

    @FindBy(id = "creditCardNumber")
    private WebElement creditCardNumber;

    @FindBy(id = "creditCardYear")
    private WebElement creditCardYear;

    @FindBy(id = "creditCardMonth")
    private WebElement creditCardMonth;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCard;

    @FindBy(css = ".btn-primary")
    private WebElement purchaseFlightButton;

    public ReservationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /*
     * Page Actions as methods
     */

    public void fillNameInfo(String name) {
        this.name.clear();
        this.name.sendKeys(name);
    }

    public void fillLocationInfo(String address, String city, String state, String zipCode) {
        this.address.sendKeys(address);
        this.city.sendKeys(city);
        this.state.sendKeys(state);
        this.zipCode.sendKeys(zipCode);
    }


    public void fillCardInfo(String cardType, String creditCardNumber, String creditCardMonth, String creditCardYear, String nameOnCard) {
        this.cardTypeDropDown.findElement(By.xpath("//option[. = '" + cardType + "']"));
        this.creditCardNumber.sendKeys(creditCardNumber);
        this.creditCardMonth.sendKeys(creditCardMonth);
        this.creditCardYear.sendKeys(creditCardYear);
        this.nameOnCard.sendKeys(nameOnCard);
    }


    public ConfirmationPage purchaseFlight() {
        purchaseFlightButton.click();
        return new ConfirmationPage(this.driver);
    }


}
