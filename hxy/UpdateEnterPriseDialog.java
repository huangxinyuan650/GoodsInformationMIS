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
public class UpdateEnterPriseDialog extends JDialog {
	private JTextField tfName;
	private JTextField tfShortName;
	private JTextField tfOwner;
	private JTextField tfAddress;
	private JTextField tfPostcode;
	private JTextField tfAccount;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UpdateEnterPriseDialog(String id) {
		setVisible(true);
		setTitle("企业信息修改");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("\u4F01\u4E1AID");
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(10, 23, 54, 15);
		getContentPane().add(lblid);
		
		JLabel enIDLabel = new JLabel("id");
		enIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		enIDLabel.setBounds(86, 23, 95, 15);
		getContentPane().add(enIDLabel);
		
		JLabel label = new JLabel("\u540D\u79F0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(218, 23, 54, 15);
		getContentPane().add(label);
		
		tfName = new JTextField();
		tfName.setBounds(294, 20, 130, 21);
		getContentPane().add(tfName);
		tfName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u7B80\u79F0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 57, 54, 15);
		getContentPane().add(label_1);
		
		tfShortName = new JTextField();
		tfShortName.setBounds(76, 54, 105, 21);
		getContentPane().add(tfShortName);
		tfShortName.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6CD5\u4EBA");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(218, 57, 54, 15);
		getContentPane().add(label_2);
		
		tfOwner = new JTextField();
		tfOwner.setBounds(294, 54, 130, 21);
		getContentPane().add(tfOwner);
		tfOwner.setColumns(10);
		
		JLabel label_3 = new JLabel("\u5730\u5740");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(10, 102, 54, 15);
		getContentPane().add(label_3);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(76, 99, 105, 21);
		getContentPane().add(tfAddress);
		tfAddress.setColumns(10);
		
		JLabel label_4 = new JLabel("\u90AE\u7F16");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(218, 102, 54, 15);
		getContentPane().add(label_4);
		
		tfPostcode = new JTextField();
		tfPostcode.setBounds(294, 99, 130, 21);
		getContentPane().add(tfPostcode);
		tfPostcode.setColumns(10);
		
		JLabel label_5 = new JLabel("\u5F00\u6237\u94F6\u884C");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(10, 144, 54, 15);
		getContentPane().add(label_5);
		
		JComboBox bankComboBox = new JComboBox();
		bankComboBox.setModel(new DefaultComboBoxModel(new String[] {"\u4E2D\u56FD\u94F6\u884C", "\u5EFA\u8BBE\u94F6\u884C", "\u5DE5\u5546\u94F6\u884C", "\u519C\u4E1A\u94F6\u884C", "\u4E2D\u56FD\u4FE1\u5408"}));
		bankComboBox.setBounds(76, 141, 105, 21);
		getContentPane().add(bankComboBox);
		
		JLabel label_6 = new JLabel("\u8D26\u6237");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(10, 179, 54, 15);
		getContentPane().add(label_6);
		
		tfAccount = new JTextField();
		tfAccount.setBounds(76, 176, 259, 21);
		getContentPane().add(tfAccount);
		tfAccount.setColumns(10);
		
		{
			enIDLabel.setText(id.trim());
			String cmd="select company_name,short_name,owner,address,postcode,bank,account from Enterprise where id='"+id.trim()+"';";
			ConnectDatabase conn=new ConnectDatabase();
			try {
				ResultSet re=conn.state.executeQuery(cmd);
				while(re.next()){
					tfName.setText(re.getString(1));
					tfShortName.setText(re.getString(2));
					tfOwner.setText(re.getString(3));
					tfAddress.setText(re.getString(4));
					tfPostcode.setText(re.getString(5));
					tfAccount.setText(re.getString(7));
					String bank=re.getString(6);
					System.out.println(bankComboBox.getItemCount());
					for(int i=0;i<bankComboBox.getItemCount();i++){    ////////设置开户银行
						if(bank.trim().equals(bankComboBox.getItemAt(i).toString().trim())){
							bankComboBox.setSelectedIndex(i);
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
		
		JButton submitButton = new JButton("\u63D0\u4EA4\u4FEE\u6539");
		submitButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				String cmd="update Enterprise set short_name='"+tfShortName.getText().trim()+"',owner='"+tfOwner.getText().trim()+"',address='"
			+tfAddress.getText().trim()+"',postcode='"+tfPostcode.getText().trim()+"',bank='"+bankComboBox.getSelectedItem().toString().trim()
			+"',account='"+tfAccount.getText().trim()+"' where id='"+id+"';";
				System.out.println(cmd);
				ConnectDatabase conn=new ConnectDatabase();
				try {
					conn.state.executeUpdate(cmd);
					conn.state.close();
					new JOptionPane().showMessageDialog(submitButton, "企业信息修改成功！！！");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		submitButton.setBounds(75, 217, 93, 23);
		getContentPane().add(submitButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88\u4FEE\u6539");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setBounds(285, 217, 93, 23);
		getContentPane().add(cancelButton);
	}
}
