package data;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xpath.internal.axes.ChildTestIterator;

import bussiness.TeacherBussiness;
import data.model.Course;
import data.model.Student;
import data.model.Teacher;
import data.repository.CourseDao;
import data.repository.StudentDao;
import data.repository.TeacherDao;

public class DataTest {
	StudentDao sd = new StudentDao();
	TeacherDao tD = new TeacherDao(sd);
	CourseDao cD = new CourseDao(sd,tD);
	TeacherBussiness tB = new TeacherBussiness();
	List<Student> st = new ArrayList<>();
	List<Teacher> t = new ArrayList<>();
	List<Course> cs = new ArrayList<>();	
    Student s1 = new Student();
    Student s2 = new Student();
    Student s3 = new Student();
    Teacher t1 = new Teacher();
    Teacher t2 = new Teacher();
    Course c1 = new Course();
    Course c2 = new Course();
   @Before
   public void setUp(){
	    s1.setName("student1");
		st.add(s1);
		
		s2.setName("student2");
		st.add(s2);
		
		s3.setName("student1");
		st.add(s3);
		
		t1.setName("teacher1");
		t.add(t1);
		
		t2.setName("teacher2");
		t.add(t2);
		
		c1.setCourseName("course1");
		cs.add(c1);
		
		c2.setCourseName("course2");
		cs.add(c2);
   }
	@Test
	public void testFindCourseByName() {
		 try {
			Course cTest = cD.findByName("course1");
			assertEquals(cTest.getCourseName(), c1.getCourseName());
			Course cTest2 = cD.findByName("course2");
			assertEquals(cTest2.getCourseName(), c2.getCourseName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Test
	public void testFindAllCourses() {
		 try {
			List<Course> testcs = cD.findAll();
			assertEquals(testcs.size(), cs.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Test
	public void testFindAllStudents() {
		 try {
			List<Student> sts = sd.findAll();
			assertEquals(sts.size(), st.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Test
	public void testFindStudentsByID() {
		 try {
			Student test1 = sd.findByID("student1");
			assertEquals(test1.getName(), s1.getName());
			Student test2 = sd.findByID("student2");
			assertEquals(test2.getName(), s2.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Test
	public void testFindAllTeachers() {
		 try {
			List<Teacher> test = tD.findAll();
			assertEquals(test.size(), t.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testFindTeacherByID() {
		 try {
			Teacher test = tD.findById("teacher1");
			assertEquals( test.getName(), t1.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
