package annotationUtil;

import javax.enterprise.util.AnnotationLiteral;

import annotation.AnnotationTipoBean;

public class TipoBeanLiteral extends AnnotationLiteral<AnnotationTipoBean> implements AnnotationTipoBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipo;
	
	public TipoBeanLiteral(String tipo) {
		this.tipo = tipo;
		
	}
	
	@Override
	public String value() {
		// TODO Auto-generated method stub
		return tipo;
	}

}
