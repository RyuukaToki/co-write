package com.example.yjs_endpoint.controller;

import com.example.yjs_endpoint.model.VO.LoginResult;
import com.example.yjs_endpoint.model.DTO.UserDTO;
import com.example.yjs_endpoint.model.PO.User;
import com.example.yjs_endpoint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO dto) {
        User user = userService.register(dto);
        return ResponseEntity.ok(user);
    }

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<LoginResult> login(@RequestBody UserDTO dto) {
        String token = userService.login(dto.getUsername(), dto.getPassword());
        User user = userService.getByUsername(dto.getUsername());
        return ResponseEntity.ok(new LoginResult(token, user));
    }

    // 获取当前用户信息
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("X-User-Id") String userId) {
        User user = userService.getById(userId);
        return ResponseEntity.ok(user);
    }
}
