package com.imdb.model;

import java.util.ArrayList;
import java.util.List;

/** Movie object with fields.
 * 
<ul>
    <li>This class has the 1 to many relation with Crew object.</li>
    <li>All fields should be mapped to movie_metadata.csv.</li>
</ul>   
 *
 * @author widnu
 *
 */
public class Movie {

	private String title;

	private Double duration;

	private String[] genres;
	
	private String[] plotKeywords;

	private Integer year;

	private Double imdbScore;

	private Double aspectRatio;

	private int castTotalFacebookLike;

	private int movieFacebookLike;

	private int faceNumberInPoster;

	private String imdbLink;

	private int reviewedUser;

	private int reviewedCritic;

	private int votedUser;

	private String language;

	private String country;

	private String contentRating;

	private Double gross;

	private Double budget;

	private List<Crew> crewList;

	/**
	 * Construct the movie object
	 */
	public Movie() {
		super();
		crewList = new ArrayList<Crew>();
	}

	/**
	 * Construct the movie object with some fields
	 * 
	 * @param title
	 * @param year
	 * @param imdbScore
	 * @param budget
	 * @param gross
	 */
	public Movie(String title, Integer year, Double imdbScore, Double budget, Double gross) {
		super();
		this.title = title;
		this.year = year;
		this.imdbScore = imdbScore;
		this.budget = budget;
		this.gross = gross;
		
		crewList = new ArrayList<Crew>();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getImdbScore() {
		return imdbScore;
	}

	public void setImdbScore(Double imdbScore) {
		this.imdbScore = imdbScore;
	}

	public Double getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(Double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public int getCastTotalFacebookLike() {
		return castTotalFacebookLike;
	}

	public void setCastTotalFacebookLike(int castTotalFacebookLike) {
		this.castTotalFacebookLike = castTotalFacebookLike;
	}

	public int getMovieFacebookLike() {
		return movieFacebookLike;
	}

	public void setMovieFacebookLike(int movieFacebookLike) {
		this.movieFacebookLike = movieFacebookLike;
	}

	public int getFaceNumberInPoster() {
		return faceNumberInPoster;
	}

	public void setFaceNumberInPoster(int faceNumberInPoster) {
		this.faceNumberInPoster = faceNumberInPoster;
	}

	public String getImdbLink() {
		return imdbLink;
	}

	public void setImdbLink(String imdbLink) {
		this.imdbLink = imdbLink;
	}

	public int getReviewedUser() {
		return reviewedUser;
	}

	public void setReviewedUser(int reviewedUser) {
		this.reviewedUser = reviewedUser;
	}

	public int getReviewedCritic() {
		return reviewedCritic;
	}

	public void setReviewedCritic(int reviewedCritic) {
		this.reviewedCritic = reviewedCritic;
	}

	public int getVotedUser() {
		return votedUser;
	}

	public void setVotedUser(int votedUser) {
		this.votedUser = votedUser;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContentRating() {
		return contentRating;
	}

	public void setContentRating(String contentRating) {
		this.contentRating = contentRating;
	}

	public Double getGross() {
		return gross;
	}

	public void setGross(Double gross) {
		this.gross = gross;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public List<Crew> getCrewList() {
		return crewList;
	}

	public void setCrewList(List<Crew> crewList) {
		this.crewList = crewList;
	}

	public void addCrewList(Crew crew) {
		this.crewList.add(crew);
	}

	public String[] getPlotKeywords() {
		return plotKeywords;
	}

	public void setPlotKeywords(String[] plotKeywords) {
		this.plotKeywords = plotKeywords;
	}

}
