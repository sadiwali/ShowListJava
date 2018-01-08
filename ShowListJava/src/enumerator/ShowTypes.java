package enumerator;

public enum ShowTypes {
	NOTYPE, SHOW, ANIME;
	

	public static boolean isValidType(ShowTypes showtype) {
		for(ShowTypes type : ShowTypes.values()) {
			if (type.equals(showtype)) {
				return true;
			}
		}
		
		return false;
		
	}
	
}
