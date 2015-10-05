package currencyrate.model.providers;

import currencyrate.model.Rate;

import java.time.LocalDate;
import java.util.List;

public interface BatchRateProvider {
    List<Rate> getRatesOn(LocalDate date);
}
