package br.edu.fateczl.contratacaoDocente.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.contratacaoDocente.model.Inscricao;
import br.edu.fateczl.contratacaoDocente.model.Professor;
import br.edu.fateczl.fila.Fila;
import model.Lista;

public class InscritosController implements ActionListener {

	// Ver somente se clicando no botao vai mostrar todas as inscrições ou procurar
	// por algum atributo
	private JTextField tfCodigoDisciplinaConsulta;
	private JTextArea taConsulta;

	public InscritosController(JTextField tfCodigoDisciplinaConsulta, JTextArea taConsulta) {
		super();
		this.tfCodigoDisciplinaConsulta = tfCodigoDisciplinaConsulta;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();

		if (cmd.equals("Consultar")) {
			try {
				consultar();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	// Criando uma lista com os dados vindos diretamente do arquivo inscricoes.csv.
	// Contendo codDisciplina, codProcesso, cpfProfessor
	private void consultar() throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "inscricoes.csv");

		Lista<Inscricao> lista = new Lista<>();
		Fila<Professor> fila = new Fila<>(); // Fila de professor armazenada

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {
				// Divide a String por ; e insere no vetor vetLinha
				String[] vetLinha = linha.split(";");

				Inscricao inscri = new Inscricao();

				// Popular a Lista com cada objeto lido do arquivo

				inscri.codDisciplina = vetLinha[0];
				inscri.codProcesso = vetLinha[1];
				inscri.cpfProfessor = vetLinha[2];

				lista.addLast(inscri);

				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

		}

		// ordena(lista);
	}

	// Método para buscar o professor usando o cpf presente no arquivo
	// inscricoes.csv
	// Varrer para pegar os dados dos professores: nome e pontuação
	private Fila<Professor> populaProfessor(Lista<Inscricao> lista) throws Exception {

		Fila<Professor> fila = new Fila<>();

		int tamanho = lista.size();

		for (int i = 0; i < tamanho; i++) {

			Professor professor = buscaProfessor(lista.get(i).cpfProfessor);

			fila.insert(professor); // Inserindo o professor na fila

		}
		
		return fila;

	}

	private Professor buscaProfessor(String cpfProfessor) throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "professor.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {
				// Divide a String por ; e insere no vetor vetLinha
				String[] vetLinha = linha.split(";");

				Professor prof = new Professor();

				prof.cpf = vetLinha[0];
				prof.nome = vetLinha[1];
				prof.area = vetLinha[2];
				prof.QtdPontos = vetLinha[3];
				
				if(prof.cpf.equals(cpfProfessor)) {
					
					buffer.close();
					isr.close();
					fis.close();
					
					return prof;
					
				}

				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

		}
		
		return null; //Verificar se é para retornar null
		
	}
}
