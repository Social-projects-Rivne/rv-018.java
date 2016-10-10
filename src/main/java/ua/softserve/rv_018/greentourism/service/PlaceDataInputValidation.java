package ua.softserve.rv_018.greentourism.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceDataInputValidation {
	
    private static final String PLACE_NAME_PATTERN = 
			"^"						//Start of the string	 
			+ "[a-zA-z]"			//Must contain only latin symbols
			+ "{2,}"				//Must contain at least 2 characters
			+ "$";					//End of the string
	
    private static final String DESCRIPTION_PATTERN = 
    		"^"						//Start of the string	 
			+ "[a-zA-z]"			//Must contain only latin symbols
			+ "{2,}"				//Must contain at least 2 characters
			+ "$";					//End of the string
	
    public static boolean validatePlaceName(String placename) {
    	Pattern pattern = Pattern.compile(PLACE_NAME_PATTERN);
		
    	Matcher matcher = pattern.matcher(placename);
    	return matcher.matches();
    }
	
    public static boolean validateDescription(String description) {
    	Pattern pattern = Pattern.compile(DESCRIPTION_PATTERN);
		
    	Matcher matcher = pattern.matcher(description);
    	return matcher.matches();
    }
}
