package presentation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import business.StudentService;
import business.TeacherService;
import business.UserService;
import dataAccess.entity.Student;
import dataAccess.entity.Teacher;
import dataAccess.entity.User;

@Controller
public class TeacherController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;
	
	//for reports
	@Autowired
	private TeacherService teacherService;
	
	private ModelAndView mav;
	
	@GetMapping("/teacherPage")
	public ModelAndView teacherPage(@RequestParam(name="email")String email) {
		Optional<User> ou = userService.getUser(email);
		if (!ou.isPresent()) {
			mav = new ModelAndView("errorPage");
			mav.addObject("error", "No user found");
		}
		else {
			User u= ou.get();
			if (!(u instanceof Teacher)) {
				mav = new ModelAndView("errorPage");
				mav.addObject("error", "No teacher found");
			} else {
				mav = new ModelAndView("teacherPage");
				mav.addObject("teacher", (Teacher)u);

				mav.addObject("email", email);
				Student student = new Student();
				mav.addObject("student", student);
				List<Student> students = studentService.findAll();
				mav.addObject("students", students);
			}
		}
		return mav;
	}
	
	@PostMapping(value="/teacherPage", params="Change Email")
    public ModelAndView updateEmail(@RequestParam(name="email")String email) {
		Teacher teacher = (Teacher)mav.getModel().get("teacher");
		userService.updateEmail(teacher, email);
		mav.getModel().replace("email", email);
        return mav;
    }
	
	@PostMapping(value="/teacherPage", params="Change Address")
    public ModelAndView updateAddress(@RequestParam(name="address")String address) {
		Teacher teacher = (Teacher)mav.getModel().get("teacher");
		userService.updateAddress(teacher, address);
        return mav;
    }
	
	@PostMapping(value="/teacherPage", params="Modify Student")
	public String modifyStudent(@ModelAttribute("student")Student student, Model model, RedirectAttributes ra) {
		ra.addAttribute("student", studentService.getStudent(student.getId()).get());
		ra.addAttribute("email", (String)mav.getModel().get("email"));
		return "redirect:modStudentPage";
	}
}
