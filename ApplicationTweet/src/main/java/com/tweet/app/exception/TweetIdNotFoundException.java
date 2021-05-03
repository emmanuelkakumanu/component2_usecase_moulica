package com.tweet.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No tweetId available with the current id ...")

public class TweetIdNotFoundException extends Exception {
}
