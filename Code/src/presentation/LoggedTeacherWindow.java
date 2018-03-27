package presentation;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import bussiness.CourseBussiness;
import bussiness.StudentBussiness;
import bussiness.TeacherBussiness;
import data.model.Course;
import data.model.Student;
import data.model.Teacher;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Dimension;

public class LoggedTeacherWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txUserName;
	
	@SuppressWarnings("unused")
	private Teacher teacher;
	@SuppressWarnings("unused")
	private LoginWindow lW;
	private Student st = new Student();
	private CourseBussiness cB = new CourseBussiness();
	private StudentBussiness sB = new StudentBussiness();
	private TeacherBussiness tB = new TeacherBussiness();
	List<String> courseNames = new ArrayList<>();
	
	private JTextField txName;
	private JTextField txSurname;
	private JTextField txID;
	private JTextField txStName;
	private JTextField txStSurname;
	private JTextField txStAddress;
	private JTextField txStID;
	private JTextField txStUsername;
	private JTextField txCourse;
	private JTextField txStCode;
	private JTextField txStIDDelete;
	private JTextField txUpdateCourse;
	private JTextField txIDUpdate;
	private JTextField txIDGrading;
	private JTextField txCNameGrading;
	private JTextField txGrade;
	private JTextField txCNameExam;
	private JTextField txDateExam;
	private JPasswordField txStPassword;

	/**
	 * Create the application.
	 */
	public LoggedTeacherWindow(LoginWindow lW, Teacher teacher) {
		setMinimumSize(new Dimension(700, 650));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTitle = new JPanel();
		panel.add(pnTitle, BorderLayout.NORTH);
		pnTitle.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTitleNorth = new JPanel();
		pnTitle.add(pnTitleNorth, BorderLayout.NORTH);
		
		JLabel lbTitle = new JLabel("Teacher:  ");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 22));
		pnTitleNorth.add(lbTitle);
		
		txUserName = new JTextField();
		txUserName.setFont(new Font("Arial", Font.PLAIN, 22));
		txUserName.setEditable(false);
		
		txUserName.setText(teacher.getName());
		
		pnTitleNorth.add(txUserName);
		txUserName.setColumns(10);
		
		JLabel lbTitle2 = new JLabel(" Main Page.");
		lbTitle2.setFont(new Font("Arial", Font.BOLD, 22));
		pnTitleNorth.add(lbTitle2);
		
		JPanel pnCenter = new JPanel();
		pnTitle.add(pnCenter);
		pnCenter.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		pnCenter.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbID = new JLabel("ID: ");
		panel_1.add(lbID);
		
		txID = new JTextField();
		txID.setBackground(Color.WHITE);
		txID.setEnabled(true);
		txID.setEditable(false);
		txID.setText(teacher.getIdNumber());
		panel_1.add(txID);
		txID.setColumns(10);
		
		JLabel lbName = new JLabel("Name: ");
		panel_1.add(lbName);
		
		txName = new JTextField();
		txName.setBackground(Color.WHITE);
		txName.setEditable(false);
		txName.setText(teacher.getName());
		panel_1.add(txName);
		txName.setColumns(10);
		
		JLabel lbSurname = new JLabel("Surname: ");
		panel_1.add(lbSurname);
		
		txSurname = new JTextField();
		txSurname.setBackground(Color.WHITE);
		txSurname.setEditable(false);
		txSurname.setText(teacher.getSurname());
		panel_1.add(txSurname);
		txSurname.setColumns(10);
		
		JLabel lbCouses = new JLabel("Courses:");
		panel_1.add(lbCouses);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		int i=0;
		for(Course c: teacher.getCourses())
		{
			if(i!=teacher.getCourses().size()-1)
				textArea.setText(c.getCourseName()+", ");
			else{
				textArea.setText(c.getCourseName());
				i++;
			}
		}
		panel_1.add(textArea);
		
		JPanel panel_2 = new JPanel();
		pnCenter.add(panel_2, BorderLayout.CENTER);
		
		JLabel lbStCreation = new JLabel("Student Creation Section");
		lbStCreation.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(lbStCreation);
		
		JPanel panel_3 = new JPanel();
		pnTitle.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		JLabel lbStName = new JLabel("Name: ");
		panel_4.add(lbStName);
		
		txStName = new JTextField();
		panel_4.add(txStName);
		txStName.setColumns(10);
		
		JLabel lbStSurname = new JLabel("Surname: ");
		panel_4.add(lbStSurname);
		
		txStSurname = new JTextField();
		panel_4.add(txStSurname);
		txStSurname.setColumns(10);
		
		JLabel lbAdress = new JLabel("Address");
		panel_4.add(lbAdress);
		
		txStAddress = new JTextField();
		txStAddress.setText("");
		panel_4.add(txStAddress);
		txStAddress.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.CENTER);
		
		JLabel lbStID = new JLabel("ID: ");
		panel_5.add(lbStID);
		
		txStID = new JTextField();
		panel_5.add(txStID);
		txStID.setColumns(10);
		
		JLabel lbStUsername = new JLabel("Username: ");
		panel_5.add(lbStUsername);
		
		txStUsername = new JTextField();
		panel_5.add(txStUsername);
		txStUsername.setColumns(10);
		
		JLabel lbStPassword = new JLabel("Password: ");
		panel_5.add(lbStPassword);
		
		txStPassword = new JPasswordField();
		panel_5.add(txStPassword);
		txStPassword.setColumns(10);
		
		
		JLabel lbStCode = new JLabel("Student Code: ");
		panel_5.add(lbStCode);
		
		txStCode = new JTextField();
		panel_5.add(txStCode);
		txStCode.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		
		JLabel lbEnrol = new JLabel("Enroll to a Course: ");
		lbEnrol.setToolTipText("Course Name in text field(cannot be empty and course must exist)");
		panel_7.add(lbEnrol);
		
		txCourse = new JTextField();
		panel_7.add(txCourse);
		txCourse.setColumns(10);
		
		JButton btAddCourse = new JButton("Add Course");
		btAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txCourse.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Field (Course) in section add Student cannot be empty!");
				}
				else if(checkRepeatedCourses(txCourse.getText()) == true)
				{
					JOptionPane.showMessageDialog(null, "student is already enrol in that course (student actual enrolments): " + st.toStringEnrolments());
				}
				else if(checkCourseExists(txCourse.getText()) == false)
				{
					JOptionPane.showMessageDialog(null, "Course does not exist (list of avaliable courses): " + printCourseNames());
				}
				else
				{
					st.addEnrolment(txCourse.getText());
					txCourse.setText("");
					JOptionPane.showMessageDialog(null, "Sucessful enrolment! (student actual enrolments): " + st.toStringEnrolments());
				}
			}
		});
		panel_7.add(btAddCourse);
		
		JButton btDeleteCourse = new JButton("Delete Course");
		btDeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txCourse.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Field (Course) in section add Student cannot be empty!");
				}
				else if(checkRepeatedCourses(txCourse.getText()) == false)
				{
					JOptionPane.showMessageDialog(null, "student is not enrol in that course (student actual enrolments): " + st.toStringEnrolments());
				}
				else if(checkCourseExists(txCourse.getText()) == false)
				{
					JOptionPane.showMessageDialog(null, "Course does not exist (list of avaliable courses): " + printCourseNames());
				}
				else if(checkRepeatedCourses(txCourse.getText()) == true)
				{
					st.deleteEnrolment(txCourse.getText());
					txCourse.setText("");
					JOptionPane.showMessageDialog(null, "course deleted! (student actual enrolments): " + st.toStringEnrolments());
				}
				
			}
		});
		panel_7.add(btDeleteCourse);
		
		JButton btCreateStudent = new JButton("Create Student");
		btCreateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txStID.getText().equals("") || txStName.getText().equals("") || txStCode.getText().equals("")
						|| txStSurname.getText().equals("") || txStPassword.getText().equals("") || txStAddress.getText().equals("")
						|| txStUsername.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Fields in section add Student cannot be empty!");
				}
				else if(checkStudentRepeatedID(txStID.getText()))
				{
					JOptionPane.showMessageDialog(null, "There is a student who already has that id");
				}
				else if(checkStudentRepeatedUsername())
				{
					JOptionPane.showMessageDialog(null, "There is a student with that username");
				}
				else if(checkStudentRepeatedCode())
				{
					JOptionPane.showMessageDialog(null, "There is a student with that code");
				}else{
					st.setIdNumber(txStID.getText());
					st.setName(txStName.getText());
					st.setSurname(txStSurname.getText());
					st.setUsername(txStUsername.getText());
					st.setPassword(txStPassword.getText());
					st.setAddress(txStAddress.getText());
					st.setCode(txStCode.getText());
					
					try {
						sB.addStudent(st);
						txStID.setText("");
						txStName.setText("");
						txStSurname.setText("");
						txStUsername.setText("");
						txStPassword.setText("");
						txStAddress.setText("");
						txStCode.setText("");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error adding student!");
						e1.printStackTrace();
					} 
				}
					
			}
		});
		panel_7.add(btCreateStudent);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		
		JLabel lbDeleteStudent = new JLabel("Delete Student Section");
		lbDeleteStudent.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_8.add(lbDeleteStudent);
		
		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9, BorderLayout.SOUTH);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10, BorderLayout.NORTH);
		
		JLabel lbStIDDelete = new JLabel("ID: ");
		panel_10.add(lbStIDDelete);
		
		txStIDDelete = new JTextField();
		panel_10.add(txStIDDelete);
		txStIDDelete.setColumns(10);
		
		JButton btStDelete = new JButton("Delete");
		btStDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id = txStIDDelete.getText();
				if(checkStudentRepeatedID(id) == false || id.equals(""))
				{
					JOptionPane.showMessageDialog(null, "ID does not exist");
				}
				else
				{
					try {
						JOptionPane.showMessageDialog(null, "Deleted Student with id: " + id);
						sB.deleteStudentById(id);
						txStIDDelete.setText("");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error deleting student!");
						e.printStackTrace();
					}
				}
			}
		});
		panel_10.add(btStDelete);
		
		JPanel panel_11 = new JPanel();
		panel_9.add(panel_11, BorderLayout.CENTER);
		
		JLabel lbUpdateSt = new JLabel("Update Section");
		lbUpdateSt.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_11.add(lbUpdateSt);
		
		JPanel panel_13 = new JPanel();
		panel_9.add(panel_13, BorderLayout.SOUTH);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_13.add(panel_12, BorderLayout.NORTH);
		
		JLabel lbStUpdate = new JLabel("ID: ");
		panel_12.add(lbStUpdate);
		
		txIDUpdate = new JTextField();
		panel_12.add(txIDUpdate);
		txIDUpdate.setColumns(10);
		
		JLabel lbUpDateCourse = new JLabel("Enroll to a Course: ");
		lbUpDateCourse.setToolTipText("Course Name in text field(cannot be empty and course must exist)");
		panel_12.add(lbUpDateCourse);
		
		txUpdateCourse = new JTextField();
		txUpdateCourse.setColumns(10);
		panel_12.add(txUpdateCourse);
		
		JButton btUpdateCourseAdd = new JButton("Add Course");
		btUpdateCourseAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txIDUpdate.getText().equals("") || txUpdateCourse.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Field (Course) in section update Student cannot be empty!");
				}
				else if(checkRepeatedCoursesUpdate2(txUpdateCourse.getText()) == true)
				{
					JOptionPane.showMessageDialog(null, "student is already enrol in that course");
				}
				else if(checkStudentExists(txIDUpdate.getText()) == false)
				{
					JOptionPane.showMessageDialog(null, "student with id" + txIDUpdate.getText() + "does not exist");
				}
				else if(checkCourseExists(txUpdateCourse.getText()) == false)
				{
					JOptionPane.showMessageDialog(null, "Course does not exist (list of avaliable courses): " + printCourseNames());
				}
				else
				{
					courseNames.add(txUpdateCourse.getText());
					txUpdateCourse.setText("");
					JOptionPane.showMessageDialog(null, "Sucessful enrolment! (student actual enrolments): " + courseNames.toString());
				}
			}
		});
		panel_12.add(btUpdateCourseAdd);
		
		JButton btUpdate = new JButton("Update");
		btUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txIDUpdate.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Field (Course) in section update Student cannot be empty!");
				}
				else if(checkStudentExists(txIDUpdate.getText()) == false)
				{
					JOptionPane.showMessageDialog(null, "student with id" + txIDUpdate.getText() + "does not exist");
				}
				else
				{
					try {
						sB.updateStudent(txIDUpdate.getText(), courseNames);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "error enroling student");
						e1.printStackTrace();
					} 
				}
			}
		});
		panel_12.add(btUpdate);
		
		JPanel panel_14 = new JPanel();
		panel_13.add(panel_14, BorderLayout.CENTER);
		
		JLabel lbView = new JLabel("View Section");
		lbView.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_14.add(lbView);
		
		JPanel panel_15 = new JPanel();
		panel_13.add(panel_15, BorderLayout.SOUTH);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_18 = new JPanel();
		panel_15.add(panel_18, BorderLayout.NORTH);
		
		JButton btView = new JButton("View Students");
		panel_18.add(btView);
		
		JPanel panel_19 = new JPanel();
		panel_15.add(panel_19, BorderLayout.CENTER);
		panel_19.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_20 = new JPanel();
		panel_19.add(panel_20, BorderLayout.NORTH);
		
		JLabel lbGradeSection = new JLabel("Grading Section");
		panel_20.add(lbGradeSection);
		lbGradeSection.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JPanel panel_21 = new JPanel();
		panel_19.add(panel_21, BorderLayout.CENTER);
		
		JLabel lbIDGrading = new JLabel("ID: ");
		panel_21.add(lbIDGrading);
		
		txIDGrading = new JTextField();
		txIDGrading.setColumns(10);
		panel_21.add(txIDGrading);
		
		JLabel lbCNameGrading = new JLabel("Course Name: ");
		panel_21.add(lbCNameGrading);
		
		txCNameGrading = new JTextField();
		txCNameGrading.setText("");
		panel_21.add(txCNameGrading);
		txCNameGrading.setColumns(10);
		
		JLabel lbGrade = new JLabel("Grade:  ");
		panel_21.add(lbGrade);
		
		txGrade = new JTextField();
		txGrade.setText("");
		panel_21.add(txGrade);
		txGrade.setColumns(10);
		
		JButton btnNewButton = new JButton("Grade");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Student s = null;
				Course c = null;
				try {
					c = cB.findByCName(txCNameGrading.getText());
					s = sB.findById(txIDGrading.getText());
					if(txIDGrading.getText().equals("") || txCNameGrading.getText().equals("") || txGrade.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "fields (grading section) cannot be empty");
					}
					else if(checkTeacherCourses(txCNameGrading.getText())==false){
						JOptionPane.showMessageDialog(null, "must be a course that you are teaching");
					}else{
						try{
						sB.updateStudentGrade(txIDGrading.getText(), txCNameGrading.getText(), Integer.parseInt(txGrade.getText()));
						}
						catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Error grading (student) does not exist) ");
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error grading (student/course does not exist) ");
				}
			}
		});
		panel_21.add(btnNewButton);
		
		JButton btReport = new JButton("Generate Report");
		btReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tB.generateReport();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error generating report");
					e1.printStackTrace();
				} 
			}
		});
		panel_21.add(btReport);
		
		JPanel panel_22 = new JPanel();
		panel_19.add(panel_22, BorderLayout.SOUTH);
		panel_22.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_23 = new JPanel();
		panel_22.add(panel_23, BorderLayout.NORTH);
		
		JLabel lbCreateExamSection = new JLabel("Create Exam Section");
		lbCreateExamSection.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_23.add(lbCreateExamSection);
		
		JPanel panel_24 = new JPanel();
		panel_22.add(panel_24, BorderLayout.SOUTH);
		
		JLabel lbCourseExam = new JLabel("Course Name: ");
		panel_24.add(lbCourseExam);
		
		txCNameExam = new JTextField();
		panel_24.add(txCNameExam);
		txCNameExam.setColumns(10);
		
		JLabel lbDateExam = new JLabel("Date");
		panel_24.add(lbDateExam);
		
		txDateExam = new JTextField();
		txDateExam.setText("");
		panel_24.add(txDateExam);
		txDateExam.setColumns(10);
		
		JButton btCreateExam = new JButton("Create Exam");
		btCreateExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txCNameExam.getText().equals("") || txDateExam.getText().equals("")){
					JOptionPane.showMessageDialog(null, "fields in section Exam cannot be empty");
				}else if(checkTeacherCourses(txCNameExam.getText())==false){
					JOptionPane.showMessageDialog(null, "must be a course that you are teaching");
				}else{
					try {
						cB.createExam(txCNameExam.getText(), cB.StringToDate(txDateExam.getText()));
						JOptionPane.showMessageDialog(null, "succesfully created exam");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "error creating exam");
						//e1.printStackTrace();
					} 
				}
				
			}
		});
		panel_24.add(btCreateExam);
		
		JButton btDeleteExam = new JButton("DeleteExam");
		btDeleteExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txDateExam.getText().equals("")){
					JOptionPane.showMessageDialog(null, "fields in section Exam cannot be empty");
				}else if(checkTeacherCourses(txCNameExam.getText())==false){
					JOptionPane.showMessageDialog(null, "must be a course that you are teaching");
				}else if(checkExamCourse(txCNameExam.getText())== null){
					JOptionPane.showMessageDialog(null, "course has no exams");
				}else{
					try {
						cB.deleteExam(cB.StringToDate(txDateExam.getText()));
						JOptionPane.showMessageDialog(null, "succesfully deleted exam");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "error creating exam");
						//e1.printStackTrace();
					} 
				}
				
			}
		});
		panel_24.add(btDeleteExam);
		btView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Student> sts = sB.findAll();
				StringBuilder sb = new StringBuilder();
				sb.append("Total Students: \n ");
				for(Student s: sts)
				{
					sb.append(s.toString() + ", Enrolments: " + s.toStringEnrolments() + "\n");
				}
				JOptionPane.showMessageDialog(null, sb.toString());
				
			}
		});
		
		JPanel panel_16 = new JPanel();
		panel.add(panel_16, BorderLayout.SOUTH);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_17 = new JPanel();
		panel_16.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		JButton btLogOut = new JButton("Log Out");
		btLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_17.add(btLogOut, BorderLayout.WEST);
		initialize();
		
		this.teacher = teacher;
		this.lW = lW;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setUser(Teacher st) {
		this.teacher = st;
		
	}
	
	public boolean checkRepeatedCourses(String cNames)
	{
		if(st.getEnrolments()!=null){
			for(String cName: st.getEnrolments())
			{
				if(cName.equals(cNames))
				{
					return true;
				}
			}
			return false;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkRepeatedCoursesUpdate2(String cNames)
	{
		try {
			if(sB.findById(txIDUpdate.getText()).getEnrolments()!=null){
				for(String cName: sB.findById(txIDUpdate.getText()).getEnrolments())
				{
					if(cName.equals(cNames))
					{
						return true;
					}
				}
				return false;
			}
			else
			{
				return false;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkRepeatedCoursesUpdate(String cNames)
	{
		if(courseNames!=null){
			for(String cName: courseNames)
			{
				if(cName.equals(cNames))
				{
					return true;
				}
			}
			return false;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkCourseExists(String CourseName)
	{
		List<Course> cs = cB.findAll();
		
		for(Course c: cs)
		{
			if(c.getCourseName().equals(CourseName))
			{
				return true;
			}
		}
		return false;
	}
	public boolean checkStudentExists(String id)
	{
		List<Student> sts = sB.findAll();
		if(sts!=null){
			for(Student st: sts)
			{
				if(st.getIdNumber().equals(id))
				{
					return true;
				}
			}
			return false;
		}else{
			return false;
		}
	}
	public boolean checkStudentRepeatedID(String id)
	{
		List<Student> sts = sB.findAll();
		if(sts!=null){
			for(Student st: sts)
			{
				if(st.getIdNumber().equals(id))
				{
					return true;
				}
			}
			return false;
		}else{
			return false;
		}
	}
	
	public boolean checkStudentRepeatedUsername()
	{
		List<Student> sts = sB.findAll();
		if(sts!=null){
			for(Student st: sts)
			{
				if(st.getUsername().equals(txStUsername.getText()))
				{
					return true;
				}
			}
			return false;
		}else{
			return false;
		}
	}
	
	public boolean checkStudentRepeatedCode()
	{
		List<Student> sts = sB.findAll();
		if(sts!=null){
			for(Student st: sts)
			{
				if(st.getCode().equals(txStCode.getText()))
				{
					return true;
				}
			}
			return false;
		}else{
			return false;
		}
	}
	public String printCourseNames()
	{
		List<Course> cs = cB.findAll();
		StringBuilder sb = new StringBuilder();
		for(Course c: cs)
		{
			sb.append(c.getCourseName() + " ");
		}
		return sb.toString();
	}
	public boolean checkEnroled(String cName)
	{
		boolean isEnroled = false;
		for(Student s: sB.findAll())
		{
			for(String cName1: s.getEnrolments())
			{
				if(cName1.equals(cName))
				{
					isEnroled=true;
					break;
				}
			}
		}
		return isEnroled;
	}
	
	public boolean checkEnrolments(String ID, String cName) throws ClassNotFoundException, SQLException{
		for(String s: sB.findById(ID).getEnrolments()){
			if(s.equals(cName))
			{
				return true;
			}
		}
		return false;
	}
	public boolean checkTeacherCourses(String cName) {
		for(Course c: teacher.getCourses()){
			if(c.getCourseName().equals(cName))
			{
				return true;
			}
		}
		return false;
	}
	
    public List<Date> checkExamCourse(String cName){
    	List<Date> ds = new ArrayList<>();
    	try {
			try {
				ds = cB.findAllExams(cName);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(ds!=null)
    	return ds;
    	else{
    		return null;
    	}
    }
	
	

}
