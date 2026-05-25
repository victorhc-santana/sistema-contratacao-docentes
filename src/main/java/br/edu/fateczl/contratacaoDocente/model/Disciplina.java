package br.edu.fateczl.contratacaoDocente.model;

public class Disciplina {

	public String codigoDisciplina;
	public String nomeDisciplina;
	public String diaSemana;
	public String horaInicial;
	public String horasDiarias;
	public String codCurso;
	

	public String toString() {

		return codigoDisciplina + ";" + nomeDisciplina + ";" + diaSemana + ";" + horaInicial + ";" + horasDiarias + ";"
				+ codCurso;

	}

}
