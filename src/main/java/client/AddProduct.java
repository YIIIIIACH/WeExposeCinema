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

import DAO.ProductDAO;
import DAO.ProductServiceDAO;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String addPStr = request.getParameter("addedProductsStr");
		String orderId = request.getParameter("orderId");
		if( addPStr==null || orderId==null) {
			response.sendRedirect("/WeExpose/DisplayMovieDesp");
			return;
		}
		if( addPStr.length()<=0 || orderId.length()<=0) {
			response.sendRedirect("/WeExpose/DisplayMovieDesp");
			return;
		}
		String[] pStrs = addPStr.split(",");
		List<Integer> addPIdList = new ArrayList<Integer>();
		for( String tmp: pStrs) {			
			addPIdList.add( Integer.valueOf(tmp));
			ProductServiceDAO.createProductSerivce(Integer.valueOf(orderId), ProductDAO.getProductById(Integer.valueOf(tmp)));
		}
		HttpSession sess = request.getSession();
		if( sess.getAttribute("account")==null || sess.getAttribute("password")==null) {
			response.sendRedirect("/WeExpose/DisplayMovieDesp");
			return;
		}request.setAttribute("addPIdList", addPIdList);
		request.getRequestDispatcher("/MemberInfo").forward(request,response);
	}
}
