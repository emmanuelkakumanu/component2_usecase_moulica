package com.tweet.app.controller;

import com.tweet.app.exception.TweetIdNotFoundException;
import com.tweet.app.exception.TweetNotFoundException;
import com.tweet.app.model.DestinationModel;
import com.tweet.app.model.Tweet;
import com.tweet.app.service.TweetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Tweet")
public class TweetController {
    @Autowired
    TweetService service;
    @GetMapping("/getAllTweets")
    @ApiOperation("Returns list of all Tweets.")
    public ResponseEntity<List<DestinationModel>>getAllTweets() {
        return  ResponseEntity.ok(service.getTweets());
    }
    @GetMapping("/getTweetByUser/{userLoginId}")
    @ApiOperation("Returns a specific Tweet by their identifier. 404 if does not exist.")
    public ResponseEntity<List<DestinationModel>> getAllUsers(@ApiParam("Id  to be obtained. Cannot be empty.")@PathVariable("userLoginId") String loginId) throws TweetNotFoundException {
        return ResponseEntity.ok(service.getTweetByUser(loginId));

    }

    @PostMapping("/postTweet/{userLoginId}")
    @ApiOperation("Creates a new Tweet.")
    public ResponseEntity<?> postTweet(@ApiParam("Id  to be obtained. Cannot be empty.")@PathVariable("userLoginId") String loginId,@ApiParam("Tweet information for a new tweet to be created.")@RequestBody Tweet tweet){
           Tweet created=  service.createTweet(loginId,tweet);
          return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{tweetId}")
    @ApiOperation("Deletes a tweet . 404 if the tweets identifier is not found.")
    public  ResponseEntity<?> deleteTweet(@ApiParam("Id of the tweet to be deleted. Cannot be empty.")@PathVariable("tweetId") Long tweetId) throws TweetIdNotFoundException {
         service.deleteById(tweetId);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/updateTweet/{tweetId}")
    @ApiOperation("Updates a Tweet . 404 if the tweets identifier is not found.")
    public ResponseEntity<?> updateTweet(@ApiParam("Id of the tweet to be updated. Cannot be empty.")@PathVariable Long tweetId,@RequestBody Tweet tweet) throws TweetIdNotFoundException {
          return ResponseEntity.ok(service.updateTweet(tweetId,tweet));
    }

    @PutMapping("/like/{tweetId}")
    public  ResponseEntity updateTweetLike(@PathVariable Long tweetId) throws TweetIdNotFoundException {
        service.updateLike(tweetId);
        return ResponseEntity.ok().build();
    }
}
