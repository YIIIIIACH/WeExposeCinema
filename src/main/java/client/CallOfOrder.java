package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.OrdersDAO;

/**
 * Servlet implementation class CallOfOrder
 */
@WebServlet("/CallOfOrder")
public class CallOfOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//need orderId
		request.setCharacterEncoding("UTF-8");
		Integer orderId = Integer.valueOf( (request.getParameter("orderId")!=null)?request.getParameter("orderId"):"0");
		OrdersDAO.deleteBookingProductService(orderId);
		request.getRequestDispatcher("/MemberInfo").forward(request, response);
	}


}
