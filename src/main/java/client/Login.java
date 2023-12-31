package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import bean.MemberBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPath = request.getParameter("nextPath");
		String showingId = request.getParameter("showingId");
		String acc = request.getParameter("acc");
		String pwd = request.getParameter("pwd");
		if(acc==null || pwd==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);	
			return;
		}
		
		
		if( MemberDAO.verifyAccount(acc, pwd)) {
			HttpSession sess = request.getSession();
			sess.setAttribute("account", acc);
			sess.setAttribute("password", pwd);
			MemberBean mb = MemberDAO.getMemberBean(acc, pwd);
			if( mb==null) {
				response.sendRedirect("/WeExpose/DisplayMovieDesp");
				return;
			}
			sess.setAttribute("memberName", mb.getMemberName());
			sess.setAttribute("memberGrade", mb.getMemberGrade());
			if(nextPath== null){
				response.sendRedirect("/WeExpose/DisplayMovieDesp");
			}
			else if( nextPath.length() <=0) {
				response.sendRedirect("/WeExpose/DisplayMovieDesp");
			}else {
				// store session;
				request.setAttribute("nextPath", nextPath);
				request.setAttribute("showingId", showingId);
				request.getRequestDispatcher(nextPath).forward(request, response);
			}
		}
		else {
			request.setAttribute("nextPath", nextPath);
			request.setAttribute("showingId", showingId);
			request.setAttribute("message","無效帳號或密碼錯誤");
			request.getRequestDispatcher("/login.jsp").forward(request, response);			
		}
	}
}
