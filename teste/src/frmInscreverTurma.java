import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmInscreverTurma extends JFrame {
	private JPanel contentPane;
	private bd objBD = null;
	private JTextField txtID;
	private JTextField txtUltimoID;
	private JTextField txtStatus;
	private JLabel lblAlerta;
	private JRadioButton rdbtnSabado;
	private JRadioButton rdbtnSexta;
	private JRadioButton rdbtnQuinta;
	private JRadioButton rdbtnQuarta;
	private JRadioButton rdbtnTerca ;
	private JRadioButton rdbtnSegunda;
	private JSpinner spnValor;
	private JTextArea txtInformacoes;
	private JSpinner spnProfessoresVagas;
	private JSpinner spnAlunosVagas;
	private JSpinner spnAlunosBolsas; 
	private JComboBox<String> cbBoxCurso;
	private JTextField txtNivel;
	private JTextField txtArea;
	private JTextField txtDuracao;
	private JLabel lblDuracaoTipo;
	private JTextPane txtDescricao;
	private String[] vetMes = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private JComboBox<String> cbBoxDiaI;
	private JComboBox<String> cbBoxMesI;
	private JComboBox<String> cbBoxAnoI;
	private JComboBox<String> cbBoxDiaT;
	private JComboBox<String> cbBoxMesT;
	private JComboBox<String> cbBoxAnoT;
	private String[] duracaoTipo = {"Horas","Semestres","Anos"};
	private int[] vetDias = {0,0,0,0,0,0}; 
	private int idCurso=0;
	private boolean c;
	private Formata f;
	private String[] vDS = new String[6];
	private JComboBox<String> cbBoxPeriodo;
	private JComboBox<String> cbBoxIDCurso;
	private JLabel lblIDNome;
	private JTextField txtEstado;
	private int stCourse=0;//variavel que salva o estado do curso, bloqueia ou permite a edi��o
	private String[] eBDJOP= {"N�o foi poss�vel conectar com o banco de dados!","ERRO! "};//msg de erro inserida no vetor devido a repeti��o 7x
	private String fGD;
	private int[] vA= {0,0};
	private int[] vAB= {0,0};
	private int[] vP= {0,0};
	private JComboBox<String> cbBoxHoraI;
	private JComboBox<String> cbBoxHoraT;
	private JComboBox<String> cbBoxMinutoI;
	private JComboBox<String> cbBoxMinutoT;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmInscreverTurma frame = new frmInscreverTurma();
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
	public frmInscreverTurma() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\teste\\img\\Icon - 200 x 200.jpg"));
		this.objBD = new bd("POOX","root","");
		setTitle("POOX - Inscri\u00E7\u00E3o de Turmas");
		setBounds(100, 100, 750, 670);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ComboBox.editorBorder"));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//JLabelAlerta		
		lblAlerta = new JLabel("Ante\u00E7\u00E3o: Preencha o ID para verificar o Status!");
		lblAlerta.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		lblAlerta.setBounds(162, 0, 259, 14);
		contentPane.add(lblAlerta);
		
//JLabel ID		
		JLabel label = new JLabel("ID:");
		label.setBounds(22, 26, 24, 14);
		contentPane.add(label);
		//JTextField ID
		txtID = new JTextField();
		txtID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				lblAlerta.setVisible(false);
				boolean[] vB = {false,true};//vetor booleano para verificar os dias da semana: 0 false, 1 true
				
				if (!objBD.conectaBD()){
					errorJOP(eBDJOP[0],eBDJOP[1]);
				}else{
					String sqlID = "SELECT *, day(data_inicio), MONTH(data_inicio), YEAR(data_inicio), DAY(data_termino), MONTH(data_termino), YEAR(data_termino) from turmas where id = '"+txtID.getText()+"'";
					ResultSet objResID = objBD.consulta(sqlID);
					try{
						if (objResID.next()){
							cbBoxDiaI.setSelectedItem(objResID.getString("day(data_inicio)"));
							cbBoxMesI.setSelectedItem(objResID.getString("month(data_inicio)"));
							cbBoxAnoI.setSelectedItem(objResID.getString("year(data_inicio)"));
							cbBoxDiaT.setSelectedItem(objResID.getString("day(data_termino)"));
							cbBoxMesT.setSelectedItem(objResID.getString("month(data_termino)"));
							cbBoxAnoT.setSelectedItem(objResID.getString("year(data_termino)"));
							cbBoxPeriodo.setSelectedIndex(objResID.getInt("periodo"));
							//gabiarra para salvar dias da semana no radiobutton e no vetor
							int week =0; //criada variavel e iniciada
							vetDias[0] = week =objResID.getInt("segunda");
							rdbtnSegunda.setSelected(vB[week]);
							vetDias[1] = week = objResID.getInt("terca");
							rdbtnTerca.setSelected(vB[week]);
							vetDias[2] = week = objResID.getInt("quarta");
							rdbtnQuarta.setSelected(vB[week]);
							vetDias[3] = week = objResID.getInt("quinta");
							rdbtnQuinta.setSelected(vB[week]);
							vetDias[4] = week = objResID.getInt("sexta");
							rdbtnSexta.setSelected(vB[week]);
							vetDias[5] = week = objResID.getInt("sabado");
							rdbtnSabado.setSelected(vB[week]);
							//fim da gambiarra
							spnValor.setValue(objResID.getFloat("investimento"));
							txtInformacoes.setText(objResID.getString("informacoes"));
							spnProfessoresVagas.setValue(vP[0]=(objResID.getInt("professores_vagas")));
							spnAlunosBolsas.setValue(vAB[0]=(objResID.getInt("alunos_bolsas_vagas")));
							spnAlunosVagas.setValue(vA[0]=(objResID.getInt("alunos_vagas")));
							setCurso("cursos.id = "+objResID.getInt("cursos_id"),-1);// envia index -1, qualquer n�mero, n�o pode ser zero(verificar metodo)
							txtStatus.setText("Turma Existente");
							txtStatus.setForeground(Color.BLUE);
							mtdStCourse(objResID.getInt("estado"));				
						} else{
							limpar(false);
							txtStatus.setText("Cadastrar Turma");
							txtStatus.setForeground(Color.GREEN);
							mtdStCourse(-1);//Qualquer valor, n�o pode ser 0,1 e 2 (metodo exibe o estado da turma).
						}
					}catch(SQLException e){
						errorJOP("N�o foi poss�vel carregar o cadastro!","Erro na consulta:");
					}
				}
			}
		});
		txtID.setBounds(66, 23, 86, 20);
		contentPane.add(txtID);
		txtID.setToolTipText("ID da Turma");
		txtID.setColumns(10);
