import java.sql.*;

public class bd{
	   
    private Connection con;
	private Statement stmt;
	private boolean erro;
	private String msg;
	private String banco, usuario, senha;

      /* Método Construtor: inicializa alguns atributos do objeto
         Parâmetros: b-nome do banco de dados / u-nome o usuario / s-senha 
      */   
      public bd(String b, String u, String s){
		 this.banco="jdbc:mysql://localhost/"+b;
		 this.usuario=u;
		 this.senha=s;
		 erro=false;
		 msg="";
	 }
       /* Método conectaBD: realiza a conexão com o banco de dados
          Retorno: TRUE-conexão realizada / FALSE-falha na conexão 
       */ 
	 public boolean conectaBD(){
		 this.erro=false;
	       try {
		      Class.forName("com.mysql.jdbc.Driver");
                  con = DriverManager.getConnection(this.banco, this.usuario,
                                                                    this.senha);
		      stmt=con.createStatement();
		     }catch (SQLException e){this.erro=true;
			  this.msg="Falha na conexao com o banco de dados!"; 
			}
		      catch (java.lang.Exception e){this.erro=true; 
			     this.msg="Erro no driver de conexao!"; 
			}
		return !erro;      
	}
      /* Método: consulta
         Parâmetro: c-comando SQL de consulta (SELECT)
         Retorno: objeto ResultSet com o resultado da consulta
      */ 
	public ResultSet consulta (String c){
		ResultSet res=null;
		this.erro=false;
		this.msg="Sucesso na execução da consulta!";
		try{
			res=stmt.executeQuery(c);
		}catch (SQLException e){this.erro=true;
		      this.msg="Falha na execução da consulta!";
		 }
		return res;
	}
      /* Método: atualiza
         Parâmetro: c-comando SQL de atualização (INSERT, UPDATE, DELETE)
         Retorno: TRUE-comando executado com sucesso / FALSE-falha na execução
      */ 
	public boolean atualiza(String c){
		int i=-1;
		this.erro=false;
		this.msg="Operação realizada com sucesso!";
		try{
			i=stmt.executeUpdate(c);
		}catch (SQLException e){this.erro=true; 
			this.msg="Falha na operação!";
             }
		return !erro;
	}
      /* Método desconecta: fecha a conexão com o banco de dados
         Retorno: TRUE-desconexão realizada / FALSE-falha na desconexão
      */ 
	public boolean desconecta(){
		boolean sucesso=true;
		try{
			stmt.close();
			con.close();
		}catch(SQLException e){sucesso=false;}
		return sucesso;
	}
      /* Método ocorreuErro: retorna o valor do atributo erro
         Retorno: TRUE-ocorreu um erro durante uma operação 
                  FALSE-não ocorreu nenhum erro 
      */ 
	public boolean ocorreuErro(){
		return this.erro;
	}
       /* Método mensagem: retorna o valor do atributo mensagem
          Retorno: Mensagem sobre um possível erro que possa ter ocorrido 
                    durante a realização de uma operação
       */ 
	public String mensagem(){
		return this.msg;
	}
}
