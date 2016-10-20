package hxy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class StaffInfoForm extends JFrame {
	private JTable staffInfoTable=null;
	private int staffRecoder=0;
	public StaffInfoForm() {
		setVisible(true);
		setTitle("员工信息维护");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 329);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5458\u5DE5\u4FE1\u606F");
		label.setBounds(83, 10, 54, 15);
		getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 30, 193, 238);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		Object staffHead[]={"用户名","角色"};
		Object staffInfo[][]=new Object[20][2];
		staffInfoTable=new JTable(staffInfo,staffHead);
		JScrollPane staffInfojsp=new JScrollPane(staffInfoTable);
		panel.add(staffInfojsp);
		staffInfojsp.setBounds(0, 0, 193, 238);
		{
			String cmd="select username,role from staff_Infor;";
			staffRecoder=infoLoad(staffInfoTable,staffRecoder,2,cmd);
		}
		
		JButton addButton = new JButton("\u6DFB\u52A0\u65B0\u5458\u5DE5");
		addButton.addActionListener(new ActionListener() {      ///////添加新员工按钮监视器
			public void actionPerformed(ActionEvent arg0) {
				new AddStaffDialog();
			}
		});
		addButton.setBounds(254, 57, 105, 23);
		getContentPane().add(addButton);
		
		JButton deButton = new JButton("\u5220\u9664\u5458\u5DE5");
		deButton.addActionListener(new ActionListener() {     /////删除员工信息
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if(staffInfoTable.getSelectedRow()>=0&&staffInfoTable.getSelectedRow()<staffRecoder){
					String cmd="delete from staff_Infor where username='"+staffInfoTable.getValueAt(staffInfoTable.getSelectedRow(), 0).toString().trim()+"';";
					ConnectDatabase conn=new ConnectDatabase();
					try {
						conn.state.executeUpdate(cmd);
						conn.state.close();
						new JOptionPane().showMessageDialog(deButton, "员工信息删除成功！！！");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println(cmd);
				}
				else{
					new JOptionPane().showMessageDialog(deButton, "请选择需要删除的员工！！！");
				}
			}
		});
		deButton.setBounds(254, 102, 105, 23);
		getContentPane().add(deButton);
		
		JButton upDateButton = new JButton("\u4FEE\u6539\u5458\u5DE5\u4FE1\u606F");
		upDateButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if(staffInfoTable.getSelectedRow()>=0&&staffInfoTable.getSelectedRow()<staffRecoder){
					new UpdateStaffInfoDialog(staffInfoTable.getValueAt(staffInfoTable.getSelectedRow(), 0).toString().trim());
				}
				else{
					new JOptionPane().showMessageDialog(upDateButton, "请选择需要修改的员工!!!");
				}
			}
		});
		upDateButton.setBounds(254, 154, 105, 23);
		getContentPane().add(upDateButton);
		
		JButton exit = new JButton("\u9000\u51FA\u7CFB\u7EDF");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		exit.setBounds(254, 246, 105, 23);
		getContentPane().add(exit);
		
		JButton freshButton = new JButton("\u5237\u65B0");
		freshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cmd="select username,role from staff_Infor;";
				staffRecoder=infoLoad(staffInfoTable,staffRecoder,2,cmd);
			}
		});
		freshButton.setBounds(254, 201, 105, 23);
		getContentPane().add(freshButton);
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
