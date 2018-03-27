package bussiness;

import java.sql.SQLException;
import java.util.List;

import data.model.Student;
import data.repository.StudentDao;

public class StudentBussiness {
	
	private StudentDao sD = new StudentDao();
	
	public Student findByUsernamePassword(String username, String password){
		try {
			return sD.findByUsernamePassword(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Student> findAll() {
		try {
			return sD.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void addStudent(Student st) throws ClassNotFoundException, SQLException{
		sD.addStudent(st);
	}
	public void addStudentWithID(Student st) throws ClassNotFoundException, SQLException{
		sD.addStudentWithID(st);
	}
	
	public void deleteStudentById(String ID) throws ClassNotFoundException, SQLException{
		sD.deleteStudentById(ID);
	}
	public void updateStudent(String ID, List<String> courseName) throws ClassNotFoundException, SQLException{
		sD.updateStudentEnrol(ID, courseName);;
	}
	public Student findById(String ID) throws ClassNotFoundException, SQLException{
		return sD.findByID(ID);
	}
	public void updateStudentGrade(String ID, String cName, int grade){
		try {
			sD.updateStudentGrade(ID, cName, grade);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int findStudentGrade(String ID, String courseName) throws ClassNotFoundException, SQLException{
		return sD.findStudentGrade(ID, courseName);
	}

}
