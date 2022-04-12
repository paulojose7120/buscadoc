package managedbeans.janelas;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;

import managedbeans.ConsultaMB;
import managedbeans.EditaMB;
import util.TrataCPF;

@Named
@SessionScoped
public class JanelaEditaMB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7871934287487490553L;
	private String nome;
	private String itemSelecao;
	private boolean campoNomeDesabilitado;

	private boolean campoCPFDesabilitado;
	private String campoCpf;
	private boolean ajaxRender = true;


	@Inject
	private EditaMB edita;
	private String path;
	
	

	@PostConstruct
	public void init() {
		campoCPFDesabilitado = true;
		ajaxRender = true;
		this.path = getUrlPath();
	

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
			
			
		edita.consultaBD(nome, itemSelecao);
	
		}
		
		else if(itemSelecao.equals("cpf")){
				System.out.println(campoCpf);
					TrataCPF trata = new TrataCPF();
					String cpfSemPontos = trata.extrairApenasNumeros(campoCpf);
				//	System.out.println(cpfSemPontos);
 				
				edita.consultaBD(cpfSemPontos,itemSelecao);
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
