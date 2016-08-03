package ua.softserve.rv_018.greentourism.service;

import static org.junit.Assert.*;
import org.junit.Test;

public class DataValidationUnitTest {
	private String[] validPasswords = 
		{"Abcd1", "Ab123", "1A_2b-3c", "-_-1Ab"};
	
	private String[] invalidPasswords =
		{	"1234a", 	//Doesn't contain an upper case letter
			"Abcde", 	//Doesn't contain a digit
			"abcd1", 	//Doesn't contain a lower case letter
			"Abcd 1",	//Contains whitespace
			"Abc1",		//Contains not enough symbols
			"#Abcd1",	//Contains inappropriate symbol
			"&*#@`%" }; //Absolutely wrong password 
	
	private String[] validFLNames = 
		{"Вася", "Віка", "Vasya", "vanya", "ЄєІіЇїЬь" };
	
	private String[] invalidFLNames = 
		{	"Вася1", 	//Contains a digit
			"Віка$", 	//Contains inappropriate symbol
			"V", 		//Contains not enough symbols
			"#12%$" };  //Absolutely wrong first or last name
	
	private String[] validUsernames = 
		{"vasya", "VaSyA", "V_a-s_y-a", "vasya123", "1-2_vasya_3-4" };
	
	private String[] invalidUsernames = 
		{	"123123", 	//Doesn't contain any letter
			"vasya$", 	//Contains inappropriate symbol
			"@abc123",  //One more inappropriate symbol
			"V123", 	//Contains not enough symbols
			"#ії%$" };  //Absolutely wrong username
	
	//Checks if validatePassword method returns true for every string in validPasswords array
	@Test
	public void testValidPasswords() {
		for (String str : validPasswords)
			assertTrue(UserDataInputValidation.validatePassword(str));
	}
	
	//Checks if validatePassword method returns false for every string in invalidPasswords array
	@Test
	public void testInvalidPasswords() {
		for (String str : invalidPasswords)
			assertFalse(UserDataInputValidation.validatePassword(str));
	}
	
	//Checks if validateFLName method returns true for every string in validFLNames array
	@Test
	public void testValidFLNames() {
		for (String str : validFLNames)
			assertTrue(UserDataInputValidation.validateFLName(str));
	}
	
	//Checks if validateFLName method returns false for every string in invalidFLNames array
	@Test
	public void testInvalidFLNames() {
		for (String str : invalidFLNames)
			assertFalse(UserDataInputValidation.validateFLName(str));
	}
	
	//Checks if validateUsername method returns true for every string in validUsernames array
	@Test
	public void testValidUsernames() {
		for (String str : validUsernames)
			assertTrue(UserDataInputValidation.validateUsername(str));
	}
	
	//Checks if validateUsername method returns false for every string in invalidUsernames array
	@Test
	public void testInvalidUsernames() {
		for (String str : invalidUsernames)
			assertFalse(UserDataInputValidation.validateUsername(str));
	}
}
