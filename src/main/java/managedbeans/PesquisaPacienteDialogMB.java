package managedbeans;


import java.io.Serializable;
import java.util.concurrent.Callable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.weld.context.RequestContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.dialog.Dialog;

import util.Data;
import util.TrataCPF;




@Named
@SessionScoped
public class PesquisaPacienteDialogMB  implements Serializable{


	private String nome;
	private String itemSelecao;
	private boolean campoNomeDesabilitado;
	private boolean campoCPFDesabilitado;
	private String campoCpf;
	private boolean ajaxRender = true;
	private String nomePaciente;
	private String nomeMae;
	private String cpf;
	private String dataNasc;
	
	
	
	
	
	private static final long serialVersionUID = -1959931207415412383L;

	@PostConstruct
	public void init() {
		campoCPFDesabilitado=true;
		ajaxRender = true;
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
	

	
	public void botaoClicado() throws Exception {
		

		
		/*Condicional que será executado se o combobox NOME estiver selecionado:*/
		
		if(itemSelecao.equals("nome")) {
			
		
			
				/*testa se no campo NOME foi digitado algo, se sim executa o comando javascript
				 * que fecha a janela */
				if(nome != null && !nome.isEmpty()) {
				chamaLoading();
				
				System.out.println("Entrou");
				System.out.println(campoNomeDesabilitado);
				System.out.println(nome == null);
				System.out.println(nome);
				chamaLoading();
				
				}
				
				else 
				{
					
					System.out.println("Não entrou");
					System.out.println(campoNomeDesabilitado);
					System.out.println(nome);
					
					FacesContext.getCurrentInstance().
	                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nome inválido!", "Por favor, digite um nome válido."));
					}
		}
		
		else if(itemSelecao.equals("cpf")){
				if(campoCpf.equals("")) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"CPF inválido!", "Por favor, digite um cpf válido."));
						
					 System.out.println("CPF selecionado");
					 System.out.println(this.campoCpf);	
					
					
				}else {
					TrataCPF trata = new TrataCPF();
					String cpfSemPontos = trata.extrairApenasNumeros(campoCpf);
					System.out.println(cpfSemPontos);
					
						 
		
			Runnable  c = new Runnable() {


				@SuppressWarnings("null")
				@Override
				public void run() {
					 
					
					for(int i=0; i<900000;i++) {
						System.out.println(i);
						
					}
					PrimeFaces.current().executeScript("PF('dlg2').hide()");
					
					
					
					
					
			}};
				
				c.run();
					
					
		
						
				
					
				}
		
		
		}
	}


	private void chamaLoading() {
	/* PrimeFaces.current().executeScript("PF('dlg2').hide();");*/
	 PrimeFaces.current().executeScript("PF('carregando').show();");
	}
	
	
	public void encerraLoading() {
		
		PrimeFaces.current().executeScript("PF('carregando').hide();");
		System.out.println("FECHA!");
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
