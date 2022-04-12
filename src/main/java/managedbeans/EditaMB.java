package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import annotation.AnnotationTipoBean;
import jdbc.PacienteDAO;
import jdbc.PacienteDaoImp;
import paciente.Paciente;
import vacinacao.Documento;


@SessionScoped
@Named
public class EditaMB implements Serializable {
	

	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cpf;
	private String quantidadeDeDocs;
	
	
	private ArrayList<Paciente> pacientes;
	private ArrayList<Documento> documentos;

	private Paciente pacienteSelecionado;
	
	
	private String nome;
	
	public String getNomeAtualizado() {
		return nomeAtualizado;
	}


	public void setNomeAtualizado(String nomeAtualizado) {
		this.nomeAtualizado = nomeAtualizado;
	}


	public String getSusAtualizado() {
		return susAtualizado;
	}


	public void setSusAtualizado(String susAtualizado) {
		this.susAtualizado = susAtualizado;
	}


	public String getCpfAtualizado() {
		return cpfAtualizado;
	}


	public void setCpfAtualizado(String cpfAtualizado) {
		this.cpfAtualizado = cpfAtualizado;
	}

	/**
	 * 
	 */
	private String nomeAtualizado;
	private String susAtualizado;
	private String cpfAtualizado;
	


	public void consultaBD(String dadoDePesquisa, String modoDePesquisa) {
		

		if(modoDePesquisa.equals("cpf")) {
			this.cpf = dadoDePesquisa;
			pacientes = 	(ArrayList<Paciente>) consultaPorCPF();
			System.out.println("TAMANHO DA LISTA"+pacientes.size());
			
		}else if(modoDePesquisa.equals("nome")) {
			this.nome = dadoDePesquisa;
			pacientes = (ArrayList<Paciente>) consultaPorNome();
			System.out.println(pacientes.size());
	}
	}
	
	
private List<Paciente> consultaPorCPF() {
		
		PacienteDAO pacienteBancoDeDados = new PacienteDaoImp();
		
		
		
		return pacienteBancoDeDados.getListaDePacientes(cpf);
		

}


	public void atualizar() {
		System.out.println("atualizando...: "+nomeAtualizado);
		Paciente pacienteAtualizado = new Paciente();
		pacienteAtualizado.setCpf(cpfAtualizado);
		pacienteAtualizado.setId(pacienteSelecionado.getId());
		pacienteAtualizado.setNome(nomeAtualizado);
		pacienteAtualizado.setSus(susAtualizado);
		atualizandoBD(pacienteAtualizado);
		
	}


	private void atualizandoBD(Paciente pacienteAtualizado) {
		PacienteDAO pacienteBD = new PacienteDaoImp();
		pacienteBD.editarPaciente(pacienteAtualizado);
		
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
	public String getQuantidadeDeDocs() {
		return quantidadeDeDocs;
	}
	public void setQuantidadeDeDocs(String quantidadeDeDocs) {
		this.quantidadeDeDocs = quantidadeDeDocs;
	}
	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}
	public void setPacientes(ArrayList<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	public ArrayList<Documento> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(ArrayList<Documento> documentos) {
		this.documentos = documentos;
	}
	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}
	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
