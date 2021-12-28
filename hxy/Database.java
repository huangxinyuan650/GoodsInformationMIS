package hxy;

public class Database {
	private String dbType;
	private String dbDriver;
	private String dbName;
	private String dbUri;
	private String dbPort;
	private String dbUser;
	private String dbPassword;
	
	public Database(){
		
	}
	
	public void setType(String dbType){
		this.dbType = dbType;
	}
	public void setDriver(String dbDriver){
		this.dbDriver = dbDriver;
	}
	public void setName(String dbName){
		this.dbName = dbName;
	}
	public void setUri(String dbUri){
		this.dbUri = dbUri;
	}
	public void setPort(String dbPort){
		this.dbPort = dbPort;
	}
	public void setUser(String dbUser){
		this.dbUser = dbUser;
	}
	public void setPassword(String dbPassword){
		this.dbPassword = dbPassword;
	}
	
	public String getType(){
		return dbType;
	}
	public String getDriver(){
		return dbDriver;
	}
	public String getName(){
		return dbName;
	}
	public String getUri(){
		return dbUri;
	}
	public String getPort(){
		return dbPort;
	}
	public String getUser(){
		return dbUser;
	}
	public String getPassword(){
		return dbPassword;
	}

}
