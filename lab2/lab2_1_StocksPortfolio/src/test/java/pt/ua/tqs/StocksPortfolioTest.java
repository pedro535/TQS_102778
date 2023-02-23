package pt.ua.tqs;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StocksPortfolioTest {

    @Test
    @DisplayName("Test getTotalValue method")
    void testGetTotalValue() {

        //1. Prepare a mock to subsitute the remote service (or @Mock annotation)
        IStockmarketService mockedMarket = mock(IStockmarketService.class);

        //2. Create an instance of the subjet under test (SUT) and use the mock to set the (remote) service
        StocksPortfolio myStocks = new StocksPortfolio(mockedMarket);

        //3. Load the mock with the proper expectations (when...thenReturn)
        when(mockedMarket.lookUpPrice("EBAY")).thenReturn(4.0);
        when(mockedMarket.lookUpPrice("MSFT")).thenReturn(2.0);

        //4. Execute the test (use the service in the SUT)
        myStocks.addStock(new Stock("EBAY", 2));
        myStocks.addStock(new Stock("MSFT", 3));

        //5. Verify the results (assert) and verify the mock was used (verify)
        assertEquals(14.0, myStocks.getTotalValue());

        verify(mockedMarket).lookUpPrice("EBAY"); //review this
        verify(mockedMarket).lookUpPrice("MSFT");
    }
    
}
