package br.edu.fateczl.contratacaoDocente.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

import br.edu.fateczl.contratacaoDocente.estrutura.TabelaHash;


public class discAbertaController implements ActionListener{
	private JTextArea taDiscAberta;

	public discAbertaController(JTextArea taDiscAberta) {
		super();
		this.taDiscAberta = taDiscAberta;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Consultar")) {
			try {
				consultar();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	private void consultar() throws Exception {
		TabelaHash hash = popularTabela();
		taDiscAberta.setText(hash.listar());
		
	}

	private TabelaHash popularTabela() throws Exception {
		
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
        File arqInsc = new File(path, "inscricoes.csv");

        TabelaHash tabela = new TabelaHash(20);

        if (arqInsc.exists() && arqInsc.isFile()) {
            FileInputStream fis = new FileInputStream(arqInsc);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffer = new BufferedReader(isr);
            String linha = buffer.readLine();

            while (linha != null) {
                String[] vet = linha.split(";");
                
                String codDisciplina = vet[1];
                String nomeDisciplina = buscarNomeDisciplina(codDisciplina);
                
                if (nomeDisciplina != null) {
                    tabela.inserir(Integer.parseInt(codDisciplina), nomeDisciplina);
                }

                linha = buffer.readLine();
            }

            buffer.close();
            isr.close();
            fis.close();
        }

        return tabela;
    }

	private String buscarNomeDisciplina(String codDisciplina) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
        File arq = new File(path, "disciplinas.csv");

        if (arq.exists() && arq.isFile()) {
            FileInputStream fis = new FileInputStream(arq);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffer = new BufferedReader(isr);
            String linha = buffer.readLine();

            while (linha != null) {
                String[] vet = linha.split(";");

                if (vet[0].equals(codDisciplina)) {
                    buffer.close();
                    isr.close();
                    fis.close();
                    return vet[1]; // retorna o nome da disciplina
                }
                linha = buffer.readLine();
            }
            buffer.close();
            isr.close();
            fis.close();
        }
        return null;
    }
}

