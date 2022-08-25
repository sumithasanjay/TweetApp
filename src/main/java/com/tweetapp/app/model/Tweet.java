package com.tweetapp.app.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tweetData")
public class Tweet {
	
	@Transient
    public static final String SEQUENCE_NAME = "tweetData_sequence";
	
	@Id 
	private long id;
	
	private String tweetData;
	private Integer likes;
	private String tag;
	private List<Reply> reply;
	
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public List<Reply> getReply() {
		return reply;
	}
	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTweetData() {
		return tweetData;
	}
	public void setTweetData(String tweetData) {
		this.tweetData = tweetData;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}


}
