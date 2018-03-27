package data.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.DBConnection;
import data.model.Course;
import data.model.Teacher;

public class TeacherDao {
	
	private DBConnection conn = new DBConnection();
	private StudentDao sD;
	
	
	
	public TeacherDao(StudentDao sD) {
		super();
		this.sD = sD;
	}

	public List<Teacher> findAll() throws SQLException {
		try {
			conn.start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Statement stat = conn.getConn().createStatement();
		String query = "SELECT * FROM teacher ORDER BY ID";
		ResultSet rs = stat.executeQuery(query);
		List<Teacher> teachers = new ArrayList<Teacher>();
		while (rs.next()) {
			Teacher teacher = new Teacher();
			int id = rs.getInt("id");
			teacher.setId(id);
			String name = rs.getString("name");
			teacher.setName(name);
			String surname = rs.getString("surname");
			teacher.setSurname(surname);
			String username = rs.getString("username");
			teacher.setUsername(username);
			String password = rs.getString("password");
			teacher.setPassword(password);
			String idNumber = rs.getString("id_number");
			teacher.setIdNumber(idNumber);
			String address = rs.getString("address");
			teacher.setAddress(address);


			String courseEnrolmentQuery = "SELECT * FROM course WHERE teacher_id=?";
			PreparedStatement ps2 = conn.getConn().prepareStatement(courseEnrolmentQuery);
			ps2.setInt(1, id);
			ResultSet result2 = ps2.executeQuery();
			List<Course> courses = new ArrayList<>();
			while (result2.next()) {
				Course c = new Course();
				int course_id = result2.getInt("id");
				c.setId(course_id);
				String courseName = result2.getString("course_name");
				c.setCourseName(courseName);
				Date start_date = result2.getDate("start_date");
				c.setStartDate(start_date);
				Date end_date = result2.getDate("end_date");
				c.setEndDate(end_date);
				c.setStudents(sD.findByCourseName(courseName));
				
				//get exams
				String courseExams = "SELECT * FROM course_exam WHERE course_id=?";
				PreparedStatement ps3 = conn.getConn().prepareStatement(courseExams);
				ps3.setInt(1, course_id);
				ResultSet result3 = ps3.executeQuery();
				List<Date> exams = new ArrayList<>();
				while (result3.next()) {
					Date exam = result3.getDate("date");
					exams.add(exam);
				}
				c.setExams(exams);
				c.setTeacher(name);
				courses.add(c);
			}
			result2.close();
			teacher.setCourses(courses);
			teachers.add(teacher);
		}
		rs.close();
		stat.close();
		try {
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teachers;		
	}
	public List<Teacher> findAllNoClose() throws SQLException {
		try {
			conn.start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Statement stat = conn.getConn().createStatement();
		String query = "SELECT * FROM teacher ORDER BY ID";
		ResultSet rs = stat.executeQuery(query);
		List<Teacher> teachers = new ArrayList<Teacher>();
		while (rs.next()) {
			Teacher teacher = new Teacher();
			int id = rs.getInt("id");
			teacher.setId(id);
			String name = rs.getString("name");
			teacher.setName(name);
			String surname = rs.getString("surname");
			teacher.setSurname(surname);
			String username = rs.getString("username");
			teacher.setUsername(username);
			String password = rs.getString("password");
			teacher.setPassword(password);
			String idNumber = rs.getString("id_number");
			teacher.setIdNumber(idNumber);
			String address = rs.getString("address");
			teacher.setAddress(address);


			String courseEnrolmentQuery = "SELECT * FROM course WHERE teacher_id=?";
			PreparedStatement ps2 = conn.getConn().prepareStatement(courseEnrolmentQuery);
			ps2.setInt(1, id);
			ResultSet result2 = ps2.executeQuery();
			List<Course> courses = new ArrayList<>();
			while (result2.next()) {
				Course c = new Course();
				int course_id = result2.getInt("id");
				c.setId(course_id);
				String courseName = result2.getString("course_name");
				c.setCourseName(courseName);
				Date start_date = result2.getDate("start_date");
				c.setStartDate(start_date);
				Date end_date = result2.getDate("end_date");
				c.setEndDate(end_date);
				c.setStudents(sD.findByCourseName(courseName));
				
				//get exams
				String courseExams = "SELECT * FROM course_exam WHERE course_id=?";
				PreparedStatement ps3 = conn.getConn().prepareStatement(courseExams);
				ps3.setInt(1, course_id);
				ResultSet result3 = ps3.executeQuery();
				List<Date> exams = new ArrayList<>();
				while (result3.next()) {
					Date exam = result3.getDate("date");
					exams.add(exam);
				}
				c.setExams(exams);
				c.setTeacher(name);
				courses.add(c);
			}
			result2.close();
			teacher.setCourses(courses);
			teachers.add(teacher);
		}
		rs.close();
		stat.close();
		return teachers;		
	}
	public Teacher findTeacherByCourseName(String cName) throws SQLException {
		for(Teacher t: findAllNoClose()){
			for(Course c1: t.getCourses()){
				if(c1.getCourseName().equals(cName)){
					return t;
				}
			}
		}
		return null;	
	}
	public Teacher findByUsernamePassword(String username, String password) throws SQLException {
		List<Teacher> ts = findAllNoClose();
		for(Teacher t: ts){
			if(t.getUsername().equals(username) && t.getPassword().equals(password)){
				return t;
			}
		}
		return null;
	}
	public Teacher findById(String ID) throws SQLException {
		List<Teacher> ts = findAll();
		for(Teacher t: ts){
			if(t.getIdNumber().equals(ID)){
				return t;
			}
		}
		return null;
	}

}
