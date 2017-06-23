package next.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

public class CreateController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = new User(request.getParameter("userId"), request.getParameter("password"),
				request.getParameter("name"), request.getParameter("email"));
		log.debug("user : {}", user);
		DataBase.addUser(user);
		
		return "redirect:/user/list";
	}

}
