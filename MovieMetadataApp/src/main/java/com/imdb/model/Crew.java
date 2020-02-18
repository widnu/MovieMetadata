package com.imdb.model;

/** Crew object with fields.
 * 
<ul>
    <li>This class has the many to 1 relation with Movie object.</li>
    <li>The fullname and facebookLikes variables should be correlated to fields of director, actor1, actor2 and actor3 in movie_metadata.csv.</li>
</ul>   
 *
 * @author widnu
 *
 */
public class Crew {

	private String fullname;

	private int facebookLikes;
	
	public Crew(String fullname, int facebookLikes) {
		super();
		this.fullname = fullname;
		this.facebookLikes = facebookLikes;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getFacebookLikes() {
		return facebookLikes;
	}

	public void setFacebookLikes(int facebookLikes) {
		this.facebookLikes = facebookLikes;
	}

	@Override
	public String toString() {
		return "Crew [fullname=" + fullname + ", facebookLikes=" + facebookLikes + "]";
	}

}
