package cn.edu.imnu.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import cn.edu.imnu.User.User;
import cn.edu.imnu.Util.DBUtil;

public class UserDAO {
	public void register(User user) {
		String sql = "insert into tb_user(user_name,user_pwd,user_registdate)values(?,?,?)";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	public boolean isExistUser(User user) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_user where user_name=?";
		Connection conn =DBUtil.getConnection();
		PreparedStatement pstmt =null;
		ResultSet result=null;
		try {
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			result =pstmt.executeQuery();
			if(result.next())
			{
				return true;		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			DBUtil.closeJDBC(result, pstmt, conn);
		}
		return false;
	}
	public int isRightPassword(User user) {
		// TODO Auto-generated method stub
		int UserID=0;
		String sql = "select user_id from tb_user where user_name=? and user_pwd=?";
		Connection conn =DBUtil.getConnection();
		PreparedStatement pstmt =null;
		ResultSet result=null;
		try {
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			result =pstmt.executeQuery();
			if(result.next())
			{
				UserID=result.getInt("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			DBUtil.closeJDBC(result, pstmt, conn);
		}
		return UserID;
	}
}
