package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Progaram2 {	//DATA INSERT

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String title="TEST2";
		String writerId="newlc";
		String content="hahaha";
		String files="";
		
		String dburl = "jdbc:mysql://localhost:3306/connectdb?useSSL=false";
		String dbUser = "connectuser";
		String dbpasswd = "connect123!@#";

		String sql="INSERT INTO notice("
				+ "title,"
				+ "writer_id,"
				+ "content,"
				+ "files"
				+ ")VALUES(?,?,?,?)";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
		PreparedStatement st=conn.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		int result=st.executeUpdate();

		System.out.println(result);

		
		
		
		
		st.close();
		conn.close();	}

}
