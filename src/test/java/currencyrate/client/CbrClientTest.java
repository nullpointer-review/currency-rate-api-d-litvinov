package currencyrate.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Test;

import static java.time.LocalDate.now;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CbrClientTest {
    private CbrClient client = new CbrClient();
    private CloseableHttpResponse response;

    @Before
    public void setUp() throws Exception {
        client.httpClient = mock(CloseableHttpClient.class, RETURNS_DEEP_STUBS);
        response = mock(CloseableHttpResponse.class, RETURNS_DEEP_STUBS);

        when(client.httpClient.execute(any(HttpGet.class))).thenReturn(response);
    }

    @Test(expected = OutgoingRequestException.class)
    public void checkResponceStatus() throws Exception {
        when(response.getStatusLine().getStatusCode()).thenReturn(500);
        client.getRatesOn(now());
    }
}