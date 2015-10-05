package currencyrate.client;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FloatAdapter extends XmlAdapter<String, Float> {
    private static final DecimalFormat formatter = new DecimalFormat();

    static {
        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
        decimalFormatSymbols.setDecimalSeparator(',');
        formatter.setDecimalFormatSymbols(decimalFormatSymbols);
    }

    @Override
    public Float unmarshal(String str) throws Exception {
        return formatter.parse(str).floatValue();
    }

    @Override
    public String marshal(Float value) throws Exception {
        return formatter.format(value);
    }
}
