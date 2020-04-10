import java.sql.*;

public class bd{
	   
    private Connection con;
	private Statement stmt;
	private boolean erro;
	private String msg;
	private String banco, usuario, senha;

      /* M�todo Construtor: inicializa alguns atributos do objeto
         Par�metros: b-nome do banco de dados / u-nome o usuario / s-senha 
      */   
      public bd(String b, String u, String s){
		 this.banco="jdbc:mysql://localhost/"+b;
		 this.usuario=u;
		 this.senha=s;
		 erro=false;
		 msg="";
	 }
       /* M�todo conectaBD: realiza a conex�o com o banco de dados
          Retorno: TRUE-conex�o realizada / FALSE-falha na conex�o 
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
      /* M�todo: consulta
         Par�metro: c-comando SQL de consulta (SELECT)
         Retorno: objeto ResultSet com o resultado da consulta
      */ 
	public ResultSet consulta (String c){
		ResultSet res=null;
		this.erro=false;
		this.msg="Sucesso na execu��o da consulta!";
		try{
			res=stmt.executeQuery(c);
		}catch (SQLException e){this.erro=true;
		      this.msg="Falha na execu��o da consulta!";
		 }
		return res;
	}
      /* M�todo: atualiza
         Par�metro: c-comando SQL de atualiza��o (INSERT, UPDATE, DELETE)
         Retorno: TRUE-comando executado com sucesso / FALSE-falha na execu��o
      */ 
	public boolean atualiza(String c){
		int i=-1;
		this.erro=false;
		this.msg="Opera��o realizada com sucesso!";
		try{
			i=stmt.executeUpdate(c);
		}catch (SQLException e){this.erro=true; 
			this.msg="Falha na opera��o!";
             }
		return !erro;
	}
      /* M�todo desconecta: fecha a conex�o com o banco de dados
         Retorno: TRUE-desconex�o realizada / FALSE-falha na desconex�o
      */ 
	public boolean desconecta(){
		boolean sucesso=true;
		try{
			stmt.close();
			con.close();
		}catch(SQLException e){sucesso=false;}
		return sucesso;
	}
      /* M�todo ocorreuErro: retorna o valor do atributo erro
         Retorno: TRUE-ocorreu um erro durante uma opera��o 
                  FALSE-n�o ocorreu nenhum erro 
      */ 
	public boolean ocorreuErro(){
		return this.erro;
	}
       /* M�todo mensagem: retorna o valor do atributo mensagem
          Retorno: Mensagem sobre um poss�vel erro que possa ter ocorrido 
                    durante a realiza��o de uma opera��o
       */ 
	public String mensagem(){
		return this.msg;
	}
}
