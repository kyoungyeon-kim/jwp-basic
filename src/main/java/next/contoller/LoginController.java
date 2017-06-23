package next.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import next.model.User;

public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String redirectPage = "redirect:/user/login_failed";
		
		User user = DataBase.findUserById(userId);
		
		if(user != null && user.getPassword().equals(password)) {
			HttpSession session  = request.getSession();
			session.setAttribute("user", user);
			redirectPage = "redirect:/user/list";
		}
		
		return redirectPage;
	}

}
