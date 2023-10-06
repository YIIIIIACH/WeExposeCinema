package test;
import bean.EmpBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SQL= "select * from employee";
	public static final String INSERT_SQL= " insert into employee values( ?,?,?,?,?,?)";
	public static final String DELETE_SQL = "delete from employee where empno=?";
	public static final String UPDATE_SQL = "update employee set salary=? where empno=?";
    Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				try {
					Context context= new InitialContext();
					DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
					conn = ds.getConnection();
					PreparedStatement  stmt = conn.prepareStatement(SQL);
					ResultSet rs =stmt.executeQuery();
					List<EmpBean> empList = new ArrayList<EmpBean>();
					EmpBean emp = null;
					while( rs.next()) {
						emp= new EmpBean();
						emp.setEmpno(rs.getString("empno"));
						emp.setEname(rs.getString("ename"));
						emp.setHiredate(rs.getString("hiredate"));
						emp.setSalary(rs.getString("salary"));
						emp.setDeptno(rs.getString("deptno"));
						emp.setTitle(rs.getString("title"));
						empList.add(emp);
					}
					request.setAttribute("empList" , empList);
					stmt.close();
					conn.close();
					response.getWriter().print("query Employee success");
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
}
