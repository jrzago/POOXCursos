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
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class frmCadastroCursos extends JFrame {
	private JPanel contentPane;
	private bd objBD = null;
	private JTextField txtID;
	private JTextField txtCurso;
	private JTextField txtDuracao;
	private JLabel lblUltimoID;
	private JLabel lblAlerta;
	private JTextPane txtDescricao;
	private JLabel lblStatus;
	private JComboBox<String> cbBoxArea;
	private int[] vetArea;
	private int duracao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCadastroCursos frame = new frmCadastroCursos();
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
	public frmCadastroCursos() {
//JFrame Cadastro de Cursos
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\teste\\img\\Icon - 200 x 200.jpg"));
		this.objBD = new bd("POOX","root","");
		setTitle("Cadastrar Cursos ");
		setBounds(100, 100, 450, 330);
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
		lblID.setBounds(25, 28, 30, 14);
		contentPane.add(lblID);
		//JTextField ID
		txtID = new JTextField();
		txtID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				lblAlerta.setVisible(false);
				if (!objBD.conectaBD()){
					JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
				} else {
					String sqlPesquisa = "SELECT * FROM cursos where ID = " + txtID.getText();
					ResultSet objRes = objBD.consulta(sqlPesquisa);
					try{
						if (objRes.next()){
							txtCurso.setText(objRes.getString("nome"));
							txtDuracao.setText(objRes.getString("duracao"));
							txtDescricao.setText(objRes.getString("descricao"));
							lblStatus.setText("ID cadastrado.");
							lblStatus.setForeground(Color.BLUE);
							cursoArea();
						} else {
							txtCurso.setText("");
							txtDuracao.setText("");
							txtDescricao.setText("");
							lblStatus.setText("ID novo.");
							lblStatus.setForeground(Color.GREEN);
						}
					} catch (SQLException e){
						JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);;
					}	
					showID();
					objBD.desconecta();
				}
			}
		});
		txtID.setColumns(10);
		txtID.setBounds(84, 25, 86, 20);
		contentPane.add(txtID);
		
//JLabel Sequencial ID	
		JLabel lblIdSequncial = new JLabel("\u00DAltimo ID:");
		lblIdSequncial.setBounds(208, 19, 62, 14);
		contentPane.add(lblIdSequncial);
		//JLabel Ultimo ID
		lblUltimoID = new JLabel("");
		lblUltimoID.setHorizontalAlignment(SwingConstants.CENTER);
		lblUltimoID.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUltimoID.setBounds(267, 19, 79, 14);
		showID();
		contentPane.add(lblUltimoID);	
		
//JLabel Status		
		JLabel jlabel = new JLabel("Status:");
		jlabel.setBounds(208, 44, 46, 14);
		contentPane.add(jlabel);
		//lblStatus mensagem
		lblStatus = new JLabel("                     ");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblStatus.setBounds(264, 43, 145, 14);
		contentPane.add(lblStatus);
		
//JLabel Curso
		JLabel lblCurso = new JLabel("Nome do Curso:");
		lblCurso.setBounds(25, 65, 99, 14);
		contentPane.add(lblCurso);
		//JTextField Curso
		txtCurso = new JTextField();
		txtCurso.setBounds(25, 80, 409, 20);
		contentPane.add(txtCurso);
		txtCurso.setColumns(10);
		
//JLabel Duração
		JLabel lblDuracao = new JLabel("Dura\u00E7\u00E3o:");
		lblDuracao.setHorizontalAlignment(SwingConstants.LEFT);
		lblDuracao.setBounds(25, 111, 89, 14);
		contentPane.add(lblDuracao);
		//JTextField Duração
		txtDuracao = new JTextField();
		txtDuracao.setColumns(10);
		txtDuracao.setBounds(84, 111, 72, 20);
		contentPane.add(txtDuracao);
//JComboBox Duracao		
		JComboBox<String> cbBoxDuracao = new JComboBox<String>();
		cbBoxDuracao.setModel(new DefaultComboBoxModel<String>(new String[] {"Horas", "Semestres", "Anos"}));
		cbBoxDuracao.setBounds(171, 111, 127, 20);
		contentPane.add(cbBoxDuracao);		
		
		
//JLabel Area		
		JLabel labelArea = new JLabel("Área:");
		labelArea.setBounds(25, 139, 46, 14);
		contentPane.add(labelArea);
		//JComboBox Area
		cbBoxArea = new JComboBox<String>();
		cbBoxArea.setBounds(84, 136, 350, 20);
		boxAreas();
		objBD.desconecta();
		contentPane.add(cbBoxArea);
		
//JLabel Descricao		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setBounds(25, 162, 62, 14);
		contentPane.add(lblDescricao);
		//JScrollPane para JTextPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 177, 409, 84);
		contentPane.add(scrollPane);
		//JTextPane Descricao
		txtDescricao = new JTextPane();
		txtDescricao.setToolTipText("Descri\u00E7\u00E3o de at\u00E9 700 caracteres.");
		txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDescricao.setForeground(Color.GRAY);
		scrollPane.setViewportView(txtDescricao);
		
		
		
