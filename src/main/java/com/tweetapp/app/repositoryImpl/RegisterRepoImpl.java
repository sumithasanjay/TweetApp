package com.tweetapp.app.repositoryImpl;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.tweetapp.app.model.Register;
import com.tweetapp.app.model.TweetData;
import com.tweetapp.app.repository.RegisterRepo;

@Repository("registerRepoImpl")
public class RegisterRepoImpl implements RegisterRepo {
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void registerdata(Register register) {
		mongoTemplate.save(register, "registration");
		
	}

	@Override
	public Document logindata(Register logindata) {
		Aggregation aggregation=Aggregation.newAggregation(Aggregation.match(Criteria.where("loginId").is(logindata.getLoginId()) .andOperator(Criteria.where("password").is(logindata.getPassword()))),Aggregation.project(Register.class));
		AggregationResults<Document> result= mongoTemplate.aggregate(aggregation, "registration", Document.class);
		Document doc=result.getUniqueMappedResult();
		return doc;
		
		 
	}

	@Override
	public void forgotPassword(String username,Register password) {
		Query query=new Query();
		query.addCriteria(Criteria.where("username").is(username));
		Register data=mongoTemplate.findOne(query, Register.class);
		Update update=new Update();
		update.set("password", password);
        mongoTemplate.updateFirst(query, update, Register.class);
		
	}

}
