package br.edu.fateczl.contratacaoDocente.controller;

import br.edu.fateczl.contratacaoDocente.model.Professor;

import model.Lista;

public class Ordenar {
	
	public Ordenar() {
		
		super();
		
	}
	
	public Lista<Professor> quickSort(Lista<Professor> lista, int inicio, int fim) {

		if (fim > inicio) {

			int pivoFixo = dividir(lista, inicio, fim);
			quickSort(lista, inicio, pivoFixo - 1); // divisão esquerda
			quickSort(lista, pivoFixo + 1, fim); // divisão direita

		}

		return lista;
	}

	private int dividir(Lista<Professor> lista, int inicio, int fim) {

		Professor pivo = lista.get(inicio);
		int ponteiroEsquerda = inicio + 1;
		int ponteiroDireita = fim;

		// enquanto os ponteiros não se cruzarem

		while (ponteiroEsquerda <= ponteiroDireita) {

			while (ponteiroEsquerda <= ponteiroDireita && lista.get(ponteiroEsquerda).QtdPontos <= pivo.QtdPontos) { // 
				ponteiroEsquerda++;
			}
			while (ponteiroDireita >= ponteiroEsquerda && lista.get(ponteiroDireita).QtdPontos > pivo.QtdPontos) {
				ponteiroDireita--;
			}
			if (ponteiroEsquerda < ponteiroDireita) {
				// os valores trocam de lugar
				trocar(lista, ponteiroEsquerda, ponteiroDireita);

				ponteiroEsquerda++;
				ponteiroDireita--;

			}

		}

		trocar(lista, inicio, ponteiroDireita);
		return ponteiroDireita; // vai retornar a posição que já está fixada e ordenada e a partir desse valor
								// consigo subdividir em vetores de esquerda e direita

	}

	// Criando um método de troca simples;

	private void trocar(Lista<Professor> lista, int i, int j) {

		Professor aux = lista.get(i);

		lista.set(i,lista.get(j));

		lista.set(j, aux);

	}


}