//JLabel Estado da inscri��o 
		JLabel lblNewLabel_4 = new JLabel("Turma:");
		lblNewLabel_4.setBounds(214, 25, 56, 14);
		contentPane.add(lblNewLabel_4);
		//JTextField Estado da inscri��o
		txtEstado = new JTextField();
		txtEstado.setHorizontalAlignment(SwingConstants.CENTER);
		txtEstado.setText("");
		txtEstado.setEditable(false);
		txtEstado.setBounds(278, 23, 102, 20);
		txtEstado.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(txtEstado);
//JLabel Status
		JLabel label_10 = new JLabel("Status:");
		label_10.setBounds(476, 14, 46, 14);
		contentPane.add(label_10);
		//JLabel Status do ID
		txtStatus = new JTextField();
		txtStatus.setText("");
		txtStatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatus.setEditable(false);
		txtStatus.setBounds(428, 30, 141, 20);
		txtStatus.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(txtStatus);
//JLabel �ltimo ID
		JLabel label_2 = new JLabel("\u00DAltimo ID:");
		label_2.setBounds(621, 14, 62, 14);
		contentPane.add(label_2);
		//JTextField �ltimo ID
		txtUltimoID = new JTextField();
		txtUltimoID.setBounds(595, 30, 127, 20);
		contentPane.add(txtUltimoID);
		txtUltimoID.setEditable(false);
		txtUltimoID.setColumns(10);
		
//JPanel Turma
		JPanel pnlTurma = new JPanel();
		pnlTurma.setBorder(new LineBorder(new Color(171, 173, 179)));
		pnlTurma.setBounds(10, 55, 724, 260);
		contentPane.add(pnlTurma);
		pnlTurma.setLayout(null);
		//JLabel Turma
		JLabel lblData = new JLabel("Turma");
		lblData.setBounds(262, 5, 166, 17);
		pnlTurma.add(lblData);
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
//JLabel data In�cio
		JLabel lblIncio = new JLabel("In\u00EDcio:");
		lblIncio.setBounds(21, 26, 46, 14);
		pnlTurma.add(lblIncio);
		//JComboBox Dia
		cbBoxDiaI = new JComboBox();
		cbBoxDiaI.setModel(new DefaultComboBoxModel(new String[] {"Dia"}));
		cbBoxDiaI.setBounds(86, 23, 46, 20);
		pnlTurma.add(cbBoxDiaI);
		//JComboBox M�s
		cbBoxMesI = new JComboBox<String>();
		cbBoxMesI.setModel(new DefaultComboBoxModel(new String[] {"M\u00EAs"}));
		cbBoxMesI.setBounds(142, 23, 93, 20);
		pnlTurma.add(cbBoxMesI);
		//JComboBox Ano
		cbBoxAnoI = new JComboBox();
		cbBoxAnoI.setModel(new DefaultComboBoxModel(new String[] {"Ano"}));
		cbBoxAnoI.setBounds(245, 23, 68, 20);
		pnlTurma.add(cbBoxAnoI);
		
//JLabel data T�rmino
		JLabel lblNewLabel = new JLabel("T\u00E9rmino:");
		lblNewLabel.setBounds(21, 51, 68, 14);
		pnlTurma.add(lblNewLabel);
		//JComboBox Dia
		cbBoxDiaT = new JComboBox();
		cbBoxDiaT.setModel(new DefaultComboBoxModel(new String[] {"Dia"}));
		cbBoxDiaT.setBounds(86, 51, 46, 20);
		pnlTurma.add(cbBoxDiaT);
		//JComboBox M�s
		cbBoxMesT = new JComboBox();
		cbBoxMesT.setModel(new DefaultComboBoxModel(new String[] {"M\u00EAs"}));
		cbBoxMesT.setBounds(142, 51, 93, 20);
		pnlTurma.add(cbBoxMesT);
		//JComboBox Ano
		cbBoxAnoT = new JComboBox();
		cbBoxAnoT.setModel(new DefaultComboBoxModel(new String[] {"Ano"}));
		cbBoxAnoT.setBounds(245, 51, 68, 20);
		pnlTurma.add(cbBoxAnoT);
		
