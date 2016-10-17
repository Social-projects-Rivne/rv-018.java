package ua.softserve.rv_018.greentourism.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceDataInputValidation {
	
    private static final String PLACE_NAME_PATTERN =  
    		"[\\s\\w]"		        //Must contain only latin symbols, may contain numbers and backspaces
    		+ "{5,}";				//Must contain at least 5 characters
	
    private static final String DESCRIPTION_PATTERN = 
    		"[\\s\\w]"		        //May contain only latin symbols, may contain numbers
			+ "{5,}";				//Must contain at least 5 characters
    
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
