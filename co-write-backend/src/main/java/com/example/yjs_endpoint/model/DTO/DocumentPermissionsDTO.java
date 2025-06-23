package com.example.yjs_endpoint.model.DTO;

import lombok.Data;

@Data
public class DocumentPermissionsDTO {
    private String id;

    private String documentId;

    private String userId;

    private String permission;
}
