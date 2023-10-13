package DAO;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ShowingBean;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class ShowingDAO {

	private static final String SEARCH_SHOWING_SQL=
			"select * from showing join theater on theaterId_fk= theaterId where movieId_fk=? and cinemaId_fk=? and showingDatetime > ? order by showingDatetime";
	private static final String GET_SHOWING_BY_BOOKING_SQL = 
			" select * from showing where showingId in ( select showingId_fk from booking where bookingId =?)";
	private static final String ADVANCE_SEARCH_SQL = 
			"select * from showing join theater on theaterId_fk= theaterId where movieId_fk=? and cinemaId_fk=? and showingDatetime>= ? and showingDatetime<= ? order by showingDatetime";
	private static final String GET_INTERVAL_BY_DATE_THEATER = " select CONVERT(char(30), showingDatetime, 120) [startTime] ,"
			+ " convert(char(30), (showingDatetime + cast(movieDuration as datetime)),120) [endTime]  from showing join movie on movieId=movieId_fk "
			+ "where theaterId_fk=? and showingDatetime between convert(datetime, ?) and convert(datetime, ?)";
	//select showingId, showingDatetime , cast(showingDatetime as datetime) + cast(movieDuration as datetime) from showing join movie on movieId_fk = movieId where theaterId_fk=? and ;
	/**
	 * select * from showing join movie on movieId_fk = movieId 
	 */
	//[startTime], cast(showingDatetime as time) + cast(movieDuration as time) [endTime]
	private static final String INSERT_SHOWING = "insert into showing values(?,?,?,?)";
	public static List<ShowingBean> searchShowing( int movieId, int cinemaId){
		List<ShowingBean> res=null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(SEARCH_SHOWING_SQL);
			pstm.setInt(1, movieId);
			pstm.setInt(2, cinemaId);
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date());
			pstm.setString(3, date);
			ResultSet rs = pstm.executeQuery();
			res = new ArrayList<ShowingBean>();
			while( rs.next()) {
				ShowingBean sb = new ShowingBean();
				sb.setShowingId(rs.getInt("showingId"));
				sb.setMovieId_fk(rs.getInt("movieId_fk"));
				sb.setTheaterId_fk(rs.getInt("theaterId_fk"));
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
				try {
					sb.setShowingDatetime(df.parse( rs.getString("showingDatetime")));
				} catch (ParseException e) {
					System.err.println("date parse error");
					e.printStackTrace();
				}
				sb.setShowingTypeId_fk(rs.getInt("showingTypeId_fk"));
				res.add(sb);
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
	public static ShowingBean getShowinByBooking( Integer bookingId) {
		ShowingBean res=null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_SHOWING_BY_BOOKING_SQL);
			pstm.setInt(1, bookingId);
			ResultSet rs = pstm.executeQuery();
			res = new ShowingBean();
			if( rs.next()) {
				res.setShowingId(rs.getInt("showingId"));
				res.setMovieId_fk(rs.getInt("movieId_fk"));
				DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
				try {
					res.setShowingDatetime(f.parse( rs.getString("showingDatetime")));
				} catch (ParseException e) {
					System.err.println("date parse error");
					e.printStackTrace();
				}
				res.setTheaterId_fk(rs.getInt("theaterId_fk"));
				res.setShowingTypeId_fk(rs.getInt("showingTypeId_fk"));
				
			}
			else {
				System.out.println("no result");
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
	public static List<ShowingBean> advanceSearchShowing(Integer movieId, Integer cinemaId, String selectDateStr ){
		List<ShowingBean> res = null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(ADVANCE_SEARCH_SQL);
			pstm.setInt(1, movieId);
			pstm.setInt(2, cinemaId);
			pstm.setString(3, selectDateStr+" 00:00:00.000");
			pstm.setString(4, selectDateStr+" 23:59:59.999");
			ResultSet rs = pstm.executeQuery();
			res = new ArrayList<ShowingBean>();
			while( rs.next()) {
				ShowingBean sb = new ShowingBean();
				sb.setShowingId(rs.getInt("showingId"));
				sb.setMovieId_fk(rs.getInt("movieId_fk"));
				DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
				try {
					sb.setShowingDatetime(f.parse( rs.getString("showingDatetime")));
				} catch (ParseException e) {
					System.err.println("date parse error");
					e.printStackTrace();
				}
				sb.setTheaterId_fk(rs.getInt("theaterId_fk"));
				sb.setShowingTypeId_fk(rs.getInt("showingTypeId_fk"));
				res.add(sb);
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
	
	public static Integer addShowing( Integer movieId, Integer theaterId, String dateStr, String timeStr,Integer showingTypeId) {
		int res=-1; // -1 means no Success;
		// 1. check no conflict
		// 1-1 get set of interval on specific showing start time and end time;
		List< String[] > timeIntervals= new ArrayList<String []>();;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_INTERVAL_BY_DATE_THEATER);
			pstm.setInt(1, 14);
			pstm.setString(2, dateStr+"T00:00:00");
			pstm.setString(3, dateStr+"T23:59:59");
			ResultSet rs = pstm.executeQuery();
			String [] tmp = null;
			while( rs.next()) {
				tmp = new String[2];
				tmp[0]= rs.getString("startTime");
				tmp[1]= rs.getString("endTime");
				timeIntervals.add(tmp);
			}
			// find interval
			
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




















