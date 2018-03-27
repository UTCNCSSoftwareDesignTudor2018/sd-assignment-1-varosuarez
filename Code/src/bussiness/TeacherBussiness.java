package bussiness;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

import data.model.Student;
import data.model.Teacher;
import data.repository.CourseDao;
import data.repository.StudentDao;
import data.repository.TeacherDao;

public class TeacherBussiness {
	private StudentDao sd= new StudentDao();
	private TeacherDao tD = new TeacherDao(sd);
	private CourseDao cD = new CourseDao(sd, tD);
	
	public Teacher findByUsernamePassword(String username, String password){
		try {
			return tD.findByUsernamePassword(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Teacher findByID(String ID) throws SQLException
	{
		return tD.findById(ID);
	}
	
	public void generateReport() throws SQLException, ClassNotFoundException
	{
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("report.txt"), "utf-8"));
		    writer.write(infoReport());
		} catch (IOException ex) {
		    // Report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
	public String infoReport() throws SQLException, ClassNotFoundException{
		StringBuilder sb = new StringBuilder();
		List<Student> sts= sd.findAll();
		for(Student s: sts)
		{
			sb.append("Student with id: [" + s.getIdNumber() + "] Name: ["+  s.getName() +
			"] Surname: ["+ s.getSurname() + "] Code:" + s.getCode() + " Address: [" +
					s.getAddress() + "]. \n");
			sb.append("Courses/Marks: ");
			for(String cName: s.getEnrolments()){
				int grade =sd.findStudentGrade(s.getIdNumber(), cName);
				if(grade==-1)
				sb.append("Course: [" + cName + "] Mark[" + " "
				+ "] \n");
				else{
					sb.append("Course: [" + cName + "] Mark[" + grade
							+ "] \n");
				}
			}
			sb.append("-----------------------------------------------\n");
		}
		return sb.toString();
	}
	
	

}
