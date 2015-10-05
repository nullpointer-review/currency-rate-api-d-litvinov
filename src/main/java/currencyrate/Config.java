package currencyrate;

import currencyrate.model.providers.CbrRatesProvider;
import currencyrate.model.providers.RateProvider;
import currencyrate.rest.CurrencyRateResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CurrencyRateResource currencyRateResource() {
        return new CurrencyRateResource(rateProvider());
    }

    @Bean
    public RateProvider rateProvider() {
        return new CbrRatesProvider();
    }

}
