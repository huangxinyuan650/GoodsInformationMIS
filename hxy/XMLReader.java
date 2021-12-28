package hxy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XMLReader {
	private DocumentBuilderFactory dbf = null;        //////////声明工厂方法变量
	private DocumentBuilder db = null;               ///////////////声明解析器对象的变量
	private Document  doc = null;                   ////////////////声明解析器解析之后的节点对象集变量
	private File fx = null;
	public XMLReader(){
	}
	
	public void setXML(Database database){
		fx = new File("config//config.xml");
		if(fx.exists()){
			fx.delete();
		}
		{
			try {
				fx.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		{
			try {
				fx.createNewFile();
				FileWriter fw = new FileWriter(fx);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("<?xml version="+(char)34+"1.0"+(char)34+" encoding="+(char)34+"UTF-8"+(char)34+"?>");
				bw.newLine(); 
				bw.write("<databases>");
				bw.newLine();
				bw.write("    <type>"+database.getType()+"</type>");
				bw.newLine();
				bw.write("    <driver>"+database.getDriver()+"</driver>");
				bw.newLine();
				bw.write("    <database>"+database.getName()+"</database>");
				bw.newLine();
				bw.write("    <uri>"+database.getUri()+"</uri>");
				bw.newLine();
				bw.write("    <port>"+database.getPort()+"</port>");
				bw.newLine();
				bw.write("    <user>"+database.getUser()+"</user>");
				bw.newLine();
				bw.write("    <password>"+database.getPassword()+"</password>");
				bw.newLine();
				bw.write("</databases>");
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Database getXML(){
		Database database = new Database();
		fx = new File("config//config.xml");
		if(!fx.exists()){
			System.out.println("Config document not exit!!!");
		}else{
			dbf = DocumentBuilderFactory.newInstance();
			try {
				db = dbf.newDocumentBuilder();
				try {
					doc = db.parse(fx);
					Node data = doc.getDocumentElement().getFirstChild().getNextSibling();
					database.setType(data.getFirstChild().getNodeValue());
					data = data.getNextSibling().getNextSibling();
					database.setDriver(data.getFirstChild().getNodeValue());
					data.getFirstChild().setNodeValue("huangxinyuan");
					data = data.getNextSibling().getNextSibling();
					database.setName(data.getFirstChild().getNodeValue());
					data = data.getNextSibling().getNextSibling();
					database.setUri(data.getFirstChild().getNodeValue());
					data = data.getNextSibling().getNextSibling();
					database.setPort(data.getFirstChild().getNodeValue());
					data = data.getNextSibling().getNextSibling();
					database.setUser(data.getFirstChild().getNodeValue());
					data = data.getNextSibling().getNextSibling();
					database.setPassword(data.getFirstChild().getNodeValue());
				} catch (SAXException | IOException e) {
					e.printStackTrace();
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}		
		return database;
	}
	
}
