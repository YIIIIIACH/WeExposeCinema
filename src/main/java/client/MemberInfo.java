package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import struct.MemberOrderInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.*;
import bean.*;
/**
 * Servlet implementation class MemberInfo
 */
@WebServlet("/MemberInfo")
public class MemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		if( sess.getAttribute("account")==null || sess.getAttribute("password")==null) {
			request.setAttribute("nextPath", "/MemberInfo");
			request.getRequestDispatcher("/Login").forward(request, response);
		}else {
			// get MemberBean by account and password;
			MemberBean mb = MemberDAO.getMemberBean((String)sess.getAttribute("account"), (String)sess.getAttribute("password"));
			List<MemberOrderInfo> orderInfoes = new ArrayList< MemberOrderInfo>();
			List<OrdersBean> obList = (mb==null)? new ArrayList<OrdersBean>():OrdersDAO.getOrders( mb.getMemberId() );
			if (obList !=null) {
				for( OrdersBean ob : obList) {
					// get all booking by order
					MemberOrderInfo info = new MemberOrderInfo();
					List<BookingBean> bbList = BookingDAO.getBookingByOrder(ob.getOrderId());
					List<ProductBean> addPbList = ProductDAO.getAddProductByOrderId(ob.getOrderId());
					MovieBean mvb = null;
					CinemaBean cb = null;
					ShowingBean sb = null;
//					TheaterBean tb = null;
					
					if(bbList.size()>0) {
						mvb = MovieDAO.getMovieByBooking(bbList.get(0).getBookingId());
						cb = CinemaDAO.getCinemaByBooking(bbList.get(0).getBookingId());
						sb = ShowingDAO.getShowinByBooking(bbList.get(0).getBookingId());
					
						info.bookings = bbList;
						info.order = ob;
						info.movie= mvb;
						info.cinema= cb;
						info.showing= sb;
						info.showingType = ShowingTypeDAO.getShowingType(sb.getShowingTypeId_fk());
						info.theater = TheaterDAO.getTheater(info.showing.getShowingId());
						List<SeatBean> sbList = new ArrayList<SeatBean>();
						List<ProductServiceBean> psbList = new ArrayList<ProductServiceBean>();
						for( BookingBean bb: bbList) {
							// product service
							ProductServiceBean psb =ProductServiceDAO.getProductServiceByBooking(bb.getBookingId()); 
							psbList.add( psb);
							sbList.add( SeatDAO.getSeat(bb.getBookingId()));
						}
						info.addedProduct = addPbList;
						info.seats = sbList;
						info.productServices= psbList;
						orderInfoes.add(info);
					}
				}
				request.setAttribute("orderInfoes", orderInfoes);
			}else {
				System.out.println("obList is null");
			}
			request.setAttribute("mb", mb);
			request.getRequestDispatcher("/memberInfo.jsp").forward(request,response);
		}
	}
	protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}

}
