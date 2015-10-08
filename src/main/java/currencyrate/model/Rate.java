package currencyrate.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

public class Rate {
    private final String code;
    private final Float rate;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date;

    public Rate(String code, Float rate, LocalDate date) {
        this.code = code;
        this.rate = rate;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public Float getRate() {
        return rate;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate1 = (Rate) o;
        return Objects.equals(code, rate1.code) &&
                Objects.equals(rate, rate1.rate) &&
                Objects.equals(date, rate1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, rate, date);
    }

    @Override
    public String toString() {
        return "Rate{" +
                "code='" + code + '\'' +
                ", rate=" + rate +
                ", date=" + date +
                '}';
    }
}
