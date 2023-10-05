package bean;

public class TheaterBean {
	private int theaterId;
	private String theaterName;
	private int cinemaId_fk;
	public int getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public int getCinemaId_fk() {
		return cinemaId_fk;
	}
	public void setCinemaId_fk(int cinemaId_fk) {
		this.cinemaId_fk = cinemaId_fk;
	}
	
}
