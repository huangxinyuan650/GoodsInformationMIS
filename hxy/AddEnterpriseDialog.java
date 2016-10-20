package hxy;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AddEnterpriseDialog extends JDialog {
	private JTextField tfEnID;
	private JTextField tfEnName;
	private JTextField tfEnShortName;
	private JTextField tfOwner;
	private JTextField tfEnAddress;
	private JTextField tfPostcode;
	private JTextField tfAccount;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AddEnterpriseDialog() {
		setVisible(true);
		setTitle("企业信息添加");
		setBounds(100, 100, 450, 354);
		getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("\u4F01\u4E1AID");
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(10, 13, 54, 15);
		getContentPane().add(lblid);
		
		tfEnID = new JTextField();
		tfEnID.setBounds(78, 10, 104, 21);
		getContentPane().add(tfEnID);
		tfEnID.setColumns(10);
		
		JLabel label = new JLabel("\u4F01\u4E1A\u540D\u79F0");
		label.setBounds(207, 13, 54, 15);
		getContentPane().add(label);
		
		tfEnName = new JTextField();
		tfEnName.setBounds(272, 10, 133, 21);
		getContentPane().add(tfEnName);
		tfEnName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u7B80\u79F0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 50, 54, 15);
		getContentPane().add(label_1);
		
		tfEnShortName = new JTextField();
		tfEnShortName.setBounds(78, 47, 104, 21);
		getContentPane().add(tfEnShortName);
		tfEnShortName.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6CD5\u4EBA");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(207, 50, 54, 15);
		getContentPane().add(label_2);
		
		tfOwner = new JTextField();
		tfOwner.setBounds(272, 47, 133, 21);
		getContentPane().add(tfOwner);
		tfOwner.setColumns(10);
		
		JLabel label_3 = new JLabel("\u4F01\u4E1A\u5730\u5740");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(10, 99, 54, 15);
		getContentPane().add(label_3);
		
		tfEnAddress = new JTextField();
		tfEnAddress.setBounds(78, 96, 327, 21);
		getContentPane().add(tfEnAddress);
		tfEnAddress.setColumns(10);
		
		JLabel label_4 = new JLabel("\u90AE\u7F16");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(10, 146, 54, 15);
		getContentPane().add(label_4);
		
		tfPostcode = new JTextField();
		tfPostcode.setBounds(78, 143, 104, 21);
		getContentPane().add(tfPostcode);
		tfPostcode.setColumns(10);
		
		JLabel label_5 = new JLabel("\u5F00\u6237\u94F6\u884C");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(207, 146, 54, 15);
		getContentPane().add(label_5);
		
		JComboBox bankComboBox = new JComboBox();
		bankComboBox.setModel(new DefaultComboBoxModel(new String[] {"中国银行", "建设银行", "工商银行", "农业银行", "中国信合"}));
		bankComboBox.setBounds(272, 143, 133, 21);
		getContentPane().add(bankComboBox);
		
		JLabel label_6 = new JLabel("\u8D26\u6237");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(10, 192, 54, 15);
		getContentPane().add(label_6);
		
		tfAccount = new JTextField();
		tfAccount.setBounds(78, 189, 327, 21);
		getContentPane().add(tfAccount);
		tfAccount.setColumns(10);
		
		JButton submitButton = new JButton("\u6DFB\u52A0");
		submitButton.addActionListener(new ActionListener() {     /////////企业天剑按钮监视器
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if((!"".equals(tfEnID.getText().trim()))&&(!"".equals(tfEnName.getText().trim()))){
					String cmd="select company_name from Enterprise where id='"+tfEnID.getText().trim()+"';";
					ConnectDatabase conn=new ConnectDatabase();
					try {
						ResultSet re=conn.state.executeQuery(cmd);
						if(re.next()){ ////////////该企业已经存在
							re.close();
							new JOptionPane().showMessageDialog(submitButton, "该企业已经存在！！！"+re.getString(1));
						}
						else{ /////////该企业不存在
							re.close();
							cmd="insert into Enterprise values('"+tfEnID.getText().trim()+"','"+tfEnName.getText().trim()+"','"+tfEnShortName.getText().trim()+"','"+tfOwner.getText().trim()
							    +"','"+tfEnAddress.getText().trim()+"',"+Integer.parseInt(tfPostcode.getText().trim())+",'"+bankComboBox.getSelectedItem().toString().trim()+"','"+tfAccount.getText().trim()+"');";
						    conn.state.executeUpdate(cmd);
						    conn.state.close();
						    new JOptionPane().showMessageDialog(submitButton, "企业信息添加成功！！！");
						}
					} catch (SQLException e) {
						e.printStackTrace();
						new JOptionPane().showMessageDialog(submitButton, "提交失败！！！");
					}
				}
				else{
					new JOptionPane().showMessageDialog(submitButton, "请输入企业信息！！！");
				}
				
			}
		});
		submitButton.setBounds(78, 255, 93, 23);
		getContentPane().add(submitButton);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setBounds(281, 255, 93, 23);
		getContentPane().add(button_1);
	}

	void init(){
		
	}
}
