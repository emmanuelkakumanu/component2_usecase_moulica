package com.tweet.app.dao;

import com.tweet.app.model.Tweet;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends MongoRepository<Tweet,Long> {

    List<Tweet > findByUserLoginId(String loginId);

    @DeleteQuery
    Long deleteByTweetId(Long tweetId);
}
