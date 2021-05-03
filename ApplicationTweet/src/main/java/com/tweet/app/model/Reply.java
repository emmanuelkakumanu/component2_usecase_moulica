package com.tweet.app.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Reply {
    @ApiModelProperty(hidden = true)
    @Id
    private Long replyId;
    private Long tweetId;
    private String replyText;
    private String userLoginId;
    private String dateOfReply;

}
