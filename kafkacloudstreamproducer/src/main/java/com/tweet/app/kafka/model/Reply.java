package com.tweet.app.kafka.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Reply {
    @Id
    private Long replyId;
    private Long tweetId;
    private String replyText;
    private String userLoginId;
    private String dateOfReply;

}