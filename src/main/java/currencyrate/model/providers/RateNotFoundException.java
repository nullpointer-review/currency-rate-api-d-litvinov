package currencyrate.model.providers;

public class RateNotFoundException extends RuntimeException {
    public RateNotFoundException(String msg) {
        super(msg);
    }
}
