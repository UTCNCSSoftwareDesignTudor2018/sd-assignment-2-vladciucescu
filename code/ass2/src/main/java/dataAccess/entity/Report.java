package dataAccess.entity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import dataAccess.repository.EnrollmentRepository;

public class Report {

	@Autowired
	private EnrollmentRepository enrollmentRepo;
	
	@Id
	private Integer id;
	
	private Student student;
	 
	private LocalDate start;
	
	private LocalDate end;
	
	private List<Enrollment> enrollments;
	
	
	private Report(Student student, LocalDate start, LocalDate end) {
		this.id = 0;
		this.student = student;
		this.start = start;
		this.end = end;
		this.enrollments = enrollmentRepo.findAllByStudent(student);
	}
	
	private String buildReport() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Report for " + student.getName() + '\n');
        sb.append(start.toString() + " - " + end.toString());
        sb.append("\n\n\n");
        for (Enrollment e : enrollments) {
            if (e.getCourse().getStartDate().isAfter(start) && e.getCourse().getStartDate().isBefore(end)) {
                sb.append("Started course " + e.getCourse().getName() + " on " + e.getCourse().getStartDate() + '\n');
            }
            if (e.getCourse().getEndDate().isAfter(start) && e.getCourse().getEndDate().isBefore(end)) {
                sb.append("Finished course " + e.getCourse().getName() + " on " + e.getCourse().getEndDate() + '\n');
            }
            if (e.getCourse().getExamDate().isAfter(start) && e.getCourse().getExamDate().isBefore(end)) {
                sb.append("Took exam on " + e.getCourse().getExamDate() + '\n');
            }
            sb.append('\n');
        }
        return sb.toString();
	}
	
	public File createReportFile() throws Exception {
        File directory = new File("reports");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File out = new File("reports/Report" + student.getName() + student.getSurname() + ".txt");
        try (FileOutputStream foStream = new FileOutputStream(out);
             OutputStreamWriter osWriter = new OutputStreamWriter(foStream);
             BufferedWriter writer = new BufferedWriter(osWriter)) {
            writer.write(buildReport());
        } catch (Exception e) {
            throw new Exception("Cannot create report");
        }
        return out;
    }

	@Override
	public String toString() {
		return buildReport();
	}
}
