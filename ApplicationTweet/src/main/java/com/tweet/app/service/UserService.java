package com.tweet.app.service;

import com.tweet.app.dao.UserRepository;
import com.tweet.app.exception.UserExistException;
import com.tweet.app.exception.UserExistExceptionWithMailId;
import com.tweet.app.exception.UserNotFoundException;
import com.tweet.app.model.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserService {

    @Value("${app.exception.userAlreadyAvailable}")
    private String userExistMessage;
    @Autowired
    private UserRepository repository;

    //for encodig password
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User saveUserList(User user) throws UserExistException, UserExistExceptionWithMailId {
      try{
          if(repository.findById(user.getLoginId()).isPresent()) {
//              var message = String.format("User %s exists already "+user.getLoginId());
              throw new UserExistException();

          }

          else {
              user.setPassWord(passwordEncoder().encode(user.getPassWord()));
              return  repository.save(user);
          }
      }catch (Exception e) {
//          var message = String.format("User %s exists already "+user.getLoginId());
      throw new UserExistExceptionWithMailId();
      }



    }
    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> searchById(String loginId) throws UserNotFoundException {
        Optional<User> user=  repository.findById(loginId);
        if(user.isPresent())
         return user;
       else
           throw new UserNotFoundException();


    }

    public Map<String, String> authenticate(String authHeader) {
        Map<String, String> jwt = new HashMap<String, String>();
        String user = getUser(authHeader);
        String token = generateJwt(user);
        User loggedInUser = repository.findByLoginId(user);
        jwt.put("token", token);
        jwt.put("username", loggedInUser.getLoginId());
        return jwt;
    }



    private String getUser(String authHeader) {
        String encodedCredentials = authHeader.split(" ")[1];
        byte[] decodedCredentials = Base64.getDecoder().decode(encodedCredentials);
        String user = new String(decodedCredentials).split(":")[0];
        return user;
    }

    private String generateJwt(String user) {
        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user); // Set the token issue time as current time
        builder.setIssuedAt(new Date()); // Set the token expiry as 20minutes from now
        builder.setExpiration(new Date((new Date()).getTime() + 120000));
        builder.signWith(SignatureAlgorithm.HS256, "secretkey");
        String token = builder.compact();
        return token;
    }

    public void updatePassword(User user) throws UserNotFoundException {
       User existed= repository.findByLoginId(user.getLoginId());
       if(existed == null)
           throw new  UserNotFoundException();
       else {
          if( user.getContactNumber().equals(existed.getContactNumber())) {
               existed.setPassWord(passwordEncoder().encode(user.getPassWord()));
//              System.out.println(existed);
              repository.save(existed);
          }
          else
              throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User Contact");

       }

    }
}
