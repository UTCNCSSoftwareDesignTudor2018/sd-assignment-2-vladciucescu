package dataAccess.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private LocalDate startDate;
	
	@Column
	private LocalDate endDate;
	
	@Column
	private LocalDate examDate;
	
	@ManyToOne
    @JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	@ManyToMany(mappedBy = "courses")
	private List<Student> students; 
	
	public Course() {
		id = 0;
	}
	
	public Course(Integer id, String name, LocalDate startDate, LocalDate endDate, LocalDate examDate,
			Teacher teacher) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.examDate = examDate;
		this.teacher = teacher;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public LocalDate getExamDate() {
		return examDate;
	}
	
	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", examDate=" + examDate + ", teacher=" + teacher + "]";
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
		Course other = (Course) obj;
		if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static class CourseBuilder {
		private Integer id;
		private String name;
		private LocalDate startDate;
		private LocalDate endDate;
		private LocalDate examDate;
		private Teacher teacher;
		
		public CourseBuilder setId(Integer id) {
			this.id = id;
			return this;
		}
		
		public CourseBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public CourseBuilder setStartDate(LocalDate startDate) {
			this.startDate = startDate;
			return this;
		}
		
		public CourseBuilder setEndDate(LocalDate endDate) {
			this.endDate = endDate;
			return this;
		}
		
		public CourseBuilder setExamDate(LocalDate examDate) {
			this.examDate = examDate;
			return this;
		}
		
		public CourseBuilder setTeacher(Teacher teacher) {
			this.teacher = teacher;
			return this;
		}
		
		public Course build() {
			return new Course(id, name, startDate, endDate, examDate, teacher);
		}
	}

}
