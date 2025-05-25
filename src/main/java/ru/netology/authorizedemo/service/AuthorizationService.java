package ru.netology.authorizedemo.service;

import org.springframework.stereotype.Service;
import ru.netology.authorizedemo.exception.InvalidCredentials;
import ru.netology.authorizedemo.exception.UnauthorizedUser;
import ru.netology.authorizedemo.repository.User;
import ru.netology.authorizedemo.repository.UserRepository;

import java.util.List;

@Service
public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    public void addUser(User user, Authorities authorities) {
        userRepository.addUserAuthorities(user, authorities);
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
