package br.edu.fateczl.contratacaoDocente.util;

public class ValidadorCPF {
    public static boolean formatoValido(String cpf) {
        String cpfLimpo = "";   
        for (int i = 0; i < cpf.length(); i++) {
            char c = cpf.charAt(i);
            if (c != '.' && c != '-') {
                cpfLimpo = cpfLimpo + c;
            }
        }   
        return cpfLimpo != null && cpfLimpo.length() == 11;
    }
}

