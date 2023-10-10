package client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CinemaDAO;
import DAO.ShowingDAO;
import bean.ShowingBean;
/**
 * Servlet implementation class SearchShowing
 */
@WebServlet("/SearchShowing")
public class SearchShowing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cinemaId= Integer.valueOf(request.getParameter("cinemaId"));
		int movieId = Integer.valueOf(request.getParameter("movieId"));
		String dStr = (String)request.getParameter("selectDate");
		if(dStr != null ){
			List<ShowingBean> showingList = null;
			if( dStr.length()>0) {
				showingList =ShowingDAO.advanceSearchShowing(movieId, cinemaId, dStr);
			}else {
				showingList = ShowingDAO.searchShowing(movieId, cinemaId);
			}
			request.setAttribute("showes", showingList);
		}else {			
			List<ShowingBean> showingList = ShowingDAO.searchShowing(movieId, cinemaId);
			request.setAttribute("showes", showingList);
		}
		request.setAttribute("cinemaName", CinemaDAO.getCinemaName(cinemaId));
		request.setAttribute("cinemaId", cinemaId);
		request.setAttribute("movieId", movieId);
		request.getRequestDispatcher("/searchShowing.jsp").forward(request, response);
	}
}
