package br.conexa.agenda.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatUtils {
    public static LocalDate toDate(String data) {
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String toWithoutFormat(String valor) {
        return valor.replaceAll("[^0-9]", "");
    }
}