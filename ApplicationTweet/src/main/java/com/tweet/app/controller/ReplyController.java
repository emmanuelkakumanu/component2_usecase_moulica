package com.tweet.app.controller;

import com.tweet.app.model.Reply;
import com.tweet.app.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Tweet/reply")
public class ReplyController {

    @Autowired
    private ReplyService service;
    @PostMapping("/{tweetId}")
    public ResponseEntity<?> postTweetReply(@RequestBody Reply reply, @PathVariable("tweetId") Long tweetId){
        Reply created= service.createReply(reply,tweetId);
        return  ResponseEntity.status(HttpStatus.CREATED).body(created);
    }



}
