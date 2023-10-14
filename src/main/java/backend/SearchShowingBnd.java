package backend;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MovieDAO;
import DAO.ShowingDAO;
import DAO.TheaterDAO;

/**
 * Servlet implementation class SearchShowingBnd
 */
@WebServlet("/SearchShowingBnd")
public class SearchShowingBnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchShowingBnd() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String theaterIdstr = request.getParameter("theaterId");
		theaterIdstr = (theaterIdstr==null)? "8": theaterIdstr;
		int theaterId = Integer.valueOf(theaterIdstr);
		
		String dateStr = request.getParameter("searchDate");
		dateStr = (dateStr==null)?"2023-10-13": dateStr;
		String timeStr = request.getParameter("searchTime");
		timeStr = (timeStr==null)? "12:00:00": timeStr;
//		System.out.println( this.getLocalDateTimeStr( request.getParameter("searchDate"),request.getParameter("searchTime")));
		List<String> movieNameList = new ArrayList<String>();
		List<LocalDateTime[]> intves = ShowingDAO.getShowingStEnd(theaterId,dateStr,timeStr,movieNameList);
		List<String[]> intvesStr = new ArrayList<String[]>();
		for( LocalDateTime[] intv : intves) {
			String [] tmp = new String[2];
			tmp[0] = intv[0].format( DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			tmp[1] = intv[1].format( DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			intvesStr.add(tmp);
		}
		request.setAttribute("selectTheaterId", theaterIdstr);
		request.setAttribute("selectMovieId", request.getParameter("movieId"));
		request.setAttribute("allTheaters", TheaterDAO.getAllTheater());
		request.setAttribute("allMovies", MovieDAO.getAllMovie());
		request.setAttribute("movieNameList", movieNameList);
		request.setAttribute("dateStr", dateStr);
		request.setAttribute( "timeStr"  , timeStr);
		request.setAttribute("stEnds", intvesStr);
		request.getRequestDispatcher("/AddShowingBnd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private String getLocalDateTimeStr(String dateStr, String timeStr) {
		String[] d = dateStr.split("-");
		String[] t = timeStr.split(":");
		return LocalDateTime.of(Integer.valueOf(d[0]),Integer.valueOf(d[1]), Integer.valueOf(d[2]),Integer.valueOf(t[0]),
				Integer.valueOf(t[1]),0,0).format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
	}

}
