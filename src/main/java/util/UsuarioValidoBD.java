package util;

import jdbc.UsuarioDAOImpl;
import usuario.Usuario;

public class UsuarioValidoBD {
	private static UsuarioDAOImpl verifica = new UsuarioDAOImpl();
	public static boolean verifica(Usuario u ) {
		
	
	boolean resultado = 	verifica.usuarioValido(u);
		
		
		return resultado;
		
		
		
		
	}
	
	
	
}
