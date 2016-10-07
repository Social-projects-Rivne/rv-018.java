package ua.softserve.rv_018.greentourism.service;

public interface SecurityUserService {
	
	String validatePasswordResetToken(long id, String token);

}
