package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


import paciente.Paciente;
import vacinacao.Documento;



@Named
@RequestScoped
public class PacienteDaoImp implements PacienteDAO{
	
	private int idPaciente;
	
	@Override
	public int addPaciente(Paciente p) {
		

		ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
		
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("INSERT INTO tb_paciente (nome, cpf, sus) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getNome());
			ps.setString(2, p.getCpf());
			ps.setString(3, p.getSus());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
		
			if(rs.next()) {
				idPaciente = rs.getInt("id");
				
				System.out.println("ID DO PACIENTE: "+idPaciente);
				ArquivoImpl salvarArquivos = new ArquivoImpl();
				salvarArquivos.addArquivo(p.getListaDeDocs(), idPaciente);
			
			
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idPaciente;
	}

	@Override
	public Paciente getPaciente(String cpf, String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paciente> getListaDePacientes(String cpfPaciente) {
	/*	String tipoDeDado;
			
	 * 	if(dado) {
			verificar se o dado  e cpf ou nome
			
			
		}
		*/
		
		
		
			ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
			
			
			
			
				List<Paciente> listaDeUsuarios = new ArrayList<Paciente>();	
			try {
				
				PreparedStatement ps = conexao.getConnection().prepareStatement("SELECT * FROM tb_paciente WHERE cpf = ?");
				ps.setString(1, cpfPaciente);
				ResultSet rs = ps.executeQuery();
				System.out.println("CPF PACIENTE"+cpfPaciente);
				
				while(rs.next()) {
	
					Paciente paciente = new Paciente();
						
					
					
	int id = rs.getInt("id");
	String nome  =rs.getString("nome");
	String sus = rs.getString("sus");
	String cpf = rs.getString("cpf");

		System.out.println("CPF PESQUISA"+cpf);

		
		paciente.setId(id);
		paciente.setNome(nome);
		paciente.setSus(sus);
		paciente.setCpf(cpf);
		
		ArquivoDAO arquivoDao = new ArquivoImpl();
		
		
		paciente.setListaDeDocs(arquivoDao.getAllArquivos(id));
		listaDeUsuarios.add(paciente);

		
				}
				
				
			rs.close();	
			conexao.getConnection().close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listaDeUsuarios;	}

		


	@Override
	public void editarPaciente(Paciente p) {
		ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
		
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("UPDATE  tb_paciente SET nome = ?, cpf = ?, sus = ?  WHERE id = ?");
			ps.setString(1, p.getNome());
			ps.setString(2, p.getCpf());
			ps.setString(3, p.getSus());
			ps.setInt(4, p.getId());
			ps.executeUpdate();
			/*
			ArquivoImpl arquivo = new ArquivoImpl();
			
				for(Documento doc : p.getListaDeDocs()) {
					arquivo.updateArquivo(doc.getUrl(), p.getId());
				System.out.println(doc.getUrl());
				
				}*/
					ps.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	

	

	@Override
	public List<Paciente> getListaDePacientesPorNome(String nome) {
		ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
		
		
		
		
List<Paciente> listaDeUsuarios = new ArrayList<Paciente>();	
		try {
			
			PreparedStatement ps = conexao.getConnection().prepareStatement("SELECT * FROM tb_paciente WHERE nome LIKE ?");
			ps.setString(1, "%"+nome+"%");
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {

				Paciente paciente = new Paciente();
					
				
				
int id = rs.getInt("id");
String nomeBD  =rs.getString("nome");
String sus = rs.getString("sus");
String cpf = rs.getString("cpf");



	
	paciente.setId(id);
	paciente.setNome(nomeBD);
	paciente.setSus(sus);
	paciente.setCpf(cpf);
	
	ArquivoDAO arquivoDao = new ArquivoImpl();
	
	
	paciente.setListaDeDocs(arquivoDao.getAllArquivos(id));
	listaDeUsuarios.add(paciente);

	
			}
			
			
		rs.close();	
		conexao.getConnection().close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaDeUsuarios;
	}







	@Override
	public ArrayList<Paciente> getListaDePacientePorAgente(int idDoAgente) {
		ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
try {
			
			PreparedStatement ps = conexao.getConnection().prepareStatement("SELECT * FROM tb_paciente WHERE id_tb_unidade_saude = ?");
			ps.setInt(1, idDoAgente);
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {

				Paciente paciente = new Paciente();
					
				
				
int id = rs.getInt("id");
String nomeBD  =rs.getString("nome");
String sus = rs.getString("sus");
String cpf = rs.getString("cpf");



	
	paciente.setId(id);
	paciente.setNome(nomeBD);
	paciente.setSus(sus);
	paciente.setCpf(cpf);
	
	ArquivoDAO arquivoDao = new ArquivoImpl();
	
	
	paciente.setListaDeDocs(arquivoDao.getAllArquivos(id));
	
	
	
			}
			
			
		rs.close();	
		conexao.getConnection().close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
