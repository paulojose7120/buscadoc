package managedbeans.janelas;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;

import org.primefaces.PrimeFaces;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import factoryArquivo.FactoryDeArquivo;
import jdbc.*;
import managedbeans.InserirMB;
import managedbeans.Upload;
import paciente.Paciente;
import util.Data;
import util.TrataCPF;
import vacinacao.Documento;


@Named
@SessionScoped
public class JanelaInserirMBClone implements Serializable{

	public InserirMB getInserirBean() {
		return inserirBean;
	}


	public void setInserirBean(InserirMB inserirBean) {
		this.inserirBean = inserirBean;
	}


	public ArrayList<FileUploadEvent> getFileUploadEvent() {
		return FileUploadEvent;
	}


	public void setFileUploadEvent(ArrayList<FileUploadEvent> fileUploadEvent) {
		FileUploadEvent = fileUploadEvent;
	}


	public ArrayList<Documento> getDocumentos() {
		return documentos;
	}


	public void setDocumentos(ArrayList<Documento> documentos) {
		this.documentos = documentos;
	}


	public ArrayList<FileUploadEvent> getListaDeEventos() {
		return listaDeEventos;
	}


	public void setListaDeEventos(ArrayList<FileUploadEvent> listaDeEventos) {
		this.listaDeEventos = listaDeEventos;
	}


	public void setQtdDocs(int qtdDocs) {
		this.qtdDocs = qtdDocs;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = -8795489251530840955L;


	@Inject
	private Upload imagem;
	@Inject 
	InserirMB inserirBean;
	private String nome;
	private String cpf;
	private String sus;
	private int qtdDocs = 0;

	private ArrayList<FileUploadEvent> FileUploadEvent;
	private ArrayList<Documento> documentos;
	private ArrayList<FileUploadEvent>listaDeEventos;
	private FileUploadEvent event;

	
	
	
	
	public FileUploadEvent getEvent() {
		return event;
	}


	public void setEvent(FileUploadEvent event) {
		this.event = event;
	}


	@PostConstruct
	public void init() {
		FileUploadEvent =  new ArrayList<>() ;
		documentos = new ArrayList<>();
		listaDeEventos = new ArrayList<FileUploadEvent>();
	}
	

	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void somaDoc() {
		qtdDocs++;
	}

	
	public void verificaCPF(){
		TrataCPF tratacpf = new TrataCPF();
		
		if(!verificaCPFExclusivo(tratacpf.extrairApenasNumeros(cpf))) {
 
			PrimeFaces.current().executeScript("PF('confirmacao').show();");
			
		
			
		}else {
			
			
			botaoClicado();			
		}
	}
	
	public void redireciona() throws IOException {
		
		 FacesContext.getCurrentInstance().getExternalContext().redirect("editar.xhtml");
		 
		 
	}
	
	public void botaoClicado() {
	
		PrimeFaces.current().executeScript("PF('confirmacao').hide();");
		PrimeFaces.current().executeScript("PF('dlg2').hide();");
		
		 
		
	
	
	
		
	}
		private boolean verificaCPFExclusivo(String cpf) {
		
		boolean pacienteExclusivo = false;
		//VERIFICAR EXCLUSIVIDADE
			PacienteDAO pacienteDao = new PacienteDaoImp();
			if(pacienteDao.getListaDePacientes(cpf).isEmpty()) {
				pacienteExclusivo = true;
			}
		
			return pacienteExclusivo;
	}

		


		
		
		
		public String getNome() {
			return nome;
		}


		public void setNome(String nome) {
			this.nome = nome;
		}


		public String getSus() {
			return sus;
		}


		public void setSus(String sus) {
			this.sus = sus;
		}


		public Upload getImagem() {
			return imagem;
		}


		public void setImagem(Upload imagem) {
			this.imagem = imagem;
		}

		public void zeraArquivos() {
			qtdDocs = 0;
			FileUploadEvent.clear();
		}


		public void handleFileUpload(FileUploadEvent event) throws MalformedURLException, IOException, DocumentException {

	//		FileUploadEvent.add(event);
			this.event = event;
			
			qtdDocs += 1;
			
			}
			

		
		
		private void criarPDF(Paciente paciente) throws FileNotFoundException, DocumentException, IOException {
					
			
			
			
			
				   // Creating a PdfWriter      
			

				 Document doc = new Document();
				 FileOutputStream out = new FileOutputStream("D:\\"+Data.getDataString()+".pdf");
				 PdfWriter.getInstance(doc, out);    
			     doc.open();
			     
		     
		        	
		        	ByteArrayInputStream bais = new ByteArrayInputStream(event.getFile().getContent()); 
			  Image imagemItext = Image.getInstance(ImageIO.read(bais), Color.white);
		       System.out.println("HEIGHT"+imagemItext.getHeight());
		       
				
		
				
				
      

				
       
      //   doc.add(imagemItext);
				out.close();
				bais.close();
				doc.close(); 
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			/*
			
			
			FactoryDeArquivo fabrica = new FactoryDeArquivo(FileUploadEvent, paciente);
			fabrica.create();
			
			documentos = fabrica.create();
			
			
			*/
			
			
			
			/*
			
			
		
			
			String url = "C:\\Users\\Paulo\\Desktop\\WebContent\\";
			String diretorioDoPaciente = paciente.getCpf().substring(0, 4)+paciente.getNome().substring(0, 3);
			new File(url+diretorioDoPaciente).mkdirs();
			
			
			
			
			for(int i=0; i<listaDeEventos.size();i++) {
				
				String nomeArquivo = "\\teste.pdf";
				String fullPathDocs = url+diretorioDoPaciente+nomeArquivo;
			 Document doc = new Document();
			
			     try {
			
			
			 FileOutputStream out = new FileOutputStream(fullPathDocs);
			 PdfWriter.getInstance(doc, out);    
		     doc.open();
		     
	   
	            
	            ByteArrayInputStream bais = new ByteArrayInputStream(listaDeEventos.get(i).getFile().getContent());
	              bais.close();
	            Image imagemItext = Image.getInstance(ImageIO.read(bais), Color.white);
	           
	           imagemItext.scaleToFit(com.itextpdf.text.PageSize.A4.getWidth()-50, com.itextpdf.text.PageSize.A4.getHeight());
	          
	            doc.add(imagemItext);
	              doc.close();
	        
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
			}}*/



		public int getQtdDocs() {
			return qtdDocs;
		}
		


		public void cadastrar() throws DocumentException, IOException {
			FacesContext facesContextInstance =  FacesContext.getCurrentInstance();
			if((!nome.isEmpty())&& (!cpf.isEmpty())) {
				Paciente paciente = new Paciente();
				
				
				paciente.setNome(nome);
				paciente.setSus(sus);
				paciente.setListaDeDocs(documentos);
				
				paciente.setCpf(cpf);
				criarPDF(paciente);
				
				inserirBean.setPaciente(paciente);
				inserirBean.consultaBD();

				qtdDocs = 0;
				listaDeEventos.clear();
				 documentos.clear();
				
				PrimeFaces.current().executeScript("PF('sucesso').show();");
			}else {
			facesContextInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo obrigatório vazio!", "Por favor, preencha todos os campos vazios."));
			
			}
			


			
			
		}


			


	
}
