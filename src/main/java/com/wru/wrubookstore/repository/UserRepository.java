package com.wru.wrubookstore.repository;

import com.wru.wrubookstore.dto.UserDto;

import java.util.List;

public interface UserRepository {

    int count() throws Exception;
    List<UserDto> selectAll() throws Exception;
    void deleteAll() throws Exception;
    int insert(UserDto userDto) throws Exception;
    UserDto select(String email) throws Exception;
    UserDto selectByEmailAndPassword(String email, String password) throws Exception;
    int update(UserDto userDto) throws Exception;
    int delete(String email) throws Exception;

}
