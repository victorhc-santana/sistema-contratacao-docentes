package br.edu.fateczl.contratacaoDocente.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;

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
		
		JLabel lblNewLabel = new JLabel("Código da disciplina:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 180, 25);
		tabDisciplina.add(lblNewLabel);
		
		JLabel lblNomeDaDisciplina = new JLabel("Nome da disciplina:");
		lblNomeDaDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDaDisciplina.setBounds(320, 11, 180, 25);
		tabDisciplina.add(lblNomeDaDisciplina);
		
		JLabel lblDiaDaSemana = new JLabel("Dia da semana:");
		lblDiaDaSemana.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiaDaSemana.setBounds(10, 47, 180, 25);
		tabDisciplina.add(lblDiaDaSemana);
		
		JLabel lblHorrioInicial = new JLabel("Horário inicial:");
		lblHorrioInicial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHorrioInicial.setBounds(330, 47, 180, 25);
		tabDisciplina.add(lblHorrioInicial);
		
		JLabel lblHorasDirias = new JLabel("Horas diárias:");
		lblHorasDirias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHorasDirias.setBounds(10, 83, 180, 25);
		tabDisciplina.add(lblHorasDirias);
		
		JLabel lblCdigoDoCurso = new JLabel("Código do curso:");
		lblCdigoDoCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCdigoDoCurso.setBounds(320, 83, 180, 25);
		tabDisciplina.add(lblCdigoDoCurso);
		
		textField = new JTextField();
		textField.setBounds(145, 15, 96, 20);
		tabDisciplina.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(448, 15, 145, 20);
		tabDisciplina.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(117, 51, 124, 20);
		tabDisciplina.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(428, 51, 165, 20);
		tabDisciplina.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(101, 87, 140, 20);
		tabDisciplina.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(428, 87, 165, 20);
		tabDisciplina.add(textField_5);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 211, 601, 193);
		tabDisciplina.add(textArea);
		
		JButton btnNewButton = new JButton("Inserir");
		btnNewButton.setBounds(102, 133, 96, 22);
		tabDisciplina.add(btnNewButton);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(247, 133, 96, 22);
		tabDisciplina.add(btnAtualizar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(101, 178, 97, 22);
		tabDisciplina.add(btnRemover);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnConsultar.setBounds(392, 133, 96, 22);
		tabDisciplina.add(btnConsultar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		tabDisciplina.add(panel);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(392, 178, 96, 22);
		tabDisciplina.add(btnLimpar);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(246, 178, 97, 22);
		tabDisciplina.add(btnListar);
		
		JPanel tabCursos = new JPanel();
		tabbedPane.addTab("Cursos", null, tabCursos, "Cursos disponibilizados");
		tabCursos.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Código do curso:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 11, 150, 27);
		tabCursos.add(lblNewLabel_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(10, 211, 601, 193);
		tabCursos.add(textArea_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(125, 16, 96, 20);
		tabCursos.add(textField_6);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome do curso:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 51, 150, 27);
		tabCursos.add(lblNewLabel_1_1);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(125, 56, 291, 20);
		tabCursos.add(textField_7);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Área de conhecimento:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(10, 89, 150, 27);
		tabCursos.add(lblNewLabel_1_1_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(161, 94, 255, 20);
		tabCursos.add(textField_8);
		
		JButton btnNewButton_1 = new JButton("Inserir");
		btnNewButton_1.setBounds(95, 140, 96, 22);
		tabCursos.add(btnNewButton_1);
		
		JButton btnAtualizar_1 = new JButton("Atualizar");
		btnAtualizar_1.setBounds(240, 140, 96, 22);
		tabCursos.add(btnAtualizar_1);
		
		JButton btnConsultar_1 = new JButton("Consultar");
		btnConsultar_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnConsultar_1.setBounds(385, 140, 96, 22);
		tabCursos.add(btnConsultar_1);
		
		JButton btnRemover_1 = new JButton("Remover");
		btnRemover_1.setBounds(95, 178, 97, 22);
		tabCursos.add(btnRemover_1);
		
		JButton btnListar_1 = new JButton("Listar");
		btnListar_1.setBounds(240, 178, 97, 22);
		tabCursos.add(btnListar_1);
		
		JButton btnLimpar_1 = new JButton("Limpar");
		btnLimpar_1.setBounds(386, 178, 96, 22);
		tabCursos.add(btnLimpar_1);
		
		JPanel tabProfessores = new JPanel();
		tabbedPane.addTab("Professores", null, tabProfessores, "Professores cadastrados");
		tabProfessores.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("CPF:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 11, 150, 27);
		tabProfessores.add(lblNewLabel_1_2);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(51, 16, 166, 20);
		tabProfessores.add(textField_9);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Nome Completo:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(239, 11, 150, 27);
		tabProfessores.add(lblNewLabel_1_2_1);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(349, 16, 235, 20);
		tabProfessores.add(textField_10);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Área Pretendida:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1.setBounds(239, 49, 150, 27);
		tabProfessores.add(lblNewLabel_1_2_1_1);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(349, 54, 235, 20);
		tabProfessores.add(textField_11);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("Pontuação:");
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_2.setBounds(10, 49, 109, 27);
		tabProfessores.add(lblNewLabel_1_2_1_2);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(83, 54, 134, 20);
		tabProfessores.add(textField_12);
		
		JTextArea textArea_1_1 = new JTextArea();
		textArea_1_1.setBounds(10, 211, 601, 193);
		tabProfessores.add(textArea_1_1);
		
		JButton btnNewButton_1_1 = new JButton("Inserir");
		btnNewButton_1_1.setBounds(100, 126, 96, 22);
		tabProfessores.add(btnNewButton_1_1);
		
		JButton btnAtualizar_1_1 = new JButton("Atualizar");
		btnAtualizar_1_1.setBounds(245, 126, 96, 22);
		tabProfessores.add(btnAtualizar_1_1);
		
		JButton btnConsultar_1_1 = new JButton("Consultar");
		btnConsultar_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnConsultar_1_1.setBounds(390, 126, 96, 22);
		tabProfessores.add(btnConsultar_1_1);
		
		JButton btnRemover_1_1 = new JButton("Remover");
		btnRemover_1_1.setBounds(100, 164, 97, 22);
		tabProfessores.add(btnRemover_1_1);
		
		JButton btnListar_1_1 = new JButton("Listar");
		btnListar_1_1.setBounds(245, 164, 97, 22);
		tabProfessores.add(btnListar_1_1);
		
		JButton btnLimpar_1_1 = new JButton("Limpar");
		btnLimpar_1_1.setBounds(391, 164, 96, 22);
		tabProfessores.add(btnLimpar_1_1);
		
		JPanel tabInscrições = new JPanel();
		tabbedPane.addTab("Inscrições", null, tabInscrições, "Professores inscritos");
		tabInscrições.setLayout(null);
		
		JTextArea textArea_1_1_1 = new JTextArea();
		textArea_1_1_1.setBounds(10, 211, 601, 193);
		tabInscrições.add(textArea_1_1_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("CPF do Professor:");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2.setBounds(10, 11, 150, 27);
		tabInscrições.add(lblNewLabel_1_2_2);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(128, 16, 166, 20);
		tabInscrições.add(textField_13);
		
		JLabel lblNewLabel_1_2_2_1 = new JLabel("Código da disciplina:");
		lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2_1.setBounds(10, 49, 150, 27);
		tabInscrições.add(lblNewLabel_1_2_2_1);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(148, 54, 146, 20);
		tabInscrições.add(textField_14);
		
		JLabel lblNewLabel_1_2_2_1_1 = new JLabel("Código do processo:");
		lblNewLabel_1_2_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2_1_1.setBounds(10, 87, 150, 27);
		tabInscrições.add(lblNewLabel_1_2_2_1_1);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(148, 92, 146, 20);
		tabInscrições.add(textField_15);
		
		JButton btnNewButton_1_1_1 = new JButton("Inserir");
		btnNewButton_1_1_1.setBounds(107, 129, 96, 22);
		tabInscrições.add(btnNewButton_1_1_1);
		
		JButton btnAtualizar_1_1_1 = new JButton("Atualizar");
		btnAtualizar_1_1_1.setBounds(252, 129, 96, 22);
		tabInscrições.add(btnAtualizar_1_1_1);
		
		JButton btnConsultar_1_1_1 = new JButton("Consultar");
		btnConsultar_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnConsultar_1_1_1.setBounds(397, 129, 96, 22);
		tabInscrições.add(btnConsultar_1_1_1);
		
		JButton btnRemover_1_1_1 = new JButton("Remover");
		btnRemover_1_1_1.setBounds(107, 167, 97, 22);
		tabInscrições.add(btnRemover_1_1_1);
		
		JButton btnListar_1_1_1 = new JButton("Listar");
		btnListar_1_1_1.setBounds(252, 167, 97, 22);
		tabInscrições.add(btnListar_1_1_1);
		
		JButton btnLimpar_1_1_1 = new JButton("Limpar");
		btnLimpar_1_1_1.setBounds(398, 167, 96, 22);
		tabInscrições.add(btnLimpar_1_1_1);
		
		JPanel tabConsultaInscrito = new JPanel();
		tabbedPane.addTab("Inscritos", null, tabConsultaInscrito, "Consultar inscritos");
		tabConsultaInscrito.setLayout(null);
		
		JTextArea textArea_1_1_1_1 = new JTextArea();
		textArea_1_1_1_1.setBounds(10, 156, 601, 248);
		tabConsultaInscrito.add(textArea_1_1_1_1);
		
		JLabel lblNewLabel_1_2_2_1_2 = new JLabel("Código da disciplina:");
		lblNewLabel_1_2_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2_1_2.setBounds(69, 30, 150, 27);
		tabConsultaInscrito.add(lblNewLabel_1_2_2_1_2);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(207, 35, 183, 20);
		tabConsultaInscrito.add(textField_16);
		
		JButton btnConsultar_1_1_1_1 = new JButton("Consultar");
		btnConsultar_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnConsultar_1_1_1_1.setBounds(400, 34, 96, 22);
		tabConsultaInscrito.add(btnConsultar_1_1_1_1);
		
		JLabel lblNewLabel_1_2_2_1_2_1 = new JLabel("Professores inscritos nessa disciplina: ");
		lblNewLabel_1_2_2_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2_1_2_1.setBounds(10, 107, 323, 27);
		tabConsultaInscrito.add(lblNewLabel_1_2_2_1_2_1);
		
		JPanel tabDiscipAbertas = new JPanel();
		tabbedPane.addTab("Disciplinas Abertas", null, tabDiscipAbertas, null);
		tabDiscipAbertas.setLayout(null);
		
		JTextArea textArea_1_1_1_1_1 = new JTextArea();
		textArea_1_1_1_1_1.setBounds(10, 142, 601, 262);
		tabDiscipAbertas.add(textArea_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_2_1_2_1_1 = new JLabel("Disciplinas com processos em aberto");
		lblNewLabel_1_2_2_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2_1_2_1_1.setBounds(10, 11, 362, 55);
		tabDiscipAbertas.add(lblNewLabel_1_2_2_1_2_1_1);
		
		JButton btnConsultar_1_1_1_1_1 = new JButton("Consultar");
		btnConsultar_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnConsultar_1_1_1_1_1.setBounds(136, 64, 119, 36);
		tabDiscipAbertas.add(btnConsultar_1_1_1_1_1);
		
		JButton btnLimpar_1_1_1_1 = new JButton("Limpar");
		btnLimpar_1_1_1_1.setBounds(327, 64, 109, 36);
		tabDiscipAbertas.add(btnLimpar_1_1_1_1);

	}
}
