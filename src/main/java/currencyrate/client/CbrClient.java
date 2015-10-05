package currencyrate.client;


import com.sun.jersey.api.uri.UriBuilderImpl;
import currencyrate.utils.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static javax.ws.rs.core.Response.Status.Family.SUCCESSFUL;
import static javax.ws.rs.core.Response.Status.fromStatusCode;

public class CbrClient {
    private static final Logger logger = Logger.getLogger(CbrClient.class);

    CloseableHttpClient httpClient = HttpClients.createDefault();

    private static URI buildUri(LocalDate date) {
        return new UriBuilderImpl().scheme("http").host("www.cbr.ru")
                .path("/scripts/XML_daily.asp")
                .queryParam("date_req", date.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")))
                .build();
    }

    private static void requireSuccessful(CloseableHttpResponse response) {
        if (isNotSuccessful(response)) {
            throw new OutgoingRequestException("Request failed. Response: " + response);
        }
    }

    private static boolean isNotSuccessful(CloseableHttpResponse response) {
        int code = response.getStatusLine().getStatusCode();
        return fromStatusCode(code).getFamily() != SUCCESSFUL;
    }

    public String getRatesOn(LocalDate date) {
        HttpGet get = new HttpGet(buildUri(date));
        try (CloseableHttpResponse response = httpClient.execute(get)) {
            logger.debug(() -> "response = " + response);

            requireSuccessful(response);

            HttpEntity entity = response.getEntity();
            Objects.requireNonNull(entity, "Response entity is null");

            String responseEntity = EntityUtils.toString(entity);
            logger.trace(() -> "responseEntity = " + responseEntity);
            return responseEntity;
        } catch (IOException e) {
            throw new OutgoingRequestException(e);
        }
    }
}
