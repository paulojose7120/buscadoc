package managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;

import annotation.AnnotationTipoBean;

import jdbc.PacienteDAO;
import jdbc.PacienteDaoImp;
import paciente.Paciente;
import util.FileDownloaderMB;
import vacinacao.Documento;

@SessionScoped
@Named
public class ConsultaMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4510511868719562237L;

	@Inject
	FileDownloaderMB downloader;




	private String cpf;
	private String quantidadeDeDocs;
	private String urlDocumento;
	private ArrayList<Paciente> pacientes;
	private ArrayList<Documento> documentos;

	private Paciente pacienteSelecionado;
	
	
	private String nome;

	
	public ArrayList<Paciente> getPacientes() {

		return pacientes;
	}

	
	public void setPacientes(ArrayList<Paciente> pacientes) {
		this.pacientes = pacientes;
	}


	public void consultaBD(String dadoDePesquisa, String modoDePesquisa) {		
		if(modoDePesquisa.equals("cpf")) {
			this.cpf = dadoDePesquisa;
			pacientes = 	(ArrayList<Paciente>) consultaPorCPF();
			System.out.println(pacientes.size());
			
		}else if(modoDePesquisa.equals("nome")) {
			this.nome = dadoDePesquisa;
			pacientes = (ArrayList<Paciente>) consultaPorNome();
			System.out.println(pacientes.size());
	}}
	
	
	
	public void setSelecionado(Paciente p ) {
		this.documentos = (ArrayList<Documento>) p.getListaDeDocs();
		
		
	}
	
	
	private List<Paciente> consultaPorCPF() {
		
		PacienteDAO pacienteBancoDeDados = new PacienteDaoImp();
		
		
		
		return pacienteBancoDeDados.getListaDePacientes(cpf);
		
	}
	


	private List<Paciente> consultaPorNome() {
		PacienteDAO pacienteBancoDeDados = new PacienteDaoImp();
		
		
		
		
		return pacienteBancoDeDados.getListaDePacientesPorNome(nome);
	}
	
	
	
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getQuantidadeDeDocs() {
		return quantidadeDeDocs;
	}


	public void setQuantidadeDeDocs(String quantidadeDeDocs) {
		this.quantidadeDeDocs = quantidadeDeDocs;
	}


	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}


	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}


	public ArrayList<Documento> getDocumentos() {
		return documentos;
	}


	public void setDocumentos(ArrayList<Documento> documentos) {
		this.documentos = documentos;
	}



	



 
}
