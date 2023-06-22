package com.ctoutweb.JDBCTemplate.service.dao;

import java.util.List;
import com.ctoutweb.JDBCTemplate.dto.UserInformationDTO;
import com.ctoutweb.JDBCTemplate.model.User;

public interface UserDAO {
	public int save(User user);
	public UserInformationDTO findUserById(Long id);
	public User findUserByEmail(String email);
	public List<UserInformationDTO> getAllUser();	
	public int update(Long id, User user);
	public int deleteUserById(Long id);
}
