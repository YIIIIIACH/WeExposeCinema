package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;

/**
 * Servlet implementation class EditMember
 */
@WebServlet("/EditMember")
public class EditMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// account password memberName memberGrade
		request.setCharacterEncoding("UTF-8");
		int resStat = MemberDAO.editMember(request.getParameter("account"),request.getParameter("password"), request.getParameter("memberName"),(String)request.getSession().getAttribute("account"));
		if(resStat<0) {
			// exception;
			request.setAttribute("message", "edit failed");
		}else if(resStat==0){
			// edit failed
			request.setAttribute("message", "can not find account");
		}else {
			//success
			HttpSession sess= request.getSession();
			sess.setAttribute("memberName", request.getParameter("memberName"));
			sess.setAttribute("account", request.getParameter("account")); //update acc sess and pwd sess
			sess.setAttribute("password", request.getParameter("password"));
		}
		request.getRequestDispatcher("/MemberInfo").forward(request, response);
	}

}
