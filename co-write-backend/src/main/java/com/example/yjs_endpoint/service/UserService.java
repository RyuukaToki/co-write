package com.example.yjs_endpoint.service;

import com.example.yjs_endpoint.model.DTO.UserDTO;
import com.example.yjs_endpoint.model.PO.User;

public interface UserService {
    User register(UserDTO dto);
    String login(String username, String password);
    User getById(String userId);
    User getByUsername(String username);
}
