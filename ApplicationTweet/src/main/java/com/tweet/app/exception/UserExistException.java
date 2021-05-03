package com.tweet.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User already exists with respective loginId ...")
public class UserExistException extends  Exception {


}
