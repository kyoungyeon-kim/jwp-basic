package next.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import next.dao.UserDao;

public class ListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Object userSession = session.getAttribute("user");
		
		String returnPage = "redirect:/user/loginForm" ;
		UserDao userDao = new UserDao();
		
		if (userSession != null) {
			request.setAttribute("users", userDao.findAll());
			returnPage = "/user/list.jsp";
		}
		
		return returnPage;
	}
}
