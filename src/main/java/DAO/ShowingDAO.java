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

import bean.ShowingBean;

public class ShowingDAO {

	private static final String SEARCH_SHOWING_SQL=
			"select * from showing join theater on theaterId_fk= theaterId where movieId_fk=? and cinemaId_fk=? order by showingDatetime";
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
}



















