package factory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import annotationUtil.TipoBeanLiteral;
import managedbeans.TipoBean;

@RequestScoped
public class TipoBeanFactory {

	
	@Inject
	@Any
	private Instance<TipoBean> tipoBean;
	
	
	
	
	
	public TipoBean criarBean(String url) {
		
		Instance<TipoBean>  instanciaTipoBean = null;
		if(url.equals("/getDoc-vacina/seguranca/consultar.xhtml")) {
			
			instanciaTipoBean = tipoBean.select(new TipoBeanLiteral("consulta"));
		
		}
		
		if(url.equals("/getDoc-vacina/seguranca/editar.xhtml")) {
			
			instanciaTipoBean = tipoBean.select(new TipoBeanLiteral("edita"));
		
		}
		return instanciaTipoBean.get();
		
		
		
		
		
		
	}
}
