package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ConexaoComBancoDeDados {

	  Connection conexao;
	 
	 
	  
	  public ConexaoComBancoDeDados() {
		  abreConexao();
	  }
	  
	private void abreConexao()  {
		 try {
			 Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5000/busca-doc",
				"postgres", "root");
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	/*	
		Session sessao = Hibernate.getSessionfactory();
		System.out.println("OBTEVE A SESS�O");
		  List<Usuario> list = (List<Usuario>)  sessao.createQuery("from Usuario").list();
		  	System.out.println(list.size());
			 
			sessao.close();*/
			
	public  Connection getConnection() {
		return conexao;
		
	}


}
