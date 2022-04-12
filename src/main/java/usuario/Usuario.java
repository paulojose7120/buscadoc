package usuario;

public class Usuario {

	
	
	private String nome, login, senha, cargo, matricula, email;
	private int nivel, id;
	
	
	public Usuario(String nome, String login, String senha, String cargo, String matricula,int nivel, String email, int id) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.cargo = cargo;
		this.matricula = matricula;
		this.setNivel(nivel);
		this.setEmail(email);
		this.setId(id);
	}
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
		
		
	}

	
	public Usuario() {}
	

	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
