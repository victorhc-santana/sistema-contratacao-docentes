package br.edu.fateczl.contratacaoDocente.estrutura;

public class TabelaHash {

    private String[] tabela;
    private int tamanho;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new String[tamanho];
    }

    private int hash(int codigo) {
        return codigo % tamanho;
    }

    public void inserir(int codigoProcesso, String nomeDisciplina) {
        int posicao = hash(codigoProcesso);
        tabela[posicao] = nomeDisciplina;
    }

    public String buscar(int codigoProcesso) {
        int posicao = hash(codigoProcesso);
        return tabela[posicao];
    }

    public void remover(int codigoProcesso) {
        int posicao = hash(codigoProcesso);
        tabela[posicao] = null;
    }

    public String listar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            if (tabela[i] != null) {
                sb.append("Posição ").append(i).append(": ").append(tabela[i]).append("\n");
            }
        }
        return sb.toString();
    }
}