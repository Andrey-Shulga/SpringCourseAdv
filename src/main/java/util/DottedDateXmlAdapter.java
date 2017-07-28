package util;

import com.migesok.jaxb.adapter.javatime.TemporalAccessorXmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DottedDateXmlAdapter extends TemporalAccessorXmlAdapter<LocalDate> {
    public DottedDateXmlAdapter() {
        super(DateTimeFormatter.ofPattern("dd.MM.yyyy"), LocalDate::from);
    }
}
