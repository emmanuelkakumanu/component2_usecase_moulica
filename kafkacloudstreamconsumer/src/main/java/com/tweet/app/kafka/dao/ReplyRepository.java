package com.tweet.app.kafka.dao;

import com.tweet.app.kafka.model.Reply;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends MongoRepository<Reply,Long> {
}
