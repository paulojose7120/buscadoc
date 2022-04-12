package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.checkerframework.common.reflection.qual.GetClass;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.SerializableSupplier;

public class TesteUtil {

	private static InputStream input;
	

	public static void main(String[] args) throws IOException {
		
    //	File arquivo= new File("src/main/webapp/WEB-INF/teste/01.txt");
	//	System.out.println(arquivo);
		System.out.println(PropertiesImpl.getValor("pathworkspacetmp"));
		
		
		File arquivo = new File(PropertiesImpl.getValor("pathworkspacetmp")+"20190429_075909.pdf");
		System.out.println(arquivo.exists());

		System.out.println(System.getProperty("user.dir"));
		
}
}