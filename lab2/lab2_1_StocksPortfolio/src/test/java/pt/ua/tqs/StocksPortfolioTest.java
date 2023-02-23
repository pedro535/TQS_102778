package pt.ua.tqs;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class StocksPortfolioTest {

    @Test
    @DisplayName("Test getTotalValue method with mock service")
    void testGetTotalValue() {

        //1. Prepare a mock to subsitute the remote service (or @Mock annotation)
        IStockmarketService mockedMarket = mock(IStockmarketService.class);

        //2. Create an instance of the subject under test (SuT) and use the mock to set the (remote) service instance.
        StocksPortfolio myStocks = new StocksPortfolio(mockedMarket);

        //3. Load the mock with the proper expectations (when...thenReturn)
        when(mockedMarket.lookUpPrice("TSLA")).thenReturn(2.0);
        when(mockedMarket.lookUpPrice("NFLX")).thenReturn(1.0);
        when(mockedMarket.lookUpPrice("AAPL")).thenReturn(4.0);

        //4. Execute the test (use the service in the SuT)
        myStocks.addStock(new Stock("TSLA", 2));
        myStocks.addStock(new Stock("NFLX", 1));
        myStocks.addStock(new Stock("AAPL", 3));
        double totalValue = myStocks.getTotalValue();

        //5. Verify the results (assert) and the use of the mock (verify)
        assertThat(totalValue, equalTo(17.0));
        verify(mockedMarket).lookUpPrice("TSLA");
        verify(mockedMarket).lookUpPrice("NFLX");
        verify(mockedMarket).lookUpPrice("AAPL");
    }
    
}
