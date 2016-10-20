package hxy;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("unused")
public class OwnerForm extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTable goodsInfoTable=null;
	private JTable enterpriseInfoTable=null;
	private JTable staffInfoTable=null;
	private JTable linkManInfoTable=null;
	private int goodsRecoder=0;
	private int enterpriseRecoder=0;
	private int staffRecoder=0;
	private int linkManRecoder=0;
	public OwnerForm() {
		init();
		allInfoLoad();
	}
	
	void init(){
		setVisible(true);
		setTitle("Huang&650");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1030, 499);
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 261, 21);
		getContentPane().add(menuBar);
		
		JMenu menu = new JMenu("\u5546\u54C1\u4FE1\u606F");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u5546\u54C1\u4FE1\u606F\u7EF4\u62A4");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				new MaintainForm();
			}
		});
		menu.add(menuItem);
		
		JMenu menu_1 = new JMenu("\u5BA2\u6237\u4FE1\u606F");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("\u5BA2\u6237\u8BA2\u5355\u67E5\u8BE2");
		menuItem_1.addMouseListener(new MouseAdapter() {           //////////////订单查询菜单监视器
			@Override
			public void mousePressed(MouseEvent arg0) {
				new CustomerOrderForm();
			}
		});
		menu_1.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u5BA2\u6237\u4FE1\u606F\u7EF4\u62A4");
		menuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				new CustomerInfoForm();
			}
		});
		menu_1.add(menuItem_2);
		
		JMenu menu_2 = new JMenu("\u5458\u5DE5\u4FE1\u606F");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u5458\u5DE5\u4FE1\u606F\u7EF4\u62A4");
		menuItem_3.addMouseListener(new MouseAdapter() {     ////////员工信息维护菜单监视器
			@Override
			public void mousePressed(MouseEvent arg0) {
				new StaffInfoForm();
			}
		});
		menu_2.add(menuItem_3);
		
		JMenu menu_4 = new JMenu("\u91C7\u8D2D\u7EF4\u62A4");
		menuBar.add(menu_4);
		
		JMenuItem menuItem_7 = new JMenuItem("\u91C7\u8D2D\u5355\u7EF4\u62A4");
		menuItem_7.addMouseListener(new MouseAdapter() {    /////////采购单维护按钮监视器
			@Override
			public void mousePressed(MouseEvent arg0) {
				new BuyerForm();
			}
		});
		menu_4.add(menuItem_7);
		
		JMenu menu_3 = new JMenu("\u5237\u65B0");
		menuBar.add(menu_3);
		
		JMenuItem menuItem_4 = new JMenuItem("\u5546\u54C1\u4FE1\u606F\u5237\u65B0");
		menuItem_4.addMouseListener(new MouseAdapter() { ///////////商品信息刷新
			@Override
			public void mousePressed(MouseEvent arg0) {
				String cmd="select id,name,packstyle,unite,productarea,keeptime,description,price from Infor_Goods;";
				infoLoad(goodsInfoTable,goodsRecoder,8,cmd);
			}
		});
		menu_3.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("\u4F01\u4E1A\u4FE1\u606F\u5237\u65B0");
		menuItem_5.addMouseListener(new MouseAdapter() {      ////////////企业信息刷新
			@Override
			public void mousePressed(MouseEvent arg0) {
				String cmd="select id,company_name,short_name,owner,address,postcode,bank,account from Enterprise;";
				infoLoad(enterpriseInfoTable,enterpriseRecoder,8,cmd);
				cmd="select company_name,name,phone,email from Linkman;";
				infoLoad(linkManInfoTable,linkManRecoder,4,cmd);
			}
		});
		menu_3.add(menuItem_5);
		
		JMenuItem menuItem_6 = new JMenuItem("\u5458\u5DE5\u4FE1\u606F\u5237\u65B0");
		menuItem_6.addMouseListener(new MouseAdapter() {       //////////////员工信息刷新
			@Override
			public void mousePressed(MouseEvent arg0) {
				String cmd="select username,role from staff_Infor;";
				infoLoad(staffInfoTable,staffRecoder,2,cmd);
			}
		});
		menu_3.add(menuItem_6);
		
		JPanel goosInfoPanel = new JPanel();
		goosInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		goosInfoPanel.setBounds(10, 31, 490, 408);
		getContentPane().add(goosInfoPanel);
		goosInfoPanel.setLayout(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u4FE1\u606F");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(195, 0, 101, 25);
		goosInfoPanel.add(label);

		
		JPanel customerPanel = new JPanel();
		customerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		customerPanel.setBounds(523, 31, 481, 176);
		getContentPane().add(customerPanel);
		customerPanel.setLayout(null);
		
		JPanel staffPanel = new JPanel();
		staffPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		staffPanel.setBounds(824, 242, 180, 191);
		getContentPane().add(staffPanel);
		staffPanel.setLayout(null);
		
		
		JPanel linkManPanel = new JPanel();
		linkManPanel.setBounds(523, 242, 291, 191);
		getContentPane().add(linkManPanel);
		linkManPanel.setLayout(null);
		
		JLabel label_1 = new JLabel("\u5BA2\u6237\u4FE1\u606F");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(736, 10, 54, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u5458\u5DE5\u4FE1\u606F");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(883, 217, 54, 15);
		getContentPane().add(label_2);
		

		/////////////商品信息表显示
		Object headGoods[]={"ID","商品名称", "包装方式", "计量单位", "商品产地", "保质期", "特征描述", "售价"};
		Object goodsInfo[][]=new Object[30][8];
		goodsInfoTable=new JTable(goodsInfo,headGoods);
		JScrollPane goodsInfojsp=new JScrollPane(goodsInfoTable);
		goosInfoPanel.add(goodsInfojsp);
		goodsInfojsp.setBounds(0, 28, 490, 380);
		
	   ///////////企业信息表
		Object enHead[]={"ID","企业名称","简称","法人","地址","邮编","开户银行","账号"};
		Object enterpriseInfo[][]=new Object[20][8];
		enterpriseInfoTable=new JTable(enterpriseInfo, enHead);
		JScrollPane enterpriseInfojsp=new JScrollPane(enterpriseInfoTable);
		customerPanel.add(enterpriseInfojsp);
		enterpriseInfojsp.setBounds(0, 0, 481, 173);
		
		////////////联系人信息表
		Object linkManHead[]={"公司","姓名","电话","邮箱"};
		Object linkManInfo[][]=new Object[20][4];
		linkManInfoTable=new JTable(linkManInfo, linkManHead);
		JScrollPane linkManInfojsp=new JScrollPane(linkManInfoTable);
		linkManPanel.add(linkManInfojsp);
		linkManInfojsp.setBounds(0, 0, 291, 191);
		
		//////////////员工信息表显示
		Object staffHead[]={"用户名","角色"};
		Object staffData[][]=new Object[20][2];
		staffInfoTable=new JTable(staffData,staffHead);
		JScrollPane staffInfojsp=new JScrollPane(staffInfoTable);
		staffPanel.add(staffInfojsp);
		staffInfojsp.setBounds(0, 0, 180, 190);
		
		JLabel label_3 = new JLabel("\u8054\u7CFB\u4EBA");
		label_3.setBounds(644, 217, 54, 15);
		getContentPane().add(label_3);

	}
	
	void allInfoLoad(){
		String cmd="select id,name,packstyle,unite,productarea,keeptime,description,price from Infor_Goods;";
		infoLoad(goodsInfoTable,goodsRecoder,8,cmd);
		cmd="select id,company_name,short_name,owner,address,postcode,bank,account from Enterprise;";
		infoLoad(enterpriseInfoTable,enterpriseRecoder,8,cmd);
		cmd="select company_name,name,phone,email from Linkman;";
		infoLoad(linkManInfoTable,linkManRecoder,4,cmd);
		cmd="select username,role from staff_Infor;";
		infoLoad(staffInfoTable,staffRecoder,2,cmd);	
	}
	
	int infoLoad(JTable table,int recoder,int clumn,String cmd){
		try {
			for(int i=0;i<table.getRowCount();i++){       /////////////将表格清空
				for(int j=0;j<table.getColumnCount();j++){
					table.setValueAt(null, i, j);
				}
			}
			ConnectDatabase conn=new ConnectDatabase();
			ResultSet re=conn.state.executeQuery(cmd);        ///////////////查询商品信息；可以写成存储过程
			recoder=0;
			while(re.next()){
				for(int i=0;i<clumn;i++){
					table.setValueAt(re.getObject(i+1), recoder, i);
				}
				recoder++;
			}
			re.close();
			conn.state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return recoder;
	}
}
