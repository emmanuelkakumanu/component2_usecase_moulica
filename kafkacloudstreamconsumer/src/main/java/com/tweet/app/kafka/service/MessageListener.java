package com.tweet.app.kafka.service;

import com.tweet.app.kafka.dao.ReplyRepository;
import com.tweet.app.kafka.model.Reply;
import com.tweet.app.kafka.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageListener {
    @Autowired
    private ReplyRepository repository;
    @KafkaListener(topics = "ReplyInfoTopic", groupId = "my-app", containerFactory = "userKafkaListenerFactory")
    public void consumeReply( Reply reply) {
        Reply reply1=new Reply();
        String value= DateUtil.convertToTime();
        Date date= new Date();
        Long timeMilli = date.getTime();
        reply1.setReplyId(timeMilli);
        reply1.setTweetId(reply.getTweetId());
        reply1.setUserLoginId(reply.getUserLoginId());
        reply1.setReplyText(reply.getReplyText());
        reply1.setDateOfReply(value);
       repository.save(reply1);

    }



}
