package model.DAO;

import java.sql.SQLException;

import model.DTO.AuthInfo;

public class LoginDAO extends DataBaseInfo{
	public AuthInfo login(String userId) {
		AuthInfo authInfo = null;
		sql="select mem_id user_id, mem_pw user_pw, 1 grade from member where mem_id = ? "
				+ "union select emp_userid user_id, emp_pw user_pw, employee_id grade from employees where emp_userid=?";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				authInfo=new AuthInfo();
				authInfo.setGrade(rs.getString("grade"));
				authInfo.setUserId(rs.getString("user_id"));
				authInfo.setUserPw(rs.getString("user_pw"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return authInfo;
	}
	
}
