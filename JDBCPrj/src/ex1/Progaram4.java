package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Progaram4 {	//DATA DELETE

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int id= 3;
		
		String dburl = "jdbc:mysql://localhost:3306/connectdb?useSSL=false";
		String dbUser = "connectuser";
		String dbpasswd = "connect123!@#";

		String sql="DELETE FROM notice WHERE ID=?";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
		PreparedStatement st=conn.prepareStatement(sql);
		st.setInt(1, id);

		int result=st.executeUpdate();

		System.out.println(result);

		
		
		
		
		st.close();
		conn.close();	}

}
