package client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		String dStr = request.getParameter("selectDate");
		if(dStr != null ){
			List<ShowingBean> showingList = null;
			if( dStr.length()>0) {
				showingList =ShowingDAO.advanceSearchShowing(movieId, cinemaId, dStr);
			}else {
				//sdFormat.format(current)
				dStr= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				showingList = ShowingDAO.advanceSearchShowing(movieId, cinemaId, dStr);
			}
			request.setAttribute("showes", showingList);
		}else {		
			dStr= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			List<ShowingBean> showingList = ShowingDAO.advanceSearchShowing(movieId, cinemaId, dStr);
			request.setAttribute("showes", showingList);
		}
		request.setAttribute("selectDate", dStr);
		request.setAttribute("cinemaName", CinemaDAO.getCinemaName(cinemaId));
		request.setAttribute("cinemaId", cinemaId);
		request.setAttribute("movieId", movieId);
		request.getRequestDispatcher("/searchShowing.jsp").forward(request, response);
	}
}
