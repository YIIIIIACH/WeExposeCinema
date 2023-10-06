package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CinemaBean;
import bean.MovieBean;
import bean.ShowingBean;
import bean.TheaterBean;

public class ShowingDAO {
	private static final String SEARCH_SHOWING_SQL  = "select * from showing where movieId_fk=? ";
	public static List<ShowingBean> searchShowing(MovieBean movie, CinemaBean cinema ){
		List<ShowingBean> res=null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			// get the list of theaterId of the cinemaId first;
			List<TheaterBean> theaters  = TheaterDAO.searchTheater(cinema);
			Connection conn = ds.getConnection();
			String tmp = new String(SEARCH_SHOWING_SQL);
			tmp += "and ( 1=1 ";
			for( TheaterBean t: theaters) {
				tmp+= " or theaterId = "+ String.valueOf( t.getTheaterId());
			}
			tmp+= ")";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery( tmp);
			res = new ArrayList<ShowingBean>();
			while (rs.next()) {
				ShowingBean s = new ShowingBean();
				s.setShowingId( rs.getInt("showingId"));
				s.setMovieId_fk( rs.getInt("movieId_fk"));
				s.setTheaterId_fk(rs.getInt("theaterId_fk"));
				s.setShowingDatetime( rs.getDate("showingDatetime"));
				s.setShowingTypeId_fk(rs.getInt("showingTypeId_fk"));
				res.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(NamingException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}




















