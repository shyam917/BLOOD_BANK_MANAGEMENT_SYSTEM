package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

import sqlUtils.CheckForData;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class OrgLogin {

	public JFrame frame;
	private final JPanel panel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	Connection con;
	
	public OrgLogin(Connection con) {
		this.con = con;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.RED);
		frame.setBounds(100, 100, 1062, 674);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 646, 637);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Orgnisation Login");
		lblNewLabel_1.setBounds(174, 10, 288, 47);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel_1.setForeground(new Color(178, 34, 34));
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(54, 347, 523, 280);
		panel_1.setBackground(new Color(240, 255, 240));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(198, 54, 186, 42);
		panel_1.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(198, 135, 186, 42);
		panel_1.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("User Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_3.setBounds(71, 56, 117, 28);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Password");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_3_1.setBounds(71, 137, 117, 28);
		panel_1.add(lblNewLabel_3_1);
		
		JButton btnNewButton_1 = new JButton("LOGIN");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String username = textField.getText();
					String password = passwordField.getText();
					if (username.equals("") || password.equals("")) {
		                JOptionPane.showMessageDialog(null, "Please fill all the fields");
		            }
					else {
		                String query = "select * from organization where org_name = '" + username + "' and password = '" + password + "'";
		                CheckForData check = new CheckForData(con);
		                if (check.check_data_exist(query)) {
		                }
		                if (check.check_data_exist(query)){
		                    String[] fields = new String[5];
		                    fields[0] = "";
		                    fields[1] = "";
		                    fields[2] = "";
		                    fields[3] = "";
							fields[4] = "";
		                    try{
		                        String query1 = "select * from 	organization where org_name = ?;";
		                        PreparedStatement pst = con.prepareStatement(query1);
		                        pst.setString(1, username);
		                        ResultSet rs = pst.executeQuery();
		                        if(rs.next()) {
		                        	
		                            fields[0] = rs.getString("org_name");
		                            fields[1] = rs.getString("email");
		                            fields[2] = rs.getString("contact");
		                            fields[3] = rs.getString("city");
									fields[4] = rs.getString("state");
		                        }
		                        
		                    }
		                    
		                    catch(Exception e3) {
		                    	System.out.println(e3);
		                    }
		            	OrgInfo d_info  = new OrgInfo(fields,con);
		             
		                d_info.frame.setVisible(true);
		                frame.dispose();
		            }
		               else {
		            	JOptionPane.showMessageDialog(null, "Invalid credentials");
		      
		            }
					}
					}
				});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBackground(new Color(240, 255, 255));
		btnNewButton_1.setBounds(176, 216, 91, 35);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommonLogin cobj = new CommonLogin(con);
				cobj.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1_1.setBackground(new Color(240, 255, 240));
		btnNewButton_1_1.setBounds(311, 216, 91, 35);
		panel_1.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(".\\images\\bg26.png"));
		lblNewLabel_2.setBounds(54, 34, 534, 539);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(".\\images\\bg8.jpg"));
		lblNewLabel.setBounds(703, 116, 289, 289);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Click Here");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrgSignup orobj = new OrgSignup(con);
				orobj.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		btnNewButton.setBounds(746, 407, 158, 45);
		frame.getContentPane().add(btnNewButton);
	}
}

