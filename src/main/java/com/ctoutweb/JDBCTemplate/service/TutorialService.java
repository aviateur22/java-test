package com.ctoutweb.JDBCTemplate.service;

import java.util.List;


import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ctoutweb.JDBCTemplate.model.Tutorial;
import com.ctoutweb.JDBCTemplate.repository.TutorialRepository;

@Service
public class TutorialService implements TutorialRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int save(Tutorial tutorial) {
		//String created = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());		
		
		return jdbcTemplate.update("INSERT INTO tutorials (title, description, level, published, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)",
				new Object[] {tutorial.getTitle(), tutorial.getDescription(), tutorial.getLevel(), tutorial.isPublished(), tutorial.getCreatedAt(), tutorial.getUpdatedAt()});	
	}

	@Override
	public int update(Tutorial tutorial) {	
		return jdbcTemplate.update("UPDATE tutorials SET title=?, description=?, level=?, published=?, created_at=?, updated_at=? WHERE tutorials.id = ?",
			new Object[] {tutorial.getTitle(), tutorial.getDescription(), tutorial.getLevel(), tutorial.isPublished(), tutorial.getCreatedAt(), tutorial.getUpdatedAt()});	
	}

	@Override
	public Tutorial findById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM tutorials WHERE id=?", BeanPropertyRowMapper.newInstance(Tutorial.class), id);
	}

	@Override
	public List<Tutorial> findAll() {
		return jdbcTemplate.query("SELECT * FROM tutorials", BeanPropertyRowMapper.newInstance(Tutorial.class));
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("DELETE FROM tutorials");
	}

}
