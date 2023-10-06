package client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CinemaDAO;
import DAO.MovieDAO;
import bean.CinemaBean;
import bean.MovieBean;

/**
 * Servlet implementation class DisplayMovieDesp
 */
@WebServlet("/DisplayMovieDesp")
public class DisplayMovieDesp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DAO all movie and DAO all cinema
		List<CinemaBean> cinemas = CinemaDAO.getAllCinema();
		List<MovieBean> movies = MovieDAO.getAllMovie();
		request.setAttribute("cinemas", cinemas);
		request.setAttribute("movies", movies);
		request.getRequestDispatcher("/displayMovieDesp.jsp").forward(request, response);
	}
}
