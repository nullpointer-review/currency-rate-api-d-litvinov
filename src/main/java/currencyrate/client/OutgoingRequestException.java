package currencyrate.client;

import currencyrate.rest.ServerInternalException;

public class OutgoingRequestException extends ServerInternalException {
    public OutgoingRequestException(String msg) {
        super(msg);
    }

    public OutgoingRequestException(Throwable cause, String msg) {
        super(cause, msg);
    }

    public OutgoingRequestException(Throwable cause) {
        super(cause);
    }
}
