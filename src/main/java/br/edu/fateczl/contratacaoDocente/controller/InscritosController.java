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
import model.Lista;

public class InscritosController implements ActionListener {
	
	
	//Ver somente se clicando no botao vai mostrar todas as inscrições ou procurar por algum atributo
	private JTextField tfCodigoDisciplinaConsulta;
	private JTextArea taConsulta;
	
	

	public InscritosController(JTextField tfCodigoDisciplinaConsulta,JTextArea taConsulta) {
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
	
	//Cria uma lista a partir dos dados lidos do arquivo inscrições
	private void consultar() throws Exception {

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
		
		ordena(lista);
	}
	
	//Lista deve ser ordenada pela pontuação e as informações devem ser printadas no TextArea!!!
	private void ordena(Lista<Inscricao> lista) {
		
	}
	
	

}
