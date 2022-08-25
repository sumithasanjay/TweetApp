package com.tweetapp.app.validation;

import org.springframework.stereotype.Service;

import com.tweetapp.app.model.Register;
import com.tweetapp.app.response.RegisterResponse;

@Service
public class RegisterValidations {

	public RegisterResponse getRegisterValidator(Register register) {
	RegisterResponse response=new RegisterResponse();
		if(register.getFirstNmae()== null || register.getLastname()== null || register.getEmailId()== null
				||register.getLoginId()== null || register.getPassword()== null || register.getContactNumber()== null
				||register.getConfirmPassword()== null || register.getUsername()==null
				) {
			response.setError("validationError");
			response.setValidationerror("All the fields are Mandatory");
			return response;
		}if(!(register.getLoginId().equals(register.getEmailId()))) {
			response.setError("validationError");
			response.setValidationerror("LoginId and EmailId must be same");
			return response;
		}if(!(register.getPassword().equals(register.getConfirmPassword()))) {
			response.setError("validationError");
			response.setValidationerror("password and confirmpassword must be same");
			return response;
		}
		return response;
		 
		
	}

	public RegisterResponse getloginValidator(Register register) {
		RegisterResponse response=new RegisterResponse();
		if(register.getLoginId()== null || register.getPassword()== null ) {
			response.setError("validationError");
			response.setValidationerror("LoginId and Password are mandatory to login");
		}
		return response;
	}
	

}