//JLabel Per�odo
		JLabel label_9 = new JLabel("Per\u00EDodo:");
		label_9.setBounds(21, 79, 56, 14);
		pnlTurma.add(label_9);
		//JComboBox Per�odo
		cbBoxPeriodo = new JComboBox<String>();
		cbBoxPeriodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearCbBoxHoras();
				addHour(cbBoxPeriodo.getSelectedIndex());
				addMin(cbBoxMinutoI);
				addMin(cbBoxMinutoT);
			}
		});
		cbBoxPeriodo.setBounds(86, 76, 86, 20);
		pnlTurma.add(cbBoxPeriodo);
		cbBoxPeriodo.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecionar", "Manh\u00E3", "Tarde", "Noite"}));

//JPanel Dias da semana
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 104, 350, 76);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlTurma.add(panel_4);
		panel_4.setLayout(null);
		//JLabel Dias da Semana
		JLabel lblNewLabel_2 = new JLabel("Dias da Semana");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(92, 0, 98, 14);
		panel_4.add(lblNewLabel_2);
		//JRadioButton Segunda 
		rdbtnSegunda = new JRadioButton("Segunda-Feira");
		rdbtnSegunda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rbS(rdbtnSegunda, 0);//Se selecionado o rdbtn, o vetor na posi��o [0] recebe valor 1, sen�o vetor recebe 0.
			}
		});
		rdbtnSegunda.setBounds(6, 21, 110, 23);
		panel_4.add(rdbtnSegunda);
		//JRadioButton Terca
		rdbtnTerca = new JRadioButton("Ter\u00E7a-Feira");
		rdbtnTerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rbS(rdbtnTerca,1);
			}
		});
		rdbtnTerca.setBounds(122, 21, 110, 23);
		panel_4.add(rdbtnTerca);
		//JRadioButton Quarta
		rdbtnQuarta = new JRadioButton("Quarta-Feira");
		rdbtnQuarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rbS(rdbtnQuarta,2);
			}
		});
		rdbtnQuarta.setBounds(234, 21, 110, 23);
		panel_4.add(rdbtnQuarta);
		//JRadioButton Quinta
		rdbtnQuinta = new JRadioButton("Quinta-Feira");
		rdbtnQuinta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rbS(rdbtnQuinta,3);
			}
		});
		rdbtnQuinta.setBounds(6, 46, 110, 23);
		panel_4.add(rdbtnQuinta);
		//JRadioButton Sexta
		rdbtnSexta = new JRadioButton("Sexta-Feira");
		rdbtnSexta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rbS(rdbtnSexta,4);
			}
		});
		rdbtnSexta.setBounds(122, 46, 110, 23);
		panel_4.add(rdbtnSexta);
		//JRadioButton Sabado
		rdbtnSabado = new JRadioButton("S\u00E1bado");
		rdbtnSabado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rbS(rdbtnSabado,5);
			}
		});
		rdbtnSabado.setBounds(234, 47, 110, 23);
		panel_4.add(rdbtnSabado);
		
//JLabel Investimento		
		JLabel lblNewLabel_3 = new JLabel("Investimento");
		lblNewLabel_3.setBounds(86, 191, 111, 14);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTurma.add(lblNewLabel_3);
		//JLabel Total
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(21, 219, 46, 14);
		pnlTurma.add(lblTotal);
		//Jlabel R$
		JLabel lblR = new JLabel("R$");
		lblR.setBounds(126, 219, 46, 14);
		pnlTurma.add(lblR);
		//spnValor
		spnValor = new JSpinner();
		spnValor.setBounds(175, 216, 84, 20);
		spnValor.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(999999), new Float(1)));
		pnlTurma.add(spnValor); 
		
//JLabel Informa��es
		JLabel lblNewLabel_1 = new JLabel("Informa\u00E7\u00F5es :");
		lblNewLabel_1.setBounds(510, 45, 86, 14);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTurma.add(lblNewLabel_1);
		//JScrollPane Informa��es
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(370, 63, 344, 186);
		pnlTurma.add(scrollPane_1);
		//JTextArea txtInforma��es
		txtInformacoes = new JTextArea();
		scrollPane_1.setViewportView(txtInformacoes);
//JLabel Horario Curso		
		JLabel lblHorrio = new JLabel("Hor\u00E1rio");
		lblHorrio.setBounds(370, 26, 46, 14);
		pnlTurma.add(lblHorrio);
		//JComboBox Hora inicio
		cbBoxHoraI = new JComboBox();
		cbBoxHoraI.setBounds(424, 23, 45, 20);
		pnlTurma.add(cbBoxHoraI);
		//Jlabel separa��o horario inicio
		JLabel label_1 = new JLabel(":");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(472, 25, 19, 14);
		pnlTurma.add(label_1);
		//JComboBox minuto inicio
		cbBoxMinutoI = new JComboBox();
		cbBoxMinutoI.setBounds(491, 23, 45, 20);
		pnlTurma.add(cbBoxMinutoI);
		//JLabel separador dos horarios
		JLabel label_3 = new JLabel("-");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(546, 25, 23, 14);
		pnlTurma.add(label_3);
		//JComboBox horario termino
		cbBoxHoraT = new JComboBox<String>();
		cbBoxHoraT.setBounds(579, 23, 45, 20);
		pnlTurma.add(cbBoxHoraT);
		//JLabel separador horario termino
		JLabel label_4 = new JLabel(":");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_4.setBounds(623, 25, 19, 14);
		pnlTurma.add(label_4);		
		//JComboBox minuto termino
		cbBoxMinutoT = new JComboBox<String>();
		cbBoxMinutoT.setBounds(643, 23, 45, 20);
		pnlTurma.add(cbBoxMinutoT);
		
