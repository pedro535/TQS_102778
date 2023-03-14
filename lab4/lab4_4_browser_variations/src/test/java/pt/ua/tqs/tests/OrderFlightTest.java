package pt.ua.tqs.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.assertThat;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import pt.ua.tqs.webpages.ConfirmationPage;
import pt.ua.tqs.webpages.FlightsPage;
import pt.ua.tqs.webpages.HomePage;
import pt.ua.tqs.webpages.ReservationPage;
import io.github.bonigarcia.seljup.DockerBrowser;
import static io.github.bonigarcia.seljup.BrowserType.FIREFOX;
import org.openqa.selenium.WebDriver;

@ExtendWith(SeleniumJupiter.class)
public class OrderFlightTest {

    @Test
    @DisplayName("Order a flight test using Page Object Pattern (POM)")
    public void testOrderFlight(@DockerBrowser(type = FIREFOX) WebDriver driver) {

        //Homepage
        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("Cairo");

        //Flights page
        FlightsPage flights = home.findFlights();

        //Reservation page
        ReservationPage reservation = flights.chooseFlight(2);
        reservation.fillNameInfo("Pedro");
        reservation.fillLocationInfo("Rua dos Moinhos", "Viseu", "PT", "12345");
        reservation.fillCardInfo("American Express", "123456789", "12", "2024", "Pedro Rodrigues");

        //Confirmation page
        ConfirmationPage confirmation = reservation.purchaseFlight();
        assertThat(confirmation.getTitle()).isEqualTo("BlazeDemo Confirmation");
    }

}
