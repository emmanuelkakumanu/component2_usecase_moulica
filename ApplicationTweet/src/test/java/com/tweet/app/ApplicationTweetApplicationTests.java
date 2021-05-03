package com.tweet.app;

import com.tweet.app.model.Tweet;
import com.tweet.app.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ApplicationTweetApplicationTests {

    String userUrl="http://localhost:9500/v1/User/register";
    String tweetUrl = "http://localhost:9500/v1/Tweet/postTweet/";
	RestTemplate template = new RestTemplate();

    User userData(){
        User user = new User();
        user.setFirstName("Moulica");
        user.setContactNumber(7036133542L);
        user.setLastName("Sudamalla");
        user.setPassWord("NewPassword");
        user.setLoginId("Moulica123");
        user.setMailId("moulica@gmail.com");
        return  user;
	}

	Tweet tweetData(){
    	Tweet tweet = new Tweet();
    	tweet.setTweetId(12345L);
    	tweet.setTweetMessage("Do or die never give up");
    	tweet.setLikesCount(3);
    	tweet.setHashTag("#positive vibes");
    	tweet.setUserLoginId("Moulica123");
    	tweet.setDateOfPost("14-04-2021");
    	return  tweet;
	}

   public void insertDetails(){
	   ResponseEntity<User>user = template.postForEntity(userUrl,userData(),User.class);
	   ResponseEntity<Tweet> tweet = template.postForEntity(tweetUrl,tweetData(),Tweet.class);

   }

}
