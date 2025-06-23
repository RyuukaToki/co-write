package com.example.yjs_endpoint.model.VO;

import com.example.yjs_endpoint.model.PO.User;
import lombok.Data;

@Data
public class LoginResult {
    private String token;
    private User user;

    public LoginResult(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
