package jdbc;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import usuario.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	private ConexaoComBancoDeDados conn =  new ConexaoComBancoDeDados();
	
	
	@Override
	public void addUsuario(Usuario u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getAllUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUsuario(Usuario u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUsuario(Usuario u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario getUsuario(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public boolean usuarioValido(Usuario u) {
		boolean resultado = false;
		
		try {
			
			PreparedStatement ps = conn.getConnection().prepareStatement("SELECT * FROM tb_usuario WHERE usuario = ? AND senha = ?");
			ps.setString(1, u.getLogin());
			ps.setString(2, u.getSenha());
			
			ResultSet rs	= ps.executeQuery();
			
			if(rs.next()) {
				
				resultado = true;	
	
			}
			
		} catch (SQLException e) {
			System.err.print("HOUVE ERRRO AO VALIDAR");
			e.printStackTrace();
		}
		
		
		
		return resultado;
	}

	@Override
	public ArrayList<Usuario> getUsuarioByUnidadeSaude(int id) {
		ArrayList<Usuario> listaDeUsuarios = new ArrayList<>();
		try {
			
			PreparedStatement ps = conn.getConnection().prepareStatement("SELECT * FROM tb_usuario WHERE id_tb_unidade_saude = ?");
			ps.setInt(1, id);
			ResultSet rs	= ps.executeQuery();
			
			while(rs.next()) {
			Usuario usuario = new Usuario();	
			String nome = rs.getString("nome");
			String cargo = rs.getString("cargo");
			String nomeUsuario = rs.getString("usuario");
			String matricula =	rs.getString("matricula");
			int nivel = 	rs.getInt("nivel");
			int idUsuario = 	rs.getInt("id");
			String email =	rs.getString("email");
				usuario.setNome(nome);
				usuario.setLogin(nomeUsuario);
				usuario.setCargo(cargo);
				usuario.setMatricula(matricula);
				usuario.setNivel(nivel);
				usuario.setEmail(email);
				usuario.setId(idUsuario);
				listaDeUsuarios.add(usuario);
			
			}

		} catch (SQLException e) {
			System.err.print("HOUVE ERRRO AO VALIDAR");
			e.printStackTrace();
		}
		
		
		return listaDeUsuarios;
	}

	


}
