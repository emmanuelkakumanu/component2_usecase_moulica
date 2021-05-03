package com.tweet.app.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class DestinationModel {
    @ApiModelProperty(hidden = true)
    @Id
    private Long tweetId;
    private String userLoginId;
    private String tweetMessage;
    private String hashTag;
    private String  dateOfPost;
    private List<Reply> replies;
    private int likesCount;
}
