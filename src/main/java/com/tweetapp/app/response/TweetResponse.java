package com.tweetapp.app.response;

import java.util.List;

import org.bson.Document;

import com.tweetapp.app.model.Tweet;
import com.tweetapp.app.model.TweetData;

public class TweetResponse {
	
	private String tweetResponse;
	
	private String error;
	
	private String validationerror;
	
	private String success;
	
	private List<TweetData> userTweet;
	
	private Document reply;
	
	private TweetData data;
	
	private List<Tweet> tweet;
	
	private String username;
	
	
	
	
	

	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	

	public List<Tweet> getTweet() {
		return tweet;
	}

	public void setTweet(List<Tweet> tweet) {
		this.tweet = tweet;
	}

	
	public TweetData getData() {
		return data;
	}

	public void setData(TweetData data) {
		this.data = data;
	}

	public Document getReply() {
		return reply;
	}

	public void setReply(Document reply) {
		this.reply = reply;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getValidationerror() {
		return validationerror;
	}

	public void setValidationerror(String validationerror) {
		this.validationerror = validationerror;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getTweetResponse() {
		return tweetResponse;
	}

	public void setTweetResponse(String tweetResponse) {
		this.tweetResponse = tweetResponse;
	}

	public List<TweetData> getUserTweet() {
		return userTweet;
	}

	public void setUserTweet(List<TweetData> userTweet) {
		this.userTweet = userTweet;
	}

	
	

}
