package bussiness;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import data.model.Course;
import data.repository.CourseDao;
import data.repository.StudentDao;
import data.repository.TeacherDao;

public class CourseBussiness {
	
	private StudentDao sd= new StudentDao();
	private TeacherDao tD= new TeacherDao(sd);
	private CourseDao cD = new CourseDao(sd, tD);
	
	public List<Course> findAll()
	{
		try {
			return cD.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Course findByCName(String cName) throws SQLException{
		return cD.findByName(cName);
	}
	
	public void createExam(String cName, Date date) throws ClassNotFoundException, SQLException{
		cD.createExam(cName, date);
	}
	public void deleteExam(Date date) throws SQLException, ClassNotFoundException{
		cD.deleteExam(date);
	}
	public Date StringToDate(String s) throws java.text.ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    java.util.Date parsed = null;
	    parsed = sdf.parse(s);
	    java.sql.Date data = new java.sql.Date(parsed.getTime());
	    return data;
		
	}
	public List<Date> findAllExams(String cName) throws SQLException, ClassNotFoundException{
		return cD.findAllExams(cName);
		
	}
	

}
