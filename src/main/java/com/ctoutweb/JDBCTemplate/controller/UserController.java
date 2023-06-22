package com.ctoutweb.JDBCTemplate.controller;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctoutweb.JDBCTemplate.model.UserCredential;

@RestartScope
@RequestMapping("api/user")
public class UserController {
	
	@PostMapping("/register")
	public ResponseEntity<Boolean> register(@RequestBody UserCredential user){
		try {
			return new ResponseEntity<>(true, HttpStatus.ACCEPTED);	
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	
}
