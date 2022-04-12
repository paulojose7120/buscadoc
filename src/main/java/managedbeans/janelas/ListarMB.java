package managedbeans.janelas;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import jdbc.UnidadeSaudeDAO;
import jdbc.UnidadeSaudeImpl;
import jdbc.UsuarioDAO;
import jdbc.UsuarioDAOImpl;
import paciente.Paciente;
import unidadesaude.UnidadeSaude;
import unidadesaude.ubsf;
import usuario.Usuario;

@SessionScoped
@Named
public class ListarMB implements Serializable{

	private ArrayList<UnidadeSaude> listaUnidadeSaude;
	private ArrayList<Usuario> listaUsuario= null;
	
	private String itemSelecionado;
	private int idUnidadeSelecionada;
	private String usuarioSelecionado;
	private ArrayList<Paciente> pacientes;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@PostConstruct
	public void init() {
		UnidadeSaudeDAO unidadeDAO = new UnidadeSaudeImpl();
		this.listaUnidadeSaude = unidadeDAO.getAll();
		
	}


	public ArrayList<UnidadeSaude> getListaUnidadeSaude() {
	
	
		return listaUnidadeSaude;
	}
	
	public void changeListener(ValueChangeEvent event) {	
	idUnidadeSelecionada =Integer.valueOf((String) event.getNewValue());
	
		UsuarioDAO conexaoDAO = new UsuarioDAOImpl();
		listaUsuario = conexaoDAO.getUsuarioByUnidadeSaude(idUnidadeSelecionada);
		
	}

	public void changeUsuarioListener(ValueChangeEvent event) {
		System.out.println(Integer.valueOf((String) event.getNewValue()));	
	}
	
	
	public void botaoAcao(ActionEvent event) {
		System.out.println("BOTAO CLICADO");
		System.out.println(usuarioSelecionado);
		
		
	}
	
	public String getItemSelecionado() {
		return itemSelecionado;
	}


	public void setItemSelecionado(String itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}


	public int getIdUnidadeSelecionada() {
		return idUnidadeSelecionada;
	}


	public void setIdUnidadeSelecionada(int idUnidadeSelecionada) {
		this.idUnidadeSelecionada = idUnidadeSelecionada;
	}


	public ArrayList<Usuario> getListaUsuario() {
		return listaUsuario;
	}


	public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}


	public String getUsuarioSelecionado() {
		return usuarioSelecionado;
	}


	public void setUsuarioSelecionado(String usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}


	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}


	public void setPacientes(ArrayList<Paciente> pacientes) {
		this.pacientes = pacientes;
	}







	
	
}
