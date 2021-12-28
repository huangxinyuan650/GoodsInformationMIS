package hxy;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AddLinkManDialog extends JDialog {
	private JTextField tfName;
	private JTextField tfTelephone;
	private JTextField tfPhone;
	private JTextField tfEmail;
	public AddLinkManDialog() {
		setVisible(true);
		setTitle("联系人添加");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u6240\u5C5E\u516C\u53F8");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 16, 68, 18);
		getContentPane().add(label);
		
		JComboBox<String> companyComboBox = new JComboBox<String>();
		companyComboBox.setBounds(88, 15, 112, 21);
		getContentPane().add(companyComboBox);
		{
			String cmd1="select company_name from Enterprise;";
			ConnectDatabase conn1=new ConnectDatabase();
			try {
				ResultSet re1=conn1.state.executeQuery(cmd1);
				while(re1.next()){
					companyComboBox.addItem(re1.getString(1));					
				}
				re1.close();
				conn1.state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		JLabel label_1 = new JLabel("\u59D3\u540D");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(230, 19, 54, 15);
		getContentPane().add(label_1);
		
		tfName = new JTextField();
		tfName.setBounds(294, 15, 112, 21);
		getContentPane().add(tfName);
		tfName.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7535\u8BDD");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 70, 68, 15);
		getContentPane().add(label_2);
		
		tfTelephone = new JTextField();
		tfTelephone.setBounds(88, 67, 112, 21);
		getContentPane().add(tfTelephone);
		tfTelephone.setColumns(10);
		
		JLabel label_3 = new JLabel("\u624B\u673A");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(230, 70, 54, 15);
		getContentPane().add(label_3);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(294, 67, 112, 21);
		getContentPane().add(tfPhone);
		tfPhone.setColumns(10);
		
		JLabel label_4 = new JLabel("\u90AE\u7BB1");
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(24, 126, 54, 15);
		getContentPane().add(label_4);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(88, 126, 161, 21);
		getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		JButton addLinkManButton = new JButton("\u6DFB\u52A0\u8054\u7CFB\u4EBA");
		addLinkManButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if((!"".equals(tfName.getText().trim()))&&(!"".equals(companyComboBox.getSelectedItem().toString().trim()))){
					String cmd="select company_name from Linkman where name='"+tfName.getText().trim()+"' and company_name='"+companyComboBox.getSelectedItem().toString().trim()+"';";
					ConnectDatabase conn=new ConnectDatabase();
					try {
						ResultSet re=conn.state.executeQuery(cmd);
						if(re.next()){
							re.close();
							new JOptionPane().showMessageDialog(addLinkManButton, "该联系人已经存在！！！");
						}
						else{
							re.close();
							cmd="insert into Linkman values('"+companyComboBox.getSelectedItem().toString().trim()+"','"+tfName.getText().trim()+"','"
							+tfTelephone.getText().trim()+"','"+tfPhone.getText().trim()+"','"+tfEmail.getText().trim()+"');";
							conn.state.executeUpdate(cmd);
							conn.state.close();
							new JOptionPane().showMessageDialog(addLinkManButton, "联系人添加成功！！！");
							System.out.println(cmd);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					//System.out.println(cmd);
				}
				else{
					new JOptionPane().showMessageDialog(addLinkManButton, "请填入联系人信息！！！");
				}
			}
		});
		addLinkManButton.setBounds(88, 204, 112, 33);
		getContentPane().add(addLinkManButton);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setBounds(294, 204, 112, 33);
		getContentPane().add(button_1);
	}

}
