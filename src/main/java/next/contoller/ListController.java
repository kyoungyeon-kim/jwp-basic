package next.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;

public class ListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Object userSession = session.getAttribute("user");
		
		String returnPage = "redirect:/user/loginForm" ;

		if (userSession != null) {
			request.setAttribute("users", DataBase.findAll());
			returnPage = "/user/list.jsp";
		}
		
		return returnPage;
	}
}
