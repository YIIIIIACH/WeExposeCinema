package DAO;

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

import bean.CinemaBean;
import bean.TheaterBean;

public class TheaterDAO {
	private static final String SEARCH_THEATER= " select * from theater where cinemaId_fk=?";
	private static final String GET_THEATER = " select * from theater where theaterId in ( select theaterId_fk from showing where showingId=?)";
	private static final String GET_ALL = " select * from theater";
	public static List<TheaterBean> searchTheater( CinemaBean cinema){
		// will return all the theater in that cinema
		List<TheaterBean> res = new ArrayList<TheaterBean>(); 
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			// get the list of theaterId of the cinemaId first;
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(SEARCH_THEATER);
			pstm.setInt(1, cinema.getCinemaId());
			ResultSet rs = pstm.executeQuery();
			while( rs.next()) {
				TheaterBean tmp = new TheaterBean();
				tmp.setTheaterId(rs.getInt("theaterId"));
				tmp.setTheaterName(rs.getString("theaterName"));
				tmp.setCinemaId_fk(rs.getInt("cinemaId_fk"));
				res.add(tmp);
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
	
	public static TheaterBean getTheater( Integer showingId) {
		TheaterBean tb = null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			// get the list of theaterId of the cinemaId first;
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_THEATER);
			pstm.setInt(1, showingId);
			ResultSet rs = pstm.executeQuery();
			if( rs.next()) {
				tb = new TheaterBean();
				tb.setTheaterId(rs.getInt("theaterId"));
				tb.setTheaterName(rs.getString("theaterName"));
				tb.setCinemaId_fk(rs.getInt("cinemaId_fk"));
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
		return tb;
	}
	public static List<TheaterBean> getAllTheater(){
		List<TheaterBean> tbList = new ArrayList<TheaterBean>();
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			// get the list of theaterId of the cinemaId first;
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_ALL);
//			pstm.setInt(1, showingId);
			ResultSet rs = pstm.executeQuery();
			while( rs.next()) {
				TheaterBean tb = new TheaterBean();
				tb = new TheaterBean();
				tb.setTheaterId(rs.getInt("theaterId"));
				tb.setTheaterName(rs.getString("theaterName"));
				tb.setCinemaId_fk(rs.getInt("cinemaId_fk"));
				tbList.add(tb);
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
		return tbList;
		
	}
}
