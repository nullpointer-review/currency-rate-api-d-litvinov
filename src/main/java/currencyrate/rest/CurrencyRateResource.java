package currencyrate.rest;

import currencyrate.model.Rate;
import currencyrate.model.providers.RateProvider;
import currencyrate.utils.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static java.time.LocalDate.now;

/**
 * This realization is not considered the time the exchange rate is set.
 * It's may cause that the rate request on the next day may return the rate of the current day
 */
@Path("rate")
@Produces("application/json")
public class CurrencyRateResource {
    private static final Logger logger = Logger.getLogger(CurrencyRateResource.class);

    RateProvider rateProvider;

    public CurrencyRateResource(RateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }

    @GET
    @Path("{code}/{date}")
    public Rate getRate(@PathParam("code") String code, @PathParam("date") String dateStr) {
        logger.debug(() -> "code = [" + code + "], dateStr = [" + dateStr + "]");
        return getRate(code, parseDate(dateStr));
    }

    @GET
    @Path("{code}")
    public Rate getRate(@PathParam("code") String code) {
        logger.debug(() -> "code = [" + code + "]");
        return getRate(code, now().plusDays(1));
    }

    private Rate getRate(String code, LocalDate date) {
        validateCode(code);
        return rateProvider.getRate(code.toUpperCase(), date);
    }

    private void validateCode(String code) {
        if (!code.matches("^[A-Za-z]{3}$")) {
            throw new WrongArgumentException("Incorrect currency code [" + code + "]. It shall consist of 3 characters. For example: USD, EUR");
        }
    }

    private LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            logger.error(() -> "Wrong date format : " + dateStr, e);
            throw new WrongArgumentException(e.getMessage() + ". Date format: yyyy-MM-dd");
        }
    }
}
