package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookingDAO;
import DAO.CinemaDAO;
import DAO.MemberDAO;
import DAO.MovieDAO;
import DAO.OrdersDAO;
import DAO.ProductDAO;
import DAO.ProductServiceDAO;
import DAO.SeatDAO;
import DAO.TheaterDAO;
import bean.ProductBean;
import bean.SeatBean;
/**
 * Servlet implementation class BookingSeatsAndMakeOrder
 */
@WebServlet("/Booking")
public class BookingSeatsAndMakeOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String seatsStr = request.getParameter("seatsStr");
		Integer showingId = Integer.valueOf( request.getParameter("showingId"));
		List<SeatBean> sbList = parseSeatsString( seatsStr, showingId);
		for( SeatBean sb: sbList) {
			if( BookingDAO.checkBookingValid(sb.getSeatId(), showingId)==false) {
				request.getRequestDispatcher("/bookingFailed").forward(request,response);
				return;
			}
		}
		HttpSession sess = request.getSession();
		if( sess.getAttribute("account")==null || sess.getAttribute("password")==null) {
			
			request.getRequestDispatcher("/DisplayMovieDesp").forward(request, response);
			return;
		}
		String acc = (String)sess.getAttribute("account");
		String pwd =  (String)sess.getAttribute("password");
		// 1. create oder
		Integer MemberId = MemberDAO.getMemberId(acc, pwd);
		if( MemberId<=0 ) {
			// member invalid; 
			request.getRequestDispatcher("/DisplayMovieDesp").forward(request, response);
			return;
		}
		Integer OrderId = OrdersDAO.createOrder(MemberId);  
		// 2. get the productId and productPricing  
		ProductBean pb = ProductDAO.getProduct(showingId);
		for( SeatBean sb : sbList) {
			//3 .use productId and productPricing to insert  productService 
			Integer productServiceId = ProductServiceDAO.createProductSerivce(OrderId, pb);
			//4. use showingId and  selectedRow , selectedColumn to get the seatId;
			Integer seatId = SeatDAO.getSeatId(showingId, sb.getSeatRow(), sb.getSeatColumn());
			//5. create booking for selected seats;
			BookingDAO.createBooking(productServiceId, showingId, seatId, "booked");
		}
		//showingId -> movieName, movieDuration and theaterName -> cinemaName
		// seatList
		
		// setAttribute of ava add product
		
		request.setAttribute("addableProducts",ProductDAO.getAddedProduct());
		request.setAttribute("orderId", OrderId);
		request.setAttribute("cinema", CinemaDAO.getCinemaNameWithShowingId(showingId));
		request.setAttribute("theater", TheaterDAO.getTheater(showingId));
		request.setAttribute("movie", MovieDAO.getShowingMovie(showingId));
		request.setAttribute("seatList", sbList);
		request.setAttribute("product", pb);
		request.getRequestDispatcher("/bookingSuccess.jsp").forward(request, response);
	}
	
	
	public static List<SeatBean> parseSeatsString(String seatsStr, Integer showingId){
		List<SeatBean> sb = new ArrayList<SeatBean>();
		String[] seats= seatsStr.split(",");
		if(seats.length<=0) return sb;
		for( String seat: seats) {
			String[] s = seat.split("-");
			if(s!=null && s[0]!="") {				
				SeatBean tmp = new SeatBean();
				tmp.setSeatRow(Integer.valueOf(s[0]));
				tmp.setSeatColumn(Integer.valueOf(s[1]));
				tmp.setSeatId( SeatDAO.getSeatId(showingId, Integer.valueOf(s[0]), Integer.valueOf(s[1]) ));
				sb.add(tmp);
			}
		}
		return sb;
	}

}
