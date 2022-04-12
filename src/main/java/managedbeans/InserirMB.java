package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import annotation.AnnotationTipoBean;
import jdbc.PacienteDAO;
import jdbc.PacienteDaoImp;
import paciente.Paciente;
import vacinacao.Documento;


@SessionScoped
@Named
public class InserirMB implements Serializable{


	private String cpf;
	private String nome;
	private String sus;
	private Paciente paciente;
	private int  idPaciente;
	private static final long serialVersionUID = 6857788616698052268L;


	public void consultaBD() {
		addPaciente(paciente);
	}
	
	
	private void addPaciente(Paciente paciente) {
		PacienteDAO pacienteDao = new PacienteDaoImp();
		this.setIdPaciente(pacienteDao.addPaciente(paciente));
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

	public String getSus() {
		return sus;
	}

	public void setSus(String sus) {
		this.sus = sus;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


	public int getIdPaciente() {
		return idPaciente;
	}


	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	
	
}
