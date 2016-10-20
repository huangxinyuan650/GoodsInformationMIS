package hxy;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateGoods extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfProductArea;
	private JTextField tfKeepTime;
	private JTextField tfDescription;
	private JTextField tfPrice;
	public UpdateGoods(String id) {
		System.out.println(id);
		setVisible(true);
		setTitle("UpdateGoods");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JButton okButton = new JButton("\u66F4\u65B0\u5546\u54C1");
		okButton.setFont(new Font("宋体", Font.PLAIN, 14));
		okButton.setBounds(206, 228, 99, 23);
		getContentPane().add(okButton);
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("宋体", Font.PLAIN, 14));
		cancelButton.setBounds(330, 228, 81, 23);
		getContentPane().add(cancelButton);
		cancelButton.setActionCommand("Cancel");
		
		JLabel label = new JLabel("\u5546\u54C1\u4FE1\u606F");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(182, 10, 86, 23);
		getContentPane().add(label);
		
		JLabel lblid = new JLabel("\u5546\u54C1ID");
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setFont(new Font("宋体", Font.PLAIN, 14));
		lblid.setBounds(10, 43, 54, 15);
		getContentPane().add(lblid);
		
		tfID = new JTextField();
		tfID.setBounds(93, 40, 86, 21);
		getContentPane().add(tfID);
		tfID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u5546\u54C1\u540D\u79F0");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(214, 43, 66, 15);
		getContentPane().add(lblNewLabel);
		
		tfName = new JTextField();
		tfName.setBounds(290, 40, 121, 21);
		getContentPane().add(tfName);
		tfName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5305\u88C5\u65B9\u5F0F");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 81, 66, 15);
		getContentPane().add(label_1);
		
		tfProductArea = new JTextField();
		tfProductArea.setBounds(93, 119, 86, 21);
		getContentPane().add(tfProductArea);
		tfProductArea.setColumns(10);
		
		JComboBox<String> packStyleComboBox = new JComboBox<String>();
		packStyleComboBox.addItem("散装");
		packStyleComboBox.addItem("盒装");
		packStyleComboBox.setBounds(93, 78, 86, 21);
		getContentPane().add(packStyleComboBox);
		
		JLabel label_2 = new JLabel("\u8BA1\u91CF\u5355\u4F4D");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(214, 81, 66, 15);
		getContentPane().add(label_2);
		
		tfKeepTime = new JTextField();
		tfKeepTime.setBounds(290, 119, 66, 21);
		getContentPane().add(tfKeepTime);
		tfKeepTime.setColumns(10);
		
		JComboBox<String> measureUnitComboBox = new JComboBox<String>();
		measureUnitComboBox.addItem("Kg");
		measureUnitComboBox.addItem("盒");
		measureUnitComboBox.setBounds(290, 78, 66, 21);
		getContentPane().add(measureUnitComboBox);
		
		JLabel label_3 = new JLabel("\u5546\u54C1\u4EA7\u5730");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(10, 125, 66, 15);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u4FDD\u8D28\u671F");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setBounds(214, 122, 54, 15);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u5929");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(366, 122, 20, 15);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("\u7279\u5F81");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("宋体", Font.PLAIN, 14));
		label_6.setBounds(10, 162, 54, 15);
		getContentPane().add(label_6);
		
		tfDescription = new JTextField();
		tfDescription.setBounds(93, 159, 318, 21);
		getContentPane().add(tfDescription);
		tfDescription.setColumns(10);
		
		JLabel label_7 = new JLabel("\u5355\u4EF7");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("宋体", Font.PLAIN, 14));
		label_7.setBounds(10, 197, 54, 15);
		getContentPane().add(label_7);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(93, 194, 66, 21);
		getContentPane().add(tfPrice);
		tfPrice.setColumns(10);

		
		
		/////////////////将选中商品的信息填入到对应的控件中
		{
			ConnectDatabase conn=new ConnectDatabase();
			String cmd="select id,name,packstyle,unite,productarea,keeptime,description,price from Infor_Goods where id='"+id+"'";
			System.out.println(cmd);
			try {
				ResultSet re=conn.state.executeQuery(cmd);
				while(re.next()){
					tfID.setText(re.getString(1));
					tfName.setText(re.getString(2));
					if(re.getString(3).trim().equals("散装".trim())){
						packStyleComboBox.setSelectedItem(0);
					}
					else{
						packStyleComboBox.setSelectedItem(1);
					}
					if(re.getString(4).trim().equals("Kg".trim())){
						measureUnitComboBox.setSelectedItem(0);
					}
					else{
						measureUnitComboBox.setSelectedItem(1);
					}
					tfProductArea.setText(re.getString(5));
					tfKeepTime.setText(re.getString(6));
					tfDescription.setText(re.getString(7));
					tfPrice.setText(re.getString(8));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		okButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				ConnectDatabase conn=new ConnectDatabase();
				String cmd="update Infor_Goods set name='"+tfName.getText().trim()+"',packStyle='"
				+packStyleComboBox.getSelectedItem().toString().trim()+"',unite='"+measureUnitComboBox.getSelectedItem().toString().trim()
				+"',productArea='"+tfProductArea.getText()+"',keepTime="+Float.parseFloat(tfKeepTime.getText().trim())+",description='"
				+tfDescription.getText().trim()+"',price="+Float.parseFloat(tfPrice.getText().trim())+" where id='"+id+"'";
				System.out.println(cmd);
				try {
					conn.state.executeUpdate(cmd);
					new JOptionPane().showMessageDialog(okButton, "商品修改成功！！！");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
