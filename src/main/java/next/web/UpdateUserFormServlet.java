package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/updateForm")
public class UpdateUserFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object userSession = session.getAttribute("user");
		String userId = req.getParameter("userId");
		User user = DataBase.findUserById(userId);

		if (userSession == null || !((User) userSession).getUserId().equals(user.getUserId())) {
			resp.sendRedirect("/user/login.jsp");
		} else {
			req.setAttribute("userInfo", user);

			RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
			rd.forward(req, resp);
		}

	}
}
