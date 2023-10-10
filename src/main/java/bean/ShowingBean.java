package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowingBean {
	private int showingId;
	public int getShowingId() {
		return showingId;
	}
	public void setShowingId(int showingId) {
		this.showingId = showingId;
	}
	public int getMovieId_fk() {
		return movieId_fk;
	}
	public void setMovieId_fk(int movieId_fk) {
		this.movieId_fk = movieId_fk;
	}
	public int getTheaterId_fk() {
		return theaterId_fk;
	}
	public void setTheaterId_fk(int theaterId_fk) {
		this.theaterId_fk = theaterId_fk;
	}
	public Date getShowingDatetime() {
		return showingDatetime;
	}
	public void setShowingDatetime(Date showingDatetime) {
		this.showingDatetime = showingDatetime;
	}
	public String getShowingDatetimeFormated() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format( this.showingDatetime);
	}
	public void setShowingDatetimeFormated(String showingDatetimeStr) {
		try {
			this.showingDatetime = new SimpleDateFormat("yyyy-MM-dd HH-mm").parse(showingDatetimeStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getShowingTypeId_fk() {
		return showingTypeId_fk;
	}
	public void setShowingTypeId_fk(int showingTypeId_fk) {
		this.showingTypeId_fk = showingTypeId_fk;
	}
	private int  movieId_fk;
	private int theaterId_fk;
	private Date showingDatetime;
	private int showingTypeId_fk;
}
