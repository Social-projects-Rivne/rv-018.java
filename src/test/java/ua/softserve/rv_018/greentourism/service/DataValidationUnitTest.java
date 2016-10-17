package ua.softserve.rv_018.greentourism.service;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataValidationUnitTest {
	private static final String[] VALID_PASSWORDS = 
		{"Abcd1", "Ab123", "1A_2b-3c", "-_-1Ab"};
	
	private static final String[] INVALID_PASSWORDS =
		{	"1234a", 	//Doesn't contain an upper case letter
			"Abcde", 	//Doesn't contain a digit
			"abcd1", 	//Doesn't contain a lower case letter
			"Abcd 1",	//Contains whitespace
			"Abc1",		//Contains not enough symbols
			"#Abcd1",	//Contains inappropriate symbol
			"&*#@%" }; //Absolutely wrong password 
	
	private static final String[] VALID_NAMES = 
		{"Whatever", "smth", "Vasya", "vanya", "me" };
	
	private static final String[] INVALID_NAMES = 
		{	"Vasya1", 	//Contains a digit
			"smth$", 	//Contains inappropriate symbol
			"V", 		//Contains not enough symbols
			"#12%$" };  //Absolutely wrong first or last name
	
	private static final String[] VALID_USERNAMES = 
		{"vasya", "VaSyA", "V_a-s_y-a", "vasya123", "1-2_vasya_3-4" };
	
	private static final String[] INVALID_USERNAMES = 
		{	"123123", 	//Doesn't contain any letter
			"vasya$", 	//Contains inappropriate symbol
			"@abc123",  //One more inappropriate symbol
			"V123", 	//Contains not enough symbols
			"#3a%$" };  //Absolutely wrong username
	
	private static final String[] VALID_PLACE_NAMES = 
		{"Whatever", "smthing 5", "Tarakaniv", "fortress", "blablabla bla" };
	
	private static final String[] INVALID_PLACE_NAMES = 
		{   "smth$", 	//Contains inappropriate symbol
			"V", 		//Contains not enough symbols
			"#12%$" };  //Absolutely wrong name
	
	private static final String[] VALID_DESCRIPTIONS = 
		{"Whatever", "smthing 5", "Tarakaniv", "fortress", "blablabla bla" };
	
	private static final String[] INVALID_DESCRIPTIONS = 
		{   "smth$", 	//Contains inappropriate symbol
			"V", 		//Contains not enough symbols
			"#12%$" };  //Contains symbols - not appropriate

	//Checks if validatePassword method returns true for every string in validPasswords array
	@Test
	public void testValidPasswords() {
		for (String str : VALID_PASSWORDS)
			assertTrue(UserDataInputValidation.validatePassword(str));
	}
	
	//Checks if validatePassword method returns false for every string in invalidPasswords array
	@Test
	public void testInvalidPasswords() {
		for (String str : INVALID_PASSWORDS)
			assertFalse(UserDataInputValidation.validatePassword(str));
	}
	
	//Checks if validateName method returns true for every string in validNames array
	@Test
	public void testValidNames() {
		for (String str : VALID_NAMES)
			assertTrue(UserDataInputValidation.validateName(str));
	}
	
	//Checks if validateName method returns false for every string in invalidNames array
	@Test
	public void testInvalidNames() {
		for (String str : INVALID_NAMES)
			assertFalse(UserDataInputValidation.validateName(str));
	}
	
	//Checks if validateUsername method returns true for every string in validUsernames array
	@Test
	public void testValidUsernames() {
		for (String str : VALID_USERNAMES)
			assertTrue(UserDataInputValidation.validateUsername(str));
	}
	
	//Checks if validateUsername method returns false for every string in invalidUsernames array
	@Test
	public void testInvalidUsernames() {
		for (String str : INVALID_USERNAMES)
			assertFalse(UserDataInputValidation.validateUsername(str));
	}
	
	//Checks if validatePlaceName method returns true for every string in validPlaceNames array
	@Test
	public void testValidPlaceNames() {
		for (String str : VALID_PLACE_NAMES)
			assertTrue(PlaceDataInputValidation.validatePlaceName(str));
	}
		
	//Checks if validatePlaceName method returns false for every string in invalidPlaceNames array
	@Test
	public void testInvalidPlaceNames() {
		for (String str : INVALID_PLACE_NAMES){		
			assertFalse(PlaceDataInputValidation.validatePlaceName(str));
		}
	}
	
	//Checks if validateDescription method returns true for every string in validDescriptions array
	@Test
	public void testValidDescriptions() {
		for (String str : VALID_DESCRIPTIONS)
			assertTrue(PlaceDataInputValidation.validateDescription(str));
	}
			
	//Checks if validateDescription method returns false for every string in invalidDescriptions array
	@Test
	public void testInvalidDescriptions() {
		for (String str : INVALID_DESCRIPTIONS)
			assertFalse(PlaceDataInputValidation.validateDescription(str));
	}
}
