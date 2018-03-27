package data.model;


import java.sql.Date;
import java.util.List;

public class Course {
	
	private int id;
	private String courseName;
	private Date startDate;
	private Date endDate;
	private List<Student> students;
	private List<Date> exams;
	private String teacherName;
	
	public Course(int id, String courseName, Date startDate, Date endDate, List<Student> students, String teacher) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.students = students;
		this.teacherName = teacher;
	}
	public Course(String courseName, Date startDate, Date endDate, List<Student> students, String teacher) {
		super();
		this.courseName = courseName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.students = students;
		this.teacherName = teacher;
	}
	
	public Course(int id, String courseName, Date startDate, Date endDate, String teacher) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.teacherName = teacher;
	}
	public Course() {
		super();
	}
	public Course(int id, String courseName, java.sql.Date startDate, java.sql.Date endDate) {
		this.id = id;
		this.courseName = courseName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public String getTeacher() {
		return teacherName;
	}
	public void setTeacher(String teacher) {
		this.teacherName = teacher;
	}
	
	public List<Date> getExams() {
		return exams;
	}
	public void setExams(List<Date> exams) {
		this.exams = exams;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", teacher=" + teacherName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((students == null) ? 0 : students.hashCode());
		result = prime * result + ((teacherName == null) ? 0 : teacherName.hashCode());
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
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		if (teacherName == null) {
			if (other.teacherName != null)
				return false;
		} else if (!teacherName.equals(other.teacherName))
			return false;
		return true;
	}
	
}
