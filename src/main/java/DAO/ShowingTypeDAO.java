package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CinemaBean;
import bean.ShowingTypeBean;

public class ShowingTypeDAO {
	private static final String GET_TYPE_BY_ID =" select * from showingType where showingTypeId =?";
	public static ShowingTypeBean getShowingType( Integer showingType) {
		ShowingTypeBean res = null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_TYPE_BY_ID);
			pstm.setInt(1, showingType);
			ResultSet rs = pstm.executeQuery();
			if( rs.next()) {
				res = new ShowingTypeBean();
				res.setProductId_fk(rs.getInt("productId_fk"));
				res.setShowingTypeId(rs.getInt("showingTypeId"));
				res.setShowingTypeName(rs.getString("showingTypeName"));
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
