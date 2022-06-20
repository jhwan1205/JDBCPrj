package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Progaram {	//DATA SELECT

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String dburl = "jdbc:mysql://localhost:3306/connectdb?useSSL=false";
		String dbUser = "connectuser";
		String dbpasswd = "connect123!@#";

		String driver="com.mysql.jdbc.Driver";

		String sql="SELECT * FROM NOTICE";
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery(sql);
		
		while(rs.next()) {
			int id=rs.getInt("ID");
			String title=rs.getString("TITLE");
			String writerId=rs.getString("WRITER_ID");
			Date regDate=rs.getDate("REGDATE");
			String content=rs.getString("CONTENT");
			int hit=rs.getInt("hit");
			
			System.out.printf("id: %d,name:%s, writerId:%s,regDate:%s,content:%s,hit:%d\n",id,title,writerId,regDate,content,hit);
		}
		
		rs.close();
		ps.close();
		conn.close();	
	}

}
