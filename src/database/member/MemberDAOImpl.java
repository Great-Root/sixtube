package database.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.MemberDTO;

public class MemberDAOImpl implements MemberDAO {
	String url = "jdbc:oracle:thin:@3.34.231.231:1521:XE";
	String id = "sixtube";
	String pw = "1234";

	public MemberDAOImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveMember(MemberDTO dto) {
//		String sql = "insert into member(id,pw,name,gender,age,hobby) values(?,?,?,?,?,?)";
		int result = 0;
//		try {
//			Connection con = DriverManager.getConnection(url,id,pw);
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, dto.getId());
//			ps.setString(2, dto.getPw());
//			ps.setString(3, dto.getName());
//			ps.setInt(4, dto.getGender());
//			ps.setString(5, dto.getAge());
//			ps.setInt(6, dto.getHobby());
//			
//			result = ps.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return result;
	}
}
