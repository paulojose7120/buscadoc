package managedbeans;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFiles;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;






@Named
@SessionScoped
public class Upload implements Serializable{


	
	private static final long serialVersionUID = 6568841552325646811L;


	

		
		public void handleFileUpload(FileUploadEvent event) throws MalformedURLException, IOException, DocumentException {

			byte[] b = event.getFile().getContent();
			criarPDF(b, event);
			
			}

		private void criarPDF(byte[] b, FileUploadEvent event) throws FileNotFoundException, DocumentException {
			   // Creating a PdfWriter      
			System.out.println("bytes "+b.length);
			System.out.println(event.getFile().getSize());
			 Document doc = new Document();
			 FileOutputStream out = new FileOutputStream("C:\\Users\\Paulo\\Desktop\\WebContent\\"+event.getFile().getFileName()+".pdf");
			 PdfWriter.getInstance(doc, out);    
		     doc.open();
		     
	        try {
	            
	            ByteArrayInputStream bais = new ByteArrayInputStream(event.getFile().getContent());
	            
	            Image imagemItext = Image.getInstance(ImageIO.read(bais), Color.white);
	            float alturaImagem = imagemItext.getHeight();
	            float largura = imagemItext.getWidth();
	            
	            System.out.println(alturaImagem+" - "+imagemItext.getWidth());
	            doc.getPageSize();
	            
	            imagemItext.scaleToFit(com.itextpdf.text.PageSize.A4.getWidth()-50, com.itextpdf.text.PageSize.A4.getHeight());
	            
	            doc.add(imagemItext);
	            
	          doc.close();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
		}
		private Rectangle configurarLandScape(float largura, float alturaImagem) {
			Rectangle landscape = null;
			if(largura > alturaImagem) {
				
			
				landscape = PageSize.A4.rotate();
			
		}
		return landscape;

		}
		
	
	
	
}
