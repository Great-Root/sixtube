package database.video;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.VideoDTO;


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
	public ArrayList<VideoDTO> getVideoList() {
		ArrayList<VideoDTO> list = new ArrayList<VideoDTO>();
		String sql = "SELECT * FROM video";
		try {
			Connection con = DriverManager.getConnection(url, id, pw);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				VideoDTO dto = new VideoDTO();
				dto.setVnum(rs.getInt("vnum"));
				dto.setTitle(rs.getString("title"));
				dto.setLikes(rs.getInt("likes"));
				dto.setVpath(rs.getString("vpath"));
				dto.setThpath(rs.getString("thpath"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public VideoDTO getVideo(int vnum) {
		return null;
	}

	@Override
	public boolean uploadVideo(VideoDTO dto) {
		String sql = "INSERT INTO video VALUES(?,?,?,?)";
		try {
			Connection con = DriverManager.getConnection(url, id, pw);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
//				VideoDTO dto = new VideoDTO();
//				dto.setVnum(rs.getInt("vnum"));
//				dto.setTitle(rs.getString("title"));
//				dto.setLikes(rs.getInt("likes"));
//				dto.setDisLikes(rs.getInt("dislikes"));
//				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteVideo(int vnum) {
		return false;
	}

	@Override
	public void addLikes(int vnum) {
		
	}

	@Override
	public void addDisLikes(int vnum) {
		
	}

	@Override
	public void addViews(int vnum) {
		
	}

//	@Override
//	public int saveMember() {
//		String sql = "insert into member(id,pw,name,gender,age) values(?,?,?,?,?)";
//		int result = 0;
//		try {
//			Connection con = DriverManager.getConnection(url, id, pw);
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, "아이디");
//			ps.setString(2, "비밀번호");
//			ps.setString(3, "이름");
//			ps.setInt(4, 1);
//			ps.setInt(5, 20);
//
//			result = ps.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
}
