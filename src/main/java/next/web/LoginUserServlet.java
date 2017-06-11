package next.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String redirectPage = "/user/login_failed.jsp";
		
		User user = DataBase.findUserById(userId);
		
		if(user != null && user.getPassword().equals(password)) {
			HttpSession session  = req.getSession();
			session.setAttribute("user", user);
			redirectPage = "/index.jsp";
		}
		
		resp.sendRedirect(redirectPage);
	}
}
