package ua.com.alevel.config.security;

import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.entity.user.User;

public interface SecurityService {

    boolean isAuthenticated();
    void autoLogin(String username, String password);
    boolean existsByEmail(String email);

    User currentUser();

    Personal currentPersonal();
}
