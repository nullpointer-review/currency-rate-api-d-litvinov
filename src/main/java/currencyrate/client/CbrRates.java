package currencyrate.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class CbrRates {
    @XmlElement(name = "Valute")
    private List<CbrRate> cbrRateList;

    public List<CbrRate> getCbrRates() {
        return cbrRateList;
    }
}
