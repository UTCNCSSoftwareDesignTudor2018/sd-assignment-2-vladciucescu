package dataAccess.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="students")
public class Student extends User {
	
	@Column
	private Short year;
	
	@Column(name="groupp")
	private Integer group;

	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "enrollments", 
        joinColumns = { @JoinColumn(name = "student_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
	List<Course> courses;
	
	public Student() {}
	
	private Student(Integer id, String name, String surname, Integer idCardNumber, String address, String email, Short year, Integer group) {
		super(id, name, surname, idCardNumber, address, email);
		this.year = year;
		this.group = group;
	}

	public Short getYear() {
		return year;
	}
	
	public void setYear(Short year) {
		this.year = year;
	}

	public Integer getGroup() {
		return group;
	}
	
	public void setGroup(Integer group) {
		this.group = group;
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	@Override
	public String toString() {
		return "Student [year=" + year + ", group=" + group + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getSurname()=" + getSurname() + ", getIdCardNumber()=" + getIdCardNumber() + ", getAddress()="
				+ getAddress() + ", getEmail()=" + getEmail() + ", toString()=" + super.toString() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + "]";
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	
	public static class StudentBuilder extends User.UserBuilder {
		private Short year = 1;
		private Integer group = 30000;
		
		public StudentBuilder setYear(Short year) {
			this.year = year;
			return this;
		}
		
		public StudentBuilder setGroup(Integer group) {
			this.group = group;
			return this;
		}
		
		@Override
		public Student build() {
			return new Student(id, name, surname, idCardNumber, address, email, year, group);
		}
	}
}
