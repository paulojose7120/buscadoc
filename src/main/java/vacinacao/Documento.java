package vacinacao;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class Documento{

	
	

	private String url;
	private int id;
	private long tamanho;
	private String dataDeUpload;
	private String nome;


	public Documento(String url, int id) {
		this.url = url;
		this.id=id;
		
	}
	
	public Documento(String url, int id, long tamanho){
		this.url = url;
		this.id = id;
		this.setTamanho(tamanho);
	}
	
	public Documento(String url, String nome, int id, long tamanho, String dataDeUpload){
		this.url = url;
		this.nome = nome;
		this.id = id;
		this.setTamanho(tamanho);
		this.dataDeUpload = dataDeUpload;
	}
	
	public Documento() {}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public long getTamanho() {
		return tamanho;
	}

	public void setTamanho(long tamanho) {
		this.tamanho = tamanho;
	}

	public String getDataDeUpload() {
		return dataDeUpload;
	}

	public void setDataDeUpload(String dataDeUpload) {
		this.dataDeUpload = dataDeUpload;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
