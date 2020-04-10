import java.awt.EventQueue;
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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.Toolkit;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class frmCadastroProfessores extends JFrame {
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtCPF;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private bd objBD = null;
	private JTextField txtEmail;
	private JTable tblDisciplina;
	private DefaultTableModel tabela;
	private int[] vetDisciplina; 
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
					frmCadastroProfessores frame = new frmCadastroProfessores();
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
	public frmCadastroProfessores() {
//JFrame cadastro e alteração de Professores
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\teste\\img\\Icon - 200 x 200.jpg"));
		setResizable(false);
		this.objBD = new bd("POOX","root","");
		setTitle("Cadastrar e alterar Professores");
		setBounds(100, 100, 450, 465);
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
		//JTexField CPF
		txtCPF = new JTextField();
		txtCPF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				lblAlerta.setVisible(false);
				boolean p=false;
				
				if (!objBD.conectaBD()){
					JOptionPane.showMessageDialog(null,
							objBD.mensagem(), "Erro", 
							JOptionPane.ERROR_MESSAGE);
				} else {
					String sqlPesquisa = "select *, day(nascimento), month(nascimento), year(nascimento) from professores where CPF = '" + txtCPF.getText()+"'";
					ResultSet objRes = objBD.consulta(sqlPesquisa);
					try{
						if (objRes.next()){
							txtID.setText(objRes.getString("id"));
							txtNome.setText(objRes.getString("nome"));
							txtTelefone.setText(objRes.getString("telefone"));
							txtEmail.setText(objRes.getString("email"));
							cbBoxDia.setSelectedItem(Integer.parseInt(objRes.getString("day(nascimento)")));
							cbBoxMes.setSelectedIndex( (Integer.parseInt(objRes.getString("month(nascimento)"))-1)  );
							cbBoxAno.setSelectedItem(Integer.parseInt(objRes.getString("year(nascimento)")));
							lblStatus.setText("Cadastro Existente.");
							lblStatus.setForeground(Color.BLUE);
							p=true;
							} else {
								txtID.setText("");
								txtNome.setText("");
								txtTelefone.setText("");
								txtEmail.setText("");
								funcTable();
								lblStatus.setText("Cadastrar Novo.");
								lblStatus.setForeground(Color.GREEN);
								}
						} catch (SQLException e){
							JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
							}
					
					if(p){
						//Pesquisa no banco e carrega Disciplinas
						String sqlPesquisaDisciplina = "select disciplina.id, disciplina.nome from disciplina, professores, professores_disciplina where disciplina.id = professores_disciplina.disciplina_id and professores.id = professores_disciplina.professores_id and professores.id = '"+txtID.getText()+"'";		
						ResultSet objResDisciplina = objBD.consulta(sqlPesquisaDisciplina);
						funcTable();
						try{
							if (objResDisciplina.next()){
								objResDisciplina.beforeFirst();
								while (objResDisciplina.next()){
									tabela.addRow(new Object[]{
											objResDisciplina.getString("id"),
											objResDisciplina.getString("nome")
											});
									}
								}
						    }catch (SQLException e){
						    	JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro ao carregar disciplinas!",JOptionPane.ERROR_MESSAGE);
						    	}
					}
				
					showID();
					objBD.desconecta();
					}//else linha 75
			}//procedimento focusLost linha 70
			
		});//focusListener 68
		txtCPF.setBounds(82, 19, 86, 20);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		
//JLabel Status		
		JLabel jlabel = new JLabel("Status:");
		jlabel.setBounds(225, 22, 46, 14);
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
		lblIdSequncial.setBounds(225, 53, 62, 14);
		contentPane.add(lblIdSequncial);
		//JLabel Ultimo ID
		lblUltimoID = new JLabel("");
		lblUltimoID.setHorizontalAlignment(SwingConstants.CENTER);
		lblUltimoID.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUltimoID.setBounds(291, 53, 79, 14);
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
		txtTelefone.setBounds(82, 106, 118, 20);
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

