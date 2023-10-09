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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acc = request.getParameter("acc");
		String pwd = request.getParameter("pwd");
		String memberName = request.getParameter("memberName");
		Integer age = (request.getParameter("age")==null)? -1 :Integer.valueOf(request.getParameter("age"));
		request.setAttribute("nextPath", "/MemberInfo");
		if( acc==null || pwd==null || memberName==null || age<0 ) {
			request.getRequestDispatcher("/DisplayMoiveDesp").forward(request, response);
			return;
		}
		int resStat = MemberDAO.register(acc, pwd, memberName, age); 
		if(resStat>0) {
			HttpSession sess = request.getSession();
			sess.setAttribute("account", acc);
			sess.setAttribute("password", pwd);
			MemberBean mb = MemberDAO.getMemberBean(acc, pwd);
			sess.setAttribute("memberName", mb.getMemberName());
			sess.setAttribute("memberGrade", mb.getMemberGrade());
			request.getRequestDispatcher("/MemberInfo").forward(request, response);
		}else if(resStat == -2) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("重複的帳號或非法的密碼");
			request.getRequestDispatcher("/register.jsp").include(request, response);
		}
		else {
			request.getRequestDispatcher("/DisplayMovieDesp").forward(request, response);
		}
	}

}
