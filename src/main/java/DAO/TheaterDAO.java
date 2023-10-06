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
			if( rs.next()) {
				TheaterBean tmp = new TheaterBean();
				tmp.setTheaterId(rs.getInt("theaterId"));
				res.add(tmp);
			}
		}catch(NamingException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
