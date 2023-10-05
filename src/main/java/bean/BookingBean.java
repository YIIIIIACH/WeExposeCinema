package bean;

public class BookingBean {
	private int bookingId;
	private int productServiceId_fk;
	private int showingId_fk;
	private int seatId_fk;
	private String bookingStatus;
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getProductServiceId_fk() {
		return productServiceId_fk;
	}
	public void setProductServiceId_fk(int productServiceId_fk) {
		this.productServiceId_fk = productServiceId_fk;
	}
	public int getShowingId_fk() {
		return showingId_fk;
	}
	public void setShowingId_fk(int showingId_fk) {
		this.showingId_fk = showingId_fk;
	}
	public int getSeatId_fk() {
		return seatId_fk;
	}
	public void setSeatId_fk(int seatId_fk) {
		this.seatId_fk = seatId_fk;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
}