//JPanel Professores
		JPanel pnlProfessores = new JPanel();
		pnlProfessores.setBorder(new LineBorder(new Color(171, 173, 179)));
		pnlProfessores.setBounds(10, 320, 245, 54);
		contentPane.add(pnlProfessores);
		//JLabel Professores
		JLabel lblProfesssores = new JLabel("Professores");
		lblProfesssores.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfesssores.setBounds(12, 13, 81, 27);
		lblProfesssores.setFont(new Font("Times New Roman", Font.BOLD, 14));
		//JLabel Vagas Professores
		JLabel lblVagas = new JLabel("Vagas:");
		lblVagas.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblVagas.setBounds(109, 20, 39, 14);
		//JSpinner spnProfessoresVagas
		spnProfessoresVagas = new JSpinner();
		spnProfessoresVagas.setBounds(158, 20, 64, 20);
		spnProfessoresVagas.setModel(new SpinnerNumberModel(0, 0, 50, 1));
		//JPanel Professores
		pnlProfessores.setLayout(null);
		pnlProfessores.add(lblVagas);
		pnlProfessores.add(spnProfessoresVagas);
		pnlProfessores.add(lblProfesssores);	
		
//JPanel Alunos		
		JPanel pnlAlunos = new JPanel();
		pnlAlunos.setBorder(new LineBorder(new Color(171, 173, 179)));
		pnlAlunos.setBounds(262, 320, 470, 54);
		contentPane.add(pnlAlunos);
		//JLabel Alunos
		JLabel lblAlunos = new JLabel("Alunos");
		lblAlunos.setBounds(32, 19, 43, 17);
		lblAlunos.setFont(new Font("Times New Roman", Font.BOLD, 14));
		//Jlabel Vagas Alunos
		JLabel lblVagasTotal = new JLabel("Vagas Total:");
		lblVagasTotal.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblVagasTotal.setBounds(122, 20, 86, 14);
		//JSpinner spnAlunosVagas
		spnAlunosVagas = new JSpinner();
		spnAlunosVagas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int aluNum= (int)spnAlunosVagas.getValue();
				int bolNum= (int)spnAlunosBolsas.getValue();
				
				if(bolNum > aluNum){
					bolNum = aluNum;
				}
				spnAlunosBolsas.setModel(new SpinnerNumberModel(bolNum,0,aluNum,1));
				
			}
		});
		spnAlunosVagas.setBounds(218, 18, 53, 20);
		spnAlunosVagas.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
		//Jlabel 
		JLabel label_bolsas = new JLabel("Bolsas:");
		label_bolsas.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_bolsas.setBounds(319, 20, 47, 14);
		//JSpinner spnAlunosBolsas
		spnAlunosBolsas = new JSpinner();
		spnAlunosBolsas.setBounds(376, 18, 53, 20);
		spnAlunosBolsas.setModel(new SpinnerNumberModel(0, 0, 0, 1));
		//JPanel Alunos
		pnlAlunos.setLayout(null);
		pnlAlunos.add(lblVagasTotal);
		pnlAlunos.add(spnAlunosVagas);
		pnlAlunos.add(lblAlunos);
		pnlAlunos.add(label_bolsas);
		pnlAlunos.add(spnAlunosBolsas);
		
//JPanel Curso
		JPanel pnlCurso = new JPanel();
		pnlCurso.setBorder(new LineBorder(new Color(171, 173, 179)));
		pnlCurso.setBounds(10, 380, 724, 211);
		contentPane.add(pnlCurso);
		pnlCurso.setLayout(null);
		//JLabel lblCurso
		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurso.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblCurso.setBounds(327, 0, 58, 20);
		pnlCurso.add(lblCurso);
//JLabel ID do Curso
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 20, 28, 16);
		pnlCurso.add(lblId);
		//JComboBox ID Curso
		cbBoxIDCurso = new JComboBox<String>();
		cbBoxIDCurso.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecionar"}));
		cbBoxIDCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sqlSelectedID = "cursos.id = "+cbBoxIDCurso.getSelectedItem();
				int ci = cbBoxIDCurso.getSelectedIndex();
				setCurso(sqlSelectedID,ci);
			}
		});
		cbBoxIDCurso.setBounds(70, 20, 111, 20);
		pnlCurso.add(cbBoxIDCurso);
//JLabel alerta ID ou Nome		
		lblIDNome = new JLabel("Selecione o ID ou Nome.");
		lblIDNome.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		lblIDNome.setBounds(206, 21, 150, 19);
		pnlCurso.add(lblIDNome);
