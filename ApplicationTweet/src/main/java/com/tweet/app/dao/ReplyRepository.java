package com.tweet.app.dao;

import com.tweet.app.model.Reply;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends MongoRepository<Reply,Long> {

    @DeleteQuery
    void deleteByTweetId(Long tweetId);
}
