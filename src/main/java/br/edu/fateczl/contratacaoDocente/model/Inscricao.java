package br.edu.fateczl.contratacaoDocente.model;

public class Inscricao {
	
	public String cpfProfessor;
	public String codDisciplina;
	public String codProcesso;
	
	public String toString() {

		return cpfProfessor + ";" + codDisciplina + ";" + codProcesso;

	}

}
