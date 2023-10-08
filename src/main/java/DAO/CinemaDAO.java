package DAO;
import bean.CinemaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CinemaDAO {
	public static final String GET_ALL_CINEMA_SQL = "select * from cinema";
	public static final String GET_CINEMA_ID_SQL = "select * from cinema where cinemaId in ("
			+" select cinemaId_fk from theater where theaterId in ("
			+" select theaterId_fk from showing where showingId =?))";
	public static List<CinemaBean> getAllCinema(){
		List<CinemaBean> res=null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_ALL_CINEMA_SQL);
			ResultSet rs = pstm.executeQuery();
			res = new ArrayList<CinemaBean>();
			while( rs.next()) {
				CinemaBean cb = new CinemaBean();
				cb.setCinemaId( rs.getInt("cinemaId"));
				cb.setCinemaName(rs.getString("cinemaName"));
				cb.setCinemaLocation(rs.getString("cinemaLocation"));
				res.add(cb);
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
	
	public static String getCinemaName(int cinemaId){
		String res=null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement("select * from cinema where cinemaId=?");
			pstm.setInt(1, cinemaId);
			ResultSet rs = pstm.executeQuery();
			res = new String();
			if( rs.next()) {
				res+= rs.getString( "cinemaName");
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
	
	public static CinemaBean getCinemaNameWithShowingId(int showingId){
		CinemaBean res=null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_CINEMA_ID_SQL);
			pstm.setInt(1, showingId);
			ResultSet rs = pstm.executeQuery();
			res = new CinemaBean();
			if( rs.next()) {
				res.setCinemaName (rs.getString( "cinemaName"));
				res.setCinemaId(rs.getInt("cinemaId"));
				res.setCinemaLocation(rs.getString("cinemaLocation"));
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
}
