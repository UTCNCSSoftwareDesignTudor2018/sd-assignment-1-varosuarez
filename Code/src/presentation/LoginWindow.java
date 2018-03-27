package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import bussiness.StudentBussiness;
import bussiness.TeacherBussiness;
import data.model.Student;
import data.model.Teacher;

import javax.swing.JPasswordField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class LoginWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txUserName;
	private JPasswordField password;
	private StudentBussiness sB= new StudentBussiness();
	private TeacherBussiness tB= new TeacherBussiness();
	creationUserWindow creationWindow;
	private LoggedUserWindow userWindow;
	private LoggedTeacherWindow teacherWindow;
	private LoginWindow lwd = this;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lbLogin = new JLabel("Login");
		lbLogin.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel.add(lbLogin);
		
		JPanel pncenter = new JPanel();
		frame.getContentPane().add(pncenter, BorderLayout.CENTER);
		pncenter.setLayout(new BorderLayout(0, 0));
		
		JPanel pnNorth = new JPanel();
		pncenter.add(pnNorth, BorderLayout.NORTH);
		pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbUser = new JLabel("User: ");
		pnNorth.add(lbUser);
		
		txUserName = new JTextField();
		pnNorth.add(txUserName);
		txUserName.setColumns(10);
		
		JPanel pncenter2 = new JPanel();
		pncenter.add(pncenter2, BorderLayout.CENTER);
		pncenter2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		pncenter2.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbPassword = new JLabel("Password: ");
		panel_1.add(lbPassword);
		
		password = new JPasswordField();
		password.setColumns(10);
		panel_1.add(password);
		
		JPanel panel_2 = new JPanel();
		pncenter2.add(panel_2);
		
		JButton btLogin = new JButton("Login");
		btLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = txUserName.getText();
				@SuppressWarnings("deprecation")
				String pass = password.getText();
				
				Student st = sB.findByUsernamePassword(username, pass);
				Teacher t = tB.findByUsernamePassword(username, pass);
				
				if(username.equals("") || pass.equals("")){
					JOptionPane.showMessageDialog(null, "fields cannot be empty!.");
				}
				else if(st == null && t==null){
					JOptionPane.showMessageDialog(null, "Error 404 User not found");
				}else if(t!=null){
					try{
						teacherWindow =  new LoggedTeacherWindow(lwd, t);
						teacherWindow.setModal(true);
						teacherWindow.setLocationRelativeTo(lwd);
						teacherWindow.setVisible(true);
						teacherWindow.setSize(300,300);
					}
					catch(Exception e){
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!.");
					}
				}else if(st!=null){
					try{
						userWindow =  new LoggedUserWindow(lwd, st);
						userWindow.setModal(true);
						userWindow.setLocationRelativeTo(lwd);
						userWindow.setVisible(true);
						userWindow.setSize(300,300);
					}
					catch(Exception e){
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!.");
					}
				}

			}
		});
		panel_2.add(btLogin);
		
		JButton btCreateAccount = new JButton("Create Account");
		btCreateAccount.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    creationWindow = new creationUserWindow(lwd);
			creationWindow.setModal(true);
			creationWindow.setLocationRelativeTo(lwd);
			creationWindow.setVisible(true);
			creationWindow.setSize(new Dimension(400, 400));
			creationWindow.setResizable(false);
			}
		});
		panel_2.add(btCreateAccount);
	}

}
