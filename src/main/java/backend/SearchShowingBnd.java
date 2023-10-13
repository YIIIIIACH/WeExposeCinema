package backend;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ShowingDAO;

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
		Integer theaterId = (theaterIdstr==null)? -1: Integer.valueOf(theaterIdstr);
		String dateStr = request.getParameter("searchDate");
		String timeStr = request.getParameter("searchTime");
//		System.out.println( this.getLocalDateTimeStr( request.getParameter("searchDate"),request.getParameter("searchTime")));
		ShowingDAO.addShowing(4, theaterId, dateStr, timeStr, 1);
		response.getWriter().append("Served at: ");
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
