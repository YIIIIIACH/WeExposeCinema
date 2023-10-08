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

import bean.SeatBean;

public class BookingDAO {
	private static final String GET_BOOKED_SEAT_SQL="select * from seat where seatId in ("
			+ "	select seatId_fk from booking where bookingStatus='booked' and showingId_fk= ? )";
	private static final String CREATE_BOOKING_SQL = " insert into booking values(?,?,?,'booked')";
	public static List<SeatBean> getBookedSeats(int showingId){
		List<SeatBean> res=null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_BOOKED_SEAT_SQL);
			pstm.setInt(1, showingId);
			ResultSet rs = pstm.executeQuery();
			res = new ArrayList<SeatBean>();
			while( rs.next()) {
				SeatBean sb = new SeatBean();
				sb.setSeatId(rs.getInt("seatId"));
				sb.setTheaterId_fk(rs.getInt("theaterId_fk"));
				sb.setSeatRow(rs.getInt("seatRow"));
				sb.setSeatColumn(rs.getInt("seatColumn"));
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
	
	public static int createBooking( Integer productServiceId , Integer showingId, Integer seatId) {
		int res=-1;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(CREATE_BOOKING_SQL);
			pstm.setInt(1, productServiceId);
			pstm.setInt(2, showingId);
			pstm.setInt(3, seatId);
		}catch(NamingException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
