package currencyrate.model.providers;

import currencyrate.client.CbrClient;
import currencyrate.client.CbrRate;
import currencyrate.client.ResponseParser;
import currencyrate.model.Rate;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CbrRatesProvider implements BatchRateProvider, RateProvider {
    CbrClient cbrClient = new CbrClient();

    public List<Rate> getRatesOn(LocalDate date) {
        List<CbrRate> cbrRates = ResponseParser.parse(cbrClient.getRatesOn(date));
        return cbrRates.stream()
                .map(r -> new Rate(r.getCode(), r.getRate(), date))
                .collect(toList());
    }

    @Override
    public Rate getRate(String currencyCode, LocalDate date) {
        return getRatesOn(date).stream()
                .filter(rate -> rate.getCode().equals(currencyCode))
                .findFirst()
                .orElseThrow(() -> new RateNotFoundException("Can't found rate for currency [" + currencyCode + "] on date [" + date + "]"));
    }
}
