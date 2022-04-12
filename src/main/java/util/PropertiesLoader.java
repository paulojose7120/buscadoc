package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesLoader {

	private Properties props;

	private String nomeDoProperties = "C:/Users/Paulo/eclipse-workspace/getDoc-vacina/src/main/webapp/seguranca/url.properties";
	
	
	
	
	protected PropertiesLoader() throws IOException {
        props = new Properties();
        InputStream in = null;
		try {
		in= new FileInputStream(new File(nomeDoProperties));
			
		BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
		
		
		  props.load(buffer);
          in.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	  protected String getValor(String chave){
          return (String)props.getProperty(chave);
  }
	
	
	
}
