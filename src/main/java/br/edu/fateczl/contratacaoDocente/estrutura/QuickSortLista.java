package br.edu.fateczl.contratacaoDocente.estrutura;

import model.Lista;
//import br.edu.fateczl.contratacaoDocente.estrutura.*;
import br.edu.fateczl.contratacaoDocente.model.Professor;


public class QuickSortLista {

	public QuickSortLista() {

		super();

	}

	public Lista<Professor> quickSort(Lista<Professor> lista, int inicio, int fim) throws Exception {

		if (inicio < fim) {

			int pivoFixo = dividir(lista, inicio, fim);
			quickSort(lista, inicio, pivoFixo - 1); // divisão esquerda
			quickSort(lista, pivoFixo + 1, fim); // divisão direita

		}

		return lista;
	}

	private int dividir(Lista<Professor> lista, int inicio, int fim) throws Exception {

	    Professor pivo = lista.get(inicio);

	    int ponteiroEsquerda = inicio + 1;
	    int ponteiroDireita = fim;

	    while (ponteiroEsquerda <= ponteiroDireita) {

	        while (ponteiroEsquerda <= ponteiroDireita
	        		&& ponteiroEsquerda < lista.size()
	                && Integer.parseInt(lista.get(ponteiroEsquerda).QtdPontos)
	                        >= Integer.parseInt(pivo.QtdPontos)) { 
	            ponteiroEsquerda++;
	        }

	        while (ponteiroDireita >= ponteiroEsquerda
	        		&& ponteiroDireita >= 0
	                && Integer.parseInt(lista.get(ponteiroDireita).QtdPontos)
	                        < Integer.parseInt(pivo.QtdPontos)) { 
	            ponteiroDireita--;
	        }

	        if (ponteiroEsquerda < ponteiroDireita) {
	            trocar(lista, ponteiroEsquerda, ponteiroDireita);
	        }
	    }

	    trocar(lista, inicio, ponteiroDireita);

	    return ponteiroDireita;
	}

	// Criando um método de troca simples evitando a perda de referência do professor
	
	private void trocar(Lista<Professor> lista, int i, int j) throws Exception {
	    if (i == j) {
	        return;
	    }

	    Professor p1 = lista.get(i);
	    Professor p2 = lista.get(j);

	    lista.remove(i);
	    lista.add(p2, i);

	    lista.remove(j);
	    lista.add(p1, j);
	}
		
}