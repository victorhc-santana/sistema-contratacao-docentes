package br.edu.fateczl.contratacaoDocente.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setTitle("Sistema de Contratação de Docentes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 626, 443);
		contentPane.add(tabbedPane);
		
		JPanel tabDisciplina = new JPanel();
		tabbedPane.addTab("Disciplinas", null, tabDisciplina, "Cadastro de disciplinas");
		tabDisciplina.setLayout(null);
		
		JTabbedPane tabCursos = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Cursos", null, tabCursos, null);
		
		JTabbedPane tabProfessores = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Professores", null, tabProfessores, null);
		
		JTabbedPane tabInscrições = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Inscrições", null, tabInscrições, null);
		
		JTabbedPane tabConsultaInscritos = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Consulta Inscritos", null, tabConsultaInscritos, null);
		
		JTabbedPane tabDiscAbertas = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Disciplinas Abertas", null, tabDiscAbertas, null);

	}
}