//JButton Salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!objBD.conectaBD()){
					JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro ao conectar.!",JOptionPane.ERROR_MESSAGE);
				} else {

					String sqlPesquisa = "select * from cursos where ID ="+txtID.getText();
					ResultSet objRes = objBD.consulta(sqlPesquisa);
					try{
						if (objRes.next()){
							String sqlAlterar = "update cursos set ID = "+txtID.getText()+", nome ='"+txtCurso.getText()+"', duracao ="+txtDuracao.getText()+", area_id ="+vetArea[cbBoxArea.getSelectedIndex()]+", descricao = '"+txtDescricao.getText()+"' where ID = '"+txtID.getText()+"'";
							if (objBD.atualiza(sqlAlterar)){
								JOptionPane.showMessageDialog(null,"Dados alterados com sucesso!", "Aviso",JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro ao atualizar no banco.",JOptionPane.ERROR_MESSAGE);
									}
							} else {
								String sqlInserir = "insert into cursos (id,nome,duracao,area_id,descricao) values("+txtID.getText()+",'"+txtCurso.getText()+"',"+txtDuracao.getText()+", "+vetArea[cbBoxArea.getSelectedIndex()]+", '"+txtDescricao.getText()+"')";
								if (objBD.atualiza(sqlInserir)){
									JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
									lblStatus.setText("Curso Cadastrado.");
									lblStatus.setForeground(Color.GRAY);
									} else {
										JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro ao inserir novo cadastro",JOptionPane.ERROR_MESSAGE);
										}
								}
						} catch(SQLException f){
							JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
							}
					showID();
					objBD.desconecta();
					}
			}
		});
		btnSalvar.setMnemonic('S');
		btnSalvar.setBounds(25, 267, 89, 23);
		contentPane.add(btnSalvar);
		
//JButton Excluir
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String delCurso = txtCurso.getText();
				int test = JOptionPane.showConfirmDialog(null, "Deseja excluir o curso? \nCurso: "+delCurso);
				if ( test == 0){
				
					 if (!objBD.conectaBD()){
						 JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro", JOptionPane.ERROR_MESSAGE);
					 } else {
						
						 String sqlExcluir = "delete from cursos where ID = "+ txtID.getText();
						 if (objBD.atualiza(sqlExcluir)){
									Limpar();
									lblStatus.setText("Curso Excluído.");
									lblStatus.setForeground(Color.RED);
						 } else {
							 JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro", JOptionPane.ERROR_MESSAGE);
						 }
						 showID();
						 objBD.desconecta();
					 }
				}
			}
		});
		btnExcluir.setBounds(158, 267, 89, 23);
		contentPane.add(btnExcluir);
		
//JButton Limpar
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpar();
			}
		});
		btnLimpar.setBounds(249, 267, 89, 23);
		contentPane.add(btnLimpar);
		
//JButton Sair
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				objBD.desconecta();
				dispose();
			}
		});
		btnSair.setBounds(345, 267, 89, 23);
		contentPane.add(btnSair);
					
		
	}//form cursos
//função limpar os campos
	private void Limpar(){
		txtID.setText("");
		txtCurso.setText("");
		txtDuracao.setText("");
		txtDescricao.setText("");
		lblStatus.setText("");
		lblAlerta.setVisible(true);
		}
	
// mostrar ultimo id
	private void showID(){
		String msgID="";
		if (!objBD.conectaBD()){
			msgID = objBD.mensagem();
			} else {
				String ultID = "SELECT * FROM cursos ORDER BY id DESC LIMIT 1";
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

//metodo cbbox mostrar Area
	private void cursoArea(){
		if (!objBD.conectaBD()){
			JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro ao conectar com o banco!",JOptionPane.ERROR_MESSAGE);
		}else{
			String sqlAreaID = "select * from cursos, area where cursos.area_id = area.id and cursos.id = "+txtID.getText();
			ResultSet objRes = objBD.consulta(sqlAreaID);
			try{
				if (objRes.next()){
					cbBoxArea.setSelectedItem(objRes.getString(7));
				}
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro de consulta!",JOptionPane.ERROR_MESSAGE);
			}
			objBD.desconecta();
		}
	}
	
//inserir Areas no JComboBox Areas
	private void boxAreas(){
		if (!objBD.conectaBD()){
			JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro de Conexão!",JOptionPane.ERROR_MESSAGE);
		}else{
			String sqlAreaCount = "select count(*) from area";
			ResultSet objResCount = objBD.consulta(sqlAreaCount);
			try{
				if(objResCount.next()){
					vetArea = new int[(objResCount.getInt(1))];
				}	
			}catch(SQLException c){
				JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro de busca Area!",JOptionPane.ERROR_MESSAGE);
				}
			String sqlArea = "select * from Area order by nome";
			ResultSet objRes = objBD.consulta(sqlArea);
			int i = 0;
			try{
				while (objRes.next()){
					vetArea[i]= objRes.getInt(1);
					cbBoxArea.addItem(objRes.getString(2));
					i++;
				}
			} catch (SQLException e){
				JOptionPane.showMessageDialog(null, objBD.mensagem(),"ERRO ao adicionar item Area!",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}//class cursos
