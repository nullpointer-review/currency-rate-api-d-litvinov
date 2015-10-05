package currencyrate.client;

import currencyrate.utils.Logger;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;

public class ResponseParser {
    private static final Logger logger = Logger.getLogger(ResponseParser.class);

    private ResponseParser() {
    }

    public static List<CbrRate> parse(String responseBody) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CbrRates.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            InputSource is = new InputSource(new StringReader(responseBody));
            CbrRates cbrRates = (CbrRates) jaxbUnmarshaller.unmarshal(is);
            logger.trace(() -> "received rates = " + cbrRates);

            List<CbrRate> result = cbrRates.getCbrRates();
            return result != null ? result : Collections.emptyList();
        } catch (JAXBException e) {
            logger.error(() -> "Can't parse response: " + responseBody);
            throw new ParsingException(e, "Can't parse response");
        }
    }
}
