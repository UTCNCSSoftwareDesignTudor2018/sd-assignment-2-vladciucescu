package business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataAccess.entity.Enrollment;
import dataAccess.entity.Student;
import dataAccess.entity.Teacher;
import dataAccess.repository.EnrollmentRepository;
import dataAccess.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private EnrollmentRepository enrollmentRepo;
	
	@Autowired
	private TeacherRepository teacherRepo;
	
	public Optional<Teacher> getTeacher(Integer id) {
		return teacherRepo.findById(id);
	}
	
	public File createReport(Student student, LocalDate start, LocalDate end) throws Exception {
        File directory = new File("reports");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File out = new File("reports/Report" + student.getName() + student.getSurname() + ".txt");
        try (FileOutputStream foStream = new FileOutputStream(out);
             OutputStreamWriter osWriter = new OutputStreamWriter(foStream);
             BufferedWriter writer = new BufferedWriter(osWriter)) {
            writer.write("Report for " + student.getName());
            writer.newLine();
            writer.write(start.toString() + " - " + end.toString());
            writer.newLine();
            writer.newLine();
            writer.newLine();
            List<Enrollment> enrollments = enrollmentRepo.findAllByStudent(student);
            for (Enrollment e : enrollments) {
                if (e.getCourse().getStartDate().isAfter(start) && e.getCourse().getStartDate().isBefore(end)) {
                    writer.write("Started course " + e.getCourse().getName() + " on " + e.getCourse().getStartDate());
                    writer.newLine();
                }
                if (e.getCourse().getEndDate().isAfter(start) && e.getCourse().getEndDate().isBefore(end)) {
                    writer.write("Finished course " + e.getCourse().getName() + " on " + e.getCourse().getEndDate());
                    writer.newLine();
                }
                if (e.getCourse().getExamDate().isAfter(start) && e.getCourse().getExamDate().isBefore(end)) {
                    writer.write("Took exam on " + e.getCourse().getExamDate());
                    writer.newLine();
                }
                writer.newLine();
            }
        } catch (Exception e) {
            throw new Exception("Cannot create report");
        }
        return out;
    }
}
