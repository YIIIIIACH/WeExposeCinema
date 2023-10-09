package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession();
		if(sess.getAttribute("account")!=null) {
			sess.removeAttribute("account");
		}
		if(sess.getAttribute("password")!=null) {
			sess.removeAttribute("password");
		}
		if(sess.getAttribute("memberName")!=null) {
			sess.removeAttribute("memberName");
		}
		if(sess.getAttribute("memberGrade")!=null) {
			sess.removeAttribute("memberGrade");
		}
		request.getRequestDispatcher("DisplayMovieDesp").forward(request, response);
	}
}
