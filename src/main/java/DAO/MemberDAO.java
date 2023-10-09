package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.MemberBean;


public class MemberDAO {
	private static final String VERIFY_ACC_SQL = "select * from member where memberAccount=? and memberPassword=?";
	private static final String MEMBERID_SQL = " select memberId from member where memberAccount=? and memberPassword=?";
	private static final String MEMBER_SQL = " select * from member where memberAccount=? and memberPassword=?";
	private static final String CREATE_MEMBER_SQL="insert into member values( ? , ? , ? , ?,?)";
	public static Boolean verifyAccount(String account, String password){
		Boolean res= false;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(VERIFY_ACC_SQL);
			pstm.setString(1, account);
			pstm.setString(2, password);
			ResultSet rs = pstm.executeQuery();
			if( rs.next()) {
				res = true;
			}
			rs.close();
			pstm.close();
			conn.close();
			context.close();
		}catch(NamingException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static Integer getMemberId( String acc ,String pwd) {
		int res =- 1;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(MEMBERID_SQL);
			pstm.setString(1, acc);
			pstm.setString(2, pwd);
			ResultSet rs = pstm.executeQuery();
			if( rs.next()) {
				res = rs.getInt("memberId");
			}
			rs.close();
			pstm.close();
			conn.close();
			context.close();
		}catch(NamingException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static MemberBean getMemberBean( String acc, String pwd) {
		if( acc==null || pwd==null) {
			return null;
		}
		if( acc.length()<=0 || pwd.length()<= 0) {
			return null;
		}
		MemberBean res = null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(MEMBER_SQL);
			pstm.setString(1, acc);
			pstm.setString(2, pwd);
			ResultSet rs = pstm.executeQuery();
			if( rs.next()) {
				res = new MemberBean();
				res.setMemberId(rs.getInt("memberId"));
				res.setMemberAccount(rs.getString("memberAccount"));
				res.setMemberName(rs.getNString("memberName"));
				res.setMemberGrade(rs.getString("memberGrade"));
				res.setMemberAge(rs.getInt("memberAge"));
				res.setMemberPassword(rs.getString("memberPassword"));
			}
			rs.close();
			pstm.close();
			conn.close();
			context.close();
		}catch(NamingException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	public static Integer register( String acc, String pwd, String memberName, Integer age) {
		int res =- 1;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(CREATE_MEMBER_SQL);
			pstm.setString(1, acc);
			pstm.setString(2, memberName);
			pstm.setString(3, "普通");
			pstm.setInt(4, age);
			pstm.setString(5, pwd);
			res = pstm.executeUpdate();
			pstm.close();
			conn.close();
			context.close();
		}catch(NamingException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			return -2; // -2 means have duplicate account;
		}
		return res;
	}
}
