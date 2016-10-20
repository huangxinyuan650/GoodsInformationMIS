package hxy;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AddGoods extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfProductArea;
	private JTextField tfKeepTime;
	private JTextField tfDescription;
	private JTextField tfPrice;
	@SuppressWarnings("unused")
	private boolean flag=false;      /////////////判断商品是否添加成功
	public AddGoods() {
		setVisible(true);
		setTitle("AddGoodsForm");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("\u5546\u54C1ID");
		lblid.setFont(new Font("宋体", Font.PLAIN, 14));
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(10, 38, 54, 15);
		getContentPane().add(lblid);
		
		JLabel label = new JLabel("\u6DFB\u52A0\u5546\u54C1\u4FE1\u606F");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(154, 10, 118, 23);
		getContentPane().add(label);
		
		tfID = new JTextField();
		tfID.setBounds(85, 35, 91, 21);
		getContentPane().add(tfID);
		tfID.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u540D\u79F0");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(197, 38, 75, 15);
		getContentPane().add(label_1);
		
		tfName = new JTextField();
		tfName.setBounds(299, 35, 99, 21);
		getContentPane().add(tfName);
		tfName.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5305\u88C5\u65B9\u5F0F");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 75, 69, 15);
		getContentPane().add(label_2);
		
		JComboBox<String> packStyleComboBox = new JComboBox<String>();
		packStyleComboBox.addItem("散装");
		packStyleComboBox.addItem("盒装");
		//packStyleComboBox.addItem("散装");		
		packStyleComboBox.setBounds(85, 72, 91, 21);
		getContentPane().add(packStyleComboBox);
		
		JComboBox<String> measureUnitComboBox = new JComboBox<String>();
		measureUnitComboBox.addItem("Kg");
		measureUnitComboBox.addItem("盒");
		measureUnitComboBox.setBounds(299, 72, 69, 21);
		getContentPane().add(measureUnitComboBox);
		
		JLabel label_3 = new JLabel("\u8BA1\u91CF\u5355\u4F4D");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(197, 75, 79, 15);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u5546\u54C1\u4EA7\u5730");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setBounds(10, 119, 69, 15);
		getContentPane().add(label_4);
		
		tfProductArea = new JTextField();
		tfProductArea.setBounds(85, 116, 91, 21);
		getContentPane().add(tfProductArea);
		tfProductArea.setColumns(10);
		
		JLabel label_5 = new JLabel("\u5546\u54C1\u4FDD\u8D28\u671F");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));
		label_5.setBounds(197, 119, 75, 15);
		getContentPane().add(label_5);
		
		tfKeepTime = new JTextField();
		tfKeepTime.setBounds(299, 116, 91, 21);
		getContentPane().add(tfKeepTime);
		tfKeepTime.setColumns(10);
		
		JLabel label_6 = new JLabel("\u5929");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(394, 119, 19, 15);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("\u7279\u5F81\u63CF\u8FF0");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("宋体", Font.PLAIN, 14));
		label_7.setBounds(10, 147, 69, 15);
		getContentPane().add(label_7);
		
		tfDescription = new JTextField();
		tfDescription.setBounds(85, 144, 313, 21);
		getContentPane().add(tfDescription);
		tfDescription.setColumns(10);
		
		JLabel label_8 = new JLabel("\u5355\u4EF7");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("宋体", Font.PLAIN, 14));
		label_8.setBounds(10, 182, 54, 15);
		getContentPane().add(label_8);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(85, 179, 66, 21);
		getContentPane().add(tfPrice);
		tfPrice.setColumns(10);
		
		JLabel label_9 = new JLabel("\u5143");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(154, 182, 22, 15);
		getContentPane().add(label_9);
		
		JButton ADDButton = new JButton("\u6DFB\u52A0\u5546\u54C1");
		ADDButton.setFont(new Font("宋体", Font.PLAIN, 14));
		ADDButton.setBounds(231, 215, 91, 23);
		getContentPane().add(ADDButton);
		ADDButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {	
				if(!"".equals(tfID.getText().trim())){
					ConnectDatabase conn=new ConnectDatabase();
					String cmd="select id from Infor_Goods where id='"+tfID.getText().trim()+"'";
					try {
						ResultSet re=conn.state.executeQuery(cmd);
						if(!re.next()){
							re.close();
							cmd="insert into Infor_Goods values('"+tfID.getText().trim()+"','"+tfName.getText().trim()+"','"+packStyleComboBox.getSelectedItem().toString().trim()
									+"','"+measureUnitComboBox.getSelectedItem().toString().trim()+"','"+tfProductArea.getText().trim()+"',"
									+Float.parseFloat(tfKeepTime.getText().trim())+",'"+tfDescription.getText().trim()+"',"+Float.parseFloat(tfPrice.getText().trim())+")";
							System.out.println(cmd);
							try {
								conn.state.executeUpdate(cmd);
								conn.state.close();
								System.out.println("商品添加成功！！！");
								new JOptionPane().showMessageDialog(ADDButton, "商品添加成功！！！\n商品ID："+tfID.getText().trim());
								flag=true;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						else{
							re.close();
							conn.state.close();
							new JOptionPane().showMessageDialog(ADDButton, "该商品ID已经存在！！！\n请确认后再添加");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}					
				}
				else{
					new JOptionPane().showMessageDialog(ADDButton, "请输入商品信息！！！");
				}
			}
			});
		ADDButton.setActionCommand("OK");
		getRootPane().setDefaultButton(ADDButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("宋体", Font.PLAIN, 14));
		cancelButton.setBounds(344, 215, 69, 23);
		getContentPane().add(cancelButton);
		cancelButton.setActionCommand("Cancel");		
	}
}
