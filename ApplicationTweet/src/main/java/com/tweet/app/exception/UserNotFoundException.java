package com.tweet.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not  available with the current loginid ...")

public class UserNotFoundException extends Exception {
}
