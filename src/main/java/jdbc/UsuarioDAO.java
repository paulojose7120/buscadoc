package jdbc;

import java.util.ArrayList;
import java.util.List;

import usuario.Usuario;




public interface UsuarioDAO {

	public void addUsuario(Usuario u);

	public List getAllUsuarios();
	
	public void updateUsuario(Usuario u );
	


	public void deleteUsuario(Usuario u);

	
	public Usuario getUsuario(String nome);
	
	public ArrayList<Usuario> getUsuarioByUnidadeSaude(int id);
	
	public boolean usuarioValido(Usuario u);
}


