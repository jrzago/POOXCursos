import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class frmCadastroAlunos extends JFrame {
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtCPF;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private bd objBD = null;
	private JTextField txtEmail;
	private int[] vetDia = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
	private String[] vetMes ={"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
	private JComboBox<Integer> cbBoxAno;
	private JComboBox<String> cbBoxMes;
	private JComboBox<Integer> cbBoxDia;
	private JLabel lblAlerta;
	private JLabel lblStatus;
	private JLabel lblUltimoID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCadastroAlunos frame = new frmCadastroAlunos();
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
	public frmCadastroAlunos() {
//JFrame Cadastramento e alteração dos Alunos		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\teste\\img\\Icon - 200 x 200.jpg"));
		this.objBD = new bd("POOX","root","");
		setTitle("Cadastrar e alterar Alunos ");
		setBounds(100, 100, 450, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//JLabelAlerta		
		lblAlerta = new JLabel("Ante\u00E7\u00E3o: Preencha o CPF para verificar o Status!");
		lblAlerta.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		lblAlerta.setBounds(95, 0, 259, 14);
		contentPane.add(lblAlerta);
		
//JLabel CPF
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(10, 22, 62, 14);
		contentPane.add(lblCPF);
		//JTextField CPF
		txtCPF = new JTextField();
		txtCPF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				lblAlerta.setVisible(false);
				
				if (!objBD.conectaBD()){
					JOptionPane.showMessageDialog(null,
							objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
				} else {
					String sqlPesquisa = "select *, day(nascimento), month(nascimento), year(nascimento) from alunos where CPF = '" + txtCPF.getText()+"'";
					ResultSet objRes = objBD.consulta(sqlPesquisa);
					try{
						if (objRes.next()){
							txtID.setText(objRes.getString("id"));
							txtNome.setText(objRes.getString("nome"));
							txtTelefone.setText(objRes.getString("telefone"));
							txtEmail.setText(objRes.getString("email"));
							cbBoxDia.setSelectedItem(Integer.parseInt(objRes.getString("day(nascimento)")));
							cbBoxMes.setSelectedIndex( (Integer.parseInt(objRes.getString("month(nascimento)"))-1));
							cbBoxAno.setSelectedItem(Integer.parseInt(objRes.getString("year(nascimento)")));
							lblStatus.setText("Cadastro Existente.");
							lblStatus.setForeground(Color.BLUE);
						} else {
							txtID.setText("");
							txtNome.setText("");
							txtTelefone.setText("");
							txtEmail.setText("");
							lblStatus.setText("Cadastrar Novo.");
							lblStatus.setForeground(Color.GREEN);
						}
						} catch (SQLException e){
							JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
							}
					objBD.desconecta();
					}
				}
			});
		txtCPF.setBounds(82, 19, 86, 20);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		
//JLabel Status		
		JLabel jlabel = new JLabel("Status:");
		jlabel.setBounds(215, 25, 46, 14);
		contentPane.add(jlabel);
		//lblStatus mensagem
		lblStatus = new JLabel("                     ");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblStatus.setBounds(269, 22, 145, 14);
		contentPane.add(lblStatus);
				
//JLabel ID
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(10, 53, 62, 14);
		contentPane.add(lblID);
		//JTextField ID
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(82, 47, 86, 20);
		contentPane.add(txtID);
		
//JLabel Sequancial ID	
		JLabel lblIdSequncial = new JLabel("\u00DAltimo ID:");
		lblIdSequncial.setBounds(215, 47, 62, 14);
		contentPane.add(lblIdSequncial);
		//JLabel Ultimo ID
		lblUltimoID = new JLabel("");
		lblUltimoID.setHorizontalAlignment(SwingConstants.CENTER);
		lblUltimoID.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUltimoID.setBounds(279, 47, 79, 14);
		showID();
		contentPane.add(lblUltimoID);
		
//JLabel Nome
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 78, 62, 14);
		contentPane.add(lblNome);
		//JTextField Nome
		txtNome = new JTextField();
		txtNome.setBounds(82, 75, 348, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
//JLabel Telefone
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 109, 62, 14);
		contentPane.add(lblTelefone);
		//JTextField Telefone
		txtTelefone = new JTextField();
		txtTelefone.setBounds(82, 106, 115, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
//JLabel Email
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 140, 62, 14);
		contentPane.add(lblEmail);
		//JTextField Email
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(82, 137, 179, 20);
		contentPane.add(txtEmail);
		
//JLabel Nascimento
		JLabel lblNascimento = new JLabel("Data de Nascimento:");
		lblNascimento.setBounds(10, 176, 118, 14);
		contentPane.add(lblNascimento);	
		//JComboBox	Dia		
		cbBoxDia = new JComboBox<Integer>();
		cbBoxDia.setToolTipText("Dia");
		cbBoxDia.setBounds(138, 173, 62, 20);
		for (int d: vetDia){
			cbBoxDia.addItem(d);
		}
		contentPane.add(cbBoxDia);
		//JComboBox Mês			
		cbBoxMes = new JComboBox<String>();
		cbBoxMes.setToolTipText("M\u00EAs");
		cbBoxMes.setBounds(215, 173, 98, 20);
		for	(String m: vetMes){
			cbBoxMes.addItem(m);
		}
		contentPane.add(cbBoxMes);
		//JComboBox Ano			
		cbBoxAno = new JComboBox<Integer>();
		cbBoxAno.setToolTipText("Ano");
		cbBoxAno.setBounds(324, 173, 106, 20);
		for (int y=1905; y<=2018;y++){
			cbBoxAno.addItem(y);
		}
		contentPane.add(cbBoxAno);
		
//JButton Salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((txtCPF.getText().equals(""))){//verifica se cpf == vazio
					JOptionPane.showMessageDialog(null, "Dados inválidos","Erro",JOptionPane.ERROR_MESSAGE);
				}else if (!objBD.conectaBD()){
					JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro",JOptionPane.ERROR_MESSAGE);
					} else {
						String sqlPesquisa = "select * from alunos where CPF ='"+txtCPF.getText()+"'";
						ResultSet objRes = objBD.consulta(sqlPesquisa);
						try{
							if (objRes.next()){
								String sqlAlterar = "update alunos set ID = "+txtID.getText()+", nome = '" +txtNome.getText() + "', telefone =  '"+txtTelefone.getText() + "', email = '" +txtEmail.getText() + "', nascimento = '"+nascDate()+"' where CPF = '"+txtCPF.getText()+"'";
								if (objBD.atualiza(sqlAlterar)){
									JOptionPane.showMessageDialog(null,"Dados alterados com sucesso!", "Aviso",JOptionPane.INFORMATION_MESSAGE);
									
									} else {
										JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro ao atualizar cadastro",JOptionPane.ERROR_MESSAGE);
										}
								} else {
									String sqlInserir = "insert into alunos (id,cpf,nome,telefone,email,nascimento) values("+txtID.getText()+",'"+txtCPF.getText()+"','"+txtNome.getText()+"','"+txtTelefone.getText()+"','"+txtEmail.getText()+"','"+nascDate()+"')";
									if (objBD.atualiza(sqlInserir)){
										JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
										lblStatus.setText("Aluno Cadastrado.");
										lblStatus.setForeground(Color.GRAY);
										} else {
											JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
											}
									}
							showID();
							} catch(SQLException f){
								JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
								}
						objBD.desconecta();
						}
			}
		});
		btnSalvar.setMnemonic('S');
		btnSalvar.setBounds(25, 227, 89, 23);
		contentPane.add(btnSalvar);
		
