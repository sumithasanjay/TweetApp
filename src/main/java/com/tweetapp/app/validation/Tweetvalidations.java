package com.tweetapp.app.validation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweetapp.app.model.Reply;
import com.tweetapp.app.model.Tweet;
import com.tweetapp.app.response.TweetResponse;

@Service
public class Tweetvalidations {

	public TweetResponse validateTweet(List<Tweet> list) { 
		TweetResponse response=new TweetResponse();
		for (Tweet tweet : list) {
			if(tweet.getTweetData().length()>144) {
				response.setError("Validation Error");
				response.setValidationerror("Tweet should not be greater than 144 characters");
			}
		}
		return response; 
		
	}

	public TweetResponse validateUsername(String username) { 
		TweetResponse response=new TweetResponse();
		if(username==null) {
			response.setError("Validation error");
			response.setValidationerror("Username should not be null");
		}
		return response;
	}

	public TweetResponse validateReply(List<Reply> reply) {
		TweetResponse response=new TweetResponse();
		for (Reply tweet : reply) {
			if(tweet.getReplyData().length()>144) {
				response.setError("Validation Error");
				response.setValidationerror("Reply should not be greater than 144 characters");
			}
		}
		return response; 
	}  
 
}
