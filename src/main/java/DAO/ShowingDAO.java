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
			"select * from showing join theater on theaterId_fk= theaterId where movieId_fk=? and cinemaId_fk=? order by showingDatetime";
	private static final String GET_SHOWING_BY_BOOKING_SQL = 
			" select * from showing where showingId in ( select showingId_fk from booking where bookingId =?)";
	public static List<ShowingBean> searchShowing( int movieId, int cinemaId){
		List<ShowingBean> res=null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(SEARCH_SHOWING_SQL);
			pstm.setInt(1, movieId);
			pstm.setInt(2, cinemaId);
			ResultSet rs = pstm.executeQuery();
			res = new ArrayList<ShowingBean>();
			while( rs.next()) {
				ShowingBean sb = new ShowingBean();
				sb.setShowingId(rs.getInt("showingId"));
				sb.setMovieId_fk(rs.getInt("movieId_fk"));
				sb.setTheaterId_fk(rs.getInt("theaterId_fk"));
				sb.setShowingDatetime(rs.getDate("showingDatetime"));
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
}




















