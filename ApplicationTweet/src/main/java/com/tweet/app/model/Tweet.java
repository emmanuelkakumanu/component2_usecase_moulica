package com.tweet.app.model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;


@Data
@Document(collection = "tweet")
public class Tweet {

    @ApiModelProperty(hidden = true)
    @Id
    private Long tweetId;
    private String userLoginId;
    private String tweetMessage;
    private String hashTag;
    @ApiModelProperty(hidden = true)
    private String  dateOfPost;
    private int likesCount;

}
