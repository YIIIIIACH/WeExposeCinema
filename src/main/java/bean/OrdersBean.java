package bean;

public class OrdersBean {
	private int orderId;
	private int memberId_fk;
	private String orderStatus;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getMemberId_fk() {
		return memberId_fk;
	}
	public void setMemberId_fk(int memberId_fk) {
		this.memberId_fk = memberId_fk;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
