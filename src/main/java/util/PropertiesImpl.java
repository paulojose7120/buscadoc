package util;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PropertiesImpl {

	
	   private static PropertiesLoader loader;   
	    public static String getValor(String chave){
	    	try {
				loader = new PropertiesLoader();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	            return (String)loader.getValor(chave);
	    }

	
	
}
