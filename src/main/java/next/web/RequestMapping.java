package next.web;

import java.util.HashMap;
import java.util.Map;

import next.contoller.Controller;
import next.contoller.CreateController;
import next.contoller.ForwardController;
import next.contoller.HomeController;
import next.contoller.ListController;
import next.contoller.LoginController;
import next.contoller.LogoutController;
import next.contoller.UpdateController;

public class RequestMapping {
	private Map<String, Controller> mapping = new HashMap<String, Controller>();

	public RequestMapping(){
		mapping.put("/", new HomeController());
		mapping.put("/index", new HomeController());
		mapping.put("/user/login", new LoginController());
		mapping.put("/user/logout", new LogoutController());
		mapping.put("/user/create", new CreateController());
		mapping.put("/user/list", new ListController());
		mapping.put("/user/update", new UpdateController());

		mapping.put("/user/loginForm", new ForwardController("/user/login.jsp"));
		mapping.put("/user/form", new ForwardController("/user/form.jsp"));
		mapping.put("/user/profile", new ForwardController("/user/profile.jsp"));
		mapping.put("/user/login_failed", new ForwardController("/user/login_failed.jsp"));

	}

	public Controller getRequestContoller(String url) {
		return mapping.get(url);
	}
	
	public void setMapping(String key , Controller controller) {
		mapping.put(key, controller);
	}
}
