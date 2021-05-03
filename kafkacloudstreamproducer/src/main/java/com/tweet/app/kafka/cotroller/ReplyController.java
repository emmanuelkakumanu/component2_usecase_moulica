package com.tweet.app.kafka.cotroller;

import com.tweet.app.kafka.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Tweet/reply")
public class ReplyController {
    @Autowired
    private KafkaTemplate<String,Reply> kafkaTemplate;
    @Value("${kafka.topic}")
    private  String topicName;

    @PostMapping("/{tweetId}")
    public void postTweetReply(@RequestBody Reply reply, @PathVariable("tweetId") Long tweetId){
         reply.setTweetId(tweetId);
//        Message<Reply> message = MessageBuilder.withPayload(reply).build();
         kafkaTemplate.send(topicName,"Message", reply);

    }



}
