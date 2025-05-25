package ru.netology.authorizedemo.repository;

import org.springframework.stereotype.Repository;
import ru.netology.authorizedemo.service.Authorities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class UserRepository {
    public ConcurrentHashMap<User, CopyOnWriteArrayList<Authorities>> users = new ConcurrentHashMap<>();

    public List<Authorities> getUserAuthorities(String user, String password) {
        return users.get(new User(user, password));
    }

    public void addUserAuthorities(User user, Authorities authorities) {
        users.computeIfAbsent(user, k -> new CopyOnWriteArrayList<>()).add(authorities);
    }
}
