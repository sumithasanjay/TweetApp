package com.tweetapp.app.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.app.model.Register;
import com.tweetapp.app.response.RegisterResponse;
import com.tweetapp.app.service.RegisterService;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class RegisterController {
	
	@Autowired
	RegisterService registerservice;
	
	private static final Logger LOG = Logger.getLogger(RegisterController.class.getName());

	
	@PostMapping("/register")
	public RegisterResponse register(@RequestBody Register register) {
		RegisterResponse data= registerservice.registerdata(register);
		LOG.info(data + "Deleted successfully");
		return data;
		
	} 
	
	@GetMapping("/login")
	public RegisterResponse login(@RequestBody Register logindata) {
		RegisterResponse data= registerservice.logindata(logindata);
		return data;
		
	} 
	
	@GetMapping("/{username}/forgot")
	public RegisterResponse forgetPassword(@PathVariable String username, @RequestBody Register password) {
		RegisterResponse data= registerservice.forgetPassword(username,password);
		return data;
		
	}
	
	

}
