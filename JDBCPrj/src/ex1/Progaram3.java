package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Progaram3 {	//DATA INSERT

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String title="TEST3";
		String content="hahaha3";
		String files="";
		int id= 256;
		
		String dburl = "jdbc:mysql://localhost:3306/connectdb?useSSL=false";
		String dbUser = "connectuser";
		String dbpasswd = "connect123!@#";

		String sql="UPDATE notice "
				+ "SET "
				+ "TITLE=?,"
				+ " CONTENT=?,"
				+ " FILES=?"
				+ " WHERE ID=?";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
		PreparedStatement st=conn.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4,id);

		int result=st.executeUpdate();

		System.out.println(result);

		
		
		
		
		st.close();
		conn.close();	}

}
