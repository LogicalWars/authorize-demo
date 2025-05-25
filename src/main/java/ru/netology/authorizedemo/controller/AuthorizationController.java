package ru.netology.authorizedemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.authorizedemo.exception.InvalidCredentials;
import ru.netology.authorizedemo.exception.UnauthorizedUser;
import ru.netology.authorizedemo.repository.User;
import ru.netology.authorizedemo.service.Authorities;
import ru.netology.authorizedemo.service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentials e) {
        return new ResponseEntity<>(String.valueOf(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> handleUnauthorizedUserException(UnauthorizedUser e) {
        System.err.println(e);
        return new ResponseEntity<>(String.valueOf(e), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/add/{authorize}")
    public void addAuthorities(@RequestBody User user, @PathVariable("authorize") Authorities authorize) {
        service.addUser(user, authorize);
    }
}
