package com.tweetapp.app.repository;


import org.bson.Document;

import com.tweetapp.app.model.Register;

public interface RegisterRepo {

	void registerdata(Register register);

    Document logindata(Register logindata);

    void forgotPassword(String username, Register password);  

}
  