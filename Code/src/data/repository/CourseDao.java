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


public class CourseDao {
	
	public DBConnection conn = new DBConnection();
	private StudentDao sD;
	private TeacherDao tD;
	
	

	public CourseDao(StudentDao sD, TeacherDao tD) {
		super();
		this.sD = sD;
		this.tD = tD;
	}
	public List<Course> findAll() throws SQLException {
		try {
			conn.start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Statement stat = conn.getConn().createStatement();
		String query = "SELECT * FROM course ORDER BY ID";
		ResultSet rs = stat.executeQuery(query);
		List<Course> cs = new ArrayList<Course>();
		while (rs.next()) {
			Course c = new Course();
			int course_id = rs.getInt("id");
			c.setId(course_id);
			String courseName = rs.getString("course_name");
			c.setCourseName(courseName);
			Date start_date = rs.getDate("start_date");
			c.setStartDate(start_date);
			Date end_date = rs.getDate("end_date");
			c.setEndDate(end_date);
			c.setStudents(sD.findByCourseName(courseName));
			c.setTeacher(tD.findTeacherByCourseName(courseName).getName());
			cs.add(c);
		}
		rs.close();
		stat.close();
		try {
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cs;		
	}
	public List<Course> findAllNoClose() throws SQLException {
		try {
			conn.start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Statement stat = conn.getConn().createStatement();
		String query = "SELECT * FROM course ORDER BY ID";
		ResultSet rs = stat.executeQuery(query);
		List<Course> cs = new ArrayList<Course>();
		while (rs.next()) {
			Course c = new Course();
			int course_id = rs.getInt("id");
			c.setId(course_id);
			String courseName = rs.getString("course_name");
			c.setCourseName(courseName);
			Date start_date = rs.getDate("start_date");
			c.setStartDate(start_date);
			Date end_date = rs.getDate("end_date");
			c.setEndDate(end_date);
			c.setStudents(sD.findByCourseName(courseName));
			c.setTeacher(tD.findTeacherByCourseName(courseName).getName());
			cs.add(c);
		}
		rs.close();
		stat.close();
		return cs;		
	}
	public Course findByName(String CourseName) throws SQLException {
			for(Course c: findAllNoClose())
			{
				if(c.getCourseName().equals(CourseName))
				{
					return c;
				}
			}
			return null;
	}
	public void createExam(String cName, Date date) throws SQLException, ClassNotFoundException {
		conn.start();
		Course c = findByName(cName);
		String query3 = "INSERT INTO course_exam"
				+ "(course_id, date) VALUES" + "(?,?)";
		PreparedStatement psQuery3 = conn.getConn().prepareStatement(query3);
		psQuery3.setInt(1, c.getId());
		psQuery3.setDate(2, date);
		psQuery3.executeUpdate();
		conn.close();
	}
	public void deleteExam(Date date) throws SQLException, ClassNotFoundException {
		conn.start();
		String query3 = "DELETE FROM course_exam WHERE date=?";
		PreparedStatement psQuery3 = conn.getConn().prepareStatement(query3);
		psQuery3.setDate(1, date);
		psQuery3.executeUpdate();
		conn.close();
	}

	public List<Date> findAllExams(String cName) throws SQLException, ClassNotFoundException{
		Course c = findByName(cName);
		String query3 = "SELECT * FROM course_exam WHERE course_id =?";
		PreparedStatement psQuery3 = conn.getConn().prepareStatement(query3);
		psQuery3.setInt(1, c.getId());
		ResultSet rs = psQuery3.executeQuery();
		List<Date> dates = new ArrayList<>();
		while(rs.next()){
			Date d = rs.getDate("date");
			dates.add(d);
		}
		rs.close();
		conn.close();
		return dates;
	}
	
}
