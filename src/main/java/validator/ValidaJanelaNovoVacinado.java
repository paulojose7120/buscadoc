package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import util.ValidaCPF;

@FacesValidator("validator.validaNovo")
public class ValidaJanelaNovoVacinado implements Validator {
	
	
	
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		UIInput inputMask = (UIInput) component;
		
		if(value.equals("") || !CPFValido((String)value)) {
		
		inputMask.setValid(false);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF inválido!","Por favor, verifique se o cpf está correto."));
		System.out.print("Olá Mundo-validate");
		}

	}
	
	
	private boolean CPFValido(String cpf) {
			boolean CPFValido = false;
			String cpfSemPontosETraco = extrairApenasNumeros(cpf);
			if(verificaPrimeiroDigito(cpfSemPontosETraco) && verificaSegundoDigito(cpfSemPontosETraco) && !verificaSeTodosOsDigitosSaoIguais(cpfSemPontosETraco)) {
				CPFValido = true;
			}
			return  CPFValido;
	}
	
	
	private  String extrairApenasNumeros(String cpf) {
		int  size = cpf.length();
		char c;
		StringBuilder str2=  new StringBuilder();
		for(int i = 0; i<size; i++) {
			c = cpf.charAt(i);
			String str = String.valueOf(c);
			if(!(str.equals(".") || str.equals("-"))) {
			str2.append(str);
			
		}}
	
		return String.valueOf(str2);
	}
	
	
	
	
	private boolean verificaPrimeiroDigito(String cpfSemPontosETraco) {
		boolean cpfValido=false;
			int digitoDecrescente = 10;
		int somatorio=0;

		int tamanhoStrCPF = cpfSemPontosETraco.length();
		int codigoVerificador =Integer.parseInt(toString().valueOf(cpfSemPontosETraco.charAt(9)));
	
		int y=0;
		for(int i =tamanhoStrCPF-1; i>1; i--) {
			
			int z  = Character.getNumericValue(cpfSemPontosETraco.charAt(y++));
			
			somatorio += z*i;
			
			
			
			
		/*	
			System.out.println(i);
			System.out.println(z);
			System.out.println("SOMATÓRIO"+somatorio);*/

		}	
		int modulus = (somatorio*10)%11;
		if(modulus == codigoVerificador) {
			 cpfValido = true;
		}
		return cpfValido;
	}
	
	
	
	private boolean verificaSegundoDigito(String cpfSemPontosETraco) {
		boolean cpfValido=false;
			int digitoDecrescente = 11;
		int somatorio=0;

		int tamanhoStrCPF = cpfSemPontosETraco.length();
		int codigoVerificador =Integer.parseInt(toString().valueOf(cpfSemPontosETraco.charAt(10)));
		
		int y=0;
		for(int i =tamanhoStrCPF; i>1; i--) {
			
			int z  = Character.getNumericValue(cpfSemPontosETraco.charAt(y++));
			
			somatorio += z*i;
			
			
	
		}	
		int modulus = (somatorio*10)%11;
	
		
		
		if(modulus == codigoVerificador) {
			 cpfValido = true;
		
		}
		return cpfValido;
	}
	
	
	private boolean verificaSeTodosOsDigitosSaoIguais(String cpfSemPontosETraco) {
		
		int tamanhoCpf = cpfSemPontosETraco.length();
		int comparador=  Character.getNumericValue(cpfSemPontosETraco.charAt(0));
		boolean numerosIguais = true;
		for(int i = 0; i <tamanhoCpf; i++) {
			
			int z  = Character.getNumericValue(cpfSemPontosETraco.charAt(i));
			
			if(z != comparador) {
				numerosIguais = false;
			}
			comparador = z;
			
		}

		return numerosIguais;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
