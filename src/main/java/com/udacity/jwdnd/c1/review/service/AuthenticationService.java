package com.udacity.jwdnd.c1.review.service;

import com.udacity.jwdnd.c1.review.mapper.UserMapper;
import com.udacity.jwdnd.c1.review.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * It tells spring how a user get authenticated (login)
 * The task of AuthenticationProvider is to get user details and compare it with the user input
 * and tells if it matches or not.
 */
@Service
public class AuthenticationService implements AuthenticationProvider {

    private final HashService hashService;
    private final UserMapper userMapper;

    public AuthenticationService(HashService hashService, UserMapper userMapper) {
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    /**
     * On successful login it returns Authentication object else throw AuthenticationException
     *
     * @param authentication the authentication request object.
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = this.userMapper.getUser(username);
        if (user != null) {
            String encodedSalt = user.getSalt();
            String hashedPassword = this.hashService.getHashedValue(password, encodedSalt);
            if (user.getPassword().equals(hashedPassword)) {
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
