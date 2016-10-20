package hxy;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServiceForm {
    private JFrame frmHuang;
    private JTable goodsInfoTable;
    private JLabel lblInformationOfGoods;
    private JLabel lblNewLabel;
    private JPanel panel;
    private JPanel enInfoPanel;
    private JLabel enName;
    private JTextField tfEnName;
    private JLabel enLinkMan;
    private JTextField tfShortName;
    private JLabel enShortName;
    private JLabel enAdress;
    private JTextField tfAdress;
    private JLabel enPostCode;
    private JTextField tfPostCode;
    private JButton addGoodsButton;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem maintainMenuItem;
    private JMenu menu_1;
    private JMenuItem searchMenuItem;
    private JTextField tfPhone;
    private JTextField tfEmail;
    private JTextField tfLinkMan;
    private int recoder=0;
	private int counter=0;                        ////////////商品记录计数器
    
    private JLabel label;
    private JTextField tfOrderDate;
    private JLabel label_1;
    private JTextField tfSendDate;
    private JLabel label_2;
    private JTextField tfSendAddress;
    private JLabel label_3;
    private JTextField tfSpecial;
    private JLabel label_4;
    private JLabel label_5;
    private JLabel label_6;
    private JMenu menu_2;
    private JMenuItem menuItem;
	public ServiceForm(String uername) {
		initialize();
	}
	private void initialize() {
		frmHuang = new JFrame();  ////////////订货主窗口的设置
		frmHuang.setVisible(true);
		frmHuang.setTitle("Huang&650");
		frmHuang.setBounds(0, 0, 985, 674);
		frmHuang.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHuang.getContentPane().setLayout(null);
		
		//////////商品信息的显示
				////////////表头信息的设置
		Object head[]={"ID","商品名称", "包装方式", "计量单位", "商品产地", "保质期", "特征描述", "售价"};
		Object goods[][]=new Object[35][8];
		ConnectDatabase conn=new ConnectDatabase();
		//ConnectMySQL conn = new ConnectMySQL();
		String cmd="select id,name,packstyle,unite,productarea,keeptime,description,price from Infor_Goods;";
		try {
			ResultSet re=conn.state.executeQuery(cmd);
//			while(re.next()){
//				recoder++;
//			}
//		    //goods=new Object[40][8];
//			re.beforeFirst();
//			int count=0;
			if(re.isBeforeFirst()){
				while(re.next()){
					for(int i=0;i<7;i++){
						goods[counter][i]=re.getString(i+1);
					}
					goods[counter++][7]=re.getFloat(8);
				}
			}
			re.close();
			conn.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		goodsInfoTable = new JTable(goods,head);			
		JScrollPane jsp=new JScrollPane(goodsInfoTable);
	    frmHuang.getContentPane().add(jsp);
	    jsp.setBounds(10, 54, 531, 545);
		
		lblInformationOfGoods = new JLabel("Information Of Goods");   /////////////商品信息标签
		lblInformationOfGoods.setFont(new Font("宋体", Font.PLAIN, 16));
		lblInformationOfGoods.setHorizontalAlignment(SwingConstants.CENTER);
		frmHuang.getContentPane().add(lblInformationOfGoods);
		lblInformationOfGoods.setBounds(125, 25, 233, 30);
		
		
		lblNewLabel = new JLabel("\u5BA2\u6237\u9009\u8D2D");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(694, 15, 115, 15);
		frmHuang.getContentPane().add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmHuang.getContentPane().add(panel);
		panel.setBounds(562, 40, 382, 585);
		panel.setLayout(null);
		
		enInfoPanel = new JPanel();
		panel.add(enInfoPanel);
		enInfoPanel.setBounds(10, 10, 362, 208);
		enInfoPanel.setLayout(null);
		
		enName = new JLabel("\u4F01\u4E1A\u540D\u79F0");
		enName.setBounds(10, 10, 54, 15);
		enInfoPanel.add(enName);
		

		tfLinkMan = new JTextField();
		tfLinkMan.setBounds(74, 72, 96, 21);
		enInfoPanel.add(tfLinkMan);
		tfLinkMan.setColumns(10);
		
		tfEnName = new JTextField();
		tfEnName.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent arg0) {       ////////////////////企业名称文本框失去焦点监视器，即查询企业信息和联系人信息填入相应的控件中
				String enName=tfEnName.getText().trim();
				if(!"".equals(enName)){ ///////////////////查询企业信息
					ConnectDatabase conn=new ConnectDatabase();     /////////////////创建数据库链接对象准备连接数据库
					String cmd="select Enterprise.id,Enterprise.company_name,Enterprise.short_name,Enterprise.address,Enterprise.postcode,Linkman.name,Linkman.telephone,Linkman.phone,Linkman.email,Enterprise.address from Linkman,Enterprise where Enterprise.company_name=Linkman.company_name and Enterprise.company_name='"+enName+"'";
				    try {
						ResultSet re=conn.state.executeQuery(cmd);
						if(re.next()){
							re.beforeFirst();
							int counter=0;
							Object []linkman=null;
							String [][]linkManInfo=null;
							while(re.next()){        ///////////////处理结果集判断企业联系人的个数
								counter++;
							}
							re.beforeFirst();
//							if(counter==0){           //////////////////公司没有联系人信息
//								System.out.println("cuocucocuciocucocic");
//							}
							linkman=new Object[counter];
							linkManInfo=new String[counter][3];
							counter=0;
							while(re.next()){        //////////////将联系人存到数组中
								linkman[counter]=re.getString(6);
								linkManInfo[counter][0]=re.getString(6);   //////////联系人姓名
								linkManInfo[counter][1]=re.getString(8);   //////////电话
								linkManInfo[counter++][2]=re.getString(9);  //////////电子信箱
							}
							
							for(int k=0;k<linkManInfo.length;k++){
								System.out.println(linkManInfo[k][0]+"   "+linkManInfo[k][1]+"   "+linkManInfo[k][2]);
							}
							
							re.first();     //////////////添加企业信息到相应的控件
							{
								tfShortName.setText(re.getString(3));
								tfAdress.setText(re.getString(4));
								tfPostCode.setText(re.getString(5));  /////////////生成弹出式选择对话框中的选择的内容
								Object selectValve=JOptionPane.showInputDialog(null, "ChooseLinkMan","LinkMan",JOptionPane.INFORMATION_MESSAGE,null,linkman,linkman[0]);
								
								for(int i=0;i<linkManInfo.length;i++){
									if(selectValve.toString().equals(linkManInfo[i][0].trim())){   /////////////找到选中的联系人并将信息填入到相应的控件中
										tfLinkMan.setText(selectValve.toString());
										tfPhone.setText(linkManInfo[i][1]);
										tfEmail.setText(linkManInfo[i][2]);
										break;
									}
								}
							}
							Date today=new Date();
							tfOrderDate.setText((today.getYear()+1900)+"-"+(today.getMonth()+1)+"-"+today.getDate());
							tfSendDate.setText((today.getYear()+1900)+"-"+(today.getMonth()+1)+"-"+(today.getDate()+1));
							tfSendAddress.setText(re.getString(10));
							re.close();
							conn.conn.close();
						}
						else{
							JOptionPane.showMessageDialog(frmHuang, "该公司不存在！！！请检查名称\n如果新伙伴请先注册！！！");
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		tfEnName.setBounds(74, 7, 96, 21);
		enInfoPanel.add(tfEnName);
		tfEnName.setColumns(10);
		
		enLinkMan = new JLabel("\u4F01\u4E1A\u8054\u7CFB\u4EBA");
		enLinkMan.setBounds(10, 75, 66, 15);
		enInfoPanel.add(enLinkMan);
		
		tfShortName = new JTextField();
		tfShortName.setBounds(243, 7, 109, 21);
		enInfoPanel.add(tfShortName);
		tfShortName.setColumns(10);
		
		enShortName = new JLabel("\u4F01\u4E1A\u7B80\u79F0");
		enShortName.setBounds(180, 10, 54, 15);
		enInfoPanel.add(enShortName);
		
		enAdress = new JLabel("\u4F01\u4E1A\u5730\u5740");
		enAdress.setBounds(10, 41, 54, 15);
		enInfoPanel.add(enAdress);
		
		tfAdress = new JTextField();
		tfAdress.setBounds(74, 38, 96, 21);
		enInfoPanel.add(tfAdress);
		tfAdress.setColumns(10);
		
		enPostCode = new JLabel("\u90AE\u7F16");
		enPostCode.setBounds(190, 41, 54, 15);
		enInfoPanel.add(enPostCode);
		
		tfPostCode = new JTextField();
		tfPostCode.setBounds(243, 38, 109, 21);
		enInfoPanel.add(tfPostCode);
		tfPostCode.setColumns(10);
		
		JLabel laPhone = new JLabel("\u8054\u7CFB\u7535\u8BDD");
		laPhone.setBounds(180, 72, 54, 15);
		enInfoPanel.add(laPhone);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(74, 103, 145, 21);
		enInfoPanel.add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel laEmail = new JLabel("\u7535\u5B50\u4FE1\u7BB1");
		laEmail.setBounds(10, 106, 54, 15);
		enInfoPanel.add(laEmail);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(243, 69, 109, 21);
		enInfoPanel.add(tfPhone);
		tfPhone.setColumns(10);
		
		label = new JLabel("\u8BA2\u5355\u65E5\u671F");
		label.setBounds(10, 134, 54, 15);
		enInfoPanel.add(label);
		
		tfOrderDate = new JTextField();
		tfOrderDate.setBounds(74, 128, 96, 21);
		enInfoPanel.add(tfOrderDate);
		tfOrderDate.setColumns(10);
		
		label_1 = new JLabel("\u9001\u8D27\u65E5\u671F");
		label_1.setBounds(180, 134, 54, 15);
		enInfoPanel.add(label_1);
		
		tfSendDate = new JTextField();
		tfSendDate.setBounds(243, 131, 109, 21);
		enInfoPanel.add(tfSendDate);
		tfSendDate.setColumns(10);
		
		label_2 = new JLabel("\u9001\u8D27\u5730\u5740");
		label_2.setBounds(10, 164, 54, 15);
		enInfoPanel.add(label_2);
		
		tfSendAddress = new JTextField();
		tfSendAddress.setBounds(74, 159, 278, 21);
		enInfoPanel.add(tfSendAddress);
		tfSendAddress.setColumns(10);
		
		label_3 = new JLabel("\u7279\u6B8A\u8BF4\u660E");
		label_3.setBounds(10, 189, 54, 15);
		enInfoPanel.add(label_3);
		
		tfSpecial = new JTextField();
		tfSpecial.setBounds(74, 186, 278, 21);
		enInfoPanel.add(tfSpecial);
		tfSpecial.setColumns(10);
		
		label_4 = new JLabel("\u5E74");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(253, 109, 25, 15);
		enInfoPanel.add(label_4);
		
		label_5 = new JLabel("\u6708");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(276, 109, 25, 15);
		enInfoPanel.add(label_5);
		
		label_6 = new JLabel("\u65E5");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(298, 109, 25, 15);
		enInfoPanel.add(label_6);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBounds(10, 253, 362, 295);
		
		Object []headGoods={"ID","商品名称","计量单位","单价","数量"};
		Object [][]goodsData=new Object[20][5];
		JTable goodsTable=new JTable(goodsData,headGoods);
		
		JScrollPane scrollPane = new JScrollPane(goodsTable);
		scrollPane.setBounds(0, 0, 362, 285);
		panel_2.add(scrollPane);
		
		
		JLabel enOrderForm = new JLabel("\u8BA2\u5355\u8BE6\u60C5");
		enOrderForm.setHorizontalAlignment(SwingConstants.CENTER);
		enOrderForm.setBounds(10, 228, 54, 15);
		panel.add(enOrderForm);
		
		
		addGoodsButton = new JButton("\u6DFB\u52A0\u5546\u54C1");
		addGoodsButton.addActionListener(new ActionListener() {    ////////////////////订单商品添加按钮监视器
			public void actionPerformed(ActionEvent arg0) {
//				System.out.println(goodsInfoTable.getSelectedRow()+"");
//				System.out.println(goodsInfoTable.getValueAt(goodsInfoTable.getSelectedRow(), 0)+"  jjjj");
				if(goodsInfoTable.getSelectedRow()>=0&&goodsInfoTable.getSelectedRow()<counter){      /////////////选中的商品存在
					if(recoder<=goodsTable.getRowCount()){
						goodsTable.setValueAt(goodsInfoTable.getValueAt(goodsInfoTable.getSelectedRow(), 0), recoder, 0);   /////////////选中信息栏的商品添加到订单栏
						goodsTable.setValueAt(goodsInfoTable.getValueAt(goodsInfoTable.getSelectedRow(), 1), recoder, 1);
						goodsTable.setValueAt(goodsInfoTable.getValueAt(goodsInfoTable.getSelectedRow(), 3), recoder, 2);
						goodsTable.setValueAt(goodsInfoTable.getValueAt(goodsInfoTable.getSelectedRow(), 7), recoder++, 3);
					}
					else{           /////////////当记录数大于表格的行数时
						
					}
				}	
				else{
					JOptionPane.showMessageDialog(frmHuang, "请选择商品！！！");
				}
			}
		});
		addGoodsButton.setBounds(177, 228, 93, 23);
		panel.add(addGoodsButton);
		
		JButton submitButton = new JButton("\u63D0\u4EA4\u8BA2\u5355");
		submitButton.addActionListener(new ActionListener() {                     ///////////提交订单按钮监视器
			public void actionPerformed(ActionEvent arg0) {
				if(recoder>0){					
					Date orderTime=new Date();	
					@SuppressWarnings("deprecation")
					String orderID="650"+tfPhone.getText().trim()+orderTime.getYear()+(orderTime.getMonth()>10?orderTime.getMonth()+1:"0"+(orderTime.getMonth()+1))+(orderTime.getDate()>10?orderTime.getDate()+1:"0"+(orderTime.getDate()+1));  /////////生成订单号
					try {
						ConnectDatabase conn=new ConnectDatabase();
						String validCmd="select bookMan from orderInfo where orderID='"+orderID+"'";
						ResultSet re=conn.state.executeQuery(validCmd);       ////////////////////判断是否已经提交了一次订单
						if(re.next()){
							JOptionPane.showMessageDialog(frmHuang, "对不起，你今天已经提交了订单！！！\n订单号为："+orderID);
							conn.conn.close();
							re.close();
						}
						else{
							String cmd="insert into orderInfo values('";        //////////添加订单信息表，订单号自动生成（设置为时间加订货人手机号）					
						    cmd=cmd+orderID+"','"+tfOrderDate.getText().trim()+"','"+tfSendDate.getText().trim()+"','"+tfSendAddress.getText().trim()+"','"+tfSpecial.getText().trim()+"','"+tfLinkMan.getText().trim()+"')";
							conn.state.executeUpdate(cmd);
							System.out.println("Order has been created!!!");
							//conn.state.close();
							
							///////////////订单详情的生成
							try{
								//ConnectSQLServer conn=new ConnectSQLServer();
								cmd="insert into orderGoodsInfo values('"+orderID+"','";
								int count=0;
								while(goodsTable.getValueAt(count, 0)!=null){     //////////判断表格中的行中有数据
									cmd=cmd+goodsTable.getValueAt(count, 0)+"','"+goodsTable.getValueAt(count, 1)+"',"+Float.parseFloat(goodsTable.getValueAt(count,4).toString().trim())+","+Float.parseFloat(goodsTable.getValueAt(count, 3).toString().trim())+","+Float.parseFloat(goodsTable.getValueAt(count, 3).toString().trim())*Float.parseFloat(goodsTable.getValueAt(count, 4).toString().trim())+")";
								    conn.state.executeUpdate(cmd);
								    cmd="insert into orderGoodsInfo values('"+orderID+"','";
								    count++;
								}
								conn.state.close();
								new JOptionPane();
								JOptionPane.showMessageDialog(frmHuang, "订单提交成功！！！\n订单号为："+orderID);
							}catch (SQLException e) {
								e.printStackTrace();
							}
						}						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					
				}
				else{
					JOptionPane.showMessageDialog(frmHuang, "请选择商品后再提交订单！！！");
				}
			}
		});
		submitButton.setBounds(279, 552, 93, 23);
		panel.add(submitButton);
		
		JButton reduceGoodsButton = new JButton("\u5220\u9664\u5546\u54C1");
		reduceGoodsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {                  //////////////////选中商品删除按钮监视器
				if(goodsTable.getSelectedRow()>=0&&goodsTable.getSelectedRow()<recoder){
					if(!"".equals(goodsTable.getValueAt(goodsTable.getSelectedRow(), 0).toString().trim())){
						if(goodsTable.getSelectedRow()==(recoder-1)){    ////////////判断删除的信息是否为最后一条
							for(int i=0;i<goodsTable.getColumnCount();i++){
								goodsTable.setValueAt("", goodsTable.getSelectedRow(), i);
							}
							recoder--;
						}
						else{         //////////////不是最后一条
							for(int i=goodsTable.getSelectedRow();i<recoder-1;i++){        ///////////////数据向前移动一行
								for(int j=0;j<goodsTable.getColumnCount();j++){
									goodsTable.setValueAt(goodsTable.getValueAt(i+1, j), i, j);								
								}
							}
							for(int k=0;k<goodsTable.getColumnCount();k++){          /////////////最后一行置空
								goodsTable.setValueAt("", recoder-1, k);
							}
							recoder--;
						}
					}
				}	
				else{
					JOptionPane.showMessageDialog(frmHuang, "请选择需要删除的商品！！！");
				}
			}
		});
		reduceGoodsButton.setBounds(280, 228, 93, 23);
		panel.add(reduceGoodsButton);
		
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 115, 30);
		frmHuang.getContentPane().add(menuBar);
		
		menu = new JMenu("\u7EF4\u62A4");
		menuBar.add(menu);
		
		maintainMenuItem = new JMenuItem("\u7EF4\u62A4\u5546\u54C1"); ////////维护商品信息菜单被选中
		maintainMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				new MaintainForm();
			}
		});
		menu.add(maintainMenuItem);
		
		menu_1 = new JMenu("\u67E5\u8BE2");
		menuBar.add(menu_1);
		
		searchMenuItem = new JMenuItem("\u67E5\u8BE2\u5BA2\u6237\u4FE1\u606F");
		searchMenuItem.addMouseListener(new MouseAdapter() {                          /////////////////查询客户信息菜单监视器
			@Override
			public void mousePressed(MouseEvent arg0) {                     ////////////////调用一个窗体显示客户订单信息（customer）
				//System.out.println("hhhhhhhhhhhhhhhhhhhhhhhh");
				new CustomerOrderForm();
			} 
		});
		menu_1.add(searchMenuItem);
		
		menu_2 = new JMenu("\u5237\u65B0");
		menuBar.add(menu_2);
		
		menuItem = new JMenuItem("\u4FE1\u606F\u5237\u65B0");
		menuItem.addMouseListener(new MouseAdapter() {                  //////////////////////页面刷新菜单栏，将表中的数据清空，重新读取数据库提取商品信息
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println(counter);
				int count=0;
				System.out.println(goodsInfoTable.getValueAt(count, 0).toString().trim());
				while(count<counter){/////////////////判断表中有数据,将数据清空
					for(int i=0;i<goodsInfoTable.getColumnCount();i++){
						goodsInfoTable.setValueAt("", count, i);          //////////////将表格中的单元格置空
					}
					count++;
				}
				String cmd="select id,name,packstyle,unite,productarea,keeptime,description,price from Infor_Goods;";
				System.out.println(cmd);
				ConnectDatabase conn=new ConnectDatabase();
				try {
					ResultSet re=conn.state.executeQuery(cmd);
					counter=0;
					while(re.next()){
						for(int i=0;i<goodsInfoTable.getColumnCount();i++){
							goodsInfoTable.setValueAt(re.getString(i+1), counter, i);							
						}
						counter++;
					}
					re.close();
					conn.state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		menu_2.add(menuItem);
	}
}