//JLabel Disciplina
		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setBounds(10, 216, 62, 14);
		contentPane.add(lblDisciplina);
		//JComboBox Disciplinas 
		JComboBox<String> cbBoxDisciplina = new JComboBox<String>();
		cbBoxDisciplina.setBounds(82, 213, 231, 20);
		int i=0;//contador para mudar a posição do vetor
		//Consulta ao banco, quantidade de disciplinas
			if (!objBD.conectaBD()){
				JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
				} else {
					String sqlPesquisaCount = "select count(*) from disciplina";//busca a quantidade de registros cadastrados para determinar o tamanho do vetor
					ResultSet objResCount = objBD.consulta(sqlPesquisaCount); // Realiza a consulta no banco e salva em objResCount
					//Try para determinar tamanho do vetor
					try{
						while(objResCount.next()){
							int countResult = objResCount.getInt(1);
							vetDisciplina = new int[countResult];
						}
						
					} catch (SQLException c1) {
						JOptionPane.showMessageDialog(null,objBD.mensagem(),"Erro",JOptionPane.ERROR_MESSAGE);
						}
					objBD.desconecta();
				}
					
			//consulta ao banco e inserção no combo box.
			if (!objBD.conectaBD()){
				JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
				} else {
					//try para comboBox
					String sqlPesquisa = "select * from disciplina";
					ResultSet objRes = objBD.consulta(sqlPesquisa);
					try{
						while (objRes.next()){
							int idDisciplina = objRes.getInt(1);// salvando resultado da busca 1(id) do banco na variavel
							String name = objRes.getString(2);// salvando  resultado da busca 2(nome) do banco na variavel
							vetDisciplina[i] = idDisciplina; //vetor para gravar o id das disciplinas.			
							cbBoxDisciplina.addItem(name);//Adiciona as disciplinas no comboBox
							i++;
							}
						} catch (SQLException e1){
							JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
							}
					objBD.desconecta();
					}
		contentPane.add(cbBoxDisciplina);
//JButton Adicionar (Disciplina)
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int boxDisciplina = cbBoxDisciplina.getSelectedIndex();// Salva o o index selecionado na variavel (index do comboBox inicia com zero (0))
				String nomeDisciplina = (String)cbBoxDisciplina.getSelectedItem();// salva o nome do item selecionado
				int c; //variavel c para receber a contagem
				int rowCount = tblDisciplina.getRowCount(); // tamanho da tabela salva na variavel
				
				// For para verificar disciplina repetida
				for (c=0;c<rowCount;c++){
					 int profDisc =  Integer.parseInt((tblDisciplina.getValueAt(c, 0)).toString());
					 if (vetDisciplina[boxDisciplina] == profDisc){
						JOptionPane.showMessageDialog(null,"Disciplina já inserida!","ERRO!", JOptionPane.ERROR_MESSAGE);
						break;
						}		
				}
				//if se a disciplina não for repetida será inserida na tabela
				if (c >= rowCount){
					tabela.addRow(new Object[]{
							vetDisciplina[boxDisciplina],
							nomeDisciplina
							});
				}	
			}//action
		});//btnadd
		btnAdicionar.setBounds(341, 212, 89, 23);
		contentPane.add(btnAdicionar);
		
//JScrollPane JTable
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 241, 319, 125);
		contentPane.add(scrollPane_1);
			//JTable: Disciplinas
			tblDisciplina = new JTable();
			tblDisciplina.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//seleção unica de row
			tblDisciplina.setModel(new DefaultTableModel(
				new Object[][] {
				}, 
				new String[] {
					"ID", "Disciplina"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tblDisciplina.getColumnModel().getColumn(0).setResizable(false);
			tblDisciplina.getColumnModel().getColumn(0).setPreferredWidth(100);
			tblDisciplina.getColumnModel().getColumn(0).setMinWidth(30);
			tblDisciplina.getColumnModel().getColumn(0).setMaxWidth(100);
			tblDisciplina.getColumnModel().getColumn(1).setResizable(false);
			tblDisciplina.getColumnModel().getColumn(1).setPreferredWidth(300);
			tblDisciplina.getColumnModel().getColumn(1).setMinWidth(100);
			tblDisciplina.getColumnModel().getColumn(1).setMaxWidth(600);
			scrollPane_1.setViewportView(tblDisciplina);
			
//JButton Remover (disciplina)
			JButton btnRemover = new JButton("Remover");
			btnRemover.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int rowSelected = tblDisciplina.getSelectedRow();// Salva linha selecionada na variavel
					tabela.getValueAt(rowSelected, 1);// variavel é usada
					tabela.removeRow(rowSelected);	
				}
			});
			btnRemover.setBounds(341, 299, 89, 23);
			contentPane.add(btnRemover);
			
