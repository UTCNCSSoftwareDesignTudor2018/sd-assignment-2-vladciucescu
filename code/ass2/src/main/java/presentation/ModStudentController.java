package presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import business.EnrollmentService;
import business.UserService;
import dataAccess.entity.Enrollment;
import dataAccess.entity.Student;

@Controller
public class ModStudentController {

	@Autowired
	UserService userService;
	
	@Autowired
	EnrollmentService enrollmentService;
	
	private ModelAndView mav;
	
	@GetMapping("/modStudentPage")
	public ModelAndView modStudentPage(@RequestParam(name="student") Student student, @RequestParam(name="email")String email) {
		mav = new ModelAndView("modStudentPage");
		mav.addObject("student", student);
		mav.addObject("email", email);
		Enrollment enrollment = new Enrollment();
		mav.addObject("enrollment", enrollment);
		List<Enrollment> enrollments = enrollmentService.findEnrollmentsForStudent(student);
		mav.addObject("enrollments", enrollments);
		return mav;
	}
	
	@GetMapping(value="/teacherPage", params="Back to profile") 
	public String back(RedirectAttributes ra) {
		ra.addAttribute("email", mav.getModel().get("email"));
		return "redirect:teacherPage";
	}
	
	@PostMapping(value="/modStudentPage", params="Change Email")
    public ModelAndView updateEmail(@RequestParam(name="email")String email) {
		Student student = (Student)mav.getModel().get("student");
		userService.updateEmail(student, email);
		return mav;
    }
	
	@PostMapping(value="/modStudentPage", params="Change Address")
    public ModelAndView updateAddress(@RequestParam(name="address")String address) {
		Student student = (Student)mav.getModel().get("student");
		userService.updateAddress(student, address);
        return mav;
    } 
	
	@PostMapping(value="/modStudentPage", params="Delete Enrollment")
	public ModelAndView deleteEnrollment(@ModelAttribute("enrollment")Enrollment enrollment, Model model) {
		enrollmentService.deleteEnrollment(enrollment.getId());
		Student student = (Student)mav.getModel().get("student");
		mav.getModel().replace("enrollments", enrollmentService.findEnrollmentsForStudent(student));
		return mav;
	}
	
	@PostMapping(value="/modStudentPage", params="Assign Grade")
	public ModelAndView assignGrade(@ModelAttribute("enrollment")Enrollment enrollment, Model model, @RequestParam(name="grade")String aux) {
		Enrollment enr = enrollmentService.findById(enrollment.getId()).get();
		Double grade = Double.parseDouble(aux);
		enrollmentService.updateGrade(enr, grade);
		Student student = (Student)mav.getModel().get("student");
		mav.getModel().replace("enrollments", enrollmentService.findEnrollmentsForStudent(student));
		return mav;
	}
}
