package br.edu.fateczl.contratacaoDocente.estrutura;

import br.edu.fateczl.contratacaoDocente.model.Professor;

public class Ordenacao {

    public Professor[] ordenarPorPontos(Professor[] professores) {
        
        for (int i = 1; i < professores.length; i++) {
            Professor atual = professores[i];
            int pontoAtual = Integer.parseInt(atual.QtdPontos);
            int j = i - 1;

            while (j >= 0 && Integer.parseInt(professores[j].QtdPontos) > pontoAtual) {
                professores[j + 1] = professores[j];
                j--;
            }
            professores[j + 1] = atual;
        }
        return professores;
    }
}