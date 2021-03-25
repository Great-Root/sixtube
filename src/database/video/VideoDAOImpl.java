package database.video;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.MemberDTO;

public class VideoDAOImpl implements VideoDAO {
	String url = "jdbc:oracle:thin:@3.34.231.231:1521:XE";
	String id = "sixtube";
	String pw = "1234";

	public VideoDAOImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveMember() {
		String sql = "insert into member(id,pw,name,gender,age) values(?,?,?,?,?)";
		int result = 0;
		try {
			Connection con = DriverManager.getConnection(url, id, pw);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "아이디");
			ps.setString(2, "비밀번호");
			ps.setString(3, "이름");
			ps.setInt(4, 1);
			ps.setInt(5, 20);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
