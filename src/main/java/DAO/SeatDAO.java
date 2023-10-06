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
}
