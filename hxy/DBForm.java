package hxy;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DBForm extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField userName;
	private JPasswordField password;
	private JTextField database;
	private JComboBox<String> databaseType;
	private JButton okButton;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DBForm() {
		setTitle("Database");
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 357, 276);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DatabaseSet");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(114, 10, 108, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel lblDatabasetype = new JLabel("DatabaseType");
		lblDatabasetype.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabasetype.setBounds(64, 42, 82, 15);
		contentPanel.add(lblDatabasetype);
		
		databaseType = new JComboBox();
		databaseType.setModel(new DefaultComboBoxModel(new String[] {"MySql", "SQLServer"}));
		databaseType.setBounds(181, 39, 108, 21);
		contentPanel.add(databaseType);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(64, 76, 82, 15);
		contentPanel.add(lblUsername);
		
		userName = new JTextField();
		userName.setBounds(181, 73, 108, 21);
		contentPanel.add(userName);
		userName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(64, 113, 82, 15);
		contentPanel.add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(181, 110, 108, 21);
		contentPanel.add(password);
		
		okButton = new JButton("OK");
		okButton.setVisible(false);
		//okButton.setVisible(true);
		//OKButton listener set
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Database db = getDatabase();				
				//初始默认数据库设置完成，接下来创建新的数据库并创建表初始化
				//创建连接对象
				ConnectDatabase conn = new ConnectDatabase(db);
				//获取连接的statement对象
				try {
					///创建新的数据库并对并将默认数据库改为新建的数据库
					String dbName = "initializedb";
					conn.state.executeUpdate("create database "+dbName+";");
					conn.state.close();
					conn.conn.close();
					db.setName(dbName);
					new InitializeDatabase(db);
					XMLReader xr = new XMLReader();
					xr.setXML(db);
					JOptionPane.showMessageDialog(okButton, "数据库初始化成功！！！");
					dispose();
					new LogIn();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(okButton, "数据库初始化失败！！！"+e);
					e.printStackTrace();
				}				
//				JOptionPane.showMessageDialog(okButton, "数据库配置完成!!!\n");				
//				dispose();
//				new LogIn();
			}
		});
		okButton.setBounds(71, 191, 93, 23);
		contentPanel.add(okButton);
		
		JButton testButton = new JButton("Test");
		/////////////TestButton listener
		testButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectDatabase conn = new ConnectDatabase(getDatabase());
				if(conn.conn!=null){
					okButton.setVisible(true);
					JOptionPane.showMessageDialog(testButton, "Test Successed!!!");
				}
				else{
					JOptionPane.showMessageDialog(testButton, "Test Failed!!!");
				}
			}
		});
		testButton.setBounds(191, 191, 93, 23);
		contentPanel.add(testButton);
		
		JLabel lblNewLabel_1 = new JLabel("Database");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(64, 155, 82, 15);
		contentPanel.add(lblNewLabel_1);
		
		database = new JTextField();
		database.setText("mysql");
		database.setBounds(181, 152, 108, 21);
		contentPanel.add(database);
		database.setColumns(10);
		
	}
	@SuppressWarnings("static-access")
	public Database getDatabase(){
		Database db = new Database();
		String dbType = databaseType.getSelectedItem().toString().trim();
		System.out.println(dbType);
		String dbUser = userName.getText().trim();
		@SuppressWarnings("deprecation")
		String dbPassword = password.getText().trim();
		String dbName = database.getText().trim();
		if(dbType==null|dbUser==null|dbPassword==null|dbName==null){
			new JOptionPane().showMessageDialog(okButton, "请完善数据库信息!!!");;
		}
		else{
			if(dbType.equals("MySql")){
				db.setType(dbType);
				db.setDriver("com.mysql.jdbc.Driver");
				db.setUri("localhost");
				db.setPort("3306");
				db.setUser(dbUser);
				db.setPassword(dbPassword);
				db.setName(dbName);
			}
			else{
				db.setType(dbType);
				db.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				db.setUri("localhost");
				db.setPort("1433");
				db.setUser(dbUser);
				db.setPassword(dbPassword);
				db.setName(dbName);
			}
		}			
		return db;
	}
	
}
