package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.component.inputtext.InputText;

@FacesValidator("validator.validaCampoNomeVacina")
public class ValidaCampoNomeVacina implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String nome = (String) value;
		Pattern padrao = Pattern.compile("[0-9]");
		Matcher matcher = padrao.matcher(nome);
		
		if(matcher.find()){
			InputText entradaDeTexto = (InputText) component;
			entradaDeTexto.setValid(false);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nome inválido!", "Por favor, digite um nome válido."));
			
			
		}


		
	}

}
