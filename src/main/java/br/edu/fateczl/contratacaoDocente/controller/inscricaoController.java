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

import br.edu.fateczl.contratacaoDocente.model.Inscricao;
import br.edu.fateczl.contratacaoDocente.model.Professor;
import br.edu.fateczl.fila.Fila;
import model.Lista;

public class inscricaoController implements ActionListener {

	private JTextField tfcpfInscricao;
	private JTextField tfcodigoDisciplina;
	private JTextField tfcodigoProcesso;
	private JTextArea tainscricao;

	public inscricaoController(JTextField tfcpfInscricao, JTextField tfcodigoDisciplina, JTextField tfcodigoProcesso,
			JTextArea tainscricao) {
		super();
		this.tfcpfInscricao = tfcpfInscricao;
		this.tfcodigoDisciplina = tfcodigoDisciplina;
		this.tfcodigoProcesso = tfcodigoProcesso;
		this.tainscricao = tainscricao;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();

		if (cmd.equals("Inserir")) {
			try {
				cadastro();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Consultar")) {
			try {
				busca();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Atualizar")) {
			try {
				atualizar();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Remover")) {
			try {
				remover();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	private void cadastro() throws Exception {

		Inscricao inscricao = new Inscricao();
		inscricao.codDisciplina = tfcodigoDisciplina.getText();
		inscricao.codProcesso = tfcodigoProcesso.getText();
		inscricao.cpfProfessor = tfcpfInscricao.getText();

		// Só pode se inscrever se o professor estiver presente no professor.csv
		// Preciso verificar se o professor está presente no arquivo professor.csv antes
		// de iniciar o cadastro
		// tenho que ler o arquivo para ver se o professor está la

		// Leitura do arquivo

		if (lerCsvProfessor(inscricao.cpfProfessor)) {

			cadastraInscricao(inscricao.toString());
			tfcodigoDisciplina.setText("");
			tfcodigoProcesso.setText("");
			tfcpfInscricao.setText("");

		} else {
			
			tainscricao.setText("Professor não está cadastrado. Cadastre o professor antes de iniciar uma inscrição.");
		}

	}

	//Método para validar se o CPF do professor está presente no arquivo professor.csv
	//Só permite a inscrição se o cpf estiver presente
	private boolean lerCsvProfessor(String cpf) {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "professor.csv");

		try {
			if (arq.exists() && arq.isFile()) {
				
				FileInputStream fis = new FileInputStream(arq);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader buffer = new BufferedReader(isr);
				String linha = buffer.readLine();

				while (linha != null) {
					
					String[] vetLinha = linha.split(";");

					Professor prof = new Professor();

					prof.cpf = vetLinha[0];
					prof.nome = vetLinha[1];
					prof.area = vetLinha[2];
					prof.QtdPontos = vetLinha[3];

					if (prof.cpf.equals(cpf)) {
						
						buffer.close();
						isr.close();
						fis.close();
						
						return true;
					}
					
					linha = buffer.readLine();
				}

				buffer.close();
				isr.close();
				fis.close();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return false;

	}

	private void cadastraInscricao(String csvInscricao) throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File dir = new File(path);

		if (!dir.exists()) {

			dir.mkdir();

		}
		File arq = new File(path, "inscricoes.csv");
		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}

		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		tainscricao.setText("Professor cadastrado com sucesso!!!");
		pw.write(csvInscricao + "\r\n");
		pw.flush();
		pw.close();
		fw.close();

	}

	private void busca() throws Exception {

		Inscricao inscricao = new Inscricao();
		inscricao.codProcesso = tfcodigoProcesso.getText();

		inscricao = buscaInscricao(inscricao);
		if (inscricao.codProcesso != null) {
			tainscricao.setText("Código Processo " + inscricao.codProcesso + " - CPF Professor: "
					+ inscricao.cpfProfessor + " - Codigo Disciplina " + inscricao.codDisciplina);
		} else {
			tainscricao.setText("Inscrição não encontrada!!!");
		}

		tfcodigoDisciplina.setText("");
		tfcodigoProcesso.setText("");
		tfcpfInscricao.setText("");

	}

	private Inscricao buscaInscricao(Inscricao inscricao) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "inscricoes.csv");

		Fila<Inscricao> fila = new Fila<>(); // Criando uma fila com objeto inscricao

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			// lendo o arquivo para popular a fila;
			while (linha != null) {
				// Divide a String por ; e insere no vetor vetLinha
				String[] vetLinha = linha.split(";");

				Inscricao inscri = new Inscricao();

				// Popular a Fila com cada objeto lido do arquivo

				inscri.codProcesso = vetLinha[0];
				inscri.cpfProfessor = vetLinha[1];
				inscri.codDisciplina = vetLinha[2];

				// Insere o objeto prof na Fila criada
				fila.insert(inscri);

				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

			int tamanhoFila = fila.size();

			// Vai percorrer a fila até achar o código da incrição correspondente e retornar
			// o objeto
			// inscricao

			for (int i = 0; i < tamanhoFila; i++) {

				Inscricao inscri = new Inscricao();

				inscri = fila.remove();

				if (inscri.codProcesso.equals(inscricao.codProcesso)) {
					inscricao = inscri;
				}

				fila.insert(inscri);

			}
		}

		return inscricao;
	}

	private void atualizar() throws Exception {

		Inscricao inscricao = new Inscricao();
		inscricao.codDisciplina = tfcodigoDisciplina.getText();
		inscricao.codProcesso = tfcodigoProcesso.getText();
		inscricao.cpfProfessor = tfcpfInscricao.getText();

		atualizarInscricao(inscricao);
		tfcodigoDisciplina.setText("");
		tfcodigoProcesso.setText("");
		tfcpfInscricao.setText("");

	}

	private void atualizarInscricao(Inscricao inscricao) throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "inscricoes.csv");

		Lista<Inscricao> lista = new Lista<>();

		// Faz a leitura do arquivo inscricoes.csv

		if (arq.exists() && arq.isFile()) {

			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {

				String[] vetLinha = linha.split(";");

				Inscricao inscri = new Inscricao();

				// vai popular o objeto inscri com as características

				inscri.codDisciplina = vetLinha[0];
				inscri.codProcesso = vetLinha[1];
				inscri.cpfProfessor = vetLinha[2];

				lista.addLast(inscri);

				// Incremento do While! Importante!
				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

		}

		// Parâmetro de busca para atualizar Inscricao seria o código do processo Logo
		// busca na
		// lista pelo código do processo

		for (int i = 0; i < lista.size(); i++) {

			// pega a inscricao do indice i e salva em um objeto

			Inscricao inscri = new Inscricao();
			inscri = lista.get(i);

			// Faz a valição para ver se os dois códigos do processo são iguais
			if (inscri.codProcesso.equals(inscricao.codProcesso)) {

				inscri.codDisciplina = inscricao.codDisciplina;
				inscri.cpfProfessor = inscricao.cpfProfessor;
				inscri.codProcesso = inscricao.codProcesso;
				// O código não sofre atualização. Caso for necessária atualização, implementar
				// no
				// código
				// Preciso remover o professor com os dados antigos da lista
				lista.remove(i);
				// Inserindo o professor atualizado na posição da lista
				lista.add(inscri, i);

				// Força o fim do laço
				break;
			}
		}

		// Reescrever o que foi atualizado no arquivo Professor.csv

		FileWriter fw = new FileWriter(arq, false);
		PrintWriter pw = new PrintWriter(fw);

		for (int i = 0; i < lista.size(); i++) {

			Inscricao inscri = new Inscricao();

			inscri = lista.get(i);

			// Chama a função toString para reescrever o professor no arquivo
			pw.println(inscri.toString());

		}

		tainscricao.setText("Inscrição atualizada com sucesso!!!");

		pw.flush();
		pw.close();
		fw.close();

	}

	private void remover() throws Exception {

		Inscricao inscricao = new Inscricao();
		inscricao.codDisciplina = tfcodigoDisciplina.getText();
		inscricao.codProcesso = tfcodigoProcesso.getText();
		inscricao.cpfProfessor = tfcpfInscricao.getText();

		removerInscricao(inscricao);
		tfcodigoDisciplina.setText("");
		tfcodigoProcesso.setText("");
		tfcpfInscricao.setText("");

	}

	private void removerInscricao(Inscricao inscricao) throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "inscricoes.csv");

		// Adicionar o arquivo correto na lib para Listas
		Lista<Inscricao> lista = new Lista<>();

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

				Inscricao inscri = new Inscricao();

				// Popular a Lista com cada objeto lido do arquivo

				inscri.codDisciplina = vetLinha[0];
				inscri.codProcesso = vetLinha[1];
				inscri.cpfProfessor = vetLinha[2];

				// Insere o objeto prof na Lista criada
				lista.addLast(inscri);

				linha = buffer.readLine();

			}

			tainscricao.setText("Inscrição removida com sucesso!!!");

			buffer.close();
			isr.close();
			fis.close();

		}

		// Procurando o CPF do professor presente na lista e removendo os dados do
		// professor

		for (int i = 0; i < lista.size(); i++) {

			Inscricao inscri = new Inscricao();

			// Vai pegar as informações do professor na Lista na posição i
			inscri = lista.get(i);

			// Vai fazer a comparação com o CPF que do professor que foi mandado como
			// parâmetro

			if (inscri.codProcesso.equals(inscricao.codProcesso)) {

				// Vai remover o objeto professor na posição i
				lista.remove(i);
				// Encerra o laço for apos ter achado o CPF a ser removido
				break;
			}

		}
		// Necessário reescrever o arquivo com todos os professores que sobraram na
		// lista com o CPF removido

		FileWriter fw = new FileWriter(arq, false);
		PrintWriter pw = new PrintWriter(fw);

		for (int i = 0; i < lista.size(); i++) {

			Inscricao inscri = new Inscricao();

			inscri = lista.get(i);

			// Chama a função toString para reescrever o professor no arquivo
			pw.println(inscri.toString());

		}

		pw.flush();
		pw.close();
		fw.close();

	}

}
