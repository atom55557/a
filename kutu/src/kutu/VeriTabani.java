package kutu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VeriTabani {
	static final String DB_URL="jdbc:mysql://localhost:3306/kutuphane";
	static final String user="root";
	static final String password="root";
	
	public Statement bagla() throws SQLException {
		Connection c=DriverManager.getConnection(DB_URL,user,password);
		Statement st=c.createStatement();
		return st;
	}
	
	public ResultSet rs(String sql) throws SQLException {
		Connection c=DriverManager.getConnection(DB_URL,user,password);
		Statement st=c.createStatement();
		ResultSet rs=st.executeQuery(sql);
		return rs;
	}


}
