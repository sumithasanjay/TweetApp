package com.tweetapp.app.serviceImpl;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tweetapp.app.controller.TweetController;
import com.tweetapp.app.model.Register;
import com.tweetapp.app.repository.RegisterRepo;
import com.tweetapp.app.response.RegisterResponse;
import com.tweetapp.app.service.RegisterService;
import com.tweetapp.app.validation.RegisterValidations;

@Service("registerServiceImpl")
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	RegisterValidations validations;
	
	@Autowired
	RegisterRepo registerDao;
	private static final Logger LOG = Logger.getLogger(RegisterServiceImpl.class.getName());


	@Override
	public RegisterResponse registerdata(Register register) {
		
		RegisterResponse response= validations.getRegisterValidator(register);
		if (response.getValidationerror()==null) {
			try{
				registerDao.registerdata(register);
				response.setSuccess("Registered Successfully");
			}catch(Exception e) {
				e.printStackTrace();
				LOG.error(e); 
			}
		}
		return response;
	}

	@Override
	public RegisterResponse logindata(Register logindata) {
		RegisterResponse response= validations.getloginValidator(logindata);
		if (response.getValidationerror()==null) {
			try{
				Document doc=registerDao.logindata(logindata);
				response.setSuccess("login Success");
				response.setUserData(doc);
			}catch(Exception e) {
				LOG.error(e);
				e.printStackTrace();
			}
		}
		return response;
	}

	@Override
	public RegisterResponse forgetPassword(String username,Register password) {
	
		RegisterResponse response=new RegisterResponse();
		registerDao.forgotPassword(username,password);
		
		return response;
	}

}
