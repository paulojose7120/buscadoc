package managedbeans.janelas;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.nio.file.Files;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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

import factoryArquivo.Arquivo;
import factoryArquivo.Factory;
import factoryArquivo.FactoryDeArquivo;
import jdbc.*;
import managedbeans.InserirMB;
import managedbeans.Upload;
import paciente.Paciente;
import util.Data;
import util.PropertiesImpl;
import util.TrataCPF;
import vacinacao.Documento;


@Named
@SessionScoped
public class JanelaInserirMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	


	@Inject
	private Upload imagem;
	@Inject 
	InserirMB inserirBean;
	private String nome;
	private String cpf;
	private String sus;
	private int qtdDocs = 0;
	//private String diretorioTmp = "C:\\Users\\Paulo\\Desktop\\WebContent\\tmp\\";
	//	private String diretorio = "C:\\Users\\Paulo\\Desktop\\WebContent\\";
	
	
	private String diretorioTmp = "C:\\Users\\Paulo\\eclipse-workspace\\getDoc-vacina\\src\\main\\webapp\\WEB-INF\\WorkDrive\\tmp\\";
	private String diretorio = "C:\\Users\\Paulo\\eclipse-workspace\\getDoc-vacina\\src\\main\\webapp\\WEB-INF\\WorkDrive\\";
	
	
	
	private ArrayList<FileUploadEvent> FileUploadEvent = new ArrayList<FileUploadEvent>();
	private ArrayList<Documento> documentos;
	private ArrayList<UploadedFile>listaArquivos = new ArrayList<UploadedFile>();
	private ArrayList<byte[]>listaBytes = new ArrayList<byte[]>();
	private FileUploadEvent event;
	FactoryDeArquivo fabricaArquivo = new FactoryDeArquivo();
	private Image imagemItext;
	private boolean desabled;
	private boolean checkBoxDesabled;
	
	
	private Arquivo arquivo ;
	
	
	public void checkBoxAlterado(ValueChangeEvent e) {
		System.out.println("CheckBOX Alterador!");
		if(checkBoxDesabled == true) {desabled = false;}else {desabled = true; cpf = "000.000.000-00";}
	}
	
	
	
	
	
	
	
	
	public FileUploadEvent getEvent() {
		return event;
	}


	public void setEvent(FileUploadEvent event) {
		this.event = event;
	}


	@PostConstruct
	public void init() {
	
		documentos = new ArrayList<>();
		
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
		
		
		if(!verificaCPFExclusivo(cpf) && !cpf.equals("000.000.000-00")) {
 
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
				System.out.println(cpf.equals("000.000.000-00"));
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


		private Rectangle configurarLandScape(float largura, float alturaImagem) {
			Rectangle landscape = null;
			if(largura > alturaImagem) {
				
			
				landscape = PageSize.A4.rotate();
			
		}
		return landscape;

		}
		

		public void handleFileUpload(FileUploadEvent event) throws MalformedURLException, IOException, DocumentException {
			
			FileUploadEvent.add(event);
			
			
			arquivo  = fabricaArquivo.create(event);
			
			
			qtdDocs++;
		//	criarArquivoTmp(event);
			
			
			}
			

		
		
		private void criarArquivoTmp(FileUploadEvent event) throws FileNotFoundException, DocumentException {
			
				   // Creating a PdfWriter      
				
				 System.out.println(event.getFile().getSize());
				 Document doc = new Document();
				 FileOutputStream out = new FileOutputStream(this.diretorioTmp+event.getFile().getFileName()+".pdf");
				 PdfWriter.getInstance(doc, out);    
			     doc.open();
			     
		         try {
		            
		            ByteArrayInputStream bais = new ByteArrayInputStream(event.getFile().getContent());
		            
		            Image imagemItext = Image.getInstance(ImageIO.read(bais), Color.white);
		            float alturaImagem = imagemItext.getHeight();
		            float largura = imagemItext.getWidth();
		            
		            System.out.println(alturaImagem+" - "+imagemItext.getWidth());
		            doc.getPageSize();
		            imagemItext.setCompressionLevel(9);
		            
		            imagemItext.scaleToFit(com.itextpdf.text.PageSize.A4.getWidth()-50, com.itextpdf.text.PageSize.A4.getHeight());
		            
		            doc.add(imagemItext);
		            
		          doc.close();
		            
		        } catch (IOException e) {
		            e.printStackTrace();
		        } 
			}
			
			
			
			
			


				private void criarPDF(Paciente paciente) throws DocumentException, IOException {
				File pastaDefinitiva = new File(diretorio+"\\"+paciente.getCpf().substring(0, 4)+paciente.getNome().substring(0, 3));
				
				
				pastaDefinitiva.mkdir();
				for(int i = 0; i < FileUploadEvent.size(); i++) {
				
				System.out.println();
	
				
	
					   FileInputStream bais = new FileInputStream(diretorioTmp+FileUploadEvent.get(i).getFile().getFileName()+".pdf");
					   
					     
			           
				       	OutputStream out = new FileOutputStream(pastaDefinitiva.getAbsolutePath()+"\\"+FileUploadEvent.get(i).getFile().getFileName()+".pdf");
				    	byte[] buf = new byte[1024];
				    	int len;
				    	while ((len = bais.read(buf)) > 0) {
				    		out.write(buf, 0, len);
				    	}
				    	bais.close();
				    	out.close();
				      
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
			
			
			public void criarArquivo(Paciente paciente) throws FileNotFoundException, DocumentException {
				this.arquivo.CopiarParaPastaDefinitiva(paciente, FileUploadEvent);
				for(int i= 0; i < FileUploadEvent.size(); i++) {
					UploadedFile arquivoUpload = FileUploadEvent.get(i).getFile();
				documentos.add(new Documento(diretorio+paciente.getCpf().substring(0, 4)+paciente.getNome().substring(0, 3)+"/"+FileUploadEvent.get(i).getFile().getFileName(),arquivoUpload.getFileName(),0, arquivoUpload.getSize(),Data.getDataString()));
				System.out.println( arquivoUpload.getSize());
				}
				
				
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

			public void deleteArquivosTmp() {
				for(int i = 0; i < FileUploadEvent.size(); i++) {
				
				try {
					
				boolean b=	Files.deleteIfExists(new File(diretorioTmp+FileUploadEvent.get(i).getFile().getFileName()+".pdf").toPath());		
				System.out.println(b);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}

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
		//		criarPDF(paciente);
				criarArquivo(paciente);
				deleteArquivosTmp();
				inserirBean.setPaciente(paciente);
				inserirBean.consultaBD();

				qtdDocs = 0;
				//listaDeEventos.clear();
				 documentos.clear();
				
				PrimeFaces.current().executeScript("PF('sucesso').show();");
			}else {
			facesContextInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo obrigat�rio vazio!", "Por favor, preencha todos os campos vazios."));
			
			}
	
		}


		public boolean isDesabled() {
			return desabled;
		}


		public void setDesabled(boolean desabled) {
			this.desabled = desabled;
		}


		public boolean isCheckBoxDesabled() {
			return checkBoxDesabled;
		}


		public void setCheckBoxDesabled(boolean checkBoxDesabled) {
			this.checkBoxDesabled = checkBoxDesabled;
		}


			


	
}
