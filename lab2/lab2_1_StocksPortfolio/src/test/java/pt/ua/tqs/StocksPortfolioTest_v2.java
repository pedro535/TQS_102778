package pt.ua.tqs;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest_v2 {

    @Mock
    IStockmarketService mockedMarket;

    @InjectMocks
    StocksPortfolio myStocks;

    @Test
    @DisplayName("Test getTotalValue method")
    void testGetTotalValue() {

        //1. Load the mock with the proper expectations (when...thenReturn)
        when(mockedMarket.lookUpPrice("EBAY")).thenReturn(4.0);
        when(mockedMarket.lookUpPrice("MSFT")).thenReturn(2.0);

        //2. Execute the test (use the service in the SUT)
        myStocks.addStock(new Stock("EBAY", 2));
        myStocks.addStock(new Stock("MSFT", 3));

        //3. Verify the results (assert) and verify the mock was used (verify)
        assertEquals(14.0, myStocks.getTotalValue());

        verify(mockedMarket).lookUpPrice("EBAY"); //review this
        verify(mockedMarket).lookUpPrice("MSFT");
    }
    
}
