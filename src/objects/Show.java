package objects;

import enumerator.ShowType;

/**
 * This class represents a show.
 * @author Sadi Wali
 *
 */
public class Show {
	private String title = "";
	private String comments = "";
	private int rating = -1;
	private ShowType type = ShowType.NOTYPE;
	
	/**
	 * Construct the show with title, comments, and rating. Rating is out of 10.
	 * @param title is the title of the show.
	 * @param comments is for the comments the user has regarding the show
	 * @param rating is the rating in set [0, 10]
	 */
	public Show(String title, String comments, int rating) {
		this.title = title;
		this.comments = comments;
		this.rating = rating;
	}
	
	/**
	 * Construct the show with title, comments, rating, and a show type. Rating is out of 10.
	 * @param title is the title of the show.
	 * @param comments is for the comments the user has regarding the show
	 * @param rating is the rating in set [0, 10]
	 * @throws Exception 
	 */
	public Show(String title, String comments, int rating, String type) throws Exception {
		this.title = title;
		this.comments = comments;
		this.rating = rating;
		
		// check if given show type is valid
		if (ShowType.isValidType(type)) {
			this.type = ShowType.getShowType(type);
		} else {
			throw new Exception("Not a real type");
		}
	}
	
	/**
	 * Get the title of the show.
	 * @return is the title of the show.
	 */
	public String getTitle() { return this.title; }
	
	/**
	 * Get the comments of the show.
	 * @return is the comments given to the show.
	 */
	public String getComments() { return this.comments; }
	
	/**
	 * Get the rating given tothe show.
	 * @return is the rating given out of 10.
	 */
	public int getRating() { return this.rating; }
	
	/**
	 * Set the title of the show.
	 * @param title is the new title of the show.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set the comments for the show.
	 * @param comments is the new set of comments given to the show.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * Set the rating for the show.
	 * @param rating is the new rating for the show.
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	/**
	 * Determine if show is a good show or not. A good show has to have a minimum
	 * rating amount.
	 * @return true if it is a good show, false otherwise.
	 */
	public boolean isGoodShow() {
		if (this.rating >= Const.GOOD_SHOW_MIN_RATING) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return this.title + ";" + this.comments + ";" + this.rating + ";" + this.type; 
	}


}
