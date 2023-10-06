package bean;

public class TheaterDetailBean {
	private int theaterDetailId;
	private int theaterId_fk;
	private int theaterRow;
	private int theaterRowSize;
	public int getTheaterDetailId() {
		return theaterDetailId;
	}
	public void setTheaterDetailId(int theaterDetailId) {
		this.theaterDetailId = theaterDetailId;
	}
	public int getTheaterId_fk() {
		return theaterId_fk;
	}
	public void setTheaterId_fk(int theaterId_fk) {
		this.theaterId_fk = theaterId_fk;
	}
	public int getTheaterRow() {
		return theaterRow;
	}
	public void setTheaterRow(int theaterRow) {
		this.theaterRow = theaterRow;
	}
	public int getTheaterRowSize() {
		return theaterRowSize;
	}
	public void setTheaterRowSize(int theaterRowSize) {
		this.theaterRowSize = theaterRowSize;
	}
}
