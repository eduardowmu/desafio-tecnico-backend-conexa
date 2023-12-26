package br.conexa.agenda.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatUtils {
    public static LocalDate toLocalDate(String data) {
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String withoutFormat(String info) {
        return info.replaceAll("[^0-9]", "");
    }
}