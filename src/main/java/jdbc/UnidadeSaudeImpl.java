package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import unidadesaude.UnidadeSaude;
import unidadesaude.ubsf;
import vacinacao.Documento;

public class UnidadeSaudeImpl implements UnidadeSaudeDAO{

	
	
	
	@Override
	public ArrayList<UnidadeSaude> getAll() {
		ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
		ArrayList<UnidadeSaude> listaDeUnidades = new ArrayList<UnidadeSaude>();
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("SELECT * FROM tb_unidade_saude");
			
		
		
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {

				
			
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				int id = rs.getInt("id");
				UnidadeSaude unidadeSaude = new ubsf(nome,id, email);
				listaDeUnidades.add(unidadeSaude);
				
			
				
				
				

			}
			
			
			rs.close();
			conexao.getConnection().close();
			
			
			
			
			
			
			
			
			
			
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
;	
		
		
		
		
		return listaDeUnidades;
	}

	@Override
	public UnidadeSaude getPorId(int id) {
		ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
		UnidadeSaude unidadeSaude = null;
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("BEGIN; SELECT * FROM tb_unidade_saude WHERE id = ?; COMMIT");
			ps.setInt(1, id);
		
		
		
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {

				
			
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				
				unidadeSaude = new ubsf(nome, email);
			
				
			
				
				
				

			}
			
			
			rs.close();
			conexao.getConnection().close();
			
			
			
			
			
			
			
			
			
			
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unidadeSaude;
	}

	@Override
	public boolean update(UnidadeSaude unidade) {
		ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
		boolean linhaAdicionadaComSucesso = false;
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("BEGIN; UPDATE tb_unidade_saude SET nome = ?, email = ? WHERE id = ?");
			ps.setString(1, unidade.getNome());
			ps.setString(2, unidade.getEmail());
			ps.setInt(3, unidade.getId());
		
			linhaAdicionadaComSucesso = ps.execute();
			ps.close();
			conexao.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return linhaAdicionadaComSucesso;
		
		
	}

	@Override
	public void delete(int id) {
		ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean add(UnidadeSaude unidade) {
		ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
		boolean linhaAdicionadaComSucesso = false;
		
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("BEGIN; INSERT INTO tb_unidade_saude (nome, email) VALUES (?,?)");
			ps.setString(1, unidade.getNome());
			ps.setString(2, unidade.getEmail());

			linhaAdicionadaComSucesso = ps.execute();
			
			ps.close();
			conexao.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return linhaAdicionadaComSucesso;
		
		
		
		
	}

}
