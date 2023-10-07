package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SelectSeat
 */
@WebServlet("/SelectSeat")
public class SelectSeat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		if( sess.getAttribute("account")==null || sess.getAttribute("password")==null) {
			request.setAttribute("nextPath", "/SelectSeat");
			request.setAttribute("showingId", request.getParameter("showingId"));
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			request.setAttribute("showingId", request.getParameter("showingId"));
			request.getRequestDispatcher("/selectSeat.jsp").forward(request, response);
		}
	}
}
