package currencyrate.model.providers;

import currencyrate.model.Rate;

import java.time.LocalDate;

public interface RateProvider {
    Rate getRate(String currencyCode, LocalDate date);
}
