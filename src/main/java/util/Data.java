package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.joda.time.DateTime;

public class Data {

	public static DateTime getData() {
		DateTime data = new DateTime();
		return  data;
		
	}
	
	
	public static String getDataString() {
		
		int mes = getData().getMonthOfYear();
		String mesEmTexto = null;
		
		
		switch(mes){
			case 1:
				mesEmTexto = "jan";
				break;
			
			case 2:
				mesEmTexto = "fev";
				break;
		
			case 3:
				mesEmTexto = "mar";
				break;
				
			case 4:
				mesEmTexto = "abr";
				break;
				
			case 5:
				mesEmTexto = "mai";
				break;
				
			case 6:
				mesEmTexto = "jun";
				break;
				
			case 7:
				mesEmTexto = "jul";
				break;
				
			case 8:
				mesEmTexto = "ago";
				break;
				
				
			case 9:
				mesEmTexto = "set";
				break;	
				
				
			case 10:
				mesEmTexto = "out";
				break;	
		
		
			case 11:
				mesEmTexto = "nov";
				break;
		
			
			case 12:
				mesEmTexto = "dez";
				break;
		
		
		}
		
		
		String dataEmTexto = String.valueOf(getData().getDayOfMonth()+"/"+mesEmTexto+"/"+getData().getYear());
			
		
		
		return dataEmTexto;
	}



	public String getHora() {
		
	return null;	
	}


	public static String getLocalEHora() {
		String localEhora = null;
		Properties propriedade = new Properties();
		try {
			propriedade.load(new FileInputStream("conf.properties"));
			String local = propriedade.getProperty("municipio");
			String uf = propriedade.getProperty("uf");
			localEhora = local+" - "+uf+", "+ getDataString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return localEhora;
		
	
		
	}


}
