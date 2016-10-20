package hxy;

import java.awt.EventQueue;
import java.io.File;


public class MainCom {

	
	public static void main(String[] args) {
//		Connection conn = null;
//		Statement state =null;
//		try{                                        ///////链接数据库
//			Class.forName("com.mysql.jdbc.Driver");
//			}
//		catch(ClassNotFoundException e){
//			System.out.println(e);
//			}
//		
//		try{
//			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql", "root", "huang650");
//			//System.out.println("You has connected to Mysql!!!");
//			state=conn.createStatement();
//			state.executeUpdate("create database huangxinyuan;");
//			state.close();
//			conn.close();
//			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/huangxinyuan", "root", "huang650");
//			System.out.println("You has connected to Mysql!!!");
//			state=conn.createStatement();
//			state.executeUpdate("create table huang(name char(20));");
//		}
//		catch(SQLException e){
//			System.out.print(e);
//		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					File fx = new File("config");
					File file = new File("config//config.xml");
					if(!fx.exists()){
						if(fx.mkdir()){
							if(!file.exists()){
								new DBForm();
							}else{
								System.out.print("配置文件创建失败！！！");
							}							
						}
						else{
							System.out.println("目录创建失败！！！");
						}
					}
					else{
						if(!file.exists()){
							new DBForm();
						}else{
							new LogIn();							
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
