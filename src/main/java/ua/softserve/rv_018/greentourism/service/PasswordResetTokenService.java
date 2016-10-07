package ua.softserve.rv_018.greentourism.service;

import ua.softserve.rv_018.greentourism.model.PasswordResetToken;

public interface PasswordResetTokenService {
	
	PasswordResetToken findeByToken (String token);

}
