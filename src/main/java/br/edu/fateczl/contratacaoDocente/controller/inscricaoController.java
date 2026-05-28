package br.edu.fateczl.contratacaoDocente.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class inscricaoController {
	
	private JTextField tfcpfInscricao;
	private JTextField tfcodigoDisciplina;
	private JTextField tfcodigoProcesso;
	private JTextArea tadisciplina;
	
	public inscricaoController(JTextField tfcpfInscricao, JTextField tfcodigoDisciplina, JTextField tfcodigoProcesso, JTextArea tadisciplina) {
		super();
		this.tfcpfInscricao = tfcpfInscricao;
		this.tfcodigoDisciplina = tfcodigoDisciplina;
		this.tfcodigoProcesso = tfcodigoProcesso;
		this.tadisciplina = tadisciplina;
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
			}
		}
		if (cmd.equals("Remover")) {
			try {
				remover();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	

}
