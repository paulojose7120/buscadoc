package managedbeans;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class MenuNavegacaoMB {
	
	@Inject
	JanelaCPFeNome janela;

	public String inicio() throws IOException {
		System.out.println(FacesContext.getCurrentInstance());
		  FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.xhtml");
		 
		
		   return "/adicionar.xhtml";
		
	}
	
	
	
	public String adicionar() throws IOException {
		System.out.println(FacesContext.getCurrentInstance());
		  FacesContext.getCurrentInstance().getExternalContext().redirect("adicionar.xhtml");
		   return "/adicionar.xhtml";
		
	}
	
	
	public String editar() throws IOException {
		System.out.println(FacesContext.getCurrentInstance());
		  FacesContext.getCurrentInstance().getExternalContext().redirect("editar.xhtml");
		   return "/editar.xhtml";
		
	}
	
	
	public String remover() throws IOException {
		System.out.println(FacesContext.getCurrentInstance());
		  FacesContext.getCurrentInstance().getExternalContext().redirect("remover.xhtml");
		   return "/remover.xhtml";
		
	}
	
	public String consultar() throws IOException {
		System.out.println(FacesContext.getCurrentInstance());
		  FacesContext.getCurrentInstance().getExternalContext().redirect("consultar.xhtml");

		   return "/consultar.xhtml";
		
	}
	

	
	
	
	
	
	
	
	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
}
