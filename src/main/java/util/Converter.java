package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static LocalDateTime getStrToLocalDateTime(String strDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(strDate, formatter);
    }

    public static List<Integer> parseSeatsList(String seats) {

        String regexCommaPattern = "\\s*,\\s*";
        List<String> strSeats = Arrays.asList(seats.split(regexCommaPattern));
        return strSeats.stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}
