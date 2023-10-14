package DAO;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import bean.MovieBean;
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
	private static final String GET_INTERVAL_BY_DATE_THEATER = " select CONVERT(char(19), showingDatetime, 127) [startTime] ,"
			+ " convert(char(19), (showingDatetime + cast(movieDuration as datetime)),127) [endTime] ,movieId [movieId] from showing join movie on movieId=movieId_fk "
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
		List< LocalDateTime[] > timeIntervals= new ArrayList< LocalDateTime []>();;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_INTERVAL_BY_DATE_THEATER);
			pstm.setInt(1, 14);
			pstm.setString(2, dateStr+"T00:00:00");
			pstm.setString(3, dateStr+"T23:59:59");
			ResultSet rs = pstm.executeQuery();
			LocalDateTime [] tmp = null;
			while( rs.next()) {
				tmp = new LocalDateTime[2];
				System.out.println(rs.getString("startTime"));
				System.out.println(rs.getString("endTime"));
				tmp[0] = LocalDateTime.parse( rs.getString("startTime"), DateTimeFormatter.ISO_LOCAL_DATE_TIME) ;
				tmp[1]= LocalDateTime.parse( rs.getString("endTime"), DateTimeFormatter.ISO_LOCAL_DATE_TIME) ;
				timeIntervals.add(tmp);
			}
			// find interval
			Time dur = MovieDAO.getMovie(movieId).getMovieDuration();
			LocalDateTime added = LocalDateTime.parse(dateStr+"T"+timeStr , DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			LocalDateTime addedEnd = added.plusHours(dur.getHours());
			addedEnd.plusMinutes(dur.getMinutes());
			addedEnd.plusSeconds(dur.getSeconds());
			Boolean flag = true;
			for( int i =0; i<timeIntervals.size() && flag; i++) {
				if( added.isAfter( timeIntervals.get(i)[1]) ) {
//					res= insertShowing(movieId, theaterId, dateStr, timeStr, showingTypeId);
				}else  if (addedEnd.isBefore(timeIntervals.get(i)[0])){
//					res= insertShowing(movieId, theaterId, dateStr, timeStr, showingTypeId);
//					;
				}else {
					flag= false;
				}
			}
			res = (flag)? insertShowing(movieId, theaterId, dateStr, timeStr, showingTypeId): -1;
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
	//INSERT_SHOWING
	public static int insertShowing( Integer movieId, Integer theaterId, String dateStr, String timeStr,Integer showingTypeId) {
		int res= -1;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(INSERT_SHOWING);
			pstm.setInt(1, movieId);
			pstm.setInt(2, theaterId);
			pstm.setString(3, dateStr+" "+timeStr);
			pstm.setInt(4, showingTypeId);
			res = pstm.executeUpdate();
			
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
	
	
	public static List<LocalDateTime[]> getShowingStEnd(Integer theaterId, String dateStr, String timeStr , List<String> movieNameList) {
		int res=-1; // -1 means no Success;
		// 1. check no conflict
		// 1-1 get set of interval on specific showing start time and end time;
		List< LocalDateTime[] > timeIntervals= new ArrayList< LocalDateTime []>();;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_INTERVAL_BY_DATE_THEATER);
			pstm.setInt(1, theaterId);
			pstm.setString(2, dateStr+"T00:00:00");
			pstm.setString(3, dateStr+"T23:59:59");
			ResultSet rs = pstm.executeQuery();
			LocalDateTime [] tmp = null;
			List<MovieBean> mbList = MovieDAO.getAllMovie();
			while( rs.next()) {
				tmp = new LocalDateTime[2];
//				System.out.println(rs.getString("startTime") +"\t\t"+rs.getString("endTime"));
				tmp[0] = LocalDateTime.parse( rs.getString("startTime"), DateTimeFormatter.ISO_LOCAL_DATE_TIME) ;
				tmp[1]= LocalDateTime.parse( rs.getString("endTime"), DateTimeFormatter.ISO_LOCAL_DATE_TIME) ;
//				System.out.println(tmp[0] + "\t\t" + tmp[1]);
				// rs.getInt("movieId");
				InnerLoop: for( int i=0; i< mbList.size(); i++) {
					if( rs.getInt("movieId")== mbList.get(i).getMovieId()) {
						Time dur = MovieDAO.getMovie(mbList.get(i).getMovieId()).getMovieDuration();
						LocalDateTime added = LocalDateTime.parse(dateStr+"T"+timeStr , DateTimeFormatter.ISO_LOCAL_DATE_TIME);
						LocalDateTime addedEnd = added.plusHours(dur.getHours());
						addedEnd.plusMinutes(dur.getMinutes());
						addedEnd.plusSeconds(dur.getSeconds());
						movieNameList.add(mbList.get(i).getMovieName());
						timeIntervals.add(tmp);
						break InnerLoop;
					}
				}
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
		return timeIntervals;
	}
}




















