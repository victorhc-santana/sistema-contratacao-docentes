package br.edu.fateczl.contratacaoDocente.controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.contratacaoDocente.model.Professor;
import br.edu.fateczl.fila.Fila;
import model.Lista;

public class professorControl implements ActionListener {

	// Pegar os elementos da tela para extrair o conteúdo deles

	private JTextField tfProfessorCpf;
	private JTextField tfProfessorNome;
	private JTextField tfProfessorArea;
	private JTextField tfProfessorPontos;
	private JTextArea taProfessor;

	public professorControl(JTextField tfProfessorCpf, JTextField tfProfessorNome, JTextField tfProfessorArea,
			JTextField tfProfessorPontos, JTextArea taProfessor) {

		this.tfProfessorCpf = tfProfessorCpf;
		this.tfProfessorNome = tfProfessorNome;
		this.tfProfessorArea = tfProfessorArea;
		this.tfProfessorPontos = tfProfessorPontos;
		this.taProfessor = taProfessor;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();

		if (cmd.equals("Inserir")) {
			try {
				cadastro();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Consultar")) {
			try {
				busca();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Atualizar")) {
			try {
				atualizar();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Remover")) {
			try {
				remover();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	// COMENTARIO PARA CORRECAO ==> NESSE CASO atualizarProf ESTA ESPERANDO UM
	// OBJETO DO TIPO PROFESSOR, MAS ESTOU PASSANDO UMA STRING
	private void atualizar() throws Exception {

		Professor professor = new Professor();
		professor.cpf = tfProfessorCpf.getText();
		professor.nome = tfProfessorNome.getText();
		professor.area = tfProfessorArea.getText();
		professor.QtdPontos = tfProfessorPontos.getText();

		atualizarProf(professor);
		tfProfessorCpf.setText("");
		tfProfessorNome.setText("");

	}

	// Fazer utilizando lista encadeada
	private void atualizarProf(Professor professor) throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "professor.csv");

		Lista<Professor> lista = new Lista<>();

		// Faz a leitura do arquivo professor.csv

		if (arq.exists() && arq.isFile()) {

			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {

				String[] vetLinha = linha.split(";");

				Professor prof = new Professor();

				// vai popular o objeto prof com as características

				prof.cpf = vetLinha[0];
				prof.nome = vetLinha[1];
				prof.area = vetLinha[2];
				prof.QtdPontos = vetLinha[3];

				lista.addLast(prof);

				// Incremento do While! Importante!
				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

		}

		// Parâmetro de busca para atualizar o Professor seria o CPF.. Logo busca na
		// lista pelo CPF do Prof

		for (int i = 0; i < lista.size(); i++) {

			// pega o professor do indice i e salva em um objeto

			Professor prof = new Professor();
			prof = lista.get(i);

			// Faz a valição para ver se os dois CPF são iguais
			if (prof.cpf.equals(professor.cpf)) {

				prof.nome = professor.nome;
				prof.area = professor.area;
				prof.QtdPontos = professor.QtdPontos;

				// O CPF não sofre atualização. Caso for necessária atualização, implementar no
				// código
				// Preciso remover o professor com os dados antigos da lista
				lista.remove(i);
				// Inserindo o professor atualizado na posição da lista
				lista.add(prof, i);

				// Força o fim do laço
				break;
			}
		}

		// Reescrever o que foi atualizado no arquivo Professor.csv

		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);

		for (int i = 0; i < lista.size(); i++) {

			Professor prof = new Professor();

			prof = lista.get(i);

			// Chama a função toString para reescrever o professor no arquivo
			pw.println(prof.toString());

		}

		pw.flush();
		pw.close();
		fw.close();

	}

	private void remover() throws Exception {

		Professor professor = new Professor();
		professor.cpf = tfProfessorCpf.getText();
		professor.nome = tfProfessorNome.getText();
		professor.area = tfProfessorArea.getText();
		professor.QtdPontos = tfProfessorPontos.getText();

		removerProf(professor);
		tfProfessorCpf.setText("");
		tfProfessorNome.setText("");

	}

	// O método vai receber o CPF do professor
	private void removerProf(Professor professor) throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "professor.csv");

		// Adicionar o arquivo correto na lib para Listas
		Lista<Professor> lista = new Lista<>();

		// Captura o CPF inserido pelo usuário e faz um busca em professor.csv para
		// remover
		// Popular a lista com os professores a partir do arquivo professor.csv
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {
				// Divide a String por ; e insere no vetor vetLinha
				String[] vetLinha = linha.split(";");

				Professor prof = new Professor();

				// Popular a Lista com cada objeto lido do arquivo

				prof.cpf = vetLinha[0];
				prof.nome = vetLinha[1];
				prof.area = vetLinha[2];
				prof.QtdPontos = vetLinha[3];

				// Insere o objeto prof na Lista criada
				lista.addLast(prof);

				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

		}

		// Procurando o CPF do professor presente na lista e removendo os dados do
		// professor

		for (int i = 0; i < lista.size(); i++) {

			Professor prof = new Professor();

			// Vai pegar as informações do professor na Lista na posição i
			prof = lista.get(i);

			// Vai fazer a comparação com o CPF que do professor que foi mandado como
			// parâmetro

			if (prof.cpf.equals(professor.cpf)) {

				// Vai remover o objeto professor na posição i
				lista.remove(i);
				// Encerra o laço for apos ter achado o CPF a ser removido
				break;
			}

		}
		// Necessário reescrever o arquivo com todos os professores que sobraram na
		// lista com o CPF removido

		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);

		for (int i = 0; i < lista.size(); i++) {

			Professor prof = new Professor();

			prof = lista.get(i);

			// Chama a função toString para reescrever o professor no arquivo
			pw.println(prof.toString());

		}

		pw.flush();
		pw.close();
		fw.close();

	}

	private void cadastro() throws IOException {
		Professor professor = new Professor();
		professor.cpf = tfProfessorCpf.getText();
		professor.nome = tfProfessorNome.getText();
		professor.area = tfProfessorArea.getText();
		professor.QtdPontos = tfProfessorPontos.getText();

		cadastraProfessor(professor.toString());
		tfProfessorCpf.setText("");
		tfProfessorNome.setText("");

	}

	private void cadastraProfessor(String csvProfessor) throws IOException {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File dir = new File(path);

		if (!dir.exists()) {

			dir.mkdir();

		}
		File arq = new File(path, "professor.csv");
		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}

		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvProfessor + "\r\n");
		pw.flush();
		pw.close();
		fw.close();

	}

	// buscar o professor por CPF e mostre no Text Area o resultado da busca

	private void busca() throws IOException {
		Professor professor = new Professor();
		professor.cpf = tfProfessorCpf.getText();

		professor = buscaProfessor(professor);
		if (professor.nome != null) {
			taProfessor.setText("CPF: " + professor.cpf + " - Nome: " + professor.nome);
		} else {
			taProfessor.setText("Professor não encontrado");
		}

	}

	// A parte de busca deve ser feito por meio de uma estrutura Fila
	// Vai ler o arquivo e colocar cada objeto do tipo professor em uma Fila
	// Ler cada posição da Fila até achar o CPF correspondente. Caso ache, retorna o
	// objeto do tipo professor. Caso contrário, retorna "Professor não cadastrado".

	private Professor buscaProfessor(Professor professor) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "professor.csv");

		Fila<Professor> fila = new Fila<>(); // Criando uma fila com objeto professor

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			// lendo o arquivo para popular a fila;
			while (linha != null) {
				// Divide a String por ; e insere no vetor vetLinha
				String[] vetLinha = linha.split(";");

				Professor prof = new Professor();

				// Popular a Fila com cada objeto lido do arquivo

				prof.cpf = vetLinha[0];
				prof.nome = vetLinha[1];
				prof.area = vetLinha[2];
				prof.QtdPontos = vetLinha[3];

				// Insere o objeto prof na Fila criada
				fila.insert(prof);

				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

			int tamanhoFila = fila.size();

			// Vai percorrer a fila até achar o CPF correspondente e retornar o objeto
			// professor

			for (int i = 0; i < tamanhoFila; i++) {

				Professor prof = new Professor();

				prof = fila.remove();

				if (prof.cpf.equals(professor.cpf)) {
					professor = prof;
				}

				fila.insert(prof);

			}
		}

		return professor;
	}

}
