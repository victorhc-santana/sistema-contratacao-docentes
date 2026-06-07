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

import br.edu.fateczl.contratacaoDocente.model.Disciplina;
import br.edu.fateczl.fila.Fila;
import model.Lista;

public class disciplinaController implements ActionListener {

	private JTextField tfCodigoDisciplina;
	private JTextField tfNomeDisciplina;
	private JTextField tfDiadaSemana;
	private JTextField tfHorarioInicial;
	private JTextField tfHorasDiarias;
	private JTextField tfCodigoCursoDisciplina;
	private JTextArea taDisciplina;

	public disciplinaController(JTextField tfCodigoDisciplina, JTextField tfNomeDisciplina, JTextField tfDiadaSemana,
			JTextField tfHorarioInicial, JTextField tfHorasDiarias, JTextField tfCodigoCursoDisciplina,
			JTextArea taDisciplina) {

		this.tfCodigoDisciplina = tfCodigoDisciplina;
		this.tfNomeDisciplina = tfNomeDisciplina;
		this.tfDiadaSemana = tfDiadaSemana;
		this.tfHorarioInicial = tfHorarioInicial;
		this.tfHorasDiarias = tfHorasDiarias;
		this.tfCodigoCursoDisciplina = tfCodigoCursoDisciplina;
		this.taDisciplina = taDisciplina;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();

		if (cmd.equals("Inserir")) {
			try {
				cadastro();
			} catch (IOException e1) {
				taDisciplina.setText("erro no arquivo :" + e1.getMessage());
			} catch (Exception e1) {
				taDisciplina.setText("erro:" + e1.getMessage());
			}
		}
		if (cmd.equals("Consultar")) {
			try {
				busca();
			} catch (IOException e1) {
				taDisciplina.setText("erro no arquivo :" + e1.getMessage());
			} catch (Exception e1) {
				taDisciplina.setText("erro:" + e1.getMessage());
			}
		}
		if (cmd.equals("Atualizar")) {
			try {
				atualizar();
			} catch (IOException e1) {
				taDisciplina.setText("erro no arquivo :" + e1.getMessage());
			} catch (Exception e1) {
				taDisciplina.setText("erro:" + e1.getMessage());
			}
		}
		if (cmd.equals("Remover")) {
			try {
				remover();
			} catch (IOException e1) {
				taDisciplina.setText("erro no arquivo :" + e1.getMessage());
			} catch (Exception e1) {
				taDisciplina.setText("erro:" + e1.getMessage());
			}
		}
		if (cmd.equals("Listar")) {
			
			try {
				listar();
			} catch (Exception e1) {
				taDisciplina.setText("erro:" + e1.getMessage());
			}
		}
		if (cmd.equals("Limpar")) {
			limpar();
		}

	}

	private void cadastro() throws Exception {

		Disciplina disciplina = new Disciplina();
		disciplina.codCurso = tfCodigoCursoDisciplina.getText();
		disciplina.codigoDisciplina = tfCodigoDisciplina.getText();
		disciplina.diaSemana = tfDiadaSemana.getText();
		disciplina.horaInicial = tfHorarioInicial.getText();
		disciplina.horasDiarias = tfHorasDiarias.getText();
		disciplina.nomeDisciplina = tfNomeDisciplina.getText();

		cadastrarDisciplina(disciplina);
		tfCodigoCursoDisciplina.setText("");
		tfCodigoDisciplina.setText("");
		tfDiadaSemana.setText("");
		tfHorarioInicial.setText("");
		tfHorasDiarias.setText("");
		tfNomeDisciplina.setText("");

	}

	private void cadastrarDisciplina(Disciplina csvDisciplina) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File dir = new File(path);

