package com.tweet.app.service;

import com.tweet.app.dao.ReplyRepository;
import com.tweet.app.dao.TweetRepository;
import com.tweet.app.dao.UserRepository;
import com.tweet.app.exception.TweetIdNotFoundException;
import com.tweet.app.exception.TweetNotFoundException;
import com.tweet.app.model.DestinationModel;
import com.tweet.app.model.Tweet;
import com.tweet.app.model.User;
import com.tweet.app.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TweetService {


    @Autowired
    MongoTemplate template;
    @Autowired
    TweetRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReplyRepository replyRepository;
    public List<DestinationModel> getTweets() {

        LookupOperation lookup = LookupOperation.newLookup()
                .from("reply")
                .localField("_id")
                .foreignField("tweetId")
                .as("replies");
        Aggregation aggregation = Aggregation.newAggregation(lookup,Aggregation.sort(Sort.Direction.DESC, "dateOfPost"));
        List<DestinationModel> posts = template
                .aggregate(aggregation, "tweet", DestinationModel.class).getMappedResults();
        return posts;
    }

    public List<DestinationModel> getTweetByUser(String loginId) throws TweetNotFoundException {
//         User tweet= userRepository.findById(loginId);
        List<Tweet> userTweets= repository.findByUserLoginId(loginId);
        if(userTweets.isEmpty())
            throw  new TweetNotFoundException();
        else {
            LookupOperation lookup = LookupOperation.newLookup()
                    .from("reply")
                    .localField("_id")
                    .foreignField("tweetId")
                    .as("replies");
            Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("userLoginId").is(loginId)),
                    lookup);
            List<DestinationModel> userPosts = template
                    .aggregate(aggregation, "tweet", DestinationModel.class).getMappedResults();
            return userPosts;

//            return userTweets;
        }
    }

    public Tweet createTweet(String loginId,Tweet tweet) {
        Tweet tweet1 = new Tweet();
//        UUID uuid= UUID.randomUUID();
//        String value= uuid.toString();
        Date date= new Date();
        String value= DateUtil.convertToTime();
        Long timeMilli = date.getTime();
        tweet1.setTweetId(timeMilli);
        tweet1.setUserLoginId(loginId);
        tweet1.setTweetMessage(tweet.getTweetMessage());
        tweet1.setHashTag(tweet.getHashTag());
        tweet1.setDateOfPost(value);
//        tweet1.setReplies(tweet.getReplies());
        return  repository.save(tweet1);
    }

    public void deleteById(Long tweetId) throws TweetIdNotFoundException {
        Long status = null;
        if(repository.findById(tweetId).isPresent()) {
            status = repository.deleteByTweetId(tweetId);
            replyRepository.deleteByTweetId(tweetId);
        }
        else if(status == 0)

            throw new TweetIdNotFoundException();
    }

    public Tweet updateTweet(Long tweetId, Tweet tweet) throws TweetIdNotFoundException {
        if(repository.findById(tweetId).isPresent()) {
            Tweet t = repository.findById(tweetId).get();
             t.setTweetMessage(tweet.getTweetMessage());
             t.setHashTag(tweet.getHashTag());
             return  repository.save(t);
        }
        else
            throw new TweetIdNotFoundException();

    }

    public void updateLike(Long tweetId) throws TweetIdNotFoundException {
        if(repository.findById(tweetId).isPresent()){
            Tweet t1 = repository.findById(tweetId).get();
            int likes= t1.getLikesCount();
            likes++;
            t1.setLikesCount(likes);
             repository.save(t1);
        }
        else
            throw  new TweetIdNotFoundException();
    }
}
