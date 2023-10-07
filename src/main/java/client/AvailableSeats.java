package client;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookingDAO;
import DAO.TheaterDetailDAO;
import bean.SeatBean;
import bean.TheaterDetailBean;

@WebServlet("/AvailableSeats")
public class AvailableSeats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int showingId = Integer.valueOf(request.getParameter("showingId"));
		List<TheaterDetailBean> details = TheaterDetailDAO.getRowSizes(showingId);
		List<SeatBean> seats = BookingDAO.getBookedSeats(showingId);
		String tmp= new String();
		String bookedStr= new String();
		Iterator<TheaterDetailBean> it =  details.iterator();
		while( it.hasNext() ) {
			TheaterDetailBean d = it.next();
			tmp+= (char)(d.getTheaterRow()+'A') + ":" + String.valueOf(d.getTheaterRowSize());
			if( it.hasNext())
				tmp+= ",";
		}
		Iterator<SeatBean> sit = seats.iterator();
		while(sit.hasNext()) {
			SeatBean st = sit.next();
			bookedStr += "["+ String.valueOf(st.getSeatRow())+"," + String.valueOf(st.getSeatColumn()) + "]";
			if( sit.hasNext()) {
				bookedStr+= ",";
			}
		}

		request.setAttribute("seatsString", tmp);
		request.setAttribute("bookedSeats", bookedStr);
		request.getRequestDispatcher("/seatAvaiable.jsp").forward(request,response);
	}

}
