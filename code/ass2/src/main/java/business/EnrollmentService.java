package business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataAccess.entity.Course;
import dataAccess.entity.Enrollment;
import dataAccess.entity.Student;
import dataAccess.repository.EnrollmentRepository;

@Service
public class EnrollmentService {

	@Autowired 
	private EnrollmentRepository enrollmentRepo;
	
	public Enrollment createEnrollment(Student student, Course course) {
		
		return enrollmentRepo.save(new Enrollment(0, student, course, 0.0));
	}
	
	public List<Enrollment> findEnrollmentsForStudent(Student student) {
		
		List<Enrollment> enrollments = enrollmentRepo.findAllByStudent(student);
		return enrollments;
	}
	
	public void updateGrade(Enrollment enrollment, Double grade) {
		enrollment.setGrade(grade);
		enrollmentRepo.save(enrollment);
	}
	
	public void deleteEnrollment(Enrollment enrollment) {
		enrollmentRepo.delete(enrollment);
	}
}
