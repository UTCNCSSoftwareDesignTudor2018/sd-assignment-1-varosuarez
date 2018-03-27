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
import data.model.Course;
import data.model.Student;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class LoggedUserWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txUserName;
	private Student student;
	private LoginWindow lW;
	private StudentBussiness sB = new StudentBussiness();
	private CourseBussiness cB = new CourseBussiness();
	
	private JTextField txName;
	private JTextField txSurname;
	private JTextField txID;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txAddress;
	private JTextField txCode;
	private JTextField txCName;

	/**
	 * Create the application.
	 */
	public LoggedUserWindow(LoginWindow lW, Student user) {
		setMinimumSize(new Dimension(500, 400));
		getContentPane().setLayout(new BorderLayout(0, 0));
		this.student = user;
		this.lW = lW;
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTitle = new JPanel();
		panel.add(pnTitle, BorderLayout.NORTH);
		pnTitle.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTitleNorth = new JPanel();
		pnTitle.add(pnTitleNorth, BorderLayout.NORTH);
		
		JLabel lbTitle = new JLabel("User: ");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 22));
		pnTitleNorth.add(lbTitle);
		
		txUserName = new JTextField();
		txUserName.setFont(new Font("Arial", Font.PLAIN, 22));
		txUserName.setEditable(false);
		
		txUserName.setText(user.getName());
		
		pnTitleNorth.add(txUserName);
		txUserName.setColumns(10);
		
		JLabel lbTitle2 = new JLabel(" Main Page.");
		lbTitle2.setFont(new Font("Arial", Font.BOLD, 22));
		pnTitleNorth.add(lbTitle2);
		
		JPanel panel_1 = new JPanel();
		pnTitle.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JLabel lbTitleSection = new JLabel("Student Profile");
		lbTitleSection.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(lbTitleSection);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		
		JLabel lbName = new JLabel("Name");
		panel_3.add(lbName);
		
		txName = new JTextField();
		txName.setText(user.getName());
		txName.setBackground(Color.WHITE);
		panel_3.add(txName);
		txName.setColumns(10);
		
		JLabel lbSurname = new JLabel("Surname");
		panel_3.add(lbSurname);
		
		txSurname = new JTextField();
		txSurname.setText(user.getSurname());
		txSurname.setBackground(Color.WHITE);
		panel_3.add(txSurname);
		txSurname.setColumns(10);
		
		JLabel lbID = new JLabel("ID:");
		panel_3.add(lbID);
		
		txID = new JTextField();
		txID.setText(user.getIdNumber());
		txID.setBackground(Color.WHITE);
		panel_3.add(txID);
		txID.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.NORTH);
		
		JLabel lbUsername = new JLabel("UserName");
		panel_5.add(lbUsername);
		
		textField = new JTextField();
		textField.setText(user.getUsername());
		panel_5.add(textField);
		textField.setColumns(10);
		
		JLabel lbPassword = new JLabel("Password");
		panel_5.add(lbPassword);
		
		passwordField = new JPasswordField(10);
		passwordField.setText(user.getPassword());
		panel_5.add(passwordField);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.CENTER);
		
		JLabel Address = new JLabel("Address");
		panel_6.add(Address);
		
		txAddress = new JTextField();
		txAddress.setText(user.getAddress());
		txAddress.setBackground(Color.WHITE);
		txAddress.setEnabled(true);
		txAddress.setEditable(true);
		panel_6.add(txAddress);
		txAddress.setColumns(10);
		
		JLabel lbStCode = new JLabel("Code: ");
		panel_6.add(lbStCode);
		
		txCode = new JTextField();
		txCode.setText(user.getCode());
		txCode.setBackground(Color.WHITE);
		panel_6.add(txCode);
		txCode.setColumns(10);
		
		JButton btUpdate = new JButton("Update");
		btUpdate.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(txAddress.getText().equals("") || txCode.getText().equals("")
						|| txID.getText().equals("") || txName.getText().equals("")
						|| txSurname.getText().equals("") || textField.getText().equals("") || passwordField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "fields cannot be empty while updating");
				}else if(txAddress.getText().equals(user.getAddress()) && txCode.getText().equals(user.getCode())
						&& txID.getText().equals(user.getId()) && txName.getText().equals(user.getName())
						&& txSurname.getText().equals(user.getSurname()) && passwordField.getText().equals(user.getPassword())
						&& textField.getText().equals(user.getUsername())){
					JOptionPane.showMessageDialog(null, "you need to modify some values to perfrom and update");
				}
				else if(checkStudentRepeatedID(txID.getText())){
					JOptionPane.showMessageDialog(null, "Another student is using the same id");
				}
				else if(checkStudentRepeatedUsername()){
					JOptionPane.showMessageDialog(null, "Another student is using the same username");
				}
				else{
					Student previousUser = student;
					Student newUser = student;
					newUser.setId(user.getId());
					newUser.setName(txName.getText());
					newUser.setSurname(txSurname.getText());
					newUser.setAddress(txAddress.getText());
					newUser.setUsername(textField.getText());
					newUser.setCode(txCode.getText());
					newUser.setPassword(passwordField.getText());
					newUser.setIdNumber(txID.getText());
					newUser.setEnrolments(user.getEnrolments());
					try {
						sB.deleteStudentById(previousUser.getIdNumber());
						sB.addStudentWithID(newUser);
						JOptionPane.showMessageDialog(null, "Succesfully updated");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "error updating");
					} 
					student = newUser;
				}
			}
		});
		panel_6.add(btUpdate);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8, BorderLayout.NORTH);
		
		JLabel lbViewSection = new JLabel("View Section");
		lbViewSection.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_8.add(lbViewSection);
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9, BorderLayout.CENTER);
		
		JButton btViewEnrolments = new JButton("View Enrolments");
		btViewEnrolments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder  sb = new StringBuilder();
				for(String cName: user.getEnrolments())
				{
					try {
						int grade = sB.findStudentGrade(user.getIdNumber(),cName);
						if(grade!=-1)
						sb.append("Course: " + cName + " grade: [" + grade + "] \n");
						else{
							sb.append("Course: " + cName + " grade: [" + " " + "] \n");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, "error viewing enrolments");
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, sb.toString());
			}
		});
		panel_9.add(btViewEnrolments);
		
		JButton btViewExams = new JButton("View Exams");
		btViewExams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				for(String cName: user.getEnrolments()){
					try {
						List<Date> ds= cB.findAllExams(cName);
						sb.append("Course: [" + cName + "] Exams: ");
						for(Date d:ds) { sb.append( d + " ");}
						sb.append("\n");
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, "error viewing exams");
					}
				}
				JOptionPane.showMessageDialog(null, sb.toString());
			}
		});
		panel_9.add(btViewExams);
		
		JButton btAllCourses = new JButton("View Courses");
		btAllCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				List<Course> cs = cB.findAll();
				for(Course c : cs) {
					sb.append("Course: [" + c.getCourseName() + "] Start Date: [" + c.getStartDate() +
							"] End date :[" + c.getEndDate() + "] Teacher: [" + c.getTeacher() + "] \n");
				}
				JOptionPane.showMessageDialog(null, sb.toString());
			}
		});
		panel_9.add(btAllCourses);
		
		JPanel panel_10 = new JPanel();
		panel_7.add(panel_10, BorderLayout.SOUTH);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_11.add(panel_12, BorderLayout.NORTH);
		
		JLabel lbEnrolSection = new JLabel("Enrol Section");
		lbEnrolSection.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_12.add(lbEnrolSection);
		
		JPanel panel_13 = new JPanel();
		panel_11.add(panel_13, BorderLayout.SOUTH);
		
		JLabel lbCourseName = new JLabel("Course Name: ");
		panel_13.add(lbCourseName);
		
		txCName = new JTextField();
		panel_13.add(txCName);
		txCName.setColumns(10);
		
		JButton btnNewButton = new JButton("Enroll");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txCName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Field Course name cannot be empty");
				}
				else if(checkStEnrolments()){
					JOptionPane.showMessageDialog(null, "already enrol in that course");
				}else{
					Student previousUser = user;
					Student newUser = user;
					newUser.addEnrolment(txCName.getText());
					try {
						sB.deleteStudentById(previousUser.getIdNumber());
						sB.addStudentWithID(newUser);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Succesfully Enroled!");
					txCName.setText("");
				}
			}

			
		});
		panel_13.add(btnNewButton);
		
		JButton btDeleteEnroll = new JButton("Delete Enroll");
		btDeleteEnroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txCName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Field Course name cannot be empty");
				}
				else if(checkStEnrolments()==false){
					JOptionPane.showMessageDialog(null, "not enroled in that course");
				}else{
					Student previousUser = user;
					Student newUser = user;
					newUser.deleteEnrolment(txCName.getText());
					try {
						sB.deleteStudentById(previousUser.getIdNumber());
						sB.addStudentWithID(newUser);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Succesfully deleted!");
					txCName.setText("");
				}
			}
		});
		panel_13.add(btDeleteEnroll);
		
		JPanel panel_14 = new JPanel();
		panel.add(panel_14, BorderLayout.SOUTH);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_14.add(btnNewButton_1, BorderLayout.WEST);
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setUser(Student st) {
		this.student = st;
		
	}
	private boolean checkStEnrolments() {
		for(String cName: student.getEnrolments())
		{
			if(cName.equals(txCName.getText())){
				return true;
			}
		}
		return false;
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
				if(st.getUsername().equals(txSurname.getText()))
				{
					return true;
				}
			}
			return false;
		}else{
			return false;
		}
	}

}
