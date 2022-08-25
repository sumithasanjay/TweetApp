package com.tweetapp.app.model;

import java.util.List;

public class TweetData {
	
	private List<Tweet> tweets;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String username;
	
	
	
	public List<Tweet> getTweets() {
		return tweets;
	}
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	

	
	

}
