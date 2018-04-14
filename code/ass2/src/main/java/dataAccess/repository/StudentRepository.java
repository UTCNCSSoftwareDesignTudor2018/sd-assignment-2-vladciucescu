package dataAccess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dataAccess.entity.Course;
import dataAccess.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("SELECT s.courses FROM Student s WHERE s.id = :id") 
	public List<Course> findAllCourses(@Param("id")Integer id);
}
