package pl.prodzajto.estolowkabackend.user.passwordrecovery;

public interface UserPasswordRecovery {
    void passwordRecoveryFlow(String email);

    boolean validatePasswordRecoverToken(String token);

    void changeUserPassword(String password, String token);
}
