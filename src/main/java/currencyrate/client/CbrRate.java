package currencyrate.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Valute")
public class CbrRate {
    @XmlElement(name = "CharCode")
    private String code;

    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(FloatAdapter.class)
    private Float rate;

    public CbrRate() {
    }

    public String getCode() {
        return code;
    }

    public Float getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "CbrRate{" +
                "code='" + code + '\'' +
                ", rate=" + rate +
                '}';
    }
}
