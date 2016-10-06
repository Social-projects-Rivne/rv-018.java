package ua.softserve.rv_018.greentourism.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.PasswordResetToken;
import org.springframework.transaction.annotation.Propagation;
import ua.softserve.rv_018.greentourism.repository.PasswordResetTokenRepository;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class PasswordResetTokenServiceImpl  implements PasswordResetTokenService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public PasswordResetToken findeByToken(String token) {
		logger.info("> PasswordResetToken findeByToken token:{}", token); 
		
		PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
		
		logger.info("< PasswordResetToken findeByToken token:{}", token); 
		
		return passwordResetToken;
	}
	
	
}
