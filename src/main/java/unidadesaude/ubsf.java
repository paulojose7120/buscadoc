package unidadesaude;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ubsf implements UnidadeSaude {
	
	private String nome;
	private int id;
	private String email;
	
	public ubsf(String nome) {
		this.nome = nome;	}
	
	public ubsf(String nome, int id) {
		this.nome = nome;
		this.id = id;
	}
	
	public ubsf(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	
	public ubsf(String nome, int id, String email) {
		this.nome = nome;
		this.id = id;
		this.email = email;
	}
	
	public ubsf() {}
	
	@Override
	public void setNome(String nome) {
		this.nome = nome;
		
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}

	@Override
	public void setId(int id) {
		this.id = id;
		
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
		
	}

	@Override
	public String getEmail() {
	
		return this.email;
	}

}
