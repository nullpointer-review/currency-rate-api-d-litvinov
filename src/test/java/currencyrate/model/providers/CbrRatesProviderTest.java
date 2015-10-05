package currencyrate.model.providers;

import currencyrate.client.CbrClient;
import currencyrate.model.Rate;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.of;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CbrRatesProviderTest {
    private CbrRatesProvider ratesProvider = new CbrRatesProvider();
    private LocalDate testDate = of(2015, 9, 24);

    @Before
    public void setUp() {
        ratesProvider.cbrClient = mock(CbrClient.class, RETURNS_DEEP_STUBS);
        when(ratesProvider.cbrClient.getRatesOn(testDate))
                .thenReturn("<ValCurs Date=\"24/09/2015\" name=\"Foreign Currency Market\">\n" +
                        "<Valute ID=\"R01235\">\n" +
                        "\t<NumCode>840</NumCode>\n" +
                        "\t<CharCode>USD</CharCode>\n" +
                        "\t<Nominal>1</Nominal>\n" +
                        "\t<Name>Доллар США</Name>\n" +
                        "\t<Value>66,0410</Value>\n" +
                        "</Valute>\n" +
                        "<Valute ID=\"R01239\">\n" +
                        "\t<NumCode>978</NumCode>\n" +
                        "\t<CharCode>EUR</CharCode>\n" +
                        "\t<Nominal>1</Nominal>\n" +
                        "\t<Name>Евро</Name>\n" +
                        "\t<Value>73,5367</Value>\n" +
                        "</Valute>\n" +
                        "</ValCurs>");
    }

    @Test
    public void batchRequest() throws Exception {
        List<Rate> rates = ratesProvider.getRatesOn(testDate);
        assertThat(rates, containsInAnyOrder(new Rate("USD", 66.0410f, testDate),
                new Rate("EUR", 73.5367f, testDate)));
    }

    @Test
    public void simpleRequest() throws Exception {
        Rate rate = ratesProvider.getRate("USD", testDate);
        assertThat(rate, is(new Rate("USD", 66.0410f, testDate)));
    }

    @Test(expected = RateNotFoundException.class)
    public void notFoundRate() throws Exception {
        ratesProvider.getRate("XXX", testDate);
    }
}