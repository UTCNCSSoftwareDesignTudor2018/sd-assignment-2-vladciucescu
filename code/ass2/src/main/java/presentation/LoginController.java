package presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class LoginController {
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping(value = "/studentLogin")
	public String studentLogin() {
		return "studentLogin";
	}
	
	@GetMapping(value = "/teacherLogin")
	public String teacherLogin() {
		return "teacherLogin";
	}
}
