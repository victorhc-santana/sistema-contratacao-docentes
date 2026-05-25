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

import br.edu.fateczl.contratacaoDocente.model.Curso;
//import br.edu.fateczl.contratacaoDocente.model.Professor;
import br.edu.fateczl.fila.Fila;
import model.Lista;

public class cursoController implements ActionListener {

	private JTextField tfcodigoCurso;
	private JTextField tfnomeCurso;
	private JTextField tfareaCurso;
	private JTextArea taCurso;

	public cursoController(JTextField tfcodigoCurso, JTextField tfnomeCurso, JTextField tfareaCurso,
			JTextArea taCurso) {
		this.tfcodigoCurso = tfcodigoCurso;
		this.tfnomeCurso = tfnomeCurso;
		this.tfareaCurso = tfareaCurso;
		this.taCurso = taCurso;
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

	private void cadastro() throws IOException {
		Curso curso = new Curso();
		curso.codCurso = tfcodigoCurso.getText();
		curso.nomeCurso = tfnomeCurso.getText();
		curso.areaCurso = tfareaCurso.getText();

		cadastraCurso(curso.toString());
		tfcodigoCurso.setText("");
		tfnomeCurso.setText("");
		tfareaCurso.setText("");

	}

	private void cadastraCurso(String csvCurso) throws IOException {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File dir = new File(path);

		if (!dir.exists()) {

			dir.mkdir();

		}
		File arq = new File(path, "cursos.csv");
		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}

		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvCurso + "\r\n");
		pw.flush();
		pw.close();
		fw.close();

	}

	private void busca() throws IOException {

		Curso curso = new Curso();
		curso.codCurso = tfcodigoCurso.getText();

		curso = buscaCurso(curso);
		if (curso.nomeCurso != null) {
			taCurso.setText("CODIGO CURSO: " + curso.codCurso + " - NOME CURSO: " + curso.nomeCurso);
		} else {
			taCurso.setText("Curso não encontrado!");
		}

	}

	private Curso buscaCurso(Curso curso) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "cursos.csv");

		Fila<Curso> fila = new Fila<>(); // Criando uma fila com objeto curso

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			// lendo o arquivo para popular a fila;
			while (linha != null) {
				// Divide a String por ; e insere no vetor vetLinha
				String[] vetLinha = linha.split(";");

				Curso cursoBusca = new Curso();

				// Popular a Fila com cada objeto lido do arquivo

				cursoBusca.codCurso = vetLinha[0];
				cursoBusca.nomeCurso = vetLinha[1];
				cursoBusca.areaCurso = vetLinha[2];

				// Insere o objeto prof na Fila
				fila.insert(cursoBusca);

				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

			int tamanhoFila = fila.size();

			// Vai percorrer a fila até achar o codigo do curso correspondente e retornar o objeto
			// curso

			for (int i = 0; i < tamanhoFila; i++) {

				Curso cursoBusca = new Curso();

				cursoBusca = fila.remove();

				if (cursoBusca.codCurso.equals(curso.codCurso)) {
					curso = cursoBusca;
				}

				fila.insert(cursoBusca);

			}
		}

		return curso;
	}

	private void atualizar() throws Exception {
		Curso curso = new Curso();
		curso.codCurso = tfcodigoCurso.getText();
		curso.nomeCurso = tfnomeCurso.getText();
		curso.areaCurso = tfareaCurso.getText();

		atualizaCurso(curso);
		tfcodigoCurso.setText("");
		tfnomeCurso.setText("");
		tfareaCurso.setText("");

	}

	private void atualizaCurso(Curso curso) throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "cursos.csv");

		Lista<Curso> lista = new Lista<>();

		// Faz a leitura do arquivo cursos.csv

		if (arq.exists() && arq.isFile()) {

			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {

				String[] vetLinha = linha.split(";");

				Curso cursoAtualiza = new Curso();

				// vai popular o objeto curso com as características

				cursoAtualiza.codCurso = vetLinha[0];
				cursoAtualiza.nomeCurso = vetLinha[1];
				cursoAtualiza.areaCurso = vetLinha[2];

				lista.addLast(cursoAtualiza);

				// Incremento do While! Importante!
				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

		}

		// Parâmetro de busca para atualizar o Curso seria o código curso.. Logo busca
		// na
		// lista pelo código do Curso

		for (int i = 0; i < lista.size(); i++) {

			// pega o curso do indice i e salva em um objeto

			Curso cursoAtualiza = new Curso();
			cursoAtualiza = lista.get(i);

			// Faz a valição para ver se os dois codigos são iguais
			if (cursoAtualiza.codCurso.equals(curso.codCurso)) {

				cursoAtualiza.nomeCurso = curso.nomeCurso;
				cursoAtualiza.areaCurso = curso.areaCurso;

				// O codigo não sofre atualização. Caso for necessária atualização, implementar
				// no
				// código
				// Preciso remover o curso com os dados antigos da lista
				lista.remove(i);
				// Inserindo o curso atualizado na posição da lista
				lista.add(cursoAtualiza, i);

				// Força o fim do laço
				break;
			}
		}

		// Reescrever o que foi atualizado no arquivo curso.csv

		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);

		for (int i = 0; i < lista.size(); i++) {

			Curso cursoAtualiza = new Curso();

			cursoAtualiza = lista.get(i);

			// Chama a função toString para reescrever o curso no arquivo
			pw.println(cursoAtualiza.toString());

		}

		pw.flush();
		pw.close();
		fw.close();
	}

	// VERIFICAR se é para excluir o curso pelo Código somente e adaptar essa parte
	// do código eliminando partes não necessárias
	private void remover() throws Exception {

		Curso curso = new Curso();
		curso.codCurso = tfcodigoCurso.getText();
		curso.nomeCurso = tfnomeCurso.getText();
		curso.areaCurso = tfareaCurso.getText();

		removeCurso(curso);
		tfcodigoCurso.setText("");
		tfnomeCurso.setText("");
		tfareaCurso.setText("");
	}

	private void removeCurso(Curso curso) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "cursos.csv");

		// Adicionar o arquivo correto na lib para Listas
		Lista<Curso> lista = new Lista<>();

		// Captura o Codigo inserido pelo usuário e faz um busca em curso.csv para
		// remover
		// Popular a lista com os cursos a partir do arquivo cursos.csv
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {
				// Divide a String por ; e insere no vetor vetLinha
				String[] vetLinha = linha.split(";");

				Curso cursoRemove = new Curso();

				// Popular a Lista com cada objeto lido do arquivo

				cursoRemove.codCurso = vetLinha[0];
				cursoRemove.nomeCurso = vetLinha[1];
				cursoRemove.areaCurso = vetLinha[2];

				// Insere o objeto curso na Lista criada
				lista.addLast(cursoRemove);

				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

		}

		// Procurando o codigo do curso presente na lista e removendo os dados do
		// curso

		for (int i = 0; i < lista.size(); i++) {

			Curso cursoRemove = new Curso();

			// Vai pegar as informações do curso na Lista na posição i
			cursoRemove = lista.get(i);

			// Vai fazer a comparação com o codigo do curso que foi mandado como
			// parâmetro

			if (cursoRemove.codCurso.equals(curso.codCurso)) {

				// Vai remover o objeto curso na posição i
				lista.remove(i);
				// Encerra o laço for apos ter achado o codigo do curso a ser removido
				break;
			}

		}
		// Necessário reescrever o arquivo com todos os cursos que sobraram na
		// lista sem o cod removido

		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);

		for (int i = 0; i < lista.size(); i++) {

			Curso cursoRemove = new Curso();

			cursoRemove = lista.get(i);

			// Chama a função toString para reescrever o curso no arquivo
			pw.println(cursoRemove.toString());

		}

		pw.flush();
		pw.close();
		fw.close();

	}

}
