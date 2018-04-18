package business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataAccess.entity.Student;
import dataAccess.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	public Optional<Student> getStudent(Integer id) {
		return studentRepo.findById(id);
	}
	
	public void deleteStudent(Student student) {
		studentRepo.delete(student);
	}
	
	public void updateGroup(Student student, Integer group) {
		student.setGroup(group);
		studentRepo.save(student);
	}
	
	public void updateYear(Student student, Short year) {
		student.setYear(year);
		studentRepo.save(student);
	}
	
	public List<Student> findAll() {
		return studentRepo.findAll();
	}
	
}
