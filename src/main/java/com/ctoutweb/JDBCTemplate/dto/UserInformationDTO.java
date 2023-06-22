package com.ctoutweb.JDBCTemplate.dto;

import java.util.Date;

public record UserInformationDTO(
		String name,
		String login,
		String email,
		Date createdAt,
		Date updatedAt
		) {	
}
