package com.tweet.app.service;

import com.tweet.app.dao.ReplyRepository;
import com.tweet.app.dao.TweetRepository;
import com.tweet.app.model.Reply;
import com.tweet.app.model.Tweet;
import com.tweet.app.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository repository;

    public Reply createReply(Reply reply, Long tweetId) {

        Reply reply1=new Reply();
        String value= DateUtil.convertToTime();
        Date date= new Date();
        Long timeMilli = date.getTime();
        reply1.setReplyId(timeMilli);
        reply1.setTweetId(tweetId);
        reply1.setUserLoginId(reply.getUserLoginId());
        reply1.setReplyText(reply.getReplyText());
        reply1.setDateOfReply(value);
       return repository.save(reply1);

    }
}
