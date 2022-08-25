package com.tweetapp.app.repository;

import java.util.List;

import com.tweetapp.app.model.Reply;
import com.tweetapp.app.model.Tweet;
import com.tweetapp.app.model.TweetData;
import com.tweetapp.app.response.TweetResponse;

public interface TweetRepo {

	TweetResponse add(TweetData tweets, String username);

	List<TweetData> getUserTweet(String username); 

	TweetResponse update(Tweet data, String username, Long id);

	TweetResponse delete(String username, Long id);
 
	TweetResponse like(String username, Long id, Tweet data); 

	TweetResponse reply(String username, Long id, Tweet data);

	TweetResponse getAlldata();

	TweetResponse getOne(String username);

	List<TweetData> allUsers();    
    
  
}  
   