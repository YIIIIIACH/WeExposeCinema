package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ShowingDAO;
import bean.ShowingBean;

/**
 * Servlet implementation class TestDAO
 */
@WebServlet("/TestDAO")
public class TestDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int res =ShowingDAO.addShowing(5, 14, "2023-10-17","11:11:00.000" ,2);
		response.getWriter().write("res"+ ((res>0)?"success":"failed"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
