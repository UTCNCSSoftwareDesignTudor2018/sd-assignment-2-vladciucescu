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

import business.CourseService;
import business.EnrollmentService;
import business.UserService;
import dataAccess.entity.Course;
import dataAccess.entity.Enrollment;
import dataAccess.entity.Student;
import dataAccess.entity.User;

@Controller
public class StudentController {

	@Autowired
	private UserService userService;

	@Autowired
	EnrollmentService enrollmentService;
	
	@Autowired
	CourseService courseService;
	
	private ModelAndView mav;
	
	@GetMapping("/studentPage")
	public ModelAndView studentPage(@RequestParam(name="email")String email) {
		Optional<User> ou = userService.getUser(email);
		if (!ou.isPresent()) {
			mav = new ModelAndView("errorPage");
			mav.addObject("error", "No user found");
		}
		else {
			User u= ou.get();
			if (!(u instanceof Student)) {
				mav = new ModelAndView("errorPage");
				mav.addObject("error", "No student found");
			} else {
				mav = new ModelAndView("studentPage");
				Student student = (Student)u;
				mav.addObject("student", student);
				Course course = new Course();
				mav.addObject("course", course);
				List<Enrollment> enrollments = enrollmentService.findEnrollmentsForStudent(student);
				mav.addObject("enrollments", enrollments);
				List<Course> courses = courseService.findAllAvailableForStudent(student);
				mav.addObject("courses", courses);
			}
		}
		return mav;
	}
	
	@PostMapping(value="/studentPage", params="Change Email")
    public ModelAndView updateEmail(@RequestParam(name="email")String email) {
		Student student = (Student)mav.getModel().get("student");
		userService.updateEmail(student, email);
		return mav;
    }
	
	@PostMapping(value="/studentPage", params="Change Address")
    public ModelAndView updateAddress(@RequestParam(name="address")String address) {
		Student student = (Student)mav.getModel().get("student");
		userService.updateAddress(student, address);
		return mav;
    }
	
	@PostMapping(value="/studentPage", params="Enroll")
	public ModelAndView enroll(@ModelAttribute("course")Course course, Model model) {
		System.out.println(course);
		Course crs = courseService.findById(course.getId()).get();
		Student student = (Student)mav.getModel().get("student");
		enrollmentService.createEnrollment(student, crs);
		mav.getModel().replace("enrollments", enrollmentService.findEnrollmentsForStudent(student));
		mav.getModel().replace("courses", courseService.findAllAvailableForStudent(student));
		return mav;
	}
}
