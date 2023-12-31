package com.udacity.jwdnd.c1.review.service;

import com.udacity.jwdnd.c1.review.mapper.UserMapper;
import com.udacity.jwdnd.c1.review.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        return this.userMapper.getUser(username) == null;
    }

    public int createUser(User user) {

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);// generates 16 random bytes (numbers)
        String encodedSalt = Base64.getEncoder().encodeToString(salt); // Encodes the specified byte array into a String using the Base64 encoding scheme.
        String hashedPassword = this.hashService.getHashedValue(user.getPassword(), encodedSalt);
        return this.userMapper.saveUser(
                new User(null, user.getUsername(), encodedSalt,
                        hashedPassword, user.getFirstName(), user.getLastName())
        );
    }

    public User getUser(String username) {
        return this.userMapper.getUser(username);
    }


}
