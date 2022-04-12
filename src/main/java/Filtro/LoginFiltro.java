package Filtro;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFiltro  implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest	= (HttpServletRequest) request;
		String str = (String) (httpRequest.getSession().getAttribute("login"));
if (str != null && str !="") {
		
		chain.doFilter(request, response);
		
	}else {
		
	
		
	
		HttpServletResponse resp = (HttpServletResponse) response;
		httpRequest.getSession().invalidate();
		
		resp.sendRedirect("/getDoc-vacina/login.xhtml");

	}

	}

	@Override
	public void destroy() {
	}

}
