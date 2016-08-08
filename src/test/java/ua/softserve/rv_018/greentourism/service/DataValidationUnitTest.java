package ua.softserve.rv_018.greentourism.service;

import static org.junit.Assert.*;
import org.junit.Test;

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
			"&*#@`%" }; //Absolutely wrong password 
	
	private static final String[] VALID_NAMES = 
		{"Вася", "Віка", "Vasya", "vanya", "ЄєІіЇїЬь" };
	
	private static final String[] INVALID_NAMES = 
		{	"Вася1", 	//Contains a digit
			"Віка$", 	//Contains inappropriate symbol
			"V", 		//Contains not enough symbols
			"#12%$" };  //Absolutely wrong first or last name
	
	private static final String[] VALID_USERNAMES = 
		{"vasya", "VaSyA", "V_a-s_y-a", "vasya123", "1-2_vasya_3-4" };
	
	private static final String[] INVALID_USERNAMES = 
		{	"123123", 	//Doesn't contain any letter
			"vasya$", 	//Contains inappropriate symbol
			"@abc123",  //One more inappropriate symbol
			"V123", 	//Contains not enough symbols
			"#ії%$" };  //Absolutely wrong username
	
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
	
	//Checks if validateFLName method returns true for every string in validNames array
	@Test
	public void testValidFLNames() {
		for (String str : VALID_NAMES)
			assertTrue(UserDataInputValidation.validateFLName(str));
	}
	
	//Checks if validateFLName method returns false for every string in invalidNames array
	@Test
	public void testInvalidFLNames() {
		for (String str : INVALID_NAMES)
			assertFalse(UserDataInputValidation.validateFLName(str));
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
}
