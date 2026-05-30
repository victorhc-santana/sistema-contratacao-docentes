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
import controller.QuickSort;
import model.Lista;

public class InscritosController implements ActionListener {

	// Ver somente se clicando no botao vai mostrar todas as inscrições ou procurar
	// por algum atributo
	private JTextField tfCodigoDisciplinaConsulta;
	private JTextArea taConsulta;

	public InscritosController(JTextField tfCodigoDisciplinaConsulta, JTextArea taConsulta) {
		super();
		this.tfCodigoDisciplinaConsulta = tfCodigoDisciplinaConsulta;
		this.taConsulta = taConsulta;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();

		if (cmd.equals("Consultar")) {
			try {
				busca();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	private void busca() throws Exception {
		
		Inscricao inscricao = new Inscricao();
		
		inscricao.codDisciplina = tfCodigoDisciplinaConsulta.getText();

		consultarProf(inscricao);
		
	}

	// Criando uma lista com os dados vindos diretamente do arquivo inscricoes.csv.
	// Contendo codDisciplina, codProcesso, cpfProfessor
	private void consultarProf(Inscricao inscricao) throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "inscricoes.csv");

		Lista<Inscricao> lista = new Lista<>();

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

				inscri.cpfProfessor = vetLinha[0];
				inscri.codDisciplina = vetLinha[1];
				inscri.codProcesso = vetLinha[2];

				lista.addLast(inscri);

				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

		}

		// Popula a fila com os professores presentes no arquivo inscrição
		// Talvez criar uma outra classe chamada inscritos
		Lista<Professor> listaprof = populaProfessor(lista);

		int[] vetPontos = pegarPontos(listaprof);

		vetPontos = ordenarPontos(vetPontos);

		imprimeVetor(vetPontos);

	}

	// Imprimir o vetor de pontos no text area para ver se está funcionando
	private void imprimeVetor(int[] vetPontos) {
		
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < vetPontos.length; i++) {

			sb.append(vetPontos[i]).append("\n");
			//taConsulta.setText(Integer.toString(vetPontos[i]));

		}
		
		taConsulta.setText(sb.toString());
		
	}

	// Falta implementar o método!! Coloquei uma biblioteca para teste
	// Ordena o vetPontos
	private int[] ordenarPontos(int[] vetPontos) {

		QuickSort q1 = new QuickSort();

		vetPontos = q1.quickSort(vetPontos, 0, vetPontos.length - 1);

		return vetPontos;

	}

	// Método para pegar a quantidade de pontos de cada professor inscrito e salvar
	// em um vetor para ordenação convertendo a String pontuação para inteiro

	private int[] pegarPontos(Lista<Professor> listaprof) throws Exception {

		int tamanho = listaprof.size();
		int[] vetPontos = new int[tamanho];

		for (int i = 0; i < tamanho; i++) {

			vetPontos[i] = Integer.parseInt(listaprof.get(i).QtdPontos);

		}

		return vetPontos;

	}

	// Método para buscar o professor usando o cpf presente no arquivo
	// inscricoes.csv
	// Varrer para pegar os dados dos professores: nome e pontuação
	private Lista<Professor> populaProfessor(Lista<Inscricao> lista) throws Exception {

		Lista<Professor> listaprof = new Lista<>();

		int tamanho = lista.size();

		for (int i = 0; i < tamanho; i++) {

			Professor professor = buscaProfessor(lista.get(i).cpfProfessor);

			listaprof.add(professor, i); // Inserindo o professor na fila

		}

		return listaprof;

	}

	// Método que le o arquivo professor.csv e retorna o professor com o cpf
	// presente em inscrições
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

				if (prof.cpf.equals(cpfProfessor)) {

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

		return null; // Verificar se é para retornar null

	}
}
