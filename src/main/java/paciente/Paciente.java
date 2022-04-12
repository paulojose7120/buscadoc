package paciente;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import vacinacao.Documento;

@Named
@SessionScoped
public class Paciente implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5981463825455104458L;
	private String nome;
	private String cpf;
	private String sus;
	private int qtde;

	private int id;
	
	
	private ArrayList<Documento> listaDeDocs;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSus() {
		return sus;
	}
	public void setSus(String sus) {
		this.sus = sus;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Documento> getListaDeDocs() {
		return listaDeDocs;
	}
	public void setListaDeDocs(List<Documento> listaDeDocs) {
		qtde = listaDeDocs.size();
		this.listaDeDocs = (ArrayList<Documento>) listaDeDocs;
	}
	public int getQtde() {
		return qtde;
	}
	

	
}
