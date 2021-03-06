package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.newlecture.app.entity.Notice;

public class NoticeService {
	private String dburl = "jdbc:mysql://localhost:3306/connectdb?useSSL=false";
	private String dbUser = "connectuser";
	private String dbpasswd = "connect123!@#";
	private String driver="com.mysql.jdbc.Driver";
	
	public List<Notice> getList(int page,String field, String query) throws SQLException, ClassNotFoundException{

		int start=page*10-10;
		int end=10;
		
		String sql="SELECT * FROM (SELECT notice.*, @ROWNUM:=@ROWNUM+1 AS ROWNUM FROM notice, (SELECT @ROWNUM:=0) AS R) T WHERE "+field+" LIKE ? LIMIT ?,?;";
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1,query);
		ps.setInt(2, start);
		ps.setInt(3, end);;

		ResultSet rs=ps.executeQuery();
		
		List<Notice> list=new ArrayList<Notice>();
		
		while(rs.next()) {
			int id=rs.getInt("ID");
			String title=rs.getString("TITLE");
			String writerId=rs.getString("WRITER_ID");
			Date regDate=rs.getDate("REGDATE");
			String content=rs.getString("CONTENT");
			int hit=rs.getInt("hit");
			String files=rs.getString("FILES");
			
			Notice notice =new Notice(id,title,writerId,regDate,content,hit,files);
			
		list.add(notice);
		
		}
		
		
		
		rs.close();
		ps.close();
		conn.close();	
		
		
		return list;
	}
	//Scalar
	public int getCount() throws SQLException, ClassNotFoundException {
		int count=0;
		String sql="SELECT COUNT(id) count FROM notice";
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
		Statement ps=(Statement) conn.createStatement();
		ResultSet rs=ps.executeQuery(sql);
		
		if(rs.next())
			count=rs.getInt("COUNT");

		rs.close();
		ps.close();
		conn.close();	
		
		return count;
	}
	
	public int insert(Notice notice) throws SQLException, ClassNotFoundException {
		String title=notice.getTitle();
		String writerId=notice.getWriterId();
		String content=notice.getContent();
		String files=notice.getFiles();
		

		String sql="INSERT INTO notice("
				+ "title,"
				+ "writer_id,"
				+ "content,"
				+ "files"
				+ ")VALUES(?,?,?,?)";
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
		PreparedStatement st=conn.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		int result=st.executeUpdate();

		
		st.close();
		conn.close();
		
		return result;
	}
	
	public int  update(Notice notice) throws ClassNotFoundException, SQLException {
		String title=notice.getTitle();
		String content=notice.getContent();
		String files=notice.getFiles();
		int id= notice.getId();
		
		String sql="UPDATE notice "
				+ "SET "
				+ "TITLE=?,"
				+ " CONTENT=?,"
				+ " FILES=?"
				+ " WHERE ID=?";
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
		PreparedStatement st=conn.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4,id);

		int result=st.executeUpdate();
		
		
		st.close();
		conn.close();
		return result;
	}

	public int delete(int id) throws ClassNotFoundException, SQLException {

		String sql="DELETE FROM notice WHERE ID=?";
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
		PreparedStatement st=conn.prepareStatement(sql);
		st.setInt(1, id);

		int result=st.executeUpdate();

		System.out.println(result);

		
		
		st.close();
		conn.close();
		return result;
	}

	

}
