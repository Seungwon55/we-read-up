package com.wru.wrubookstore.service;

import com.wru.wrubookstore.dto.UserDto;

import java.util.List;

public interface UserService {

    int count() throws Exception;
    List<UserDto> selectAll() throws Exception;
    void deleteAll() throws Exception;
    int insert(UserDto userDto) throws Exception;
    UserDto select(String email) throws Exception;
    UserDto login(String email, String password);
    int update(UserDto userDto) throws Exception;
    int delete(String email) throws Exception;

}
