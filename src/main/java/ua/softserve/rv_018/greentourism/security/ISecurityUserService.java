package ua.softserve.rv_018.greentourism.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(long id, String token);

}
