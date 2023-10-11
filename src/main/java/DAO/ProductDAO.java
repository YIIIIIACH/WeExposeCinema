package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ProductBean;

public class ProductDAO {
	private static final String GETPRODUCT_SQL= "select productId , productName ,productPricing from product where productId in (select 	productId_fk from showingType where showingTypeId in( select showingTypeId_fk from showing where showingId = ?))";
	private static final String GET_ADDABLE_PRODUCT_SQL = "select * from product where productType = '加購'";
	private static final String GET_PRODUCT_BY_ID_SQL = "select * from product where productId =?";
	private static final String GET_ALL_ADD_PRODUCT_SQL = "select * from product where productType='加購'";
	private static final String GET_ADDED_PRODUCT_BY_ORDERID_SQL= 
			"select * from product right join productService on productId=productId_fk where orderId_fk=? and productType='加購'";
	public static ProductBean getProductById( int productId) {
		ProductBean pb = null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_PRODUCT_BY_ID_SQL);
			pstm.setInt(1, productId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				pb= new ProductBean();
				pb.setProductId(rs.getInt("productId"));
				pb.setProductName(rs.getString("productName"));
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
				pb.setProductName(rs.getString("productName"));
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
	
	public static List<ProductBean> getAddedProduct(){
		List<ProductBean> pbList = null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_ADDABLE_PRODUCT_SQL);
			pbList = new ArrayList<ProductBean>();
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				ProductBean tmp = new ProductBean();
				tmp.setProductId(rs.getInt("productId"));
				tmp.setProductName(rs.getString("productName"));
				tmp.setProductType(rs.getString("productType"));
				tmp.setProductPricing(rs.getInt("productPricing"));
				pbList.add(tmp);
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
		return pbList;
	}
	
	public static Map<Integer,ProductBean> getAllAddedProductMap(){
		Map<Integer,ProductBean> pbMap = null;
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_ALL_ADD_PRODUCT_SQL);
			pbMap = new HashMap<Integer,ProductBean>();
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				ProductBean tmp = new ProductBean();
				tmp.setProductId(rs.getInt("productId"));
				tmp.setProductName(rs.getString("productName"));
				tmp.setProductType(rs.getString("productType"));
				tmp.setProductPricing(rs.getInt("productPricing"));
				pbMap.put(tmp.getProductId(), tmp);
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
		return pbMap;
	}
	public static List<ProductBean> getAddProductByOrderId(Integer orderId){
		List<ProductBean> pbList = null;
		//ProductServiceDAO.getProductServiceByBooking(bb.getBookingId())
		try {
			Context context= new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(GET_ADDED_PRODUCT_BY_ORDERID_SQL);
			pstm.setInt(1, orderId);
			ResultSet rs = pstm.executeQuery();
			pbList = new ArrayList<ProductBean>();
			while(rs.next()) {
				ProductBean pb = new ProductBean();
				pb= new ProductBean();
				pb.setProductId(rs.getInt("productId"));
				pb.setProductName(rs.getString("productName"));
				pb.setProductPricing(rs.getInt("productPricing"));
				pbList.add(pb);
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
		return pbList;
	}
}