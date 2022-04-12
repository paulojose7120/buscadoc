package factoryArquivo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.primefaces.event.FileUploadEvent;

import com.itextpdf.text.DocumentException;

import paciente.Paciente;
import vacinacao.Documento;

public interface Arquivo {

	
	//public Documento criar(FileUploadEvent event, Paciente paciente);
	
	public Documento criarArquivoTmp(FileUploadEvent event) throws FileNotFoundException, DocumentException;
	public void CopiarParaPastaDefinitiva(Paciente paciente, ArrayList<FileUploadEvent> events) throws FileNotFoundException, DocumentException;

}
