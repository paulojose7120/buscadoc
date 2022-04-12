package managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;
import factory.TipoBeanFactory;
import jdbc.PacienteDAO;
import jdbc.PacienteDaoImp;
import util.TrataCPF;

@SessionScoped
@Named
public class JanelaCPFeNome implements Serializable {



	private String nome;
	private String itemSelecao;
	private boolean campoNomeDesabilitado;
	
	private boolean campoCPFDesabilitado;
	private String campoCpf;
	private boolean ajaxRender = true;

	


	@Inject
	private ConsultaMB consulta;
	private String path;

		
	
	
	private static final long serialVersionUID = -44031425231181345L;




	@PostConstruct
	public void init() {
	
		
		campoCPFDesabilitado = true;
		ajaxRender = true;
		this.path = getUrlPath();
		System.out.println("NOME DIGITADO: "+nome);
	

	}

		private String getUrlPath() {
		HttpServletRequest servletRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String fullURI = servletRequest.getRequestURI();
		System.out.println(fullURI);
		return fullURI;
	}
	
	public void alteraCampoDeSelecao() {
		System.out.println(this.itemSelecao);
		campoNomeDesabilitado = true;
		campoCPFDesabilitado = true;
		
		
	if(itemSelecao.equals("nome")){
		this.setCampoNomeDesabilitado(false); 
		this.setCampoCPFDesabilitado(true);
		this.setCampoCpf("");
		System.out.println("NOME DESABILITADO "+campoNomeDesabilitado);
		System.out.println("CPF DESABILITADO "+campoCPFDesabilitado);
		
	}if(itemSelecao.equals("cpf")) {
		this.setCampoNomeDesabilitado(true);
		this.setCampoCPFDesabilitado(false);
		this.setNome("");
		System.out.println("NOME DESABILITADO "+campoNomeDesabilitado);
		System.out.println("CPF DESABILITADO "+campoCPFDesabilitado);
	}
	
	
		
	/*	if(e.getNewValue() =="nome") {
			this.campoNomeDesabilitado = false;
			this.campoCPFDesabilitado = true;
			
		}else {
			this.campoNomeDesabilitado = true;
			this.campoCPFDesabilitado = false;
		}*/
}
	

	
	public void botaoClicado() {
		System.out.println("MODO DE QUE FOI SELECIONADO:"+itemSelecao);
		System.out.println("CAMPO CPF DESABILITADO"+this.campoCPFDesabilitado);
		System.out.println("TEXTO CAMPO CPF:"+this.campoCpf);
		
	
		/*Condicional que será executado se o combobox NOME estiver selecionado:*/
		
		if(itemSelecao.equals("nome")) {
			PrimeFaces.current().executeScript("PF('dlg2').hide()");
			
			
		consulta.consultaBD(nome, itemSelecao);

		
		}
		
		else if(itemSelecao.equals("cpf")){
				System.out.println(campoCpf);
					
				//	System.out.println(cpfSemPontos);
 				
				consulta.consultaBD(campoCpf,itemSelecao);
					PrimeFaces.current().executeScript("PF('dlg2').hide()");
					
		}
		}

	public boolean isCampoNomeDesabilitado() {
		return campoNomeDesabilitado;
	}


	public void setCampoNomeDesabilitado(boolean campoNomeDesabilitado) {
		this.campoNomeDesabilitado = campoNomeDesabilitado;
	}






	public boolean isCampoCPFDesabilitado() {
		return campoCPFDesabilitado;
	}






	public void setCampoCPFDesabilitado(boolean campoCPFDesabilitado) {
		this.campoCPFDesabilitado = campoCPFDesabilitado;
	}





	public String getItemSelecao() {
		return itemSelecao;
	}


	public void setItemSelecao(String itemSelecao) {
		this.itemSelecao = itemSelecao;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCampoCpf() {
		return campoCpf;
	}


	public void setCampoCpf(String campoCpf) {
		this.campoCpf = campoCpf;
	}



	public boolean isAjaxRender() {
		return ajaxRender;
	}



	public void setAjaxRender(boolean ajaxRender) {
		this.ajaxRender = ajaxRender;
	}



	
	
	
	
	
	
}
