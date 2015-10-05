package currencyrate.client;

import currencyrate.rest.ServerInternalException;

public class ParsingException extends ServerInternalException {
    public ParsingException(String msg) {
        super(msg);
    }

    public ParsingException(Throwable cause, String msg) {
        super(cause, msg);
    }
}
