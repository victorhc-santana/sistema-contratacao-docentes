package br.edu.fateczl.contratacaoDocente.estrutura;

import br.edu.fateczl.contratacaoDocente.model.Professor;

public class Ordenacao {

    // Bubble Sort — ordena professores por pontuação
    public Professor[] ordenarPorPontos(Professor[] professores) {
        
        int n = professores.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                
                int pontoAtual = Integer.parseInt(professores[j].QtdPontos);
                int proximo = Integer.parseInt(professores[j + 1].QtdPontos);
                
                if (pontoAtual > proximo) {
                    // troca
                    Professor temp = professores[j];
                    professores[j] = professores[j + 1];
                    professores[j + 1] = temp;
                }
            }
        }
        return professores;
    }
}