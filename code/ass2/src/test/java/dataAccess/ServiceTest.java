package dataAccess;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ass2.Application;
import business.EnrollmentService;
import business.StudentService;
import dataAccess.entity.Course;
import dataAccess.entity.Enrollment;
import dataAccess.entity.Student;
import dataAccess.repository.CourseRepository;
import dataAccess.repository.EnrollmentRepository;
import dataAccess.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ServiceTest {
	
	@Autowired 
	private EnrollmentService enrollmentService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private EnrollmentRepository enrollmentRepo;

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Test
	public void createEnrollmentTest() {
		Optional<Student> student = studentRepo.findById(2);
		Optional<Course> course = courseRepo.findById(1);
		Enrollment expected = enrollmentService.createEnrollment(student.get(), course.get());
		Optional<Enrollment> res = enrollmentRepo.findById(expected.getId());
		Assert.assertEquals(expected, res.get());
		enrollmentService.deleteEnrollment(res.get());
	}
	
	@Test
	public void updateGradeTest() {
		Optional<Enrollment> e = enrollmentRepo.findById(1);
		enrollmentService.updateGrade(e.get(), 10.0);
		Optional<Enrollment> e2 = enrollmentRepo.findById(1);
		Assert.assertEquals(e2.get().getGrade(), (Double)10.0);
		enrollmentService.updateGrade(e.get(), 9.5);
	}
	
	@Test
	public void updateGroupTest() {
		Optional<Student> s = studentRepo.findById(2);
		studentService.updateGroup(s.get(), 33333);
		Optional<Student> s2 = studentRepo.findById(2);
		Assert.assertEquals((Integer)33333, s2.get().getGroup());
		studentService.updateGroup(s.get(), 30000);
	}

}
