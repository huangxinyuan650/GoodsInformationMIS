package hxy;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class AddStaffDialog extends JDialog {
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JPasswordField surePassword;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AddStaffDialog() {
		setVisible(true);
		setTitle("员工添加");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(107, 29, 54, 15);
		getContentPane().add(label);
		
		tfUsername = new JTextField();
		tfUsername.setFont(new Font("宋体", Font.PLAIN, 14));
		tfUsername.setBounds(190, 26, 150, 21);
		getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(107, 76, 54, 15);
		getContentPane().add(label_1);
		
		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("宋体", Font.PLAIN, 16));
		tfPassword.setEchoChar('*');
		tfPassword.setBounds(190, 73, 150, 21);
		getContentPane().add(tfPassword);
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(107, 116, 54, 15);
		getContentPane().add(label_2);
		
		surePassword = new JPasswordField();
		surePassword.setFont(new Font("宋体", Font.PLAIN, 16));
		surePassword.setEchoChar('*');
		surePassword.setBounds(190, 116, 150, 21);
		getContentPane().add(surePassword);
		
		
		JComboBox roleComboBox = new JComboBox();
		roleComboBox.setModel(new DefaultComboBoxModel(new String[] {"Service", "Administrator", "Buyer"}));
		roleComboBox.setBounds(190, 158, 150, 21);
		getContentPane().add(roleComboBox);
		
		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings({ "static-access", "deprecation" })
			public void actionPerformed(ActionEvent arg0) {
				String cmd="select username from staff_Infor where username='"+tfUsername.getText().trim()+"';";
				ConnectDatabase conn1=new ConnectDatabase();
				try {
					ResultSet re=conn1.state.executeQuery(cmd);
					if(re.next()){
						re.close();
						conn1.state.close();
						new JOptionPane().showMessageDialog(button, "该用户名已经存在！！！");
					}
					else{
						re.close();
						conn1.state.close();
						if(tfPassword.getText().trim().equals(surePassword.getText().trim())){
							cmd="insert into staff_Infor values('"+tfUsername.getText().trim()+"','"+tfPassword.getText().trim()+"','"+roleComboBox.getSelectedItem().toString().trim()+"');";
							ConnectDatabase conn=new ConnectDatabase();
							conn.state.executeUpdate(cmd);
							conn.state.close();
							new JOptionPane().showMessageDialog(button, "员工信息添加成功！！！");
							System.out.println(cmd);							
						}
						else{
							new JOptionPane().showMessageDialog(button, "前后密码不匹配！！！请重新输入");
						}
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		button.setBounds(107, 201, 93, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setBounds(247, 201, 93, 23);
		getContentPane().add(button_1);
		
		JLabel label_3 = new JLabel("\u89D2\u8272");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(107, 161, 54, 15);
		getContentPane().add(label_3);

	}
}
