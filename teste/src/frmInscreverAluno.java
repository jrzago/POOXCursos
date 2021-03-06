import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSpinner;

public class frmInscreverAluno extends JFrame {
	private JPanel contentPane;
	private bd objBD = null;
	private DefaultTableModel tblPesquisa;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtSegundaTeraQuarta;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmInscreverAluno frame = new frmInscreverAluno();
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
	public frmInscreverAluno() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\teste\\img\\Icon - 200 x 200.jpg"));
		this.objBD = new bd("POOX","root","");
		setTitle("Inscri��o de Alunos");
		setBounds(100, 100, 750, 588);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlTurma = new JPanel();
		pnlTurma.setBorder(new LineBorder(new Color(51, 204, 51)));
		pnlTurma.setBounds(10, 11, 714, 257);
		contentPane.add(pnlTurma);
		pnlTurma.setLayout(null);
		
		JLabel lblTurmas = new JLabel("Turma");
		lblTurmas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurmas.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTurmas.setBounds(307, 2, 98, 14);
		pnlTurma.add(lblTurmas);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 21, 375, 127);
		pnlTurma.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("N\u00EDvel:");
		lblNewLabel_1.setBounds(10, 14, 42, 14);
		panel.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(49, 11, 155, 20);
		panel.add(comboBox);
		
		JLabel lblrea = new JLabel("\u00C1rea:");
		lblrea.setBounds(10, 45, 46, 14);
		panel.add(lblrea);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(49, 39, 204, 20);
		panel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(49, 68, 319, 20);
		panel.add(comboBox_2);
		
		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setBounds(10, 71, 46, 14);
		panel.add(lblCurso);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 99, 42, 14);
		panel.add(lblId);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(49, 96, 123, 20);
		panel.add(comboBox_3);
		
		JLabel lblSelecionarTurma = new JLabel("Selecionar Turma");
		lblSelecionarTurma.setBounds(10, 9, 99, 14);
		pnlTurma.add(lblSelecionarTurma);
		lblSelecionarTurma.setFont(new Font("Trebuchet MS", Font.ITALIC, 11));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(395, 21, 309, 127);
		pnlTurma.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("In\u00EDcio:");
		lblNewLabel_2.setBounds(10, 11, 46, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblTrmino = new JLabel("T\u00E9rmino:");
		lblTrmino.setBounds(172, 11, 46, 14);
		panel_1.add(lblTrmino);
		
		JLabel lblPerodo = new JLabel("Per\u00EDodo:");
		lblPerodo.setBounds(10, 36, 46, 14);
		panel_1.add(lblPerodo);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("13/04/2018");
		textField.setEditable(false);
		textField.setBounds(53, 8, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(218, 8, 86, 20);
		textField_1.setEditable(false);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(53, 36, 86, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDias = new JLabel("Dias da Semana:");
		lblDias.setBounds(105, 61, 86, 14);
		panel_1.add(lblDias);
		
		txtSegundaTeraQuarta = new JTextField();
		txtSegundaTeraQuarta.setEditable(false);
		txtSegundaTeraQuarta.setHorizontalAlignment(SwingConstants.CENTER);
		txtSegundaTeraQuarta.setText("Segunda, Ter\u00E7a, Quarta, Quinta, Sexta, S\u00E1bado");
		txtSegundaTeraQuarta.setBounds(10, 76, 288, 20);
		panel_1.add(txtSegundaTeraQuarta);
		txtSegundaTeraQuarta.setColumns(10);
		
		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoras.setBounds(10, 107, 46, 14);
		panel_1.add(lblHoras);
		
		JLabel lblInformaes = new JLabel("Informa\u00E7\u00F5es das aulas");
		lblInformaes.setFont(new Font("Trebuchet MS", Font.ITALIC, 11));
		lblInformaes.setBounds(395, 9, 128, 14);
		pnlTurma.add(lblInformaes);
		
		JPanel pnlAluno = new JPanel();
		pnlAluno.setBorder(new LineBorder(new Color(171, 173,179)));
		pnlAluno.setBounds(10, 279, 714, 259);
		contentPane.add(pnlAluno);
		pnlAluno.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aluno");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(337, 11, 58, 14);
		pnlAluno.add(lblNewLabel);
		
		

	}
}
