package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.SerializableSupplier;

import paciente.Paciente;
import vacinacao.Documento;

import org.primefaces.model.DefaultStreamedContent.Builder;
@Named
@SessionScoped
public class FileDownloaderMB implements Serializable{

	private static final long serialVersionUID = 1L;
	private StreamedContent file;
	final private String folder = "/WEB-INF/WorkDrive/";
	private String folder2 = "C://Users//Paulo//Desktop//WebContent//tmp//20190429_075909.pdf";
	private String folder3 = "C:\\Users\\Paulo\\Desktop\\WebContent\\tmp\\20190429_075909.pdf";
	private String nomeArquivo;		
	
	private Paciente paciente;


	public void DocumentacaoController() throws FileNotFoundException  {  
	
		
			 InputStream arquivo = new FileInputStream(new File(PropertiesImpl.getValor("pathworkspacetmp")+"20190429_075909.pdf"));
			 file = DefaultStreamedContent.builder()
		                .name("teste.pdf")
		                .contentType("application/pdf")
		                .stream(() -> arquivo)
		                .build();
			
			System.out.println("ARQUIVO NULO "+file.equals(null));
		
		
	     
	}  

	public StreamedContent getFile()  {  
	//	System.out.println("IMPRIMINF PROPERTIES"+PropertiesImpl.getValor("downloadFolder"));
		try {
			DocumentacaoController();
			
		} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("ARQUIVO NULO "+file.equals(null));
		
	    return this.file;  
	}

	public Paciente getPaciente() {
		System.out.println(file.equals(null));
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}  

 
	/*
	public StreamedContent getFile() throws FileNotFoundException {
	    try{
	    	System.out.println("BOTAO DONWLOAD APERTADO");
	        InputStream streamFile = new FileInputStream("C:\\Users\\Paulo\\Desktop\\WebContent\\000.Pau\\01");
	        this.file =  DefaultStreamedContent.builder().name("teste").contentType("application/pdf")  .stream(() -> this.getClass().getResourceAsStream("C:\\Users\\Paulo\\Desktop\\WebContent\\000.Pau\\01.pdf")).build();
	      System.out.println("TAMANHO"+file.getContentLength());
	    } catch (IOException erro) {
	     
	    }
	    return file;
	}
*/

	
	public void itemSelecionado(Documento p) {
		
		
		nomeArquivo = p.getNome();
		
		
		
	}
	
	
	
	
}
