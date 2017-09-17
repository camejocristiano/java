package br.com.main.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AutenticarUsuario
 */
@WebFilter("/*")
public class AutenticarUsuario implements Filter {

    /**
     * Default constructor. 
     */
    public AutenticarUsuario() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url = httpServletRequest .getRequestURI();				
		HttpSession sessao = httpServletRequest .getSession();				
		if (sessao.getAttribute("usuario.logado")!= null || url.lastIndexOf("index.jsp")>-1 ||
			url.lastIndexOf("logincontroller.do") >-1 || url.lastIndexOf("sitecontroller.do")>-1){
			chain.doFilter(request, response); 
			}else{
				if	( url.lastIndexOf("restrict")>-1 ) {
					((HttpServletResponse) response).sendRedirect("../index.jsp");
				} else {
					((HttpServletResponse) response).sendRedirect("index.jsp");
				}
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
