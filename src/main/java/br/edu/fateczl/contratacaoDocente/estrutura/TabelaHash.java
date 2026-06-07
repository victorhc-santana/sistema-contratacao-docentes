package br.edu.fateczl.contratacaoDocente.estrutura;

public class TabelaHash {
	
	private int[] chaves;
    private String[] tabela;
    private int tamanho;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.chaves = new int[tamanho];
        this.tabela = new String[tamanho];
     // -1 indica posição vazia
        for (int i = 0; i < tamanho; i++) {
            chaves[i] = -1;
        }
    }

    private int hash(int codigo) {
        return codigo % tamanho;
    }

    public void inserir(int codigoProcesso, String nomeDisciplina) {
        int posicao = hash(codigoProcesso);
        int tentativas = 0;
        
        
     // procura a próxima posição livre
        while (chaves[posicao] != -1 && chaves[posicao] != codigoProcesso) {
            posicao = (posicao + 1) % tamanho;
            tentativas += 1;
            
            if (tentativas == tamanho) {
            	System.out.println("Tabela cheia! Não foi possível inserir: " + codigoProcesso);
                return;
            }
        }
        chaves[posicao] = codigoProcesso;
        tabela[posicao] = nomeDisciplina;
    }

    public String buscar(int codigoProcesso) {
        int posicao = hash(codigoProcesso);
        
        while (chaves[posicao] != -1) {
            if (chaves[posicao] == codigoProcesso) {
                return tabela[posicao];
            }
            posicao = (posicao + 1) % tamanho;
        }
        
        return tabela[posicao];
    }

    public void remover(int codigoProcesso) {
        int posicao = hash(codigoProcesso);
        
        while (chaves[posicao] != -1) {
            if (chaves[posicao] == codigoProcesso) {
                chaves[posicao] = -1;
                tabela[posicao] = null;
                return;
            }
            posicao = (posicao + 1) % tamanho;
        }
        
        tabela[posicao] = null;
    }

    public String listar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            if (chaves[i] != -1) {
                sb.append("Posição ").append(i)
                  .append(" | Código: ").append(chaves[i])
                  .append(" | Disciplina: ").append(tabela[i])
                  .append("\n");
            }
        }
        return sb.toString();
    }
}