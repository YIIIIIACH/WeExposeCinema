package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MemberDAO {
	private static final String VERIFY_ACC_SQL = "select * from member where memberAccount=? and memberPassword=?";
	public static Boolean verifyAccount(String account, String password){
		Boolean res= false;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(VERIFY_ACC_SQL);
			pstm.setString(1, account);
			pstm.setString(2, password);
			ResultSet rs = pstm.executeQuery();
			if( rs.next()) {
				res = true;
			}
			rs.close();
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