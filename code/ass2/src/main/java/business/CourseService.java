package business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataAccess.entity.Course;
import dataAccess.entity.Student;
import dataAccess.repository.CourseRepository;
import dataAccess.repository.StudentRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	public Optional<Course> findById(Integer id) {
		return courseRepo.findById(id);
	}
	
	public List<Course> getCourses() {
		
		List<Course> courses = courseRepo.findAll();
		return courses;
	}
	
	public List<Course> findAllAvailableForStudent(Student student) {
		
		List<Course> allCourses = courseRepo.findAll();
		List<Course> studentCourses = studentRepo.findAllCourses(student.getId());
		allCourses.removeAll(studentCourses);
		return allCourses;
	}

}
