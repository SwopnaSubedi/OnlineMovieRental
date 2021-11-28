package com.groupinsy.omr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

@Mapper
public interface UserMapper {
	
	User registerNewUserAccount(User userDto);

}
