package currencyrate.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

public class WrongArgumentException extends WebApplicationException {
    public WrongArgumentException(String msg) {
        super(Response.status(BAD_REQUEST).entity(msg).type(TEXT_PLAIN_TYPE).build());
    }
}
