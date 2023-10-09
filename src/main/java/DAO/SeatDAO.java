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

public class SeatDAO {
	private static final String SHOWING_SEATS_SQL= 
			"select * from seat where theaterId_fk in("
			+ "	select theaterId_fk from showing where showingId=?"
			+ ")";
	private static final String SEATID_SQL = 
			"select seatId  from seat where theaterId_fk in ("
			+ "		select theaterId_fk from showing where showingId = ?"
			+ "	) and seatRow = ? and seatColumn = ?";
	private static final String SEAT_SQL = " select * from seat where seatId in ( select seatId_fk from booking where bookingId=?)";
	public static List<SeatBean> searchSeat(int showingId){
		List<SeatBean> seatList = new ArrayList<SeatBean>();
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(SHOWING_SEATS_SQL);
			pstm.setInt(1, showingId);
			ResultSet rs = pstm.executeQuery();
			seatList = new ArrayList<SeatBean>();
			while( rs.next()) {
				SeatBean sb = new SeatBean();
				sb.setSeatId(rs.getInt("seatId"));
				sb.setSeatRow(rs.getInt("seatRow"));
				sb.setSeatColumn(rs.getInt("seatColumn"));
				sb.setTheaterId_fk(rs.getInt("theaterId_fk"));
				seatList.add(sb);
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
		return seatList;
	}
	
	public static Integer getSeatId( int showingId , int seatRow, int seatColumn) {
		int res = -1; // -1 is invaild seatId
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(SEATID_SQL);
			pstm.setInt(1, showingId);
			pstm.setInt(2, seatRow);
			pstm.setInt(3, seatColumn);
			ResultSet rs = pstm.executeQuery();
			
			if( rs.next()) {
				res = rs.getInt("seatId");
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
	
	public static SeatBean getSeat( Integer bookingId) {
		SeatBean res = null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(SEAT_SQL);
			pstm.setInt(1, bookingId);
			ResultSet rs = pstm.executeQuery();
			
			if( rs.next()) {
				res = new SeatBean();
				res.setSeatId( rs.getInt("seatId"));
				res.setTheaterId_fk(rs.getInt("theaterId_fk"));
				res.setSeatRow(rs.getInt("seatRow"));
				res.setSeatColumn(rs.getInt("seatColumn"));
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
