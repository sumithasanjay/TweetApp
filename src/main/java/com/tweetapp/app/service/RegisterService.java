package com.tweetapp.app.service;

import com.tweetapp.app.model.Register;
import com.tweetapp.app.response.RegisterResponse;

public interface RegisterService {

	RegisterResponse registerdata(Register register);

	RegisterResponse logindata(Register logindata);

	RegisterResponse forgetPassword(String username, Register password);

} 
   