//JLabel lblNome
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 47, 46, 14);
		pnlCurso.add(lblNome);
		//JComboBox Curso
		cbBoxCurso = new JComboBox<String>();
		cbBoxCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedCourse = " cursos.nome like '%"+(cbBoxCurso.getSelectedItem().toString())+"%'";
				int ci = cbBoxCurso.getSelectedIndex();
				setCurso(selectedCourse, ci);		
			}
		});
		cbBoxCurso.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecionar"}));
		cbBoxCurso.setBounds(10, 65, 375, 20);
		pnlCurso.add(cbBoxCurso);			
//JLabel Area
		JLabel label_6 = new JLabel("\u00C1rea:");
		label_6.setBounds(10, 96, 46, 14);
		pnlCurso.add(label_6);
		//JTextField Area
		txtArea = new JTextField();
		txtArea.setBounds(10, 110, 375, 20);
		pnlCurso.add(txtArea);
		txtArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtArea.setEditable(false);
		txtArea.setColumns(10);
//JLabel N�vel
		JLabel label_5 = new JLabel("N\u00EDvel:");
		label_5.setBounds(10, 154, 46, 14);
		pnlCurso.add(label_5);
		//JTextField N�vel
		txtNivel = new JTextField();
		txtNivel.setBounds(70, 150, 200, 20);
		pnlCurso.add(txtNivel);
		txtNivel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNivel.setEditable(false);
		txtNivel.setColumns(10);
//JLabel Dura��o
		JLabel lblDurao = new JLabel("Dura\u00E7\u00E3o:");
		lblDurao.setBounds(10, 189, 58, 14);
		pnlCurso.add(lblDurao);
		//JTextField Dura��o
		txtDuracao = new JTextField();
		txtDuracao.setBounds(70, 185, 58, 20);
		pnlCurso.add(txtDuracao);
		txtDuracao.setHorizontalAlignment(SwingConstants.CENTER);
		txtDuracao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDuracao.setEditable(false);
		txtDuracao.setColumns(10);
		//JLabel Dura��o Tipo
		lblDuracaoTipo = new JLabel(" ");
		lblDuracaoTipo.setBounds(146, 185, 94, 14);
		pnlCurso.add(lblDuracaoTipo);	
//JLabel Descri��o do Curso
		JLabel lblDescrioDoCurso = new JLabel("Descri\u00E7\u00E3o do Curso:");
		lblDescrioDoCurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrioDoCurso.setBounds(511, 23, 130, 14);
		pnlCurso.add(lblDescrioDoCurso);		
		//JScrollPane Descri��o do Curso
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(397, 39, 317, 164);
		pnlCurso.add(scrollPane);
		//JtextPane Descri��o do Curso
		txtDescricao = new JTextPane();
		txtDescricao.setEditable(false);
		scrollPane.setViewportView(txtDescricao);	
		
