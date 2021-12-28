package hxy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class InitializeDatabase {
	//初始化数据库表
	public InitializeDatabase(Database db){
		File inifile = new File("config//initialize.txt");
		//读取初始化文档初始化数据库
		if(!inifile.exists()){
			try {
				inifile.createNewFile();
				FileWriter fw = new FileWriter(inifile);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("create table staff_infor(username varchar(40) primary key,password varchar(20),role varchar(20));");
				bw.newLine();
				bw.write("insert into staff_infor values("+(char)34+"administrator"+(char)34+","+(char)34+"administrator"+(char)34+","+(char)34+"Administrator"+(char)34+");");
				bw.newLine();
				bw.write("create table infor_goods(id varchar(10) primary key,name varchar(20),packstyle varchar(20),unite varchar(5),productarea varchar(20),keeptime float,description varchar(50),price float);");
				bw.newLine();
				bw.write("insert into infor_goods values("+(char)34+"111"+(char)34+","+(char)34+"西瓜"+(char)34+","+(char)34+"散装"+(char)34+","+(char)34+"Kg"+(char)34+","+(char)34+"湖北"+(char)34+",10,"+(char)34+"湖北西瓜大又甜"+(char)34+",2);");
				bw.newLine();
				bw.write("create table enterprise(id varchar(20) primary key,company_name varchar(50),short_name varchar(20),owner varchar(40),address varchar(50),postcode int,bank varchar(20),account varchar(20));");
				bw.newLine();
				bw.write("insert into enterprise values("+(char)34+"10100"+(char)34+","+(char)34+"黄鑫源650IT"+(char)34+","+(char)34+"650IT"+(char)34+","+(char)34+"Blue sir"+(char)34+","+(char)34+"湖北省广水市应山名门雅居"+(char)34+",432700,"+(char)34+"中国银行"+(char)34+","+(char)34+"653424726464"+(char)34+");");
				bw.newLine();
				bw.write("create table linkman(company_name varchar(50) not null,name varchar(40) not null,telephone varchar(20),phone varchar(20),email varchar(40),primary key(company_name,name));");
				bw.newLine();
				bw.write("insert into linkman values("+(char)34+"黄鑫源650IT"+(char)34+","+(char)34+"黄鑫源"+(char)34+","+(char)34+"0722-6689281"+(char)34+","+(char)34+"15898102065"+(char)34+","+(char)34+"huangxinyuan650@outlook.com"+(char)34+");");
				bw.newLine();
				bw.write("create table orderinfo(orderID varchar(20) primary key,orderDate varchar(20),getDate varchar(20),address varchar(50),special varchar(50),bookMan varchar(40));");
				bw.newLine();
				bw.write("insert into orderinfo values("+(char)34+"201604343432432"+(char)34+","+(char)34+"2016-05-04"+(char)34+","+(char)34+"2016-05-05"+(char)34+","+(char)34+"辽宁省大连市大连海事大学"+(char)34+","+(char)34+"按时送到"+(char)34+","+(char)34+"黄童"+(char)34+");");
				bw.newLine();
				bw.write("create table ordergoodsinfo(orderID varchar(20) not null,goodsID varchar(10) not null,goodsName varchar(20),goodsCount float,price float,total float,primary key(orderID,goodsID));");
				bw.newLine();
				bw.write("insert into ordergoodsinfo values("+(char)34+"201604343432432"+(char)34+","+(char)34+"111"+(char)34+","+(char)34+"西瓜"+(char)34+",10,2,20);");
				bw.newLine();
				bw.write("create table buygoodsinfo(date varchar(20) not null,goodsID varchar(10) not null,goodsName varchar(20),count float,price float,total float,buyer varchar(40),primary key(date,goodsID));");
				bw.newLine();
				bw.write("insert into buygoodsinfo values("+(char)34+"2016-05-05"+(char)34+","+(char)34+"111"+(char)34+","+(char)34+"西瓜"+(char)34+",15,2,30,"+(char)34+"Blue sir"+(char)34+");");
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("初始化文件缺失，初始化失败!!!");
		}
		{
			try {
				FileReader fr = new FileReader(inifile);
				BufferedReader br = new BufferedReader(fr);
				ConnectDatabase conn = new ConnectDatabase(db);
				try {
					//用来存放从文档读进来的数据库命令
					String cmd = null;
					while((cmd=br.readLine())!=null){
						try {
							conn.state.executeUpdate(cmd);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					try {
						conn.state.close();
						conn.conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}
}