//JButton Salvar
			JButton btnSalvar = new JButton("Salvar");
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if ((txtCPF.getText().equals(""))){//verifica se cpf == vazio
						JOptionPane.showMessageDialog(null, objBD.mensagem(),"CPF inválido",JOptionPane.ERROR_MESSAGE);
						} else if (!objBD.conectaBD()){
							JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro de conexão",JOptionPane.ERROR_MESSAGE);
							} else {
								String sqlPesquisa = "select * from professores where CPF ='"+txtCPF.getText()+"'";
								ResultSet objRes = objBD.consulta(sqlPesquisa);
								try{
									if (objRes.next()){
										String sqlAlterar = "update professores set id = "+txtID.getText()+", nome = '" +txtNome.getText() + "', telefone =  '"+txtTelefone.getText() + "', email = '" +txtEmail.getText() + "',  nascimento = '"+nascDate()+"' where CPF = '"+txtCPF.getText()+"'";
										if (objBD.atualiza(sqlAlterar)){
											saveDisc();
											JOptionPane.showMessageDialog(null,"Dados alterados com sucesso!", "Aviso",JOptionPane.INFORMATION_MESSAGE);
											} else {
												JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro ao atualizar dados!",JOptionPane.ERROR_MESSAGE);
												}
										} else {
											String sqlInserir = "insert into professores (id,cpf,nome,telefone,email,nascimento) values("+txtID.getText()+",'"+txtCPF.getText()+"','"+txtNome.getText()+"','"+txtTelefone.getText()+"','"+txtEmail.getText()+"','"+nascDate()+"')";
											if (objBD.atualiza(sqlInserir)){
												JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
												saveDisc();
												lblStatus.setText("Professor Cadastrado.");
												lblStatus.setForeground(Color.GRAY);
												} else {
													JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
													}
											}
									} catch(SQLException f){
										JOptionPane.showMessageDialog(null,objBD.mensagem(), "Erro",JOptionPane.ERROR_MESSAGE);
										}
								showID();
								objBD.desconecta();
								}//else, conexao banco ok
					}//actionPerformed
				});//actionListener 
			btnSalvar.setMnemonic('S');
			btnSalvar.setBounds(10, 390, 89, 23);
			contentPane.add(btnSalvar);
			
//JButton Excluir
			JButton btnExcluir = new JButton("Excluir");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String delCPF = txtCPF.getText();
					
					int test = JOptionPane.showConfirmDialog(null, "Deseja excluir o cadastro? \nCPF: "+delCPF);
					if ( test == 0){
						 if (!objBD.conectaBD()){
							 JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro", JOptionPane.ERROR_MESSAGE);
							 } else {
								 String sqlExcluirDisc = "delete from professores_disciplina where professores_id = '"+txtID.getText()+"'";
								 if (!objBD.atualiza(sqlExcluirDisc)){
									 JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro", JOptionPane.ERROR_MESSAGE);
								 }
								
								 String sqlExcluir = "delete from professores where CPF = '"+ txtCPF.getText()+"'";
								 if (objBD.atualiza(sqlExcluir)){
									 Limpar();
									 lblStatus.setText("Professor Excluído.");
									 lblStatus.setForeground(Color.RED);
									 JOptionPane.showMessageDialog(null, "Cadastro excluido! \n CPF: "+delCPF);
									 } else {
										 JOptionPane.showMessageDialog(null, objBD.mensagem(),"Erro", JOptionPane.ERROR_MESSAGE);
										 }
									showID();
									objBD.desconecta();
								 }
						 }
					
					}
				});
			btnExcluir.setBounds(141, 390, 89, 23);
			contentPane.add(btnExcluir);
			
//JButton Limpar
			JButton btnLimpar = new JButton("Limpar");
			btnLimpar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Limpar();
				}
				});
			btnLimpar.setBounds(240, 390, 89, 23);
			contentPane.add(btnLimpar);
			
//JButton Sair
			JButton btnSair = new JButton("Sair");
			btnSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					objBD.desconecta();
					dispose();
					}
				});
			btnSair.setBounds(341, 390, 89, 23);
			contentPane.add(btnSair);

			
		}//frmProfessores
	
//função limpar
	private void Limpar(){
		 txtID.setText("");
		 txtCPF.setText("");
		 txtNome.setText("");
		 txtTelefone.setText("");
		 txtEmail.setText("");
		 funcTable();
		 lblStatus.setText("");
		 lblAlerta.setVisible(true);
	}
	
//remover e inserir disciplinas
	private void saveDisc (){
		// Inserindo disciplinas
		String sqlDelDisc = "delete from professores_disciplina where professores_id = '"+txtID.getText()+"'";
		if (objBD.atualiza(sqlDelDisc)){
			for	(int c=0; c<tblDisciplina.getRowCount();c++){
				String sqlInsereDisc = "insert into professores_disciplina (professores_id,disciplina_id) values ('"+txtID.getText()+"','"+tblDisciplina.getValueAt(c, 0)+"')";
				if (!objBD.atualiza(sqlInsereDisc)){
					JOptionPane.showMessageDialog(null, "Erro ao inserir disciplinas!","Erro",JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao exlcuir disciplinas", "AVISO",JOptionPane.ERROR_MESSAGE);
			}
		objBD.desconecta();
	}
	
// limpando a tabela
	private void funcTable(){
		tabela = (DefaultTableModel) tblDisciplina.getModel(); 
		tabela.setNumRows(0);
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
				String ultID = "SELECT * FROM professores ORDER BY id DESC LIMIT 1";
				ResultSet objRes= objBD.consulta(ultID);
				try	{
					if(objRes.first()){
						msgID = objRes.getString(1);
						}
					}catch (SQLException e) {
						JOptionPane.showMessageDialog(null, objBD.mensagem(),"ERRO!",JOptionPane.ERROR_MESSAGE);
						}
				objBD.desconecta();
				}
		lblUltimoID.setText(msgID);
	}	
	
} // class frmProfessores
