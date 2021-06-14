package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import model.DTO.EmployeeDTO;
import model.DTO.MemberDTO;

public class MemberDAO {
	final String COLUMNS = " MEM_ID, MEM_PW, POST_NUMBER, MEM_ADDRESS, DETAIL_ADD, MEM_NAME, MEM_PHONE, MEM_BIRTH, MEM_GENDER, MEM_ACCOUNT, MEM_EMAIL, MEM_EMAIL_CK ";
	static String jdbcDriver;
	static String jdbcUrl;
	static Connection conn;
	String sql;
	PreparedStatement pstmt;
	ResultSet rs;
	
	static {
		jdbcDriver="oracle.jdbc.driver.OracleDriver";
		jdbcUrl="jdbc:oracle:thin:@localhost:1521:xe";
	}
	
	public static void getConnect() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl, "web", "oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List <MemberDTO> getMemList(){
		List <MemberDTO> list = new ArrayList<MemberDTO>();
		sql="select "+COLUMNS+" from member";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto= new MemberDTO();
				dto.setMemId(rs.getString("EMP_ID"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	public void memInsert(MemberDTO dto) {
		sql ="insert into member ("+COLUMNS+") "+"values(?,?,?,?,?,?,?,?,?,?,?,?)";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMemId());
			pstmt.setString(2, dto.getMemPw());
			pstmt.setString(3, dto.getPostNumber());
			pstmt.setString(4, dto.getMemAddress());
			pstmt.setString(5, dto.getDetailAdd());
			pstmt.setString(6, dto.getMemName());
			pstmt.setString(7, dto.getMemPhone());
			long birth = dto.getMemBirth().getTime();
			pstmt.setDate(8, new Date(birth));
			pstmt.setString(9, dto.getMemGender());
			pstmt.setString(10, dto.getMemAccount());
			pstmt.setString(11, dto.getMemEmail());
			pstmt.setString(12, dto.getMemEmailCk());
			int i =pstmt.executeUpdate();
			System.out.println(i+"개 저장되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 }
