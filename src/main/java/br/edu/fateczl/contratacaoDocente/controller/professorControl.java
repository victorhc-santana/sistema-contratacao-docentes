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
			try {
				remover();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
			
	}
	
	
	//Fazer utilizando lista encadeada
	private void atualizar() { //vai atualizar o professor do arquivo professor.csv PARA TERMINAR
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "professor.csv");
		
		
	}
	
	
	//Fazer utilizando lista encadeada
	private void remover() throws IOException { //vai remover o professor do arquivo professor.csv PARA TERMINAR
		
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "professor.csv");
		
		//Captura o CPF inserido pelo usuário e faz um busca em professor.csv para remover
		if (arq.exists() && arq.isFile()) {
			
			String cpfBusca = tfProfessorCpf.getText();
			
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			
			while(linha!=null) {
				String[] vetLinha = linha.split(";");
				if(vetLinha[0].equals(cpfBusca)) {
					vetLinha[1] = null;
					break;
				}
				
			}
			
			
		}
		
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
	
	
	//A parte de busca deve ser feito por meio de uma estrutura Fila
	//Vai ler o arquivo e colocar cada objeto do tipo professor em uma Fila
	//Ler cada posição da Fila até achar o CPF correspondente. Caso ache, retorna o objeto do tipo professor. Caso contrário, retorna "Professor não cadastrado".
	
	private Professor buscaProfessor(Professor professor) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "professor.csv");
		
		Fila<Professor> fila = new Fila<>(); //Criando uma fila com objeto professor
		
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			
			//lendo o arquivo para popular a fila;
			while(linha != null) {
				//Divide a String por ; e insere no vetor vetLinha
				String[] vetLinha = linha.split(";");
				
				Professor prof = new Professor();
				
				//Popular a Fila com cada objeto lido do arquivo
				
				prof.cpf = vetLinha[0];
				prof.nome = vetLinha[1];
				prof.area = vetLinha[2];
				prof.QtdPontos = vetLinha[3];
				
				//Insere o objeto prof na Fila criada
				fila.insert(prof);
				
				linha = buffer.readLine();
				
			}
			
			buffer.close();
			isr.close();
			fis.close();
			
			int tamanhoFila = fila.size();
			
			//Vai percorrer a fila até achar o CPF correspondente e retornar o objeto professor
			
			for (int i = 0; i < tamanhoFila; i++) {
				
				 Professor prof = new Professor();
				 
				 prof = fila.remove();
				 
				 if(prof.cpf.equals(professor.cpf)) {
					 professor = prof;
				 }
				 
				 fila.insert(prof);
				
			}
		}
		
		return professor;
	}		

}
