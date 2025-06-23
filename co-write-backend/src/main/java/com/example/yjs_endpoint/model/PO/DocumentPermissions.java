package com.example.yjs_endpoint.model.PO;

import lombok.Data;

import java.util.Date;

@Data
public class DocumentPermissions {
    private String id;

    private String documentId;

    private String userId;

    private String permission;

    private Date createdAt;

    private Date updatedAt;
}
