package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.contoller.Controller;

@WebServlet(name = "dispactcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
	private static final String REDIRECT_PRFIX = "redirect:";
	private RequestMapping requestMapping;

	@Override
	public void init() throws ServletException {
		requestMapping = new RequestMapping();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Controller contoller = getController(req);

			if (contoller == null) {
				throw new IllegalArgumentException("Not exists request mapping url");
			}

			String url = contoller.execute(req, resp);

			movePage(req, resp, url);
		} catch (Exception e) {
			log.error("DispatcherServlet error ", e);

			throw new ServletException(e);
		}
	}

	private void movePage(HttpServletRequest req, HttpServletResponse resp, String url)
			throws IOException, ServletException {
		if (url.startsWith(REDIRECT_PRFIX)) {
			resp.sendRedirect(url.substring(REDIRECT_PRFIX.length()));
		} else {
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
		}
	}

	private Controller getController(HttpServletRequest req) {
		String url = req.getRequestURI().substring(req.getContextPath().length());

		return requestMapping.getRequestContoller(url);
	}
}
