package hxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {
	Connection conn=null;
	Statement state=null;
	public ConnectDatabase(){
		XMLReader xr = new XMLReader();
		Database db = xr.getXML();
		getConnectDatabase(db);
		}

	public ConnectDatabase(Database db){
		getConnectDatabase(db);
	}
	
	private void getConnectDatabase(Database db){
		try{                                        ///////Á´½ÓÊý¾Ý¿â
			Class.forName(db.getDriver());
			}
		catch(ClassNotFoundException e){
			System.out.println(e);
			}
		
		try{
			conn=DriverManager.getConnection("jdbc:"+db.getType().toLowerCase()+"://"+db.getUri()+":"+db.getPort()+"/"+db.getName(), db.getUser(), db.getPassword());
			System.out.println("You has connected to Mysql!!!");
			state=conn.createStatement();
		}
		catch(SQLException e){
			System.out.print(e);
		}
	}
}
