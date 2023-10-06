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

import bean.TheaterDetailBean;

public class TheaterDetailDAO {
	private static final String ROW_SIZE_SQL ="select * from theaterDetail where theaterId_fk in("
			+" select theaterId_fk from showing where showingId=?"
			+ " )order by theaterRow ASC";
	
	public static List<TheaterDetailBean> getRowSizes( int showingId){
		List<TheaterDetailBean> res = new ArrayList<TheaterDetailBean>();
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(ROW_SIZE_SQL);
			pstm.setInt(1,  showingId);
			ResultSet rs = pstm.executeQuery();
			res = new ArrayList<TheaterDetailBean>();
			while( rs.next()) {
				TheaterDetailBean tdb = new TheaterDetailBean();
				tdb.setTheaterDetailId(rs.getInt("theaterDetailId"));
				tdb.setTheaterId_fk(rs.getInt("theaterId_fk"));
				tdb.setTheaterRow(rs.getInt("theaterRow"));
				tdb.setTheaterRowSize(rs.getInt("theaterRowSize"));
				res.add(tdb);
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
