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
import bean.ProductServiceBean;

public class ProductServiceDAO {
	private static final String CREATE_PRODUCT_SERVICE_SQL=
			"insert into productService "
			+ "	values"
			+ "	( ? , ? , null , ? )";
	private static final String GET_LASTEST_PK_SQL= " select @@identity";
	private static final String GET_PRODUCT_SERVICE_BY_BOOKING_SQL = 
			"select * from productService where productServiceId in("
				+"select productServiceId_fk from booking where bookingId =? )";
	public static int createProductSerivce( Integer orderId , ProductBean pb) {
		int res =- 1;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(CREATE_PRODUCT_SERVICE_SQL);
			pstm.setInt(1, orderId);
			pstm.setInt(2, pb.getProductId());
			pstm.setInt(3, pb.getProductPricing());
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
	public static ProductServiceBean getProductServiceByBooking(Integer bookingId) {
		ProductServiceBean res = null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_PRODUCT_SERVICE_BY_BOOKING_SQL);
			pstm.setInt(1, bookingId);
			ResultSet rs = pstm.executeQuery();
			if( rs.next()) {
				res= new  ProductServiceBean();
				res.setProductServiceId(rs.getInt("productServiceId"));
				res.setProductId_fk(rs.getInt("productId_fk"));
				res.setOrderId_fk(rs.getInt("orderId_fk"));
				res.setDiscountTicketId_fk(rs.getInt("discountTicketId_fk"));
				res.setProductServicePrice(rs.getInt("productServicePrice"));
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
