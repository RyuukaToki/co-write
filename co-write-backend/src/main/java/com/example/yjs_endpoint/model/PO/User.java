package com.example.yjs_endpoint.model.PO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private Date createdAt;

    private Date updatedAt;
}
