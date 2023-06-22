package com.ctoutweb.JDBCTemplate.service.dao;

import java.util.List;

import com.ctoutweb.JDBCTemplate.model.Tutorial;

public interface TutorialDAO {
	int save(Tutorial tutorial);
	int update(Long id, Tutorial tutorial);	
	Tutorial findById(Long id);
	List<Tutorial> findAll();
	int deleteAll();
	int deleteById(Long id);
}
