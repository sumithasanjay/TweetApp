package com.tweetapp.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.app.model.Tweet;
import com.tweetapp.app.model.TweetData;
import com.tweetapp.app.repository.TweetRepo;
import com.tweetapp.app.response.TweetResponse;
import com.tweetapp.app.service.TweetService;
import com.tweetapp.app.validation.Tweetvalidations;

@Service
public class TweetServiceImpl implements TweetService {
	
	@Autowired
	Tweetvalidations tweetValidatiuons;
	
	@Autowired
	TweetRepo tweetRepo;

	@Override
	public TweetResponse addTweet(String username, TweetData tweets) {
		TweetResponse response =new TweetResponse();
		response=tweetValidatiuons.validateTweet(tweets.getTweets());
		if(response.getValidationerror()==null) {
			response=tweetRepo.add(tweets,username);
		}
		return response;
	}

	@Override
	public TweetResponse getAllTweetsUser(String username) {
		TweetResponse response =new TweetResponse();
		response=tweetValidatiuons.validateUsername(username);
		if(response.getValidationerror()==null) {
			List<TweetData> tweet=tweetRepo.getUserTweet(username);
		    response.setUserTweet(tweet);
		    response.setSuccess("Tweets Retrieved Successfully"); 
		}
		return response;
	}

	@Override
	public TweetResponse updateTweet(String username, Long id, Tweet data) {
		TweetResponse response =new TweetResponse();
//		response=tweetValidatiuons.validateTweet(data.getTweets());
		if(response.getValidationerror()==null) {
			response=tweetRepo.update(data,username,id);
		}
		return response;
	}

	@Override
	public TweetResponse deleteTweet(String username, Long id) {
		TweetResponse response =tweetRepo.delete(username,id);
		return response;
	}

	@Override
	public TweetResponse likeTweet(String username, Long id, Tweet data) {
		TweetResponse response =tweetRepo.like(username,id,data);
		return response;
	}

	@Override
	public TweetResponse replyTweet(String username, Long id, Tweet data) {
		TweetResponse response =new TweetResponse();
		response=tweetValidatiuons.validateReply(data.getReply());
		if(response.getValidationerror()==null) {
		 response =tweetRepo.reply(username,id,data);
		}
		return response;
	}

	@Override
	public TweetResponse getAll() {
		TweetResponse response =new TweetResponse();
		 response =tweetRepo.getAlldata();
	return response;
	}

	@Override
	public TweetResponse getOne(String username) {
		TweetResponse response =new TweetResponse();
		 response =tweetRepo.getOne(username);
	return response;
	}

	@Override
	public List<TweetData> allUsers() {
		List<TweetData> res =tweetRepo.allUsers();
	return res;
	}
 

}
