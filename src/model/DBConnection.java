package model;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import app.Application;



@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DBConnection.class, name = "Repository")})



public class DBConnection {
	 private Connection conn=null;

	 @JsonProperty("username")
	 private String mUserName;
	 
	 @JsonProperty("password")
	 private String mPassword;
	 
	 @JsonProperty("server")
	 private String mServer;
	 
	 
	 
	 public DBConnection() {
		// TODO Auto-generated constructor stub
		openConnection();
		
	}
	 
	 private boolean openConnection(){
		 System.out.println("Opening connection...");
		
		
		 
		 String serverName = Application.getInstance().getRepository().getmServer();
				 //"147.91.175.155";
		 String databaseName = "ui-2016-tim201.9";
		 String userName = Application.getInstance().getRepository().getmUserName();
				 //"ui-2016-tim201.9";
		 String password = Application.getInstance().getRepository().getmPassword();
				 //"ui-2015-tim201.9.IoP994";
		 boolean result=false;
			try {
				Class.forName("net.sourceforge.jtds.jdbc.Driver");
				String url="jdbc:jtds:sqlserver://"+serverName+"/"+databaseName;

				conn=DriverManager.getConnection(url, userName, password);
				result=true;
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "UI Project", JOptionPane.ERROR_MESSAGE);
			}
			if(result)System.out.println("Connection opened successsfuly");
			else System.out.println("Connection open failed");
			return result;
			
	 }
	 
	 public Connection getConnection(){
		 return conn;
	 }
}
