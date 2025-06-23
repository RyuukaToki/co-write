package com.example.yjs_endpoint.model.PO;

import lombok.Data;

import java.util.Date;

@Data
public class KnowledgeBase {
    private Long id;

    private String name;

    private String coverUrl;

    private String ownerId;

    private Date createdAt;

    private Date updatedAt;
}
