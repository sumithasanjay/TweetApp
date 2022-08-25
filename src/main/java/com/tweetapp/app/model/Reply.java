package com.tweetapp.app.model;


public class Reply {
	
	private String replyData;
	private String username;
	private String tag;
	private String timeZone;
	
	
	
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getReplyData() {
		return replyData;
	}
	public void setReplyData(String replyData) {
		this.replyData = replyData;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

}
