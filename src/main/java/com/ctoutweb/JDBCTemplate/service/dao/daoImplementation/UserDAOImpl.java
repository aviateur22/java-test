package com.ctoutweb.JDBCTemplate.service.dao.daoImplementation;


import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.ctoutweb.JDBCTemplate.model.User;
import com.ctoutweb.JDBCTemplate.service.dao.UserDAO;

import com.ctoutweb.JDBCTemplate.dto.UserInformationDTO;
import com.ctoutweb.JDBCTemplate.mapper.UserInformationMapper;

@Service
public class UserDAOImpl implements UserDAO {
	@Autowired
	UserInformationMapper userInformationMapper;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int save(User user) {
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
		
		String query = "INSERT INTO users (login, email, password, name, created_at, updated_at) VALUES (:login, :email, :password, :name , :createdAt, :updatedAt)";
		
		return namedParameterJdbcTemplate.update(query, sqlParameterSource);
	}

	@Override
	public UserInformationDTO findUserById(Long id) {
		try {
			String query = "SELECT * FROM users WHERE id=:id";
			
			User findUser = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(User.class), id);			
			
			return new UserInformationDTO(findUser.getName(), findUser.getLogin(), findUser.getEmail(),findUser.getCreatedAt(), findUser.getUpdatedAt());
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
		
		
	}

	@Override
	public User findUserByEmail(String email) {
		try {
			String query = "SELECT * FROM users WHERE email=:email";
			
			return jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(User.class), email);
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}		
	}

	@Override
	public List<UserInformationDTO> getAllUser() {
		List<User> users = new ArrayList<>();
		
		String query = "INSERT INTO users (login, email, password, name, created_at, updated_at) VALUES (:login, :email, :password, :name , :createdAt, :updatedAt)";
		
		users = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(User.class));
		
		return users.stream().map(userInformationMapper).collect(Collectors.toList());
	}

	@Override
	public int update(Long id, User user) {
		UserInformationDTO findUser = findUserById(id);
		
		if(findUser == null) {
			return 0;
		}
		
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
		
		String query = "UPDATE users SET email=:email, name=:name, login=:login WHERE id=:id";
		
		int row = namedParameterJdbcTemplate.update(query, sqlParameterSource);
		
		return row;
	}

	@Override
	public int deleteUserById(Long id) {		
		UserInformationDTO findUser = findUserById(id);
		
		if(findUser == null) {
			return 0;
		}		
		
		String query = "DELETE users WHERE id=:id";
		
		int row = jdbcTemplate.update(query, id);
		
		return row;
	}

}
