package enumerator;

/**
 * A list of all show types. 
 * @author Sadi Wali
 *
 */
public enum ShowType {
	NOTYPE, SHOW, ANIME, MOVIE;
	
	/**
	 * Given a string representation of a show type, 
	 * determine if its a valid show type from enumerator.
	 * @param showtype is a show type.
	 * @return true if it is a valid show type, false otherwise.
	 */
	public static boolean isValidType(String showtype) {
		for(ShowType type : ShowType.values()) {
			if (type.toString().equalsIgnoreCase(showtype)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Given a string representation of a show type, get the 
	 * ShowType object corresponding to that show type.
	 * If the show type entered is not one of the valid
	 * show types, return null.
	 * @param showtypestr the show type to get back.
	 * @return the show type if it is found, null otherwise.
	 */
	public static ShowType getShowType(String showtypestr) {
		for(ShowType type : ShowType.values()) {
			if (type.toString().equalsIgnoreCase(showtypestr)) {
				return type;
			}
		}
		// show type not found
		return null;
		
	}
	
}
