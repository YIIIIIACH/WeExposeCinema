package bean;

import java.sql.Date;

public class MovieBean {
	private int movieId;
	private String movieName;
	private Date movieDuration;
	private String movieGrade;
	private String movieDescription;
	private String movieImagePath;
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Date getMovieDuration() {
		return movieDuration;
	}
	public void setMovieDuration(Date movieDuration) {
		this.movieDuration = movieDuration;
	}
	public String getMovieGrade() {
		return movieGrade;
	}
	public void setMovieGrade(String movieGrade) {
		this.movieGrade = movieGrade;
	}
	public String getMovieDescription() {
		return movieDescription;
	}
	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}
	public String getMovieImagePath() {
		return movieImagePath;
	}
	public void setMovieImagePath(String movieImagePath) {
		this.movieImagePath = movieImagePath;
	}
	
}
