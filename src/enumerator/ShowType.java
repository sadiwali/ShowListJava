package enumerator;

public enum ShowType {
	NOTYPE, SHOW, ANIME;
	

	public static boolean isValidType(String showtype) {
		for(ShowType type : ShowType.values()) {
			if (type.toString().equalsIgnoreCase(showtype)) {
				return true;
			}
		}
		
		return false;
		
	}
	
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
