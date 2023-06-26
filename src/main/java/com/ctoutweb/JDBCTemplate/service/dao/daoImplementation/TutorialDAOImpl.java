package com.ctoutweb.JDBCTemplate.service.dao.daoImplementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.ctoutweb.JDBCTemplate.model.Tutorial;
import com.ctoutweb.JDBCTemplate.service.dao.TutorialDAO;

@Service
public class TutorialDAOImpl implements TutorialDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	
	MapSqlParameterSource mSQLParameterSource = new MapSqlParameterSource();

	@Override
	public int save(Tutorial tutorial) {
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(tutorial);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String query = "INSERT INTO tutorials (title, description, level, published, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
		
		Date createdDate = new Date();			
	
		int row =  jdbcTemplate.update(query,
				new Object[] {tutorial.getTitle(), tutorial.getDescription(), tutorial.getLevel(), tutorial.isPublished(), createdDate, createdDate});
		
		//System.out.println(keyHolder);
		return row;
	}

	@Override
	public int update(Long id, Tutorial tutorial) {
		Tutorial findTutorialById = findById(id);		
		
		if(findTutorialById == null) {
			return 0;
		}		
		
		return jdbcTemplate.update("UPDATE tutorials SET title=?, description=?, level=?, published=?, updated_at=? WHERE tutorials.id = ?",
			new Object[] {tutorial.getTitle(), tutorial.getDescription(), tutorial.getLevel(), tutorial.isPublished(), new Date(), id});	
	}

	@Override
	public Tutorial findById(Long id) {
		try {
			System.out.println(jdbcTemplate.queryForObject("SELECT * FROM tutorials WHERE id=?", BeanPropertyRowMapper.newInstance(Tutorial.class), id));
			return jdbcTemplate.queryForObject("SELECT * FROM tutorials WHERE id=?", BeanPropertyRowMapper.newInstance(Tutorial.class), id);
			
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}		
	}

	@Override
	public List<Tutorial> findAll() {
		List<Tutorial> tutorials = new ArrayList<>();		
		tutorials = jdbcTemplate.query("SELECT * FROM tutorials", BeanPropertyRowMapper.newInstance(Tutorial.class));		
		return tutorials;
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("DELETE FROM tutorials");
	}

	@Override
	public int deleteById(Long id) {
		Tutorial findTutorialById = findById(id);		
		
		if(findTutorialById == null) {
			return 0;
		}
		
		return jdbcTemplate.update("DELETE FROM tutorials WHERE id=?", id);
	}

}
