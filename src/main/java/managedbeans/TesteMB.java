package managedbeans;




import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;



import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class TesteMB implements Serializable{

	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;

	

public TesteMB() {}
	
	
	public void comando() {
		System.out.println(nome);
		


	}
	
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
