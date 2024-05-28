package com.firewheels.auth_service.service;

import com.firewheels.auth_service.model.User;
import com.firewheels.auth_service.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void saveUser(User user) {
        LOGGER.info("Saving user: " + user);
        userRepository.save(user);
        LOGGER.info("User saved: " + user);
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findFirstByEmailAndPassword(email, password);
        return user != null;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById((long) id).orElse(null);
    }
}
