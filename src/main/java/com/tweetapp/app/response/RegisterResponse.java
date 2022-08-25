package com.tweetapp.app.response;

import org.bson.Document;

public class RegisterResponse {
	
	private String success;
	private String error;
	private String validationerror;
	private Document userData;
	
	
	
	
	public Document getUserData() {
		return userData;
	}
	public void setUserData(Document userData) {
		this.userData = userData;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getValidationerror() {
		return validationerror;
	}
	public void setValidationerror(String validationerror) {
		this.validationerror = validationerror;
	}
	
	

}
