package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.DTO.EmployeeDTO;

public class EmployeeDAO {
	static String jdbcDriver;
	static String jdbcUrl;
	static Connection conn;
	String sql;
	PreparedStatement pstmt;//sql���� DB�� �����Ҷ� ����
	Integer result;
	
	static {
		jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	}
	
	
	public static void getConnect() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl, "gold", "oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void empInsert(EmployeeDTO dto) {
		sql = "insert into employees (EMPLOYEE_ID,EMP_USERID, EMP_PW, "
				+ " EMP_NAME, HIRE_DATE, JOB_ID, PH_NUMBER, OFFICE_NUMBER,"
				+ " EMAIL, EMP_ADDR)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmployeeId());
			pstmt.setString(2, dto.getEmpUserid());
			pstmt.setString(3, dto.getEmpPw());
			pstmt.setString(4, dto.getEmpName());
			pstmt.setString(5, dto.getHireDate());
			pstmt.setString(6, dto.getJobId());
			pstmt.setString(7, dto.getPhNumber());
			pstmt.setString(8, dto.getOfficeNumber());
			pstmt.setString(9, dto.getEmail());
			pstmt.setString(10, dto.getEmpAddr());
			result = pstmt.executeUpdate();
			System.out.println(result+"�� ���� ����Ǿ����ϴ�.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
