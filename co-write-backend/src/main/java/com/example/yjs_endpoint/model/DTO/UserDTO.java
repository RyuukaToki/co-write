package com.example.yjs_endpoint.model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserDTO {
    private Long id;

    private String username;

    private String password;
}
