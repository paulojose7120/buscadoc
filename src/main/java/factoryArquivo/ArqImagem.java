package factoryArquivo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.primefaces.event.FileUploadEvent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import paciente.Paciente;
import util.PropertiesImpl;
import vacinacao.Documento;

public class ArqImagem implements Arquivo {

//	private String diretorioTmp = "C:\\Users\\Paulo\\Desktop\\WebContent\\tmp\\";
//	private String diretorio = "C:\\Users\\Paulo\\Desktop\\WebContent\\";
	
	
	private String diretorioTmp = "C:\\Users\\Paulo\\eclipse-workspace\\getDoc-vacina\\src\\main\\webapp\\WEB-INF\\WorkDrive\\tmp\\";
	private String diretorio = "C:\\Users\\Paulo\\eclipse-workspace\\getDoc-vacina\\src\\main\\webapp\\WEB-INF\\WorkDrive\\";
	
	
	private Documento documento;
	ArrayList<FileUploadEvent> events;
	
/*	public Documento criar(FileUploadEvent event, Paciente paciente){
		
		String url = "C:\\Users\\Paulo\\Desktop\\WebContent\\";
		String diretorioDoPaciente = paciente.getCpf().substring(0, 4)+paciente.getNome().substring(0, 3);
		new File(url+diretorioDoPaciente).mkdir();
		
		 System.out.println("bytes vazio"+event.getFile().getContent().length);
		
		
	
			
			String nomeArquivo = "\\"+event.getFile().getFileName()+".pdf";
			String fullPathDocs = url+diretorioDoPaciente+nomeArquivo;
		
		     try {
		
		 Document doc = new Document();
		  doc.open();
		 FileOutputStream out = new FileOutputStream(fullPathDocs);
		 PdfWriter.getInstance(doc, out);    
	   
	     
   
            
            ByteArrayInputStream bais = new ByteArrayInputStream(event.getFile().getContent());
           
            Image imagemItext = Image.getInstance(ImageIO.read(bais), Color.white);
           
            doc.add(imagemItext);
            
          doc.close();
          Documento docto =new Documento();
        docto.setUrl(fullPathDocs);
       documento = docto;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return documento;}*/

	@Override
	public Documento criarArquivoTmp(FileUploadEvent event) throws DocumentException, FileNotFoundException {
		
		
			byte[] arrayDeBytes = comprimirImagem(event);
	
		
		
		 Document doc = new Document();
		 FileOutputStream out = new FileOutputStream(this.diretorioTmp+event.getFile().getFileName()+".pdf");
		 PdfWriter.getInstance(doc, out);    
	     doc.open();
	     
         try {
            
            ByteArrayInputStream bais = new ByteArrayInputStream(arrayDeBytes);
            
            Image imagemItext = Image.getInstance(ImageIO.read(bais), Color.white);
            float alturaImagem = imagemItext.getHeight();
            float largura = imagemItext.getWidth();
            
          
            doc.getPageSize();
            imagemItext.setCompressionLevel(9);
            
            imagemItext.scaleToFit(com.itextpdf.text.PageSize.A4.getWidth()-50, com.itextpdf.text.PageSize.A4.getHeight());
            
            doc.add(imagemItext);
            
          doc.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return documento; 
		
		
	}
		
	private byte[] comprimirImagem(FileUploadEvent event) {
	BufferedImage buffer = null;
	try {
		buffer = ImageIO.read(event.getFile().getInputStream());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Dimension newMaxSize = new Dimension(1000, 1500);
	BufferedImage resizedImg = Scalr.resize(buffer, Method.QUALITY,
	                                        newMaxSize.width, newMaxSize.height);

	
	  ByteArrayOutputStream baos = new ByteArrayOutputStream();
	  try {
		ImageIO.write(resizedImg, "jpg", baos);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  byte[] bytes = baos.toByteArray();
	
	
	
	
	return bytes;
}

	
	
	
	
	public void CopiarParaPastaDefinitiva(Paciente paciente, ArrayList<FileUploadEvent> events) throws FileNotFoundException {
		this.events = events;
		File pastaDefinitiva = new File(diretorio+"\\"+paciente.getCpf().substring(0, 4)+paciente.getNome().substring(0, 3));
		System.out.println("TAMANHO DA LISTA"+events.size());
		
		pastaDefinitiva.mkdir();
		for(int i = 0; i < events.size(); i++) {
		
	

		

			   FileInputStream bais = new FileInputStream(diretorioTmp+events.get(i).getFile().getFileName()+".pdf");
			   
			     
	           
		       	OutputStream out = new FileOutputStream(pastaDefinitiva.getAbsolutePath()+"\\"+events.get(i).getFile().getFileName()+".pdf");
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
	}
	
		
		private void deleteArquivo() {
		for(int i = 0; i < events.size(); i++) {
			
			try {
				
			boolean b=	Files.deleteIfExists(new File(diretorioTmp+events.get(i).getFile().getFileName()+".pdf").toPath());		
			System.out.println(b);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
}