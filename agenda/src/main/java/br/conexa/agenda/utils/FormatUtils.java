package br.conexa.agenda.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatUtils {
    public static LocalDate toDate(String data) {
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static LocalDateTime toDateTime(String data) {
        return LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String dateToText(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(dateTime);
    }

    public static String toWithoutFormat(String valor) {
        return valor.replaceAll("[^0-9]", "");
    }

    // https://homepages.dcc.ufmg.br/~rodolfo/aedsi-2-10/regrasDigitosVerificadoresCPF.html
    public static Boolean validCpf(String cpf) {
        if(cpf.length() != 11) return Boolean.FALSE;

        Integer[] numero = new Integer[11];
        for(int i = 0; i < cpf.length(); i++) {
            numero[i] = Integer.parseInt(String.valueOf(cpf.charAt(i)));
        }

        int  soma = 0;
        for(int i = 0; i < (numero.length - 2); i++) {
            soma += numero[i] * (10 - i);
        }

        //dezena do digito verificador
        int dezenaDv = soma % 11;
        dezenaDv = dezenaDv <= 1 ? 0 : (11 - dezenaDv);
        if(dezenaDv != numero[9]) {
            return Boolean.FALSE;
        }

        return validaUltimoDigito(numero);
    }

    private static Boolean validaUltimoDigito(Integer[] numero) {
        int soma = 0;
        for(int i = 0; i < (numero.length - 1); i++) {
            soma += numero[i] * (11 - i);
        }

        int unidadeDv = soma % 11;
        unidadeDv = unidadeDv <= 1 ? 0 : (11 - unidadeDv);
        if(unidadeDv != numero[10]) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}