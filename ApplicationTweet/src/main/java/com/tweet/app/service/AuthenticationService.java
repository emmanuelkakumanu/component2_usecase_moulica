package com.tweet.app.service;

import com.tweet.app.dao.UserRepository;
import com.tweet.app.model.AppUser;
import com.tweet.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {



    @Autowired
    private UserRepository userRepository;

    public AuthenticationService(UserRepository repository) {
        this.userRepository = repository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(name);
        System.err.println("in loadbyuser");
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return new AppUser(user);
        }
    }
}
