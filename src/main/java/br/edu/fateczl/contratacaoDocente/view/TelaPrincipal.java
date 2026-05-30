package br.edu.fateczl.contratacaoDocente.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.fateczl.contratacaoDocente.controller.InscritosController;
import br.edu.fateczl.contratacaoDocente.controller.cursoController;
import br.edu.fateczl.contratacaoDocente.controller.disciplinaController;
import br.edu.fateczl.contratacaoDocente.controller.inscricaoController;
//import br.edu.fateczl.contratacaoDocente.controller.disciplinaController;
import br.edu.fateczl.contratacaoDocente.controller.professorControl;

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
	private JTextField txtCodigoDisciplina;
	private JTextField txtNomeDisciplina;
	private JTextField txtDiadaSemana;
	private JTextField txtHorarioInicial;
	private JTextField txtHorasDiarias;
	private JTextField txtCodigoCursoDisciplina;
	private JTextField txtCodigoCurso;
	private JTextField txtNomeCurso;
	private JTextField txtAreaConhecimento;
	private JTextField tfProfessorCpf;
	private JTextField tfProfessorNome;
	private JTextField tfProfessorArea;
	private JTextField tfProfessorPontos;
	private JTextField txtCpfInscricao;
	private JTextField txtCodigoDisciplinaInscricao;
	private JTextField txtCodigoProcesso;
	private JTextField txtCodigoDisciplinaConsulta;

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
		
		/*
		 * inicio da tad disciplina
		 * */
		JPanel tabDisciplina = new JPanel();
		tabbedPane.addTab("Disciplinas", null, tabDisciplina, "Cadastro de disciplinas");
		tabDisciplina.setLayout(null);
		
		JLabel lblCodDisciplina = new JLabel("Código da disciplina:");
		lblCodDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodDisciplina.setBounds(10, 11, 180, 25);
		tabDisciplina.add(lblCodDisciplina);
		
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
		
		txtCodigoDisciplina = new JTextField();
		txtCodigoDisciplina.setBounds(145, 15, 96, 20);
		tabDisciplina.add(txtCodigoDisciplina);
		txtCodigoDisciplina.setColumns(10);
		
		txtNomeDisciplina = new JTextField();
		txtNomeDisciplina.setBounds(448, 15, 145, 20);
		tabDisciplina.add(txtNomeDisciplina);
		txtNomeDisciplina.setColumns(10);
		
		txtDiadaSemana = new JTextField();
		txtDiadaSemana.setBounds(117, 51, 124, 20);
		tabDisciplina.add(txtDiadaSemana);
		txtDiadaSemana.setColumns(10);
		
		txtHorarioInicial = new JTextField();
		txtHorarioInicial.setColumns(10);
		txtHorarioInicial.setBounds(428, 51, 165, 20);
		tabDisciplina.add(txtHorarioInicial);
		
		txtHorasDiarias = new JTextField();
		txtHorasDiarias.setColumns(10);
		txtHorasDiarias.setBounds(101, 87, 140, 20);
		tabDisciplina.add(txtHorasDiarias);
		
		txtCodigoCursoDisciplina = new JTextField();
		txtCodigoCursoDisciplina.setColumns(10);
		txtCodigoCursoDisciplina.setBounds(428, 87, 165, 20);
		tabDisciplina.add(txtCodigoCursoDisciplina);
		
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
		
		disciplinaController disciplinaControl = new disciplinaController(txtCodigoDisciplina, txtNomeDisciplina,
				txtDiadaSemana, txtHorarioInicial, txtHorasDiarias, txtCodigoCursoDisciplina, textArea);
		btnNewButton.addActionListener(disciplinaControl);
		btnAtualizar.addActionListener(disciplinaControl);
		btnRemover.addActionListener(disciplinaControl);
		btnConsultar.addActionListener(disciplinaControl);
		btnLimpar.addActionListener(disciplinaControl);
		btnListar.addActionListener(disciplinaControl);
		
		
		/*
		 * inicio tab cursos 
		 * */
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
		
		txtCodigoCurso = new JTextField();
		txtCodigoCurso.setColumns(10);
		txtCodigoCurso.setBounds(125, 16, 96, 20);
		tabCursos.add(txtCodigoCurso);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome do curso:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 51, 150, 27);
		tabCursos.add(lblNewLabel_1_1);
		
		txtNomeCurso = new JTextField();
		txtNomeCurso.setColumns(10);
		txtNomeCurso.setBounds(125, 56, 291, 20);
		tabCursos.add(txtNomeCurso);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Área de conhecimento:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(10, 89, 150, 27);
		tabCursos.add(lblNewLabel_1_1_1);
		
		txtAreaConhecimento = new JTextField();
		txtAreaConhecimento.setColumns(10);
		txtAreaConhecimento.setBounds(161, 94, 255, 20);
		tabCursos.add(txtAreaConhecimento);
		
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
		
		cursoController cursoCont = new cursoController(txtCodigoCurso, txtNomeCurso, txtAreaConhecimento, textArea_1);
		//há dois botões não implementados no curso controller, listar e limpar
		btnNewButton_1.addActionListener(cursoCont);
		btnAtualizar_1.addActionListener(cursoCont);
		btnConsultar_1.addActionListener(cursoCont);
		btnRemover_1.addActionListener(cursoCont);
		btnListar_1.addActionListener(cursoCont);
		btnLimpar_1.addActionListener(cursoCont);
		
		/*
		 * inicio tab professores
		 */
		JPanel tabProfessores = new JPanel();
		tabbedPane.addTab("Professores", null, tabProfessores, "Professores cadastrados");
		tabProfessores.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("CPF:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 11, 150, 27);
		tabProfessores.add(lblNewLabel_1_2);
		
		tfProfessorCpf = new JTextField();
		tfProfessorCpf.setColumns(10);
		tfProfessorCpf.setBounds(51, 16, 166, 20);
		tabProfessores.add(tfProfessorCpf);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Nome Completo:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(239, 11, 150, 27);
		tabProfessores.add(lblNewLabel_1_2_1);
		
		tfProfessorNome = new JTextField();
		tfProfessorNome.setColumns(10);
		tfProfessorNome.setBounds(349, 16, 235, 20);
		tabProfessores.add(tfProfessorNome);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Área Pretendida:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1.setBounds(239, 49, 150, 27);
		tabProfessores.add(lblNewLabel_1_2_1_1);
		
		tfProfessorArea = new JTextField();
		tfProfessorArea.setColumns(10);
		tfProfessorArea.setBounds(349, 54, 235, 20);
		tabProfessores.add(tfProfessorArea);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("Pontuação:");
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_2.setBounds(10, 49, 109, 27);
		tabProfessores.add(lblNewLabel_1_2_1_2);
		
		tfProfessorPontos = new JTextField();
		tfProfessorPontos.setColumns(10);
		tfProfessorPontos.setBounds(83, 54, 134, 20);
		tabProfessores.add(tfProfessorPontos);
		
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
		
		//da função aos botoes
		professorControl professorCont = new professorControl(tfProfessorCpf, tfProfessorNome, tfProfessorArea, tfProfessorPontos, textArea_1_1);
		//há dois botoes sem função no professorControl, listar e limpar
		btnNewButton_1_1.addActionListener(professorCont);
		btnAtualizar_1_1.addActionListener(professorCont);
		btnConsultar_1_1.addActionListener(professorCont);
		btnRemover_1_1.addActionListener(professorCont);
		btnListar_1_1.addActionListener(professorCont);
		btnLimpar_1_1.addActionListener(professorCont);
		
		
		/*
		 * inicio tab inscrições
		 * */
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
		
		txtCpfInscricao = new JTextField();
		txtCpfInscricao.setColumns(10);
		txtCpfInscricao.setBounds(128, 16, 166, 20);
		tabInscrições.add(txtCpfInscricao);
		
		JLabel lblNewLabel_1_2_2_1 = new JLabel("Código da disciplina:");
		lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2_1.setBounds(10, 49, 150, 27);
		tabInscrições.add(lblNewLabel_1_2_2_1);
		
		txtCodigoDisciplinaInscricao = new JTextField();
		txtCodigoDisciplinaInscricao.setColumns(10);
		txtCodigoDisciplinaInscricao.setBounds(148, 54, 146, 20);
		tabInscrições.add(txtCodigoDisciplinaInscricao);
		
		JLabel lblNewLabel_1_2_2_1_1 = new JLabel("Código do processo:");
		lblNewLabel_1_2_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2_1_1.setBounds(10, 87, 150, 27);
		tabInscrições.add(lblNewLabel_1_2_2_1_1);
		
		txtCodigoProcesso = new JTextField();
		txtCodigoProcesso.setColumns(10);
		txtCodigoProcesso.setBounds(148, 92, 146, 20);
		tabInscrições.add(txtCodigoProcesso);
		
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
		
		inscricaoController inscricaoControl = new inscricaoController(txtCpfInscricao,
				txtCodigoDisciplinaInscricao, txtCodigoProcesso, textArea_1_1_1);
		btnNewButton_1_1_1.addActionListener(inscricaoControl);
		btnAtualizar_1_1_1.addActionListener(inscricaoControl);
		btnRemover_1_1_1.addActionListener(inscricaoControl);
		btnConsultar_1_1_1.addActionListener(inscricaoControl);
		btnListar_1_1_1.addActionListener(inscricaoControl);
		btnLimpar_1_1_1.addActionListener(inscricaoControl);
		
		/*
		 * inicio tab consulta
		 * */
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
		
		txtCodigoDisciplinaConsulta = new JTextField();
		txtCodigoDisciplinaConsulta.setColumns(10);
		txtCodigoDisciplinaConsulta.setBounds(207, 35, 183, 20);
		tabConsultaInscrito.add(txtCodigoDisciplinaConsulta);
		
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
		
		InscritosController inscritosControl = new InscritosController(txtCodigoDisciplinaConsulta, textArea_1_1_1_1);
		btnConsultar_1_1_1_1.addActionListener(inscritosControl);

	}
}
