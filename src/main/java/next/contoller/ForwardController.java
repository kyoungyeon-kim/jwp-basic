package next.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {
	private String fowardPage;
	
	public ForwardController(String fowardPage) {
		if(fowardPage.isEmpty()) {
			throw new IllegalArgumentException("forwardPage is empty");
		}
		
		this.fowardPage = fowardPage;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return fowardPage;
	}
}
