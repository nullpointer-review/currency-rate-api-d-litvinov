package currencyrate.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

public class ServerInternalException extends WebApplicationException {
    public ServerInternalException(Throwable cause, String msg) {
        super(cause, Response.status(INTERNAL_SERVER_ERROR).entity(msg).type(TEXT_PLAIN_TYPE).build());
    }

    public ServerInternalException(String msg) {
        super(Response.status(INTERNAL_SERVER_ERROR).entity(msg).type(TEXT_PLAIN_TYPE).build());
    }

    public ServerInternalException(Throwable cause) {
        super(cause, INTERNAL_SERVER_ERROR);
    }
}
