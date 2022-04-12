package managedbeans.janelas;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import paciente.Paciente;
import vacinacao.Documento;

@Named
public class Upmb implements Serializable {

	
	
	private ArrayList<FileUploadEvent>listaDeEventos = new ArrayList<FileUploadEvent>();
	private ArrayList<Documento>documentos = new ArrayList<Documento>();
	
	private static final long serialVersionUID = 1L;

	private void criarPDF(Paciente paciente) throws FileNotFoundException, DocumentException {
		
		
		
		
		
		
		
	
		
		String url = "C:\\Users\\Paulo\\Desktop\\WebContent\\";
		String diretorioDoPaciente = paciente.getCpf().substring(0, 4)+paciente.getNome().substring(0, 3);
		new File(url+diretorioDoPaciente).mkdir();
		
		
		
		
		for(int i=0; i<listaDeEventos.size();i++) {
			
			String nomeArquivo = "\\"+listaDeEventos.get(i).getFile().getFileName()+".pdf";
			String fullPathDocs = url+diretorioDoPaciente+nomeArquivo;
		 Document doc = new Document();
		 doc.open();
		     try {
		
		
		 FileOutputStream out = new FileOutputStream(fullPathDocs);
		 PdfWriter.getInstance(doc, out);    
	    
	     
   
            
            ByteArrayInputStream bais = new ByteArrayInputStream(listaDeEventos.get(i).getFile().getContent());
            
            Image imagemItext = Image.getInstance(ImageIO.read(bais), Color.white);
           
        //    imagemItext.scaleToFit(com.itextpdf.text.PageSize.A4.getWidth()-50, com.itextpdf.text.PageSize.A4.getHeight());
            
            doc.add(imagemItext);
            
        
          Documento docto =new Documento();
          docto.setUrl(fullPathDocs);
          doc.close();
         documentos.add(docto);
        } catch (IOException e) {
            e.printStackTrace();
        } 
		}}
	
	
}
