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

	private static final String SEARCH_SHOWING_SQL="select * from showing s join theater t on s.theaterid_fk=t.theaterId where s.movieId_fk=? and t.cinemaId_fk=? ";
	
}




















