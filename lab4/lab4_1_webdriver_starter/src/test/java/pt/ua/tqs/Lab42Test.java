package pt.ua.tqs;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.*;

public class Lab42Test {

  private WebDriver driver;
  JavascriptExecutor js;

  @BeforeAll
  static void setupClass() {
      WebDriverManager.firefoxdriver().setup();
  }

  @BeforeEach
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void lab42() {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(1440, 875));
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Philadelphia']")).click();
    }
    driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(2)")).click();
    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'London']")).click();
    }
    driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(3)")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(2) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Pedro");
    driver.findElement(By.id("address")).sendKeys("Rodrigues");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("Viseu");
    driver.findElement(By.id("state")).click();
    driver.findElement(By.id("state")).sendKeys("PT");
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("12345");
    driver.findElement(By.id("cardType")).click();
    {
      WebElement dropdown = driver.findElement(By.id("cardType"));
      dropdown.findElement(By.xpath("//option[. = 'Visa']")).click();
    }
    driver.findElement(By.cssSelector("option:nth-child(3)")).click();
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).sendKeys("123456789");
    driver.findElement(By.id("creditCardYear")).click();
    driver.findElement(By.id("creditCardYear")).sendKeys("2024");
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("Pedro ");
    driver.findElement(By.id("nameOnCard")).sendKeys(Keys.DOWN);
    driver.findElement(By.id("nameOnCard")).sendKeys("Pedro Rodrigues");
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
  }

}