//JButton Salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fGD = f.getDigitos(txtID.getText());//formatando o id apenas numeros, sem letras e espa�os
				String dataI="", dataT ="";
				
				if (stCourse == 2){
					errorJOP("Turma 'FINALIZADA' n�o pode ser alterada!","       ATEN��O!");
				}else {
					if (fGD.equals("")){
						infJOP("Verifique o ID!");
					}else{
						txtID.setText(fGD);
						c=true;
					//testando combobox data de inicio
						String d= "Data Inicial incorreta!";
						if (!vIndex(cbBoxDiaI, 0)|| !vIndex(cbBoxMesI, 1) || !vIndex(cbBoxAnoI, 2))
							infJOP(d);
						else dataI = vDS[2]+"/"+vDS[1]+"/"+vDS[0];
					//testando combobox data de t�rmino
						d = "Data de T�rmino incorreta!";
						if (!vIndex(cbBoxDiaT, 3)||!vIndex(cbBoxMesT, 4)||!vIndex(cbBoxAnoT, 5))
							infJOP(d);
						else dataT = vDS[5]+"/"+vDS[4]+"/"+vDS[3];
					//verificar vetor dia da semana
						int contador =0;
						for(int cs: vetDias){
							contador++;
							if (cs == 1){
								contador--;
								break;
								} 
						}
						if(contador==6){
								c= false;
								infJOP("Dias da semana incorretos! Nenhum selecionado.");
							}
					// verifica o curso 
						if(idCurso==0){
							infJOP("Selecione o curso!");
							c=false;
						}
					// verifica professores e alunos
						vP[1] = (int) spnProfessoresVagas.getValue();
						vA[1] = (int) spnAlunosVagas.getValue();
						vAB[1] = (int) spnAlunosBolsas.getValue();
						if (vA[1] ==0 && vP[1]==0)
							infJOP("Verifique as vagas dos Professores e Alunos!");
					//verifica per�odo
						int periodo = cbBoxPeriodo.getSelectedIndex();
						if (periodo == 0){
							c=false;
							infJOP("Selecione o per�odo!");
						}
						if (c){
						//variaveis para coletar os dados antes de salvar ou atualizar no banco
							String investimento = spnValor.getValue().toString();
							String info = txtInformacoes.getText();
							
						//conectando com o banco e salvando os dados
							if (!objBD.conectaBD()){
								errorJOP(eBDJOP[0],eBDJOP[1]);
							} else {
								String sqlPesquisa = "select * from turmas where ID = "+txtID.getText();
								ResultSet objRes = objBD.consulta(sqlPesquisa);
								
								try{
									if (objRes.next()){
										String sqlAlterar = "update turmas set data_inicio ='"+dataI+"', data_termino = '"+dataT+"', periodo ="+periodo+", segunda ="+vetDias[0]+", terca = "+vetDias[1]+", quarta = "+vetDias[2]+", quinta = "+vetDias[3]+", sexta = "+vetDias[4]+", sabado ="+vetDias[5]+", investimento = '"+investimento+"', informacoes = '"+info+"', professores_vagas = "+vP[1]+", alunos_vagas = "+vA[1]+", alunos_bolsas_vagas = "+vAB[1]+", cursos_id ="+idCurso+" where id ="+txtID.getText();
										if (objBD.atualiza(sqlAlterar)){
											infJOP("Dados alterados com sucesso!");
											} else {
												errorJOP(objBD.mensagem(), "Erro ao atualizar no banco.");
												}
										} else {
											String sqlInserir = "insert into turmas (id,data_inicio,data_termino,periodo,segunda,terca,quarta,quinta,sexta,sabado,investimento,informacoes,professores_vagas,alunos_vagas,alunos_bolsas_vagas,cursos_id,estado) values ("+fGD+",'"+dataI+"','"+dataT+"',"+periodo+","+vetDias[0]+","+vetDias[1]+","+vetDias[2]+","+vetDias[3]+","+vetDias[4]+","+vetDias[5]+",'"+investimento+"','"+info+"',"+vP[1]+","+vA[1]+","+vAB[1]+","+idCurso+",0)";
											if (objBD.atualiza(sqlInserir)){
												infJOP("Turma inserida com sucesso!");
												txtStatus.setText("Curso Cadastrado.");
												txtStatus.setForeground(Color.GRAY);
												mtdStCourse(0);//valor default para o estado do curso (0)
												} else {
													errorJOP(objBD.mensagem(), "Erro ao inserir novo cadastro");
													}
												
										}
									//novo metodo
										// professores
									if (!objBD.conectaBD()){
										errorJOP("N�o foi poss�vel conectar com o banco id: 620.",objBD.mensagem());
										}else {

											insDelVag(vP[0],vP[1],"professores","","","","",spnProfessoresVagas);
										//alunos	
											insDelVag((vA[0]-vAB[0]),(vA[1]-vAB[1]),"alunos","bolsa","0","","",spnAlunosVagas);
											insDelVag(vAB[0],vAB[1],"alunos","bolsa","1","_bolsas","",spnAlunosBolsas);
											}
									} catch(SQLException f){
										errorJOP(objBD.mensagem(), "Erro");
										}
								showID();
								objBD.desconecta();
								}
						}//if c==true
					}//if id vazio
				}//if verifica estado da turma
				//atualiza o valor das vagas
				vP[0]= Integer.parseInt(spnProfessoresVagas.getValue().toString());
				vA[0]= Integer.parseInt(spnAlunosVagas.getValue().toString());
				vAB[0]= Integer.parseInt(spnAlunosBolsas.getValue().toString());
			}//action performed	
		});
		btnSalvar.setMnemonic('S');
		btnSalvar.setBounds(22, 602, 89, 23);
		contentPane.add(btnSalvar);
		
//JButton Excluir
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (stCourse == 2){//curso no estado 2 (FINALIZADA) n�o pode ser exclu�da
					errorJOP("Turma 'FINALIZADA' n�o pode ser exclu�da!","       ATEN��O!!!");
				}else {
					String delCurso = txtID.getText();
					int test = JOptionPane.showConfirmDialog(null, "Deseja excluir a Turma do Curso? \n ID: "+delCurso);
					if (test == 0){
						 if (!objBD.conectaBD()){
							 errorJOP(eBDJOP[0],eBDJOP[1]);
						 } else {
							
							 String sqlExcluir = "delete from turmas where id = '"+ txtID.getText()+"'";
							 if (objBD.atualiza(sqlExcluir)){
										limpar(true);
										txtStatus.setText("Curso Exclu�do.");
										txtStatus.setForeground(Color.RED);
										lblAlerta.setVisible(true);
							 } else {
								 errorJOP(objBD.mensagem(),"Erro");
							 }
							 showID();
							 objBD.desconecta();
						 }//conex�o com banco
					}//if teste de confirma��o para exclus�o
				}//verifica o estado da turma
				
				
			}//action performed
		});//action listener
		btnExcluir.setBounds(394, 602, 89, 23);
		contentPane.add(btnExcluir);
				
//JButton Limpar
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar(true);
			}
		});
		btnLimpar.setBounds(522, 602, 89, 23);
		contentPane.add(btnLimpar);
		
//JButton Sair
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				objBD.desconecta();
				dispose();
			}
		});
		btnSair.setBounds(621, 602, 89, 23);
		contentPane.add(btnSair);
		
//carregando combo box e id
		showID(); //Mostra o �ltimo id cadastrado no banco: order by id desc
		addDays();//Insere os dias do m�s nas datas de in�cio e t�rmino
		addYear();//insere os anos nas datas de in�cio e t�rmino
		addMonth();//insere os meses nas datas de in�cio e t�rmino
		addCourses();//carrega os dados do curso selecionado, nos campos do painel Curso
  		
	}//frm turmas *****************************************************************************************
	
