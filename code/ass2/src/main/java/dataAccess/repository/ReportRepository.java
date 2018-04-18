package dataAccess.repository;

import java.util.List;

//import org.springframework.data.mongodb.repository.MongoRepository;

import dataAccess.entity.Report;
import dataAccess.entity.Student;

public interface ReportRepository /*extends MongoRepository<Report, Integer>*/ {

	public List<Report> findByStudent(Student student);
}
