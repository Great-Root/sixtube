package database.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.LoginUser;
import member.Controller;
import member.service.MemberServiceImpl;
import model.MemberDTO;
import modify.service.ModifyServiceImpl;

public class MemberDAOImpl implements MemberDAO {
	String url = "jdbc:oracle:thin:@3.34.231.231:1521:XE";
	String id = "sixtube";
	String pw = "1234";
	
	MemberServiceImpl ms;
	LoginUser lu;
	ModifyServiceImpl mos;
	
	public MemberDAOImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int saveMember(MemberDTO dto) {
		String sql = "insert into member(id,pw,name,gender,age) values(?,?,?,?,?)";
		int result = 0;
		try {
			Connection con = DriverManager.getConnection(url,id,pw);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getName());
			ps.setInt(4, dto.getGender());
			ps.setInt(5, dto.getAge());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String loginCheck(String userId) {
		String sql="select pw from member where id='"+userId+"'";
		try {
			Connection con=DriverManager.getConnection(url,id,pw);
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				return rs.getString("pw");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int modifyMember(MemberDTO dto) {
		String sql = "update member set pw=? where id=?";
		System.out.println(dto.getPw());
		System.out.println("아이디"+Controller.lu.getUserId());
		
		int result=0;
		
		try {
			Connection con = DriverManager.getConnection(url,id,pw);
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getPw());
			ps.setString(2, Controller.lu.getUserId());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteMember(MemberDTO dto) {
		String sql = "delete from member where id=?";	
				
		int result=0;
		
		try {
			Connection con = DriverManager.getConnection(url,id,pw);
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, Controller.lu.getUserId());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean checkMemberID(String userId) {
        
		String sql="select count(*) from member where id = '"+userId+"'";
        Connection con;
        boolean result = false;
		try {
			con = DriverManager.getConnection(url,id,pw);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt(1) == 1 ? true : false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
        }

    }