// mostrar ultimo id
	private void showID(){
		String msgID="";
		if (!objBD.conectaBD()){
			errorJOP(eBDJOP[0],eBDJOP[1]);
			msgID = objBD.mensagem();
			} else {
				String ultID = "SELECT * FROM turmas ORDER BY id DESC LIMIT 1";
				ResultSet objRes= objBD.consulta(ultID);
				try	{
					if(objRes.first()){
						msgID = objRes.getString(1);
						}
					}catch (SQLException e) {
						errorJOP(objBD.mensagem(),"ERRO!");
						}
				objBD.desconecta();
				}
		txtUltimoID.setText(msgID);
	}
	
// limpar
	private void limpar(boolean lmr){
		clearCbBoxHoras();
		cbBoxPeriodo.setSelectedIndex(0);
		if(lmr){
			txtID.setText("");
			txtStatus.setText("");
			lblAlerta.setVisible(true); 
		} 
		 cbBoxDiaI.setSelectedIndex(0);
		 cbBoxMesI.setSelectedIndex(0);
		 cbBoxAnoI.setSelectedIndex(0);
		 cbBoxDiaT.setSelectedIndex(0);
		 cbBoxMesT.setSelectedIndex(0);
		 cbBoxAnoT.setSelectedIndex(0); 
		 rdbtnSegunda.setSelected(false);
		 rdbtnTerca.setSelected(false);
		 rdbtnQuarta.setSelected(false);
		 rdbtnQuinta.setSelected(false);
		 rdbtnSexta.setSelected(false);
		 rdbtnSabado.setSelected(false);
		 spnValor.setValue(0.00);
		 txtInformacoes.setText("");
		 spnProfessoresVagas.setValue(vP[0]=0);
		 spnAlunosVagas.setValue(vA[0]=0);
		 spnAlunosBolsas.setValue(vAB[0]=0);
		 mtdStCourse(-1);//Qualquer valor. N�o pode ser 0,1 e 2.
		 clearCourse();                                               
	}
	
//limpa painel curso
	private void clearCourse(){
	 	txtNivel.setText("");
	 	txtArea.setText("");
	 	txtDuracao.setText("");
	 	lblDuracaoTipo.setText("");
	 	txtDescricao.setText("");
	 	cbBoxCurso.setSelectedIndex(0);
	 	cbBoxIDCurso.setSelectedIndex(0);
	 	lblIDNome.setVisible(true);
	}
	
//metodo inserir os anos 
	private void addYear(){
		for (int a=2018; a<= 2118;a++){
			cbBoxAnoI.addItem(Integer.toString(a));
			cbBoxAnoT.addItem(Integer.toString(a));
		}
	}
	
//metodo inserir os dias
	private void addDays(){
		for (int d=31; d>0;d--){
			cbBoxDiaI.addItem(Integer.toString(d));
			cbBoxDiaT.addItem(Integer.toString(d));
		}
	}
// motodo insere zero esquerda
	private void zE(){
		
	}
	
// inserir mes
	private void addMonth(){
		for(String m: vetMes){
			cbBoxMesI.addItem(m);
			cbBoxMesT.addItem(m);
		}
	}
	
// metodo carrega cursos
	private void addCourses(){
		if(!objBD.conectaBD()){
			errorJOP(eBDJOP[0],eBDJOP[1]);
		} else{
			String sql = "SELECT * FROM CURSOS";
			ResultSet objResCursos = objBD.consulta(sql);
			try{
				while(objResCursos.next()){
					cbBoxIDCurso.addItem(objResCursos.getString("id"));
					cbBoxCurso.addItem(objResCursos.getString("nome"));
				}
			} catch(SQLException e){
				errorJOP(objBD.mensagem(),"Erro ao consultar Cursos!");
			}
		}
	}
	
//metodo rdbtn Dias da semana 
	private void rbS(JRadioButton j, int v){
		if (j.isSelected()){
			vetDias[v] = 1;
		} else{
			vetDias[v] = 0;
		}
	}
	
// method verifica index 
	private boolean vIndex(JComboBox<String> b, int vn){
		boolean vi = true;
		String adZ = "";
		if (b.getSelectedIndex() == 0){
			vi = c = false;
		} else {
			if (vn < 10){
				adZ= "0";
			}
			vDS[vn] = adZ+(b.getSelectedItem().toString()); 
		}
		return vi;
	}
	
// metodo que carrega informa��es do curso no painel Curso, quando selecionado
	private void setCurso(String etc, int index){
		
		if (index == 0){//se o item selecionado for default: "Selecionar"
			clearCourse();
		}else {
			lblIDNome.setVisible(false);
			if (!objBD.conectaBD()){
				errorJOP(eBDJOP[0],eBDJOP[1]);
			} else{
				String sqlLC = "select * from cursos, area, nivel where cursos.area_id = area.id and area.nivel_id = nivel.id and "+etc;     
				ResultSet objResSC = objBD.consulta(sqlLC);
				try{
					if(objResSC.next()){
							idCurso = objResSC.getInt("cursos.id");
							txtNivel.setText(objResSC.getString("nivel.nome"));
							txtArea.setText(objResSC.getString("area.nome"));
							txtDuracao.setText(objResSC.getString("duracao"));					
							lblDuracaoTipo.setText(duracaoTipo[Integer.parseInt(objResSC.getString("duracao_tipo"))]);
							txtDescricao.setText(objResSC.getString("descricao"));
							//gambiarras ?
							cbBoxIDCurso.setSelectedItem(objResSC.getString("cursos.id"));
							cbBoxCurso.setSelectedItem(objResSC.getString("cursos.nome"));
							//fim gambiarras ?
					}
				}catch(SQLException cbC){
					errorJOP("N�o foi poss�vel carregar informa��es do curso.", objBD.mensagem());
				}
			}
			objBD.desconecta();
		}
	}
	
