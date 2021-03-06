import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class frmPrincipal extends JFrame {
	private JFrame frmPOOXandao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrincipal window = new frmPrincipal();
					window.frmPOOXandao.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public frmPrincipal() {
		//JFrame POOX
		frmPOOXandao = new JFrame();
		frmPOOXandao.setResizable(false);
		frmPOOXandao.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\teste\\img\\Icon - 200 x 200.jpg"));
		frmPOOXandao.setTitle(" POOX Cursos v2.0");
		frmPOOXandao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPOOXandao.setBounds(100, 100, 450, 300);
		
		// Barra de Menu
		JMenuBar menuBar = new JMenuBar();
		frmPOOXandao.setJMenuBar(menuBar);
		
			// Menu Cadastros
			JMenu mnCadastros = new JMenu("Cadastros");
			menuBar.add(mnCadastros);
			
				// Item do Menu Cadastros: Alunos
				JMenuItem mntmAlunos = new JMenuItem("Alunos");
				mntmAlunos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frmCadastroAlunos fmAlu = new frmCadastroAlunos();
						fmAlu.setVisible(true);
					}
				});
				mnCadastros.add(mntmAlunos);
			
				// Item do Menu Cadastros: Professores	
				JMenuItem mntmProfessores = new JMenuItem("Professores");
				mntmProfessores.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frmCadastroProfessores fmProf = new frmCadastroProfessores();
						fmProf.setVisible(true);
					}
				});
				mnCadastros.add(mntmProfessores);
				
				// Item do Menu Cadastros: Areas dos cursos
				JMenuItem mntmArea = new JMenuItem("Area");
				mntmArea.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frmCadastroAreaCurso fmTm = new frmCadastroAreaCurso();
						fmTm.setVisible(true);
					}
				});
				
				JMenuItem mntmTipo = new JMenuItem("N�vel");
				mntmTipo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frmCadastroNivelCurso fmTp = new frmCadastroNivelCurso();
						fmTp.setVisible(true);
					}
				});
				
					// Item do Menu Cadastros: Disciplinas
					JMenuItem mntmDisciplinas = new JMenuItem("Disciplinas");
					mntmDisciplinas.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							frmCadastroDisciplina fmDisc = new frmCadastroDisciplina();
							fmDisc.setVisible(true);
						}
					});
					mnCadastros.add(mntmDisciplinas);
				mnCadastros.add(mntmTipo);
				mnCadastros.add(mntmArea);
				
					// Item do Menu Cadastros: Cursos
					JMenuItem mntmCursos = new JMenuItem("Cursos");
					mntmCursos.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frmCadastroCursos fmCur = new frmCadastroCursos();
							fmCur.setVisible(true);
						}
					});
					mnCadastros.add(mntmCursos);
		
			// Menu Inscrever
			JMenu mnInscrever = new JMenu("Inscrever");
			menuBar.add(mnInscrever);
				
				// Item do Menu Inscrever: Alunos		
				JMenuItem mntmAluno = new JMenuItem("Aluno");
				mnInscrever.add(mntmAluno);
				// Item do Menu Inscrever: Professor	
				JMenuItem mntmProfessor = new JMenuItem("Professor");
				mnInscrever.add(mntmProfessor);
				// Item do Menu Inscrever: Turmas	
				JMenuItem mntmTurmas = new JMenuItem("Turma");
				mntmTurmas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frmInscreverTurma fmInsTur = new frmInscreverTurma();
						fmInsTur.setVisible(true);
					}
				});
				mnInscrever.add(mntmTurmas);
		
			// Menu Listas
			JMenu mnTurmas = new JMenu("Listas");
			menuBar.add(mnTurmas);
		
				// Item do Menu Listas: Alunos
				JMenuItem mntmBuscarAlunos = new JMenuItem("Alunos");
				mntmBuscarAlunos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frmListaAlunos fmLisAlu = new frmListaAlunos();
						fmLisAlu.setVisible(true);
					}
				});
				mnTurmas.add(mntmBuscarAlunos);
				// Item do Menu Listas: Professores
				JMenuItem mntmBuscar_Professores = new JMenuItem("Professores");
				mnTurmas.add(mntmBuscar_Professores);
				// Item do Menu Listas: Cursos
				JMenuItem mntmBuscar_Cursos = new JMenuItem("Cursos");
				mnTurmas.add(mntmBuscar_Cursos);
				// Item do Menu Listas: Turmas
				JMenuItem mntmBuscar_Turmas = new JMenuItem("Turmas");
				mnTurmas.add(mntmBuscar_Turmas);
			
			// Menu Sobre
			JMenu mnSobre = new JMenu("Sobre");
			menuBar.add(mnSobre);
				// Item do Menu Sobre: Vers�o
				JMenuItem mntmVerso = new JMenuItem("Vers\u00E3o 1.0");
				mnSobre.add(mntmVerso);
				// Item do Menu Sobre: Data
				JMenuItem mntmData = new JMenuItem("Data 14/12/2017");
				mnSobre.add(mntmData);
				// Item do Menu Sobre: O que faz
				JMenuItem mntmCadastroParaCursos = new JMenuItem("Gerenciador de Cursos");
				mnSobre.add(mntmCadastroParaCursos);
				
				JMenu mnSistema = new JMenu("Sistema");
				menuBar.add(mnSistema);
				
				JMenuItem mntmSair = new JMenuItem("Sair");
				mntmSair.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				mnSistema.add(mntmSair);
				frmPOOXandao.getContentPane().setLayout(null);
				
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon("C:\\workspace\\teste\\img\\POOX logo 2 - 400 - 200.png"));
				label.setBounds(0, 0, 444, 250);
				frmPOOXandao.getContentPane().add(label);
				
	}//frmPrincipal
}//class frmPrincipal
