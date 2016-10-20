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

@SuppressWarnings("serial")
public class UpdateStaffInfoDialog extends JDialog {
	private JTextField tfUsername;
	private JPasswordField tfpassword;
	private JPasswordField tfSurePassword;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UpdateStaffInfoDialog(String username) {
		setVisible(true);
		setTitle("员工信息修改");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(106, 27, 54, 15);
		getContentPane().add(label);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(199, 24, 119, 21);
		getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u5BC6\u7801");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(106, 65, 54, 15);
		getContentPane().add(lblNewLabel);
		
		tfpassword = new JPasswordField();
		tfpassword.setEchoChar('*');
		tfpassword.setBounds(199, 62, 119, 21);
		getContentPane().add(tfpassword);
		
		JLabel label_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_1.setBounds(106, 109, 54, 15);
		getContentPane().add(label_1);
		
		tfSurePassword = new JPasswordField();
		tfSurePassword.setEchoChar('*');
		tfSurePassword.setBounds(199, 106, 119, 21);
		getContentPane().add(tfSurePassword);
		
		JLabel label_2 = new JLabel("\u89D2\u8272");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(106, 156, 54, 15);
		getContentPane().add(label_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Service", "Administrator", "Buyer"}));
		comboBox.setBounds(199, 153, 119, 21);
		getContentPane().add(comboBox);
		
		{
			String cmd="select username,password,role from staff_Infor where username='"+username.trim()+"';";
			ConnectDatabase conn=new ConnectDatabase();
			try {
				ResultSet re=conn.state.executeQuery(cmd);
				if(re.next()){
					tfUsername.setText(re.getString(1));
					tfpassword.setText(re.getString(2));
					tfSurePassword.setText(re.getString(2));
					for(int i=0;i<comboBox.getItemCount();i++){
						if(re.getString(3).trim().equals(comboBox.getItemAt(i).toString().trim())){
							comboBox.setSelectedIndex(i);
							break;
						}
					}
				}
				re.close();
				conn.state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(cmd);
		}
		
		JButton UpdateButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		UpdateButton.addActionListener(new ActionListener() {     //////确认修好按钮
			@SuppressWarnings({ "deprecation", "static-access" })
			public void actionPerformed(ActionEvent arg0) {
				if(tfpassword.getText().trim().equals(tfSurePassword.getText().trim())){
					String cmd="update staff_Infor set password='"+tfpassword.getText().trim()+"',role='"+comboBox.getSelectedItem().toString().trim()+"' where username='"+username+"';";
					ConnectDatabase conn=new ConnectDatabase();
					try {
						conn.state.executeUpdate(cmd);
						conn.state.close();
						new JOptionPane().showMessageDialog(UpdateButton, "信息修改成功！！！");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println(cmd);
				}
				else{
					new JOptionPane().showMessageDialog(UpdateButton, "前后密码不一致!!!");
				}
			}
		});
		UpdateButton.setBounds(100, 204, 93, 23);
		getContentPane().add(UpdateButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setBounds(225, 204, 93, 23);
		getContentPane().add(cancelButton);
	}

}
