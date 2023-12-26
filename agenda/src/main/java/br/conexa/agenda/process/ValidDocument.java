package br.conexa.agenda.process;

import br.conexa.agenda.model.Doctor;
import br.conexa.agenda.model.EntityModel;

public class ValidDocument implements ValidationProcess {
    @Override
    public void process(EntityModel entityModel) {
        Doctor doctor = (Doctor) entityModel;
        if(!this.validCpf(doctor.getCpf())) {
            throw new IllegalArgumentException("CPF inválido " + doctor.getCpf());
        }
    }
    //https://homepages.dcc.ufmg.br/~rodolfo/aedsi-2-10/regrasDigitosVerificadoresCPF.html
    private Boolean validCpf(String cpf) {
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

        return this.validaUltimoDigito(numero);
    }

    private Boolean validaUltimoDigito(Integer[] numero) {
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