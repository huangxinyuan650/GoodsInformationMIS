package hxy;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class UpdateLinkManDialog extends JDialog {
	private JTextField tfCompany;
	private JTextField tfName;
	private JTextField tfTelephone;
	private JTextField tfPhone;
	private JTextField tfEmail;
	public UpdateLinkManDialog(String company_name,String name) {
		setVisible(true);
		setTitle("联系人信息修改");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u516C\u53F8");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 22, 54, 15);
		getContentPane().add(label);
		
		tfCompany = new JTextField();
		tfCompany.setBounds(74, 19, 111, 21);
		getContentPane().add(tfCompany);
		tfCompany.setColumns(10);
		
		JLabel label_1 = new JLabel("\u59D3\u540D");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(215, 22, 54, 15);
		getContentPane().add(label_1);
		
		tfName = new JTextField();
		tfName.setBounds(279, 19, 133, 21);
		getContentPane().add(tfName);
		tfName.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7535\u8BDD");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 70, 54, 15);
		getContentPane().add(label_2);
		
		tfTelephone = new JTextField();
		tfTelephone.setBounds(74, 67, 111, 21);
		getContentPane().add(tfTelephone);
		tfTelephone.setColumns(10);
		
		JLabel label_3 = new JLabel("\u624B\u673A");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(215, 70, 54, 15);
		getContentPane().add(label_3);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(279, 67, 133, 21);
		getContentPane().add(tfPhone);
		tfPhone.setColumns(10);
		
		JLabel label_4 = new JLabel("\u90AE\u7BB1");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(10, 128, 54, 15);
		getContentPane().add(label_4);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(74, 125, 159, 21);
		getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		{////////必要信息加载
			String cmd="select telephone,phone,email from Linkman where company_name='"+company_name.trim()+"' and name='"+name.trim()+"'";
			System.out.println(cmd);
			ConnectDatabase conn=new ConnectDatabase();
			try {
				ResultSet re=conn.state.executeQuery(cmd);
				while(re.next()){
					tfCompany.setText(company_name);
					tfName.setText(name);
					tfTelephone.setText(re.getString(1));
					tfPhone.setText(re.getString(2));
					tfEmail.setText(re.getString(3));
				}
				re.close();
				conn.state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		JButton updateButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		updateButton.addActionListener(new ActionListener() {     ///////确认提交按钮
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				String cmd="update Linkman set telephone='"+tfTelephone.getText().trim()+"',phone='"+tfPhone.getText().trim()+"',email='"+tfEmail.getText().trim()
						+"' where company_name='"+company_name.trim()+"' and name='"+name.trim()+"';";
				System.out.println(cmd);
				ConnectDatabase conn=new ConnectDatabase();
				try {
					conn.state.executeUpdate(cmd);
					conn.state.close();
					new JOptionPane().showMessageDialog(updateButton, "联系人信息修改成功！！！");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		updateButton.setBounds(74, 194, 93, 23);
		getContentPane().add(updateButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setBounds(279, 194, 93, 23);
		getContentPane().add(cancelButton);
	}

}
