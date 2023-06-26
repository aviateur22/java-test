package com.ctoutweb.JDBCTemplate.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.ctoutweb.JDBCTemplate.dto.UserInformationDTO;
import com.ctoutweb.JDBCTemplate.model.user.User;


@Service
public class UserInformationMapper implements Function<User, UserInformationDTO>  {

	@Override
	public UserInformationDTO apply(User user) {
		// TODO Auto-generated method stub
		return new UserInformationDTO(user.getName(), user.getLogin(), user.getEmail(),user.getCreatedAt(), user.getUpdatedAt());
	}

	
}
