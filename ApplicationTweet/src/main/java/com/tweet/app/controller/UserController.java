package com.tweet.app.controller;

import com.tweet.app.exception.UserExistException;
import com.tweet.app.exception.UserExistExceptionWithMailId;
import com.tweet.app.exception.UserNotFoundException;
import com.tweet.app.model.User;
import com.tweet.app.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/User")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    @ApiOperation("Creates a new User.")
    public ResponseEntity<?> saveUser(@ApiParam("User information for a new user to be created.")@RequestBody User user) throws UserExistException, UserExistExceptionWithMailId {
        User created = service.saveUserList(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping("/getAllUsers")
    @ApiOperation("Returns list of all users.")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/searchByUserId/{loginId}")
    @ApiOperation("Returns a specific user by their identifier. 404 if does not exist.")
    public ResponseEntity<Optional<User>> getUserById(@ApiParam("Id  to be obtained. Cannot be empty.")@PathVariable String loginId) throws UserNotFoundException {
        return  ResponseEntity.ok(service.searchById(loginId));
    }

    @GetMapping("/loginUsers")
    public Map<String, String> login(@RequestHeader("Authorization") String authHeader) {
        System.out.println(authHeader);
        return service.authenticate(authHeader);
    }
   @PutMapping("/forgot")
    public void setPassword(@RequestBody User user) throws UserNotFoundException {
        service.updatePassword(user);

   }
}
