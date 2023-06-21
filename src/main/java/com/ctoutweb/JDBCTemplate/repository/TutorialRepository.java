package com.ctoutweb.JDBCTemplate.repository;

import java.util.List;

import com.ctoutweb.JDBCTemplate.model.Tutorial;

public interface TutorialRepository {	
	int save(Tutorial tutorial);
	int update(Long id, Tutorial tutorial);	
	Tutorial findById(Long id);
	List<Tutorial> findAll();
	int deleteAll();
	int deleteById(Long id);
	

}
