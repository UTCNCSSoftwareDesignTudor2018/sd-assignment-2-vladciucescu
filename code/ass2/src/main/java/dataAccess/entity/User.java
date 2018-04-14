package dataAccess.entity;

import javax.persistence.*;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private final Integer id;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column(name="id_card_number")
	private Integer idCardNumber;
	
	@Column
	private String address;
	
	@Column
	private String email;
	
	protected User() {
		id = 0;
	}
	
	protected User(Integer id, String name, String surname, Integer idCardNumber, String address, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.idCardNumber = idCardNumber;
		this.address = address;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getIdCardNumber() {
		return idCardNumber;
	}
	
	public void setIdCardNumber(Integer idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}	

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", idCardNumber=" + idCardNumber
				+ ", address=" + address + ", email=" + email + "]";
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static class UserBuilder {
		protected Integer id = 0;
		protected String name = "name";
		protected String surname = "surname";
		protected Integer idCardNumber = 1111;
		protected String address = "address";
		protected String email = "email";
		
		public UserBuilder setId(Integer id) {
			this.id=id;
			return this;
		}
		
		public UserBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public UserBuilder setSurname(String surname) {
			this.surname = surname;
			return this;
		}
		
		public UserBuilder setIdCardNumber(Integer idCardNumber) {
			this.idCardNumber = idCardNumber;
			return this;
		}
		
		public UserBuilder setAddress(String address) {
			this.address = address;
			return this;
		}
		
		public UserBuilder setEmail(String email) {
			this.email = email;
			return this;
		}
		
		public User build() {
			return new User(id, name, surname, idCardNumber, address, email);
		}
	}

}
