package com.wru.wrubookstore.service;

import com.wru.wrubookstore.dto.UserDto;
import com.wru.wrubookstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int count() throws Exception {
        return userRepository.count();
    }

    @Override
    public List<UserDto> selectAll() throws Exception {
        return userRepository.selectAll();
    }

    @Override
    public void deleteAll() throws Exception {
        userRepository.deleteAll();
    }

    @Override
    public int insert(UserDto userDto) throws Exception {
        return userRepository.insert(userDto);
    }

    @Override
    public UserDto select(String email) throws Exception {
        return userRepository.select(email);
    }

    @Override
    public int update(UserDto userDto) throws Exception {
        return userRepository.update(userDto);
    }

    @Override
    public int delete(String email) throws Exception {
        return userRepository.delete(email);
    }
}
