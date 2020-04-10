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
import javax.swing.JComboBox;

public class frmCadastroAreaCurso extends JFrame {
	private JPanel contentPane;
	private bd objBD = null;
	private JTextField txtID;
	private JTextField txtArea;
	private JLabel lblAlerta;
	private JLabel lblStatus;
	private JLabel lblUltimoID;
	private JComboBox<String> cbBoxNivel;
	private int[] vetNivel;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCadastroAreaCurso frame = new frmCadastroAreaCurso();
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
	public frmCadastroAreaCurso() {
//JFrame cadastrar Tema		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\teste\\img\\Icon - 200 x 200.jpg"));
		this.objBD = new bd("POOX","root","");
		setTitle("Cadastrar Area dos Cursos");
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
					String sqlPesquisa = "SELECT * FROM area, nivel WHERE area.nivel_id = nivel.id and area.id ="+txtID.getText();
					ResultSet objRes = objBD.consulta(sqlPesquisa);
					try{
						if (objRes.next()){
							txtArea.setText(objRes.getString(2));
							lblStatus.setText("ID cadastrado.");
							lblStatus.setForeground(Color.BLUE);
							cbBoxNivel.setSelectedItem(objRes.getString(5));
						} else {
							txtArea.setText("");
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
		
//JLabel Area
		JLabel lblDisciplina = new JLabel("Nome do Area:");
		lblDisciplina.setBounds(25, 83, 114, 14);
		contentPane.add(lblDisciplina);
		//JTextField Area
		txtArea = new JTextField();
		txtArea.setBounds(25, 108, 342, 20);
		contentPane.add(txtArea);
		txtArea.setColumns(10);
		
//JLabel Nivel		
		JLabel lblNivel = new JLabel("Nivel:");
		lblNivel.setBounds(25, 166, 46, 14);
		contentPane.add(lblNivel);
		//JComboBox Nivel		
		cbBoxNivel = new JComboBox<String>();
		cbBoxNivel.setBounds(86, 163, 201, 20);
		if (!objBD.conectaBD()){
			JOptionPane.showMessageDialog(null, "Falha na conexão. Erro ao conectar com o banco!","ERRO de Conexão!",JOptionPane.ERROR_MESSAGE);
		} else {
			//consultando quantidade de registros da tabela Nivel
			String sqlNivelCount = "SELECT COUNT(*) FROM nivel";
			ResultSet objResNivelCount = objBD.consulta(sqlNivelCount);
			try {
				if(objResNivelCount.next()){
					vetNivel = new int[objResNivelCount.getInt(1)];
				}
				}catch(SQLException d){
					JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro de consulta Count Nivel!",JOptionPane.ERROR_MESSAGE);
					}
			
			String sqlNivel = "SELECT * FROM NIVEL ORDER BY nome";
			ResultSet objResNivel = objBD.consulta(sqlNivel);
			int c=0;
			try	{
				while (objResNivel.next()){
					vetNivel[c] = objResNivel.getInt(1);
					cbBoxNivel.addItem(objResNivel.getString(2));
					c++;
				}
			}catch (SQLException e){
				JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro ao acessar o banco!",JOptionPane.ERROR_MESSAGE);
			}
			objBD.desconecta();
		}
		contentPane.add(cbBoxNivel);
		
//JButton Salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int getCbBox = vetNivel[cbBoxNivel.getSelectedIndex()];
				if (!objBD.conectaBD()){
					JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro",JOptionPane.ERROR_MESSAGE);
				} else {
					String sqlPesquisa = "SELECT * FROM area WHERE id ="+txtID.getText();
					ResultSet objRes = objBD.consulta(sqlPesquisa);
					try{
						if (objRes.next()){
							String sqlAlterar = "UPDATE area SET id ="+txtID.getText()+", nome='"+txtArea.getText()+"', nivel_id ="+getCbBox+"  WHERE id = "+txtID.getText();
							if (objBD.atualiza(sqlAlterar)){
								JOptionPane.showMessageDialog(null,"Dados alterados com sucesso!", "Aviso",JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
									}
							} else {
								String sqlInserir = "INSERT INTO area (id,nome,nivel_id) VALUES("+txtID.getText()+",'"+txtArea.getText()+"',"+getCbBox+")";
								if (objBD.atualiza(sqlInserir)){
									JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
									lblStatus.setText("Tema Cadastrado.");
									lblStatus.setForeground(Color.GRAY);
									} else {
										JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
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
				
				String delArea = txtArea.getText();
				
				int test = JOptionPane.showConfirmDialog(null, "Deseja excluir? \nArea: "+delArea);
				if ( test == 0){
					 if (!objBD.conectaBD()){
						 JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro", JOptionPane.ERROR_MESSAGE);
					 } else {
						 String sqlExcluir = "DELETE FROM area WHERE id ="+ txtID.getText();
						 if (objBD.atualiza(sqlExcluir)){
									Limpar();
									lblStatus.setText("Area excluída.");
									lblStatus.setForeground(Color.RED);
									} else {
										JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro", JOptionPane.ERROR_MESSAGE);
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
		txtArea.setText("");
		lblStatus.setText("");
		lblAlerta.setVisible(true);
	}
	
// mostrar ultimo id	
	private void showID(){
		String msgID="";
		if (!objBD.conectaBD()){
			msgID = objBD.mensagem();
			} else {
				String ultID = "SELECT * FROM area ORDER BY id DESC LIMIT 1";//orde o id do ultimo ao primeiro
				ResultSet objRes= objBD.consulta(ultID);
				try	{
					if(objRes.first()){//pega o primeiro resultado, que seria o ultimo id
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