//method para alterar visual do txtEstado
	private void mtdStCourse(int st){
		stCourse=st;
		String msg;
		Color cor;
		
		switch (stCourse){
		case 0: msg ="ABERTA"; cor = Color.GREEN;break;
		case 1: msg ="FECHADA"; cor = Color.BLUE;break;
		case 2: msg ="FINALIZADA"; cor = Color.RED;break;
		default: msg="   -   "; cor= Color.BLACK; ;break;
		}
		txtEstado.setText(msg);
		txtEstado.setForeground(cor);
	}


//metodo para criar, remover e alterar vagas de bolsas para alunos
	private void insDelVag(int x, int y, String strTbl, String insBsa, String insAlu, String upVga, String sqlDel, JSpinner spnRtn){
		int vi=x;//valor inicial da vaga
		boolean fail = false; 
		if (x<y){//x menor, insere no banco (y = valor atual)
			if (strTbl.equals("alunos")){
				insBsa = ","+insBsa;
				insAlu = ","+insAlu;
			}
			for(int i=x+1;i<=y;i++){//for para inserir novas vagas bolsas
				   String sqlAddVga = "insert into turmas_"+strTbl+"(turmas_id,vaga,"+strTbl+"_id"+insBsa+") values ("+fGD+","+i+",null"+insAlu+")";//string de inser��o
				   if(!objBD.atualiza(sqlAddVga)){//verifica se inseriu
					 errorJOP("Erro id:954 ao cadastrar vagas dos "+strTbl+"!",objBD.mensagem());//informa o erro 
					 fail=true;
					 break;
				 }else
					 vi++;
			}
		}else if(x>y){//x maior, remove do banco (y = valor atual). Reduziu n�mero de vagas
			if (strTbl.equals("alunos")){
				sqlDel = " and "+insBsa+" ="+insAlu;
			}
			for(int i=x;i>y;i--){//for para remover vagas
				String sqlDelVga = "delete from turmas_"+strTbl+" where turmas_id="+fGD+" and vaga="+i+sqlDel;//" and bolsa ="
				if(!objBD.atualiza(sqlDelVga)){
					errorJOP("N�o foi poss�vel reduzir as vagas dos "+strTbl+"!",objBD.mensagem());
					fail=true;
					break;
				} else{
					vi--;
				}		
			}
		}//else if
		if (fail){
			String sqlUpVga = "update turmas set "+strTbl+upVga+"_vagas = "+vi+" WHERE id ="+fGD;//cria string sql para atualizar o n�mero correto de vagas
			 if(!objBD.atualiza(sqlUpVga)){//verifica se o n�mero correto de vagas foi inserida
						 errorJOP("Ocorreu um erro ao sincronizar vagas dos "+strTbl+"!",objBD.mensagem());//informa que n�o foi inserido o n�mero correto de vagas
			 }
			 spnRtn.setValue(vi);//insere o valor que parou quando deu erro, no momento da inser��o ou remo��o
		}	
	}//ins del de vagas
		
//msg erro de conex�o com banco
	private void errorJOP(String eMsg,String tMsg){
		JOptionPane.showMessageDialog(null, eMsg,tMsg,JOptionPane.ERROR_MESSAGE);
	}
//msg informativa
	private void infJOP(String iMsg){
		JOptionPane.showMessageDialog(null,iMsg,"  Aten��o!!!  " ,JOptionPane.INFORMATION_MESSAGE);
	}
	
//JComboBox minutos e segundos
	private void addMin(JComboBox<String> cbT){
		for (int t=0;t <60;t++){
		String az =Integer.toString(t);
			if (t<10){
				az = "0"+t;				
			}
			cbT.addItem(az);
		}
	}
//JComboBox horas do per�odo
	private void addHour (int ind){
		int ci = 0;
		int ct = 0;
		
		if (ind==1){
			ci = 5;
			ct = 13;
		} else if(ind ==2){
			ci = 12;
			ct = 19;
		} else if (ind == 3){
			ci = 17;
			ct = 24;
		}
		
		for (int f =ci;f<=ct;f++){
			String fs=Integer.toString(f);
			if (f==24){
				fs = "00";
			}
			cbBoxHoraI.addItem(fs);
			cbBoxHoraT.addItem(fs);
		}
	}
//JComboBoxRemove all
	private void clearCbBoxHoras(){
		cbBoxHoraI.removeAllItems();
		cbBoxHoraT.removeAllItems();
		cbBoxMinutoI.removeAllItems();
		cbBoxMinutoT.removeAllItems();
	}
	
}// class
