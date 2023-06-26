package com.ctoutweb.JDBCTemplate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.JDBCTemplate.model.UserCredential;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@PostMapping
	public ResponseEntity<Boolean> register(@RequestBody UserCredential user){
		try {
			return new ResponseEntity<>(true, HttpStatus.CREATED);	
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	
}
