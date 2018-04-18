package dataAccess;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ass2.Application;
import dataAccess.entity.Course;
import dataAccess.entity.Enrollment;
import dataAccess.entity.Student;
import dataAccess.entity.Teacher;
import dataAccess.entity.User;
import dataAccess.repository.CourseRepository;
import dataAccess.repository.EnrollmentRepository;
import dataAccess.repository.StudentRepository;
import dataAccess.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class PersistenceTest {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private EnrollmentRepository enrollmentRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Test
	public void userTest() {
		
		Optional<User> user = userRepo.findById(5);
		Assert.assertTrue(user.isPresent());
	}
	
	@Test
	public void studentTest() {
		
		Optional<Student> student = studentRepo.findById(5);
		Assert.assertTrue(student.isPresent());
	}
	
	@Test
	public void enrollmentTest() {

		Optional<Student> student = studentRepo.findById(2);
		Optional<Course> course = courseRepo.findById(1);
		Double e = enrollmentRepo.findGradeByStudentAndCourse(student.get(), course.get());
		Assert.assertEquals(e, (Double)9.5);
	}
	
	@Test
	public void enrollmentTest2() {

		Optional<Student> student = studentRepo.findById(2);
		List<Enrollment> e = enrollmentRepo.findAllByStudent(student.get());
		Assert.assertFalse(e.isEmpty());
	}
	
	@Test
	public void findAllCoursesTest() {

		Optional<Student> student = studentRepo.findById(2);
		List<Course> courses = studentRepo.findAllCourses(student.get().getId());
		Assert.assertFalse(courses.isEmpty());
	}
	
	@Test
	public void findByEmailTest() {
		
		Optional<User> user = userRepo.findByEmail("pop.ana@gmail.com");
		Teacher.TeacherBuilder ub = new Teacher.TeacherBuilder();
		User expected = ub.setId(1).setName("Ana").setSurname("Pop").setIdCardNumber(2314).setAddress("strada Mica").build();
		Assert.assertEquals(expected, user.get());
	}

}
