package data.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	private int id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private String idNumber;
	private String address;
	private String studentcode;
	private List<String> enrolments = new ArrayList<>();
	public Student(int id, String name, String surname, String username, String password, String idNumber,
			String address, String code, List<String> enrolments) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.idNumber = idNumber;
		this.address = address;
		this.studentcode = code;
		this.enrolments = enrolments;
	}
	
	public Student(String name, String surname, String username, String password, String idNumber, String address,
			String studentcode, List<String> enrolments) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.idNumber = idNumber;
		this.address = address;
		this.studentcode = studentcode;
		this.enrolments = enrolments;
	}

	public Student() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCode() {
		return studentcode;
	}
	public void setCode(String code) {
		this.studentcode = code;
	}
	public List<String> getEnrolments() {
		return enrolments;
	}
	public void setEnrolments(List<String> enrolments) {
		this.enrolments = enrolments;
	}
	public void addEnrolment(String courseName) {
		this.enrolments.add(courseName);
	}
	public void deleteEnrolment(String courseName) {
		this.enrolments.remove(courseName);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((enrolments == null) ? 0 : enrolments.hashCode());
		result = prime * result + id;
		result = prime * result + ((idNumber == null) ? 0 : idNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((studentcode == null) ? 0 : studentcode.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Student other = (Student) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (enrolments == null) {
			if (other.enrolments != null)
				return false;
		} else if (!enrolments.equals(other.enrolments))
			return false;
		if (id != other.id)
			return false;
		if (idNumber == null) {
			if (other.idNumber != null)
				return false;
		} else if (!idNumber.equals(other.idNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (studentcode == null) {
			if (other.studentcode != null)
				return false;
		} else if (!studentcode.equals(other.studentcode))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username
				+ ", password=" + password + ", idNumber=" + idNumber + ", address=" + address + ", studentcode="
				+ studentcode + "]";
	}
	public String toStringEnrolments() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(String c: enrolments){
			sb.append(c + " ");
		}
		sb.append("]");
		return sb.toString();
	}
	

	
	
}
