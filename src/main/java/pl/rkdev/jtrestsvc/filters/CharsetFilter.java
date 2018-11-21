package pl.rkdev.jtrestsvc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "charsetFilter", urlPatterns = "/*")
public class CharsetFilter implements Filter {
	
	private String encoding;
	
	public CharsetFilter() {
		encoding = "UTF-8";
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if(request.getCharacterEncoding() == null)
			request.setCharacterEncoding(encoding);
		
		if(chain != null)
			chain.doFilter(request, response);
		
		response.setCharacterEncoding(encoding);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("requestEncoding");
		if(encoding == null)
			encoding = "UTF-8";
	}
}
