package dataAccess.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="teachers")
public class Teacher extends User {
	
	@OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
	private List<Course> courses;
	
	private Teacher() {}
	
	private Teacher(Integer id, String name, String surname, Integer idCardNumber, String address, String email) {
		super(id, name, surname, idCardNumber, address, email);
	}

	public List<Course> getCourses() {
		return courses;
	}
	
	@Override
	public String toString() {
		return "Teacher " + getName() + getSurname();
	}
	
	public static class TeacherBuilder extends User.UserBuilder {
		
		@Override
		public Teacher build() {
			return new Teacher(id, name, surname, idCardNumber, address, email);
		}
	}
}
