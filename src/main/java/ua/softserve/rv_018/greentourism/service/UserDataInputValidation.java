package ua.softserve.rv_018.greentourism.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataInputValidation {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*.(com|org|au|net|biz|me|name|tel|pw|asia|cc|in|it|tv|ws|global|host|email|club|company|domains|fitness|finansia|help|hous|works|top|uno|website)$";

    private static final String PASSWORD_PATTERN = 
			"^"                     //Start of the string
			+ "(?=.*[0-9])"			//A digit must occur at least once
			+ "(?=.*[a-z])"			//A lower case letter must occur at least once
			+ "(?=.*[A-Z])"			//An upper case letter must occur at least once
			+ "[0-9a-zA-Z_-]"		//Only numerics, chars, underscores and hyphens allowed
			+ "{5,}"				//Must contain at least 5 characters
			+ "$";					//End of the string
	
    private static final String NAME_PATTERN = 
			"^"						//Start of the string	 
			+ "[a-zA-z]"			//Must contain only latin symbols
			+ "{2,}"				//Must contain at least 2 characters
			+ "$";					//End of the string
	
    private static final String USERNAME_PATTERN = 
			"^"						//Start of the string
			+ "(?=.*[a-zA-Z])"		//A lower or upper case letter must occur at least once
			+ "[a-zA-Z0-9_-]"		//Must contain numerics, chars, underscores and hyphens
			+ "{5,15}"				//Must contain from 3 to 15 characters
			+ "$";					//End of the string

    public static boolean validateEmail(final String hex) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
    
    public static boolean validatePassword(String password) {
    	Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		
    	Matcher matcher = pattern.matcher(password);
    	return matcher.matches();
    }
	
    public static boolean validateName(String name) {
    	Pattern pattern = Pattern.compile(NAME_PATTERN);
		
    	Matcher matcher = pattern.matcher(name);
    	return matcher.matches();
    }
	
    public static boolean validateUsername(String username) {
    	Pattern pattern = Pattern.compile(USERNAME_PATTERN);
		
    	Matcher matcher = pattern.matcher(username);
    	return matcher.matches();
    }
}