//JButton Excluir
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setMnemonic('X');
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String delCPF = txtCPF.getText();// salva cpf para exibir o cpf excluido
			
				int test = JOptionPane.showConfirmDialog(null, "Deseja excluir o cadastro? \nCPF: "+delCPF);
				if (test == 0){
					if (!objBD.conectaBD()){
						 JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro", JOptionPane.ERROR_MESSAGE);
					 } else {
						 String sqlExcluir = "delete from alunos where CPF = '"+ txtCPF.getText()+"'";
						 if (objBD.atualiza(sqlExcluir)){
							 Limpar();
							 lblStatus.setText("Aluno Excluído.");
							 lblStatus.setForeground(Color.RED);
							 JOptionPane.showMessageDialog(null, "Cadastro excluido! \n CPF: "+delCPF);
						 } else {
							 JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro", JOptionPane.ERROR_MESSAGE);
						 }
						 objBD.desconecta();
					 }
					showID();
					objBD.desconecta();
				}
			
			}
		});
		btnExcluir.setBounds(159, 227, 89, 23);
		contentPane.add(btnExcluir);
		
//JButton Limpar
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setMnemonic('L');
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpar();
			}
		});
		btnLimpar.setBounds(250, 227, 89, 23);
		contentPane.add(btnLimpar);
		
//JButton Sair
		JButton btnSair = new JButton("Sair");
		btnSair.setMnemonic('R');
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				objBD.desconecta();
				dispose();
			}
		});
		btnSair.setBounds(341, 227, 89, 23);
		contentPane.add(btnSair);	
		
		
	}//form alunos
	
	private void Limpar(){
		txtID.setText("");
		 txtCPF.setText("");
		 txtNome.setText("");
		 txtTelefone.setText("");
		 txtEmail.setText("");
		 lblStatus.setText("");
		 lblAlerta.setVisible(true);
	}
	
// formata date para o banco
	private String nascDate(){
		int mes = cbBoxMes.getSelectedIndex()+1;
		String strMes= Integer.toString(mes);
		if (mes<10){
			strMes = ""+0+mes;
		}
		return strMes = cbBoxAno.getSelectedItem()+"-"+strMes+"-"+cbBoxDia.getSelectedItem();
	}
	
// mostrar ultimo id
	private void showID(){
		String msgID="";
		if (!objBD.conectaBD()){
			msgID = objBD.mensagem();
			} else {
				String ultID = "SELECT * FROM alunos ORDER BY id DESC LIMIT 1";
				ResultSet objRes= objBD.consulta(ultID);
				try	{
					if(objRes.first()){
						msgID = objRes.getString(1);
						}
					}catch (SQLException e) {
						JOptionPane.showMessageDialog(null, objBD.mensagem(),"ERRO!",JOptionPane.ERROR_MESSAGE);
						}
				}
		lblUltimoID.setText(msgID);
		objBD.desconecta();
	}
	
}//class alunos
