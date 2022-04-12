package managedbeans;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import usuario.Usuario;
import util.UsuarioValidoBD;

@Named
@SessionScoped
public class LoginMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String usuario;
	private String senha;
	
	
	
	
	public void logar() throws IOException {
		
		System.out.println(usuario+senha);
		Usuario u = new Usuario(usuario, senha);
		
		if(UsuarioValidoBD.verifica(u)) {
			System.out.println("logado com sucesso!");
			FacesContext.getCurrentInstance().getExternalContext().redirect("seguranca/inicio.xhtml");
			HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			sessao.setAttribute("senha", senha);
			sessao.setAttribute("login", usuario);
			
		}else {
		System.out.println("USUARIO NÃO IDENTIFICADO");
	
		}}
	
	
	public void deslogar() throws IOException {
		ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
	
		contexto.redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/login.xhtml");
		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/login.xhtml");
		sessao.invalidate();
	}
	
	
	
	
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}
