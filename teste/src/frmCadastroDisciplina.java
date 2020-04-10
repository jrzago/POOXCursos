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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class frmCadastroDisciplina extends JFrame {
	private JPanel contentPane;
	private bd objBD = null;
	private JTextField txtID;
	private JTextField txtDisciplina;
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
					frmCadastroDisciplina frame = new frmCadastroDisciplina();
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
	public frmCadastroDisciplina() {
//JFrame cadastrar Disciplina		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\teste\\img\\Icon - 200 x 200.jpg"));
		this.objBD = new bd("POOX","root","");
		setTitle("Cadastrar Disciplinas para Professores");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//JLabelAlerta		
		lblAlerta = new JLabel("Ante\u00E7\u00E3o: Preencha o ID  para verificar o Status!");
		lblAlerta.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblAlerta.setBounds(95, 0, 259, 14);
		contentPane.add(lblAlerta);
		
		
//JLabel ID
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(25, 40, 62, 14);
		contentPane.add(lblID);
		//JTextField ID
		txtID = new JTextField();
		txtID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!objBD.conectaBD()){
					JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
				} else {
					String sqlPesquisa = "select * from disciplina where ID = " + txtID.getText();
					ResultSet objRes = objBD.consulta(sqlPesquisa);
					try{
						if (objRes.next()){
							txtDisciplina.setText(objRes.getString(2));
							lblStatus.setText("ID cadastrado.");
							lblStatus.setForeground(Color.BLUE);
						} else {
							txtDisciplina.setText("");
							lblStatus.setText("ID novo.");
							lblStatus.setForeground(Color.GREEN);
						}
					} catch (SQLException e){
						JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
						}
					showID();
					objBD.desconecta();
					lblAlerta.setVisible(false);
					}
				}
			});
		txtID.setColumns(10);
		txtID.setBounds(92, 37, 86, 20);
		contentPane.add(txtID);
		
//JLabel Sequencial ID	
		JLabel lblIdSequncial = new JLabel("\u00DAltimo ID:");
		lblIdSequncial.setBounds(208, 31, 62, 14);
		contentPane.add(lblIdSequncial);
		//JLabel Ultimo ID
		lblUltimoID = new JLabel("");
		lblUltimoID.setHorizontalAlignment(SwingConstants.CENTER);
		lblUltimoID.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUltimoID.setBounds(267, 31, 79, 14);
		showID();
		contentPane.add(lblUltimoID);	
		
//JLabel Status		
		JLabel jlabel = new JLabel("Status:");
		jlabel.setBounds(208, 56, 46, 14);
		contentPane.add(jlabel);
		//lblStatus mensagem
		lblStatus = new JLabel("                     ");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblStatus.setBounds(264, 55, 145, 14);
		contentPane.add(lblStatus);
		
		//JLabel Disciplina
		JLabel lblDisciplina = new JLabel("Nome da Disciplina:");
		lblDisciplina.setBounds(25, 83, 114, 14);
		contentPane.add(lblDisciplina);
		//JTextField Disciplina
		txtDisciplina = new JTextField();
		txtDisciplina.setBounds(25, 108, 342, 20);
		contentPane.add(txtDisciplina);
		txtDisciplina.setColumns(10);
		
		
		//JButton Salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!objBD.conectaBD()){
					JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro",JOptionPane.ERROR_MESSAGE);
				} else {
					String sqlPesquisa = "select * from disciplina where ID ="+txtID.getText();
					ResultSet objRes = objBD.consulta(sqlPesquisa);
					try{
						if (objRes.next()){
							String sqlAlterar = "update disciplina set ID = "+txtID.getText()+", nome = '" +txtDisciplina.getText() + "' where ID = "+txtID.getText()+"";
							if (objBD.atualiza(sqlAlterar)){
								JOptionPane.showMessageDialog(null,"Dados alterados com sucesso!", "Aviso",JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
									}
							} else {
								String sqlInserir = "insert into disciplina (id,nome) values("+txtID.getText()+",'"+txtDisciplina.getText()+"')";
								if (objBD.atualiza(sqlInserir)){
									JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
									lblStatus.setText("Disciplina Cadastrada.");
									lblStatus.setForeground(Color.GRAY);
									} else {
										JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro ao cadastrar!",JOptionPane.ERROR_MESSAGE);
										}
								}
						} catch(SQLException f){
							JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
							}
					}
				showID();
				}
			});
		btnSalvar.setMnemonic('S');
		btnSalvar.setBounds(25, 227, 89, 23);
		contentPane.add(btnSalvar);
		
//JButton Excluir
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String delDisc = txtDisciplina.getText();
				
				int test = JOptionPane.showConfirmDialog(null, "Deseja excluir a disciplina? \nDisciplina: "+delDisc);
				if ( test == 0){
					 if (!objBD.conectaBD()){
						 JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro", JOptionPane.ERROR_MESSAGE);
					 } else {
						 String sqlExcluir = "delete from disciplina where id = "+ txtID.getText();
						 if (objBD.atualiza(sqlExcluir)){
									Limpar();
									lblStatus.setText("Disciplina Exclu�da.");
									lblStatus.setForeground(Color.RED);
									} else {
										JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro, Disciplina em uso!", JOptionPane.ERROR_MESSAGE);
										}
						 }
					 showID();
					 }
				}
			
			});
		btnExcluir.setBounds(155, 227, 89, 23);
		contentPane.add(btnExcluir);
		
		//JButton Limpar
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpar();
			}
		});
		btnLimpar.setBounds(250, 227, 89, 23);
		contentPane.add(btnLimpar);
		
		//JButton Sair
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSair.setBounds(345, 227, 89, 23);
		contentPane.add(btnSair);	
		
	}//frmDisciplina
	
	private void Limpar(){
		txtID.setText("");
		txtDisciplina.setText("");
		lblStatus.setText("");
		lblAlerta.setVisible(true);
	}
	
// mostrar ultimo id	
	private void showID(){
		String msgID="";
		if (!objBD.conectaBD()){
			msgID = objBD.mensagem();
			} else {
				String ultID = "SELECT * FROM disciplina ORDER BY id DESC LIMIT 1";
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

}//class frmDisciplina
