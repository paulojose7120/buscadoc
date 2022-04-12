package util;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


public class ValidaCPF {
	
	
	
	
	
	public String CPF(String cpf) {
		
		String cpfSemPontosETraco = extrairApenasNumeros(cpf);
		System.out.println("DENTRO DA CLASSE VALIDA CPF!");
		return  cpfSemPontosETraco;
	
	}
	
	private  String extrairApenasNumeros(String cpf) {
		
		int tamanhoString = cpf.length();
		String listaDeDigitos = null;
		String charConvertido;
		char c;
		for(int i = 0; i<tamanhoString; i++) {
			
		charConvertido = String.valueOf(c = cpf.charAt(i));
		if (!(charConvertido.equals(".")||charConvertido.equals("-") )) {
			listaDeDigitos.concat(charConvertido);
			System.out.println(charConvertido);
		}
		
			
		}
		
		
		return listaDeDigitos;
	}
	
	
	
	
}
