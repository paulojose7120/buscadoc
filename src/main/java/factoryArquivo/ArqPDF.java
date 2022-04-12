package factoryArquivo;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import paciente.Paciente;
import vacinacao.Documento;

public class ArqPDF implements Arquivo {
	private String diretorioTmp = "C:\\Users\\Paulo\\Desktop\\WebContent\\tmp\\";
	private String diretorio = "C:\\Users\\Paulo\\Desktop\\WebContent\\";
	private Documento documento;
	private ArrayList<FileUploadEvent> FileUploadEvent = new ArrayList<FileUploadEvent>();
/*	
	@Override
public Documento criar(FileUploadEvent event, Paciente paciente) {
		String url = "C:\\Users\\Paulo\\Desktop\\WebContent\\";
		String diretorioDoPaciente = paciente.getCpf().substring(0, 4)+paciente.getNome().substring(0, 3);
		new File(url+diretorioDoPaciente).mkdir();
		
		
		
		
	
			
			String nomeArquivo = "\\"+event.getFile().getFileName()+".pdf";
			String fullPathDocs = url+diretorioDoPaciente+nomeArquivo;
		
		     try {
		
	
		 
		    	 
		    	 
		    	 
		    	 
		    	 
           ByteArrayInputStream bais = new ByteArrayInputStream(event.getFile().getContent());
          
     
           
       	OutputStream out = new FileOutputStream(fullPathDocs);
    	byte[] buf = new byte[1024];
    	int len;
    	while ((len = bais.read(buf)) > 0) {
    		out.write(buf, 0, len);
    	}
    	bais.close();
    	out.close();
           
       
          Documento docto =new Documento();
        docto.setUrl(fullPathDocs);
       this.documento = docto;
        } catch (IOException e) {
            e.printStackTrace();
        
			e.printStackTrace();
		} 

		return documento;
	}*/

	@Override
	public Documento criarArquivoTmp(FileUploadEvent event) {
				FileUploadEvent.add(event);
	
			  	try {
					try (InputStream bais = new BufferedInputStream(event.getFile().getInputStream())) {
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					OutputStream out = new FileOutputStream(diretorioTmp+"\\"+event.getFile().getFileName()+".pdf");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	byte[] buf = new byte[1024];
		    	
		    	
		    	
		    	
		    	
		return documento;
	}

	
	
	
	public void CopiarParaPastaDefinitiva(Paciente paciente)throws FileNotFoundException, DocumentException {
		File pastaDefinitiva = new File(diretorio+"\\"+paciente.getCpf().substring(0, 4)+paciente.getNome().substring(0, 3));
		
		
		pastaDefinitiva.mkdir();
		for(int i = 0; i < FileUploadEvent.size(); i++) {
		
		System.out.println();

		

			   FileInputStream bais = new FileInputStream(diretorioTmp+FileUploadEvent.get(i).getFile().getFileName()+".pdf");
			   
			     
	           
		       	OutputStream out = new FileOutputStream(pastaDefinitiva.getAbsolutePath()+"\\"+FileUploadEvent.get(i).getFile().getFileName()+".pdf");
		    	byte[] buf = new byte[1024];
		    	int len;
		    	try {
					while ((len = bais.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	try {
					bais.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		           
	}
	
	
	
	}}
