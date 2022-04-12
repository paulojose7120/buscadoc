package factoryArquivo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.itextpdf.text.DocumentException;

import paciente.Paciente;
import vacinacao.Documento;

public class FactoryDeArquivo {
	ArrayList<FileUploadEvent> listaDeEventos;
	Paciente paciente;
	
	Arquivo arquivo;
	ArrayList<Documento> documentos = new ArrayList<Documento>();
	
/*	public FactoryDeArquivo(ArrayList<FileUploadEvent> listaDeEventos, Paciente paciente) {
	this.listaDeEventos = listaDeEventos;	
	this.paciente = paciente;
	}*/
	
	public FactoryDeArquivo() {
	
	}

	public Arquivo create(FileUploadEvent event) throws FileNotFoundException, DocumentException {
		System.out.println(event.getFile().getContentType());
		switch(event.getFile().getContentType()) {
			
			
			case "application/pdf":
				this.arquivo = new ArqPDF();
				this.arquivo.criarArquivoTmp(event);
				break;
			
			case "image/jpeg":
				this.arquivo = new ArqImagem();
				this.arquivo.criarArquivoTmp(event);
				break;

		}
		return this.arquivo;	
	}
	
	
	public void addPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
	/*
	public ArrayList<Documento> create() {
		
		for(int i = 0; i <listaDeEventos.size(); i++) {
			UploadedFile arquivoArrayList = listaDeEventos.get(i).getFile();
				if(!arquivoArrayList.getContentType().equals("application/pdf")) {
					System.out.println("NÃO É PDF");
					System.out.println("Lista de eventos"+listaDeEventos.size());
				arquivo = new ArqImagem();
				documentos.add(arquivo.criar(listaDeEventos.get(i), paciente));
				
				}else {
					
				arquivo = new ArqPDF();
				documentos.add(arquivo.criar(listaDeEventos.get(i), paciente));
					
				}
		}
			
		return documentos;
		
		
		
	}	*/	
}
