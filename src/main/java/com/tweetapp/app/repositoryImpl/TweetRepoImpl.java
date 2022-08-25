package com.tweetapp.app.repositoryImpl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.OptionalLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.tweetapp.app.model.Reply;
import com.tweetapp.app.model.Tweet;
import com.tweetapp.app.model.TweetData;
import com.tweetapp.app.repository.TweetRepo;
import com.tweetapp.app.response.TweetResponse;
import com.tweetapp.app.serviceImpl.SequenceGeneratorService;


@Repository
public class TweetRepoImpl implements TweetRepo {
	
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	SequenceGeneratorService sequenceGenerator;


	@Override
	public TweetResponse add(TweetData tweet,String username) {
   
		TweetResponse response=new TweetResponse();
		Query query=new Query();
		query.addCriteria(Criteria.where("username").is(username));
		TweetData tweetdata=mongoTemplate.findOne(query, TweetData.class); 
		if(tweetdata!=null) {
			Update update=new Update(); 
			List<Tweet> queryData=tweetdata.getTweets();
			List<Tweet> tw=tweet.getTweets();
			tw.forEach(s ->{
				OptionalLong num=queryData.stream().mapToLong(Tweet::getId).max(); 
				s.setId(num.getAsLong()+1); 
				s.setLikes(0);
				update.push("tweets", s);
			});
			mongoTemplate.updateFirst( query, update, TweetData.class);
		    response.setSuccess("Tweet added successfully");
		   		    
		}else { 
		tweet.setUsername(username);
	    mongoTemplate.save(tweet);
	    response.setSuccess("Tweet added successfully");
		}
		return response;

	}

	@Override
	public List<TweetData> getUserTweet(String username) {
		Query query=new Query();
		query.addCriteria(Criteria.where("username").is(username));
		List<TweetData> response=mongoTemplate.find(query, TweetData.class);
		
		return response;  
	}

	@Override
	public TweetResponse update(Tweet data, String username, Long id) {
		TweetResponse response=new TweetResponse();
		try {
			
			Query query=new Query();
			query.addCriteria(Criteria.where("username").is(username).and("tweets.id").is(id)); 
			Update update=new Update(); 
//			List<Tweet> tw=data.getTweets();
//			tw.forEach(s ->{
            update.set("tweets.$.tweetData", data.getTweetData());
            mongoTemplate.updateFirst(query, update,TweetData.class);
            response.setSuccess("Tweet Updated Successfully");
//			});
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return response;
	}

	@Override
	public TweetResponse delete(String username, Long id) {
		TweetResponse response=new TweetResponse();
		try {
		Query query=new Query();
		query.addCriteria(Criteria.where("username").is(username).and("tweets.id").is(id)); 
		Update update=new Update(); 
		update.unset("tweets.$");
		mongoTemplate.updateFirst(query, update,TweetData.class);
		response.setSuccess("Tweet Deleted Successfully"); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public TweetResponse like(String username, Long id, Tweet data) {
		TweetResponse response=new TweetResponse();
		try {
			
			Query query=new Query();
		    query.addCriteria(Criteria.where("username").is(username).and("tweets._id").is(id)); 
			Update update=new Update(); 
            update.inc("tweets.$.likes",data.getLikes());
            mongoTemplate.updateFirst(query, update,TweetData.class);
            response.setSuccess("Likes Added Successfully");
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return response;
	}

	@Override
	public TweetResponse reply(String username, Long id, Tweet data) {
		TweetResponse response=new TweetResponse();
		Query query=new Query();
	    query.addCriteria(Criteria.where("username").is(username).and("tweets._id").is(id));
	    Update update=new Update();
	    List<Reply> reply=data.getReply();
	    reply.forEach(s -> {
	    	SimpleDateFormat formatter=new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
	        Date date=new Date();
	       s.setTimeZone(formatter.format(date));
		   update.push("tweets.$.reply", s);
		   try {
           mongoTemplate.updateFirst(query, update,Tweet.class);
		   }catch(Exception e) {
			   e.printStackTrace();
		   }

           response.setSuccess("Reply posted");
	    });
		return response;
	}

	@Override
	public TweetResponse getAlldata() {
		TweetResponse response =new TweetResponse();
	    List<TweetData> data=mongoTemplate.findAll(TweetData.class);
	    for (TweetData tweetData : data) {
			response.setTweet(tweetData.getTweets());
			 
		}
		return response;
	}

	@Override
	public TweetResponse getOne(String username) {
		TweetResponse response=new TweetResponse();
		Query query=new Query();
		query.addCriteria(Criteria.where("username").is(username)); 
		TweetData data=mongoTemplate.findOne(query, TweetData.class);
		response.setData(data); 
		return response;
	}

	@Override
	public List<TweetData> allUsers() {
		TweetResponse response=new TweetResponse();
		List<TweetData> data=mongoTemplate.findAll(TweetData.class);
		
		return data;
	}

}
