package com.ctoutweb.JDBCTemplate.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ctoutweb.JDBCTemplate.model.Tutorial;
import com.ctoutweb.JDBCTemplate.service.TutorialService;

@RestController
@RequestMapping("/api")
public class TutorialController {
	
	@Autowired
	TutorialService tutorialService;
	
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials() {
		try {			
			List<Tutorial> tutorials = new ArrayList<Tutorial>();
			tutorialService.findAll().forEach(tutorials::add);
			
			if(tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(tutorials,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/tutorial")
	public ResponseEntity<String> addTutorials(@RequestBody Tutorial tutorial) {
		try {
			System.out.println(tutorial);
			int t = tutorialService.save(tutorial);
			System.out.println(t);
			return new ResponseEntity<>("ok", HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
