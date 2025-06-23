package com.example.yjs_endpoint.service.impl;

import com.example.yjs_endpoint.exception.BusinessException;
import com.example.yjs_endpoint.mapper.UserMapper;
import com.example.yjs_endpoint.model.DTO.UserDTO;
import com.example.yjs_endpoint.model.PO.User;
import com.example.yjs_endpoint.service.UserService;
import com.example.yjs_endpoint.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider tokenProvider;

    // 用户注册
    public User register(UserDTO dto) {
        if (userMapper.existsByUsername(dto.getUsername())) {
            throw new BusinessException("用户名已存在");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        userMapper.insert(user);
        return user;
    }

    // 用户登录
    public String login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new BusinessException("用户名或密码错误");
        }
        return tokenProvider.generateToken(user.getId().toString());
    }

    // 根据ID获取用户
    public User getById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    // 根据用户名获取用户
    public User getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
