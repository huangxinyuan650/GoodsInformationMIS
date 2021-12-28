package hxy;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LogIn {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	ConnectDatabase conn=null;
	Statement state=null;
	ResultSet re=null;
	public LogIn() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();	
		frame.setVisible(true);
		//frame.getContentPane().setBackground();
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 529, 347);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblHang = new JLabel("Hang&650");
		lblHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblHang.setFont(new Font("宋体", Font.PLAIN, 18));
		lblHang.setBounds(223, 30, 106, 38);
		frame.getContentPane().add(lblHang);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("宋体", Font.PLAIN, 16));
		lblUsername.setBounds(72, 75, 86, 27);
		frame.getContentPane().add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(193, 76, 183, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("PassWord");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("宋体", Font.PLAIN, 16));
		lblPassword.setBounds(73, 130, 85, 27);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");   //////////////////登录按钮监视器
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 16));
		passwordField.setEchoChar('*');
		passwordField.setBounds(193, 130, 183, 27);
		frame.getContentPane().add(passwordField);
		
		passwordField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==(char)10){
					if((!"".equals(textField.getText().trim()))&&(!"".equals(passwordField.getText().trim()))){/////判断输入的值是否为空
						conn=new ConnectDatabase();  /////////将连接数据库对象实例化准备用户名密码验证
						String cmd=null;
						cmd="select password,role from staff_Infor where username='"+textField.getText().trim()+"';";
						try {
							re=conn.state.executeQuery(cmd);
							if(re.next()){
								if(passwordField.getText().equals(re.getString(1).trim())){ ///////////密码验证成功
									System.out.println("Log in succeed!!!");
									frame.dispose(); /////////登录界面退出
									if(re.getString(2).trim().equals("Administrator".trim())){    ////////判断权限进入不同的界面，管理员
										new OwnerForm();
									}
									else if(re.getString(2).trim().equals("Buyer".trim())){//////////采购员
										new BuyerForm();
									}
									else if(re.getString(2).trim().equals("Service".trim())){                  /////////////客服									
										new ServiceForm(textField.getText().trim());
									}
									else{
										JOptionPane.showMessageDialog(frame, "用户权限不存在！！！\n请重新登录");
									}
								}
								else{                                  ///////////////////密码验证失败
									JOptionPane.showMessageDialog(frame, "密码错误！！！\n请重新输入");
								}
							}
							else{
								JOptionPane.showMessageDialog(frame, "用户名不存在！！！\n请重新输入");
							}
							conn.conn.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(frame, e1);
						}
					}
				
				}
			}
		});
		

		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if((!"".equals(textField.getText().trim()))&&(!"".equals(passwordField.getText().trim()))){/////判断输入的值是否为空
					try {
						conn=new ConnectDatabase();  /////////将连接数据库对象实例化准备用户名密码验证
						String cmd=null;
						cmd="select password,role from staff_Infor where username='"+textField.getText().trim()+"';";						
						re=conn.state.executeQuery(cmd);
						if(re.next()){
							if(passwordField.getText().equals(re.getString(1).trim())){ ///////////密码验证成功
								System.out.println("Log in succeed!!!");
								frame.dispose(); /////////登录界面退出
								if(re.getString(2).trim().equals("Administrator".trim())){    ////////判断权限进入不同的界面，管理员
									new OwnerForm();
								}
								else if(re.getString(2).trim().equals("Buyer".trim())){//////////采购员
									new BuyerForm();
								}
								else if(re.getString(2).trim().equals("Service".trim())){                  /////////////客服									
									new ServiceForm(textField.getText().trim());
								}
								else{
									JOptionPane.showMessageDialog(frame, "用户权限不存在！！！\n请重新登录");
								}
							}
							else{                                  ///////////////////密码验证失败
								JOptionPane.showMessageDialog(frame, "密码错误！！！\n请重新输入");
							}
						}
						else{
							JOptionPane.showMessageDialog(frame, "用户名不存在！！！\n请重新输入");
						}
						conn.conn.close();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "Database connection Error!!!"+e);
						e.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(frame, "请输入用户名和密码!!!");
				}
			}
		});
		btnLogin.setBounds(193, 189, 68, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(btnRegister, "请以管理员身份进入添加信息！！！");
			}
		});
		btnRegister.setBounds(279, 189, 92, 23);
		frame.getContentPane().add(btnRegister);
	}
}
