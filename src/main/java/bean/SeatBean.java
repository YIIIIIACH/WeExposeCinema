package bean;

public class SeatBean {
	private int seatId;
	private int theaterId_fk;
	private int seatRow;
	private int seatColumn;
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public int getTheaterId_fk() {
		return theaterId_fk;
	}
	public void setTheaterId_fk(int theaterId_fk) {
		this.theaterId_fk = theaterId_fk;
	}
	public int getSeatRow() {
		return seatRow;
	}
	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}
	public int getSeatColumn() {
		return seatColumn;
	}
	public void setSeatColumn(int seatColumn) {
		this.seatColumn = seatColumn;
	}
}
