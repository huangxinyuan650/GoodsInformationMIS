package hxy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CustomerOrderForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField orderIDtf;
	private JTextField orderIDSHow;
	private JTextField linkManShow;
	private JTextField orderTimeShow;
	private JTextField sendTimeShow;
	private JTextField sendAddressShow;
	private String [][]bookMan=null;
	private JTextField tfSpecial;
	public CustomerOrderForm() {
		setVisible(true);
		getContentPane().setLayout(null);
		

		JPanel searchPanel = new JPanel();
		searchPanel.setToolTipText("");
		searchPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		searchPanel.setBounds(379, 35, 244, 186);
		getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		
		JLabel orderIDLa = new JLabel("\u8BA2\u5355\u53F7");
		orderIDLa.setBounds(10, 10, 54, 15);
		searchPanel.add(orderIDLa);
		orderIDLa.setHorizontalAlignment(SwingConstants.CENTER);
		
		orderIDtf = new JTextField();
		orderIDtf.setBounds(73, 7, 161, 21);
		searchPanel.add(orderIDtf);
		orderIDtf.setColumns(10);
		
		JLabel orderTimeLa = new JLabel("\u8BA2\u5355\u65E5\u671F");
		orderTimeLa.setHorizontalAlignment(SwingConstants.CENTER);
		orderTimeLa.setBounds(10, 46, 54, 15);
		searchPanel.add(orderTimeLa);
		
		JLabel linkmanLa = new JLabel("\u4E0B\u8BA2\u5355\u4EBA");
		linkmanLa.setHorizontalAlignment(SwingConstants.CENTER);
		linkmanLa.setBounds(10, 147, 54, 15);
		searchPanel.add(linkmanLa);
		
		JComboBox<Integer> yearComboBox = new JComboBox<Integer>();
		yearComboBox.addItem(2015);
		yearComboBox.addItem(2016);
		yearComboBox.addItem(2017);
		yearComboBox.setBounds(73, 43, 68, 21);
		searchPanel.add(yearComboBox);
		
		JLabel label_6 = new JLabel("\u5E74");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(151, 46, 19, 15);
		searchPanel.add(label_6);
		
		
		JPanel orderShowpanel = new JPanel();
		orderShowpanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		orderShowpanel.setBounds(10, 113, 339, 358);
		getContentPane().add(orderShowpanel);
		orderShowpanel.setLayout(null);
		
		Object head[]={"商品ID","商品名称","购买数量","单价","金额"};
		Object orderGoods[][]=new Object[25][5];
		JTable orderGoodsTable=new JTable(orderGoods, head);
		JScrollPane orderGoodsjsp=new JScrollPane(orderGoodsTable);
		orderShowpanel.add(orderGoodsjsp);
		orderGoodsjsp.setBounds(0, 0, 339, 358);
		
		JComboBox<Integer> monthComboBox = new JComboBox<Integer>();
		{
			for(int i=1;i<13;i++){
				monthComboBox.addItem(i);
			}
		}
		
		JComboBox<Integer> dayComboBox = new JComboBox<Integer>();
		monthComboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
		          /////////////////月份下拉列表框失去焦点，即选择天数
						dayComboBox.removeAllItems();
						if("1".equals(monthComboBox.getSelectedItem().toString())|"3".equals(monthComboBox.getSelectedItem().toString())
								|"5".equals(monthComboBox.getSelectedItem().toString())|"7".equals(monthComboBox.getSelectedItem().toString())
								|"8".equals(monthComboBox.getSelectedItem().toString())|"10".equals(monthComboBox.getSelectedItem().toString())
								|"12".equals(monthComboBox.getSelectedItem().toString())){
							for(int i=1;i<32;i++){
								dayComboBox.addItem(i);
							}					
						}
						else if("2".equals(monthComboBox.getSelectedItem().toString())){
							if(Integer.parseInt(yearComboBox.getSelectedItem().toString())%4==0){
								for(int i=1;i<30;i++){
									dayComboBox.addItem(i);
								}
							}else{
								for(int i=1;i<29;i++){
									dayComboBox.addItem(i);
								}
							}
						}
						else{
							for(int i=1;i<31;i++){
								dayComboBox.addItem(i);
							}
						}
					
			}
		});
		monthComboBox.setBounds(73, 74, 68, 21);
		searchPanel.add(monthComboBox);
		
		JLabel label_7 = new JLabel("\u6708");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(151, 77, 19, 15);
		searchPanel.add(label_7);
		
		JComboBox<String> linkManComboBox = new JComboBox<String>();
		linkManComboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				for(int i=0;i<bookMan.length;i++){
					if(linkManComboBox.getSelectedItem().toString().trim().equals(bookMan[i][5])){   ///////比对选择的联系人然后进行精确查找
						orderIDSHow.setText(bookMan[i][0]);
						linkManShow.setText(bookMan[i][5]);
						orderTimeShow.setText(bookMan[i][1]);
						sendTimeShow.setText(bookMan[i][2]);
						sendAddressShow.setText(bookMan[i][3]);
						tfSpecial.setText(bookMan[i][4]);
						try {
							for(int k=0;k<orderGoodsTable.getRowCount();k++){     /////////显示数据前将整个表给清空
								for(int j=0;j<orderGoodsTable.getColumnCount();j++){
									orderGoodsTable.setValueAt(null, k, j);
								}
							}
							ConnectDatabase conn=new ConnectDatabase();							
							String cmd="select goodsID,goodsName,goodsCount,price,total from orderGoodsInfo where orderID='"+bookMan[i][0].trim()+"';";//////在订单详情表中根据订单号查询订单信息
							System.out.println(cmd);
							ResultSet re=conn.state.executeQuery(cmd);
							int count=0;
							while(re.next()){           //////将查询到的数据显示在表格中
								for(int j=0;j<orderGoodsTable.getColumnCount();j++){
									orderGoodsTable.setValueAt(re.getString(j+1), count, j);
								}
								count++;
								System.out.println("/////////////////////");
							}
							re.close();
							conn.state.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					}
					else{
						
					}
				}
			}
		});
		linkManComboBox.setBounds(73, 147, 97, 21);
		searchPanel.add(linkManComboBox);
		

		dayComboBox.addFocusListener(new FocusAdapter() {      //////////////天数下拉列表框失去焦点监视器，查询选择日期当天的所有订单，提取出联系人
			@SuppressWarnings("static-access")
			@Override
			public void focusLost(FocusEvent arg0) {				
				String orderDate=yearComboBox.getSelectedItem().toString().trim()+"-"
			+monthComboBox.getSelectedItem().toString().trim()+"-"+dayComboBox.getSelectedItem().toString().trim(); /////订单时间
				ConnectDatabase conn=new ConnectDatabase();
				String cmd="select orderID,orderDate,getDate,address,special,bookMan from orderInfo where orderDate='"+orderDate+"'"; ///////////取出选择日期的订单号和联系人
				try {
					int count=0;              /////////////统计查询出来的记录的条数
					ResultSet re=conn.state.executeQuery(cmd);
					while(re.next()){
						count++;             /////////
						linkManComboBox.addItem(re.getString(6));
					}
					re.beforeFirst();
					bookMan=new String[count][6];    ///////id，订单日期，到货日期，送货地址，特殊说明，联系人
					count=0;
					while(re.next()){
						for(int i=0;i<6;i++){
							bookMan[count][i]=re.getString(i+1);
						}
						count++;
					}
					if(count==0){
						new JOptionPane().showMessageDialog(linkManComboBox, "当天没有任何订单！！！");
					}
					re.close();
					conn.state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		dayComboBox.setBounds(73, 105, 68, 21);
		searchPanel.add(dayComboBox);
		
		JLabel label_8 = new JLabel("\u65E5");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(151, 108, 19, 15);
		searchPanel.add(label_8);

		
		JLabel cuOrderLa = new JLabel("\u5BA2\u6237\u8BA2\u5355");
		cuOrderLa.setFont(new Font("宋体", Font.PLAIN, 16));
		cuOrderLa.setHorizontalAlignment(SwingConstants.CENTER);
		cuOrderLa.setBounds(121, 4, 101, 24);
		getContentPane().add(cuOrderLa);
		
		JLabel label = new JLabel("\u8BA2\u5355\u53F7");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(4, 35, 54, 15);
		getContentPane().add(label);
		
		orderIDSHow = new JTextField();
		orderIDSHow.setBounds(68, 32, 166, 21);
		getContentPane().add(orderIDSHow);
		orderIDSHow.setColumns(10);
		
		JLabel label_1 = new JLabel("\u4E0B\u8BA2\u5355\u4EBA");
		label_1.setBounds(10, 66, 54, 15);
		getContentPane().add(label_1);
		
		linkManShow = new JTextField();
		linkManShow.setBounds(68, 63, 88, 21);
		getContentPane().add(linkManShow);
		linkManShow.setColumns(10);
		
		JLabel label_2 = new JLabel("\u8BA2\u5355\u65E5\u671F");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(179, 66, 54, 15);
		getContentPane().add(label_2);
		
		orderTimeShow = new JTextField();
		orderTimeShow.setBounds(243, 63, 106, 21);
		getContentPane().add(orderTimeShow);
		orderTimeShow.setColumns(10);
		
		JLabel label_3 = new JLabel("\u8BA2\u5355\u67E5\u8BE2");
		label_3.setBounds(379, 10, 54, 15);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u5230\u8D27\u65E5\u671F");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(10, 91, 54, 15);
		getContentPane().add(label_4);
		
		sendTimeShow = new JTextField();
		sendTimeShow.setBounds(68, 88, 90, 21);
		getContentPane().add(sendTimeShow);
		sendTimeShow.setColumns(10);
		
		JLabel label_5 = new JLabel("\u9001\u8D27\u5730\u5740");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(179, 91, 54, 15);
		getContentPane().add(label_5);
		
		sendAddressShow = new JTextField();
		sendAddressShow.setBounds(243, 88, 106, 21);
		getContentPane().add(sendAddressShow);
		sendAddressShow.setColumns(10);
		
		JButton button = new JButton("\u4FEE\u6539\u8BA2\u5355");
		button.setBounds(389, 251, 101, 37);
		getContentPane().add(button);
		
		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchButton.setBounds(518, 248, 101, 42);
		getContentPane().add(searchButton);
		
		JLabel label_9 = new JLabel("\u7279\u6B8A\u8BF4\u660E");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(238, 35, 54, 15);
		getContentPane().add(label_9);
		
		tfSpecial = new JTextField();
		tfSpecial.setBounds(293, 32, 55, 21);
		getContentPane().add(tfSpecial);
		tfSpecial.setColumns(10);
		searchButton.addActionListener(new ActionListener() {    /////////////订单查询按钮监视器,根据订单号查找订单信息和订单详情
			public void actionPerformed(ActionEvent arg0) {
				String orderID=orderIDtf.getText().trim();          ///////////////获取订单ID
				if(!"".equals(orderID)){
					ConnectDatabase conn=new ConnectDatabase();
					try {
						String cmd="select orderDate,getDate,address,special,bookMan from orderInfo where orderID='"+orderID+"';";
						ResultSet re=conn.state.executeQuery(cmd);         /////////////////查询订单信息
						while(re.next()){
							orderIDSHow.setText(orderID);
							linkManShow.setText(re.getString(5).trim());
							orderTimeShow.setText(re.getString(1).trim());
							sendTimeShow.setText(re.getString(2).trim());
							sendAddressShow.setText(re.getString(3).trim());
						}
						re.close();
						cmd="select goodsID,goodsName,goodsCount,price,total from orderGoodsInfo where orderID='"+orderID+"';";
						re=conn.state.executeQuery(cmd);
						int count=0;
						for(int i=0;i<orderGoodsTable.getRowCount();i++){     /////////显示数据前将整个表给清空
							for(int j=0;j<orderGoodsTable.getColumnCount();j++){
								orderGoodsTable.setValueAt(null, i, j);
							}
						}
						while(re.next()){        ////////将查询到的信息显示在表格上
							for(int i=0;i<orderGoodsTable.getColumnCount();i++){
								orderGoodsTable.setValueAt(re.getString(i+1), count, i);								
							}
							count++;
						}
						re.close();
						conn.state.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		setTitle("CustomerOrder");
		setBounds(100, 100, 652, 520);
	}
}
