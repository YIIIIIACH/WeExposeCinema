package test;
import bean.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookingDAO;
import DAO.SeatDAO;
import DAO.TheaterDAO;
import DAO.TheaterDetailDAO;
/**
 * Servlet implementation class TestDAO
 */
@WebServlet("/TestDAO")
public class TestDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
//		int cId = Integer.valueOf( request.getParameter("cinema_id"));
//		CinemaBean c = new CinemaBean();
//		c.setCinemaId(cId);
//		List<TheaterBean> theaterList=null;
//		PrintWriter out = response.getWriter();
//		theaterList = TheaterDAO.searchTheater(c);
//		for( TheaterBean t: theaterList) {
//			out.write(t.getTheaterName()+ t.getTheaterId() + t.getCinemaId_fk());
//		}
		List<SeatBean> sb = BookingDAO.getBookedSeats(6);
		
		response.getWriter().print(sb.get(0).getSeatId() );
		response.getWriter().print(" ");
		response.getWriter().print(sb.get(0).getSeatColumn());

	}

}
