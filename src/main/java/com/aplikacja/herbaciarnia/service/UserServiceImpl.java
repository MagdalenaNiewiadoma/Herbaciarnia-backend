package com.aplikacja.herbaciarnia.service;

import com.aplikacja.herbaciarnia.model.User;
import com.aplikacja.herbaciarnia.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserServiceImpl {

    private UserRepository userRepository;

    @Autowired

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;


        User user1 = new User();
        user1.setUserId(1);
        user1.setUsername("Mikolaj");
        user1.setEmail("mikolaj12@gmail.com");
        user1.setPassword("9osa@7");

        userRepository.save(user1);

    }
}
