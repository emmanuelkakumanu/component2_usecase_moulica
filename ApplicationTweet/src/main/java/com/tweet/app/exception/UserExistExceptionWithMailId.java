package com.tweet.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User already exists with the respective MailId ...")
public class UserExistExceptionWithMailId extends Exception {

//    public UserExistExceptionWithMailId(String message) {
//        super(message);
//    }
}