		if (!dir.exists()) {

			dir.mkdir();

		}
		File arq = new File(path, "disciplinas.csv");
		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}

		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		taDisciplina.setText("Disciplina cadastrada com sucesso!!!");
		pw.write(csvDisciplina + "\r\n");
		pw.flush();
		pw.close();
		fw.close();

	}

	private void busca() throws Exception {

		Disciplina disciplina = new Disciplina();
		disciplina.codigoDisciplina = tfCodigoDisciplina.getText();

		disciplina = buscaDisciplina(disciplina);
		if (disciplina.codigoDisciplina != null) {
			taDisciplina.setText("Codigo Curso: " + disciplina.codCurso + " - Nome Disciplina: "
					+ disciplina.nomeDisciplina + " - Codigo Disciplina" + disciplina.codigoDisciplina
					+ " - Dia da Semana: " + disciplina.diaSemana + " - Hora Inicial: " + disciplina.horaInicial
					+ " - Horas Diarias: " + disciplina.horasDiarias);
		} else {
			taDisciplina.setText("Disciplina não encontrada!");
		}

		tfCodigoDisciplina.setText("");

	}

	private Disciplina buscaDisciplina(Disciplina disciplina) throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "disciplinas.csv");

		Fila<Disciplina> fila = new Fila<>();

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {

				String[] vetLinha = linha.split(";");

				Disciplina disc = new Disciplina();

				disc.codigoDisciplina  = vetLinha[0];
				disc.nomeDisciplina = vetLinha[1];
				disc.diaSemana = vetLinha[2];
				disc.horaInicial = vetLinha[3];
				disc.horasDiarias = vetLinha[4];
				disc.codCurso = vetLinha[5];

				// Insere o objeto prof na Fila criada
				fila.insert(disc);

				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

			int tamanhoFila = fila.size();

			// Vai percorrer a fila até achar o CPF correspondente e retornar o objeto
			// professor

			for (int i = 0; i < tamanhoFila; i++) {

				Disciplina disc = new Disciplina();

				disc = fila.remove();

				if (disc.codigoDisciplina.equals(disciplina.codigoDisciplina)) {
					disciplina = disc;
				}

				fila.insert(disc);

			}
		}

		return disciplina;
	}

	private void atualizar() throws Exception {

		Disciplina disciplina = new Disciplina();
		disciplina.codCurso = tfCodigoCursoDisciplina.getText();
		disciplina.codigoDisciplina = tfCodigoDisciplina.getText();
		disciplina.diaSemana = tfDiadaSemana.getText();
		disciplina.horaInicial = tfHorarioInicial.getText();
		disciplina.horasDiarias = tfHorasDiarias.getText();
		disciplina.nomeDisciplina = tfNomeDisciplina.getText();

		atualizarDisciplina(disciplina);
		tfCodigoCursoDisciplina.setText("");
		tfCodigoDisciplina.setText("");
		tfDiadaSemana.setText("");
		tfHorarioInicial.setText("");
		tfHorasDiarias.setText("");
		tfNomeDisciplina.setText("");

	}

	private void atualizarDisciplina(Disciplina disciplina) throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "disciplinas.csv");

		Lista<Disciplina> lista = new Lista<>();

		// Faz a leitura do arquivo disciplinas.csv

		if (arq.exists() && arq.isFile()) {

			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {

				String[] vetLinha = linha.split(";");

				Disciplina disc = new Disciplina();

				disc.codigoDisciplina = vetLinha[0];
				disc.nomeDisciplina = vetLinha[1];
				disc.diaSemana = vetLinha[2];
				disc.horaInicial = vetLinha[3];
				disc.horasDiarias = vetLinha[4];
				disc.codCurso = vetLinha[5];

				lista.addLast(disc);

				linha = buffer.readLine(); // Incremento while

			}

			taDisciplina.setText("Disciplina atualizada com sucesso!!!");

			buffer.close();
			isr.close();
			fis.close();

		}

		// Parâmetro de busca para atualizar o Professor seria o codigo da disciplina..
		// Logo busca na
		// lista pelo codigo da disciplina do Prof

		for (int i = 0; i < lista.size(); i++) {

			Disciplina disc = new Disciplina();
			disc = lista.get(i);

			if (disc.codigoDisciplina.equals(disciplina.codigoDisciplina)) {

				disc.codCurso = disciplina.codCurso;
				disc.codigoDisciplina = disciplina.codigoDisciplina;
				disc.diaSemana = disciplina.diaSemana;
				disc.horaInicial = disciplina.horaInicial;
				disc.horasDiarias = disciplina.horasDiarias;
				disc.nomeDisciplina = disciplina.nomeDisciplina;

				lista.remove(i);

				lista.add(disc, i);

				// Força o fim do laço
				break;
			}
		}

		// Reescrever o que foi atualizado no arquivo Disciplinas.csv

		FileWriter fw = new FileWriter(arq, false);
		PrintWriter pw = new PrintWriter(fw);

		for (int i = 0; i < lista.size(); i++) {

			Disciplina disc = new Disciplina();

			disc = lista.get(i);

			pw.println(disc.toString());

		}

		pw.flush();
		pw.close();
		fw.close();

	}

	private void remover() throws Exception {

		Disciplina disciplina = new Disciplina();
		disciplina.codCurso = tfCodigoCursoDisciplina.getText();
		disciplina.codigoDisciplina = tfCodigoDisciplina.getText();
		disciplina.diaSemana = tfDiadaSemana.getText();
		disciplina.horaInicial = tfHorarioInicial.getText();
		disciplina.horasDiarias = tfHorasDiarias.getText();
		disciplina.nomeDisciplina = tfNomeDisciplina.getText();

		removerDisciplina(disciplina);
		tfCodigoCursoDisciplina.setText("");
		tfCodigoDisciplina.setText("");
		tfDiadaSemana.setText("");
		tfHorarioInicial.setText("");
		tfHorasDiarias.setText("");
		tfNomeDisciplina.setText("");

	}

	private void removerDisciplina(Disciplina disciplina) throws Exception {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "disciplinas.csv");

		Lista<Disciplina> lista = new Lista<>();

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {

				String[] vetLinha = linha.split(";");

				Disciplina disc = new Disciplina();

				disc.codigoDisciplina = vetLinha[0];
				disc.nomeDisciplina = vetLinha[1];
				disc.diaSemana = vetLinha[2];
				disc.horaInicial = vetLinha[3];
				disc.horasDiarias = vetLinha[4];
				disc.codCurso = vetLinha[5];

				lista.addLast(disc);

				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();

		}

		// Procurando o CPF do professor presente na lista e removendo os dados do
		// professor

		for (int i = 0; i < lista.size(); i++) {

			Disciplina disc = new Disciplina();

			disc = lista.get(i);

			// Vai fazer a comparação com o CPF que do professor que foi mandado como
			// parâmetro

			if (disc.codigoDisciplina.equals(disciplina.codigoDisciplina)) {

				// Vai remover o objeto professor na posição i
				lista.remove(i);
				// Encerra o laço for apos ter achado o CPF a ser removido
				break;
			}

		}

		FileWriter fw = new FileWriter(arq, false);
		PrintWriter pw = new PrintWriter(fw);

		for (int i = 0; i < lista.size(); i++) {

			Disciplina disc = new Disciplina();

			disc = lista.get(i);

			// Chama a função toString para reescrever o professor no arquivo
			pw.println(disc.toString());

		}

		taDisciplina.setText("Disciplina removida com sucesso!!!");

		pw.flush();
		pw.close();
		fw.close();

	}
	
	private void listar() throws Exception {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "disciplinas.csv");

		Fila<Disciplina> fila = new Fila<>();

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {

				String[] vetLinha = linha.split(";");

				Disciplina disc = new Disciplina();

				disc.codigoDisciplina = vetLinha[0];
				disc.nomeDisciplina = vetLinha[1];
				disc.diaSemana = vetLinha[2];
				disc.horaInicial = vetLinha[3];
				disc.horasDiarias = vetLinha[4];
				disc.codCurso = vetLinha[5];

				// Insere o objeto prof na Fila criada
				fila.insert(disc);

				linha = buffer.readLine();

			}

			buffer.close();
			isr.close();
			fis.close();
		}
		listarDisciplina(fila);
	}

	private void listarDisciplina(Fila<Disciplina> fila) {
		Disciplina atual;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<fila.size(); i++) {
			atual = fila.remove();
			sb.append("Codigo Curso: " + atual.codCurso + " - Nome Disciplina: "
					+ atual.nomeDisciplina + " - Codigo Disciplina" + atual.codigoDisciplina
					+ " - Dia da Semana: " + atual.diaSemana + " - Hora Inicial: " + atual.horaInicial
					+ " - Horas Diarias: " + atual.horasDiarias + "\n");
		}
		taDisciplina.setText(sb.toString());
		
	}
	private void limpar() {
		tfCodigoCursoDisciplina.setText("");
		tfCodigoDisciplina.setText("");
		tfDiadaSemana.setText("");
		tfHorarioInicial.setText("");
		tfHorasDiarias.setText("");
		tfNomeDisciplina.setText("");
	}

}
