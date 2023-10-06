package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchShowing
 */
@WebServlet("/SearchShowing")
public class SearchShowing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cinemaId= Integer.valueOf(request.getParameter("cinemaId"));
		int movieId = Integer.valueOf(request.getParameter("movieId"));
		// search for all theater of cinemaId
		// List<> theaters = TheaterDAO.searchTheater(cinemaId);
		
		// search for showing of all theaters
		/* for( theater : theaters){
		 * 	ShowingDAO.searchShowing( movieId , theater.getTheaterId() );
		*/
	}
}
