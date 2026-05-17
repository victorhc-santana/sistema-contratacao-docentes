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

public class professorControl implements ActionListener {
	
	//Pegar os elementos da tela para extrair o conteúdo deles
	
	private JTextField tfProfessorCpf;
	private JTextField tfProfessorNome;
	private JTextField tfProfessorArea;
	private JTextField tfProfessorPontos;
	private JTextArea taProfessor;
	
	public professorControl(JTextField tfProfessorCpf, JTextField tfProfessorNome, JTextField tfProfessorArea, JTextField tfProfessorPontos, JTextArea taProfessor) {

		this.tfProfessorCpf = tfProfessorCpf;
		this.tfProfessorNome = tfProfessorNome;
		this.tfProfessorArea = tfProfessorArea;
		this.tfProfessorPontos = tfProfessorPontos;
		this.taProfessor = taProfessor;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Inserir")) {
			try {
				cadastro();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("Consultar")) {
			try {
				busca();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("Atualizar")) {
			atualizar();
		}
		if(cmd.equals("Remover")) {
			remover();
		}
			
	}
	
	private void atualizar() { //vai atualizar o professor do arquivo professor.csv PARA TERMINAR
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "professor.csv");
		
		
	}

	private void remover() { //vai remover o professor do arquivo professor.csv PARA TERMINAR
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "professor.csv");
		
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
		
		if(!dir.exists()) {
			
			dir.mkdir();
			
		}
		File arq = new File(path, "professor.csv");
		boolean existe = false;
		if(arq.exists()) {
			existe = true;
		}
		
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvProfessor+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
		
	}
	
	//buscar o professor por CPF e mostre no Text Area o resultado da busca
	
	private void busca() throws IOException {
		Professor professor = new Professor();
		professor.cpf = tfProfessorCpf.getText();
		
		professor = buscaProfessor(professor);
		if (professor.nome != null) {
			taProfessor.setText("CPF: " +professor.cpf+" - Nome: "+professor.nome);
		} else {
			taProfessor.setText("Professor não encontrado");
		}
		
	}

	private Professor buscaProfessor(Professor professor) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "professor.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while(linha != null) {
				String[] vetLinha = linha.split(";");
				if(vetLinha[0].equals(professor.cpf)) {
					professor.nome = vetLinha[1];
					break;
				}
				
				linha = buffer.readLine();
				
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		
		return professor;
	}		

}
