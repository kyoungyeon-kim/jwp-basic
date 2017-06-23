package core.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class ResourceFilter implements Filter {
	private static List<String> resourcePrefixs = new ArrayList<String>();
	private RequestDispatcher defaultRequestDispacher;

	static {
		resourcePrefixs.add("/css");
		resourcePrefixs.add("/js");
		resourcePrefixs.add("/fonts");
		resourcePrefixs.add("/images");
		resourcePrefixs.add("/favicon.ico");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		defaultRequestDispacher = filterConfig.getServletContext().getNamedDispatcher("default");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		String path = req.getRequestURI().substring(req.getContextPath().length());

		if (isResourceUrl(path)) {
			defaultRequestDispacher.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean isResourceUrl(String path) {
		for (String resourcePrefix : resourcePrefixs) {
			if (path.startsWith(resourcePrefix)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void destroy() {
	}

}
