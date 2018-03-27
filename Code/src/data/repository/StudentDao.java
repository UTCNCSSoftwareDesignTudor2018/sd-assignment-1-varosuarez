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
import data.model.Student;
import data.model.Teacher;

public class StudentDao {
	
	private DBConnection conn = new DBConnection();
	private CourseDao cD;
	private TeacherDao tD;
	
	public StudentDao() {
		super();
		 tD = new TeacherDao(this);
		 cD = new CourseDao(this, tD);
		
	}

	public List<Student> findAll() throws SQLException {
		try {
			conn.start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Statement stat = conn.getConn().createStatement();
		String query = "SELECT * FROM student ORDER BY ID";
		ResultSet rs = stat.executeQuery(query);
		List<Student> st = new ArrayList<Student>();
		while (rs.next()) {
			Student student = new Student();
			int id = rs.getInt("id");
			student.setId(id);
			String name = rs.getString("name");
			student.setName(name);
			String surname = rs.getString("surname");
			student.setSurname(surname);
			String username = rs.getString("username");
			student.setUsername(username);
			String password = rs.getString("password");
			student.setPassword(password);
			String idNumber = rs.getString("id_number");
			student.setIdNumber(idNumber);
			String address = rs.getString("address");
			student.setAddress(address);
			String code = rs.getString("studentCode");
			student.setCode(code);

			String courseEnrolmentQuery = "SELECT courses_id FROM enrolment WHERE students_id=?";
			PreparedStatement ps2 = conn.getConn().prepareStatement(courseEnrolmentQuery);
			ps2.setInt(1, id);
			ResultSet result2 = ps2.executeQuery();
			List<String> courses = new ArrayList<>();
			while (result2.next()) {
				int course_id = result2.getInt("courses_id");

				String coursesQuery= "SELECT * FROM course WHERE id=?";
				PreparedStatement ps3 = conn.getConn().prepareStatement(coursesQuery);
				ps3.setInt(1, course_id);
				ResultSet result3 = ps3.executeQuery();
				while (result3.next()) {
				String course_name = result3.getString("course_name");
				courses.add(course_name);
				}
				result3.close();
			}
			student.setEnrolments(courses);
			result2.close();
			st.add(student);
		}
		rs.close();
		stat.close();
		try {
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;		
	}
	
	public List<Student> findAllNoClose() throws SQLException {
		try {
			conn.start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Statement stat = conn.getConn().createStatement();
		String query = "SELECT * FROM student ORDER BY ID";
		ResultSet rs = stat.executeQuery(query);
		List<Student> st = new ArrayList<Student>();
		while (rs.next()) {
			Student student = new Student();
			int id = rs.getInt("id");
			student.setId(id);
			String name = rs.getString("name");
			student.setName(name);
			String surname = rs.getString("surname");
			student.setSurname(surname);
			String username = rs.getString("username");
			student.setUsername(username);
			String password = rs.getString("password");
			student.setPassword(password);
			String idNumber = rs.getString("id_number");
			student.setIdNumber(idNumber);
			String address = rs.getString("address");
			student.setAddress(address);
			String code = rs.getString("studentCode");
			student.setCode(code);

			String courseEnrolmentQuery = "SELECT courses_id FROM enrolment WHERE students_id=?";
			PreparedStatement ps2 = conn.getConn().prepareStatement(courseEnrolmentQuery);
			ps2.setInt(1, id);
			ResultSet result2 = ps2.executeQuery();
			List<String> courses = new ArrayList<>();
			while (result2.next()) {
				int course_id = result2.getInt("courses_id");

				String coursesQuery= "SELECT * FROM course WHERE id=?";
				PreparedStatement ps3 = conn.getConn().prepareStatement(coursesQuery);
				ps3.setInt(1, course_id);
				ResultSet result3 = ps3.executeQuery();
				while (result3.next()) {
				String course_name = result3.getString("course_name");
				courses.add(course_name);
				}
				result3.close();
			}
			student.setEnrolments(courses);
			result2.close();
			st.add(student);
		}
		rs.close();
		stat.close();
		return st;		
	}

	public Student findByUsernamePassword(String username, String password) throws SQLException {
		List<Student> st = findAll();
		for(Student s: st){
			if(s.getUsername().equals(username) && s.getPassword().equals(password)){
				return s;
			}
		}
		return null;
	}
	
	public List<Student> findByCourseName(String courseName) throws SQLException {
		List<Student> st = findAllNoClose();
		List<Student> result = new ArrayList<>();
		for(Student s: st){
			for(String e: s.getEnrolments())
			{
				if(e.equals(courseName))
				{
					result.add(s);
				}
					
			}
		}
		return result;
	}
	
	public Student findByID(String id) throws SQLException {
		List<Student> st = findAllNoClose();
		for(Student s: st){
			if(s.getIdNumber().equals(id))
			{
				return s;
			}
		}
		return null;
	}
	
	public void addStudent(Student st) throws ClassNotFoundException, SQLException{
		conn.start();
		int i=0;
		for(Student s: findAllNoClose()){
			if(s.getId()==i){
				i++;
			}else{
				break;
			}	
		}
		st.setId(i);
		String query2 = "INSERT INTO student"
				+ "(id, address, studentCode, id_number, name, surname, username, password) VALUES" + "(?,?,?,?,?,?,?,?)";
		
		PreparedStatement psQuery2 = conn.getConn().prepareStatement(query2);

		psQuery2.setInt(1, st.getId());
		psQuery2.setString(2, st.getAddress());
		psQuery2.setString(3, st.getCode());
		psQuery2.setString(4, st.getIdNumber());
		psQuery2.setString(5, st.getName());
		psQuery2.setString(6, st.getSurname());
		psQuery2.setString(7, st.getUsername());
		psQuery2.setString(8, st.getPassword());
		
		psQuery2.executeUpdate();
		
		if(st.getEnrolments()!=null)
		{
			for(String s: st.getEnrolments())
			{
				
				String query3 = "INSERT INTO enrolment"
						+ "(students_id, courses_id, grades) VALUES" + "(?,?,?)";
				
				PreparedStatement psQuery3 = conn.getConn().prepareStatement(query3);

				psQuery3.setInt(1, st.getId());
				psQuery3.setInt(2, cD.findByName(s).getId());
				psQuery3.setInt(3, -1);
				
				psQuery3.executeUpdate();
			}
		}
		conn.close();
	}
	
	public void addStudentWithID(Student st) throws ClassNotFoundException, SQLException{
		conn.start();
		String query2 = "INSERT INTO student"
				+ "(id, address, studentCode, id_number, name, surname, username, password) VALUES" + "(?,?,?,?,?,?,?,?)";
		
		PreparedStatement psQuery2 = conn.getConn().prepareStatement(query2);

		psQuery2.setInt(1, st.getId());
		psQuery2.setString(2, st.getAddress());
		psQuery2.setString(3, st.getCode());
		psQuery2.setString(4, st.getIdNumber());
		psQuery2.setString(5, st.getName());
		psQuery2.setString(6, st.getSurname());
		psQuery2.setString(7, st.getUsername());
		psQuery2.setString(8, st.getPassword());
		
		psQuery2.executeUpdate();
		
		if(st.getEnrolments()!=null)
		{
			for(String s: st.getEnrolments())
			{
				
				String query3 = "INSERT INTO enrolment"
						+ "(students_id, courses_id, grades) VALUES" + "(?,?,?)";
				
				PreparedStatement psQuery3 = conn.getConn().prepareStatement(query3);

				psQuery3.setInt(1, st.getId());
				psQuery3.setInt(2, cD.findByName(s).getId());
				psQuery3.setInt(3, -1);
				
				psQuery3.executeUpdate();
			}
		}
		conn.close();
	}
	public void deleteStudentById(String id) throws ClassNotFoundException, SQLException{
		conn.start();
		int idSQL =-1;
		for(Student s: findAllNoClose()){
			if(s.getIdNumber().equals(id)){
				idSQL = s.getId();
				break;
			}
		}
		if(idSQL == -1)
		{
			throw new ClassNotFoundException();
		}
		else
		{
			String query2 = "DELETE FROM enrolment WHERE students_id=?";
			PreparedStatement psQuery2 = conn.getConn().prepareStatement(query2);
			psQuery2.setInt(1, idSQL);
			psQuery2.executeUpdate();
			String query3 = "DELETE FROM student WHERE id=?";
			PreparedStatement psQuery3 = conn.getConn().prepareStatement(query3);
			psQuery3.setInt(1, idSQL);
			psQuery3.executeUpdate();
		}
		conn.close();
	}
	
	public void updateStudentEnrol(String ID, List<String> courseName) throws ClassNotFoundException, SQLException{
		conn.start();
		List<Course> cs = new ArrayList<>();
		Student t = findByID(ID);
		for(String cName: courseName)
		{
			for(Course c: cD.findAllNoClose())
			{
				if(c.getCourseName().equals(cName))
				{
					cs.add(c);
				}
			}
		}
		if(cs.size()!=0)
		{
			String query3 = "INSERT INTO enrolment"
					+ "(students_id, courses_id, grades) VALUES" + "(?,?,?)";
			
			PreparedStatement psQuery3 = conn.getConn().prepareStatement(query3);
			for(Course c: cs)
			{
				psQuery3.setInt(1, t.getId());
				psQuery3.setInt(2, c.getId());
				psQuery3.setInt(3, -1);
				psQuery3.executeUpdate();
			}
		}else{
			throw new ClassNotFoundException();
		}
		conn.close();
		
	}
	
	
	
	public void updateStudentGrade(String ID, String cName, int grade) throws ClassNotFoundException, SQLException{
		conn.start();
		Course c = cD.findByName(cName);
		Student t = findByID(ID);
		if(t != null && c!= null)
		{
			String query3 = "UPDATE enrolment "
					+ "SET grades=? WHERE students_id=? AND courses_id=?";
			PreparedStatement psQuery3 = conn.getConn().prepareStatement(query3);
			psQuery3.setInt(3, c.getId());
			psQuery3.setInt(2, t.getId());
			psQuery3.setInt(1, grade);
			psQuery3.executeUpdate();
		}else{
			throw new ClassNotFoundException();
		}
		conn.close();
		
	}
	
	public int findStudentGrade(String ID, String CourseName) throws ClassNotFoundException, SQLException{
		conn.start();
		int grade = -1;
		Course c = cD.findByName(CourseName);
		Student t = findByID(ID);
		if(t != null && c!= null)
		{
			String query3 = "SELECT * FROM  enrolment WHERE students_id=? AND courses_id=?";
			PreparedStatement psQuery3 = conn.getConn().prepareStatement(query3);
			psQuery3.setInt(2, c.getId());
			psQuery3.setInt(1, t.getId());
			ResultSet result3 = psQuery3.executeQuery();
			while (result3.next()) {
			grade = result3.getInt("grades");
			}
			result3.close();
		}else{
			throw new ClassNotFoundException();
		}
		conn.close();
		return grade;
	}
	
}
