package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ProductBean;

public class ProductDAO {
	private static final String GETPRODUCT_SQL= "select productId , productPricing from product where productId in (select 	productId_fk from showingType where showingTypeId in( select showingTypeId_fk from showing where showingId = ?))";
	public static ProductBean getProduct( int showingId) {
		ProductBean pb = null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GETPRODUCT_SQL);
			pstm.setInt(1, showingId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				pb= new ProductBean();
				pb.setProductId(rs.getInt("productId"));
				pb.setProductPricing(rs.getInt("productPricing"));
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
		return pb;
	}
}