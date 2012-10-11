/**
 * 
 */
package edu.usc.cs587.examples.dbhandlers;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleConnection;
import edu.usc.cs587.examples.Constants;
import edu.usc.cs587.examples.objects.UserMessage;

/**
 * @author Ling
 *
 */
public class DatabaseHandler {
	private static final String HOST = "128.125.163.168";
	private static final String PORT = "1521";
	private static final String USERNAME = "team17";   
	private static final String PASSWORD = "palhunter";
	private static final String DBNAME = "csci585";
	private static final String URL = "jdbc:oracle:thin:@";

	protected OracleConnection connection;

	/**
	 * 
	 */
	public DatabaseHandler() {
		String url = URL + HOST + ":" + PORT + ":" + DBNAME;;
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			connection = (OracleConnection) DriverManager.getConnection(url,
					USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnected() {
		return this.connection == null;
	}
	
	public boolean insertRecordToDB(String username, String randomstring, long pubdate) {
		if (this.connection == null) {
			return false;
		}
		String sqlStmt = "INSERT INTO EXAMPLE VALUES (?,?,?)";
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(sqlStmt);
			
			pstmt.setString(1, username);
			pstmt.setString(2, randomstring);
			pstmt.setLong  (3, pubdate);
			pstmt.execute();
			pstmt.close();
			return true;
		}catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean insertPeopleToDB(int pid, String first_name, String last_name, long created_date) {
		if (this.connection == null) {
			return false;
		}
		String sqlStmt = "INSERT INTO PEOPLE VALUES (?,?,?,?)";
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(sqlStmt);
			
			pstmt.setInt(1, pid);
			pstmt.setString(2, first_name);
			pstmt.setString(3, last_name);
			pstmt.setLong(4, created_date);
			pstmt.execute();
			pstmt.close();
			return true;
		}catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public String queryPeople(String id){
		String table = "PEOPLE";
		String sqlStmt =  "SELECT * FROM "+table+" where pid ="+id;
		String rs = runQuery (sqlStmt, table);
		return rs;
	}
	
	public String convertPeopleToJSON(ResultSet rs){
		if(rs == null) return "[]";
		String result = "[";
		try {
			while (rs != null && rs.next()) {
				result +="{";
				result += "\"PID\":\""+rs.getInt("PID")+"\"";
				result += ",\"FIRST_NAME\":\""+rs.getString("FIRST_NAME")+"\"";
				result += ",\"LAST_NAME\":\""+rs.getString("LAST_NAME")+"\"";
				result += ",\"CREATED_TIME\":\""+rs.getLong("CREATED_TIME")+"\"";
				result +="}";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result+="]";
		return result;
	}
	
	public String runQuery (String sqlStmt, String table) {
		ResultSet rs = null;
		String result = "[]";
		if (this.connection == null) {
			return result;
		}
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(sqlStmt);
			rs = pstmt.executeQuery();
			if(table.compareTo("PEOPLE")==0)
				result = convertPeopleToJSON(rs);
			pstmt.close();
			return result;
		}catch (SQLException ex) {
			ex.printStackTrace();
			return result;
		}
	}
	
	
	public List<UserMessage> retrieveAllRecords () {
		if (this.connection == null) {
			return null;
		}
		String sqlStmt = "SELECT * FROM EXAMPLE";
		
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(sqlStmt);
			ResultSet rs = pstmt.executeQuery();
			List<UserMessage> ret = new ArrayList<UserMessage>();
			while (rs != null && rs.next()) {
				long pubDate = rs.getLong("PUBDATE");
				String userid = rs.getString("USERID");
				String message = rs.getString("MESSAGE");
				ret.add(new UserMessage(userid, message, pubDate));
			}
			pstmt.close();
			return ret;
		}catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	/*
	 * this is to test whether the java program can write data to database
	 */
	public static void main(String[] args) {
		DatabaseHandler handler = new DatabaseHandler();
		try {
			//insert an record to the database table 
//			handler.insertRecordToDB("Ling Hu", "Helloworld!!", System.currentTimeMillis());
			//retrieve all records from the database table 
			String first_name = "Luan";
			String last_name = "Nguyen";
			int id = 1;
			long created_date = Long.parseLong("1347774599443");;
			handler.insertPeopleToDB(id, first_name, last_name,created_date);
			handler.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
