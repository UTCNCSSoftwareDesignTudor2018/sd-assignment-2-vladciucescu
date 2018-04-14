package dataAccess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dataAccess.entity.Course;
import dataAccess.entity.Enrollment;
import dataAccess.entity.Student;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{

	@Query("SELECT e.grade FROM Enrollment e WHERE e.student = :ss AND e.course = :cc") 
	public Double findGradeByStudentAndCourse(@Param("ss")Student student, @Param("cc")Course course);

	public List<Enrollment> findAllByStudent(Student student);
}
