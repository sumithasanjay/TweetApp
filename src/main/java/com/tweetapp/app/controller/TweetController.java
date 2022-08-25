package com.tweetapp.app.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.app.model.Tweet;
import com.tweetapp.app.model.TweetData;
import com.tweetapp.app.response.TweetResponse;
import com.tweetapp.app.service.TweetService;
@RestController
@RequestMapping("/api/v1.0/tweets")
public class TweetController {
	
	@Autowired
	TweetService tweetService;
	
	@Autowired
	KafkaTemplate<String,Object> kafkaTemplate;
	
	private static final String TOPIC="TestTopicSumi";
	
	private static final Logger LOG = Logger.getLogger(TweetController.class.getName());

	
	@PostMapping("/{username}/add")
	public TweetData register(@PathVariable String username,@RequestBody TweetData tweet) {
//		Tweet tweet=new Tweet();
//		tweet.setTweet("have fun");
//		tweet.setUsername(username);
		
		kafkaTemplate.send(TOPIC,tweet);
		
		 TweetData t=consume(tweet);
		
	
		return t;
		
		
//     TweetResponse response=tweetService.addTweet(username, tweet);
//      return response;
	} 

	@GetMapping("/getAllTweets")
	@KafkaListener(topics =TOPIC , 
	        groupId = "group_id")
	private TweetData consume(TweetData tweet) {
		System.out.print(tweet);
		return tweet;
	} 
	
	@GetMapping("/username")
	private TweetResponse getTweet (@RequestBody TweetData username) {
		TweetResponse tweet=new TweetResponse();
		tweet=tweetService.getAllTweetsUser(username.getUsername());
		return tweet;
		
	}
	
	@PutMapping("/{username}/update/{id}")
	public TweetResponse updateTweet(@PathVariable String username,@PathVariable Long id,@RequestBody Tweet data) {
		TweetResponse tweet=new TweetResponse();
		tweet=tweetService.updateTweet(username,id,data);
		LOG.info(id + "updated successfully");
		return tweet;
		
	}
	
	@DeleteMapping("/{username}/delete/{id}")
	public TweetResponse deleteTweet(@PathVariable String username,@PathVariable Long id) {
		TweetResponse response=new TweetResponse();
		response=tweetService.deleteTweet(username,id);
		LOG.info(id + "Deleted successfully");
		return response;
		
	}
	
	@PutMapping("/{username}/like/{id}")
	public TweetResponse likeTweet(@PathVariable String username,@PathVariable Long id,@RequestBody Tweet data) {
		TweetResponse tweet=new TweetResponse();
		tweet=tweetService.likeTweet(username,id,data);
		LOG.info(id + "Like Added successfully");
		return tweet;
		
	}
	@PostMapping("/{username}/reply/{id}")
	public TweetResponse replyTweet(@PathVariable String username,@PathVariable Long id,@RequestBody Tweet reply) {
		TweetResponse tweet=new TweetResponse();
		tweet=tweetService.replyTweet(username,id,reply);
		LOG.info(id + "Reply Added successfully");
		return tweet;
		
	}
	
	@GetMapping("/all")
	public TweetResponse getAll() {
		TweetResponse tweet=new TweetResponse();
		tweet=tweetService.getAll();
		return tweet;
	}
	@GetMapping("/user/search/{username}")
	public TweetResponse getOne(@PathVariable String username) {
		TweetResponse tweet=new TweetResponse();
		tweet=tweetService.getOne(username);
		return tweet;
	}
	@GetMapping("/users/all")
	public List<TweetData> allUsers() {
		List<TweetData> res=tweetService.allUsers();
		return res;
	}
}
