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

import bean.MovieBean;

public class MovieDAO {
	private static final String GET_ALL_MOVIE_SQL = "select * from movie";
	public static List<MovieBean> getAllMovie(){
		List<MovieBean> res=null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_ALL_MOVIE_SQL);
			ResultSet rs = pstm.executeQuery();
			res = new ArrayList<MovieBean>();
			while( rs.next()) {
				MovieBean mb = new MovieBean();
				mb.setMovieId(rs.getInt("movieId"));
				mb.setMovieName(rs.getString("movieName"));
				mb.setMovieGrade(rs.getString("movieGrade"));
				mb.setMovieDuration(rs.getTime("movieDuration"));
				mb.setMovieDescription(rs.getString("movieDescription"));
				mb.setMovieImagePath(rs.getString("movieImagePath"));
				res.add(mb);
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
