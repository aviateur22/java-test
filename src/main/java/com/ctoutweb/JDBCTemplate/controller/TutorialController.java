package com.ctoutweb.JDBCTemplate.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ctoutweb.JDBCTemplate.model.Tutorial;
import com.ctoutweb.JDBCTemplate.service.dao.daoImplementation.TutorialDAOImpl;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {
	
	@Autowired
	TutorialDAOImpl tutorialDAOImpl;
	
	@GetMapping
	public ResponseEntity<List<Tutorial>> getAllTutorials() {
		try {			
			// List<Tutorial> tutorials = new ArrayList<Tutorial>();
			// tutorialService.findAll().forEach(tutorials::add);
			List<Tutorial> tutorials = tutorialDAOImpl.findAll();
			
			if(tutorials.isEmpty()) {
				return new ResponseEntity<>(tutorials, HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(tutorials,HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> addTutorials(@RequestBody Tutorial tutorial) {
		try {			
			tutorialDAOImpl.save(tutorial);			
			return new ResponseEntity<>("ok", HttpStatus.CREATED);
			
		} catch (Exception e) {			
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") Long id) {
		try {
			
			Tutorial tutorial = tutorialDAOImpl.findById(id);
			
			if(tutorial == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}			
			
			
			return new ResponseEntity<>(tutorial, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String>updateTutorial(@PathVariable("id") Long id, @RequestBody Tutorial tutorial) {
		try {
			System.out.println(id);
			
			int rowAffected = tutorialDAOImpl.update(id, tutorial);
			
			if(rowAffected == 0) {
				return new ResponseEntity<>("no update", HttpStatus.NO_CONTENT);
			}			
			
			return new ResponseEntity<>("update ok", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String>DeleteOneTutorial(@PathVariable("id") Long id) {
		int rowAffected = tutorialDAOImpl.deleteById(id);
		
		if(rowAffected == 0) {
			return new ResponseEntity<>("no update", HttpStatus.NO_CONTENT);
		}			
		
		return new ResponseEntity<>("delete ok", HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> DeleteAll(){
		int rows = tutorialDAOImpl.deleteAll();
		
		if(rows > 0) {
			return new ResponseEntity<>("delete ok", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>("no data", HttpStatus.NO_CONTENT);		
	}
}
