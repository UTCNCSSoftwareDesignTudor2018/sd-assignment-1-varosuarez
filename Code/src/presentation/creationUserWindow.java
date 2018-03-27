package presentation;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import bussiness.StudentBussiness;
import data.model.Student;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class creationUserWindow extends JDialog{

	private JFrame frame;
	@SuppressWarnings("unused")
	private LoginWindow lW;
	private StudentBussiness sB = new StudentBussiness();
	
	
	private JTextField txName;
	private JTextField txSurname;
	private JTextField txAddress;
	private JTextField txID;
	private JTextField txUserName;
	private JPasswordField passwordField;
	private JTextField textField_5;
	private JTextField txStCode;

	/**
	 * Create the application.
	 * @param lwd 
	 */
	public creationUserWindow(LoginWindow lwd) {
		setMinimumSize(new Dimension(500, 200));
		setPreferredSize(new Dimension(500, 500));
		this.lW = lwd;
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lbTitle = new JLabel("Account Creation");
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lbTitle);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		
		JLabel label = new JLabel("Name: ");
		panel_3.add(label);
		
		txName = new JTextField();
		txName.setColumns(10);
		panel_3.add(txName);
		
		JLabel label_1 = new JLabel("Surname: ");
		panel_3.add(label_1);
		
		txSurname = new JTextField();
		txSurname.setColumns(10);
		panel_3.add(txSurname);
		
		JLabel label_2 = new JLabel("Address");
		panel_3.add(label_2);
		
		txAddress = new JTextField();
		txAddress.setText("");
		txAddress.setColumns(10);
		panel_3.add(txAddress);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		JLabel label_3 = new JLabel("ID: ");
		panel_4.add(label_3);
		
		txID = new JTextField();
		txID.setColumns(10);
		panel_4.add(txID);
		
		JLabel label_4 = new JLabel("Username: ");
		panel_4.add(label_4);
		
		txUserName = new JTextField();
		txUserName.setColumns(10);
		panel_4.add(txUserName);
		
		JLabel label_5 = new JLabel("Password: ");
		panel_4.add(label_5);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		panel_4.add(passwordField);
		
		JLabel label_6 = new JLabel("Student Code: ");
		panel_4.add(label_6);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		panel_4.add(textField_5);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.NORTH);
		
		JLabel lbStudentsCode = new JLabel("Student Code: ");
		panel_6.add(lbStudentsCode);
		
		txStCode = new JTextField();
		panel_6.add(txStCode);
		txStCode.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.SOUTH);
		
		JButton btBack = new JButton("Back");
		btBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_7.add(btBack);
		
		JButton btCreate = new JButton("Create");
		panel_7.add(btCreate);
		btCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txID.getText().equals("") || txName.getText().equals("") || txStCode.getText().equals("")
						|| txSurname.getText().equals("") || passwordField.getText().equals("") || txAddress.getText().equals("")
						|| txUserName.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Fields in section add Student cannot be empty!");
				}
				else if(checkStudentRepeatedID(txID.getText()))
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
					Student st = new Student();
					st.setIdNumber(txID.getText());
					st.setName(txName.getText());
					st.setSurname(txSurname.getText());
					st.setUsername(txUserName.getText());
					st.setPassword(passwordField.getText());
					st.setAddress(txAddress.getText());
					st.setCode(txStCode.getText());
					
					try {
						sB.addStudent(st);
						JOptionPane.showMessageDialog(null, "Profile Succesfully created");
						dispose();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error adding student!");
						e1.printStackTrace();
					} 
				}
			}
		});
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
				if(st.getUsername().equals(txUserName.getText()))
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

}
