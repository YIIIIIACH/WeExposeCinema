package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdersDAO {
	private static final String CREATE_SQL = "insert into orders values( ? , 'ordering' )";
	private static final String GET_LASTEST_PK_SQL = "select @@identity";
	
	public static int createOrder( int memberId) {
		int res =- 1;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(CREATE_SQL);
			pstm.setInt(1, memberId);
			res = pstm.executeUpdate();
			if(res<=0) {
				return res;// means insert failed
			}
			
			pstm.close();
			pstm = conn.prepareStatement(GET_LASTEST_PK_SQL);
			ResultSet rs = pstm.executeQuery();
			if( rs.next()) {
				res = rs.getInt(1);
			}
			pstm.close();
			conn.close();
			context.close();
		}catch(NamingException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}