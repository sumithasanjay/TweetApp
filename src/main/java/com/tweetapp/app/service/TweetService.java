package com.tweetapp.app.service;

import java.util.List;

import com.tweetapp.app.model.Reply;
import com.tweetapp.app.model.Tweet;
import com.tweetapp.app.model.TweetData;
import com.tweetapp.app.response.TweetResponse;

public interface TweetService { 

	TweetResponse addTweet(String username, TweetData tweet);

	TweetResponse getAllTweetsUser(String username);

	TweetResponse updateTweet(String username, Long id, Tweet data);

	TweetResponse deleteTweet(String username, Long id);

	TweetResponse likeTweet(String username, Long id, Tweet data); 
 
	TweetResponse replyTweet(String username, Long id, Tweet reply);

	TweetResponse getAll();

	TweetResponse getOne(String username);

	List<TweetData> allUsers();      
  
}
     