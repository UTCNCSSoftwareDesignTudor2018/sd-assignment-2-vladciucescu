package dataAccess.entity;

import javax.persistence.*;

@Entity
@Table(name="enrollments")
public class Enrollment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private final Integer id;
	
	@ManyToOne
    @JoinColumn(name = "student_id")
	private Student student;
	
	@ManyToOne
    @JoinColumn(name = "course_id")
	private Course course;
	
	@Column
	private Double grade;

	public Enrollment() {
		id = 0;
	}
	
	public Enrollment(Integer id, Student student, Course course, Double grade) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		this.grade = grade;
	}

	public Integer getId() {
		return id;
	}

	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Double getGrade() {
		return grade;
	}
	
	public void setGrade(Double grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Enrollment [id=" + id + ", student=" + student + ", course=" + course + ", grade=" + grade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrollment other = (Enrollment) obj;
		if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
