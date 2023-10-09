package bean;

public class ProductServiceBean {
	private int productServiceId;
	private int orderId_fk;
	private int productId_fk;
	private int discountTicketId_fk;
	private int productServicePrice;
	public int getProductServiceId() {
		return productServiceId;
	}
	public void setProductServiceId(int productServiceId) {
		this.productServiceId = productServiceId;
	}
	public int getOrderId_fk() {
		return orderId_fk;
	}
	public void setOrderId_fk(int orderId_fk) {
		this.orderId_fk = orderId_fk;
	}
	public int getProductId_fk() {
		return productId_fk;
	}
	public void setProductId_fk(int productId_fk) {
		this.productId_fk = productId_fk;
	}
	public int getDiscountTicketId_fk() {
		return discountTicketId_fk;
	}
	public void setDiscountTicketId_fk(int discountTicketIf_fk) {
		this.discountTicketId_fk = discountTicketIf_fk;
	}
	public int getProductServicePrice() {
		return productServicePrice;
	}
	public void setProductServicePrice(int productServicePrice) {
		this.productServicePrice = productServicePrice;
	}
}
